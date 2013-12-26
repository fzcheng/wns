/**
 * 
 */
package admins;

import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * @author 侯浩军
 * 
 * 2:59:10 PM
 */
public class FileServer {

	public static void sd() {
		try {
			// Encode a String into bytes
			String inputString = "abc";
			byte[] input = inputString.getBytes("UTF-8");

			// Compress the bytes
			byte[] output = new byte[100];
			Deflater compresser = new Deflater();
			compresser.setInput(input);
			compresser.finish();
			int compressedDataLength = compresser.deflate(output);

			System.out.println(compressedDataLength);
			
			// Decompress the bytes
			Inflater decompresser = new Inflater();
			decompresser.setInput(output, 0, compressedDataLength);
			byte[] result = new byte[100];
			int resultLength = decompresser.inflate(result);
			System.out.println(resultLength);
			decompresser.end();
			// Decode the bytes into a String
			String outputString = new String(result, 0, resultLength, "UTF-8");
			System.out.println(outputString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		sd();
	}
}
