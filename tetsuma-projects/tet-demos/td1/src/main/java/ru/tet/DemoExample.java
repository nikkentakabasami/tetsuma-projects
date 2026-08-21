package ru.tet;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.stream.IntStream;

import ru.tet.aux.AuxTest;
import ru.tet.aux.swing.DemoBase;

//Пример использования демок
public class DemoExample extends DemoBase {

	double d1, d2;
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);

	//Вспомогательный класс для теста 1
	@AuxTest(1)
	class testClass {
	}

	@AuxTest(value = 2)
	void auxMethod() {
		//aux method
	}

	@Override
	protected void doInit() throws Exception {
		//код для инициализации всей демки. Будет показываться для всех тестов.
		d1 = 100;

	}

	//доп. обработка значений в поле r перед их преобразованием в json.
	@Override
	public Object fixResultValue(Object value) {
		if (value instanceof Date d) {
			return dateFormat.format(d);
		}

		return super.fixResultValue(value);
	}

	public void test1() throws Exception {
		/*
		
		 */

		logExpr(() -> {
			Random r = new Random();
			double d = r.nextDouble(444);

			return d;
		}, () -> {

			LocalDateTime ldt = LocalDateTime.now();

			ZoneId zid = ZoneId.of("Europe/Paris");
			ZonedDateTime zdt = ZonedDateTime.of(ldt, zid);

			return zdt.toString();

		});

		logEval(

				d1 = 12.6e6,
				//(d1=1, d2=2),

				//кубический корень
				Math.cbrt(254),
				//деление с округлением вверх (добавлен в Java 20)
				Math.ceilDiv(22, 3));

	}

	public void test2() throws Exception {
		r.s1 = IntStream.of(55).toArray();
		r.s2 = IntStream.of(5, 7, 11, 13).toArray();

		r.s3 = new Date();

	}

	public void test3() throws Exception {
		/*
		
		 */
	}

	public void test4() throws Exception {
		/*
		
		 */
	}

	public static void main(String[] args) {
		DemoBase.run(DemoExample.class);
	}

}
