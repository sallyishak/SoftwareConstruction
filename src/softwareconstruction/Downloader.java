package softwareconstruction;

import java.util.List;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 *
 * @author Zeina Doughlas
 */

// downloader Classs 

public class Downloader
{
  
    public Downloader() {
    }

    public InputStream downloadUrl(String url) throws Exception
    {
       

        InputStream inptStream=null;

       
        if(url.endsWith(MultiPartDownloader.MANIFEST_SUFFIX)) 
        {
            
            Parser parsingManifest=new Parser();
            List<String> list = parsingManifest.readManifset(new URL(url));

            for (int i = 0; i < list.size(); i++)
            {
                String[] linkList = list.get(i).split(";");
                inptStream=manageDownload(inptStream, linkList);
            }
        }
        else
        {
            

            inptStream=openUrlStreamAndMergeIt(inptStream,url);
        }


        return inptStream;
    }
// have to check the exception 
   
    public InputStream manageDownload(InputStream inputStream1,String[] linkList) throws Exception
    {
        

        InputStream inputStream2=null;
        boolean downloadSucccess=false;
        return null;

    }
// have to check the exception 
    private InputStream openUrlStreamAndMergeIt(InputStream inptStream,String urlString) throws  Exception
    {
        return null;
        
    }



}

private InputStream openUrlStreamAndMergeIt(InputStream inptStream,String urlString) throws  Exception
    {
   

          InputStream  inptStream2=new URL(urlString).openStream();

         if(inptStream==null)
            inptStream=inptStream2;
         else
            inptStream=Merge.mergeTwoInputStream(inptStream,inptStream2);

        return inptStream;
--------------------------------
public class Downloader
{

    public Downloader() {
    }

    public PicIn DownloaderUrl(String url) throws Exception
    {
        

        PicIn inputPic=null;

        if(url!=null && !url.equals(""))
            url = url.trim();
        else
            throw new IOException(" *** Empty.");
 if (!Multipart.validateURL(url))
            throw new IOException(" *** Invalid.");

         if (!Multipart.isUrlExist(url))
            throw new IOException(" ***Not Exist.");
            
            if(url.endsWith(Main.MANIFEST_SUFFIX)) 
        {

            Parsing parsingManifest=new Parsing();
            List<String> bockList = parsingManifest.readManifset(new URL(url));

            for (int i = 0; i < bockList.size(); i++)
            {
                String[] linkList = bockList.get(i).split(";");
                inputPic=manageDownloader(inputPic, linkList);
            }
