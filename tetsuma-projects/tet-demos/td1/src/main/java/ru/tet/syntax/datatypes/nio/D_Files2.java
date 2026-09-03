package ru.tet.syntax.datatypes.nio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.io.IOUtils;

import ru.tet.aux.swing.DemoBase;

public class D_Files2 extends DemoBase {

	public void test1() throws Exception {
		/*
		byte[] readAllBytes(Path path)
		Path write(Path path, byte[] bytes, OpenOption... options)
		Считывание и запись массива байтов
		
		
		InputStream newInputStream(Path path, OpenOption... options)
		Получение потока файла
		
		BufferedReader newBufferedReader(Path path, Charset cs)
		
		
		 */

		Path p1 = D_Files.createTextFile();
		log2("p1 =", p1);
		log2("Files.readAllBytes(p1)");

		byte[] bytes = Files.readAllBytes(p1);
		String content = new String(bytes);
		log2(p1 + " content:");
		log2(content);

		log2Splitter();
		Path p2 = Path.of("target/write_test.txt");
		byte[] bytes2 = "test data to write".getBytes();
		Files.write(p2, bytes2);
		log2("p2 =", p2);
		log2("Files.write(p2, bytes2)");

		log2Splitter();
		log2("Files.newInputStream(p2)");
		InputStream is = Files.newInputStream(p2);
		IOUtils.readLines(is).forEach(this::log2);

		log2Splitter();
		log2("Files.newBufferedReader(p2)");
		try (BufferedReader br = Files.newBufferedReader(p2)) {
			br.lines().forEach(this::log2);
		}

	}

	public void test2() throws Exception {
		/*
		String readString(Path path, Charset cs)
		List<String> readAllLines(Path path, Charset cs)
		
		Path writeString(Path path, CharSequence csq, Charset cs, OpenOption... options)
		Stream<String> lines(Path path, Charset cs)
		Считывание/запись в виде строк
		
		 */
		Path p1 = D_Files.createTextFile();
		log2("p1 =", p1);

		String content = Files.readString(p1);
		log2(p1 + " content:");
		log2(content);

		log2Splitter();
		log2("Files.readAllLines(p1)");
		List<String> lines = Files.readAllLines(p1);
		lines.forEach(this::log2);

		log2Splitter();
		log2("Files.lines(p1)");
		Files.lines(p1)
				.filter(line -> line.contains("3"))
				.forEach(this::log2);

		log2Splitter();
		log2("Files.writeString");
		Path p2 = Path.of("target", "test_writeString.txt");
		Files.deleteIfExists(p2);
		Files.createFile(p2);
		Files.writeString(p2, "test content for writeString");

		/*
		BufferedWriter		newBufferedWriter(Path path, Charset cs, OpenOption... options)
		OutputStream		newOutputStream(Path path, OpenOption... options)
		 */

		log2Splitter();
		log2("Files.newBufferedWriter(p2)");

		try (BufferedWriter bw = Files.newBufferedWriter(p2, StandardOpenOption.APPEND)) {
			bw.write("\nadded line");
		}
		log2(Files.readString(p2));

		log2Splitter();
		log2("Files.newOutputStream(p2)");

		try (OutputStream os = Files.newOutputStream(p2, StandardOpenOption.APPEND)) {
			os.write("\n added third line".getBytes());
		}
		log2(Files.readString(p2));

	}

	public void test3() throws Exception {
		/*
		DirectoryStream<Path> newDirectoryStream(Path dir)
		Просмотр содержимого директории
		
		
		 */
		Path p1 = Path.of("target");

		try (DirectoryStream<Path> ds = Files.newDirectoryStream(p1)) {
			for (Path file : ds) {
				log2(file);
			}
		}

		log2Splitter();

		try (DirectoryStream<Path> ds = Files.newDirectoryStream(p1)) {
			ds.forEach(this::log2);
		}

		log2Splitter();
		log2("printDirs:");
		printDirs(p1, "");

		/*
		Stream<Path>	list(Path dir)		
		 */
		try (Stream<Path> stream = Files.list(p1)) {
			stream.forEach(this::log2);
		}

	}

	public void printDirs(Path path, String offset) throws IOException {
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
			for (Path file : stream) {

				if (!Files.isDirectory(file)) {
					continue;
				}
				log2(offset, file);
				printDirs(file, offset + "  ");
			}
		}
	}

	public void test4() throws Exception {
		/*
		Stream<Path>	walk(Path start, int maxDepth, FileVisitOption... options)
		Stream<Path>	walk(Path start, FileVisitOption... options)
		возвращает поток со всеми файлами и директориями (рекурсивно), включая текущую папку.
		опция есть только одна: FOLLOW_LINKS
		
		Для предотвращения утечек его нужно вызывать в блоке try-with-resources
		
		 */
		Path p1 = Path.of("target");

		//Пробегаемся по всем папкам
		log2Splitter("dirs");
		try (Stream<Path> stream = Files.walk(p1)) {
			stream
					.filter(path -> Files.isDirectory(path))
					.forEach(this::log2);
		}

		log2Splitter("regular files");
		try (Stream<Path> stream = Files.walk(p1, 2)) {
			stream
					.filter(path -> Files.isRegularFile(path))
					.forEach(this::log2);
		}

	}

	@Override
	public void test5() throws Exception {
		/*
		Stream<Path>	find(Path start, int maxDepth, BiPredicate matcher, FileVisitOption... options)
		 */

		Path p1 = Path.of("target");
		try (Stream<Path> stream =
				Files.find(p1, 2,
						(path, attr) -> path.toString().endsWith(".txt"))) {

			stream.forEach(this::log2);
		}

		log2Splitter("all big files");
		try (Stream<Path> stream =
				Files.find(p1, Integer.MAX_VALUE,
						(path, attr) -> attr.isRegularFile() && attr.size() > 10 * 1024)) {
			stream.forEach(this::log2);
		}
		
		
	}

	public static void main(String[] args) {
		DemoBase.run(D_Files2.class);
	}

}
