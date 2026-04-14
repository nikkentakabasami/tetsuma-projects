package ru.tet.jetty;

import java.nio.file.Path;

import javax.naming.NamingException;

import org.eclipse.jetty.ee10.jsp.JettyJspServlet;

import ru.tet.beans.UserDTO;
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

		
		
		
		options.addAdditionalStatic(Path.of("../td50_web_jslibs/src/main/resources/jslibs/"));
		options.addAdditionalStatic("/accord-lib/*",Path.of("/home/tetsuma/work/workspaces/_myprojects/accord/accord/src/main/webapp/accord/"));
		
		
		options.addAdditionalStatic(Path.of("../td50_web_jslibs/src/main/resources/jslibs/"));
		options.addAdditionalStatic("/accord-lib/*",Path.of("/home/tetsuma/work/workspaces/_myprojects/accord/accord/src/main/webapp/accord/"));
		
		
		//подключаем сервлеты
//		options.getExtraClasspathes().add(Path.of("../web-jslibs/target/classes"));
		
		TetJettyServer main = new TetJettyServer();
		
		main.init(options);
		
		bindWebappEx1Jndi(main);
		
		main.start();
		main.waitForInterrupt();
		
		
				
		

	}
	
	public static void bindWebappEx1Jndi(TetJettyServer main) throws NamingException {
		
		UserDTO user = new UserDTO("nemawashi", "123");
		user.setSecondPassword("654");
		user.setEmail("nema@mail.ru");
		user.setEnabled(true);
        new org.eclipse.jetty.plus.jndi.Resource(main.getServer(), "ttt/testUser1", user);
        new org.eclipse.jetty.plus.jndi.EnvEntry(main.getServer(), "testNumberValue1", Integer.valueOf(4000), false);
        new org.eclipse.jetty.plus.jndi.EnvEntry(main.getServer(), "testStringValue1", "Sabaki kirenai", true);
	}
	
	
}
