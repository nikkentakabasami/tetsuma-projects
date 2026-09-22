package ru.tet.syntax.datatypes.string;

import java.io.BufferedWriter;
import java.io.CharArrayWriter;
import java.io.FileWriter;
import java.io.FilterReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.CharBuffer;
import java.util.Arrays;

import ru.tet.aux.DemoAuxDataSamples;
import ru.tet.aux.swing.DemoBase;

public class D_Reader2 extends DemoBase {

	class UpperCaseFilter extends FilterReader {
		public UpperCaseFilter(Reader in) {
			super(in);
		}

		@Override
		public int read() throws IOException {
			int c = super.read();
			return (c == -1) ? c : Character.toUpperCase(c);
		}

		@Override
		public int read(char[] cbuf, int off, int len) throws IOException {
			int n = super.read(cbuf, off, len);
			for (int i = off; i < off + n; i++) {
				cbuf[i] = Character.toUpperCase(cbuf[i]);
			}
			return n;
		}
	}

	public void test1() throws Exception {
		/*
		 */

		try (Reader reader = new UpperCaseFilter(new StringReader(DemoAuxDataSamples.sampleString))) {
			CharBuffer cb1 = CharBuffer.allocate(200);
			reader.read(cb1);
			cb1.flip();
			log2(cb1.toString());
		}

	}

	public void test2() throws Exception {
		/*
		
		 */
		try (
				Writer w1 = new StringWriter();
				Reader r1 = new StringReader(DemoAuxDataSamples.sampleString);) {

			CharBuffer buf = CharBuffer.allocate(10);
			while (r1.read(buf) != -1) {
				buf.flip();
				w1.write(buf.array(), 0, buf.remaining());
			}
			log2(w1.toString());
		}

	}

	public void test3() throws Exception {
		/*
		
		 */

		try (CharArrayWriter w1 = new CharArrayWriter();) {
			w1.write(DemoAuxDataSamples.sampleStringJap);
			log2(Arrays.toString(w1.toCharArray()));
		}

	}

	public void test4() throws Exception {
		/*
		
		 */
		
		
		try (
				Writer sw1 = new StringWriter();
				PrintWriter pw1 = new PrintWriter(sw1);) {

			pw1.println("minareta machi");
			pw1.print(345);
			pw1.println();
			pw1.print(22.1134);
			pw1.println();
			pw1.format("I have %d apples", 5);

			log2(sw1.toString());
			
		}
		
		
	}

	public static void main(String[] args) {
		DemoBase.run(D_Reader2.class);
	}

}
