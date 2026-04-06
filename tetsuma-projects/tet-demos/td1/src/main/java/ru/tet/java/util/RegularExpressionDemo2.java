package ru.tet.java.util;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressionDemo2 {

	public static void groupDemo() throws Exception {

		testGroups("abc", "(.(.(.)))");
		testGroups("test@example.com",  "(\\w+)@(\\w+\\.\\w+)");
		

	}

	static void testGroups(String input, String regex) throws Exception {
		
//		Группа - это подвыражения внутри регулярного выражения, которое выделяется с помощью круглых скобок ().
//		Всегда есть нулевая группа - это всё выражение
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);

		matcher.find();
		System.out.println(matcher.groupCount());
		for (int i = 0; i <= matcher.groupCount(); i++) {
			System.out.format("group %d. '%s' (%d-%d)%n", i, matcher.group(i), matcher.start(i), matcher.end(i));
		}
	}
	
	
	public static void appendReplacementDemo() throws Exception {

//Matcher	appendReplacement(StringBuffer sb, String replacement)
//  считывает текст с input-а, начиная с append position и до конца найденного выражения, делает в нём замену на replacement, записывает результат в sb
//replacement может содержать выражение ${no} - для замены по группе
//  Используется для поиска и замены выражений.
		
		
		Pattern p = Pattern.compile("(cat)");
		Matcher m = p.matcher("one cat, two cats, or three cats on a fence");
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "$1erpillar");
//			break;  //заменить только первое выражение
		}
		m.appendTail(sb);
		System.out.println(sb);

	}

	public static void splitDemo() throws Exception {
		Pattern p = Pattern.compile(",\\s");
		String[] fields = p.split("John Doe, 47, Hillsboro Road, 32000");
		for (int i = 0; i < fields.length; i++)
			System.out.println(fields[i]);
	}
	
	
	public static void predicateDemo() throws Exception {
		//asPredicate() - Создаёт предикат, который тестирует, есть ли в тексте заданное выражение
		
		List progLangs = Arrays.asList("apl", "basic", "c", "c++", "c#", "cobol", "java", "javascript", "perl",
				"python", "scala");
		Pattern p = Pattern.compile("^c");
		progLangs.stream().filter(p.asPredicate()).forEach(System.out::println);
	}

	public static void lookingAtDemo() throws Exception {
//		boolean	lookingAt()
//		  возвращает true, если любая часть текста соответствует шаблону. 
		
//		boolean	matches()
//		  возвращает true, если весь текст соответствует шаблону.
		
		Pattern p = Pattern.compile("\\w+"); 
		Matcher m = p.matcher("abc!"); 
		System.out.println(m.matches());	//false
		System.out.println(m.lookingAt());	//true
		
	}
	
	
	public static void main(String[] args) throws Exception {
		
		groupDemo();
		
//		lookingAtDemo();
		
//		appendReplacementDemo();
		
//		predicateDemo();
//		demo6();
//		demo7();
//		demo8();
	}

	

}
