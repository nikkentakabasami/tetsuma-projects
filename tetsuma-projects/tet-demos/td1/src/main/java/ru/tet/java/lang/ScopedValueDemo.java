package ru.tet.java.lang;

import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.ThreadLocalRandom;

public class ScopedValueDemo {

	static final ScopedValue<Integer> RANDOM_NUMBER = ScopedValue.newInstance();

	public static void main(String[] args) throws InterruptedException {
//		Demo1();
		Demo3();
	}

	public static void Demo1() {

		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				int randomNumber = ThreadLocalRandom.current().nextInt(1, 101);

				ScopedValue.where(RANDOM_NUMBER, randomNumber).run(() -> {
					System.out.printf("Thread %s: Random number: %d\n", Thread.currentThread().getName(),
							RANDOM_NUMBER.get());
				});
			}).start();
		}

	}

	public static void Demo2() throws InterruptedException {

		try (StructuredTaskScope scope = new StructuredTaskScope()) {
			for (int i = 0; i < 10; i++) {
				scope.fork(() -> {
					
					int randomNumber = ThreadLocalRandom.current().nextInt(1, 101);
					ScopedValue.where(RANDOM_NUMBER, randomNumber).run(() -> {
						System.out.printf("Thread %s: Random number: %dn", Thread.currentThread().threadId(),
								RANDOM_NUMBER.get());
					});
					return null;

				});
			}
			scope.join();
		}

	}
	
	
	
	private static final ScopedValue<String> USER = ScopedValue.newInstance();
	
	public static void Demo3() throws InterruptedException {

		ScopedValue.Carrier carrierForBen = ScopedValue.where(USER, "ben");

		var boundUser = carrierForBen.get(USER);	//"ben"

	    System.out.println(boundUser);
		
		
		carrierForBen.run(() -> {
		    var currentUser = USER.get();	//"ben"
		    System.out.println(currentUser);
		});

				
		
	}
	
	
	
	
	

}
