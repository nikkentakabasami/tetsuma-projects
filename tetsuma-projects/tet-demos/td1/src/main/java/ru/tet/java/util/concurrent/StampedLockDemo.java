package ru.tet.java.util.concurrent;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.StampedLock;

public class StampedLockDemo {

//	private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

	public static final int iterationCount = 20;
	
	static volatile String message = "a";
	
	static Random randomTime;
	
	
	static final StampedLock lock = new StampedLock();

	public static void main(String[] args) throws Exception {
		demo1();
//		demo2();
	}

	public static void demo1() throws Exception {

		
		ExecutorService executor = Executors.newFixedThreadPool(5);
		
		executor.execute(new Read());
		executor.execute(new WriteA());
		executor.execute(new WriteB());
		
		executor.shutdown();
		
	}

	static void sleepRandomly(int maxLength) throws InterruptedException {
		if (randomTime==null) {
			randomTime = new Random();
		}
		int ms = randomTime.nextInt(maxLength);
		Thread.sleep(ms);
		
	}
	
	public static void demo2() throws Exception {

		
		Thread t1 = new Thread(new ReadOptimistic());
		Thread t2 = new Thread(new WriteA());
		Thread t3 = new Thread(new WriteB());
		t1.start();
		t2.start();
		t3.start();
		t1.join();
		t2.join();
		t3.join();
	}	
	

	
	
	static abstract class RunIterationRandomly implements Runnable {

		abstract void doSomething() throws InterruptedException; 
		
		public void run() {
			try {
				for (int i = 0; i <= iterationCount; i++) {
					sleepRandomly(10);
					doSomething();
				}				
			} catch (InterruptedException e) {
			}
		}
	}	
	
	
	static class Read extends RunIterationRandomly {
		@Override
		void doSomething() throws InterruptedException{
			
			long stamp = lock.readLock();
			try {
				sleepRandomly(10);
				System.out.println("readed: " + message);
			} finally {
				lock.unlockRead(stamp);
			}
		}
	}
	

	static class ReadOptimistic extends RunIterationRandomly {
		@Override
		void doSomething() throws InterruptedException{
			
			long stamp = lock.tryOptimisticRead();
			sleepRandomly(10);
			if (lock.validate(stamp)) {
				System.out.println("readed optimistically: " + message);
				return;
			}
			
			stamp = lock.readLock();
			try {
				sleepRandomly(10);
				System.out.println("readed: " + message);
			} finally {
				lock.unlockRead(stamp);
			}
			
		}
	}	
	

	
	static class WriteA extends RunIterationRandomly {
		@Override
		void doSomething() throws InterruptedException{
			
			
			long stamp = lock.writeLock();
			try {
				sleepRandomly(10);
				message = message.concat("a");
			} finally {
				lock.unlockWrite(stamp);
			}
		}
	}
	
	static class WriteB extends RunIterationRandomly {
		@Override
		void doSomething() throws InterruptedException{
			
			
			long stamp = lock.writeLock();
			try {
				sleepRandomly(10);
				message = message.concat("b");
			} finally {
				lock.unlockWrite(stamp);
			}
		}
	}	
	
	
	
	
	
}
