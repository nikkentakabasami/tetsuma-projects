package ru.tet.syntax.datatypes.string;

import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import ru.tet.aux.swing.DemoBase;

public class D_Scanner2 extends DemoBase {

	@Override
	public String toStr(Object o) {
		if (o instanceof Scanner) {
			return null;
		}
		return super.toStr(o);
	}

	String testString = "1 fish 2 fish 12 red fish blue fish";
	Pattern p;
	Scanner scanner;
	
	
	public void test1() throws Exception {
		/*
		 */

		/*
		String findInLine(String pattern)
		String findInLine(Pattern pattern)
		Поиск в текущей строке (до перехода на новую строку)
		Возвращает null, если ничего не найдено.
		 */
		log2Splitter();

		logEval1(
				testString,
				p = Pattern.compile("\\d+"),
				scanner = new Scanner(testString),
				scanner.next(),
				scanner.findInLine(p),
				scanner.findInLine(p),
				scanner.findInLine(p)

		);
		scanner.close();

		log2Splitter();

		//Получение всех токенов разом через группы
		

		logEval2(
				testString,
				scanner = new Scanner(testString),
				scanner.findInLine("(\\d+) fish (\\d+) fish (\\d+) (\\w+) fish (\\w+)"),
				expr(() -> {
					MatchResult result = scanner.match();
					for (int i = 1; i <= result.groupCount(); i++) {
						log2(result.group(i));
					}
					return null;
				})
		);
		
		scanner.close();


	}

	public void test2() throws Exception {
		/*
		String findInLine(Pattern pattern)
		
		 */


		//ищем первый открывающий таг в каждой строке 
		Pattern p = Pattern.compile("<[^!/?]+?>");
		
		try (
				FileReader fr1 = new FileReader(Path.of("pom.xml").toFile(), StandardCharsets.UTF_8);
				Scanner scanner = new Scanner(fr1)) {
			
		  while (scanner.hasNextLine()) {
		  	String firstTag = scanner.findInLine(p);
		  	if (firstTag!=null) {
			    log2(firstTag);
		  	}
		    scanner.nextLine();
		  }
		}
		
		//ищем все открывающие теги 
		log2Splitter();
		
		try (
				FileReader fr1 = new FileReader(Path.of("pom.xml").toFile(), StandardCharsets.UTF_8);
				Scanner scanner = new Scanner(fr1)) {
			
			scanner.findAll(p).map(MatchResult::group).forEach(this::log2);
			
		}
		
		
		log2Splitter();
		
		//ищем имена открывающих тегов
		p = Pattern.compile("<(\\w+).*?>");
		try (
				FileReader fr1 = new FileReader(Path.of("pom.xml").toFile(), StandardCharsets.UTF_8);
				Scanner scanner = new Scanner(fr1)) {
			
			scanner.findAll(p).map(r->r.group(1)).forEach(this::log2);
			
		}

	}

	@Override
	public void test3() throws Exception {
		
		
		
	}
	
	public void test4() throws Exception {
		/*
		String	findWithinHorizon(String pattern, int horizon)
		Поиск по регулярному выражению по всему тексту в заданных пределах horizon
		
		horizon — задаёт максимальную длину (в символах), на которую сканер будет искать совпадение.
		
		
		 */

		scanner = new Scanner(D_Scanner1.testString);
		logEval1(
				testString,
				scanner = new Scanner(D_Scanner1.testString),
				scanner.findWithinHorizon("\\d+", 13),
				scanner.findWithinHorizon("\\d+", 13),
				scanner.match().start(),
				scanner.match().end(),
				scanner.findWithinHorizon("\\d+", 13)

		);

	}

	public void test5() throws Exception {
		/*
		String	findWithinHorizon(String pattern, int horizon)
		
		Поиск в файле.
		 */

		Pattern p = Pattern.compile("<.+?>");

		try (
				FileReader fr1 = new FileReader(Path.of("pom.xml").toFile(), StandardCharsets.UTF_8);
				Scanner scanner = new Scanner(fr1)) {

			int horizon = 500;

			logEval1(
					scanner.findWithinHorizon(p, horizon),
					scanner.findWithinHorizon(p, horizon),
					scanner.findWithinHorizon(p, horizon),
					scanner.findWithinHorizon(p, horizon));

			String s;
			do {
				s = scanner.findWithinHorizon(p, horizon);
				if (s != null) {
					log2(s);
				} else {
					scanner.nextLine();
				}

			} while (scanner.hasNextLine());
		}

	}

	public static void main(String[] args) {
		DemoBase.run(D_Scanner2.class);
	}

}
