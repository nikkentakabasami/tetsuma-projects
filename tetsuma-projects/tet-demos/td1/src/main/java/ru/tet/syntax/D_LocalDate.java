package ru.tet.syntax;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;

import ru.tet.aux.swing.DemoBase;

public class D_LocalDate extends DemoBase {

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
		LocalDate
		Способы создания
		 */
		
		logEval(
				LocalDate.now(),
				LocalDate.of(2010, 01, 14),
				LocalDate.of(2014, Month.MARCH, 14),
				LocalDate.ofYearDay(1995, 256),
				LocalDate.ofEpochDay(150),
				LocalDate.parse("2007-12-03"),
				LocalDate.parse("01.01.2021", dateFormatter),
				LocalDate.EPOCH
		);

		

	}

	public void test2() throws Exception {
		/*
		LocalDate немутабельный.
		модификация 
		 */
		LocalDate ld1 = LocalDate.parse("2007-12-03");
		
		r.s1 = ld1.plusDays(1).minus(1, ChronoUnit.MONTHS);

		//получение частей
		r.s2 = ld1.getYear();
		r.s3 = ld1.getDayOfMonth();
		r.s4 = ld1.getMonth().toString();

		//Преобразование в LocalDateTime
		LocalDateTime ldt1 = LocalDate.now().atStartOfDay();		//18.08.2026 00:00:00
		LocalDateTime ldt2 = LocalDate.now().atTime(14, 55, 55);//18.08.2026 14:55:55
		

		r.s5 = ldt1;
		r.s6 = ldt2;
		
	}

	public void test3() throws Exception {
		/*
		DateTimeFormatter
		
		 */
		
		
		LocalDateTime ldt1 = LocalDateTime.now();
		r.s1 = dateFormatter.format(ldt1);

		LocalTime lt1 = LocalTime.now();
		r.s2 = timeFormatter.format(lt1);

		r.s3 = lt1.toString();	//15:39:57.439124830
		
		
		LocalDate ld1 = LocalDate.now();
		r.s4 = dateFormatter.format(ld1);
		
		
		LocalDate ld2 = LocalDate.parse("01.01.2021", dateFormatter);
		r.s5 = ld2;

		//форматирование по умолчанию
		r.s6 = ld2.toString();
		
		LocalTime lt3 = LocalTime.parse("15:39:57", timeFormatter);
		r.s7 = lt3;
		
		
		
	}

	public void test4() throws Exception {
		/*
		java.time.Instant
		Временная метка с точностью до наносекунды
		
		Оборачивает 2 поля:
		long seconds;
		int nanos;
		 */

		Instant i1 = Instant.now();
		Instant i2 = Instant.ofEpochMilli((long) 1e12);

		ZoneId zid = ZoneId.systemDefault();
		
		r.s1 = LocalDateTime.ofInstant(i1,zid);
		r.s2 = LocalDateTime.ofInstant(i2,zid);
	}


	public static void main(String[] args) {
		DemoBase.run(D_LocalDate.class);
	}

}
