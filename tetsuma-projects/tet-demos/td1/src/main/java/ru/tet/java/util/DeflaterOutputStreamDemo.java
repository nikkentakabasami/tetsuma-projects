package ru.tet.java.util;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HexFormat;
import java.util.zip.DeflaterOutputStream;

public class DeflaterOutputStreamDemo {

	
	static String STRING_TO_COMPRESS = "";
	static {
		for (int i = 0; i < 10; i++) {
			STRING_TO_COMPRESS += "What is the matrix" + i + "; ";
		}
		for (int i = 0; i < 7; i++) {
			STRING_TO_COMPRESS += "blahblah";
		}

	}
	
	
	public static void main(String[] args) throws Exception {
		demo1();
	}
	
	public static void demo1() throws Exception {
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
        DeflaterOutputStream dos = new DeflaterOutputStream(bos); 

		byte[] inputData = STRING_TO_COMPRESS.getBytes("UTF-8");
		System.out.format("input string: (%d bytes) \n", inputData.length);
		
		dos.write(inputData);
		
		dos.close();
		
		byte[] outputData = bos.toByteArray();

		HexFormat commaFormat = HexFormat.ofDelimiter(", ");
		String outputStr = commaFormat.formatHex(outputData, 0, outputData.length);
		System.out.format("compressed bytes: (%d bytes): %s\n", outputData.length, outputStr);
		
		
	}
	
	
}
