package ru.tet.java.io;

import java.io.RandomAccessFile;

import ru.tet.TemplateDemo;
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
		raf.seek(0);	//задание позиции курсора

		int number = raf.readInt();
		log2("Number: " + number);
		String str = raf.readUTF();
		log2("String: " + str);

		long length = raf.length();
		log2("File length: " + length);

		raf.close();
	}

	
	@Override
	protected void doInitControlPanel() throws Exception {
		addTest1Button(null);
	}

	public static void main(String[] args) {
		DemoBase.run(RandomAccessFileDemo.class);
	}

}