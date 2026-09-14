package ru.tet.syntax.datatypes;

import java.util.Arrays;

import ru.tet.aux.swing.DemoBase;

public class D_Char extends DemoBase {

	public void test1() throws Exception {
		/*
		Character
		Объектная версия типа char.
		
		В основном используются её статические методы.
		 */

		Character c1 = Character.valueOf('\u2700');
		char c2 = '\u01C8'; // LATIN CAPITAL LETTER: ǈ

		logEval1(
				'\u2700',
				c2,
				Character.isTitleCase(c2),
				Character.isUpperCase('A'),
				Character.isAlphabetic('A'),
				Character.isLetter('A'),
				Character.isLetterOrDigit('A'),

				Character.isDigit('7'),

				Character.toLowerCase('A'));

	}

	public void test2() throws Exception {
		/*
		char
		занимает 2 байта (в кодировке UTF-16).
		Он способен кодировать символы в диапазоне U+0000 до U+FFFF, то есть (BMP - Basic Multilingual Plane).
		
		codePoint
		целое число (int - 32 бита), которое представляет полный код символа в Юникоде.
		Может представлять любой символ Юникода, включая вне BMP
		Символы вне BMP кодируются двумя char-ми (суррогатными парами).
		 */

		String s = "𝄞"; // Углубленная нота (U+1D11E), вне BMP

		char[] chars = s.toCharArray();

		logEval1(
				s,
				s.length(),

				Arrays.toString(chars),

				s.codePointAt(0),
				s.charAt(0),

				Character.isSurrogate(chars[0]),
				Character.isSurrogatePair(chars[0], chars[1])

		);

	}

	public void test3() throws Exception {
		/*
		
		 */
	}

	public void test4() throws Exception {
		/*
		
		 */
	}

	public static void main(String[] args) {
		DemoBase.run(D_Char.class);
	}

}
