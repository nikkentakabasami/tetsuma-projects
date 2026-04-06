package ru.tet.java.nio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Пробегаемся по списку вложенных папок.
 * 
 */
public class FilesDemo {

	public static void main(String[] args) throws IOException {
		Path myFilesPath = Paths.get("/home/tetsuma/myfiles/");
		
		//		testWalk(myFilesPath);
//		testWalk2(myFilesPath);
		testWalk3(myFilesPath);
	}

	//Files.walk
	public static void testWalk(Path myFilesPath) {

		try (Stream<Path> pathStream = Files.walk(myFilesPath, 1)) {
			pathStream
					//                .filter(Files::isRegularFile)
					.filter(Files::isDirectory)
					.map(f -> f.getFileName())
					.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	//Files.walk2
	public static void testWalk2(Path myFilesPath) throws IOException {

		Files.walk(myFilesPath, 1)
				.filter(path -> Files.isDirectory(path) && !path.equals(myFilesPath))
				.map(f -> f.getFileName())
				.forEach(System.out::println);

	}

	//File.listFiles
	public static void testWalk3(Path myFilesPath) throws IOException {
		File[] dirList = myFilesPath.toFile().listFiles(f->f.isDirectory());
		Arrays.stream(dirList).forEach(dir->System.out.println(dir.getName()));

	}	
	
}
