package ru.tet.jetty.starter.uberjar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Основа для классов, запускающих веб-проект через jetty embedded.
 * 
 */
public abstract class JettyStarter {

	public static final String PROJECTS_DIR_NAME = "tetsuma-projects";
	public static final String JS_LIBS_DIR_NAME = "tet-js-libs";
	
	public static final String RESOURCES_SUBDIR_NAME = "src/main/resources";
	
	
	static Logger logger = LogManager.getLogger();
	
	
	protected TetJettyServer server;
	protected TetJettyOptions options;
	
//	boolean initiated;
	
	public JettyStarter() {
		
		options = new TetJettyOptions();
		server = new TetJettyServer();
		
		
	}
	
	//Задание опций
	public abstract void init() throws Exception;
	
	public void start() throws Exception {
		
		init();
		
		server.init(options);
		server.start();
		server.waitForInterrupt();
	}
	
	

	
}
