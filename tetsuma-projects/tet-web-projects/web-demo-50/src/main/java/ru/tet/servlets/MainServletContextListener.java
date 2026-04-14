package ru.tet.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ru.tet.beans.DemoFolder;

@WebListener
public class MainServletContextListener implements ServletContextListener {

	private final static Logger logger = Logger.getLogger(MainServletContextListener.class.getName());

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext ctx = sce.getServletContext();

		LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME).setLevel(Level.FINE);

		logger.info("MainServletContextListener: search demo pages");

		File demosDir = new File(ctx.getRealPath("/demos"));

		File[] dirList =
				demosDir
						.listFiles(f -> f.isDirectory() && (f.getName().startsWith("demos_") || f.getName().equals("templates")));

		List<DemoFolder> demoFolders = Arrays.stream(dirList).sorted().map(dir -> {
			List<String> list = findPageFiles(dir);

			String desc = readDesc(dir);
			DemoFolder f = new DemoFolder(dir.getName(), desc, list);
			ctx.setAttribute(dir.getName(), f);
			return f;
		}).collect(Collectors.toList());

		ctx.setAttribute("demoFolders", demoFolders);

	}

	private String readDesc(File demoDir) {
		File descFile = new File(demoDir, "desc.txt");
		if (descFile.exists()) {
			try {
				List<String> lines = IOUtils.readLines(new FileInputStream(descFile), Charsets.UTF_8);
				return lines.stream().collect(Collectors.joining("<br>"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "";

	}

	List<String> findPageFiles(File dir) {

		List<String> pageNames = new ArrayList<>();
		if (dir.exists() && dir.isDirectory()) {
			for (File file : dir.listFiles((d, name) -> name.endsWith(".jsp") || name.endsWith(".html"))) {
				pageNames.add(file.getName());
			}
		}
		Collections.sort(pageNames);

		String s = Arrays.toString(pageNames.toArray());
		logger.info(dir.getName() + ": found files: " + s);

		return pageNames;

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
}