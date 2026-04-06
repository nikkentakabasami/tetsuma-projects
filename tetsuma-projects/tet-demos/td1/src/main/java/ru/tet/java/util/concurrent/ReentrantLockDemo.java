package ru.tet.java.util.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

	public static void main(String[] args) throws Exception {
		demo1();
	}

	public static void demo1() throws Exception {

		final SharedObjectWithLock so = new SharedObjectWithLock();

		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(new Performer("t1", so));
		executor.execute(new Performer("t2", so));

		executor.close();
		executor.shutdown();
//		executor.awaitTermination(1, TimeUnit.MINUTES);
	}

}

class Performer implements Runnable {

	String name;
	SharedObjectWithLock so;

	public Performer(String name, SharedObjectWithLock so) {
		this.name = name;
		this.so = so;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			so.perform(name);
		}
	}

}

class SharedObjectWithLock {

	ReentrantLock lock = new ReentrantLock(true);
	int counter = 0;

	public void perform(String t) {
		lock.lock();
		try {
			Thread.currentThread().sleep(10);
			System.out.println(t + " performs with " + counter);
			counter++;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

}