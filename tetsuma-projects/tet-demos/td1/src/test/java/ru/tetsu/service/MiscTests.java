package ru.tetsu.service;

import java.awt.geom.AffineTransform;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class MiscTests {

	
	public static void main(String[] args) {

		System.out.println(LocalDateTime.now());
		
		
		
		Date d = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.MONTH, 1);
        Date next = cal.getTime();
		
        System.out.println(d);
        System.out.println(next);
        
        
		
		
	}
	
}
