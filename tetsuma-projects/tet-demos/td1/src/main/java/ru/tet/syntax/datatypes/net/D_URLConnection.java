package ru.tet.syntax.datatypes.net;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import ru.tet.aux.DemoAuxDataSamples;
import ru.tet.aux.swing.DemoBase;

public class D_URLConnection extends DemoBase {

	@Override
	protected void doInit() throws Exception {
		options().hlComments = false;
	}

	public void test1() throws Exception {
		/*
		
		java.net.URLConnection
		get запрос
		
		 */

		URL url1 = URI.create(DemoAuxDataSamples.url_get_example).toURL();
		URLConnection cnn = url1.openConnection();

		//заголовки
		log2("headers:");
		cnn.getHeaderFields().keySet().forEach(key -> {

			String value = cnn.getHeaderField(key);
			log2(key, "=", value);
		});

		logExpr1(() -> {
			//распарсить дату
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
			LocalDateTime ldt1 = LocalDateTime.ofInstant(Instant.ofEpochMilli(cnn.getDate()), ZoneId.systemDefault());
			String dts = dateTimeFormatter.format(ldt1);
			return dts;
		});

		//Вспомогательные методы, для получения данных из заголовков
		logEval1(

				cnn.getContentLength(),
				cnn.getContentType(),
				cnn.getContentEncoding(),
				cnn.getDate(),
				cnn.getLastModified(),
				cnn.getExpiration(),
				cnn.getConnectTimeout());

		//чтение данных
		InputStream is1 = cnn.getInputStream();

		try (InputStreamReader myReader = new InputStreamReader(is1, StandardCharsets.UTF_8);) {
			CharBuffer cb1 = CharBuffer.allocate(1024);
			while ((myReader.read(cb1)) != -1) {
				cb1.flip();
				log2Inline(cb1.toString());
			}
		}

	}

	public void test2() throws Exception {
		/*
		java.net.URLConnection
		post запрос x-www-form-urlencoded с параметрами
		
		https://httpbin.org/get
		
		 */

		URL url1 = URI.create(DemoAuxDataSamples.url_post_httpbin).toURL();
		HttpURLConnection cnn = (HttpURLConnection) url1.openConnection();

		//не обязательно
		cnn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

		//параметры запроса
		String params = "param1=яблоко&param2=вишня";
		params = URLEncoder.encode(params, StandardCharsets.UTF_8);

		cnn.setDoOutput(true);
		OutputStreamWriter out = new OutputStreamWriter(cnn.getOutputStream());
		out.write(params);
		out.close();

		int responseCode = cnn.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			//чтение данных
			String response = new String(cnn.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
			log2(response);
		} else {
			log2("Error: HTTP Response code - " + responseCode);
			cnn.disconnect();
		}

		log2Splitter();

	}

	public void test3() throws Exception {
		/*
		java.net.URLConnection
		post запрос
		отправка JSON
		
		 */

		URL url1 = URI.create(DemoAuxDataSamples.url_post_httpbin).toURL();
		HttpURLConnection cnn = (HttpURLConnection) url1.openConnection();

		cnn.setRequestMethod("POST");
		cnn.setRequestProperty("Content-Type", "application/json; utf-8");
		cnn.setRequestProperty("Accept", "application/json");

		cnn.setDoOutput(true);

		OutputStreamWriter out = new OutputStreamWriter(cnn.getOutputStream());
		out.write(DemoAuxDataSamples.jsonString1);
		out.close();

		int responseCode = cnn.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			//чтение данных
			InputStream is1 = cnn.getInputStream();
			try (InputStreamReader myReader = new InputStreamReader(is1, StandardCharsets.UTF_8);) {
				CharBuffer cb1 = CharBuffer.allocate(1024);
				while ((myReader.read(cb1)) != -1) {
					cb1.flip();
					log2Inline(cb1.toString());
				}
			}
		} else {
			log2("Error: HTTP Response code - " + responseCode);
			cnn.disconnect();
		}

		log2Splitter();

	}

	InetAddress a1, a2, a3;

	public void test4() throws Exception {
		/*
		java.net.InetAddress
		IP адрес
		
		 */

		byte IPAddress[] = { 125, 0, 0, 1 };
		byte[] IPAddress2 = { 105, 22, (byte) 223, (byte) 186 };
		logEval1(
				a1 = InetAddress.getLocalHost(),
				a1.getHostName(),
				a1.getHostAddress(),

				InetAddress.getByName("45.22.30.39"),
				Arrays.toString(InetAddress.getAllByName("172.19.25.29")),
				InetAddress.getByAddress(IPAddress),
				InetAddress.getByAddress("gfg.com", IPAddress2));

	}

	public static void main(String[] args) {
		DemoBase.run(D_URLConnection.class);
	}

}
