/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareconstruction;

/**
 *
 * @author sallyishak
 */

import java.io.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;

public class FileSequenceReader {

	public static byte[] readOneFile(InputStream sequence)
		throws IOException, EOFException {
	
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        byte[] byteSizeFile = new byte[4];
         if((sequence.read(byteSizeFile)) != -1)
        {
            String s="";

            for(byte b:byteSizeFile)
            s+=unsignedToBytes(b);

            int size=Integer.parseInt(s, 2);


            byte[] data = new byte[size];
            int nRead = sequence.read(data, 0, data.length);
            buffer.write(data,1, nRead);
        }


        buffer.flush();


        return buffer.toByteArray();

	}

    public static String unsignedToBytes(byte b)
    {
     
        return Integer.toBinaryString(b & 0xFF);
    }

}
