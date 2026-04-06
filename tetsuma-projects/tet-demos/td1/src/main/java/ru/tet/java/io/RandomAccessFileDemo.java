package ru.tet.java.io;

import java.io.RandomAccessFile;

public class RandomAccessFileDemo {

	public static void main(String[] args) throws Exception {
		
		RandomAccessFile raf = new RandomAccessFile("target/example.dat", "rw");
		raf.writeInt(12345);
		raf.writeUTF("Hello");
		raf.close();
		
		
		raf = new RandomAccessFile("target/example.dat", "rw");
		raf.seek(0);

		int number = raf.readInt();
		System.out.println("Number: " + number);
		String str = raf.readUTF();
		System.out.println("String: " + str);

		long length = raf.length();
		System.out.println("File length: " + length);

		raf.close();
	}

}
