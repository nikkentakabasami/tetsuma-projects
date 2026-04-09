package ru.tet.java.io;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import ru.tet.TemplateDemo;
import ru.tet.aux.AuxTest;
import ru.tet.aux.swing.DemoBase;




public class FilterInputStreamDemo extends DemoBase {

	//FilterInputStream, переводящий строки в верхний регистр
	@AuxTest
	public static class UpperCaseInputStream extends FilterInputStream {
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
	
	//FilterInputStream - основа для адаптерных InputStream-ов.
	//Наследники могут преобразовывать данные по необходимости
	@Override
	public void test1() throws Exception {
		
		String inputString = "Hello, World!";
		InputStream inputStream = new ByteArrayInputStream(inputString.getBytes());
		InputStream upperCaseInputStream = new UpperCaseInputStream(inputStream);

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(upperCaseInputStream))) {
			String line;
			while ((line = reader.readLine()) != null) {
				log2(line);
			}
		}
		
	}

	@Override
	protected void doInitControlPanel() throws Exception {
		addTest1Button(null);
	}

	public static void main(String[] args) {
		DemoBase.run(FilterInputStreamDemo.class);
	}

}



