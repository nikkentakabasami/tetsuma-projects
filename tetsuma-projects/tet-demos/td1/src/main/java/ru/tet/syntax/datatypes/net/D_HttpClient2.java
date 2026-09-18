package ru.tet.syntax.datatypes.net;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ru.tet.aux.DemoAuxDataSamples;
import ru.tet.aux.swing.DemoBase;

/**
 * Дополнительные примеры HttpClient
 */
public class D_HttpClient2 extends DemoBase {

	public void test1() throws Exception {
		/*
		post запрос - отправка текста в теле
		 */

		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request =
				HttpRequest.newBuilder()
						.uri(URI.create(DemoAuxDataSamples.url_post_httpbin))
						.headers("Content-Type", "text/plain;charset=UTF-8")
						.POST(BodyPublishers.ofString(DemoAuxDataSamples.sampleString))
						.build();

		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

		logEval1(
				response.body(),
				response.statusCode());

	}

	public void test2() throws Exception {

		/*
		post запрос - отправка и получение json
		 */

		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request =
				HttpRequest.newBuilder()
						.uri(URI.create(DemoAuxDataSamples.url_post_httpbin))
						.header("Content-Type", "application/json; utf-8")
						.header("Accept", "application/json")
						.POST(BodyPublishers.ofString(DemoAuxDataSamples.jsonString1))
						//		        .POST(BodyPublishers.ofFile(Path.of("file.json")))
						.build();

		//сохраняем в файл
		HttpResponse<Path> response = client.send(request, BodyHandlers.ofFile(Path.of("target/resp2.json")));
		log2(Files.readString(response.body()));

	}

	public void test3() throws Exception {

		/*
		post запрос - отправка параметров запроса
		 */

		HttpClient client = HttpClient.newHttpClient();

		String params = "username=john.doe&password=s3cr3t!";

		params = URLEncoder.encode(params, StandardCharsets.UTF_8);

		HttpRequest request =
				HttpRequest.newBuilder()
						.uri(URI.create(DemoAuxDataSamples.url_post_httpbin))
						.header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
						.POST(BodyPublishers.ofString(params))
						.build();

		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

		logEval1(
				response.body(),
				response.statusCode());

	}

	public void test4() throws Exception {
		/*
		get-запрос - считывание файла
		 */

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request =
				HttpRequest.newBuilder(URI.create("https://httpbin.org/static/favicon.ico"))
						.headers("Accept-Enconding", "gzip, deflate") //можно отправить в упакованном виде
						.build();

		HttpResponse<Path> response = client.send(request, BodyHandlers.ofFile(Path.of("target/favicon.ico")));
		log2(response.body());

	}

	public void test5() throws Exception {

	}

	@Override
	protected void doInit() throws Exception {
		options().hlComments = false;
	}

	public static void main(String[] args) {
		DemoBase.run(D_HttpClient2.class);
	}

}
