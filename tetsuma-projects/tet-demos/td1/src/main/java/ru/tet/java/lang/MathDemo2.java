package ru.tet.java.lang;

import java.text.DecimalFormat;

import ru.tet.aux.swing.DemoBase;

public class MathDemo2 extends DemoBase {

	public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#0.##");

	@Override
	public void test1() throws Exception {

		logEval2("""

				//кубический корень
				Math.cbrt(254)

				//деление с округлением вверх (добавлен в Java 20)
				Math.ceilDiv(22, 3)

				//возвращает знак числа в виде числа: +1, -1, 0
				Math.signum(-5)
				Math.signum(23)
				Math.signum(0)

				//Экспонента: e^x
				Math.exp(2)

				//извлекает экспоненту числа
				   //То есть находится наибольшее 2^E, которое меньше или равно d, и возвращается E
				Math.getExponent(11)
				Math.getExponent(33)

				//случайное число: 0<=x<1
				Math.random()
				Math.random()
				Math.random()

				Math.E

				//натуральный логарифм
				Math.log(10)
				Math.log(20)

				//десятичный логарифм
				Math.log10(5)
				Math.log10(100)

				""");

	}

	@Override
	protected void doInitControlPanel() throws Exception {
		addTest1Button(null);
	}

	public static void main(String[] args) {
		DemoBase.run(MathDemo2.class, 1);
	}

}
