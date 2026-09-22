package ru.tet.syntax.datatypes.string;

import java.text.ChoiceFormat;
import java.text.Format;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import ru.tet.aux.swing.DemoBase;
import ru.tet.demos.AuxTest;

public class D_MessageFormat extends DemoBase {

	public void test1() throws Exception {
		/*
		 */

		String result =
				MessageFormat.format(
						"At {0, time, medium} on {0, date}, there was {1} on planet {2, number}.",
						new Date(), "a disturbance", 7);
		log2(result);

		Object[] testArgs = { 1273, "MyDisk" };
		MessageFormat mf = new MessageFormat("The disk \"{1}\" contains {0} file(s).");

		log2(mf.format(testArgs));

	}

	@AuxTest(2)
	void displayMessage(Locale loc) {

		Object[] messageArguments =
				{
						"Mars",
						Integer.valueOf(12345),
						new Date()
				};
		MessageFormat mf = new MessageFormat("At {2,time,short} on {2,date,long}, we detected {1,number,integer} spaceships on the planet ''{0}''.", loc);
		String r = mf.format(messageArguments);
		log2(r);
	}

	public void test2() throws Exception {
		/*
		Различия в форматировании в зависимости от локали
		 */
		displayMessage(Locale.ENGLISH);
		displayMessage(Locale.GERMANY);
		displayMessage(Locale.of("ru", "RU"));

	}

	public void test3() throws Exception {
		/**
		Форматирование чисел:
		 */
		
		MessageFormat mf = new MessageFormat("{0} | {0,number} | {0,number, #00.#} | {1,number, #,###.###}");

		Object[] objs = { Math.PI, 12345.12345};
		String r = mf.format(objs);
		log2(r);

		
	}



	public void test4() throws Exception {
		/*
		Форматирование дат:
		
		 */
		MessageFormat mf = new MessageFormat("{0} | {0,date,yyyy_MM_dd_HH_mm_ss} | {0,date,E} | {0,date,short} | {0,date,long} ");

		Date d1 = new Date();
		//Не поддерживает LocalDateTime!
		LocalDate ld1 = LocalDate.now();
		Object[] objs = { d1 , ld1 };
		String r = mf.format(objs);
		log2(r);
		

	}

	@Override
	protected void doInit() throws Exception {
		options().hlComments = false;
	}

	public static void main(String[] args) {
		DemoBase.run(D_MessageFormat.class);
	}

}
