package ru.tet.java.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileChannelDemo {

	public static final String TEST_READ_FILE_NAME = "/home/tetsuma/.bashrc";

	public static void main(String[] args) throws Exception {
//		readDemo();
//		readWriteDemo();
//		copyDemo();
		mapDemo();

	}

	/**
	 * Считывание текстового файла через ByteBuffer
	 */
	public static void readDemo() throws Exception {

		FileInputStream fis = new FileInputStream(new File(TEST_READ_FILE_NAME));

		FileChannel inChannel = fis.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(48);

		while (inChannel.read(buf) != -1) {

			// готовим для считывания
			buf.flip();
			String s = new String(buf.array(), 0, buf.limit(), "UTF-8");
			System.out.print(s);

			// готовим для записи
			buf.clear();
		}
		fis.close();
	}

	/**
	 * Считывание байтов из произвольного места в одном файле и запись в другой
	 */
	public static void readWriteDemo() throws Exception {

		Path testFilePath = Paths.get("target/testFile.txt");
		System.out.println("testFilePath=" + testFilePath);

		if (Files.notExists(testFilePath)) {
			Files.createFile(testFilePath);
		}

		try (FileInputStream fis = new FileInputStream(new File(TEST_READ_FILE_NAME));
				FileChannel inChannel = fis.getChannel();
				FileChannel outChannel = FileChannel.open(testFilePath, StandardOpenOption.READ,
						StandardOpenOption.WRITE);) {

			String lineSeparator = "\n------------------\n";
			ByteBuffer lineSeparatorBytes = ByteBuffer.wrap(lineSeparator.getBytes(StandardCharsets.UTF_8));

			// чистим тестовый файл
			outChannel.truncate(0);

			// считываем первые 128 байт
			ByteBuffer buf = ByteBuffer.allocate(128);
			inChannel.read(buf);

			// записываем их в тестовый файл
			buf.flip();
			outChannel.write(buf);

			// записываем разделитель
			outChannel.write(lineSeparatorBytes);

			// считываем 128 байт по смещению 256
			buf.clear();
			inChannel.position(256);
			inChannel.read(buf);

			// записываем их в тестовый файл
			buf.flip();
			outChannel.write(buf);

			// записываем разделитель
			lineSeparatorBytes.flip();
			outChannel.write(lineSeparatorBytes);

		}

	}

	/**
	 * Копирование файлов через каналы более эффективно, чем через потоки
	 */
	public static void copyDemo() throws Exception {

		Path testInputFilePath = Paths.get("target/testFile.txt");
		Path testOutputFilePath = Paths.get("target/testFileCopy.txt");

		try (

				FileChannel in = FileChannel.open(testInputFilePath, StandardOpenOption.READ);
				FileChannel out = FileChannel.open(testOutputFilePath, StandardOpenOption.WRITE);

//				FileChannel in = new FileInputStream(testInputFilePath.toFile()).getChannel();
//				FileChannel out = new FileOutputStream(testOutputFilePath.toFile()).getChannel();
		) {
			in.transferTo(0, in.size(), out);

//			out.close();
//			in.close();

		}
	}

	public static void mapDemo() throws Exception {

		Path testInputFilePath = Paths.get("target/testFile.txt");

		FileChannel fc = FileChannel.open(testInputFilePath, StandardOpenOption.READ);

		ByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
		IntBuffer ib = bb.asIntBuffer();
		while (ib.hasRemaining()) {
			int i = ib.get();
			System.out.println(i);
		}
		fc.close();

	}

}
