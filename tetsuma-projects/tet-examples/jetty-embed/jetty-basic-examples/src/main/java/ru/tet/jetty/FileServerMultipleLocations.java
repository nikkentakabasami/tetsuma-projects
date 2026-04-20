package ru.tet.jetty;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.ee10.servlet.DefaultServlet;
import org.eclipse.jetty.ee10.servlet.ResourceServlet;
import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.ee10.servlet.ServletHolder;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.util.resource.ResourceFactory;
import org.eclipse.jetty.util.resource.Resources;

/**
 * Использование ServletContextHandler для обслуживания статического контента из 
 * разных локаций.
 *  
 * context.setBaseResource(r); - подключение статических ресурсов (из класспаза)
 * ResourceServlet - позволяет подключить дополнительно статические ресурсы из доп. локаций.
 *
 */
public class FileServerMultipleLocations {
	
	static Logger logger = LogManager.getLogger();
	
	public static void main(String[] args) throws Exception {
		
		
		
		
		Server server = new Server(8081);

		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		
		//добавляем статические ресурсы из класспаза через baseResource
		ResourceFactory resourceFactory = ResourceFactory.of(server);
		Resource baseResource = resourceFactory.newClassLoaderResource("static-root2");
		logger.info("baseResource is: " + baseResource.getPath());
		if (!Resources.isReadableDirectory(baseResource))
			throw new FileNotFoundException("Unable to find base-resource for "+baseResource.getFileName());
		
		context.setBaseResource(baseResource);
		
		//добавляем дополнительные статические ресурсы файловой системы через ResourceServlet
		Path altPath = Paths.get("../../web-apps/web-static-roots/src/webapps/alt-root/");
		String altPathUri = altPath.toUri().toASCIIString();
		logger.info("Alt Base Resource is: " + altPath);
		
		ServletHolder holderAlt = new ServletHolder("static-alt", ResourceServlet.class);
		holderAlt.setInitParameter("baseResource", altPathUri);
//		holderAlt.setInitParameter("dirAllowed", "true");
//		holderAlt.setInitParameter("pathInfoOnly", "true");
		context.addServlet(holderAlt, "/alt/*");
		
		context.setWelcomeFiles(new String[] { "r2index.html"});

		//добавляем DefaultServlet для обслуживания статического контента из context.baseResource
		ServletHolder holderDef = new ServletHolder("default", DefaultServlet.class);
		context.addServlet(holderDef, "/");
		
		
		//добавляем defaultHandler - чтобы генерировать страницу с контекстами при заходе в корень сервера
		Handler.Sequence handlers = new Handler.Sequence(context,new DefaultHandler());
    server.setHandler(handlers);
		
		
		server.start();
		server.join();
	}

}
