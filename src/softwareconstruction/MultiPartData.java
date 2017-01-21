
package softwareconstruction;

/**
 *
 * @author sallyishak
 */

import java.net.URL;
import java.util.List;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;


public class MultiPartData
{
    
    public static InputStream openStream(String url) throws Exception
    {
       InputStream inputStream = new Downloader().downloadUrl(url);


        return inputStream;

    }

 static boolean validateURL(String url) throws MalformedURLException {
      URL u = null;
      u = new URL(url);
        
            
      
        try
        {
            u.toURI();
        }
        catch (URISyntaxException e)
        {
            System.err.println("Error in Validation the URL"+e);
            return false;
        }

        return true;
    }

    public static boolean UrlFound(String url)
    {
        try
        {
            HttpURLConnection.setFollowRedirects(false);
            HttpURLConnection con =  (HttpURLConnection) new URL(url).openConnection();
            con.setRequestMethod("HEAD");
            return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
        }
        catch (Exception e)
        {
            System.err.println("Error in locating the URL:"+e);
            return false;
        }
    }

   }

   

