package ru.tet.servlets;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ru.tet.beans.DemoFolder;
import ru.tet.demos.aux.DemosScanner;

@WebListener
public class MainServletContextListener implements ServletContextListener {

	static Logger logger = LogManager.getLogger();

	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {

			ServletContext ctx = sce.getServletContext();

			List<DemoFolder> dfs = DemosScanner.getInstance().scanDemos();

			ctx.setAttribute("demoFolders", dfs);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}