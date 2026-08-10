package ru.tet.java.util.stream;

import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import ru.tet.aux.swing.DemoBase;

public class Stream_methods_terminal_Demo extends DemoBase {

	List<Integer> intList;
	List<String> strList;
	List<Integer> rangeList;

	protected void doInit() throws Exception {
		intList = List.of(21, 2, 35, 5, 9);
		strList = List.of("yanineko", "tabako", "ski");
		rangeList = IntStream.range(1, 100).boxed().collect(Collectors.toList());

	}

	public void test1() throws Exception {
		/*
		Optional<T> findFirst();
		Возвращает первый элемент
		
		Optional<T> findAny()
		Возвращает любой подходящий элемент
		
		R collect(Collector collector);
		Представление результатов в виде коллекций и других структур данных
		
		long	count()
		
		Optional<T>	min(Comparator<T> comparator)	
		Optional<T>	max(Comparator<T> comparator)	
		
		boolean	allMatch(Predicate<T> predicate)	
		boolean	anyMatch(Predicate<T> predicate)		
		boolean	noneMatch(Predicate<T> predicate)		
		 */

		r.s1 = intList.stream().findFirst().get();
		r.s2 = intList.stream().findAny().orElse(100);
		r.s3 = intList.stream().collect(Collectors.toList());

		r.s4 = intList.stream().max(Integer::compareTo).get();
		r.s5 = strList.stream().min(String::compareTo).get();

		r.s6 = intList.stream().allMatch(k -> k > 5);
		r.s7 = intList.stream().anyMatch(k -> k > 5);
		r.s8 = intList.stream().noneMatch(k -> k > 5);

		r.s9 = intList.stream().count();

	}

	public void test2() throws Exception {
		/*
		void forEach(Consumer<? super T> action)
		Применяет функцию к каждому объекту стрима
		
		void forEachOrdered(Consumer<? super T> action)
		forEach, но гарантирует сохранение порядка элементов 
		
		
		A[] toArray(IntFunction<A[]> generator);
		Возвращает массив значений стрима
		
		T reduce(T identity, BinaryOperator<T> accumulator)
		Optional<T> reduce(BinaryOperator<T> accumulator);
		Сокращает размер коллекции до одного элемента (того же типа).
		Принимает на вход функцию accumulator с двумя параметрами: результат и текущий элемент.
		
		*/
		r.s1 = intList.stream().toArray(Integer[]::new);
		r.s2 = intList.stream().toArray();

		r.s3 = intList.stream().reduce((result, item) -> result + item).get();
		r.s4 = intList.stream().reduce(100, (result, item) -> result + item);

		log2Splitter("forEach");
		intList.stream().forEach(k -> log2(k));

		log2Splitter("forEachOrdered");
		intList.stream().forEachOrdered(k -> log2(k));
		log2Splitter();

	}


	public void test3() throws Exception {
		/*
		
		 */
	}

	@Override
	protected void doInitControlPanel() throws Exception {
		addTest1Button(null);
		addTest2Button(null);
		//addTest3Button(null);
		//		addTest4Button(null);
	}

	public static void main(String[] args) {
		DemoBase.run(Stream_methods_terminal_Demo.class);
	}

}
