package ru.tet.syntax.datatypes.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Set;

import ru.tet.aux.swing.DemoBase;

public class D_ServerSockerChannel extends DemoBase {

	void startBlockingServer() {

		//блокирующий сервер
		try (var ssc = ServerSocketChannel.open()) {
			log2("blocking server started on port 9999...");
			
			ssc.socket().bind(new InetSocketAddress(9999));

			//По дефолту канал является блокирующим.
			//Перевод в неблокирующий режим - false
			ssc.configureBlocking(true);

			var responseMessage = "Привет от сервера! : " + ssc.socket().getLocalSocketAddress();
			var bb1 = ByteBuffer.wrap(responseMessage.getBytes());

			while (true) {
				try (SocketChannel sc = ssc.accept()) {
					//получаем сообщение
					log2("Принято соединение от  " + sc.socket().getRemoteSocketAddress());
					var receivedBuffer = ByteBuffer.allocate(100);
					sc.read(receivedBuffer);
					var requestMessage = new String(receivedBuffer.array(), 0, receivedBuffer.remaining());
					log2(requestMessage);
					log2Splitter();

					//отправляем ответное сообщение
					bb1.rewind();
					sc.write(bb1);
				}
			}

		} catch (Exception e) {
			log2(e.getMessage());
			e.printStackTrace();
		}

	}

	void startNonBlockingServer() {
		try (
				Selector selector = Selector.open();
				ServerSocketChannel ssc = ServerSocketChannel.open();) {

			ssc.bind(new InetSocketAddress(8080));
			ssc.configureBlocking(false);

			ssc.register(selector, SelectionKey.OP_ACCEPT);
			log2("Non-blocking server started on port 8080...");

			while (true) {
				selector.select(); // Blocks until at least one event occurs
				Set<SelectionKey> selectedKeys = selector.selectedKeys();
				Iterator<SelectionKey> iter = selectedKeys.iterator();

				while (iter.hasNext()) {
					SelectionKey key = iter.next();

					if (key.isAcceptable()) {
						// Accept the incoming connection
						ServerSocketChannel server = (ServerSocketChannel) key.channel();
						SocketChannel client = server.accept();
						client.configureBlocking(false);

						// Register the new client channel to monitor for incoming data (Read)
						client.register(selector, SelectionKey.OP_READ);
						log2("Accepted connection from: " + client.getRemoteAddress());
					}

					if (key.isReadable()) {
						// Handle client data transfer
						SocketChannel client = (SocketChannel) key.channel();
						ByteBuffer buffer = ByteBuffer.allocate(256);
						int bytesRead = client.read(buffer);

						if (bytesRead == -1) {
							client.close();
							log2("Connection closed by client.");
						} else {
							buffer.flip();
							client.write(buffer); // Echo the data back to the client
						}
					}
					iter.remove(); // Clear processed event
				}
			}

		} catch (Exception e) {
			log2(e.getMessage());
			e.printStackTrace();
		}

	}

	@Override
	protected void doInit() throws Exception {
		Thread thread = new Thread(() -> {
			startBlockingServer();
		});
		thread.setDaemon(true);
		thread.start();
		
		Thread thread2 = new Thread(() -> {
			startNonBlockingServer();
		});
		thread2.setDaemon(true);
		thread2.start();
		
		
	}

	public void test1() throws Exception {
		/*
		 * блокирующий клиент
		 */

		//int port = 9999;
		int port = 8080;
		
		try (SocketChannel sc = SocketChannel.open()) {
			sc.configureBlocking(true);
			sc.connect(new InetSocketAddress("localhost", port));

			var requestMessage = "Привет от клиента! " + LocalDateTime.now();
			ByteBuffer buffer = ByteBuffer.wrap(requestMessage.getBytes());
			sc.write(buffer);

			var receivedBuffer = ByteBuffer.allocate(100);
			//Приложение останавливается в ожидании ответа
			sc.read(receivedBuffer);
			var responseMessage = new String(receivedBuffer.array());
			log2("response from server:", responseMessage);
		}

	}

	public void test2() throws Exception {
		/*
		 * неблокирующий клиент
		 */

		try (SocketChannel sc = SocketChannel.open()) {
			sc.configureBlocking(false);
			sc.connect(new InetSocketAddress("localhost", 9999));

			while (!sc.finishConnect()) {
				System.out.println("waiting to finish connection");
				wait(100);
			}

			var requestMessage = "Привет от клиента! " + LocalDateTime.now();
			ByteBuffer bb1 = ByteBuffer.wrap(requestMessage.getBytes());
			sc.write(bb1);

			Thread.sleep(1_000);

			var bb2 = ByteBuffer.allocate(100);
			//Ответа еще нет, канал ничего не прочтет, буффер останется пустым
			sc.read(bb2);

			var responseMessage = new String(bb2.array(), 0, bb2.remaining(), StandardCharsets.UTF_8);
			log2("response from server:", responseMessage);
		}
	}

	public void test3() throws Exception {
		/*
		Selector
		Selector – это объект, относящийся к группе каналов и определяющий, какой канал готов к записи/чтению/подключению и т.д. Он позволяет одному потоку управлять несколькими каналами (подключениями). Это позволяет уменьшить траты на переключения между потоками.
		
		 */
	}

	public void test4() throws Exception {
		/*
		
		 */
	}

	public static void main(String[] args) {
		DemoBase.run(D_ServerSockerChannel.class);
	}

}
