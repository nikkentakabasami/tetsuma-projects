package ru.tet.aux;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import ru.tet.demos.AbstractDemoBase;

public class EchoClient {
	
  private Socket clientSocket;
  private PrintWriter out;
  private BufferedReader in;

  public void startConnection() throws Exception {
      clientSocket = new Socket("127.0.0.1", EchoServer.PORT);
      out = new PrintWriter(clientSocket.getOutputStream(), true);
      in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
  }

  public String sendMessage(String msg) throws IOException {
      out.println(msg);
      String resp = in.readLine();
      
			AbstractDemoBase.currentDemo.log2("got response:",resp);
      return resp;
  }

  public void stopConnection() throws IOException {
      in.close();
      out.close();
      clientSocket.close();
  }
  
  
}