package ru.tet.syntax.datatypes.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

import ru.tet.aux.DemoAuxDataSamples;
import ru.tet.aux.swing.DemoBase;

public class D_HttpClient_Builder extends DemoBase {

	public void test1() throws Exception {
		/*
		 */
		HttpClient client =
				HttpClient.newBuilder()
						.version(HttpClient.Version.HTTP_2)
						.proxy(ProxySelector.getDefault())
					  .followRedirects(HttpClient.Redirect.ALWAYS)
					  .connectTimeout(Duration.ofMinutes(1))
						.build();

		HttpRequest request =
				HttpRequest.newBuilder()
						.uri(URI.create(DemoAuxDataSamples.url_get_example))
						.build();

		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

		log2(response.body());

	}

	public void test2() throws Exception {
		/*
		
		 */

		logEval1(
				ProxySelector.getDefault());

	}

	public void test3() throws Exception {
		/*
		
		 */



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
		DemoBase.run(D_HttpClient_Builder.class);
	}

}
