package ru.tet.syntax.datatypes.string;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import ru.tet.aux.swing.DemoBase;

public class D_ResourceBundle extends DemoBase {


	
	
	
	public void test1() throws Exception {
		/*
		 */
		

	}

	public void test2() throws Exception {
		/*
		
		 */
		
		
		Locale locale = Locale.of("pl", "PL");
		ResourceBundle rb1 = ResourceBundle.getBundle("ru.tet.aux.ExampleResource", locale);

		logEval1(
				rb1.getString("currency"),
				Arrays.toString(rb1.getStringArray("cities")),
				rb1.getObject("toUsdRate")
		);

		
		locale = Locale.of("ru", "RU");
		ResourceBundle rb2 = ResourceBundle.getBundle("ru.tet.aux.ExampleResource", locale);

		logEval2(
				rb2.getString("currency"),
				rb2.getString("cities"),
				rb2.getString("toUsdRate")
		);

		ResourceBundle rb3 = ResourceBundle.getBundle("ru.tet.aux.ExampleResource", Locale.ENGLISH);

		logEval2(
				rb3.getString("currency"),
				rb3.getString("cities"),
				rb3.getString("toUsdRate")
		);

		//итерация по значениям
		Enumeration<String> keys = rb3.getKeys();
		while (keys.hasMoreElements()) {
		    String key = keys.nextElement();
		    String value = rb3.getString(key);
		    log2(key,value);
		}
		
		
		
		
	}

	public void test3() throws Exception {
		/*
		
		 */
		
		ResourceBundle rb1 = ResourceBundle.getBundle("ChoiceBundle", Locale.of("en","US"));
		logEval1(
				rb1.getString("noFiles"),
				rb1.getString("oneFile"),
				rb1.getString("multipleFiles")
		);

		rb1 = ResourceBundle.getBundle("ChoiceBundle");
		logEval2(
				rb1.getString("noFiles"),
				rb1.getString("oneFile"),
				rb1.getString("multipleFiles")
		);
		
		
	}

	public void test4() throws Exception {
		/*
		
		 */

//		URL url1 = getClass().getClassLoader().getResource("ru/tet/aux/ExampleResource_ru_RU.properties");
//		InputStream is1 = url1.openStream();
		
		
		InputStream is1 = getClass().getClassLoader().getResourceAsStream("ru/tet/aux/ExampleResource_ru_RU.properties");
//bad		InputStream is1 = getClass().getClassLoader().getResourceAsStream("ru.tet.aux.ExampleResource_en.properties");
		
		Properties properties = new Properties();
	  properties.load(is1);
	  is1.close();

		logEval2(
				properties.getProperty("currency"),
				properties.getProperty("cities")
		);	  
	  
		Path p1 = Path.of("target/testProps.xml");
		FileOutputStream os1 = new FileOutputStream(p1.toFile());
		properties.storeToXML(os1,"my comment!");
		
		String xmlContent = Files.readString(p1);
		log2(xmlContent);
		
		properties.list(System.out);
		
		
	}

	@Override
	protected void doInit() throws Exception {
		options().hlComments = false;
	}
	
	public static void main(String[] args) {
		DemoBase.run(D_ResourceBundle.class);
	}

}
