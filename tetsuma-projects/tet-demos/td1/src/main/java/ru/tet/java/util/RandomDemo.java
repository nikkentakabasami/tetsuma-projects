package ru.tet.java.util;

import java.util.Random;
import java.util.stream.DoubleStream;

public class RandomDemo {

	public static void main(String[] args) {
		randDemo1();
	}

	public static void randDemo1() {

		Random randomNumbers = new Random();


		boolean value = randomNumbers.nextBoolean();
		System.out.println("The random boolean value is: " + value);

		System.out.println("Die Roll: " + (randomNumbers.nextInt(6) + 1));
		System.out.println("Die Roll: " + (randomNumbers.nextInt(6) + 1));
		System.out.println("Die Roll: " + (randomNumbers.nextInt(6) + 1));
		System.out.println("Die Roll: " + (randomNumbers.nextInt(6) + 1));
		System.out.println("Die Roll: " + (randomNumbers.nextInt(6) + 1));

		Long val = randomNumbers.nextLong();
		System.out.println("Random Long value: " + val);

		byte[] bytes = new byte[8];
		randomNumbers.nextBytes(bytes);

		for (int i = 0; i < bytes.length; i++) {
			System.out.printf("%02x ", bytes[i]);
		}
	}

}
