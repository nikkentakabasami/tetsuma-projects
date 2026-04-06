package ru.tet.java.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class DateTimeFormatterDemo {

	public static void main(String[] args) throws Exception {
//		demo1();
//		demo2();
		demo3();
	}

	
	public static void demo3() throws Exception {
		LocalDateTime dt = LocalDateTime.now();
	
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
		DateTimeFormatter timeColonFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		
		
		System.out.println(formatter.format(dt));		
		System.out.println(timeColonFormatter.format(dt));		
		
		
		ZonedDateTime zdt = ZonedDateTime.of(dt, ZoneId.systemDefault());
		ZonedDateTime zdt2 = ZonedDateTime.of(dt, ZoneId.of("UTC-4"));
		
		
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss z");
		System.out.println(formatter2.format(zdt));		
		System.out.println(formatter2.format(zdt2));		
		
		
		
		
	}
	
	
	public static void demo2() throws Exception {
		
		
		LocalDateTime dt = LocalDateTime.now();
//		LocalDate anotherSummerDay = LocalDate.of(2016, 8, 23);
		System.out.println(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(dt));
		System.out.println(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(dt));
		System.out.println(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).format(dt));
		System.out.println(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).format(dt));		
		
		
//		LocalDate anotherSummerDay = LocalDate.of(2016, 8, 23);
//		LocalTime anotherTime = LocalTime.of(13, 12, 45);
//		ZonedDateTime zonedDateTime = ZonedDateTime.of(anotherSummerDay, anotherTime, ZoneId.systemDefault());		
		ZonedDateTime zdt = ZonedDateTime.of(dt, ZoneId.systemDefault());		
		
		System.out.println(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).format(zdt));
		System.out.println(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).format(zdt));
		System.out.println(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(zdt));
		System.out.println(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(zdt));		
		
		
	}
	
	
	public static void demo1() throws Exception {
		
		LocalDateTime dt = LocalDateTime.now();
		
		
		System.out.println(DateTimeFormatter.BASIC_ISO_DATE.format(dt));
		
		System.out.println(DateTimeFormatter.ISO_LOCAL_DATE.format(dt));
		System.out.println(DateTimeFormatter.ISO_LOCAL_TIME.format(dt));
		System.out.println(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(dt));
//		System.out.println(DateTimeFormatter.RFC_1123_DATE_TIME.format(dt.toLocalDate()));

		
		
		String s = DateTimeFormatter.ISO_OFFSET_DATE.format(LocalDate.of(2018, 3, 9).atStartOfDay(ZoneId.of("UTC-3")));		
		System.out.println(s);
		
		
		
		
		
	}
	
}
