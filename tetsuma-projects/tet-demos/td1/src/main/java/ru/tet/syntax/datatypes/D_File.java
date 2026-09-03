package ru.tet.syntax.datatypes;

import java.io.File;
import java.net.URI;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.util.Arrays;

import ru.tet.aux.swing.DemoBase;

public class D_File extends DemoBase {

	public void test1() throws Exception {
		/*
		File(File parent, String child)
		File(String pathname)
		File(URI uri)
		
		File.createTempFile(String prefix, String suffix, File directory)
		Создание временного файла.
		Если не задать directory - будет создан в системной папке для временных файлов
		Не удаляется автоматом.
		
		
		Абстрактное, системно независимое, представление имени файла (или папки)
		Устаревший класс, лучше использовать java.nio.file.Path
		
		 */

		//Способы создания файлов
		File d1 = Paths.get("/home/tetsuma/myfiles/").toFile();
		File f2 = new File(d1, "tmp.txt");

		File f1 = new File("../pom.xml");	//относительный путь
		File f3 = new File("/home/tetsuma/myfiles/theft.txt");
		File f4 = new File(new URI("file:///home/tetsuma/myfiles/theft.txt"));
		
		//Создание на основе Path
		File f5 = Paths.get("/home/tetsuma/myfiles/").toFile();
		
		//временные
		File f6 = File.createTempFile("testp", "tests", new File("target"));
		File f7 = File.createTempFile("testp", "tests");
		f6.deleteOnExit();
		f7.deleteOnExit();
		
		
		
		
		logEval(
				f1,
				
				//атрибуты файлов
				f1.exists(),
				f1.length(),
				f1.lastModified(),
				f1.canRead(),
				f1.canWrite(),
				f1.canExecute(),

				f1.isDirectory(),
				f1.isFile(),
				f1.isHidden(),
				
				//имя файла, заданное при создании - полное/абсолютное
				f1.isAbsolute(),
				
				//конвертации
				f1.toURI(),
				f1.toURI().toURL(),
				f1.toPath(),
				
				//краткое имя файла
				f1.getName(),
				//полное имя директории с файлом
				f1.getParent(),
				//путь, который был использован для создания объекта
				f1.getPath(),
				//абсолютный путь, основанный на текущей рабочей директории, если путь был относительным.
				f1.getAbsolutePath(),
				//абсолютный путь с устранёнными . и .., а также разрешёнными символическими ссылками.
				f1.getCanonicalPath(),
				
				f1.getAbsoluteFile().getPath(),
				f1.getCanonicalFile().getPath(),
				f1.getParentFile().getPath(),
				
				
				
				
				
				Arrays.toString(d1.list()),
				
				//место на диске с файлом
				f1.getTotalSpace(),
				f1.getUsableSpace(),
				f1.getFreeSpace()
				
				
				
				
				);


	}

	public void test2() throws Exception {
		/*
		boolean 	createNewFile()  создание пустого файла
		boolean 	mkdir()  		создание папки
		boolean 	mkdirs() 		создание папок, включая все родительские папки
		boolean 	delete() 		удаление файла
		void 	deleteOnExit()    	удаление файла по завершении работы vm
		boolean 	renameTo(File dest)	Переименование
		
		
		Действия над файлом
		
		 */

		File d1 = new File("target/testDir1");
		File d2 = new File("target/testDir2/aaa");
		d1.mkdir();
		d2.mkdirs();

		File f1 = new File("target/test1.txt");
		f1.createNewFile();

		File f2 = new File("target/test2.txt");
		f2.createNewFile();
		f2.deleteOnExit();

		
		
		
		f1.setExecutable(true);
		f1.setReadable(true);
		f1.setWritable(true);
		
		f1.setLastModified(Instant.now().getLong(ChronoField.MILLI_OF_SECOND));
		
		
		
		File tempFile1 = File.createTempFile("testp", "tests", new File("target"));
		//чтобы он удалился сам при выходе
		tempFile1.deleteOnExit();

		//		File tempFile2 = File.createTempFile(null, null, new File("target"));

		File targetDir = new File("target");
		Arrays.stream(targetDir.list()).forEach(this::log2);

		log2Splitter("delete test files and dirs:");

		d1.delete();
		f1.renameTo(new File("target/testDir2/renamedTest.txt"));

		//непустая папка не удалится
		File d3 = new File("target/testDir2");
		d3.delete();

		d2.delete();

		Arrays.stream(targetDir.list()).forEach(this::log2);

	}

	public void test3() throws Exception {
		/*
File[] 	listFiles()
File[] 	listFiles(FileFilter filter)
File[] 	listFiles(FilenameFilter filter)
String[] 	list()
String[] 	list(FilenameFilter filter)
		
		 */
		
		log2Splitter("File.listRoots()");
		File[] roots = File.listRoots();
		Arrays.stream(roots).forEach(f -> log2(f.getName()));

		File targetDir = new File("target");
		
		log2Splitter("targetDir.list()");
		Arrays.stream(targetDir.list()).forEach(this::log2);
		
		log2Splitter("targetDir.listFiles(()");
		Arrays.stream(targetDir.listFiles()).forEach(f -> log2(f.getName()));
		
		log2Splitter("targetDir.listFiles(FilenameFilter filter)");
		Arrays.stream(targetDir.listFiles((File dir, String name)->{
			return name.contains("test");
		})).forEach(f -> log2(f.getName()));
		
		
		
	}

	public void test4() throws Exception {
		/*
		
		 */
	}

	@Override
	protected void doInitControlPanel() throws Exception {
		addTest1Button(null);
		addTest2Button(null);
		addTest3Button(null);
		addTest4Button(null);
	}

	public static void main(String[] args) {
		DemoBase.run(D_File.class);
	}

}
