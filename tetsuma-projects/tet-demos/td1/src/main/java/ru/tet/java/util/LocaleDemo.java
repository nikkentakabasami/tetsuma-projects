package ru.tet.java.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LocaleDemo {

	public static final Locale localeRu = Locale.of("ru", "RU");
	public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy", localeRu);
	
	
	public static void main(String[] args) {
		
		System.out.println(SIMPLE_DATE_FORMAT.format(new Date()));
		
	}
	
	
	
}
