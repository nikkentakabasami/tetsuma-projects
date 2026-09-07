package ru.tet.syntax.datatypes.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import ru.tet.aux.DemoAuxDataSamples;
import ru.tet.aux.DemoAuxFunctions;
import ru.tet.aux.swing.DemoBase;

public class D_InputStream extends DemoBase {

	public void test1() throws Exception {
		/*
		
		ByteArrayInputStream
		 */
		
		byte[] bytes = DemoAuxDataSamples.sampleStringLyric.getBytes();
		InputStream is1 = new ByteArrayInputStream(bytes);

		byte[] buff = new byte[10];
		int length;
		while ((length = is1.read(buff)) != -1) {
			String s = new String(buff, 0, length, StandardCharsets.UTF_8);
			log2(s);
		}

		log2Splitter("read through channel");

		is1.reset();
		ReadableByteChannel channel1 = Channels.newChannel(is1);
		ByteBuffer buf = ByteBuffer.allocate(20);
		while (channel1.read(buf) != -1) {
			buf.flip();
			String s = new String(buf.array(), buf.position(), buf.remaining(), StandardCharsets.UTF_8);
			log2(s);
			buf.clear();
		}

		channel1.close();

	}

	public void test2() throws Exception {
		/*
		
		 */
		
		InputStream nullInputStream = InputStream.nullInputStream();
		
		Path p1 = DemoAuxFunctions.createTestFile1();

		
		try(
				FileInputStream is1 = new FileInputStream(p1.toFile());
				ByteArrayOutputStream os1 = new ByteArrayOutputStream();
				){
			is1.transferTo(os1);

			
			byte[] bytes = os1.toByteArray();
			log2(new String(bytes));
		}
		
		
//		InputStream newInputStream = Files.newInputStream(p1);

		
		
		
		

	}

	public void test3() throws Exception {
		/*
		
		 */
	}

	public void test4() throws Exception {
		/*
		
		 */
	}

	public static void main(String[] args) {
		DemoBase.run(D_InputStream.class);
	}

}
