package ru.tet.syntax.datatypes;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import ru.tet.aux.swing.DemoBase;

public class D_NumberFormat extends DemoBase {

	Locale locEn = Locale.ENGLISH;
	Locale locRu = Locale.of("ru", "RU");
	
	double n1 = 12345678.1234567;
	double n2 = 1.5;
	
	
	public void test1() throws Exception {
		/*
		java.text.NumberFormat
		Абстрактный базовый класс для всех числовых форматов.
		
		
		NumberFormat.getInstance(Locale loc)
		NumberFormat.getNumberInstance(Locale loc)
		для вещественных чисел.
		аналогичны.
		
		NumberFormat.getCompactNumberInstance(Locale loc, Style style)
		
		NumberFormat.getIntegerInstance(Locale loc) 
		NumberFormat.getPercentInstance(Locale loc) 
		NumberFormat.getCurrencyInstance(Locale loc)
		
		
		
		String 	format(number)
		Форматирование
		
		
		Number 	parse(String source)
		Парсинг
		 */



		logEval1(
				NumberFormat.getInstance(locEn).format(n1),
				NumberFormat.getNumberInstance(locEn).format(n1),
				NumberFormat.getCompactNumberInstance(locEn,NumberFormat.Style.SHORT).format(n1),
				NumberFormat.getCompactNumberInstance(locEn,NumberFormat.Style.LONG).format(n1),

				NumberFormat.getIntegerInstance(locEn).format(n1),
				NumberFormat.getPercentInstance(locEn).format(n1),
				NumberFormat.getCurrencyInstance(locEn).format(n1)


		);

	}

	public void test2() throws Exception {
		/*
		
		 */

		double n1 = 12345678.1234567;

		logEval1(
				NumberFormat.getNumberInstance(locRu).format(n1),
				NumberFormat.getCompactNumberInstance(locRu,NumberFormat.Style.SHORT).format(n1),
				NumberFormat.getCompactNumberInstance(locRu,NumberFormat.Style.LONG).format(n1),

				NumberFormat.getIntegerInstance(locRu).format(n1),
				NumberFormat.getPercentInstance(locRu).format(n1),
				NumberFormat.getCurrencyInstance(locRu).format(n1)
		);		
		
	}

	public void test3() throws Exception {
		/*
		
		 */

		double n1 = 12345678.1234567;

		NumberFormat nf1 = NumberFormat.getNumberInstance(locRu);
		nf1.setMaximumFractionDigits(5);
		nf1.setMaximumIntegerDigits(5);
		nf1.setGroupingUsed(false);
		
		logEval1(
				nf1.format(1.5),
				nf1.format(n1)
		);		
		
		nf1.setMinimumIntegerDigits(3);
		nf1.setMinimumFractionDigits(3);
		
		logEval2(
				nf1.format(1.5)
		);		
		
	}


	DecimalFormat df1,df2;
	DecimalFormatSymbols symbols;
	
	public void test4() throws Exception {
		/*
		java.text.DecimalFormat
		форматирование десятичных чисел используя заданный шаблон.
		 */

//		double d1 = 1234.123456;
//		double d2 = 33_555_457.1;

		
	  //Задание символов, используемых при форматировании, из локали
    symbols = new DecimalFormatSymbols(locRu);
    
    //самый базовый шаблон (группировка, максимум 4 знака после запятой)
		df1 = new DecimalFormat("#,##0.####", symbols);
    
		logEval1(
				df1.toPattern(),
				df1.format(n1),
				df1.format(1.5),
				df1.format(-0.3),
				df1.format(0)
		);


		log2Splitter();

	  //Явное задание символов, используемых при форматировании
    symbols = new DecimalFormatSymbols();
    symbols.setDecimalSeparator('.');
    symbols.setGroupingSeparator('_'); 
    
		//группировать по разрядам, минимум 4 числа после запятой
		df1 = new DecimalFormat("#,##0.0000",symbols);

		logEval2(
				df1.toPattern(),
				df1.format(n1),
				df1.format(1.5),
				df1.format(-0.3),
				df1.format(0)
		);

		log2Splitter();

		//группировать по разрядам, дополнять нулями до нужного размера
		df1 = new DecimalFormat("#,000.0000",symbols);

		logEval3(
				df1.toPattern(),
				df1.format(n1),
				df1.format(1.5),
				df1.format(-0.3),
				df1.format(0)
		);		
		

		
//		df1 = new DecimalFormat("¥###,###.###",symbols);
//		r.s8 = df1.format(n1);
		
		
		
	}	
	

	@Override
	protected void doInit() throws Exception {
		options().hlComments = true;
	}

	public static void main(String[] args) {
		DemoBase.run(D_NumberFormat.class);
	}

}
