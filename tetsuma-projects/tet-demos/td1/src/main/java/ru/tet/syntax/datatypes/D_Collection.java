package ru.tet.syntax.datatypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.google.common.collect.Lists;

import ru.tet.aux.swing.DemoBase;

public class D_Collection extends DemoBase {



	public void test1() throws Exception {
		/*
		Iterator
		 */

		List<String> l1 = Lists.newArrayList("1", "2", "3", "4", "5");
		Iterator<String> it1 = l1.iterator();
		while (it1.hasNext()) {
			String e = it1.next();
			if ("3".equals(e) || "5".equals(e)) {
				it1.remove();
			}
		}
		log2(l1);

		//		log2(l1.stream().collect(Collectors.joining(",")));

	}

	public void test2() throws Exception {
		/*
		ListIterator (extends Iterator)
		Добавляет методы для итерации назад, получения индексов
		Курсор как бы находится между элементами
		 */

		List<String> l1 = Lists.newArrayList("1", "2", "3", "4", "5");
		ListIterator<String> it1 = l1.listIterator();

		logEval1(
				it1.hasPrevious(),
				it1.next(),
				it1.next(),
				it1.previous(),
				it1.nextIndex(),
				it1.next(),
				it1.next());

		logExpr1(() -> {
			it1.set("S1");
			return null;
		});

		logEval2(
				it1.next());

		logExpr2(() -> {
			it1.add("S2");
			return null;
		});

		logEval3(
				it1.previous(),
				it1.next(),
				it1.next());

		log2(l1);

	}

	public void test3() throws Exception {
		/*
		
		 */
		List<Integer> l1 = IntStream.range(1, 10).boxed().toList();
		log2(l1);

		l1.removeIf(v -> v > 5);
		log2(l1);

		Object[] arr1 = l1.toArray();
		Integer[] arr2 = l1.toArray(Integer[]::new);
		Integer[] arr3 = l1.toArray(new Integer[0]);

		logEval1(
				arr1,
				arr2,
				arr3);
		
		List<Integer> l3 = Collections.emptyList();
		
		Map<Integer, String> emptyMap = Collections.emptyMap();
		Set<Integer> s1 = Collections.emptySet();
		List<Integer> l4 = Arrays.asList(7, 4, 5);
		

	}

	public void test4() throws Exception {
		/*
		Spliterator
		итератор, который умеет не только перебирать элементы коллекции, но и делить (split) её на части для параллельной обработки.
		 */

		List<Integer> l1 = IntStream.range(1, 10).boxed().toList();

		Spliterator<Integer> sp1 = l1.spliterator();
		Spliterator<Integer> sp2 = sp1.trySplit();

		List<Integer> l2 = new ArrayList<>();
		List<Integer> l3 = new ArrayList<>();
		sp1.forEachRemaining(l2::add);
		sp2.forEachRemaining(l3::add);

		logEval1(
				l2,
				l3);

		l2.clear();
		sp1 = l1.spliterator();
		while (sp1.tryAdvance(v -> l2.add(v)))
			;
		logEval2(
				l2);

	}

	@Override
	protected void doInit() throws Exception {
		options().hlComments = false;
	}

	public static void main(String[] args) {
		DemoBase.run(D_Collection.class);
	}

}
