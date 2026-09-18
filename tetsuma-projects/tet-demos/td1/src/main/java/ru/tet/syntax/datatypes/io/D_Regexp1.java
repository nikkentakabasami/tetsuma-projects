package ru.tet.syntax.datatypes.io;

import java.awt.EventQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ru.tet.aux.swing.DemoBase;

/**
 * Вложенные флаговые выражения
 */
public class D_Regexp1 extends D_RegexpBase {

	/*
	Вложенные флаговые выражения
	Включают флаги прямо внутри регулярного выражения, используя конструкцию вида (?flags)
	
	Основные флаги:
	
	(?i)	CASE_INSENSITIVE
	(?u)	UNICODE_CASE
	(?m)	MULTILINE
	(?x)	COMMENTS
	(?d)	UNIX_LINES
	(?s)	DOTALL
	 */
	public static String currentRegexps = """

				//Вложенные флаговые выражения
				(?i)tenka #поиск без учёта регистра
				(?i)сергей #без опции UNICODE_CASE не работает
				(?iu)сергей
				
				(?iu)сергей(?-i) Иванов #Global toggle
				(?iu:сергей) Иванов #Scoped group

				^Людовик
				(?m)^Людовик
				
				(?x)1\\sиндейка  #my comment

//Символьные классы

//в js такие пересечения невозможны:
[ст[50]]+
[a-z&&[^muas]]+

a{2,}

//Обратная ссылка
//ищет 2 числа, за которыми следуют такие же 2 числа
(\\d\\d)\\1
				
//поиск с группами
(Лю)до(вик)

//квантификация группы
(тр[ау]м-?)+

//экранирование
\\Q[some.\\E


				
			""";

	/*
	testRegex("Treehouse", "(?i)tree");
	testRegex("Treehouse", "tree");
	
	// многострочный поиск
	testRegex("abc\nabc", "(?m)^abc$");
	
	// комментарии
	testRegex("matter", ".at(?x)#match hat, cat, and so on");
	* 
	*/

	public D_Regexp1() {
		super(currentRegexps);
	}

	public void test2() throws Exception {
		/*
		Задание флагов глобально
		*/
		
		//поиск слова в начале каждой строки текста.
		Pattern pattern = Pattern.compile("^людовик", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE | Pattern.MULTILINE);

		Matcher matcher = pattern.matcher(D_RegexpBase.testText);
		
		while (matcher.find()) {
			log2Format("'%s' (%d-%d)", matcher.group(), matcher.start(), matcher.end());
		}
		
	}

	public void test3() throws Exception {

	}

	public void test4() throws Exception {
		/*
		
		*/
	}

	public static void main(String[] args) {
		DemoBase.run(D_Regexp1.class, 1);
	}

}
