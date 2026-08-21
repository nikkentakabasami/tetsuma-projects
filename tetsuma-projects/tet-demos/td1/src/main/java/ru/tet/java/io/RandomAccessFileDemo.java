package ru.tet.java.io;

import java.io.RandomAccessFile;

import ru.tet.DemoExample;
import ru.tet.aux.swing.DemoBase;

public class RandomAccessFileDemo extends DemoBase {

	
	//RandomAccessFile
	//  позволяет читать и записывать данные в файл произвольно
	//  Содержит курсор с которого будет идти запись/чтение
	@Override
	public void test1() throws Exception {

		//запись данных
		RandomAccessFile raf = new RandomAccessFile("target/example.dat", "rw");
		raf.writeInt(12345);
		raf.writeUTF("Hello");
		raf.close();

		raf = new RandomAccessFile("target/example.dat", "rw");
		raf.seek(0); //задание позиции курсора

		int number;
		String str;
		
		
		logEval(
				number = raf.readInt(),
				raf.getFilePointer(),
				str = raf.readUTF(),
				raf.getFilePointer(),
				raf.length()
				);

		raf.close();
	}

	public static void main(String[] args) {
		DemoBase.run(RandomAccessFileDemo.class);
	}

}