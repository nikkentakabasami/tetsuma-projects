package ru.tet.aux;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import ru.tet.demos.AbstractDemoBase;

public class EchoServer {
	ServerSocket serverSocket;

	public static final int PORT = 6666;

	public void start() {
		try {

			//другой способ
			//		InetAddress ia = InetAddress.getByName("localhost");
			//		serverSocket = new ServerSocket(port, 10, ia);
			
			serverSocket = new ServerSocket(PORT);

			while (true) {
				Socket clientSocket = serverSocket.accept();
				new EchoClientHandler(clientSocket).start();
			}

		} catch (Exception e) {
			AbstractDemoBase.currentDemo.logException(e);
		} finally {
			stop();
		}

	}

	public void stop(){
		try {
			if (serverSocket != null) {
				serverSocket.close();
			}
		} catch (IOException e) {
			AbstractDemoBase.currentDemo.logException(e);
		}
	}


}
