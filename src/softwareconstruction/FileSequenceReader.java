/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multipartdownloader;

/**
 *
 * @author sallyishak
 */


import org.apache.log4j.Logger;

import java.io.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;

/**
 * Static class for reading from a file-sequence stream.

 */
public class FileSequenceReader {

    final static Logger logger = Logger.getLogger(FileSequenceReader.class);

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

        if(logger.isDebugEnabled())
            logger.info("Start Call function readOneFile(...)");


        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        byte[] byteSizeFile = new byte[4];
         if((sequence.read(byteSizeFile)) != -1)
        {
            String s="";

            for(byte b:byteSizeFile)
            s+=unsignedToBytes(b);

            if(logger.isDebugEnabled())
            logger.debug("size file in byte:"+byteSizeFile);

            int size=Integer.parseInt(s, 2);

            if(logger.isDebugEnabled())
                logger.debug("size file in Integer:"+size);

            byte[] data = new byte[size];
            int nRead = sequence.read(data, 0, data.length);
            buffer.write(data, 0, nRead);
        }


        buffer.flush();

        if(logger.isDebugEnabled())
            logger.info("End Call function readOneFile(...)");

        return buffer.toByteArray();

	}

    public static String unsignedToBytes(byte b)
    {
        if(logger.isDebugEnabled())
            logger.info("Call function unsignedToBytes(...)");

        return Integer.toBinaryString(b & 0xFF);
    }

}
