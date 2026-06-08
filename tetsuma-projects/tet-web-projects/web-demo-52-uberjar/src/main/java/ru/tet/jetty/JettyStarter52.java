package ru.tet.jetty;

import javax.naming.NamingException;

import ru.tet.beans.UserDTO;
import ru.tet.jetty.starter.uberjar.JettyStarter;

/**
 * Запуск через jetty embedded (С использованием класса TetJettyServer)
 * 
 */
public class JettyStarter52 extends JettyStarter {

	@Override
	public void init() throws Exception {
		options.setPort(8090);
		options.setContextPath("/demo-52");
		options.setWelcomeFiles(null);

		options.addAdditionalStatic("auxjs-libs");
		options.addAdditionalStatic("accord-lib");
		options.addAdditionalStatic("tet-slick-grid-lib");

		
		
		
		//		options.addAdditionalStatic(getAccordPath());
		//		options.addAdditionalStatic(getTetSlickGridPath());
		//		options.addAdditionalStatic(getAuxJsResourcesPath().resolve("jquery31"));
		//		options.addAdditionalStatic(getAuxJsResourcesPath().resolve("misc-libs"));

		bindWebappEx1Jndi();

	}

	public static void main(String[] args) throws Exception {
		JettyStarter52 starter = new JettyStarter52();
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
