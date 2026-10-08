package ru.tet.syntax.datatypes.string;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import ru.tet.aux.DemoAuxDataSamples;
import ru.tet.aux.DemoAuxObjectSamples;
import ru.tet.aux.swing.DemoBase;
import ru.tet.beans.User;

public class D_StringUtils extends DemoBase {

	String s1;

	public void test1() throws Exception {
		/*
		String     abbreviate(String str, int maxWidth)
		String     abbreviateMiddle(String str, String middle, int length)
		Сокращение строки с использованием многоточий
		offset - символ который должен быть показан в результате
		 */

		logEval1(
				s1 = "abcdefghijklmno",
				StringUtils.abbreviate(s1, 6),
				StringUtils.abbreviate(s1, 7),
				StringUtils.abbreviate(s1, 4),

				StringUtils.abbreviate(s1, 10),
				StringUtils.abbreviate(s1, 5, 10),

				StringUtils.abbreviateMiddle(s1, "...", 7));
		/*
		String     capitalize(String str)
		String     uncapitalize(String str)
		  Делает первую букву заглавной (или нет)
		
		
		String     center(String str, int size)
		String     center(String str, int size, char padChar)
		String     center(String str, int size, String padStr)
		  Центрирует строку в большей строке, окружая её пробелами(или заданными
		строками прокладки)
		
		
		 */

		logEval2(
				StringUtils.capitalize("cat"),
				StringUtils.capitalize("боб"),
				StringUtils.center("hi", 10),
				StringUtils.center("hi", 10, '\u26e4'),
				StringUtils.center("hi", 10)

		);

	}

	public void test2() throws Exception {
		/*
		String	join(int[] array, char separator)
		String	join(int[] array, char delimiter, int startIndex, int endIndex)
		
		 */

		logEval1(

				String.join("+", "a", "b"),
				StringUtils.join("a", "b", "c"),
				StringUtils.join(DemoAuxDataSamples.numbersRandomArray),
				StringUtils.join(DemoAuxDataSamples.numbersRandomArray, '-'),
				StringUtils.join(DemoAuxDataSamples.numbersList, '-'),
				StringUtils.isEmpty("")

		);

	}

	public void test3() throws Exception {
		/*
		String     random(int count)
		Строка из любых символов
		
		String     random(int count, boolean letters, boolean numbers)
		String     randomAlphabetic(int count)
		String     randomNumeric(int count)
		String     randomAscii(int count)
		Строка из ascii-символов и чисел
		
		String     random(int count, String chars)
		Строка из заданных символов
		
		 */

		logEval1(
				RandomStringUtils.randomAlphabetic(6),

				RandomStringUtils.randomNumeric(6),
				RandomStringUtils.randomAscii(6),

				RandomStringUtils.random(7, "lmnop"),

				RandomStringUtils.random(7, true, true));

	}

	public void test4() throws Exception {
		/*
		SerializationUtils
		
		сериализация/десериализация/клонирование объектов.
		Объекты должны быть Serializable!
		
		
		 */

		User user1 = DemoAuxObjectSamples.createTestUserBean();

		User user2 = SerializationUtils.clone(user1);

		byte[] data = SerializationUtils.serialize(user1);

		Object user3 = SerializationUtils.deserialize(data);
		
		logEval1(
				StringEscapeUtils.escapeHtml3("1<5=777; <88>"),
				user2.toString(),
				user3.toString(),
				data

		);

	}

	@Override
	protected void doInit() throws Exception {
		options().hlComments = false;
	}

	public static void main(String[] args) {
		DemoBase.run(D_StringUtils.class);
	}

}
