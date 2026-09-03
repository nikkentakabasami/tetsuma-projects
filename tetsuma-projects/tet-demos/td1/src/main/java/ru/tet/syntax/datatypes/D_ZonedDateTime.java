package ru.tet.syntax.datatypes;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.Set;

import ru.tet.aux.swing.DemoBase;

//Шаблон для создания новых демо
public class D_ZonedDateTime extends DemoBase {

	public Object fixResultValue(Object value) throws Exception {

		if (value instanceof Temporal d) {
			return d.toString();
		}
		
		if (value instanceof ZoneId d) {
			return d.toString();
		}
		

		return super.fixResultValue(value);
	}

	public void test1() throws Exception {
		/*
		java.time.ZoneId
		Представляет временную зону
		
		Set<String>	ZoneId.getAvailableZoneIds()
		
		ZoneId.of(String zoneId)
		ZoneId.systemDefault()
		Получение зоны
		
		 */

		ZoneId zid1 = ZoneId.of("Europe/Moscow");
		ZoneId zid2 = ZoneId.systemDefault();
		ZoneId zid3 = ZoneId.of("UTC");
		
		log2(zid1);
		log2(zid2);
		
		r.set(zid1,zid2,zid3);

		//список доступных зон
		Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
		availableZoneIds.forEach(zone -> log2(zone));

	}

	public void test2() throws Exception {
		/*
		java.time.ZonedDateTime
		Время во временной зоне
		Учитывает правила перехода на летнее/зимнее время и другие особенности, связанные с конкретной временной зоной
		
		
		Способы создания
		
		ZonedDateTime.of(LocalDateTime localDateTime, ZoneId zone)
		ZonedDateTime.ofInstant(Instant instant, ZoneId zone)
		ZonedDateTime.ofInstant(LocalDateTime localDateTime, ZoneOffset offset, ZoneId zone)
		
		 */

		//Способы создания
		LocalDateTime ldt1 = LocalDateTime.now();

		ZoneId zid = ZoneId.of("Europe/Paris");
		ZonedDateTime zdt1 = ZonedDateTime.of(ldt1, zid);

		Date date1 = new Date();
		ZonedDateTime zdt2 = ZonedDateTime.ofInstant(date1.toInstant(), zid);

		ZonedDateTime zdt3 = ZonedDateTime.of(2021, 02, 15, 0, 0, 0, 0, zid);

		ZonedDateTime zdt4 = ZonedDateTime.parse("2007-12-03T10:15:30+01:00[Europe/Paris]");

		ZonedDateTime zdt5 = ZonedDateTime.now();

		//получить то же время, но в другой зоне
		ZonedDateTime zdt6 = zdt5.withZoneSameInstant(ZoneId.of("UTC"));

		//зона задаётся через z
		DateTimeFormatter f1 = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss z");
		

		
		r.set(zdt1, zdt2, zdt3, zdt4, zdt5, zdt6, f1.format(zdt5));

	}

	public void test3() throws Exception {
		/*
		java.time.ZoneOffset
		Разница с гринвичем (в секундах)
		
		java.time.OffsetDateTime
		LocalDateTime+смещение по времени.
		Более простой и легковесный вариант ZonedDateTime
		Не содержит зону и не учитывает правила временных зон.
		
		
		 */

		ZoneOffset offset1 = ZoneOffset.of("+02:00");
		ZoneOffset offset2 = ZoneOffset.of("-5");
		ZoneOffset offset3 = ZoneOffset.ofHours(-5);
		ZoneOffset offset4 = ZoneOffset.ofHoursMinutes(5, 30);
		ZoneOffset offset5 = ZoneOffset.ofTotalSeconds(19800);

		LocalDateTime ldt1 = LocalDateTime.now();

		OffsetDateTime odt1 = OffsetDateTime.now();
		OffsetDateTime odt2 = OffsetDateTime.of(ldt1, offset1);
		
		//задать другой оффсет (не меняя время)
		OffsetDateTime odt3 = ldt1.atOffset(offset3);
		
		//сменить оффсет (соответственно сдвинув время)
		OffsetDateTime odt4 = odt1.withOffsetSameInstant(offset3);

		//Преобразование в ZonedDateTime
		ZonedDateTime zdt1 = odt3.atZoneSameInstant(ZoneId.systemDefault());
		
		
		//offset задаётся через XXX
		DateTimeFormatter f1 = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss XXX");
		
		r.s1 = f1.format(odt1);
		r.s2 = f1.format(odt2);
		r.s3 = f1.format(odt3);
		r.s4 = f1.format(odt4);

		//		r.set(odt1);

	}

	public void test4() throws Exception {
		/*
		
		 */
	}

	@Override
	protected void doInitControlPanel() throws Exception {
		addTest1Button(null);
		addTest2Button(null);
		addTest3Button(null);
		addTest4Button(null);
	}

	public static void main(String[] args) {
		DemoBase.run(D_ZonedDateTime.class);
	}

}
