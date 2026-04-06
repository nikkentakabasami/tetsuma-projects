package ru.tet.java.util;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressionDemo1 {

	public static void main(String[] args) throws Exception {
//		demo1();
//		demo2();
//		demo3();
		demo4();
	}

	static void testRegex(String input, String regex) throws Exception {
		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(input);

		System.out.println(regex);
		while (matcher.find()) {
			System.out.format("'%s' (%d-%d)%n", matcher.group(), matcher.start(), matcher.end());
		}
		System.out.println();
	}


	public static void demo4() throws Exception {

		// поиск без учёта регистра
		testRegex("Treehouse", "(?i)tree");
		testRegex("Treehouse", "tree");

		// многострочный поиск
		testRegex("abc\nabc", "(?m)^abc$");

		// комментарии
		testRegex("matter", ".at(?x)#match hat, cat, and so on");

		String text = "fox box pox";

		testRegex(text, ".*?ox");
		testRegex(text, ".*+ox"); // не находит ничего - ведь ".*" поглощает весь текст, а возврата назад нет

		testRegex("in 1212 dc", "(\\d\\d)\\1");

		text = "ЛюдовикXV, ЛюдовикXVI, ЛюдовикXVIII, ЛюдовикLXVII, ЛюдовикXXL";

		testRegex(text, "Людовик(?=XVI)");
		testRegex(text, "Людовик(?!XVI)");

		testRegex(text, "Людовик(?!XV)");

		text = "Сергей Иванов, Игорь Иванов";

		testRegex(text, "(?<=Сергей )Иванов");
		testRegex(text, "(?<!Сергей )Иванов");

		testRegex("трам-трам-трумтрам-трум-трамтрум", "(тр[ау]м-?)*");

		testRegex("трам-трумтрам-трум", "(трам|трум)");

		text = "</div><div class=\"comment displayed\"><a class=\"avatar avatar--smd\" title=\"User, Roman\"></a>";
		testRegex(text, "<.+>");
		testRegex(text, "<.+?>"); // ленивая
		testRegex(text, "<.{2,}+>"); // ревнивая

		testRegex("aaa aaa", "\\ba"); // искать в начале слов
		testRegex("aaa aaaa", "\\Ba+\\B"); // искать в середине слов

		testRegex("что случилось", "\\bс"); // \b работает только для латиницы

		testRegex("挟み込む", "[\\u3400-\\u4DB5\\u4E00-\\u9FCB\\uF900-\\uFA6A]+");
		testRegex("What Is This", "[:upper:]+"); // posix не работает

		text = "123 2525 141414141414 2563 7841";

		testRegex(text, "(\\d\\d)\\1");
		testRegex(text, "(\\d\\d)\\1\\1");

//		testRegex(text, "\\b\\d+");

	}

	public static void demo3() throws Exception {

		String text = "123456789";

		testRegex(text, "[1-3[7-9]]");
		testRegex(text, "[1-37-9]");
		testRegex(text, "[1-6&&[3-9]]");
		testRegex(text, "(\\d\\d)");

	}

	public static void demo2() throws Exception {

		String text = "What Ever";
//		testRegex(text, ".");
//		testRegex(text, "[^er]");
//		testRegex(text, "[A-Z]");

//		String regex = Pattern.quote("t[],t.");
//		testRegex("mat[],t.sp", regex);

	}

	public static void demo1() throws Exception {

		String text = "I found 3 cats there. И что то лопнуло.";

		testRegex(text, "[rcb]at");
		testRegex(text, "\\d");

		testRegex(text, "\\w+");

	}

}
