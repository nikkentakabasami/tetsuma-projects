package ru.tet.jetty.starter.uberjar;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.ee10.webapp.WebAppContext;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.util.resource.ResourceFactory;
import org.eclipse.jetty.util.resource.Resources;


/**
 * Вспомогательный класс, умеющий запускать веб-приложения с использованием jetty embedded.
 * 
 * Запуск может быть в dev-режиме.
 * Проект должен содержать все зависимости, что есть в запускаемом веб-проекте.
 * 
 * 
 * Для запуска использует WebAppContext.
 * 
 * 
 */
public class TetJettyServer {
	static Logger logger = LogManager.getLogger();
	
	Server server;
	WebAppContext webAppContext;

	ResourceFactory resourceFactory;

	TetJettyOptions options;

	//Handler-ы этого сервера. Можно добавлять новые, если надо.
	Handler.Sequence handlers;
	
	public void init(TetJettyOptions options) throws Exception {
		this.options = options;

//		logger.debug("options: "+options.getAdditionalStaticAsString());
		
		server = new Server(options.getPort());

		webAppContext = new WebAppContext();
		webAppContext.setContextPath(options.getContextPath());
		
		if (options.getWelcomeFiles()!=null) {
			webAppContext.setWelcomeFiles(options.getWelcomeFiles());
		}

		//поддержка аннотаций
		webAppContext.setConfigurationDiscovered(options.isAnnotationSupport());
		
		//какие jar нужно сканировать на аннотации и TLD.
		//по умолчанию jetty может сканировать не всё, ради большей производительности.
		//это приводит к странным глюкам.
		//в данном случае мы сканируем всё.
		webAppContext.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern", ".*");
//		webAppContext.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern", ".*/jakarta.servlet-api-[^/]*\\.jar$");
		
		resourceFactory = ResourceFactory.of(webAppContext);

		Resource baseResource = resourceFactory.newClassLoaderResource("webroot");
		if (Resources.missing(baseResource)) {
			throw new FileNotFoundException("Unable to find resource: " + baseResource);
		}
		
		List<Resource> baseResources = new ArrayList<>();
		baseResources.add(baseResource);

		//дополнительный статический контент
		//подключается через ResourceServlet
		for(String path: options.getAdditionalStatic()) {
			
			Resource resource = resourceFactory.newClassLoaderResource(path,false);
			if (Resources.missing(resource)) {
				throw new FileNotFoundException("Unable to find resource " + path);
			}
			baseResources.add(resource);
		}
		baseResource = ResourceFactory.combine(baseResources);		
		
		webAppContext.setBaseResource(baseResource);
		
		
		//добавляем defaultHandler - чтобы генерировать страницу с контекстами при заходе в корень сервера
		DefaultHandler defaultHandler = new DefaultHandler();
		
		
        handlers = new Handler.Sequence();
        handlers.addHandler(webAppContext);
        handlers.addHandler(defaultHandler);
        server.setHandler(handlers);

	}

	public void start() throws Exception {
		if (options.dumpAfterStart) {
			server.setDumpAfterStart(true);
		}
		server.start();
	}

	public void stop() throws Exception {
		server.stop();
	}

	/**
	 * Cause server to keep running until it receives a Interrupt.
	 * Interrupt Signal, or SIGINT (Unix Signal), is typically seen as a result of a kill -TERM {pid} or Ctrl+C
	 */
	public void waitForInterrupt() throws InterruptedException {
		server.join();
	}

	public Server getServer() {
		return server;
	}

	public WebAppContext getWebAppContext() {
		return webAppContext;
	}
	
	public Handler.Sequence getHandlers() {
		return handlers;
	}

	
}
