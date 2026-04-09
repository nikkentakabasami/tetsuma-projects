package ru.tet.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Утилиты для поиска исходников.
 * 
 */
public class TetSourceUtils {

	
	/**
	 * Ищет исходники (java-файл) для заданного класса.
	 * 
	 * @param cl
	 * @return
	 * @throws IOException
	 */
	public static InputStream findSource(Class cl) throws IOException {
		
		String resourceName = cl.getCanonicalName().replaceAll("\\.", File.separator).concat(".java");
		
		//ищем в classpath
		InputStream inputStream = TetSourceUtils.class.getClassLoader().getResourceAsStream(resourceName);
		if (inputStream!=null) {
			return inputStream;
		}
		
		//ищем в текущем проекте
		Path path = Paths.get("src","main","java", resourceName);
		if (Files.exists(path)) {
			return Files.newInputStream(path);
		}
		path = Paths.get("src","test","java", resourceName);
		if (Files.exists(path)) {
			return Files.newInputStream(path);
		}
		
		throw new IOException("File not found: "+resourceName);
		
//		return null;
		
	}
	
	public static String findSourceAsString(Class cl) throws IOException {
		InputStream is = findSource(cl);
		return TetIOUtils.streamToString(is);
	}
	
	public static List<String> findSourceAsLines(Class cl) throws IOException {
		InputStream is = findSource(cl);
		return TetIOUtils.streamToLines(is);
	}
	
	
	
}
