package ru.tet.java.lang;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import ru.tet.aux.swing.DemoBase;



public class ClassLoaderDemo extends DemoBase {

	
	@Override
	public void test1() throws Exception {
		//getContextClassLoader
	    URL url = Thread.currentThread().getContextClassLoader()
	    		.getResource("ru/tet/mytest2.txt");
	    log2("url:",url.toString());
	    
	    log2Splitter("file content");
	    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
	    String inputLine;
	    while ((inputLine = in.readLine()) != null) {
	      log2(inputLine);
	    }
	    in.close();
		
	}

	@Override
	protected void doInitControlPanel() throws Exception {
		addTest1Button(null);
	}

	public static void main(String[] args) {
		DemoBase.run(ClassLoaderDemo.class,1);
	}

}

