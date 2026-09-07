package ru.tet.java.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HexFormat;
import java.util.SortedMap;
import java.util.stream.Collectors;

import ru.tet.aux.swing.DemoBase;

public class D_Charset extends DemoBase {

	public void test1() throws Exception {
		/*
		 */
    
		logEval1(
				Charset.defaultCharset(),
				StandardCharsets.UTF_8,
				Charset.forName("windows-1251")
		);

		
		logExpr1(() -> {
			SortedMap<String, Charset> availableCharsets = Charset.availableCharsets();
			return availableCharsets.keySet().stream().collect(Collectors.joining("\n"));
		});

	}

	public void test2() throws Exception {
		/*
		
		 */
		
    Charset charset = StandardCharsets.UTF_8;
    
    log2("name="+charset.name());
    log2("displayName="+charset.displayName());
    log2("aliases="+charset.aliases());

    log2Splitter();
    
    //Преобразование строки в ByteBuffer
    ByteBuffer bb = charset.encode("Что бы сделал Брайн Бойтано?");
    HexFormat hf = HexFormat.ofDelimiter(" ");
    String s = hf.formatHex(bb.array());
    log2(s);
    
    //Преобразование ByteBuffer в строку
    CharBuffer cb1 = charset.decode(bb);
    log2(cb1.toString());
    

	}

	public void test3() throws Exception {
		/*
		
		 */
    Charset charset = StandardCharsets.UTF_8;
		
		CharsetEncoder encoder = charset.newEncoder();
		CharBuffer cb1 = CharBuffer.wrap("Что бы сделал Брайн Бойтано?");
		ByteBuffer bb1 = encoder.encode(cb1);
		
		log2(Arrays.toString(bb1.array()));
		
		CharsetDecoder decoder = charset.newDecoder();
		
		CharBuffer cb2 = decoder.decode(bb1);
    log2(cb2.toString());
		
	}

	public void test4() throws Exception {
		/*
		
		 */
	}

	public static void main(String[] args) {
		DemoBase.run(D_Charset.class);
	}

}
