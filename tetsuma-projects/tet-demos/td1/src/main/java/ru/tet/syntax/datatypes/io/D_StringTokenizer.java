package ru.tet.syntax.datatypes.io;

import java.io.FileReader;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.StringTokenizer;

import ru.tet.aux.DemoAuxDataSamples;
import ru.tet.aux.swing.DemoBase;

public class D_StringTokenizer extends DemoBase {

	public void test1() throws Exception {
		/*
		java.util.StringTokenizer
		Выполняет разбиение строки на токены, используя заданные символы разделители
		Очень простое разбиение. Если нужно более сложное - использовать StreamTokenizer
		Считается устаревшим - вместо него рекомендуется использовать String.split(regex)
		
		StringTokenizer(String str, String delim, boolean returnDelims) 
		
		 */

		String initStr = "a=1;b=2;cd=333";
		StringTokenizer st2 = new StringTokenizer(initStr, ";");
		while (st2.hasMoreTokens()) {
			String s = st2.nextToken();
			int inx = s.indexOf('=');
			if (inx != -1) {
				log2(s.substring(0, inx) + "-" + s.substring(inx + 1, s.length()));
			}
		}

	}

	public void test2() throws Exception {
		/*
		java.io.StreamTokenizer
		Принимает входной поток, и разбивает его на токены, позволяя последовательно их считывать.
		Разбиение выполняется по пробелам (\s \n \t...), по комментам, по кавычкам а так же основываясь на типе токена
		 */

		log2(DemoAuxDataSamples.sampleStringTokenized);
		log2Splitter();

		StreamTokenizer tokenizer = new StreamTokenizer(new StringReader(DemoAuxDataSamples.sampleStringTokenized));

		//так комменты будут парсится как обычные символы
		tokenizer.ordinaryChar('/');

		while (tokenizer.nextToken() != StreamTokenizer.TT_EOF) {

			switch (tokenizer.ttype) {
			case StreamTokenizer.TT_WORD:
				log2("word:", tokenizer.sval);
				break;
			case StreamTokenizer.TT_NUMBER:
				log2("number:", tokenizer.nval);
				break;
			case '"':
			case '\'':
				log2("quoted word:", tokenizer.sval);
				break;
			case StreamTokenizer.TT_EOL:
				log2("end of line");
				break;
			default:
				log2("char:", (char) tokenizer.ttype);
				break;
			}

		}
	}

	public void test3() throws Exception {
		/*
		
		 */

		
		
		
	}

	public void test4() throws Exception {

	}

	public static void main(String[] args) {
		DemoBase.run(D_StringTokenizer.class);
	}

}
