package ru.tet.syntax.datatypes.string;

import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import ru.tet.aux.DemoAuxDataSamples;
import ru.tet.aux.swing.DemoBase;

/**
 * Scanner - основные операции и приёмы.
 */
public class D_Scanner3 extends DemoBase {

	@Override
	public String toStr(Object o) {
		if (o instanceof Scanner) {
			return null;
		}
		return super.toStr(o);
	}

	Pattern p;
	Scanner scanner;

	public void test1() throws Exception {

		/*
		
		String	nextLine()
		Считывает ввод до конца строки.
			 */

		//Считывание построчно
		try (
				Scanner scanner = new Scanner(DemoAuxDataSamples.multilineTestString)) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				log2(line);
			}

		}

		log2Splitter();

		//Считывание файла построчно
		try (
				FileReader fr1 = new FileReader(Path.of("pom.xml").toFile(), StandardCharsets.UTF_8);
				Scanner scanner = new Scanner(fr1)) {

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				log2(line);
			}

		}

	}

	public void test2() throws Exception {

		/*
		Stream<String> tokens()
		Получение всех токенов в виде потока
		
		 */
		logEval1(
				D_Scanner1.testString,
				scanner = new Scanner(D_Scanner1.testString),
				scanner.tokens().collect(Collectors.joining(",")));
		scanner.close();

	}

	public void test3() throws Exception {

	}

	public void test4() throws Exception {

	}

	@Override
	public void test5() throws Exception {

	}

	public static void main(String[] args) {
		DemoBase.run(D_Scanner3.class);
	}

}
