package ru.tet.jetty;

import ru.tet.jetty.starter.JettyStarter;

/**
 * Запуск через jetty embedded (С использованием класса TetJettyServer)
 * 
 */
public class JettyStarter51 extends JettyStarter {

	@Override
	public void init() {
		options.setPort(8080);
		options.setContextPath("/demo-51-svg");
		
//		options.addAdditionalStatic(getAccordPath());
//		options.addAdditionalStatic(getTetSlickGridPath());
		options.addAdditionalStatic(getAuxJsResourcesPath().resolve("jquery31"));
//		options.addAdditionalStatic(getAuxJsResourcesPath().resolve("misc-libs"));

	}

	public static void main(String[] args) throws Exception {
		JettyStarter51 starter = new JettyStarter51();
		starter.start();
	}

}
