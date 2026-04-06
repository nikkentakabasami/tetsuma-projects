package ru.tet.java.util.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {

	public static void main(String[] args) throws Exception {
		demo1();
	}

	public static void demo1() throws Exception {

		final BoundedBuffer buffer = new BoundedBuffer();
		
		ExecutorService executor = Executors.newFixedThreadPool(5);
		
		executor.execute(()->{
			try {
				for (int i = 0; i < 50; i++) {
					buffer.put(i);
					System.out.println("putted "+i);
				}
			} catch (InterruptedException e) {
			}
		});
		
		executor.execute(()->{
			try {
				for (int i = 100; i < 150; i++) {
					buffer.put(i);
					System.out.println("putted "+i);
				}
			} catch (InterruptedException e) {
			}
		});
		
		executor.execute(()->{
			try {
				for (int i = 100; i < 150; i++) {
					
					int n = buffer.take();
					System.out.println("taken "+n);
				}
			} catch (InterruptedException e) {
			}
		});

		
		executor.shutdown();
		
		
	}

	//буфер
	static class BoundedBuffer {
		final Lock lock = new ReentrantLock();
		final Condition notFull = lock.newCondition();
		final Condition notEmpty = lock.newCondition();

		final int[] items = new int[10];
		int putIndex = 0;
		int takeIndex = 0;
		int count = 0;

		public int getCount() {
			return count;
		}
		
		public boolean isFull() {
			return count == items.length;
		}

		public boolean isEmpty() {
			return count == 0;
		}
		
		
		public void put(int x) throws InterruptedException {
			lock.lock();
			try {
				while (isFull()) {
					System.out.println("buffer is full - waiting take");
					notFull.await();
				}
				items[putIndex] = x;
				if (++putIndex == items.length) {
					putIndex = 0;
				}
				++count;
				notEmpty.signal();
			} finally {
				lock.unlock();
			}
		}

		public int take() throws InterruptedException {
			lock.lock();
			try {
				while (isEmpty()) {
					System.out.println("buffer empty - waiting put");
					notEmpty.await();
				}
				int x = items[takeIndex];
				if (++takeIndex == items.length) {
					takeIndex = 0;
				}
				--count;
				notFull.signal();
				return x;
			} finally {
				lock.unlock();
			}
		}
	}

}
