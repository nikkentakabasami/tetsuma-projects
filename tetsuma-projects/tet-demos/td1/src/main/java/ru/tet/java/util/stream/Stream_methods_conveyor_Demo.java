package ru.tet.java.util.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import ru.tet.aux.swing.DemoBase;

public class Stream_methods_conveyor_Demo extends DemoBase {

	List<Integer> rangeList;
	List<Integer> intList;
	List<String> strList;

	protected void doInit() throws Exception {
		intList = List.of(21, 2, 35, 5, 9);
		strList = List.of("yanineko", "tabako", "ski");
		rangeList = IntStream.range(1, 100).boxed().collect(Collectors.toList());
	}

	public void test1() throws Exception {
		/*
		Конвеерные методы
		возвращают другой stream
		
		Stream<T> filter(Predicate<? super T> predicate);
		Отфильтровывает записи, возвращает только записи, соответствующие условию
		
		Stream<T> skip(long n);
		Позволяет пропустить N первых элементов
		
		Stream<T> limit(long maxSize);
		Позволяет ограничить выборку определенным количеством первых элементов
		
		Stream<T> distinct();
		Возвращает стрим без дубликатов (для метода equals)
		
		Stream<T> sorted();
		Stream<T> sorted(Comparator<? super T> comparator);
		Позволяет сортировать значения либо в натуральном порядке, либо задавая Comparator
		
		 */

		r.s1 = intList.stream().filter(n -> n % 2 > 0);
		r.s2 = Stream.iterate(1, n -> n * 3).skip(3).limit(5);
		r.s3 = Stream.of(2, 3, 3, 2, 77).distinct();
		r.s4 = intList.stream().sorted();

	}

	public void test2() throws Exception {
		/*
		Stream<R> map(Function<T,R> mapper);
		Преобразует каждый элемент стрима
		
		Stream<R> flatMap(Function mapper);
		превращает каждый элемент в поток, после чего объединяет эти потоки воедино.
		
		Похожие методы: flatMapToInt, flatMapToDouble, flatMapToLong
		 */

		r.s1 = intList.stream().map(String::valueOf).collect(Collectors.joining(":", "(", ")"));

		//поток из кодов символов
		r.s2 = strList.stream().flatMap(s -> s.chars().boxed());

		//поток из строк-символов
		r.s3 = strList.stream().flatMap(s -> Arrays.stream(s.split("")));

	}

	public void test3() throws Exception {
		/*
		LongStream	mapToLong(ToLongFunction mapper)		
		IntStream mapToInt(ToIntFunction<? super T> mapper);
		DoubleStream	mapToDouble(ToDoubleFunction mapper)
		Аналог map, но возвращает стрим из числовых примитивов
		
		
		 */

		LongStream s1 = intList.stream().mapToLong(v -> (long) v);
		IntStream s2 = strList.stream().mapToInt(s -> s.length());
		DoubleStream s3 = intList.stream().mapToDouble(v -> (double) v);

		r.s1 = s1.boxed();
		r.s2 = s2.boxed();
		r.s3 = s3.boxed();

	}

	public void test4() throws Exception {
		/*
		Stream<T> peek(Consumer<? super T> action);
		Возвращает тот же стрим, но применяет функцию к каждому элементу стрима.
		Используется для отладки или модификации объектов. 
		 */

		r.s1 =
				intList.stream()
						.peek(s -> log2("before: " + s))
						.map(k -> k * 3)
						.peek(s -> log2("after: " + s));
	}

	public void test5() throws Exception {
		/*
		Stream	parallel()
		  Создаёт параллельный поток, позволяя выполнять операции многопоточно
		
		Stream	sequential()	
		Преобразует параллельный поток обратно в последовательный
		
		
		крайне не рекомендуется использовать параллельные стримы для сколько-нибудь долгих операций (получение данных из базы, сетевых соединений).
		Так как все параллельные стримы работают c одним пулом fork/join - такие долгие операции могут остановить работу всех параллельных стримов в JVM из-за того отсутствия доступных потоков в пуле.
		Параллельные стримы стоит использовать лишь для коротких операций, где счет идет на миллисекунды.
		
		
		 */

		//многопоточная, непоследовательная обработка
		r.s1 = rangeList.stream().parallel().peek(v -> log2(v)).map(v -> v * v);

		//последовательная обработка
		log2Splitter("sequential");
		r.s2 = rangeList.stream().peek(v -> log2(v)).map(v -> v * v);

	}

	@Override
	protected void doInitControlPanel() throws Exception {
		addTest1Button(null);
		addTest2Button(null);
		addTest3Button(null);
		addTest4Button(null);
		addTest5Button(null);
	}

	public static void main(String[] args) {
		DemoBase.run(Stream_methods_conveyor_Demo.class);
	}

}
