package ru.tet.java.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.HexFormat;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class DeflaterDemo {

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
//		demo1();
		demo2();
//		demo4();
	}

	public static void demo1() throws Exception {

		byte[] input = STRING_TO_COMPRESS.getBytes("UTF-8");

		System.out.format("input string: (%d bytes) \n", input.length);

		Deflater deflater = new Deflater(Deflater.BEST_COMPRESSION, true);
		deflater.setInput(input);
		deflater.finish();

		byte[] output = new byte[100];
		int compressedDataLength = deflater.deflate(output);

		deflater.end();

		HexFormat commaFormat = HexFormat.ofDelimiter(", ");
		String outputStr = commaFormat.formatHex(output, 0, compressedDataLength);
		System.out.format("compressed bytes: (%d bytes): %s\n", compressedDataLength, outputStr);

//		Inflater decompresser = new Inflater();
		Inflater decompresser = new Inflater(true);
		decompresser.setInput(output, 0, compressedDataLength);
		byte[] result = new byte[100];
		int resultLength = decompresser.inflate(result);
		decompresser.end();

		String outputString = new String(result, 0, resultLength, "UTF-8");
		System.out.format("outputString: %s\n", outputString);

	}

	public static void demo2() throws Exception {

		byte[] inputData = STRING_TO_COMPRESS.getBytes("UTF-8");
		System.out.format("input string: (%d bytes): %s\n", inputData.length, STRING_TO_COMPRESS);

		byte[] compressedData = compress(inputData);

		HexFormat commaFormat = HexFormat.ofDelimiter(", ");
		String compressedStr = commaFormat.formatHex(compressedData, 0, compressedData.length);
		System.out.format("compressed bytes: (%d bytes): %s\n", compressedData.length, compressedStr);

		byte[] decompressedData = decompress(compressedData);

		String outputString = new String(decompressedData, "UTF-8");
		System.out.format("outputString: %s\n", outputString);

	}

	public static byte[] compress(byte[] data) throws Exception {
		Deflater deflater = new Deflater(Deflater.BEST_COMPRESSION, true);
		deflater.setInput(data);
		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		outputStream.close();
		deflater.end();

		return outputStream.toByteArray();

	}

	public static byte[] decompress(byte[] data) throws Exception {
		Inflater inflater = new Inflater(true);
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!inflater.finished()) {
			int count = inflater.inflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		outputStream.close();
		inflater.end();

		return outputStream.toByteArray();
	}

	/*
	 * public static void demo4() throws Exception {
	 * 
	 * System.out.format("\ninput string: (%d bytes): %s\n",
	 * STRING_TO_COMPRESS.length(), STRING_TO_COMPRESS);
	 * 
	 * InputStream inputStream = new
	 * ByteArrayInputStream(STRING_TO_COMPRESS.getBytes("UTF-8"));
	 * ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	 * ReadableByteChannel inputChannel = Channels.newChannel(inputStream);
	 * WritableByteChannel outputChannel = Channels.newChannel(outputStream);
	 * 
	 * ByteBuffer inputByteBuffer = ByteBuffer.allocate(16); ByteBuffer
	 * outputByteBuffer = ByteBuffer.allocate(16);
	 * 
	 * Deflater deflater = new Deflater(Deflater.BEST_COMPRESSION, true); while
	 * (!deflater.finished()) {
	 * 
	 * if (deflater.needsInput() && inputChannel.isOpen()) {
	 * inputByteBuffer.clear(); int readen = inputChannel.read(inputByteBuffer); if
	 * (readen > 0) { inputByteBuffer.flip(); deflater.setInput(inputByteBuffer); }
	 * else { inputChannel.close(); deflater.finish(); }
	 * 
	 * } outputByteBuffer.clear(); int compressedDataLength =
	 * deflater.deflate(outputByteBuffer);
	 * 
	 * if (compressedDataLength == 0) { // deflater.end(); } else {
	 * outputByteBuffer.flip(); outputChannel.write(outputByteBuffer); }
	 * 
	 * } deflater.end();
	 * 
	 * inputChannel.close(); outputChannel.close();
	 * 
	 * byte[] compressedBytes = outputStream.toByteArray();
	 * 
	 * HexFormat commaFormat = HexFormat.ofDelimiter(", "); String outputStr =
	 * commaFormat.formatHex(compressedBytes, 0, compressedBytes.length);
	 * System.out.format("compressed bytes: (%d bytes): %s\n",
	 * compressedBytes.length, outputStr);
	 * 
	 * 
	 * }
	 * 
	 * public static void demo3() throws Exception {
	 * 
	 * String message = "Welcome to TutorialsPoint.com;" +
	 * "Welcome to TutorialsPoint.com;" + "Welcome to TutorialsPoint.com;" +
	 * "Welcome to TutorialsPoint.com;" + "Welcome to TutorialsPoint.com;" +
	 * "Welcome to TutorialsPoint.com;" + "Welcome to TutorialsPoint.com;" +
	 * "Welcome to TutorialsPoint.com;" + "Welcome to TutorialsPoint.com;" +
	 * "Welcome to TutorialsPoint.com;";
	 * System.out.println("Original Message length : " + message.length()); byte[]
	 * input = message.getBytes("UTF-8");
	 * 
	 * byte[] output = new byte[1024];
	 * 
	 * Deflater deflater = new Deflater(); int inCount = input.length; int
	 * inPosition = 0; int outPosition = 0;
	 * 
	 * int MAX_BUFFER_SIZE = 10; while (!deflater.finished()) {
	 * 
	 * int want = -1; if (deflater.needsInput() && inCount != 0) { want = (inCount <
	 * MAX_BUFFER_SIZE) ? inCount : MAX_BUFFER_SIZE;
	 * 
	 * deflater.setInput(input, inPosition, want);
	 * 
	 * inCount -= want; inPosition += want; if (inCount == 0) { deflater.finish(); }
	 * }
	 * 
	 * // deflate to current position in output buffer int compCount;
	 * 
	 * compCount = deflater.deflate(output, outPosition, MAX_BUFFER_SIZE);
	 * outPosition += compCount;
	 * 
	 * } // while
	 * 
	 * System.out.println("Total uncompressed bytes output :" +
	 * deflater.getTotalOut()); System.out.println("Compressed Message Checksum :" +
	 * deflater.getAdler()); deflater.finished();
	 * 
	 * System.out.println("Compressed Message length : " + outPosition);
	 * 
	 * // Decompress the bytes Inflater inflater = new Inflater();
	 * inflater.setInput(output, 0, outPosition); byte[] result = new byte[1024];
	 * int resultLength = inflater.inflate(result); inflater.finished();
	 * 
	 * // Decode the bytes into a String message = new String(result, 0,
	 * resultLength, "UTF-8"); System.out.println("UnCompressed Message Checksum :"
	 * + inflater.getAdler()); System.out.println("UnCompressed Message length : " +
	 * message.length());
	 * 
	 * }
	 */

}
