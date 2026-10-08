package ru.tet.syntax.datatypes.string;

import java.util.List;
import java.util.stream.IntStream;

import ru.tet.aux.DemoAuxDataSamples;
import ru.tet.aux.swing.DemoBase;
import ru.tet.demos.AuxTest;

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

		r.s5 = "line1" + System.lineSeparator() + "line2";

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
		List<String> list1 = DemoAuxDataSamples.makeStringList(3);
		logEval1(
				String.join(",", "one", "two", "three"),
				String.join(",", list1)

		);

		
	}
	
	@Override
	public void test5() throws Exception {
//		"\u270E \u270F \u2710".codePoints().forEach(c->log2(Integer.toHexString(c)));
//		IntStream range = IntStream.range(0x2600, 0x26FF);
		
		/*
		IntStream range = IntStream.rangeClosed(0x2600, 0x26FF);
		
		
		range.forEach(cp->log2(Integer.toHexString(cp)));
		
		int[] cps = range.toArray();
		String s = new String(cps, 0, cps.length);
		log2(s);
		IntStream.rangeClosed(0x2600, 0x26FF).toArray();
		
		for (int codePoint = 0x2600; codePoint <= 0x27FF; codePoint++) {
			String glyph = new String(Character.toChars(codePoint));
			log2(Integer.toHexString(codePoint),glyph);
		}
		*/		
		
		log2("символы-иконки, записываются в формате \\u2600");
		log2("new String(Character.toChars(0x2600))");
		
		
		for (int codePoint = 0x2600; codePoint <= 0x27FF; codePoint++) {
			
			if (codePoint%8==0) {
				log2NL();
				log2Inline("\\u"+Integer.toHexString(codePoint),"-","\\u"+Integer.toHexString(codePoint+8)+": ");
			}
			String glyph = new String(Character.toChars(codePoint));
			log2Inline(glyph);
		}
		
		
	}

	public static void main(String[] args) {
		DemoBase.run(D_String.class);
	}

}
