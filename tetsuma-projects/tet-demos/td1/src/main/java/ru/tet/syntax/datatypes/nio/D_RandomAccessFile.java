package ru.tet.syntax.datatypes.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HexFormat;

import ru.tet.aux.swing.DemoBase;

public class D_RandomAccessFile extends DemoBase {

	Path p1 = Path.of("target/raf1.txt");

	@Override
	public String toStr(Object o) {

		if (o instanceof byte[] bytes) {

			HexFormat commaFormat = HexFormat.ofDelimiter(",");
			return commaFormat.formatHex(bytes);
		}

		// TODO Auto-generated method stub
		return super.toStr(o);
	}

	public void test1() throws Exception {
		/*
		 */

		ByteBuffer bb1 = ByteBuffer.allocate(100);

		try (RandomAccessFile raf = new RandomAccessFile(p1.toFile(), "rw");) {
			raf.setLength(0);
			raf.writeUTF("apple");
			raf.writeInt(12345);

			raf.getChannel().write(bb1);
			raf.writeDouble(123.456);

			raf.seek(50);
			raf.writeInt(777);

		}

		byte[] bytes = Files.readAllBytes(p1);

		logEval1(
				Files.size(p1),
				bytes,
				"apple".getBytes());

	}

	public void test2() throws Exception {
		/*
		
		 */

		try (RandomAccessFile raf = new RandomAccessFile(p1.toFile(), "r");) {

			logEval1(
					raf.readUTF(),
					raf.readInt(),
					raf.skipBytes(100),
					raf.readDouble(),

					expr(() -> {
						//считывание из заданной позиции
						raf.seek(50);
						return raf.readInt();
					}),
					raf.length());
		}

	}

	public void test3() throws Exception {
		/*
		FileChannel	getChannel()
		Получение канала.
		 */

		try (RandomAccessFile raf = new RandomAccessFile(p1.toFile(), "r");) {

			FileChannel fc = raf.getChannel();

			//считывание из заданной позиции
			fc.position(50);
			ByteBuffer bb1 = ByteBuffer.allocate(4);
			fc.read(bb1);

			logEval1(
					bb1.flip().asIntBuffer().get());

		}

	}

	public void test4() throws Exception {
		/*
		
		 */
	}

	@Override
	protected void doInit() throws Exception {
		options().hlComments = false;
	}

	public static void main(String[] args) {
		DemoBase.run(D_RandomAccessFile.class);
	}

}
