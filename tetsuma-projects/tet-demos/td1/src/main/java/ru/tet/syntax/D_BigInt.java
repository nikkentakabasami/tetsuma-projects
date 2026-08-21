package ru.tet.syntax;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import ru.tet.aux.swing.DemoBase;

//Шаблон для создания новых демо
public class D_BigInt extends DemoBase {

	public void test1() throws Exception {
		/*
		BigInteger
		Способы объявления
		 */

		BigInteger fromString = new BigInteger("123456789012345678901234567890");
		BigInteger fromLong = BigInteger.valueOf(5000000L);

		BigInteger zero = BigInteger.ZERO;
		BigInteger one = BigInteger.ONE;
		BigInteger ten = BigInteger.TEN;

	}

	public void test2() throws Exception {

		BigInteger b1 = BigInteger.valueOf(5000000L);
		BigInteger b2 = BigInteger.valueOf(70400L);

		logEval(
				b1,
				b2,
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
		
		 */

		//Ошибочный способ создания 
		BigDecimal bad = new BigDecimal(0.1);

		//Правильный способ
		BigDecimal good1 = new BigDecimal("0.1");
		BigDecimal good2 = BigDecimal.valueOf(0.1);

		r.s1 = bad;
		r.s2 = good1;
		r.s3 = good2;

		BigDecimal num1 = new BigDecimal("10.50");
		BigDecimal num2 = new BigDecimal("2.30");

		r.s4 = num1.add(num2);
		r.s5 = num1.subtract(num2);
		r.s6 = num1.multiply(num2);

		r.s7 = num1.divide(num2, 2, RoundingMode.HALF_UP);

	}

	public void test4() throws Exception {
		/*
		scale - сколько чисел после запятой
		
		 */

		BigDecimal b1 = new BigDecimal("10.6789");
		r.s1 = b1.scale();

		//округление до второго знака после запятой
		r.s2 = b1.setScale(2, RoundingMode.HALF_UP);

	}


	public static void main(String[] args) {
		DemoBase.run(D_BigInt.class);
	}

}
