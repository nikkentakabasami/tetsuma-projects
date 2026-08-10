package ru.tet.java.util.stream;

import java.util.Random;
import java.util.stream.IntStream;

import ru.tet.aux.swing.DemoBase;

public class IntStreamDemo extends DemoBase {


	public void test1() throws Exception {

		/*
		Способы создания IntStream:
		
		static IntStream	empty()	
		static IntStream	of(int t)	
		static IntStream	of(int... values)	
		 */

		r.s1 = IntStream.empty().toArray();
		r.s2 = IntStream.of(55).toArray();
		r.s3 = IntStream.of(5, 7, 11, 13, 33).toArray();

		/*
		static IntStream	iterate(int seed, IntUnaryOperator f)
		создаёт бесконечный IntStream, путём повторяющегося применения функции f к начальному значению seed.
		Поток вида:
		seed, f(seed), f(f(seed))...
		 */

		r.s4 = IntStream.iterate(0, i -> i + 2).limit(7).toArray();

		/*
		static IntStream	range(int startInclusive, int endExclusive)	
		static IntStream	rangeClosed(int startInclusive, int endInclusive)	
		Создаёт последовательность чисел с шагом 1
		 */
		r.s5 = IntStream.range(5, 11).toArray();
		r.s6 = IntStream.rangeClosed(5, 11).toArray();

	}

	public void test2() throws Exception {

		/*
		Прочие способы создания
		
		IntStream random.ints(long streamSize, int min, int max)
		Поток случайных чисел (с диапазоном min..max)
		
		
		 */

		r.s1 = new Random(1).ints(20, 100, 200).toArray();		
		

	}

	public void test3() throws Exception {
		/*
		Вычисления
		
		
		long	count()
		int	sum()			
		
		OptionalDouble	average()
		OptionalInt	findAny()	
		OptionalInt	findFirst()		
		OptionalInt	max()
		OptionalInt	min()
		
		OptionalInt	reduce(IntBinaryOperator op)		
		int	reduce(int identity, IntBinaryOperator op)		
		 */

		r.s1 = IntStream.of(5, 7, 11, 7).count();
		r.s2 = IntStream.of(5, 7, 11, 7).sum();
		r.s3 = IntStream.of(5, 7, 11, 7).average().getAsDouble();

		r.s4 = IntStream.of(5, 7, 11, 7).findAny().getAsInt();
		r.s5 = IntStream.of(5, 7, 11, 7).findFirst().getAsInt();
		r.s6 = IntStream.of(5, 7, 11, 7).max().getAsInt();
		r.s7 = IntStream.of(5, 7, 11, 7).min().getAsInt();

		r.s8 = IntStream.of(5, 7, 11, 7).reduce(100, (a, b) -> a + b);
		r.s9 = IntStream.of(5, 7, 11, 7).reduce((a, b) -> a + b).getAsInt();

	}

	public void test4() throws Exception {
		/*
		boolean	allMatch(IntPredicate predicate)	
		boolean	anyMatch(IntPredicate predicate)		
		boolean	noneMatch(IntPredicate predicate)		
		 */
		r.s1 = IntStream.of(5, 7, 11, 7).allMatch(k -> k > 5);
		r.s2 = IntStream.of(5, 7, 11, 7).anyMatch(k -> k > 5);
		r.s3 = IntStream.of(5, 7, 11, 7).noneMatch(k -> k > 5);

	}

	@Override
	protected void doInitControlPanel() throws Exception {
		addTest1Button(null);
		addTest2Button(null);
		addTest3Button(null);
		addTest4Button(null);
	}

	public static void main(String[] args) {
		DemoBase.run(IntStreamDemo.class);
	}

}
