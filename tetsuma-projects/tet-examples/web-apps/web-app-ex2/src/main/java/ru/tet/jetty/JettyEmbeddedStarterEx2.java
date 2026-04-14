package ru.tet.jetty;

import java.nio.file.Path;

import javax.naming.NamingException;

import ru.tet.jetty.starter.JettyStarter;
import ru.tet.jetty.starter.TetJettyServer;
import ru.tet.jetty.starter.TetJettyServerOptions;


/**
 * Запуск через jetty embedded (С использованием класса TetJettyServer)
 * 
 */
public class JettyEmbeddedStarterEx2 extends JettyStarter {

	
	public static void main(String[] args) throws Exception {
		
		TetJettyServerOptions options = new TetJettyServerOptions();
		options.setPort(8080);
		options.setContextPath("/webapp2");

		
		//подключаем дополнительные статические ресурсы
		options.addAdditionalStatic("/static1/*", Path.of("../web-static-roots/src/main/resources/static-root1/"));

		//по умолчанию будет подключён по пути: /static-root2/*
		options.addAdditionalStatic(Path.of("../web-static-roots/src/main/resources/static-root2/"));
		
		TetJettyServer main = new TetJettyServer();
		
		main.init(options);
		
		main.start();
		main.waitForInterrupt();
		

	}

	
	
}
