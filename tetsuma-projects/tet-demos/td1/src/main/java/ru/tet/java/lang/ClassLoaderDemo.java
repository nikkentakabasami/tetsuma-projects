package ru.tet.java.lang;

import java.io.InputStream;
import java.net.URL;

public class ClassLoaderDemo {

	public static void main(String[] args) {
		
	    URL url = Thread.currentThread().getContextClassLoader().getResource("templates/journal2/gen_controller.st");
//	    URL url = Thread.currentThread().getContextClassLoader().getResource("templates\\journal2\\gen_controller.st");
	    System.out.println(url.toString());
	    
		
		
		
	}
	
}
