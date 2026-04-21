package ru.tet.jetty.starter;

import java.io.File;
import java.nio.file.Path;

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
	
	
	protected Path projectsDir;
	
	protected TetJettyServer server;
	protected TetJettyServerOptions options;
	
//	boolean initiated;
	
	public JettyStarter() {
		
		projectsDir = findProjectsDir().toPath();
		logger.debug("projectsDir: "+projectsDir);
		
		
		options = new TetJettyServerOptions();
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
	
	
	/**
	 * Поиск корневой директории: tetsuma-projects
	 */
	private File findProjectsDir() {
		File dir = new File(System.getProperty("user.dir"));
		do {
			if (dir.getName().equals(PROJECTS_DIR_NAME)) {
				return dir;
			}
			dir = dir.getParentFile();
		} while (dir!=null);
		
		throw new RuntimeException("file not found: "+PROJECTS_DIR_NAME);
	}
	
	protected Path getAuxJsProjectPath() {
		Path path = projectsDir.resolve(JS_LIBS_DIR_NAME).resolve("auxjs-libs");
		checkDir(path);
		return path;
	}
	protected Path getAuxJsResourcesPath() {
		Path path = getAuxJsProjectPath().resolve(RESOURCES_SUBDIR_NAME).resolve("auxjs-libs");
		checkDir(path);
		return path;
	}
	
	protected Path getAccordPath() {
		Path path = projectsDir.resolve(JS_LIBS_DIR_NAME).resolve("accord-lib").resolve(RESOURCES_SUBDIR_NAME).resolve("accord-lib").resolve("accord");
		checkDir(path);
		return path;
	}
	
	protected Path getTetSlickGridPath() {
		Path path = projectsDir.resolve(JS_LIBS_DIR_NAME).resolve("tet-slick-grid-lib").resolve(RESOURCES_SUBDIR_NAME).resolve("tetSlickGrid");
		checkDir(path);
		return path;
	}
	
	
	
	
	protected void checkDir(Path path) {
		if (!path.toFile().isDirectory()) {
			throw new RuntimeException("dir not found: "+PROJECTS_DIR_NAME);
		}
	}
	
	/*
	public static void main(String[] args) throws IOException {
		
		JettyEmbeddedStarter s = new JettyEmbeddedStarter();
		System.out.println(s.getAuxJsDirPath());
		System.out.println(s.getAccordPath());
		System.out.println(s.getTetSlickGridPath());
		
	}
	*/
	
	
}
