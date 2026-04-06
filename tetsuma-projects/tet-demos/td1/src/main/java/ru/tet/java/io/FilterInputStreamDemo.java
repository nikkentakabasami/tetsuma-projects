package ru.tet.java.io;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FilterInputStreamDemo {

	static class UpperCaseInputStream extends FilterInputStream {
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

	public static void main(String[] args) throws IOException {
		demo1();
	}

	public static void demo1() throws IOException {

		String inputString = "Hello, World!";
		InputStream inputStream = new ByteArrayInputStream(inputString.getBytes());
		InputStream upperCaseInputStream = new UpperCaseInputStream(inputStream);

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(upperCaseInputStream))) {
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		}

	}

}
