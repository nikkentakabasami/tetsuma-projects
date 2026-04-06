package ru.tet.java.util.stream;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo1 {

	public static void main(String[] args) {
		randomStreamDemo();
		dequeDemo();
		treeSetDemo();

	}

	public static void randomStreamDemo() {
		System.out.println("\nRandom number stream:");
		Random r = new Random();
		Stream.generate(() -> r.nextInt(1000)).limit(10).sorted(Comparator.reverseOrder())
				.forEach(e -> System.out.println(e));

	}

	public static void dequeDemo() {
		System.out.print("\ndeque:");
		Deque<String> deque = new ArrayDeque<>();
		deque.offer("asdf"); // в конец очереди
		deque.add("Apple"); // в конец очереди
		deque.addFirst("Orange"); // Добавляет элемент Orange в начало очереди
		deque.addLast("Pineapple"); // Добавляет элемент Pineapple в конец очереди

		System.out.println(deque);

	}

	public static void treeSetDemo() {

		TreeSet<Integer> treeSet = new TreeSet<>();

		treeSet.add(55);
		treeSet.add(23);
		treeSet.add(11);
		treeSet.add(582);
		treeSet.add(352);
		treeSet.add(757);

		String result = treeSet.stream().map(e -> String.valueOf(e)).collect(Collectors.joining(";"));
		System.out.println("\ntreeSet: "+result);
		
		Set<Integer> subSet = treeSet.subSet(23, 582);

		System.out.print("\nsubSet: ");
		result = subSet.stream().map(e -> String.valueOf(e)).collect(Collectors.joining(";"));
		System.out.println(result);

		System.out.println("ceiling(50)="+treeSet.ceiling(50));
		System.out.println("floor(500)="+treeSet.floor(500));

		
		Set<String> treeSet2 = new TreeSet<>(Comparator.comparing(String::length));
		treeSet2.addAll(Arrays.asList("Forth", "First", "Third", "Second", "And", "s", "illumination"));

		result = treeSet2.stream().collect(Collectors.joining(";"));
		System.out.println("\ntreeSet2: "+result);
		
		
		
	}

	/*
	 * 
	 * public static void Demo() { }
	 * 
	 * public static void Demo() { }
	 */

}
