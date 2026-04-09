package ru.tet.java.lang;

import java.text.DecimalFormat;
import java.util.stream.Stream;

import ru.tet.aux.swing.DemoBase;


public class MathDemo extends DemoBase {

	public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#0.##");
	
	@Override
	public void test1() throws Exception {
		
		//кубический корень
		log2("cbrt(254)=",Math.cbrt(254));

		//деление с округлением вверх (добавлен в Java 20)
		log2("ceilDiv(22, 3)=",Math.ceilDiv(22, 3));
		
		//возвращает знак числа в виде числа: +1, -1, 0
		log2("signum(-5)="+Math.signum(-5));
		log2("signum(23)="+Math.signum(23));
		log2("signum(0)="+Math.signum(0));
		
		//Экспонента: e^x
		log2("exp(2)="+Math.exp(2));
		
		//извлекает экспоненту числа
	    //То есть находится наибольшее 2^E, которое меньше или равно d, и возвращается E
		log2("getExponent(11)="+Math.getExponent(11));
		log2("getExponent(33)="+Math.getExponent(33));

		//случайное число: 0<=x<1
		Object[] randArray = Stream.generate(() -> Math.random()).limit(10).toArray();
		log2("random(): ",randArray);
		log2(randArray);
		
		log2("E="+Math.E);
		
		//натуральный логарифм
		log2("log(10)="+Math.log(10));
		log2("log(20)="+Math.log(20));
		
		//десятичный логарифм
		log2("log10(5)="+Math.log10(5));
		log2("log10(100)="+Math.log10(100));
		
		
				
	}

	@Override
	protected void doInitControlPanel() throws Exception {
		addTest1Button(null);
	}

	public static void main(String[] args) {
		DemoBase.run(MathDemo.class,1);
	}

}
