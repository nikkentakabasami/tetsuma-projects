package ru.tet.syntax.datatypes.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.IntStream;

import ru.tet.aux.swing.DemoBase;

public class D_Buffer extends DemoBase {

	void createTestFile(Path p) throws IOException {
		if (Files.notExists(p)) {
			Files.createFile(p);
			Files.writeString(p, "fujori dake no kishoutenketsu");
		}
	}

	public void test1() throws Exception {
		/*
		Создание
		
		
		ByteBuffer.allocate(int capacity)
		Буфер будет заполнен нулями!
		
		ByteBuffer.allocateDirect(int capacity) 	
		Создание прямого буфера (будет храниться в памяти ОС).
		
		
		ByteBuffer.wrap(byte[] array)
		ByteBuffer.wrap(byte[] array, int offset, int length) 	
		Создание оборачиванием массива
		В этом случае (position:=offset;limit:=offset+length;capacity:=array.length)
		
		ByteBuffer bb.slice();
		Создание подмножества буфера bb.
		Оно создаётся через задание offset-а.
		offset:=bb.position
		position:=0
		limit:=capacity:=bb.remaining()
		Базовый массив будет тот же!  
		
		MappedByteBuffer fileChannel.map(MapMode mode, long position, long size)
		Проецирует кусок файла в буфер
		
		ByteBuffer charset.encode(String str)
		Создание на основе строки
		 */

		ByteBuffer bb1 = ByteBuffer.allocate(48);
		ByteBuffer bb2 = ByteBuffer.allocateDirect(48);


		byte[] bytes = "clean biosphere".getBytes(); //для латиницы UTF-8 использует 1 байт на символ
		ByteBuffer bb4 = ByteBuffer.wrap(bytes);
		ByteBuffer bb5 = ByteBuffer.wrap(bytes, 4, 5);

		ByteBuffer bb5_slice = bb5.slice();

		String bb5String = new String(bb5.array(), bb5.position(), bb5.remaining());

		Charset charset = StandardCharsets.UTF_8;
		ByteBuffer bb6 = charset.encode("Некоторая строка");

    CharsetEncoder encoder = charset.newEncoder();
    CharBuffer cb1 = CharBuffer.wrap("Пример текста");
    ByteBuffer bb7 = encoder.encode(cb1);
		
		Path p1 = Path.of("target/testbb.txt");
		createTestFile(p1);
		FileInputStream is = new FileInputStream(p1.toFile());
		FileChannel fc = is.getChannel();
		MappedByteBuffer bbf = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
		is.close();

		logEval(
				bb1.capacity(),
				bb1.limit(),
				bb1.position(),

				bb1.hasArray(),
				bb1.hasRemaining(),
				bb1.remaining(),
				bb1.arrayOffset(),
				bb1.isReadOnly(),
				bb1.isDirect());

		log2Splitter();

		logEval(
				bb5String,

				//возвращает оригинальный массив
				Arrays.toString(bb5.array()),

				//задаётся индекс для массива!
				bb5.get(0),
				bb5.getChar(0),

				bb5.capacity(),
				bb5.limit(),
				bb5.position(),

				bb5.hasArray(),
				bb5.hasRemaining(),
				bb5.remaining(),
				bb5.arrayOffset(),
				bb5.isReadOnly(),
				bb5.isDirect(),

				bb5.get(),
				bb5.remaining(),

				Arrays.toString(bb5_slice.array()),

				bb5_slice.arrayOffset(),
				bb5_slice.position(),
				bb5_slice.limit(),
				bb5_slice.remaining(),
				bb5_slice.get(0)

		);

		log2Splitter();

		logEval(
				bbf.capacity(),
				bbf.limit(),
				bbf.position(),

				bbf.hasArray(),
				bbf.hasRemaining(),
				bbf.remaining(),
				//bbf.arrayOffset(),  //не поддерживается
				bbf.isReadOnly(),
				bbf.isDirect());

	}

	public void test2() throws Exception {
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

	public void test3() throws Exception {
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
	
	public void test4() throws Exception {
		/*
		
		 */
		
		ByteBuffer bb1 = ByteBuffer.allocate(10);
		
		logExpr(() -> {
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
		DemoBase.run(D_Buffer.class);
	}

}
