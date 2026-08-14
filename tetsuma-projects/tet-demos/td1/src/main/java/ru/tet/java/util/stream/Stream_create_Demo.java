package ru.tet.java.util.stream;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import ru.tet.aux.swing.DemoBase;

public class Stream_create_Demo extends DemoBase {

	double d1;

	public void test1() throws Exception {
		/*
		static Stream<T>	empty()
		static Stream<T>	of(T... values)	
		
		static Stream<T>	generate(Supplier<T> s)	
		static Stream<T>	iterate(T seed, UnaryOperator<T> f)		
		
		 */
		Stream<Object> s1 = Stream.empty();
		Stream<String> s2 = Stream.of("Hi");
		Stream<String> s3 = Stream.of("yanineko", "tabako", "ski");

		r.s1 = s1;
		r.s2 = s2;
		r.s3 = s3;

		/*
		static Stream<T>	generate(Supplier<T> s)	
		
		static Stream<T>	iterate(T seed, UnaryOperator<T> f)		
		создаёт бесконечный Stream, путём повторяющегося применения функции f к начальному значению seed.
		
		static Stream<T>	concat(Stream a, Stream b)
		
		 */

		d1 = 7;
		r.s4 = Stream.generate(() -> {
			d1 = d1 * 1.2;
			return d1;
		}).limit(5);

		r.s5 = Stream.generate(() -> Math.round(Math.random() * 100) / 100.0).limit(3);

		//3 раза вывести Hello
		Stream.generate(() -> "Hello").limit(3).forEach(this::log2);
		
		r.s7 = Stream.iterate(1, n -> n + 1).limit(5);

		
		r.s8 = Stream.concat(IntStream.of(7,2,8).boxed(), Stream.of("w","k"));
		
		
	}

	public void test2() throws Exception {
		/*
		
		collection.stream()
		collection.parallelStream()		
		Создание стрима из коллекции
		
		Arrays.stream(массив)
		Создание стрима из массива
		
		Files.lines(путь_к_файлу)
		Создание стрима из файла (каждая строка в файле будет отдельным элементом в стриме)
		
		 */

		List<String> list1 = Arrays.asList("a1", "a2", "a3");
		Stream<String> s1 = list1.stream();
		r.s1 = s1;

		String[] array1 = { "a1", "a2", "a3" };
		Stream<String> s2 = Arrays.stream(array1);
		r.s2 = s2;

		Stream<String> s3 = Files.lines(Paths.get("NOTICE.txt"));
		r.s3 = s3;
		
		/*
		IntStream string.chars()
		Коды символов из строки.
		 */
		
		IntStream s4 = "123".chars();
		r.s4 = s4.toArray();

		
		/*
		Stream.builder().add(...)....build()
		
		 */
		
		Stream<String> s5 = Stream.<String>builder().add("a1").add("a2").add("a3").build();
		r.s5 = s5;
		
		
    Stream.Builder<String> cityBuilder = Stream.builder();
    cityBuilder.add("London");
    cityBuilder.add("Paris");
    cityBuilder.add("Tokyo");
    Stream<String> s6 = cityBuilder.build();		
		r.s6 = s6;
		
		

	}

	public void test3() throws Exception {
		/*
		
		 */
	}

	public void test4() throws Exception {
		/*
		
		 */
	}

	@Override
	protected void doInitControlPanel() throws Exception {
		addTest1Button(null);
		addTest2Button(null);
//		addTest3Button(null);
//		addTest4Button(null);
	}

	public static void main(String[] args) {
		DemoBase.run(Stream_create_Demo.class);
	}

}
