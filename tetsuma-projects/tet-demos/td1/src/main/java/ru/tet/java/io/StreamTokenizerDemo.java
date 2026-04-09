package ru.tet.java.io;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;

import ru.tet.aux.AuxTest;
import ru.tet.aux.swing.DemoBase;
import ru.tet.data.StringSamples;

public class StreamTokenizerDemo extends DemoBase {

	//java.io.StreamTokenizer
	//  Принимает входной поток, и разбивает его на токены, позволяя последовательно их считывать.
	//  Разбиение выполняется по пробелам (\s \n \t...), по комментам, по кавычкам а так же основываясь на типе токена
	@Override
	public void test1() throws Exception {
		parseString(StringSamples.tokenizedString);
	}

	@AuxTest
	void parseString(String s) throws Exception {

		log2Splitter("parsing string:");
		log2(s);
		log2Splitter();

		StreamTokenizer tokenizer = new StreamTokenizer(new StringReader(s));

		//TT_EOF - Конец потока.
		while (tokenizer.nextToken() != StreamTokenizer.TT_EOF) {

			//TT_WORD -  токен - слово. 
			//Может включать в себя буквы разных языков, иероглифы, тире.
			//Например "один-четыре", "分け前", "UTF-8"
			if (tokenizer.ttype == StreamTokenizer.TT_WORD) {
				log2("word:\t" + tokenizer.sval);

			//TT_NUMBER - токен - число
			} else if (tokenizer.ttype == StreamTokenizer.TT_NUMBER) {
				log2("number:\t" + tokenizer.nval);

			//фраза в кавычках
			} else if (tokenizer.ttype == '"' || tokenizer.ttype == '\'') {
				log2("quoted string:\t" + tokenizer.sval);

				//TT_EOL - end of line
			} else if (tokenizer.ttype == StreamTokenizer.TT_EOL) {
				log2("end of line");
			} else
				log2("char:\t" + (char) tokenizer.ttype);

		}

	}

	@Override
	protected void doInitControlPanel() throws Exception {
		addTest1Button(null);
	}
	
	@Override
	protected void doInit() throws Exception {
		//запускаем
		test1();
	}

	public static void main(String[] args) {
		DemoBase.run(StreamTokenizerDemo.class,1);
	}

}
