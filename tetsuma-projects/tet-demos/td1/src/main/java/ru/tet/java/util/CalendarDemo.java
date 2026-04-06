package ru.tet.java.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class CalendarDemo {
	
	static Locale LOCALE = Locale.of("ru", "RU");
	
	public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy",LOCALE);
	public static final SimpleDateFormat SIMPLE_DATETIME_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm",LOCALE);
	
	public static void main(String[] args) {
		calDemo1();
		calDemo2();
		calDemo3();
	}

	
	public static void printDate(Date d) {
	    System.out.print(SIMPLE_DATE_FORMAT.format(d)+" ");
	}
	
	
	public static void calDemo1() {
		System.out.println("\nroll month");
	    GregorianCalendar cal = new GregorianCalendar(2018, 11, 28);
	    printDate(cal.getTime());
	    cal.roll(Calendar.MONTH, 1);
	    printDate(cal.getTime());
	}

	public static void calDemo2() {
		System.out.println("\nadd month");
	    GregorianCalendar cal = new GregorianCalendar(2018, 11, 28);
	    printDate(cal.getTime());
	    cal.add(Calendar.MONTH, 1);
	    printDate(cal.getTime());
	}
	
	public static void calDemo3() {
		System.out.println("\nlimits");
	    GregorianCalendar cal = new GregorianCalendar(2018, 1, 15);
	    printDate(cal.getTime());
	    
	    System.out.println("\ndom actual maximum: "+cal.getActualMaximum(Calendar.DAY_OF_MONTH));
	    System.out.println("dom maximum: "+cal.getMaximum(Calendar.DAY_OF_MONTH));
	    
	}
	
}
