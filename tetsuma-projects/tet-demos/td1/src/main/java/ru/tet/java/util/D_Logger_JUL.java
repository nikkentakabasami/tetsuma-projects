package ru.tet.java.util;

import java.text.MessageFormat;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import ru.tet.aux.swing.DemoBase;

public class D_Logger_JUL extends DemoBase {

	public void test1() throws Exception {
		/*
		JUL — java.util.logging (Java Logging API )
		
		java.util.logging.Logger
		Осуществляет логгирование по умолчанию
		
		Конфигурация по умолчанию
		По умолчанию logger.level==INFO
		
		 */
		log2("demo1");

		Logger log1 = Logger.getLogger(D_Logger_JUL.class.getName());

		//не выведется
		log1.fine("my fine");

		//выведется
		log1.info("my info");
		log1.warning("my warning");
		log1.log(Level.SEVERE, "my severe");
		log1.log(Level.SEVERE, "my severe2 {0}", 55);

		//меняем уровень подробности
		log1.setLevel(Level.WARNING);

		//не выведется
		log1.info("my info2");

		//выведется
		log1.warning("my warning2");
		log1.severe("my severe2");

	}

	public void test2() throws Exception {
		/*
		Конфигурирование через properties-файл.
		 */
		log2("demo2");

		String logResource = "/log/logging3.properties";

		LogManager.getLogManager().readConfiguration(
				D_Logger_JUL.class.getResourceAsStream(logResource));

		Logger log1 = Logger.getLogger(D_Logger_JUL.class.getName());

		log1.fine("my fine");
		log1.info("my info");
		log1.warning("my warning");
		log1.log(Level.SEVERE, "my severe");

	}

	public void test3() throws Exception {
		/*
		
		 */
		log2("demo3");

		Logger log1 = Logger.getLogger(D_Logger_JUL.class.getName());
		Handler[] handlers = log1.getHandlers();
		System.out.println(handlers);

		Handler fh1 = new FileHandler("target/fileHandler1.log");
		fh1.setFormatter(new SimpleFormatter());
		log1.addHandler(fh1);

		//выведет лог в xml-формате
		Handler fh2 = new FileHandler("target/fileHandler2.log");
		log1.addHandler(fh2);

		log1.setLevel(Level.WARNING);

		//не выведется
		log1.info("my info");

		log1.warning("my warning");
		log1.log(Level.SEVERE, "my severe");
	}

	public void test4() throws Exception {
		/*
		ANSI Codes to Color Logs
		
		Эти коды позволяют организовать цвета в логгере.
		многие терминалы их поддерживают.
		 */
		System.out.println("Here's some text");
		System.out.println("\u001B[31m" + "and now the text is red" + "\u001B[0m");

		System.out.println("\u001B[30m_test1_\u001B[0m");
		System.out.println("\u001B[31m_test2_\u001B[0m"); //red
		System.out.println("\u001B[32m_test3_\u001B[0m"); //green
		System.out.println("\u001B[33m_test4_\u001B[0m"); //yellow	
		System.out.println("\u001B[34m_test4_\u001B[0m"); //blue


		//вывод форматированных сообщений с параметрами

		System.out.printf("Hi - %s! Been %.1f days. How are you %s? %n", "Sasha", 7.55, "At work");
		
		MessageFormat mf = new MessageFormat("{0} | {0,number} | {0,number, #00.#} | {1,number, #,###.###}");
		System.out.println(mf.format(new Object[]{ Math.PI, 12345.12345}));
		

		
		
	}

	@Override
	protected void doInit() throws Exception {
		options().logSources = false;
	}

	public static void main(String[] args) {
		DemoBase.run(D_Logger_JUL.class);
	}

}
