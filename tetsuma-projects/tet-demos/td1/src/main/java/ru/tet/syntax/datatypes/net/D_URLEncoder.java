package ru.tet.syntax.datatypes.net;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import ru.tet.aux.swing.DemoBase;

public class D_URLEncoder extends DemoBase {

	public void test1() throws Exception {
		/*
		 */
		
		
		
		String s = URLEncoder.encode("Что за день!", StandardCharsets.UTF_8).replaceAll("\\+", "%20");
		log2(s);
		s = URLDecoder.decode(s, StandardCharsets.UTF_8);
		log2(s);

		log2Splitter();
		
		s = URLEncoder.encode("a and b?", StandardCharsets.UTF_8);
		log2(s);
		s = URLDecoder.decode(s, StandardCharsets.UTF_8);
		log2(s);
		
		

	}

	public void test2() throws Exception {
		/*
		
		 */


	}

	public void test3() throws Exception {
		/*
		
		 */
	}

	public void test4() throws Exception {
		/*
		
		 */
	}

	public static void main(String[] args) {
		DemoBase.run(D_URLEncoder.class);
	}

}
