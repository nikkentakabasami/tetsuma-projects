package ru.tet.jetty;

import java.nio.file.Path;

import ru.tet.jetty.starter.TetJettyServer;
import ru.tet.jetty.starter.TetJettyServerOptions;


/**
 * Запуск через jetty embedded (С использованием класса TetJettyServer)
 * 
 */
public class JettyEmbeddedStarter1 {

	
	public static void main(String[] args) throws Exception {
		
		
		TetJettyServerOptions options = new TetJettyServerOptions();
		options.setPort(8080);
		options.setContextPath("/webapp1");

		options.addAdditionalStatic(Path.of("../accord-lib/src/main/resources/accord/"));
		options.addAdditionalStatic(Path.of("../auxjs-libs/src/main/resources/jquery31"));
		options.addAdditionalStatic(Path.of("../auxjs-libs/src/main/resources/misc-libs"));
		
		options.addAdditionalStatic(Path.of("../tet-slick-grid-lib/src/main/resources/tetSlickGrid/"));
		
		
		TetJettyServer main = new TetJettyServer();
		
		main.init(options);
		
		main.start();
		main.waitForInterrupt();
		
		
				
		

	}
	
	
	
}
