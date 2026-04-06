package ru.tet.java.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class FormatterDemo {

	public static void main(String[] args) throws Exception {
		demo1();
	}

	public static void demo1() throws Exception {

		String s = String.format("Hello %2$s, welcome to %1$s !", "Baeldung", "Folks");
		System.out.println(s);

		Calendar c = new GregorianCalendar(2017, 11, 22);
		s = String.format("monthNo=%tm, monthName='%1$tB', day=%1$te, year=%1$tY, hour=%1$tl, minute=%1$tM",
				c.getTime());
		System.out.println(s);

		c = Calendar.getInstance();
		System.out.format("%te %<tB, %<tY%n", c);
		System.out.format("%tl:%<tM %<tp%n", c);
		System.out.format("%tD%n", c);

		
		System.out.format("The number 25 in decimal = %d%n", 25);
	    System.out.format("The number 25 in octal = %o%n", 25);
	    System.out.format("The number 25 in hexadecimal = %x%n", 25);
	    
	    System.out.format("d = %f, %1$.2f, %1$3.2f %n", 1023.019);	    
	    System.out.format("d = %f, %1$3.4f %n", 1023.01934);	    
	    
	    System.out.format("e = %+10.4f%n", Math.E);	    
	    
	    
	}		
		

}
