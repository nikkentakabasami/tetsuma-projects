package ru.tet.java.io;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;

public class StreamTokenizerDemo {

	public static void main(String[] args) {
		demo1();
	}

	public static void demo1() {

		StreamTokenizer tokenizer = new StreamTokenizer(
				new StringReader("(;GM[1.4] //comment\n  один-четыре\t分け前 \n\"quo ted1\" \n 'quoted 2' FF[4] CA[UTF-8]AP[CGoban:3]KM[0.50]TM[3600]"));

		try {
			while (tokenizer.nextToken() != StreamTokenizer.TT_EOF) {

				if (tokenizer.ttype == StreamTokenizer.TT_WORD) {
					System.out.println("word:\t" + tokenizer.sval);

				} else if (tokenizer.ttype == StreamTokenizer.TT_NUMBER) {
					System.out.println("number:\t" + tokenizer.nval);

				} else if (tokenizer.ttype == '"' || tokenizer.ttype == '\'') {
					System.out.println("quoted word:\t" + tokenizer.sval);

				} else if (tokenizer.ttype == StreamTokenizer.TT_EOL) {
					System.out.println("end of line");
				} else
					System.out.println("char:\t" + (char) tokenizer.ttype);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
