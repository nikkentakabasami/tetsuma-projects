package ru.tet.java.util.concurrent;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicDemo {

	public static void main(String[] args) throws Exception {
		demo1();
	}

	public static void demo1() throws Exception {

		AtomicLong al = new AtomicLong(100);
		System.out.println(al.get());
		System.out.println(al.getAndAdd(7));
		System.out.println(al.decrementAndGet());
		

		
		
	}

}
