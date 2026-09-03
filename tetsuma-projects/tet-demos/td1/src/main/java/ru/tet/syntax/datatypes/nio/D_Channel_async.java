package ru.tet.syntax.datatypes.nio;

import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.concurrent.Future;

import ru.tet.aux.swing.DemoBase;

public class D_Channel_async extends DemoBase {

	public void test1() throws Exception {
		/*
		AsynchronousFileChannel
		 */

		URL fileUrl = getClass().getClassLoader().getResource("testHtmlPage.html");
		var p1 = Path.of(fileUrl.toURI());
		log2(p1);
		
		
		try (var fc1 = AsynchronousFileChannel.open(p1)) {
			var buffer = ByteBuffer.allocate(500);
			Future<Integer> future = fc1.read(buffer, 0);

			//ждать получения
			future.get();

			//или можно ждать так
			while (!future.isDone()) {
				log2("Файл еще не загружен в буффер");
			}

			
			var fileString = new String(buffer.array(), StandardCharsets.UTF_8);
			log2(fileString);
		}

	}

	public void test2() throws Exception {
		/*
		AsynchronousFileChannel
		 */

		URL fileUrl = getClass().getClassLoader().getResource("testHtmlPage.html");
		var p1 = Path.of(fileUrl.toURI());

		try (var fc1 = AsynchronousFileChannel.open(p1)) {
			var buffer = ByteBuffer.allocate(500);

			fc1.read(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {

				@Override
				public void completed(Integer result, ByteBuffer attachment) {
					var fileString = new String(buffer.array(), StandardCharsets.UTF_8);
					log2("readen",result,"bytes");
					log2(fileString);
				}

				@Override
				public void failed(Throwable exc, ByteBuffer attachment) {
				}
			});

			try {
				//чтоб не закрывался сразу
				Thread.sleep(500);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}

	}

	public void test3() throws Exception {
		/*
		
		 */
	}

	public void test4() throws Exception {
		/*
		
		 */
	}

	public static void main(String[] args) {
		DemoBase.run(D_Channel_async.class);
	}

}
