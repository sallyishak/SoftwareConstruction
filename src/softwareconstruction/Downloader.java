package multipartdownloader;

//Final Code
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import org.apache.log4j.Logger;


public class Downloader
{
    final static Logger logger = Logger.getLogger(Downloader.class);

    public Downloader() {
    }

    public InputStream downloadUrl(String url) throws Exception
    {
        if(logger.isDebugEnabled())
        logger.info("Start Call function downloadUrl(url)");

        InputStream inptStream=null;

        if(url!=null && !url.equals(""))
            url = url.trim();
        else
            throw new IOException(" *** Empty URL.");


        if (!MultiPartData.validateURL(url))
            throw new IOException(" *** Invalid URL.");

         if (!MultiPartData.UrlFound(url))
            throw new IOException(" *** Manifset Not Exist.");


        if(logger.isDebugEnabled())
        logger.debug("Download.downloadUrl:The URL Valid and Exist.");


        if(url.endsWith(MultiPartDownloader.MANIFEST_SUFFIX)) // is Manifest
        {
            if(logger.isDebugEnabled())
            logger.debug("Download.downloadUrl:The URL for Manifest.");

            Parser parsingManifest=new Parser();
            List<String> bockList = parsingManifest.readManifset(new URL(url));

            for (int i = 0; i < bockList.size(); i++)
            {
                String[] linkList = bockList.get(i).split(";");
                inptStream=manageDownload(inptStream, linkList);
            }
        }
        else
        {
            if(logger.isDebugEnabled())
            logger.debug("Download.downloadUrl:The URL for File.");

            inptStream=openUrlStreamAndMergeIt(inptStream,url);
        }

        if(logger.isDebugEnabled())
        logger.info("End Call function downloadUrl(url)");

        return inptStream;
    }

    // **************************************************** manageDownload Function **********
    public InputStream manageDownload(InputStream inptStream,String[] linkList) throws Exception// download one link of block
    {
        if(logger.isDebugEnabled())
        logger.info("Start Call function manageDownload(...)");

        InputStream inptStream2=null;
        boolean downloadSucccess=false;

        for(String urlString:linkList)
        {
            if(MultiPartData.validateURL(urlString)&&MultiPartData.UrlFound(urlString))
            {
                try
                {
                    if(urlString.endsWith(MultiPartDownloader.MANIFEST_SUFFIX))
                        inptStream2=downloadUrl(urlString);
                    else
                        inptStream2=new URL(urlString).openStream();

                    if(inptStream==null)
                        inptStream=inptStream2;
                    else
                        inptStream=Merge.mergeTwoInputStream(inptStream,inptStream2);

                    downloadSucccess=true;
                    break; // **** we need only one link form bock
                }
                catch (Exception e)
                {
                    /**
                      * Ignore Exception
                      * and
                      * try download form alternative url
                      **/
                }
            }
        }

        if(!downloadSucccess)
        throw  new Exception(" *** Fail Download Bolck");

        if(logger.isDebugEnabled())
         logger.info("End Call function manageDownload(...)");

        return inptStream;
    }

    //---------------------------------------

    private InputStream openUrlStreamAndMergeIt(InputStream inptStream,String urlString) throws  Exception
    {
        if(logger.isDebugEnabled())
            logger.info("Start Call function openUrlStreamAndMergeIt(...)");

          InputStream  inptStream2=new URL(urlString).openStream();

         if(inptStream==null)
            inptStream=inptStream2;
         else
            inptStream=Merge.mergeTwoInputStream(inptStream,inptStream2);

        if(logger.isDebugEnabled())
            logger.info("End Call function openUrlStreamAndMergeIt(...)");

        return inptStream;
