package ru.tet.java.nio;

import java.nio.CharBuffer;

public class CharBufferDemo {

	public static void main(String[] args) {
		Demo();

	}

	public static void Demo() {

		String s = "We wish you a merry christmas"; // length=29

		CharBuffer buf = CharBuffer.wrap(s.toCharArray(), 3, 10); // "wish you a"

		System.out.println("capacity=" + buf.capacity()); // 29
		System.out.println("limit=" + buf.limit()); // 13
		System.out.println("position=" + buf.position()); // 3
		System.out.println("arrayOffset=" + buf.arrayOffset()); // 0

		System.out.println("get(4)=" + buf.get(4)); // i

		while (buf.hasRemaining()) {
			System.out.println(buf.position() + "=" + buf.get());
		}

		System.out.println("capacity=" + buf.capacity()); // 29
		System.out.println("limit=" + buf.limit()); // 13
		System.out.println("position=" + buf.position()); // 13

		buf.flip().limit(buf.capacity());
		while (buf.hasRemaining()) {
			System.out.println(buf.position() + "=" + buf.get());
		}
		
	}

}
