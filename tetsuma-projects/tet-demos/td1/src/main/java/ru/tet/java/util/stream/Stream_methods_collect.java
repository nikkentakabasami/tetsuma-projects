package ru.tet.java.util.stream;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.LinkedList;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ru.tet.aux.swing.DemoBase;
import ru.tet.beans.Employee;
import ru.tet.data.BeansSamples;

public class Stream_methods_collect extends DemoBase {

	List<Integer> intList;
	List<String> strList;
	List<Integer> rangeList;
	List<Employee> employeeList;

	protected void doInit() throws Exception {
		intList = List.of(21, 2, 35, 5, 9);
		strList = List.of("yanineko", "tabako", "ski", "yakuneko", "yakuso", "ski");
		rangeList = IntStream.range(1, 100).boxed().collect(Collectors.toList());
		employeeList = BeansSamples.createEmployeeList();

	}

	public void test1() throws Exception {
		/*
		R collect(Collector collector);
		Представление результатов в виде коллекций и других структур данных
		
		Коллекторы:
		
		Collectors.toCollection(Supplier<C> collectionFactory)
		Collectors.toList()
		Collectors.toSet()
		Преобразование в коллекцию
		
		
		Преобразование в коллекцию
		Collectors.toMap(keyMapper,valueMapper)
		Collectors.toMap(keyMapper,valueMapper)
		Collectors.toConcurrentMap(keyMapper,valueMapper, mergeFunction)
		
		mergeFunction - разрешает конфликты дублирующегося ключа
		
		 */

		LinkedList<String> s1 = strList.stream().collect(Collectors.toCollection(LinkedList::new));
		Set<Integer> s2 = intList.stream().collect(Collectors.toCollection(TreeSet::new));

		List<String> s3 = strList.stream().collect(Collectors.toList());
		Set<String> s4 = strList.stream().collect(Collectors.toSet());

		Map<String, Integer> s5 =
				strList.stream().collect(
						Collectors.toMap(p -> p,
								p -> p.length(),
								(existing, replacement) -> existing + 100));

		//Задаём конструктор Map-а 
		TreeMap<String, String> s6 = employeeList.stream().collect(
        Collectors.toMap(Employee::getDepartment, Employee::getFirstName, (o1, o2) -> o1  , TreeMap::new));		
		
		
		//ConcurrentMap используется в случае многопоточного доступа к данным 
		ConcurrentMap<String, Integer> s7 =
				strList.stream().distinct().collect(Collectors.toConcurrentMap(p -> p, String::length));

		
		
		r.set(s1, s2, s3, s4, s5, s6, s7);

	}

	public void test2() throws Exception {
		/*
		Collectors.averagingInt(ToIntFunction mapper)
		Collectors.averagingDouble(ToDoubleFunction mapper)
		Collectors.averagingLong(ToLongFunction mapper)
		Выделяет из объектов потока числовые значения и вычисляет их среднее значение
		
		
		Collectors.summingInt(ToIntFunction mapper)
		Collectors.summingDouble(ToDoubleFunction mapper)
		Collectors.summingLong(ToLongFunction mapper)
		Выделяет из объектов потока числовые значения и вычисляет их сумму
		
		
		Collectors.summarizingInt(ToIntFunction mapper)
		Collectors.summarizingDouble(ToDoubleFunction mapper)
		Collectors.summarizingLong(ToLongFunction mapper)
		Общая статистика (min, max, average, count, sum)
		
		 */

		Double s1 = intList.stream().collect(Collectors.averagingInt(Integer::intValue));
		Double s2 = intList.stream().collect(Collectors.averagingDouble(Integer::doubleValue));
		Double s3 = intList.stream().collect(Collectors.averagingLong(Integer::longValue));

		Integer s4 = intList.stream().collect(Collectors.summingInt(Integer::intValue));
		Double s5 = intList.stream().collect(Collectors.summingDouble(Integer::doubleValue));
		Long s6 = intList.stream().collect(Collectors.summingLong(Integer::longValue));

		IntSummaryStatistics s7 = intList.stream().collect(Collectors.summarizingInt(Integer::intValue));
		DoubleSummaryStatistics s8 = intList.stream().collect(Collectors.summarizingDouble(Integer::doubleValue));
		LongSummaryStatistics s9 = intList.stream().collect(Collectors.summarizingLong(Integer::longValue));

		r.set(s1, s2, s3, s4, s5, s6, s7, s8, s9);

	}

	public void test3() throws Exception {
		/*
		
		Collectors.partitioningBy(Predicate predicate)
		Разбивает поток на 2 списка по соответствию условию и возвращает их как Map<Boolean, List>
		
		
		Collectors.groupingBy(Function classifier)
		группирует коллекцию, разбивая её на несколько частей. Возвращает Map<G, List>
		
		Collectors.joining(delimiter,prefix,suffix)
		Склеивает поток из строк в одну строку.
		Можно задать разделитель, префикс, постфикс.
		
		Collectors.mapping(Function mapper, Collector downstream)
		Выполняет дополнительные преобразования элементов, прежде чем передать поток коллектору downstream.
		Обычно используется вместе с groupingBy(), partitioningBy()
		
		 */

		Map<Boolean, List<Integer>> parts = intList.stream().collect(Collectors.partitioningBy(n -> n % 2 == 0));

		Map<String, List<String>> grouped = strList.stream().collect(Collectors.groupingBy(s -> s.substring(0, 1)));

		String s3 = strList.stream().collect(Collectors.joining());
		String s4 = strList.stream().collect(Collectors.joining(","));
		String s5 = strList.stream().collect(Collectors.joining(";", "(", ")"));

		//получение файла в виде строки
		String s6 = Files.lines(Paths.get("NOTICE.txt")).collect(Collectors.joining("\n"));

		List<String> s7 =
				employeeList.stream()
						.collect(Collectors.mapping(Employee::getFirstName, Collectors.toList()));

		//сгруппировать по департаменту, и вернуть только имена
		Map<String, List<String>> s8 =
				employeeList.stream()
						.collect(Collectors.groupingBy(
								Employee::getDepartment,
								Collectors.mapping(Employee::getFirstName, Collectors.toList())));

		r.set(parts, grouped, s3, s4, s5, s6, s7, s8);

	}

	public void test4() throws Exception {
		/*
		Collector.of(метод_инициализации_аккумулятора,
		         метод_обработки_каждого_элемента,
		         метод_соединения_двух_аккумуляторов,
		         [метод_последней_обработки_аккумулятора] );
		Позволяет создать собственный коллектор.
		 */

		//Колектор для объединения элементов в строку
		String s1 =
				strList.stream().collect(
						Collector.of(
								StringBuilder::new, // метод_инициализации_аккумулятора
								(b, s) -> b.append(s).append(" , "), // метод_обработки_каждого_элемента,
								(b1, b2) -> b1.append(b2).append(" , "), // метод_соединения_двух_аккумуляторов
								StringBuilder::toString // метод_последней_обработки_аккумулятора
						));
		
		r.s1 = s1;
		

	}

	@Override
	protected void doInitControlPanel() throws Exception {
		addTest1Button(null);
		addTest2Button(null);
		addTest3Button(null);
		//		addTest4Button(null);
	}

	public static void main(String[] args) {
		DemoBase.run(Stream_methods_collect.class);
	}

}
