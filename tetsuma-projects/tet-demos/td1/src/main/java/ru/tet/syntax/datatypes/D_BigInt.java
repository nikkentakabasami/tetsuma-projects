package ru.tet.syntax.datatypes;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import ru.tet.aux.swing.DemoBase;

//Шаблон для создания новых демо
public class D_BigInt extends DemoBase {

	public void test1() throws Exception {
		/*
		BigInteger
		для работы с числами произвольной точности
		Немутабельный
		
		Способы объявления
		 */

		BigInteger fromString = new BigInteger("123456789012345678901234567890");
		BigInteger fromLong = BigInteger.valueOf(5000000L);

		BigInteger zero = BigInteger.ZERO;
		BigInteger one = BigInteger.ONE;
		BigInteger ten = BigInteger.TEN;

	}

	public void test2() throws Exception {
		/*
		BigInteger
		
		Основные операции
		 */

		BigInteger b1, b2;

		logEval1(
				b1 = BigInteger.valueOf(5000000L),
				b2 = BigInteger.valueOf(70400L),
				//наибольший общий делитель
				b1.gcd(b2),
				b1.add(b2),
				b1.subtract(b2),
				b1.divide(b2),
				b1.multiply(b2),
				b1.pow(3)

		);

	}

	public void test3() throws Exception {
		/*
		BigDecimal
		для работы с дробными числами с высокой точностью.
		Немутабельный
		 */

		BigDecimal b1, b2, b3, b4, b5, num1, num2;

		logEval1(
				//Ошибочный способ создания 
				b1 = new BigDecimal(0.1),
				b1.toString(),

				//Правильный способ
				b2 = new BigDecimal("0.1"),
				b3 = BigDecimal.valueOf(0.1),

				//основные операции
				num1 = new BigDecimal("10.50"),
				num2 = new BigDecimal("2.30"),
				num1.add(num2),
				num1.subtract(num2),
				num1.multiply(num2),
				num1.divide(num2, 2, RoundingMode.HALF_UP)
		);
	}

	public void test4() throws Exception {
		/*
		scale - сколько чисел после запятой
		 */

		BigDecimal b1, b2;
		
		logEval1(
				b1 = new BigDecimal("10.6789"),
				b1.scale(),
				b2 = b1.setScale(2, RoundingMode.HALF_UP)
		);


	}

	public static void main(String[] args) {
		DemoBase.run(D_BigInt.class);
	}

}
