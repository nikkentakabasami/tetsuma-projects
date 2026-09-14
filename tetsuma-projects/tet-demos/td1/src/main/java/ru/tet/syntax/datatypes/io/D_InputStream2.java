package ru.tet.syntax.datatypes.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PushbackInputStream;
import java.io.SequenceInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import ru.tet.aux.swing.DemoBase;

public class D_InputStream2 extends DemoBase {

	class UpperCaseInputStream extends FilterInputStream {
		public UpperCaseInputStream(InputStream in) {
			super(in);
		}

		@Override
		public int read() throws IOException {
			int c = super.read();
			return (c == -1) ? c : Character.toUpperCase((char) c);
		}

		@Override
		public int read(byte[] b, int off, int len) throws IOException {
			int result = super.read(b, off, len);
			for (int i = off; i < off + result; i++) {
				b[i] = (byte) Character.toUpperCase((char) b[i]);
			}
			return result;
		}
	}

	public void test1() throws Exception {
		/*
		 */

		String inputString = "Hello, World!";
		InputStream upperCaseInputStream = new UpperCaseInputStream(new ByteArrayInputStream(inputString.getBytes()));

		String s = new String(upperCaseInputStream.readAllBytes());
		log2(s);

	}

	public void test2() throws Exception {
		/*
		
		 */
		ByteArrayOutputStream bos1 = new ByteArrayOutputStream(400);

		try (
				PrintStream ps1 = new PrintStream(bos1, false, StandardCharsets.UTF_8);) {
			ps1.print("Hello World!");
			ps1.println("Welcome to Java!");

			ps1.printf("Name: %s Age: %d \n", "Tom", 34);
			ps1.println(777_888);
			ps1.println(true);
			ps1.print("---");
		}

		String r = new String(bos1.toByteArray(), StandardCharsets.UTF_8);

		log2(r);

	}

	public void test3() throws Exception {
		/*
		SequenceInputStream
		Позволяет объединять несколько потоков в один
		 */

		try (
				FileInputStream fis1 = new FileInputStream(Path.of("pom.xml").toFile());
				FileInputStream fis2 = new FileInputStream(Path.of("../pom.xml").toFile());
				SequenceInputStream sequenceStream = new SequenceInputStream(fis1, fis2);) {
			sequenceStream.transferTo(System.out);
			sequenceStream.close();

		}

	}

	public void test4() throws Exception {
		/*
		
		 */
		String data = "x = 8+5/6;  //My comment\ny =55;";
		byte[] bytes = data.getBytes(); //символ будет занимать 1 байт

		try (ByteArrayInputStream bis1 = new ByteArrayInputStream(bytes);
				PushbackInputStream pushbackStream = new PushbackInputStream(bis1, 2)) {

			//считываем побайтово
			int i;
			while ((i = pushbackStream.read()) != -1) {
				char c = (char) i;

				//If we see a slash, look ahead to check if it's a comment
				if (c == '/') {
					int nextByte = pushbackStream.read();
					if (nextByte == -1) {
						break;
					}

					if (nextByte == '/') {
						log2Inline("[Found Comment!] -> ");
						int skip;
						while ((skip = pushbackStream.read()) != -1 && skip != '\n') {
							log2Inline((char) skip);
						}
						log2NL();
						continue;
					} else {
						pushbackStream.unread(nextByte);
					}

				}
				log2Inline(c);
			}
		}

	}

	public static void main(String[] args) {
		DemoBase.run(D_InputStream2.class);
	}

}
