package ru.tet.java.text;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import ru.tet.aux.AuxTest;
import ru.tet.aux.swing.DemoBase;

//Шаблон для создания новых демо
public class D_NumberFormat extends DemoBase {

	@AuxTest
	class testClass {
	}


	public void test1() throws Exception {
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
		java.text.NumberFormat
		Абстрактный базовый класс для всех числовых форматов.
		
		String 	format(double number)
		Форматирование
		
		Number 	parse(String source)
		Парсинг
		
		Настройки:
		
		void 	setMaximumFractionDigits(int newValue)	//default 3
		void 	setMaximumIntegerDigits(int newValue)
		void 	setMinimumFractionDigits(int newValue)
		void 	setMinimumIntegerDigits(int newValue)
		
		void 	setGroupingUsed(boolean newValue)
		Разделять тысячные части целой части числа пробелами
		
		 */

		NumberFormat nf1 = NumberFormat.getNumberInstance();

		nf1.setMaximumFractionDigits(2);
		nf1.setMaximumIntegerDigits(2);
		r.s3 = nf1.format(d1);

		nf1 = NumberFormat.getNumberInstance();
		nf1.setMaximumFractionDigits(4);
		nf1.setMinimumFractionDigits(2);
		nf1.setGroupingUsed(true);

		r.s4 = nf1.format(d1);
		r.s5 = nf1.format(d2);

		//дополняем нулями до нужной длины
		nf1.setMinimumIntegerDigits(10);
		nf1.setMinimumFractionDigits(10);
		r.s6 = nf1.format(d1);

		//денежный формат
		Locale ruLocale = Locale.of("ru", "RU");
		nf1 = NumberFormat.getCurrencyInstance(ruLocale);
		r.s7 = nf1.format(d1);

		//проценты
		nf1 = NumberFormat.getPercentInstance(ruLocale);
		r.s8 = nf1.format(d1);
		
		

	}

	public void test2() throws Exception {
		/*
		java.text.DecimalFormat
		форматирование десятичных чисел используя заданный шаблон.
		 */

		double d1 = 1234.123456;
		double d2 = 33_555_457.1;

		
	  //Задание символов, используемых при форматировании, из локали
		Locale ruLocale = Locale.of("ru", "RU");
    DecimalFormatSymbols symbols = new DecimalFormatSymbols(ruLocale);
    
    //самый базовый шаблон (группировка, максимум 4 знака после запятой)
		DecimalFormat df1 = new DecimalFormat("###,##0.####", symbols);
		r.s1 = df1.format(d1);
		r.s2 = df1.format(d2);

	  //Явное задание символов, используемых при форматировании
    symbols = new DecimalFormatSymbols();
    symbols.setDecimalSeparator('.');
    symbols.setGroupingSeparator('_'); 
		
		//группировать по разрядам, минимум 4 числа после запятой
		df1 = new DecimalFormat("###,##0.0000",symbols);
		r.s3 = df1.format(d1);
		r.s4 = df1.format(d2);


		//группировать по разрядам, дополнять нулями до нужного размера
		df1 = new DecimalFormat("00,000.0000",symbols);
		r.s5 = df1.format(d1);
		r.s6 = df1.format(d2);
		r.s7 = df1.format(-0.5);
		
		df1 = new DecimalFormat("¥###,###.###",symbols);
		r.s8 = df1.format(d1);
		
		
		
	}

	public void test3() throws Exception {
		/*
		
		 */
	}

	@Override
	protected void doInitControlPanel() throws Exception {
		addTest1Button(null);
		addTest2Button(null);
		addTest3Button(null);
		addTest4Button(null);
	}

	public static void main(String[] args) {
		DemoBase.run(D_NumberFormat.class);
	}

}
