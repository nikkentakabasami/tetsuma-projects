package ru.tet.demos.aux;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ru.tet.beans.DemoFolder;
import ru.tet.beans.DemoPage;
import ru.tet.utils.TetClasspathUtils;

public class DemosScanner {

	private static final DemosScanner instance = new DemosScanner();

	private DemosScanner() {
	}

	public static DemosScanner getInstance() {
		return instance;
	}
	
	static final String DEMOS_PATH = "webroot/demos/";
	static final String TITLE_TAG1 = "<title>";
	static final String TITLE_TAG2 = "</title>";
	
	static Logger logger = LogManager.getLogger();

	List<DemoFolder> demoFolders;
	
	
	
	public List<DemoFolder> scanDemos() throws IOException {

		logger.info("MainServletContextListener: search demo pages in '" + DEMOS_PATH + "'");

		//ищем папки с демками
		List<String> demoFolderNames = TetClasspathUtils.scanClasspathFolder(DEMOS_PATH, (String fileName, boolean isDirectory) -> {
			return isDirectory && (fileName.startsWith("demos_") || fileName.equals("templates"));
		});

		demoFolders = new ArrayList<>();
		for (String folderName : demoFolderNames) {

			String folderPath = DEMOS_PATH + folderName;

			logger.info("scan " + folderPath);

			//ищем веб-страницы с демками
			List<String> pageNames =
					TetClasspathUtils.scanClasspathFolder(folderPath, (String fileName, boolean isDirectory) -> {
						return !isDirectory && (fileName.endsWith(".jsp") || fileName.endsWith(".html"));
					});

			String desc = TetClasspathUtils.readClasspathResourceAsString(folderPath + "/desc.txt");

			
			List<DemoPage> demoPages = new ArrayList<>(pageNames.size());
			for(String pageName:pageNames) {
				String pageDesc = scanDemoFileDesc(folderPath+"/"+pageName);
				if (pageDesc==null) {
					pageDesc = pageName;
				}
				demoPages.add(new DemoPage(pageName, pageDesc));
			}
			
			
			DemoFolder df = new DemoFolder(folderName, desc, demoPages);
			demoFolders.add(df);
		}

	return demoFolders;

	}
	
	
	public String[] findSiblingPages(String pageName) {
		getDemoFolders();
		
		
		for(DemoFolder df:demoFolders) {

			String[] r = df.findSiblingPages(pageName);
			if (r!=null) {
				return r;
			}
		}
		return null;
		
	}
	
	
	public List<DemoFolder> getDemoFolders() {
		if (demoFolders==null) {
			try {
				scanDemos();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return demoFolders;
	}

	


	public String scanDemoFileDesc(String resPath) throws IOException {

		String code = TetClasspathUtils.readClasspathResourceAsString(resPath);

		int ind1 = code.indexOf(TITLE_TAG1);
		int ind2 = code.indexOf(TITLE_TAG2);

		if (ind1 < 0 || ind2 < 0) {
			return null;
		}

		ind1 += TITLE_TAG1.length();

		return code.substring(ind1, ind2);

	}


}
