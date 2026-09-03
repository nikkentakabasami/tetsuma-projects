package ru.tet.syntax.datatypes.nio;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import ru.tet.aux.swing.DemoBase;

public class D_Path extends DemoBase {

	public void test1() throws Exception {
		/*
		java.nio.file.Path
		Путь в файловой системе (список элементов пути).
		Более продвинутая альтернатива File (но не способен менять файлы!)
		
		Позволяет не заморачиваться с разделителями пути - он сам всё поправит и нормализует!
		
		Path	Paths.get(String first, String... more)
		Path	Paths.get(URI uri)
		Создание Path через helper класс Paths
		
		 */

		File f1 = new File("../pom.xml"); //относительный путь

		logEval(
				Paths.get("E:/ajp/io/sample.txt"),
				Paths.get("sample.txt"),

				Paths.get("E:", "ajp", "io", "sample.txt"),
				Paths.get("E:/ajp/io", "sample.txt"),
				Paths.get("E:", "ajp/io/sample.txt"),
				Paths.get("E:\\ajp\\io\\sample.txt"),

				f1.toPath());

		/*
		URI toUri();
		File toFile();
		Конвертация
		 */

		Path p1 = Paths.get("../pom.xml");

		logEval(
				p1.toUri(),
				p1.toFile());

	}

	public void test2() throws Exception {
		/*
		Преобразования пути.
		
		Path toAbsolutePath();
		абсолютный путь, основанный на текущей рабочей директории
		не убирает ..		
		
		Path	normalize()
		убирает из пути лишние элементы
		
		Path toRealPath(LinkOption... options)
		возвращает истинный путь к файлу (убирает .., разрешает ссылки)
		
		
		Path resolve(Path other);
		Path resolve(String other);
		Объединение путей
		
		Path resolveSibling(Path other);
		Path resolveSibling(String other);
		Замена конечного элемента пути на свой
		Позволяет переименовать файл
		
		
		Path relativize(Path other);
		Создание относительного пути
		
		
		 */

		Path p1 = Paths.get("../pom.xml");

		logEval(

				p1.toAbsolutePath(),
				p1.normalize(),

				Paths.get("/home/tetsuma/../f.txt").normalize(),

				p1.toRealPath(),

				//Объединение путей
				Paths.get("E:/ajp").resolve("io/sample.txt"),

				Paths.get("/home/tetsuma").resolve(Paths.get("../t.txt")),

				//Замена конечного элемента пути
				Paths.get("/home/tetsuma/myFile.txt").resolveSibling("myFile2.txt"),

				//Создание относительного пути
				Paths.get("/home").relativize(Paths.get("/home/tetsuma/tmp")));
	}

	public void test3() throws Exception {
		/*
		Элементы пути.
		Самый верхний элемент имеет индекс 0.
		
		Path getRoot();
		Path getFileName();
		Path getParent();
		
		int getNameCount();
		Path getName(int index);
		Path subpath(int beginIndex, int endIndex);
		Iterator<Path> iterator();
		Получение элементов пути		
		 */

		Path p1 = Paths.get("../pom.xml");
		Path p2_absolute = p1.toAbsolutePath();
		//Path p2_absolute = p1.toAbsolutePath();

		logEval(

				p1.getRoot(),

				Paths.get("/home/tetsuma").getRoot(),

				p1.getFileName(),
				p1.getParent(),

				p1.getNameCount(),
				p1.getName(0),
				p1.getName(1),

				p2_absolute,

				p2_absolute.getRoot(),

				p2_absolute.getNameCount(),
				p2_absolute.getName(0),
				p2_absolute.getName(1),
				p2_absolute.getName(10)


		);

		log2("p2_absolute.iterator()");
		p2_absolute.iterator().forEachRemaining(this::log2);

	}

	public void test4() throws Exception {
		/*
		Проверки
		
		boolean startsWith(Path other);
		boolean startsWith(String other);
		
		boolean endsWith(Path other);
		boolean endsWith(String other);
		
		boolean isAbsolute();
		
		 */
		
		
		
		Path p1 = Paths.get("/home/tetsuma/f.txt");
		
		
		logEval(
				p1.getFileSystem(),
				p1.isAbsolute(),
				p1.startsWith("/home"),
				p1.startsWith("/"),
				p1.endsWith("f.txt")
		);

		
		
		
		
	}

	@Override
	protected void doInitControlPanel() throws Exception {
		addTest1Button(null);
		addTest2Button(null);
		addTest3Button(null);
		addTest4Button(null);
	}

	public static void main(String[] args) {
		DemoBase.run(D_Path.class);
	}

}
