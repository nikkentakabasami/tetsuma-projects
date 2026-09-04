package ru.tet.syntax.datatypes.nio;

import java.nio.CharBuffer;

import ru.tet.aux.swing.DemoBase;

public class D_CharBuffer extends DemoBase {

	public void test1() throws Exception {

	}

	public void test2() throws Exception {

		log2("cb1");
		CharBuffer cb1 = CharBuffer.wrap("hello!".toCharArray());
		while (cb1.hasRemaining()) {
			log2("[" + cb1.position() + "]: " + cb1.get() + " rem:" + cb1.remaining());
		}

		//		ReadableByteChannel channel = Channels.newChannel(inputStream);
		//		ByteBuffer buffer = ByteBuffer.allocate(1024);
		//		int bytesRead = channel.read(buffer);		

	}

	public void test3() throws Exception {
		/*
		
		 */

		//  CharBuffer cb1 = CharBuffer.wrap("Пример текста");
		CharBuffer cb1 = CharBuffer.allocate(80);
		cb1.append("333");
		cb1.append("+555");
		cb1.put("=888");
		cb1.flip();
		log2(cb1.toString());

		CharBuffer cb2 = CharBuffer.allocate(80);

		cb1.read(cb2);
		cb2.flip();
		log2(cb2.toString());

	}

	public void test4() throws Exception {
		/*
		
		 */
		
		String s = "We wish you a merry christmas"; // length=29

//		CharBuffer buf = CharBuffer.wrap(s.toCharArray(), 3, 10); // "wish you a"
		CharBuffer buf = CharBuffer.wrap(s, 3, 10); // "wish you a"

		logEval(
				buf.position(),	//3
				buf.limit(),	//13
				buf.capacity(),	//29
//				buf.arrayOffset(),	//0
				buf.get(4)	//i
		);
 
		while (buf.hasRemaining()) {
			log2(buf.position(), "=", buf.get());
		}

		log2("after read");
		
		logEval(
				buf.position(),	//13
				buf.limit()	//13
		);
		
		log2("after flip");
		
		//для считывания всего массива
		buf.rewind().limit(buf.capacity());
		
		logEval(
				buf.position(),	//0
				buf.limit()	//29
		);
		
		while (buf.hasRemaining()) {
			log2(buf.position(), "=", buf.get());
		}
		
	}

	public static void main(String[] args) {
		DemoBase.run(D_CharBuffer.class);
	}

}
