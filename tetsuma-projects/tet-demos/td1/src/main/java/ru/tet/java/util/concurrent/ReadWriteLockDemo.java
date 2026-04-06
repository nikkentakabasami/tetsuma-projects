package ru.tet.java.util.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {

	private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

	private static String message = "a";

	public static void main(String[] args) throws Exception {
		demo1();
	}

	public static void demo1() throws Exception {

		ExecutorService executor = Executors.newFixedThreadPool(5);
		
		executor.execute(new Read());
		executor.execute(new WriteA());
		executor.execute(new WriteB());
		
		executor.shutdown();

	}

	static class Read implements Runnable {

		public void run() {
			for (int i = 0; i <= 10; i++) {
				if (lock.isWriteLocked()) {
					System.out.println("I'll take the lock from Write");
				}
				lock.readLock().lock();

				System.out.println("readed: " + message);

				lock.readLock().unlock();

			}
		}
	}

	static class WriteA implements Runnable {

		public void run() {
			for (int i = 0; i <= 10; i++) {

				try {
					lock.writeLock().lock();
					message = message.concat("a");

				} finally {
					lock.writeLock().unlock();
				}

			}
		}
	}

	static class WriteB implements Runnable {

		public void run() {
			for (int i = 0; i <= 10; i++) {
				try {
					lock.writeLock().lock();
					message = message.concat("b");

				} finally {
					lock.writeLock().unlock();
				}

			}
		}
	}

}
