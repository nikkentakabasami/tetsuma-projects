package ru.tet.syntax.datatypes.nio;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.stream.IntStream;

import ru.tet.aux.swing.DemoBase;

public class D_ByteBuffer2 extends DemoBase {

	ByteBuffer bb1, bb2;

	public void test1() throws Exception {
		/*
		ByteBuffer
		считывание данных через относительные методы.
		 */
		ByteBuffer bb1 = ByteBuffer.wrap("hello!".getBytes());

		logEval1(
				Arrays.toString(bb1.array()),

				bb1.get(4),
				bb1.position(),
				bb1.limit(),
				bb1.capacity());

		while (bb1.hasRemaining()) {
			log2("[" + bb1.position() + "]: " + bb1.get() + " rem:" + bb1.remaining());
		}
		
		logExpr1(() -> {
			
			//считывание 4х байтов в другой байтовый буфер
			bb1.rewind();
			bb1.limit(4);
			
			//Если размер bb2 будет недостаточен - кинет ошибку
			ByteBuffer bb2 = ByteBuffer.allocate(10);
			bb2.put(bb1);
			bb2.flip();
			
			String bb5String = new String(bb2.array(), bb2.position(), bb2.remaining());
			
			return bb5String;
		});

		
		
		
		
	}

	public void test2() throws Exception {
		/*
		Buffer compact()
		Подготавливает буфер для записи, без потери непрочитанных данных.
		Записывает все непрочитанные данные (между position и limit) в начало массива.
		position задаётся в конце этих данных.
		limit:=capacity
		mark:=0;
		
		 */

		ByteBuffer bb1 = ByteBuffer.allocate(10);

		//записываем данные
		IntStream.range(2, 9).forEach(v -> {
			bb1.put((byte) v);
		});

		//считываем 3 элемента
		bb1.flip();
		log2(bb1.get(), bb1.get(), bb1.get()); //2 3 4

		logEval(
				Arrays.toString(bb1.array()), //[2, 3, 4, 5, 6, 7, 8, 0, 0, 0]
				bb1.position(), //3
				bb1.limit() //7
		);

		//сдвигаем несчитанные данные
		bb1.compact();

		log2("after compact()");
		logEval(
				Arrays.toString(bb1.array()), //[5, 6, 7, 8, 6, 7, 8, 0, 0, 0]
				bb1.position(), //4
				bb1.limit() //10
		);

		//дописываем 2 элемента
		bb1.put((byte) 22);
		bb1.put((byte) 23);

		//выводим данные
		bb1.flip();
		while (bb1.hasRemaining()) {
			log2(bb1.get());
		}
		//5 6 7 8 22 23

	}

	
	String bbInfo(ByteBuffer bb) {
		return Arrays.toString(bb.array())+" position="+bb.position()+", limit="+bb.limit();
	}
	
	public void test3() throws Exception {
		/*
		
		 */
		
		ByteBuffer bb1 = ByteBuffer.allocate(10);
		
		logExpr1(() -> {
			//записываем данные
			IntStream.range(1, 9).forEach(v -> {
				bb1.put((byte) v);
			});
			return bbInfo(bb1);
		}, () -> {
			bb1.flip();
			return bbInfo(bb1);
		}, () -> {
			//считываем 3 элемента
			log2(bb1.get(), bb1.get(), bb1.get());
			return bbInfo(bb1);
		}, () -> {
			bb1.clear();
			return bbInfo(bb1);
		}, () -> {
			bb1.put((byte) 22);
			bb1.put((byte) 23);
			return bbInfo(bb1);
		}, () -> {
			bb1.mark();
			return bbInfo(bb1);
		}, () -> {
			bb1.put((byte) 77);
			bb1.put((byte) 23);
			return bbInfo(bb1);
		}, () -> {
			return bbInfo(bb1);
		});		
		
	}

	public static void main(String[] args) {
		DemoBase.run(D_ByteBuffer2.class);
	}

}
