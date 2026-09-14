package ru.tet.syntax.datatypes.io;

import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import ru.tet.aux.swing.DemoBase;

/**
 * Scanner - токены
 */

public class D_Scanner1 extends DemoBase {

	@Override
	public String toStr(Object o) {
		if (o instanceof Scanner) {
			return null;
		}
		return super.toStr(o);
	}

	static String testString = "1 fish 2 fish 12 red fish blue fish";
	Pattern p;
	Scanner scanner;

	public void test1() throws Exception {
		/*
		String	next()
		Считывание следующего токена
		
		String	next(Pattern pattern)
		Считывание следующего токена, если он соответствует заданному паттерну.
		Иначе - кидает ошибку.
		
		int	nextInt()
		double	nextDouble()
		...
		Считывание следующего токена-числа.
		 */
		log2Splitter("все токены через запятую");

		logEval1(
				scanner = new Scanner(testString),
				scanner.tokens().collect(Collectors.joining(",")));
		scanner.close();

		log2Splitter();

		logEval2(
				testString,
				scanner = new Scanner(testString),
				scanner.hasNextInt(),
				scanner.nextInt(),
				scanner.next(),
				scanner.nextInt(),
				scanner.next(),

				p = Pattern.compile("\\d+"),
				scanner.hasNext(p),
				scanner.next(p),

				scanner.next());
		scanner.close();

		log2Splitter();

		logEval3(
				testString,
				scanner = new Scanner(testString).useDelimiter("\\s*fish\\s*"),
				scanner.nextInt(),
				scanner.nextInt(),
				scanner.next(),
				scanner.next());

		scanner.close();

	}

	public void test2() throws Exception {

		//вывести только числовые токены
		Scanner scanner = new Scanner(testString);

		while (scanner.hasNext()) {

			if (scanner.hasNextInt()) {
				log2(scanner.nextInt());
			} else {
				scanner.next();
			}

		}
		scanner.close();

	}

	public void test3() throws Exception {

	}

	public void test4() throws Exception {
		/*
		
		 */

		logEval1(
				testString,
				scanner = new Scanner(testString),
				scanner.hasNext("\\d+"),
				scanner.skip("\\d+"),
				//				scanner.next(),
				scanner.hasNext("fish"),
				scanner.skip(" *fish *"),
				//				scanner.hasNext("\\w+"),
				scanner.next(),
				scanner.next()

		);
		scanner.close();

		/*
						scanner.skip("(fish)?"),
						scanner.next(),
						scanner.skip(".*fish.*"),
						scanner.next(),
						scanner.skip("(fish)?"),
						scanner.next()
		 * 
		 */

	}

	@Override
	public void test5() throws Exception {

	}

	public static void main(String[] args) {
		DemoBase.run(D_Scanner1.class);
	}

}
