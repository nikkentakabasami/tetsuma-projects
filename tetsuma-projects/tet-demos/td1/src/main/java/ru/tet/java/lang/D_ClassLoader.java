package ru.tet.java.lang;

import java.net.URL;

import ru.tet.aux.swing.DemoBase;
import ru.tet.utils.TetIOUtils;



public class D_ClassLoader extends DemoBase {

	
	@Override
	public void test1() throws Exception {
		//getContextClassLoader
	    URL url = Thread.currentThread().getContextClassLoader()
	    		.getResource("ru/tet/mytest2.txt");
	    log2("url:",url.toString());
	    
	    log2Splitter("file content");
	    String content = TetIOUtils.streamToString(url.openStream());
      log2(content);
		
	}


	public static void main(String[] args) {
		DemoBase.run(D_ClassLoader.class,1);
	}

}

