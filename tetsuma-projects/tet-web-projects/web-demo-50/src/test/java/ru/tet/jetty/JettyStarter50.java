package ru.tet.jetty;

import javax.naming.NamingException;

import ru.tet.beans.UserDTO;
import ru.tet.jetty.starter.JettyStarter;

/**
 * Запуск через jetty embedded (С использованием класса TetJettyServer)
 * 
 */
public class JettyStarter50 extends JettyStarter {

	@Override
	public void init() throws Exception {
		options.setPort(8080);
		options.setContextPath("/web-demo-50");
		
		options.addAdditionalStatic(getAccordPath());
		options.addAdditionalStatic(getAuxJsResourcesPath().resolve("jquery31"));
		options.addAdditionalStatic(getAuxJsResourcesPath().resolve("misc-libs"));
//		options.addAdditionalStatic(getAuxJsResourcesPath().resolve("bootstrap3"));
		
		bindWebappEx1Jndi();
		
	}

	public static void main(String[] args) throws Exception {
		JettyStarter50 starter = new JettyStarter50();
		starter.start();
	}

	public void bindWebappEx1Jndi() throws NamingException {

		UserDTO user = new UserDTO("nemawashi", "123");
		user.setSecondPassword("654");
		user.setEmail("nema@mail.ru");
		user.setEnabled(true);
		new org.eclipse.jetty.plus.jndi.Resource(server.getServer(), "ttt/testUser1", user);
		new org.eclipse.jetty.plus.jndi.EnvEntry(server.getServer(), "testNumberValue1", Integer.valueOf(4000), false);
		new org.eclipse.jetty.plus.jndi.EnvEntry(server.getServer(), "testStringValue1", "Sabaki kirenai", true);
	}

}
