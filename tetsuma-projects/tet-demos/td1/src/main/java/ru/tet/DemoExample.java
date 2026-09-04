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
	public Object fixResultValue(Object value) throws Exception {
		if (value instanceof Date d) {
			return dateFormat.format(d);
		}

		return super.fixResultValue(value);
	}

	public void test1() throws Exception {
		/*
		Работа с логами
		 */

		log2("aaa");
		log2Green("my g text");
		log2("bbb");
		log2Blue("my blue text");
		log2("ccc");
		log2Bold("my bold text");
		log2("ddd");		
		
		log2Splitter("test logExpr");

		logExpr1(() -> {
			Random r = new Random();
			double d = r.nextDouble(444);
			return d;
		}, () -> {

			LocalDateTime ldt = LocalDateTime.now();

			ZoneId zid = ZoneId.of("Europe/Paris");
			ZonedDateTime zdt = ZonedDateTime.of(ldt, zid);

			return zdt.toString();

		});

		log2Splitter("test logEval1");

		logEval1(

				d1 = 12.6e6,
				//(d1=1, d2=2),

				//кубический корень
				Math.cbrt(254),
				//деление с округлением вверх (добавлен в Java 20)
				Math.ceilDiv(22, 3));

		log2Splitter("test logEval2");

		logEval2(
				123 * 22,
				expr(() -> {
					Random randomNumbers = new Random();
					int r1 = randomNumbers.nextInt(6);
					return r1;
				}));

		logEval3(
				1,
				expr(() -> {
					int r1 = 33;
					return r1;
				}), expr(() -> {
					int r1 = 44;
					return r1;
				}));

	}

	public void test2() throws Exception {
		r.s1 = IntStream.of(55).toArray();
		r.s2 = IntStream.of(5, 7, 11, 13).toArray();

		r.s3 = new Date();

	}

	public void test3() throws Exception {
		/*
		
		 */

		int i = 11;

		for (int j = 1; j < 4; j++) {

			log2("---", j);

			logEval1(
					j,
					i * j);

		}

	}

	public void test4() throws Exception {
		/*
		
		 */

		logEval1(
				123 * 22,
				expr(() -> {
					Random randomNumbers = new Random();
					int r1 = randomNumbers.nextInt(6);
					return r1;
				}));

	}

	public static void main(String[] args) {
		DemoBase.run(DemoExample.class);
	}

}
