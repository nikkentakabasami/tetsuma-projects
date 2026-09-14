package ru.tet.syntax.datatypes.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Date;

import ru.tet.aux.DemoAuxDataSamples;
import ru.tet.aux.DemoAuxFunctions;
import ru.tet.aux.DemoAuxObjectSamples;
import ru.tet.aux.swing.DemoBase;
import ru.tet.beans.Employee;

public class D_InputStream extends DemoBase {

	public void test1() throws Exception {
		/*
		
		ByteArrayInputStream
		 */
		InputStream nullInputStream = InputStream.nullInputStream();

		byte[] bytes = DemoAuxDataSamples.sampleStringLyric.getBytes();
		InputStream is1 = new ByteArrayInputStream(bytes);

		byte[] buff = new byte[10];
		int length;
		while ((length = is1.read(buff)) != -1) {
			String s = new String(buff, 0, length, StandardCharsets.UTF_8);
			log2(s);
		}

		log2Splitter("read through channel");

		is1.reset();
		ReadableByteChannel channel1 = Channels.newChannel(is1);
		ByteBuffer buf = ByteBuffer.allocate(20);
		while (channel1.read(buf) != -1) {
			buf.flip();
			String s = new String(buf.array(), buf.position(), buf.remaining(), StandardCharsets.UTF_8);
			log2(s);
			buf.clear();
		}

		channel1.close();

	}

	public void test2() throws Exception {
		/*
		FileInputStream
		
		 */

		Path p1 = DemoAuxFunctions.getTestTextFile();

		try (
				FileInputStream is1 = new FileInputStream(p1.toFile());
				ByteArrayOutputStream os1 = new ByteArrayOutputStream();) {
			is1.transferTo(os1);
			byte[] bytes = os1.toByteArray();
			log2(new String(bytes));
		}

		log2Splitter();
		FileInputStream is1 = new FileInputStream(p1.toFile());
		byte[] bytes = is1.readAllBytes();
		log2(new String(bytes));

		//		is1 = new FileInputStream(p1.toFile());
		//		FileChannel channel1 = is1.getChannel();
		//		channel1.read(null)

		//		InputStream newInputStream = Files.newInputStream(p1);

	}

	public void test3() throws Exception {
		/*
		FileInputStream
		
		FileChannel	getChannel()
		
		 */

		try (
				FileInputStream inputStream = new FileInputStream(new File("pom.xml"));
				FileChannel fc1 = inputStream.getChannel()) {
			ByteBuffer buf = ByteBuffer.allocate(40);
			while (fc1.read(buf) != -1) {

				buf.flip();
				String s = new String(buf.array(), buf.position(), buf.remaining(), StandardCharsets.UTF_8);
				log2(s);

				// готовим для записи
				buf.clear();
			}
		}

	}

	public void test4() throws Exception {
		/*
		DataInputStream
		 */
		/*
		Path p1 = DemoAuxFunctions.getTestTextFile();
		
		DataInputStream dis1 = new DataInputStream(new FileInputStream(p1.toFile()));
		logEval1(
		//				dis1.readUTF()
				dis1.readChar(),
				dis1.readBoolean(),
				dis1.readByte(),
				dis1.readDouble(),
				dis1.readInt()
		);
		dis1.close();
		*/
		Path p2 = Path.of("target", "disTest.dat");

		DataOutputStream dos1 = new DataOutputStream(new FileOutputStream(p2.toFile()));
		dos1.writeInt(1);
		dos1.writeChar('a');
		dos1.writeBoolean(true);
		dos1.writeDouble(44.771);
		dos1.writeUTF("my test string!");
		dos1.close();

		DataInputStream dis1 = new DataInputStream(new FileInputStream(p2.toFile()));
		logEval1(
				dis1.readInt(),
				dis1.readChar(),
				dis1.readBoolean(),
				dis1.readDouble(),
				dis1.readUTF());

		dis1.close();

	}

	@Override
	public void test5() throws Exception {
		/*
		ObjectOutputStream
		  Преобразование сериализуемых объектов в поток байтов
		Продвинутая версия DataOutputStream.
		
		 */

		Path p2 = Path.of("target", "oosTest.dat");

		FileOutputStream fos = new FileOutputStream(p2.toFile());
		ObjectOutputStream oos1 = new ObjectOutputStream(fos);

		oos1.writeInt(12345);
		oos1.writeUTF("Ashita");
		oos1.writeChars("ABC");
		oos1.writeFloat(7.567f);

		oos1.writeObject("Today");
		oos1.writeObject(new Date());
		oos1.writeObject(DemoAuxObjectSamples.createEmployee1());
		oos1.close();

		FileInputStream fis1 = new FileInputStream(p2.toFile());
		ObjectInputStream ois1 = new ObjectInputStream(fis1);

		logEval1(
				ois1.readInt(),
				ois1.readUTF(),
				ois1.readChar(),
				ois1.readChar(),
				ois1.readChar(),
				ois1.readFloat(),

				ois1.readObject(),
				ois1.readObject(),
				ois1.readObject()

		);

		ois1.close();

	}

	public static void main(String[] args) {
		DemoBase.run(D_InputStream.class);
	}

}
