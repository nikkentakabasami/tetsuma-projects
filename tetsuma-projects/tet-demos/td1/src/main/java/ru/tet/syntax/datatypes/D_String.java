package ru.tet.syntax.datatypes;

import java.util.stream.IntStream;

import ru.tet.aux.AuxTest;
import ru.tet.aux.swing.DemoBase;

public class D_String extends DemoBase {

	public void test1() throws Exception {
		/*
		
		 */

		r.s1 = "Это строка\nс переносом";

		r.s2 = "\"Спартак\" - Чемпион!";

		r.s3 = """
				Это многострочная
				строка в Java 15+.
				символы можно задавать юникод-кодами: \u2206
				Иконки имеют коды: u2600 - u27FF
				""";

		r.s4 = "Иконки: \u2700 \u2B00 \u2B01";
		
	}

	public void test2() throws Exception {
		r.s1 = IntStream.of(55).toArray();
		r.s3 = IntStream.of(5, 7, 11, 13).toArray();
	}

	public void test3() throws Exception {
		/*
		Задание символов кодами
		 */
		
		log2("\u270E \u270F \u2710");
		log2('\u2711');
		
		
	}

	public void test4() throws Exception {
		/*
		
		 */
	}

	public static void main(String[] args) {
		DemoBase.run(D_String.class);
	}

}
