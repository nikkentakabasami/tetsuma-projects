package ru.tet.java.nio;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.HexFormat;
import java.util.SortedMap;

public class CharsetDemo {

	public static void main(String[] args) {
		Demo1();
	}

	public static void Demo2() {

		SortedMap<String, Charset> availableCharsets = Charset.availableCharsets();
		for(String csn:availableCharsets.keySet()) {
			System.out.println(csn);
		}
		
		
		
	}
	
	
	public static void Demo1() {

//	    Charset charset = Charset.forName("Cp1251");
	    Charset charset = Charset.forName("windows-1251");

	    
	    
	    System.out.println("name="+charset.name());
	    System.out.println("displayName="+charset.displayName());
	    System.out.println("aliases="+charset.aliases());

	    ByteBuffer bb = charset.encode("Что бы сделал Брайн Бойтано?");
	    
	    HexFormat hf = HexFormat.ofDelimiter(" ");
	    String s = hf.formatHex(bb.array());
	    System.out.println(s);

	    System.out.println(charset.decode(bb));		
		
		
	}
	
}
