package ru.tet.java.util.concurrent;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

	public static void main(String[] args) throws Exception {
		demo1();
	}

	public static void demo1() throws Exception {

		int threadCount = 6;
		
		CountDownLatch startSignal = new CountDownLatch(1);
		CountDownLatch doneSignal = new CountDownLatch(threadCount);

		for (int i = 0; i < threadCount; ++i) {
			new Thread(new Worker(startSignal, doneSignal,i)).start();
		}
		System.out.println("prepare for start");
		startSignal.countDown();
		
		System.out.println("started");
		
		doneSignal.await();
		
		System.out.println("finished");

	}

}

class Worker implements Runnable {

	final CountDownLatch startSignal;
	final CountDownLatch doneSignal;
	
	int num;
	
	Worker(CountDownLatch startSignal, CountDownLatch doneSignal, int num) {
		this.num = num;
		this.startSignal = startSignal;
		this.doneSignal = doneSignal;
	}

	public void run() {
		try {
			startSignal.await();
			doWork();
			doneSignal.countDown();
		} catch (InterruptedException ex) {
		}
	}

	void doWork() { 
		System.out.format("thread %d doing some work%n",num);
	}
}
