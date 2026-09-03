package ru.tet.syntax.datatypes.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.FileChannel;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import ru.tet.aux.swing.DemoBase;

public class D_Channel extends DemoBase {

	public void test1() throws Exception {
		/*
		FileChannel - создание
		
		FileChannel.open(Path path, OpenOption... options)
		
		 */

		Path p1 = Path.of("target/channelTest1.txt");
		if (!Files.exists(p1)) {
			Files.createFile(p1);
		}
		FileChannel fc1 = FileChannel.open(p1, StandardOpenOption.READ, StandardOpenOption.WRITE);

		CharsetEncoder encoder = StandardCharsets.UTF_8.newEncoder();
		ByteBuffer bb1 = encoder.encode(CharBuffer.wrap("clean biosphere"));
		ByteBuffer bb2 = encoder.encode(CharBuffer.wrap("\n---\n"));

		fc1.write(bb1);
		fc1.write(bb2);

		bb1.rewind();
		fc1.write(bb1);

		fc1.close();

		log2(p1, "=");
		log2(Files.readString(p1));

		/*
		fileInputStream.getChannel();
		fileOutputStream.getChannel();
		randomAccessFile.getChannel();
		 */

		try (
				FileInputStream inputStream = new FileInputStream(new File("pom.xml"));
				FileChannel fc = inputStream.getChannel()) {

			ByteBuffer bb5 = ByteBuffer.allocate(80);
			fc.read(bb5);
			bb5.flip();
			r.s1 = fc.position(); //80

			String readen = new String(bb5.array(), bb5.position(), bb5.remaining());
			log2("pom:", readen);
		}

		try (
				FileOutputStream fos = new FileOutputStream(new File("target/fos.txt"), false);
				FileChannel fc3 = fos.getChannel()) {

			ByteBuffer bb7 = ByteBuffer.wrap("test fos text".getBytes());

			r.s2 = fc3.position();
			fc3.write(bb7);
			r.s3 = fc3.position(); //13
		}

		try (RandomAccessFile raf = new RandomAccessFile("target/raf.txt", "rw");
				FileChannel fc2 = raf.getChannel();) {
			ByteBuffer bb6 = ByteBuffer.wrap("test raf text".getBytes());
			fc2.write(bb6);
		}

		/*
		 Files.newByteChannel(Path path, OpenOption... options)
		   Открывает ByteChannel для чтения/записи
		 */

		log2("Files.newByteChannel");
		try (ByteChannel inChannel = Files.newByteChannel(Path.of("pom.xml"), StandardOpenOption.READ)) {

			ByteBuffer buf = ByteBuffer.allocate(48);
			while (inChannel.read(buf) != -1) {

				// готовим для считывания
				buf.flip();
				String s = new String(buf.array(), buf.position(), buf.remaining(), StandardCharsets.UTF_8);
				log2(s);

				// готовим для записи
				buf.clear();
			}

		}

	}

	public void test2() throws Exception {
		/*
		FileChannel - основные методы
		
		MappedByteBuffer map(FileChannel.MapMode mode, long position, long size)
		проецирование в память
		
		
		FileChannel 	truncate(long size)
		обрезание до заданного размера		
		
		 */

		RandomAccessFile raf = new RandomAccessFile("target/raf.txt", "rw");
		FileChannel fc1 = raf.getChannel();
		ByteBuffer bb1 = ByteBuffer.wrap("test raf text".getBytes());
		fc1.write(bb1);

		//обрезаем файл до 8 байт
		fc1.truncate(8);

		//проецируем файл в буфер
		MappedByteBuffer bb2 = fc1.map(FileChannel.MapMode.READ_ONLY, 0, fc1.size());

		logEval(
				fc1.size(),
				bb2.hasArray());

		//считываем данные побайтово
		StringBuilder sb = new StringBuilder();
		while (bb2.hasRemaining()) {
			char c = (char) bb2.get();
			sb.append(c);
		}
		log2("bb3 content:", sb.toString());

		//считаем данные в HeapByteBuffer
		bb2.flip();
		ByteBuffer bb4 = ByteBuffer.allocate(bb2.remaining());
		bb4.put(bb2);
		bb4.flip();
		String readen = new String(bb4.array(), 0, bb4.limit());
		log2("bb3 content2:", readen);

		raf.close();
	}

	public void test3() throws Exception {
		/*
		FileChannel - считывание через ByteBuffer
		 */

		FileInputStream is = new FileInputStream(new File("pom.xml"));
		FileChannel fc1 = is.getChannel();
		ByteBuffer bb1 = ByteBuffer.allocate(48);

		while (fc1.read(bb1) != -1) {

			//готовим для считывания
			bb1.flip();

			CharBuffer decoded = StandardCharsets.UTF_8.decode(bb1);

			log2(decoded);

			//готовим для записи
			bb1.clear();
		}
		is.close();

	}

	public void test4() throws Exception {
		/*
		Копирование файлов через каналы - более эффективно, чем через потоки
		 */

		Path p1 = Path.of("pom.xml");
		Path p2 = Path.of("target/pom_copy.txt");
		if (!Files.exists(p2)) {
			Files.createFile(p2);
		}

		try (
				FileChannel in = FileChannel.open(p1, StandardOpenOption.READ);
				FileChannel out = FileChannel.open(p2, StandardOpenOption.WRITE);) {
			in.transferTo(0, in.size(), out);
		}

	}

	@Override
	public void test5() throws Exception {

	}

	public static void main(String[] args) {
		DemoBase.run(D_Channel.class);
	}

}
