package ru.tet.syntax.datatypes.io;

import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PushbackReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import ru.tet.aux.DemoAuxDataSamples;
import ru.tet.aux.swing.DemoBase;

public class D_Reader extends DemoBase {


	public void test1() throws Exception {
		/*
		java.io.Reader
		Абстрактный класс для считывания потока символов.
		StringReader
		Простейшая реализация - считывание из строки.
		 */

		try (Reader r1 = new StringReader(DemoAuxDataSamples.multilineTestString);) {
			//Считываем посимвольно
			//Символы вне BMP кодируются двумя char-ми (суррогатными парами).
			int c1;
			while ((c1 = r1.read()) != -1) {
				log2(c1, "-", (char) c1);
			}

			log2Splitter("read via CharBuffer");
			r1.reset();

			//Считываем через CharBuffer
			CharBuffer buf = CharBuffer.allocate(4);
			buf.clear();
			while ((r1.read(buf)) != -1) {
				buf.flip();
				log2Inline(buf.toString());
			}
		}

	}

	public void test2() throws Exception {
		/*
		CharArrayReader
		поток для считывания данных из символьного массива
		 */
		char[] charArray = DemoAuxDataSamples.multilineTestString.toCharArray();
		CharArrayReader r1 = new CharArrayReader(charArray);

		CharBuffer cb1 = CharBuffer.allocate(40);
		r1.read(cb1);
		cb1.flip();
		log2(cb1.toString());

		log2Splitter();
		cb1 = CharBuffer.wrap(charArray);
		log2(cb1.toString());

	}

	public void test3() throws Exception {
		/*
		
		 */

		BufferedReader bufReader = new BufferedReader(new StringReader(DemoAuxDataSamples.multilineTestString));
		String line;
		while ((line = bufReader.readLine()) != null) {
			log2(line);
		}
		bufReader.close();

		log2Splitter("InputStreamReader");
		try (
				InputStreamReader sr1 =
						new InputStreamReader(new FileInputStream(Path.of("pom.xml").toFile()), StandardCharsets.UTF_8);) {
			CharBuffer cb1 = CharBuffer.allocate(40);
			while ((sr1.read(cb1)) != -1) {
				cb1.flip();
				log2(cb1.toString());
			}
		}

		log2Splitter("FileReader");
		try (
				FileReader fr1 = new FileReader(Path.of("pom.xml").toFile(), StandardCharsets.UTF_8);) {
			CharBuffer cb1 = CharBuffer.allocate(40);
			while ((fr1.read(cb1)) != -1) {
				cb1.flip();
				log2(cb1.toString());
			}
		}

	}

	public void test4() throws Exception {
		/*
		
		 */

		LineNumberReader r1 = new LineNumberReader(new StringReader(DemoAuxDataSamples.multilineTestString));
		r1.setLineNumber(80);
		String line;
		while ((line = r1.readLine()) != null) {
			log2(r1.getLineNumber() + ")" + line);
		}
		r1.close();

	}

	@Override
	public void test5() throws Exception {
		/*
		PushbackReader
		FilterReader, который добавляет возможность unread считанные данные.
		Может использоваться в парсерах.
		
		 */
		try (Reader r1 = new StringReader(DemoAuxDataSamples.multilineTestString);
				PushbackReader br1 = new PushbackReader(r1, 6)) {
			int c1;
			while ((c1 = br1.read()) != -1) {

				if (c1 == '/') {

					int c2 = br1.read();
					if (c2 == -1) {
						break;
					}
					if (c2 == '/') {
						log2Inline("[Found Comment!] -> ");
						int skip;
						while ((skip = br1.read()) != -1 && skip != '\n') {
							log2Inline((char) skip);
						}
						log2NL();

					} else {
						br1.unread(c2);
					}
				}
				log2Inline((char) c1);
			}
		}
	}

	public static void main(String[] args) {
		DemoBase.run(D_Reader.class);
	}

}
