package ru.tet.jetty;

import ru.tet.jetty.starter.JettyStarter;

/**
 * Запуск через jetty embedded (С использованием класса TetJettyServer)
 * 
 */
public class JettyAccordDemoStarter extends JettyStarter {

	@Override
	public void init() {
		super.init();
//		options.setPort(8080);
		options.setContextPath("/webapp1");
		
		options.addAdditionalStatic(getAccordPath());
		options.addAdditionalStatic(getTetSlickGridPath());
		options.addAdditionalStatic(getAuxJsResourcesPath().resolve("jquery31"));
		options.addAdditionalStatic(getAuxJsResourcesPath().resolve("misc-libs"));

	}

	public static void main(String[] args) throws Exception {
		JettyAccordDemoStarter starter = new JettyAccordDemoStarter();
		starter.start();
	}

}
