/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareconstruction;

import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;

/**
 *
 * @author sallyishak
 */

public class Merge
{
 

    public static InputStream mergeTwoInputStream(InputStream inputStream1, InputStream inputStream2) throws IOException
    {
        
        return   new  SequenceInputStream(inputStream1,inputStream2);
    }
}



