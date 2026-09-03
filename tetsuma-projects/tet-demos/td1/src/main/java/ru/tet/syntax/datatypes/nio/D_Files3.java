package ru.tet.syntax.datatypes.nio;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

import ru.tet.aux.swing.DemoBase;

public class D_Files3 extends DemoBase {

	class FV1 extends SimpleFileVisitor<Path> {
		public FileVisitResult visitFile(Path file, BasicFileAttributes attr) throws IOException {
			log2(file);
			return FileVisitResult.CONTINUE;
		}
	}

	public void test1() throws Exception {
		/*
		Files.walkFileTree(Path start, FileVisitor visitor)
		Files.walkFileTree(Path start, Set options, int maxDepth, FileVisitor visitor)
		Пробежаться по всем файлам в директории, рекурсивно.
		Начинает просмотр с самых глубоких файлов.
		
		maxDepth=1 - выведет непосредственное содержимое папки, не будет лезть в подпапки.
		maxDepth=2 - непосредственное содержимое папки и подпапок
		 */

		//Files.walkFileTree(Path.of("target"), new FV1());
		Files.walkFileTree(Path.of("target"), Collections.EMPTY_SET, 2, new FV1());

	}

	public void test2() throws Exception {
		/*
		Удаление папки с содержимым.
		 */

		deleteDirectoryRecursively(Path.of("target/fl2"));

	}

	public static void deleteDirectoryRecursively(Path path) throws IOException {
		if (Files.exists(path)) {
			Files.walk(path)
					.sorted(Comparator.reverseOrder())
					.forEach(p -> {
						try {
							Files.delete(p);
						} catch (IOException e) {
							throw new RuntimeException("Failed to delete " + p, e);
						}
					});
		}
	}

	public void test3() throws Exception {
		/*
		
		
		A	readAttributes(Path path, Class type)
		Получение общих метаданных файла
		
		BasicFileAttributes - общие метаданные
		DOSDosFileAttributes - виндовые
		PosixFileAttributes - линуксовые
		
		
		<A> readAttributes(Path path, Class<A> type, LinkOption... options)
		Map<String,Object>	readAttributes(Path path, String attributes, LinkOption... options)
		
		setAttribute(Path path, String attribute, Object value, LinkOption... options)
		
		
		
			
		 */

		Path p1 = Path.of("target");

		BasicFileAttributes attr = Files.readAttributes(p1, BasicFileAttributes.class);
		PosixFileAttributes posixAttr = Files.readAttributes(p1, PosixFileAttributes.class);

		logEval(
				attr.creationTime(),
				attr.lastAccessTime(),
				attr.lastModifiedTime(),
				attr.isRegularFile(),
				attr.isDirectory(),
				attr.isSymbolicLink(),
				attr.size(),
				posixAttr.owner().getName(),
				PosixFilePermissions.toString(posixAttr.permissions()));

		Map<String, Object> attrMap = Files.readAttributes(p1, "basic:size,creationTime");
		attrMap.forEach((k, v) -> log2(k, ":", v));

		
		
		
	}

	public void test4() throws Exception {
		/*
		
		 */
	}

	public static void main(String[] args) {
		DemoBase.run(D_Files3.class);
	}

}
