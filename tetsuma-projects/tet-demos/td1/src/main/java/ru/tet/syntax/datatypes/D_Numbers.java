package ru.tet.syntax.datatypes;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import ru.tet.aux.swing.DemoBase;

//Шаблон для создания новых демо
public class D_Numbers extends DemoBase {


	public void test1() throws Exception {
		/*
		Объявление чисел.
		 */

		int[] numbers =
				{
						//целое число в бинарной форма
						0b1100101,

						//Целые в десятичной форме
						+5, -7, 12345678,

						//Целые в восьмеричной форме (начинаются с нуля)
						027, -0326, 0777,

						//Целые в шестнадцатиричной форме
						0xff, 0xFC2D, 0x45a8, 0X77FF,

						//начиная с версии 7 разрешены подчеркивания внутри чисел для улучшения читаемости
						33_555_45, 11_22_33, 0xD6_A3_A3,

						Integer.MAX_VALUE,
						Integer.MIN_VALUE,
						
				};

		double[] floats =
				{
						37.25d, -128.678, +27.035, .5, 33_555_45.44_77,

						//(f в конце - сохранение константы в формате float(по умолчанию double))
						3.5f, -45.67F,

						4.7e-5f, 2.5e10, -0.345e-25,
						
						Double.POSITIVE_INFINITY,
						Double.NEGATIVE_INFINITY,
						Double.NaN
				};

		r.s1 = numbers;
		r.s2 = floats;

		//log2(numbers);
		//log2(floats);

		//		System.out.println(Arrays.toString(numbers));
		//		System.out.println(Arrays.toString(floats));

	}

	public void test2() throws Exception {
		/*
		форматирование чисел
		
		String.valueOf(d)
		  простейший способ форматирования чисел.
		 */

		double d1 = 1234.123456;
		double d2 = 33_555_45.1;

		r.s1 = String.valueOf(d1);
		r.s2 = String.valueOf(d2);

		/*
		java.text.DecimalFormat
		Универсальный класс для форматирования десятичных чисел, используя заданный шаблон
		 */

		//Явное задание символов, используемых при форматировании
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator('.');
		symbols.setGroupingSeparator('_');
		
		//самый базовый шаблон (группировка, максимум 4 знака после запятой)
		DecimalFormat df1 = new DecimalFormat("###,##0.####", symbols);
		r.s3 = df1.format(d1);
		r.s4 = df1.format(d2);
		
		
		//группировать по разрядам, дополнять нулями до нужного размера
		df1 = new DecimalFormat("00,000.0000", symbols);
		r.s5 = df1.format(d1);
		r.s6 = df1.format(d2);
		r.s7 = df1.format(-0.5);
		

	}

	public void test3() throws Exception {
		/*
		Парсинг
		
		Double.valueOf(s)
		Простейший способ парсинга
		 */

		Double d1 = Double.valueOf("123456.12");
		
		r.s1 = d1;

		
		/*
		Парсинг через DecimalFormat
		 */
		
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator('.');
		symbols.setGroupingSeparator('_');
		
		DecimalFormat df1 = new DecimalFormat("###,###.#", symbols);
		Number n1 = df1.parse("1_234.123456");
		r.s2 = n1;
		
		
		
		
	}

	public void test4() throws Exception {
		/*
		
		 */
		r.s1 = Double.toHexString(11.33);
		
		r.s2 = Integer.toHexString(19);
		r.s3 = Integer.toBinaryString(19);
		r.s4 = Integer.toOctalString(19);
		
		//для показа числа в байтовом виде
		r.s5 = Integer.toBinaryString(19 & 0xFF);
		r.s6 = Integer.toBinaryString(-19 & 0xFF);
		
		 
		
	}

	@Override
	protected void doInitControlPanel() throws Exception {
		addTest1Button(null);
		addTest2Button(null);
		addTest3Button(null);
		addTest4Button(null);
	}

	public static void main(String[] args) {
		DemoBase.run(D_Numbers.class);
	}

}
