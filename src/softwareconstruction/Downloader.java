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
         if(url!=null && !url.equals(""))
            url = url.trim();
        else
            throw new IOException("Empty URL.");


        if (!MultiPartData.validateURL(url))
            throw new IOException("Invalid URL.");

         if (!MultiPartData.UrlFound(url))
            throw new IOException("Manifset Not Exist.");

       
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


 public InputStream manageDownload(InputStream inptStream,String[] linkList) throws Exception
       

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
                    break; 
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

        return inptStream;
    }

   
