package ru.tet.java.lang;

import java.text.DecimalFormat;
import java.util.Random;

import ru.tet.aux.swing.DemoBase;

public class MathDemo extends DemoBase {

	public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#0.##");

	@Override
	public void test1() throws Exception {

		logEval(

				//кубический корень
				Math.cbrt(254),

				//деление с округлением вверх (добавлен в Java 20)
				Math.ceilDiv(22, 3),

				//возвращает знак числа в виде числа: +1, -1, 0
				Math.signum(-5),
				Math.signum(23),
				Math.signum(0),

				//Экспонента: e^x
				Math.exp(2),

				//извлекает экспоненту числа
				//То есть находится наибольшее 2^E, которое меньше или равно d, и возвращается E
				Math.getExponent(11),
				Math.getExponent(33),

				//случайное число: 0<=x<1
				Math.random(),
				Math.random(),
				Math.random(),

				Math.E,

				//Округление до заданной точности
				(Math.rint(7.9536 * 100)) / 100,

				//натуральный логарифм
				Math.log(10),
				Math.log(20),

				//десятичный логарифм
				Math.log10(5),
				Math.log10(100)

		);

	}

	@Override
	public void test2() throws Exception {

		Random rand = new Random();
		logEval(
				rand.nextInt(124),
				rand.nextDouble(90));

	}

	public static void main(String[] args) {
		DemoBase.run(MathDemo.class, 1);
	}

}
