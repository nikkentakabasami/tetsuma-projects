package ru.tet.java.util;

import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerDemo {

	public static void main(String[] args) throws Exception {
		demo1();
//		demo2();
	}

	public static void demo1() throws Exception {
		
		System.out.println("demo1");
		
		Logger LOGGER = Logger.getLogger(LoggerDemo.class.getName());
		Handler[] handlers = LOGGER.getHandlers();
		System.out.println(handlers);
		
//		Handler fh = new FileHandler("%t/wombat.log");
		Handler fh1 = new FileHandler("target/fileHandler1.log");
		fh1.setFormatter(new SimpleFormatter());
		Handler fh2 = new FileHandler("target/fileHandler2.log");
		LOGGER.addHandler(fh1);
		LOGGER.addHandler(fh2);
		
		
		LOGGER.setLevel(Level.WARNING);
		
		LOGGER.info("my info");
		LOGGER.warning("my warning");
		LOGGER.log(Level.SEVERE, "my severe");
		
	}

	
	public static void demo2() throws Exception {
		System.out.println("demo2");

//		String logResource = "/log/logging1.properties";
		String logResource = "/log/logging2.properties";
		
		LogManager.getLogManager().readConfiguration(
				LoggerDemo.class.getResourceAsStream(logResource));
		
		Logger LOGGER = Logger.getLogger(LoggerDemo.class.getName());
		
		LOGGER.info("my info");
		LOGGER.warning("my warning");
		LOGGER.log(Level.SEVERE, "my severe");
		
	}
	
	
}
