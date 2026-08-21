package ru.tet.syntax;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import ru.tet.aux.swing.DemoBase;

public class D_LocalDateTime extends DemoBase {

	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
	DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	@Override
	public Object fixResultValue(Object value) throws Exception {
		if (value instanceof LocalDate d) {
			return dateFormatter.format(d);
		}
		if (value instanceof LocalTime d) {
			return timeFormatter.format(d);
		}

		if (value instanceof Temporal d) {
			return dateTimeFormatter.format(d);
		}

		return super.fixResultValue(value);
	}

	public void test1() throws Exception {
		/*
		LocalDateTime
		Способы создания
		 */
		LocalDate ld1 = LocalDate.of(2010, 01, 14);
		LocalTime lt1 = LocalTime.of(10, 35);

		logEval(
				LocalDateTime.now(),
				LocalDateTime.of(ld1, lt1),
				LocalDateTime.of(2014, Month.MARCH, 14, 17, 33),
				LocalDateTime.of(2014, 8, 20, 17, 33, 55),
				LocalDateTime.parse("2015-02-20T06:30:00"),
				LocalDateTime.parse("01.01.2005 11:30:11", dateTimeFormatter));

	}

	public void test2() throws Exception {
		/*
		LocalDateTime
		модификация
		 */
		LocalDateTime ldt1 = LocalDateTime.parse("01.01.2005 11:30:11", dateTimeFormatter);

		r.s1 = ldt1.toString();

		//модификация
		r.s2 = ldt1.plusDays(1).minus(1, ChronoUnit.MONTHS).toString();

		//Немутабельный, поэтому ldt1 не изменился
		r.s3 = ldt1.toString();

		//получение частей
		r.s4 = ldt1.getYear();
		r.s5 = ldt1.getDayOfMonth();

	}

	public void test3() throws Exception {
		/*
		TemporalAdjusters
		Содержит методы для манипулирования датами
		
		 */

		LocalDateTime ldt1 = LocalDateTime.parse("05.01.2005 11:30:11", dateTimeFormatter);

		r.s1 = ldt1.with(TemporalAdjusters.firstDayOfMonth());
		r.s2 = ldt1.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
		r.s3 = ldt1.with(TemporalAdjusters.lastDayOfMonth());

		//кастомный TemporalAdjuster
		TemporalAdjuster addTenDays = temporal -> temporal.plus(10, ChronoUnit.DAYS);
		r.s4 = ldt1.with(addTenDays);

	}

	public void test4() throws Exception {
		/*
		java.time.LocalTime
		
		Содержит поля:
		byte hour;
		byte minute;
		byte second;
		int nano;
		
		 */

		LocalTime lt1 = LocalTime.now();
		LocalTime lt2 = LocalTime.of(17, 44);
		LocalTime lt3 = LocalTime.of(17, 44, 55, 666666666);
		LocalTime lt4 = LocalTime.parse("10:15:30");

		r.s1 = lt1;
		r.s2 = lt2;
		r.s3 = lt3;
		r.s4 = lt4;

	}


	public static void main(String[] args) {
		DemoBase.run(D_LocalDateTime.class);
	}

}
