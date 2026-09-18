package ru.tet.syntax.datatypes.net;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ru.tet.aux.DemoAuxDataSamples;
import ru.tet.aux.swing.DemoBase;

public class D_HttpClient extends DemoBase {

	public void test1() throws Exception {
		/*
		Простой get-запрос
		
		
		HttpResponse	send(HttpRequest request, BodyHandler bh)
		синхронная отправка запроса
		
		HttpResponse.BodyHandler
		определяет в каком формате будут получены данные
		
		BodyHandlers.ofByteArray
		BodyHandlers.ofString
		BodyHandlers.ofFile
		BodyHandlers.discarding
		BodyHandlers.replacing
		BodyHandlers.ofLines
		BodyHandlers.fromLineSubscriber
		
		 */

		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request =
				HttpRequest.newBuilder()
						.version(HttpClient.Version.HTTP_2)
						.uri(URI.create(DemoAuxDataSamples.url_get_example))
						.build();

		/*
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		
		HttpResponse<Stream<String>> response = client.send(request, BodyHandlers.ofLines());
		response.body().forEach(this::log2);
		
		HttpResponse<Path> response = client.send(request, BodyHandlers.ofFile(Path.of("target/resp1.txt")));
		log2(Files.readString(response.body()));
		
		HttpResponse<byte[]> response = client.send(request, BodyHandlers.ofByteArray());
		
		HttpResponse<Void> response = client.send(request, BodyHandlers.discarding());
		
		HttpResponse<String> response = client.send(request, BodyHandlers.replacing("stub"));
		log2(response.body());
		
		 */

		HttpResponse<InputStream> response = client.send(request, BodyHandlers.ofInputStream());
		byte[] bytes = response.body().readAllBytes();
		log2(new String(bytes));

	}


	public void test2() throws Exception {
		/*
		Простой post запрос - отправка текста в теле
		 */

		HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

		byte[] sampleData = "Sample request body".getBytes();
		HttpRequest request =
				HttpRequest.newBuilder()
						.uri(URI.create(DemoAuxDataSamples.url_post_httpbin))
						.headers("Content-Type", "text/plain;charset=UTF-8")
						.POST(HttpRequest.BodyPublishers
								.ofInputStream(() -> new ByteArrayInputStream(sampleData)))
						.build();

		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

		logEval1(
				response.body(),
				response.statusCode());

	}

	public void test3() throws Exception {
		/*
		HttpResponse	send(HttpRequest request, BodyHandler bh)
		синхронная отправка запроса
		 */

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(URI.create(DemoAuxDataSamples.url_get_example)).build();

		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

		log2(response.body());

	}

	public void test4() throws Exception {
		/*
		CompletableFuture<HttpResponse>	sendAsync(HttpRequest request, BodyHandler bh)
		асинхронная отправка запроса
		
		 */
		
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(URI.create(DemoAuxDataSamples.url_get_example)).build();
		
		CompletableFuture<Void> future =
				client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
						.thenAccept(resp -> {
							log2("Response statuscode: " + resp.statusCode());
							log2("Response body: " + resp.body());
							flushLogs();
						});
		
		//подождём получения, если надо
		//future.get();

	}
	
	public void test5() throws Exception {
		
		//можно задать в HttpClient свой ExecutorService
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		
		HttpClient client = HttpClient.newBuilder()
			  .executor(executorService)
			  .build();
		
		HttpRequest request = HttpRequest.newBuilder(URI.create(DemoAuxDataSamples.url_get_example)).build();
		
		CompletableFuture<String> r = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
						.thenApply(response -> response.body());
		log2(r.get());
		
		
	}

	

	@Override
	protected void doInit() throws Exception {
		options().hlComments = false;
	}

	public static void main(String[] args) {
		DemoBase.run(D_HttpClient.class);
	}

}
