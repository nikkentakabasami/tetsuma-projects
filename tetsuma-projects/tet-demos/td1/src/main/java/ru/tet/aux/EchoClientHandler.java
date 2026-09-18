package ru.tet.aux;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalTime;

import ru.tet.demos.AbstractDemoBase;

public class EchoClientHandler extends Thread {
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;

	public EchoClientHandler(Socket socket) {
		this.clientSocket = socket;
		AbstractDemoBase.currentDemo.log2("ss:", "got connection! port:" + clientSocket.getPort());
	}

	public void run() {
		try {
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in =
					new BufferedReader(
							new InputStreamReader(clientSocket.getInputStream()));

			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				if (".".equals(inputLine)) {
					out.println("bye");
					AbstractDemoBase.currentDemo.log2("ss:", "bye");
					break;
				}
				AbstractDemoBase.currentDemo.log2("ss:", "got message:" + inputLine);
				
				LocalTime lt1 = LocalTime.now();
				out.println("server response" + lt1);
			}

			in.close();
			out.close();
			clientSocket.close();
		} catch (IOException e) {
			AbstractDemoBase.currentDemo.logException(e);
		}
	}
}