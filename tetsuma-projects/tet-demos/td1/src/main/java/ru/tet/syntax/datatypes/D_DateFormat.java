package ru.tet.syntax.datatypes;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ru.tet.aux.swing.DemoBase;

public class D_DateFormat extends DemoBase {

	@Override
	public Object fixResultValue(Object value) throws Exception {
		if (value instanceof Date d) {
			return DateFormat.getDateTimeInstance().format(d);
		}
		return super.fixResultValue(value);
	}

	public void test1() throws Exception {
		/*
		java.text.DateFormat
		Абстрактный класс для форматирования java.util.Date
		Позволяет получить форматтер по умолчанию для заданной или текущей локали
		
		DateFormat.getDateInstance(int style, Locale aLocale)
		
		style
		SHORT
		MEDIUM
		LONG
		FULL
		
		
		
		 */

		Date d1 = new Date();
		Locale locEn = Locale.ENGLISH;
		Locale locRu = Locale.of("ru", "RU");

		logEval1(
				DateFormat.getDateInstance().format(d1),

				DateFormat.getDateInstance(DateFormat.SHORT).format(d1),
				DateFormat.getDateInstance(DateFormat.MEDIUM).format(d1),
				DateFormat.getDateInstance(DateFormat.LONG).format(d1),

				DateFormat.getDateInstance(DateFormat.SHORT, locEn).format(d1),
				DateFormat.getDateInstance(DateFormat.MEDIUM, locEn).format(d1),
				DateFormat.getDateInstance(DateFormat.LONG, locEn).format(d1),

				DateFormat.getDateInstance(DateFormat.SHORT, locRu).format(d1),
				DateFormat.getDateInstance(DateFormat.MEDIUM, locRu).format(d1),
				DateFormat.getDateInstance(DateFormat.LONG, locRu).format(d1)

		);

	}

	public void test2() throws Exception {
		/*
		DateFormat.getDateTimeInstance(int dateStyle, int timeStyle, Locale aLocale) 
		 */

		Date d1 = new Date();
		Locale locEn = Locale.ENGLISH;
		Locale locRu = Locale.of("ru", "RU");

		logEval1(
				DateFormat.getDateTimeInstance().format(d1),

				DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, locRu).format(d1),
				DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, locRu).format(d1),
				DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locRu).format(d1),

				DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, locEn).format(d1),
				DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, locEn).format(d1),
				DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locEn).format(d1)

		);

	}

	public void test3() throws Exception {
		/*
		
		 */

		//		Date d1 = new Date();

		SimpleDateFormat df1 = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.ENGLISH);

		Date d1 = df1.parse("05.02.2008 12:31");
		r.s1 = df1.format(d1);

		SimpleDateFormat df2 = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		r.s2 = df2.format(new Date());

		SimpleDateFormat df3 = new SimpleDateFormat("MMMMM");
		r.s3 = df3.format(new Date());

	}

	public void test4() throws Exception {
		/*
		Задание символов форматирования
		
		 */

		DateFormatSymbols symbols = new DateFormatSymbols(Locale.ENGLISH);

		String[] capitalDays = { "", "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT" };
		symbols.setShortWeekdays(capitalDays);

		SimpleDateFormat df1 = new SimpleDateFormat("E", symbols);
		Date d1 = new Date();
		r.s1 = df1.format(d1);
		

	}

	@Override
	protected void doInit() throws Exception {
		options().hlComments = false;
	}

	public static void main(String[] args) {
		DemoBase.run(D_DateFormat.class);
	}

}
