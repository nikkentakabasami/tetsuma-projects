package ru.tet.java.util.stream;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ru.tet.aux.swing.DemoBase;

public class IntStreamDemo2 extends DemoBase {

	public void test1() throws Exception {
		/*
		преобразования массива
		
		IntStream	distinct()		
		IntStream	skip(long n)	
		IntStream	limit(long maxSize)	
		IntStream	filter(IntPredicate predicate)		
		IntStream	map(IntUnaryOperator mapper)
		IntStream	sorted()		
		 */

		r.s1 = IntStream.of(5, 7, 11, 7, 13).distinct().toArray();
		r.s2 = IntStream.iterate(2, i -> i * 2).skip(5).limit(7).toArray();
		r.s3 = IntStream.of(5, 7, 11, 7, 13).filter(k -> k > 7).toArray();
		r.s4 = IntStream.of(5, 7, 11, 7, 13).map(k -> k * 3).toArray();
		r.s5 = IntStream.of(5, 7, 11, 7, 13).map(k -> k * 3).sorted().toArray();

	}

	public void test2() throws Exception {
		/*
		IntStream	parallel()
		  Создаёт параллельный поток, позволяя выполнять операции многопоточно
		
		IntStream	sequential()	
		Преобразует параллельный поток обратно в последовательный
		
		
		 */

		IntStream.of(5, 7, 11, 7, 13).parallel().toArray();

		r.s1 =
				IntStream.rangeClosed(1, 1_000_000)
						.parallel()
						.mapToLong(i -> (long) i * i)
						.sum();

	}

	public void test3() throws Exception {
		/*
		IntStream	peek(IntConsumer action)
		Метод для отладки, чтобы глянуть содержимое потока между операциями.
		 */
		r.s1 =
				IntStream.of(5, 7, 11, 7, 13)
						.peek(s -> log2("before: " + s))
						.map(k -> k * 3)
						.peek(s -> log2("after: " + s))
						.toArray();

	}

	public void test4() throws Exception {
		/*
		конвертации
		
		DoubleStream	asDoubleStream()	
		LongStream	asLongStream()	
		
		Stream<Integer>	boxed()
		
		Stream<U>	mapToObj(IntFunction mapper)		
		
		 */

		r.s1 = IntStream.of(5, 7, 11, 13).asDoubleStream().toArray();
		r.s2 = IntStream.of(5, 7, 11, 13).asLongStream().toArray();
		r.s3 = IntStream.of(5, 7, 11, 13).boxed().collect(Collectors.toList());
		r.s9 = IntStream.of(5, 7, 11, 7, 13).mapToObj(String::valueOf).collect(Collectors.joining(":", "(", ")"));
	}

	@Override
	protected void doInitControlPanel() throws Exception {
		addTest1Button(null);
		addTest2Button(null);
		addTest3Button(null);
		addTest4Button(null);
	}

	public static void main(String[] args) {
		DemoBase.run(IntStreamDemo2.class);
	}

}
