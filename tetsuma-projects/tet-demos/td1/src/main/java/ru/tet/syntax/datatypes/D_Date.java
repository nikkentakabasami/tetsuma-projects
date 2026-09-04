package ru.tet.syntax.datatypes;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import ru.tet.aux.swing.DemoBase;

//Шаблон для создания новых демо
public class D_Date extends DemoBase {

	SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.ENGLISH);

	@Override
	public Object fixResultValue(Object value) throws Exception {
		if (value instanceof Date d) {
			return dateFormat.format(d);
		}
		if (value instanceof Calendar c) {
			return dateFormat.format(c.getTime());
		}

		return super.fixResultValue(value);
	}

	Date d1, d2, d3, d4, d5, d6;

	public void test1() throws Exception {
		/*
		java.util.Date
		устаревший легаси-класс для даты.
		
		new Date()
		new Date(long date)
		Date(int year, int month, int date)
		
		long	getTime()
		
		static Date	from(Instant instant)
		Instant	toInstant()
		
		 */

		logEval1(
				d1 = new Date(),
				d2 = new Date(125, 7, 12),
				d3 = dateFormat.parse("05.02.2008 12:31"),
				d4 = new Date((long) 1e12),
				d1.getTime(),
				d1.after(d2));

	}

	public void test2() throws Exception {
		/*
		java.util.Calendar
		Мутабельный
		
		Date	getTime()
		void	setTime(Date date)
		связь с датой
		
		 */
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = new GregorianCalendar(TimeZone.getTimeZone("GMT+5:30"), Locale.of("en", "IN"));

		TimeZone tz = TimeZone.getTimeZone("America/New_York");
		Calendar c3 = Calendar.getInstance(tz);

		logEval1(
				c1.get(Calendar.YEAR),
				c1.get(Calendar.MONTH),
				c1.get(Calendar.DAY_OF_MONTH),
				c1.get(Calendar.DAY_OF_WEEK),
				c1.getTime());

		logExpr1(() -> {
			//задаём части даты
			c1.set(Calendar.YEAR, 2025);
			c1.set(Calendar.MONTH, Calendar.OCTOBER);
			return c1;
		}, () -> {
			c1.set(2025, Calendar.JANUARY, 20);
			return c1;
		}, () -> {
			//задаём все части вместе
			c1.set(2025, Calendar.JANUARY, 20, 11, 32);
			return c1;
		}, () -> {
			Date d1 = new Date(125, 7, 12);
			c1.setTime(d1);
			return c1;
		}, () -> {
			c1.setTimeInMillis((long) 1e12);
			return c1;
		});

	}

	public void test3() throws Exception {
		/*
		конвертация Date в LocalDateTime
		
		 */
		Date d1 = dateFormat.parse("05.02.2008 12:31");
		Instant instant = Instant.ofEpochMilli(d1.getTime());
		LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

		logEval(instant.toString(), 
				ldt.toString());
		

	}

	public void test4() throws Exception {
		/*
		TimeUnit
		перечисление с временными единицами измерения
		 */

		logEval(
				TimeUnit.HOURS.toMinutes(5),
				TimeUnit.MILLISECONDS.toSeconds(5000));

	}

	public static void main(String[] args) {
		DemoBase.run(D_Date.class);
	}

}
