package ru.tet.java.util.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureTaskDemo {

	public static void main(String[] args) throws Exception {
		demo1();
	}

	public static void demo1() throws Exception {

		ExecutorService executor = Executors.newFixedThreadPool(1);

		String inputData = "A cancellable asynchronous computation";

		FutureTask<String> futureTask = new FutureTask<String>(() -> {
			return inputData.toUpperCase();
		});
		executor.execute(futureTask);

		futureTask.run();
		
		System.out.println(futureTask.get());
		
	}

}
