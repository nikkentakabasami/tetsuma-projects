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

import ru.tet.aux.DemoAuxFunctions;
import ru.tet.aux.DemoAuxDataSamples;
import ru.tet.aux.swing.DemoBase;

public class D_ByteBuffer1 extends DemoBase {

	public void test1() throws Exception {
		/*
		ByteBuffer
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

		Path p1 = DemoAuxFunctions.createTestFile("testbb.txt", DemoAuxDataSamples.sampleStringShort);

		//		Path p1 = Path.of("target/testbb.txt");
		//		createTestFile(p1);
		FileInputStream is = new FileInputStream(p1.toFile());
		FileChannel fc = is.getChannel();
		MappedByteBuffer bbf = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
		is.close();

	}

	ByteBuffer bb1, bb2;

	@Override
	public void test2() throws Exception {
		/*
		ByteBuffer
		
		Базовые аттрибуты
		 */

		logEval1(
				bb1 = ByteBuffer.allocate(48),

				bb1.position(),
				bb1.limit(),
				bb1.capacity(),

				bb1.hasArray(),
				bb1.hasRemaining(),
				bb1.remaining(),
				bb1.arrayOffset(),
				bb1.isReadOnly(),
				bb1.isDirect());

		log2Splitter();

		//		byte[] bytes = "clean biosphere".getBytes();
		//		ByteBuffer bb1 = ByteBuffer.wrap(bytes, 4, 5);
		//		ByteBuffer bb1_slice = bb1.slice();

		logEval2(
				expr(() -> {
					byte[] bytes = "clean biosphere".getBytes();
					bb1 = ByteBuffer.wrap(bytes, 4, 5);
					return null;
				}),

				bb1.position(),
				bb1.limit(),
				bb1.capacity(),
				bb1.remaining(),

				new String(bb1.array(), bb1.position(), bb1.remaining()),

				//возвращает оригинальный массив
				Arrays.toString(bb1.array()),

				//абсолютное считывание
				//задаётся индекс для массива!
				bb1.get(0),
				bb1.get(5),

				//считывает 2 байта на символ, так что символ неверный 
				bb1.getChar(0),

				//относительное считывание - меняет position
				bb1.get(),

				bb1.position(),
				bb1.remaining(),

				bb1.get(),
				bb1.get(),

				bb1.position(),
				bb1.remaining(),

				//получение среза - часть данных после позиции
				//использует offset
				bb2 = bb1.slice(),

				//массив тот же самый
				Arrays.toString(bb2.array()),

				//Но offset равен bb1.position()
				bb2.arrayOffset(),
				bb2.position(),
				bb2.limit(),
				bb2.remaining(),

				new String(bb2.array(), bb2.position() + bb2.arrayOffset(), bb2.remaining()),

				//абсолютные методы добавляют offset к индексу!
				bb2.get(0)

		);

	}

	@Override
	public void test3() throws Exception {
		/*
		Буфер, полученный проецированием файла
		 */

		Path p1 = DemoAuxFunctions.createTestFile("testbb.txt", DemoAuxDataSamples.sampleStringShort);
		FileInputStream is = new FileInputStream(p1.toFile());
		FileChannel fc = is.getChannel();
		MappedByteBuffer bbf = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
		is.close();

		log2Splitter();

		logEval1(
				bbf.position(),
				bbf.limit(),
				bbf.capacity(),

				bbf.hasArray(),
				bbf.hasRemaining(),
				bbf.remaining(),
				//bbf.arrayOffset(),  //не поддерживается
				bbf.isReadOnly(),
				bbf.isDirect());

		logExpr1(() -> {
			CharBuffer cb = StandardCharsets.UTF_8.decode(bbf);
			return cb.toString();
		});

	}

	public static void main(String[] args) {
		DemoBase.run(D_ByteBuffer1.class);
	}

}
