package ru.tet.java.util;

import java.util.HexFormat;

public class HexFormatDemo {

	public static void main(String[] args) {
		hfDemo1();
		hfDemo2();
		hfDemo3();
		hfDemo4();
	}

	public static void hfDemo1() {

		HexFormat hex = HexFormat.of();
		byte b = 127;
		String byteStr = hex.toHexDigits(b);
		System.out.println(byteStr);

		byte byteVal = (byte) hex.fromHexDigits(byteStr);
	}

	public static void hfDemo2() {

		HexFormat commaFormat = HexFormat.ofDelimiter(", ").withPrefix("#");
		byte[] bytes = { 0, 1, 2, 3, 124, 125, 126, 127, -55 };
		String str = commaFormat.formatHex(bytes);
		System.out.println(str);

		byte[] parsed = commaFormat.parseHex(str);
		
		
		HexFormat hexFormat = HexFormat.of().withPrefix("[").withSuffix("]").withDelimiter(", ");
		str = hexFormat.formatHex(bytes);
		System.out.println(str);
		
	}

	public static void hfDemo3() {
		
		for (int i = -128; i <= 127; i++) {
			int mi = i & 0xff;
			System.out.println(i+" = "+Integer.toHexString(mi)+" = "+Integer.toBinaryString(mi));
		}
		
		
	}
	
	public static void hfDemo4() {
		byte[] bytes = { 0, 1, 2, 3, 124, 125, 126, 127, -55 };
		String s = byteArrayToHex(bytes);
		System.out.println(s);
		
	}
	
	public static String byteArrayToHex(byte[] a) {
	    StringBuilder sb = new StringBuilder(a.length * 2);
	    for (byte b: a) {
	       sb.append(String.format("%02x", b));
	    }
	    return sb.toString();
	}
	
	
	
	
}
