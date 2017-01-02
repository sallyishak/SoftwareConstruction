package softwareconstruction;

import java.util.List;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


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

   
    public InputStream manageDownload(InputStream inputStream1,String[] linkList) throws Exception
    {
        

        InputStream inputStream2=null;
        boolean downloadSucccess=false;
        return null;

    }

    private InputStream openUrlStreamAndMergeIt(InputStream inptStream,String urlString) throws  Exception
    {
        return null;
        
    }



}
