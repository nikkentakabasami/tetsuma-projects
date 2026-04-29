package ru.tet.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class TetClasspathUtils {

	public static final String SLASH = "/";

	public static interface ResourceFilter {
		boolean accept(String fileName, boolean isDirectory);
	}

	/**
	 * Сканер ресурсов.
	 * Возвращает список имён файлов, которые лежат в classpath по заданному пути.
	 * Не погружается глубже первого уровня.
	 * 
	 * @param folderPath Например "webroot/demos/"
	 * @return
	 * @throws IOException 
	 */
	public static List<String> scanClasspathFolder(String folderPath, final ResourceFilter filter) throws IOException {

		if (!folderPath.endsWith(SLASH)) {
			folderPath += SLASH;
		}

		List<String> result = new ArrayList<>();

		// Получаем URL ресурса
		Enumeration<URL> folderUrls = Thread.currentThread().getContextClassLoader().getResources(folderPath);
		while (folderUrls.hasMoreElements()) {
			URL folderUrl = folderUrls.nextElement();
			String protocol = folderUrl.getProtocol();

			if ("jar".equals(protocol)) {
				JarURLConnection jarConnection = (JarURLConnection) folderUrl.openConnection();
				try (JarFile jarFile = jarConnection.getJarFile()) {

					Enumeration<JarEntry> entries = jarFile.entries();
					while (entries.hasMoreElements()) {
						JarEntry entry = entries.nextElement();

						String entryName = entry.getName();

						if (!entryName.startsWith(folderPath)) {
							continue;
						}
						if (entryName.equals(folderPath)) {
							continue;
						}
						entryName = entryName.substring(folderPath.length());

						if (entry.isDirectory()) {
							entryName = entryName.substring(0, entryName.length() - 1);
						}

						int ind = entryName.lastIndexOf(SLASH);
						//в подпапках не ищем
						if (ind > 0) {
							continue;
						}

						//						String entryPath = entryName.substring(0,ind+1);
						//						
						//						if (!entryPath.equals(folderPath)) {
						//							continue;
						//						}
						//						
						//						String entryShortName = entryName.substring(ind+1);
						//						if (entryShortName.length()==0) {
						//							continue;
						//						}
						//						
						if (filter != null && !filter.accept(entryName, entry.isDirectory())) {
							continue;
						}

						result.add(entryName);

					}

				}
			} else if ("file".equals(protocol)) {

				File folderFile = new File(folderUrl.getPath());
				File[] files;
				if (filter != null) {
					files = folderFile.listFiles(f -> filter.accept(f.getName(), f.isDirectory()));
				} else {
					files = folderFile.listFiles();
				}

				if (files == null) {
					continue;
				}
				Arrays.stream(files).sorted().forEach(dir -> {
					result.add(dir.getName());
				});
			}
		}

		return result;

	}

	public static String readClasspathResourceAsString(String resPath) throws IOException {
		URL url = Thread.currentThread().getContextClassLoader().getResource(resPath);
		if (url == null) {
			return null;
		}
		try (InputStream stream = url.openStream();) {
			return TetIOUtils.streamToString(stream);
		}
	}

}
