package ru.tet.java.util.concurrent;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

import lombok.SneakyThrows;

public class ForkJoinPoolDemo {

	public static void main(String[] args) throws Exception {
//		demo1();
		demo2();
	}

	public static void demo1() throws Exception {

		int[] array = new int[10000];
		for (int i = 0; i < array.length; i++) {
			array[i] = 3;
		}

		SumCounterTask counter = new SumCounterTask(array);
		System.out.println(new Date());
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		System.out.println(forkJoinPool.invoke(counter));
		System.out.println(new Date());

		forkJoinPool.close();

	}

	public static void demo2() throws Exception {

		String stringToProcess = "To help you understand how the fork/join framework works, consider the following example";
		System.out.println("input: " + stringToProcess);

		char[] charArray = stringToProcess.toCharArray();

		UpperCaseAction task = new UpperCaseAction(charArray, 0, charArray.length);

//		однопоточный режи
//		task.compute();
		
//		ForkJoinPool forkJoinPool = new ForkJoinPool();
		
//		ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
		
		int parallelism = ForkJoinPool.getCommonPoolParallelism();
		ForkJoinPool forkJoinPool = (ForkJoinPool) Executors.newWorkStealingPool(parallelism);		
		
		
		forkJoinPool.invoke(task);

//		forkJoinPool.execute(task);
//		task.join();		

		forkJoinPool.close();

		String result = new String(charArray);
		System.out.println(result);

	}

}

class SumCounterTask extends RecursiveTask<Integer> {

	private int[] array;

	public SumCounterTask(int[] array) {
		this.array = array;
	}

	@SneakyThrows
	@Override
	protected Integer compute() {
		if (array.length <= 2) {
			System.out.printf("Task %s execute in thread %s%n", this, Thread.currentThread().getName());
			Thread.sleep(1);
			return Arrays.stream(array).sum();
		}
		SumCounterTask firstHalfArrayValueSumCounter = new SumCounterTask(
				Arrays.copyOfRange(array, 0, array.length / 2));
		SumCounterTask secondHalfArrayValueSumCounter = new SumCounterTask(
				Arrays.copyOfRange(array, array.length / 2, array.length));
		firstHalfArrayValueSumCounter.fork();
		secondHalfArrayValueSumCounter.fork();
		return firstHalfArrayValueSumCounter.join() + secondHalfArrayValueSumCounter.join();
	}
}

class UpperCaseAction extends RecursiveAction {

	private static final int THRESHOLD = 4;

	char[] charArray;
	int processFrom;
	int processLength;

	public UpperCaseAction(char[] charArray, int from, int length) {
		this.charArray = charArray;
		this.processFrom = from;
		this.processLength = length;
	}

	@Override
	protected void compute() {
		if (processLength > THRESHOLD) {
			int newLength = processLength / 2;
			UpperCaseAction a1 = new UpperCaseAction(charArray, processFrom, newLength);
			UpperCaseAction a2 = new UpperCaseAction(charArray, processFrom + newLength,
					processLength - newLength);
			a1.fork();
			a2.fork();
			
//			ForkJoinTask.invokeAll(a1, a2);
		} else {
			processing();
		}
	}

	private void processing() {

		int end = processFrom + processLength;
		for (int i = processFrom; i < end; i++) {
			charArray[i] = Character.toUpperCase(charArray[i]);
		}
		System.out.println("processing with thread " + Thread.currentThread().getName());
	}
}
