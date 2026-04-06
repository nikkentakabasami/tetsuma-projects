package ru.tet.java.lang;

import java.text.DecimalFormat;

public class MathDemo {

	public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#0.##");
	
	
	public static void main(String[] args) {
		
		mathDemo1();
//		mathDemo2();
		
	}
	
	public static void mathDemo2() {
		System.out.println(Math.getExponent(33));
		
	}

	
	public static void mathDemo1() {
		System.out.format("cbrt(254)=%f%n",Math.cbrt(254));

		System.out.format("ceilDiv(22, 3)=%d%n",Math.ceilDiv(22, 3));
		System.out.println("signum(-5)="+Math.signum(-5));
		System.out.println("signum(23)="+Math.signum(23));
		System.out.println("exp(2)="+Math.exp(2));
		System.out.println("getExponent(11)="+Math.getExponent(11));
		
		
		
		System.out.print("random: ");
		for (int i = 0; i < 20; i++) {
			System.out.print(DECIMAL_FORMAT.format(Math.random())+" ");
		}
		
		System.out.println("/nE="+Math.E);
		
		System.out.println("log(20)="+Math.log(20));
		System.out.println("log10(100)="+Math.log10(100));
		
		
		
		
	}
	
	
	
	
}
