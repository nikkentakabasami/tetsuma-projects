package ru.tet.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ru.tet.beans.DemoFolder;
import ru.tet.utils.TetClasspathUtils;

@WebListener
public class MainServletContextListener implements ServletContextListener {

	static Logger logger = LogManager.getLogger();

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {

			ServletContext ctx = sce.getServletContext();


			String demosPath = "webroot/demos/";
			logger.info("MainServletContextListener: search demo pages in '"+demosPath+"'");

			List<String> demoFolders;

			demoFolders = TetClasspathUtils.scanClasspathFolder(demosPath, (String fileName, boolean isDirectory) -> {
				return isDirectory && (fileName.startsWith("demos_") || fileName.equals("templates"));
			});

			List<DemoFolder> dfs = new ArrayList<>();

			for (String folderName : demoFolders) {

				String folderPath = demosPath + folderName;

				logger.info("scan "+folderPath);
				
				//ищем html страницы с демками
				List<String> demoPages =
						TetClasspathUtils.scanClasspathFolder(folderPath, (String fileName, boolean isDirectory) -> {
							return !isDirectory && (fileName.endsWith(".jsp") || fileName.endsWith(".html"));
						});

				String desc = TetClasspathUtils.readClasspathResourceAsString(folderPath + "/desc.txt");

				DemoFolder df = new DemoFolder(folderName, desc, demoPages);
				dfs.add(df);
				ctx.setAttribute(folderName, df);

			}

			ctx.setAttribute("demoFolders", dfs);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}