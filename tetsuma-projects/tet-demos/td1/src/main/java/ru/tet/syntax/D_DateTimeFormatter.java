package ru.tet.syntax;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.Temporal;

import ru.tet.aux.swing.DemoBase;

public class D_DateTimeFormatter extends DemoBase {

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
		DateTimeFormatter
		Форматирование и парсинг дат
		
		
		Предопределённые форматтеры
		
		DateTimeFormatter	ISO_LOCAL_DATE
		DateTimeFormatter	ISO_LOCAL_DATE_TIME
		Предопределённые форматтеры
		
		 */

		LocalDateTime ldt1 = LocalDateTime.now();

		logEval(
				DateTimeFormatter.BASIC_ISO_DATE.format(ldt1),
				DateTimeFormatter.ISO_LOCAL_DATE.format(ldt1),
				DateTimeFormatter.ISO_LOCAL_TIME.format(ldt1),
				DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(ldt1));

	}

	public void test2() throws Exception {
		/*
		DateTimeFormatter	ofLocalizedDate(FormatStyle dateStyle)
		Получение форматтера дат в соответствии с текущей локалью
		
		
		DateTimeFormatter	ofLocalizedDateTime(FormatStyle dateStyle)
		Выводит дату, время и временную зону.
		Так что надо либо задать зону в форматтер через withZone, либо передавать зонированное время.
		
		 */

		LocalDateTime ldt1 = LocalDateTime.now();
		ZonedDateTime zdt1 = ZonedDateTime.of(ldt1, ZoneId.systemDefault());

		logEval(
				DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(ldt1),
				DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(ldt1),
				DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).format(ldt1),
				DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).format(ldt1),

				DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).format(zdt1),
				DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).format(zdt1),
				DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(zdt1),
				DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(zdt1),

				//задаём зону в форматтер 
				DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)
						.withZone(ZoneId.systemDefault()).format(ldt1));


	}

	public void test3() throws Exception {
		/*
		static DateTimeFormatter	ofPattern(String pattern)
		static DateTimeFormatter	ofPattern(String pattern, Locale locale)
		Создание форматтера на основе паттерна
		
		y	год
		M	месяц
		d	день
		H	час дня
		m	минута
		s	секунда
		z	временная зона		
		XXX	offset
		 */

		LocalDateTime ldt1 = LocalDateTime.now();
		ZonedDateTime zdt1 = ZonedDateTime.of(ldt1, ZoneId.systemDefault());
		OffsetDateTime odt1 = OffsetDateTime.now();

		DateTimeFormatter f1 = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
		r.s1 = f1.format(ldt1);

		DateTimeFormatter f2 = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss z");
		r.s2 = f2.format(zdt1);

		DateTimeFormatter f3 = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss XXX");
		r.s3 = f3.format(odt1);

	}

	public void test4() throws Exception {
		/*
		DateTimeFormatter
		
		 */

		LocalDateTime ldt1 = LocalDateTime.parse("01.01.2005 11:30:11", dateTimeFormatter);

		r.s1 = dateTimeFormatter.format(ldt1);
		r.s2 = dateFormatter.format(ldt1);
		r.s3 = timeFormatter.format(ldt1);

		r.s4 = dateFormatter.format(ldt1.toLocalDate());

		LocalDateTime ldt2 = LocalDateTime.parse("2015-02-20T06:30:00"); //20.02.2015 06:30:00
		r.s5 = ldt2.toString();
	}

	public static void main(String[] args) {
		DemoBase.run(D_DateTimeFormatter.class);
	}

}
