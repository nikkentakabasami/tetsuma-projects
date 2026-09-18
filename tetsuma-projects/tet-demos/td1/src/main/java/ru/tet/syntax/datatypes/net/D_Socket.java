package ru.tet.syntax.datatypes.net;

import ru.tet.aux.EchoClient;
import ru.tet.aux.EchoServer;
import ru.tet.aux.swing.DemoBase;

public class D_Socket extends DemoBase {

	EchoServer server;

	@Override
	protected void doInit() throws Exception {
		options().hlComments = false;
		options().logSources = false;

		
		server = new EchoServer();
		Thread thread = new Thread(() -> {
			server.start();
		});
		thread.setDaemon(true);
		thread.start();

	}

	@Override
	public void beforeClose() throws Exception {
		server.stop();
	}

	public void test1() throws Exception {
		/*
		
		 */

		EchoClient client1 = new EchoClient();
		client1.startConnection();

		client1.sendMessage("hello1");

		EchoClient client2 = new EchoClient();
		client2.startConnection();

		client2.sendMessage("hello2");

		client1.sendMessage(".");
		client1.stopConnection();

	}

	public void test2() throws Exception {
		/*
		
		 */

	}

	
	public void test4() throws Exception {
		/*
		
		 */



	}

	public static void main(String[] args) {
		DemoBase.run(D_Socket.class);
	}

}
