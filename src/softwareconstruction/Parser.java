/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareconstruction;

/**
 *
 * @author Remal Saad
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class Parser
{
    

	public Parser()
    {

	}



    public  List<String> readManifset(URL url) throws Exception
     {
        

         List<String> listOfParts=new ArrayList<String>();
         String str = "";
         String blockPart = "";
         BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

  try
    {
      String line = br.readLine();
      while (line != null)
       {
        line=line.replaceAll("\\s+","");// remove space

        if(line.equals("**"))
        {
        listOfParts.add(blockPart);
        blockPart = "" ;
        }
        else
        {
       
    }
    catch (Exception e)
    {
       

        throw e;

    }
    finally
    {
        br.close();
    }

      

		
	
		return listOfParts ;
	}

    private boolean validateURL(String url)
    {
       URL u = null;
        try
        {
            u = new URL(url);
        } catch (MalformedURLException e)
        {
            System.err.println("Error in multipart.Parsing.validateURL:"+e);
            return false;
        }
        try
        {
            u.toURI();
        }
        catch (URISyntaxException e)
        {
            System.err.println("Error in multipart.Parsing.validateURL"+e);
            return false;
        }

  

        return true;
    }

}
