// from the DR // 

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;



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




//-------------------------------------------------------------------We have to change this class --------//
/**
 * Static class for reading from a file-sequence stream.

 */
public class FileSequenceReader {
	/**
	 * Returns the data from the next sub-file in the given file sequence stream.
	 * <p>
	 * If no sub-files remain, returns null. If the stream ends prematurely,
	 * throws an EOFException.
	 */
	public static byte[] readOneFile(InputStream sequence)
		throws IOException, EOFException {
		// sequence files consist of a (4-byte) int giving the size of the sub-file,
		// followed by the sub-file, followed by another size, followed by the sub-file,
		// and so on until EOF
		
	}
}
