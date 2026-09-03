package ru.tet.syntax.datatypes.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import ru.tet.aux.swing.DemoBase;

public class D_FileChannel_write extends DemoBase {

	public void test1() throws Exception {
		/*
		Добавление в файл.
		 */

		try (
				FileOutputStream fos = new FileOutputStream(new File("target/fos.txt"), true);
				FileChannel fc1 = fos.getChannel()) {

			ByteBuffer bb1 = ByteBuffer.wrap("test fos text".getBytes());

			//изменение позиции никак не повлияет - текст добавится в конец
			fc1.position(100);

			fc1.write(bb1);
		}

	}

	public void test2() throws Exception {
		/*
		Запись данных в проивольную позицию файла
		
		 */
		try (RandomAccessFile raf = new RandomAccessFile("target/raf.txt", "rw");
				FileChannel fc1 = raf.getChannel();) {
			ByteBuffer bb1 = ByteBuffer.wrap("test raf text".getBytes());

			fc1.write(bb1);
			bb1.rewind();
			fc1.write(bb1);

			//записываем метку в произвольную позицию файла
			ByteBuffer bb2 = ByteBuffer.wrap("#@#".getBytes());
			
			fc1.position(4);
			fc1.write(bb2);

			fc1.close();

		}

	}

	public void test3() throws Exception {
		/*
		Запись данных в проивольную позицию файла.
		Пример2.
		 */
		
		Path p1 = Path.of("target/raf.txt");

		if (Files.notExists(p1)) {
			Files.createFile(p1);
		}

		try (FileInputStream fis = new FileInputStream(new File("pom.xml"));
				FileChannel channel1 = fis.getChannel();
				FileChannel channel2 = FileChannel.open(p1, StandardOpenOption.READ,StandardOpenOption.WRITE);
				
//				RandomAccessFile raf = new RandomAccessFile(p1.toFile(), "rw");
//				FileChannel channel2 = raf.getChannel();				
				
				) {

			ByteBuffer bbSeparator = ByteBuffer.wrap("\n------------------\n".getBytes());

			// чистим тестовый файл
			channel2.truncate(0);

			// считываем первые 128 байт
			ByteBuffer bb1 = ByteBuffer.allocate(128);
			channel1.read(bb1);

			// записываем их в тестовый файл
			bb1.flip();
			channel2.write(bb1);

			// записываем разделитель
			channel2.write(bbSeparator);

			// считываем 128 байт по смещению 256
			bb1.clear();
			channel1.position(256);
			channel1.read(bb1);

			// записываем их в тестовый файл
			bb1.flip();
			channel2.write(bb1);

			// записываем разделитель
			bbSeparator.flip();
			channel2.write(bbSeparator);

			//записываем метку в произвольную позицию файла
			channel2.position(10);
			ByteBuffer bb3 = ByteBuffer.wrap("###@@@###".getBytes());
			channel2.write(bb3);

			bb3.rewind();
			channel2.position(50);
			channel2.write(bb3);

			//если position - за пределами файла - ничего не запишется
			bb3.rewind();
			channel2.position(400);
			channel2.write(bb3);
			
		}		
		
	}

	public void test4() throws Exception {
		/*
		
		 */
	}

	public static void main(String[] args) {
		DemoBase.run(D_FileChannel_write.class);
	}

}
