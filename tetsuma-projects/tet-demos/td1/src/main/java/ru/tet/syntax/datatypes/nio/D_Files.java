package ru.tet.syntax.datatypes.nio;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
import java.util.stream.IntStream;

import ru.tet.aux.swing.DemoBase;

public class D_Files extends DemoBase {

	public void test1() throws Exception {
		/*
		java.nio.file.Files
		Работа с файлами через Path: CRUD операции, считывание атрибутов
		Крайне удобно.
		
		
		Проверки
		
		boolean exists(Path path, LinkOption... options)
		boolean notExists(Path path, LinkOption... options)
		Проверка на наличие.
		Опция есть только одна - NOFOLLOW_LINKS (обработка symbolic links)
		
		Атрибуты файла
		
		boolean isDirectory(Path path, LinkOption... options)
		boolean isExecutable(Path path)
		boolean isHidden(Path path)
		boolean isReadable(Path path)
		boolean isRegularFile(Path path, LinkOption... options)
		boolean isSameFile(Path path, Path path2)
		boolean isSymbolicLink(Path path)
		boolean isWritable(Path path)
		
FileTime	getLastModifiedTime(Path path, LinkOption... options)
long	size(Path path)
		
		 */

		Path p1 = Paths.get("../pom.xml");
		Path p2 = p1.toAbsolutePath();

		logEval(
				Files.exists(p1),
				Files.notExists(p1),

				Files.isDirectory(p1),
				Files.isExecutable(p1),
				Files.isHidden(p1),
				Files.isReadable(p1),
				Files.isRegularFile(p1),
				Files.isSameFile(p1, p2),
				Files.isSymbolicLink(p1),
				Files.isWritable(p1),
				
				
				Files.getLastModifiedTime(p2),
				Files.size(p2)
				

		);

	}

	public void test2() throws Exception {
		/*
		Path createDirectories(Path dir, FileAttribute>... attrs)
		Path createDirectory(Path dir, FileAttribute... attrs)
		Path createFile(Path path, FileAttribute... attrs)
		Создание папок и директорий
		
		Path createTempFile(Path dir, String prefix, String suffix)
		Path createTempFile(String prefix, String suffix)
		
		
		
		 */
		//FileAttribute<FileTime> attr = UnixAsBasicFileAttributes.creationTime( FileTime.from(Instant.now()) );

		//Задание атрибутов при создании файла
		Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rw-------");
		FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(perms);

		Path p1 = Paths.get("target/fl2/ttt/t1.txt");
		Files.createDirectories(p1.getParent());

		if (!Files.exists(p1)) {
			Files.createFile(p1, attr);
		}

		Path tempFile = Files.createTempFile(Paths.get("target"), "fp___", "___fs");
		log2("created:", tempFile);

	}

	public void test3() throws Exception {
		/*
		Path createLink(Path link, Path existing)
		Создание hard link (неотличима от оригинального файла - ссылка на место на диске) 
		
		Path createSymbolicLink(Path link, Path target, FileAttribute... attrs)
		Создание soft link - если оригинальный файл будет удалён - обращение к ссылке кинет ошибку
		 */

		Path textFile = createTextFile().toAbsolutePath();
		log2("created file:", textFile);

		//soft link/ symbolic link
		Path link = Paths.get("target", "symbolic_link.txt");
		if (Files.exists(link)) {
			Files.delete(link);
		}
		Path sl = Files.createSymbolicLink(link, textFile);
		log2("created symbolic link:", sl);

		//hard link
		Path link2 = Paths.get("target", "hard_link.txt");
		if (Files.exists(link2)) {
			Files.delete(link2);
		}
		Path hl = Files.createLink(link2, textFile);
		log2("created hard link:", hl);

		//вывод всех софтлинк-файлов в лог
		printLinkFiles(Paths.get("target"));

	}

	public void test4() throws Exception {
		/*
		void delete(Path path)		//кидает исключение если файла нет
		boolean deleteIfExists(Path path)
		Удаление файла
		
		
		Path copy(Path source, Path target, CopyOption... options)
		Копирование
		
		long copy(Path source, OutputStream out)
		long copy(InputStream in, Path target, CopyOption... options)
		Считывание и запись
		 */

		Path link1 = Paths.get("target", "hard_link.txt");
		Files.deleteIfExists(link1);

		Path p1 = Paths.get("target", "testTextFile.txt");
		Path p2 = Paths.get("target", "textFileCopy.txt");

		Files.copy(p1, p2, StandardCopyOption.REPLACE_EXISTING);

		ByteArrayOutputStream os1 = new ByteArrayOutputStream();
		Files.copy(p1, os1);
		String content = new String(os1.toByteArray());
		log2(p1 + " content:");
		log2(content);

		Path p3 = Paths.get("target", "stringCopy.txt");
		Files.copy(new ByteArrayInputStream("test file content".getBytes()), p3);

	}

	@Override
	public void test5() throws Exception {
		/*
		Path move(Path source, Path target, CopyOption... options)
		Перемещение
		
		options:
		REPLACE_EXISTING,
		COPY_ATTRIBUTES,
		ATOMIC_MOVE - перемещение файла должно быть выполнено атомарно, то есть как неделимая операция
		 */

		Path p4 = Paths.get("target", "tf4.txt");
		Path p5 = Paths.get("target", "tf4_moved.txt");
		if (!Files.exists(p4)) {
			Files.createFile(p4);
		}

		Files.move(p4, p5, StandardCopyOption.REPLACE_EXISTING);
	}

	public static Path createTextFile() throws IOException {
		Path filePath = Paths.get("target", "testTextFile.txt");
		if (Files.exists(filePath)) {
			return filePath;
		}

		byte[] content =
				IntStream.range(0, 20)
						.mapToObj(i -> {
							String sep = i % 5 != 0 ? ", " : System.lineSeparator();
							return i + sep;
						})
						.reduce("", String::concat)
						.getBytes(StandardCharsets.UTF_8);
		Files.write(filePath, content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
		return filePath;
	}

	//пример
	public void printLinkFiles(Path path) throws IOException {
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
			for (Path file : stream) {
				if (Files.isDirectory(file)) {
					printLinkFiles(file);
				} else if (Files.isSymbolicLink(file)) {
					Path target = Files.readSymbolicLink(file);
					log2("File link" + file + "with target " + target + " %n");
				}
			}
		}
	}

	public static void main(String[] args) {
		DemoBase.run(D_Files.class);
	}

}
