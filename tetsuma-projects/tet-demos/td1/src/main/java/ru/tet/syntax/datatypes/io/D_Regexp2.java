package ru.tet.syntax.datatypes.io;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ru.tet.aux.swing.DemoBase;

/**
 * Pattern, Matcher
 * Основы.
 * 
 */
public class D_Regexp2 extends DemoBase {

	String regex;

	void execRegex(String regex) throws Exception {
		execRegex(D_RegexpBase.testText, regex);
	}

	//Универсальная функция для поиска по РВ, включая группы
	void execRegex(String input, String regex) throws Exception {

		Pattern pattern = Pattern.compile(regex, 0);
		Matcher matcher = pattern.matcher(input);

		log2Green(regex);

		while (matcher.find()) {
			String r = String.format("'%s' (%d-%d)", matcher.group(), matcher.start(), matcher.end());
			if (matcher.groupCount() > 0) {
				String groups =
						IntStream.rangeClosed(1, matcher.groupCount())
								.boxed()
								.map(i -> "(" + matcher.group(i) + ")")
								.collect(Collectors.joining(","));
				r += " groups: " + groups;
			}

			log2(r);
		}

		log2Splitter();

	}

	@Override
	protected void doInit() throws Exception {
		options().logSources = false;
		options().hlComments = false;
	}

	@Override
	public void beforeTest(int testNo) throws Exception {
		super.beforeTest(testNo);
		log1(D_RegexpBase.testText);
	}

	public void test1() throws Exception {
		/*
		boolean	Pattern.matches(String regex, CharSequence input)
		Быстрая проверка строки на соответствие выражению
		
		String	Pattern.quote(String s)
		Создаёт regex, соответствующий заданной строке, окружая её \Q и \E
		
		String regex = Pattern.quote("t[],t.");	//\Qt[],t.\E
		 * 
		 */

		//		Pattern pattern = Pattern.compile(" \\d+ ", 0);

		logEval1(
				regex = Pattern.quote("t[],t."),
				Pattern.matches(" \\d+ ", " 456 "),
				Pattern.matches("\\d+", "SD456 "),
				Pattern.matches(".*7$", "АБС-X-57"),
				"АБС-X-57".matches(".*7$")

		);
		execRegex("mat[],t.sp", regex);

	}

	public void test2() throws Exception {
		/*
		Matcher
		Методы без состояния
		
		boolean	matches()
		возвращает true, если весь текст соответствует шаблону.
		
		boolean	lookingAt()
		возвращает true, если любая часть текста соответствует шаблону.
		
		String	replaceAll(String replacement)
		String	replaceAll(Function f)
		Замена всех вхождений
		 */

		Pattern pattern = Pattern.compile("\\w+");
		Matcher matcher = pattern.matcher("abc and cdf!");
		logEval1(
				matcher.matches(),
				matcher.lookingAt(),

				matcher.replaceAll("@"),
				matcher.replaceAll(mr -> {
					return mr.group().toUpperCase();
				}),

				Pattern.compile("Java(?i:script) is bold").matcher("JavaScript is bold").matches()

		);

		//		Pattern p = Pattern.compile("\\s+");
		//		Matcher m = p.matcher("Удаляем      \t\t лишние пробелы.   ");
		//		System.out.println(m.replaceAll(" "));

	}

	public void test3() throws Exception {
		/*
		Matcher	appendReplacement(StringBuffer sb, String replacement)
		считывает текст с input-а, начиная с append position и до конца найденного выражения, делает в нём замену на replacement, 
		записывает результат в sb
		replacement может содержать выражение ${no} - для замены по группе
		Используется для поиска и замены выражений.
		
		StringBuffer	appendTail(StringBuffer sb)
		записывает в sb оставшийся текст		
		 */
		
		Pattern p = Pattern.compile("(cat)");
		Matcher m = p.matcher("one cat, two cats, or three cats on a fence");
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
		  m.appendReplacement(sb, "big $1erpillar");
		}
		m.appendTail(sb);
		log2(sb);
		
	}

	public void test4() throws Exception {
		/*
		
		 */
		//поиск с группами
		execRegex("(Лю)до(вик)");

		//квантификация группы
		execRegex("(тр[ау]м-?)+");

		//экранирование
		execRegex("\\Q[some.\\E");

	}

	public static void main(String[] args) {
		DemoBase.run(D_Regexp2.class);
	}

}
