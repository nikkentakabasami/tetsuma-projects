package ru.tet.java.time;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.stream.Collectors;

public class PeriodDemo {
	
	
	public static void main(String[] args) throws Exception {
//		demo1();
//		demo2();
//		demo3();
		demo4();
	}
	
	
	public static void demo4() throws Exception {
		
//		ZoneId zoneId = ZoneId.of("Europe/Moscow");
		
		LocalDateTime ldt = LocalDateTime.now();

		ZoneId zid = ZoneId.of("Europe/Paris");
		ZonedDateTime zdt = ZonedDateTime.of(ldt, zid);
		
		System.out.println(zdt);
		
		Date date = new Date();
		
		ZonedDateTime dt = ZonedDateTime.ofInstant(date.toInstant(), zid);
		System.out.println(dt);
		
		
		ZonedDateTime instant = ZonedDateTime.now();
		System.out.println(instant);

		ZonedDateTime instantInUTC = instant.withZoneSameInstant(ZoneId.of("UTC"));
		System.out.println(instantInUTC);		
		
		OffsetDateTime odt = OffsetDateTime.now();
		System.out.println(odt);

		OffsetDateTime odtInUTC = odt.withOffsetSameInstant(ZoneOffset.of("+00:00"));
		System.out.println(instantInUTC);		
		
		/*
		Set<String> allZoneIds = ZoneId.getAvailableZoneIds();
		for(String id:allZoneIds) {
			System.out.println(id);
		}
		*/
		
	}

	
	
	public static void demo3() throws Exception {
		
		Duration d = Duration.ofDays(5).plusMinutes(44).minusSeconds(20);
		System.out.println(d);
		
		d = Duration.ofSeconds(55);
		System.out.println(d);
		
		
//		Period p = Period.ofMonths(2).withDays(11);
//		d = Duration.from(p);
//		System.out.println(d);
		
		LocalDateTime d1 = LocalDateTime.now();
		LocalDateTime d2 = d1.plusMonths(5); 

		
		d = Duration.between(d1, d2); 
		System.out.println(d);
		
		d = Duration.parse("PT28H43M40S"); 
		System.out.println(d);
		System.out.println(d.toDays());
		
		
		
	}
	
	
	public static void demo2() throws Exception {
		LocalDate d1 = LocalDate.now();
		LocalDate d2 = ChronoUnit.WEEKS.addTo(d1, 2);
		System.out.println(d2);
		
		boolean supportedBy = ChronoUnit.HOURS.isSupportedBy(d1);
		System.out.println(supportedBy);
		
	}
	
	
	public static void demo1() throws Exception {
		
		LocalDate d1 = LocalDate.now();
		LocalDate d2 = d1.plusMonths(5); 
		
//		Period p = Period.of(3, 2, 1);	// 3 years, 2 months, 1 day
		Period p = Period.ofMonths(2).plusDays(11).plus(Period.ofYears(5));
		
		long l = p.get(ChronoUnit.DAYS);
		
		String unitsStr = p.getUnits().stream().map(u->u.toString()).collect(Collectors.joining(","));
		System.out.println(p);
		System.out.println(unitsStr);
		
		Period p2 = Period.between(d1, d2); 
		System.out.println(p2);
		
		unitsStr = p.getUnits().stream().map(u->u.toString()).collect(Collectors.joining(","));
		System.out.println(unitsStr);

		
		Period p3 = Period.ofMonths(2);
		long days = p3.get(ChronoUnit.DAYS);
		System.out.println(days);
		
		
		Period p4 = Period.ofMonths(2).withYears(1).withDays(7);
		System.out.println(p4);
	
		Period p5 = Period.parse("P1Y2M3D");
		System.out.println(p5);
		
	}

}
