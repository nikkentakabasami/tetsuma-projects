package ru.tet.syntax.datatypes.net;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.IOUtils;

import ru.tet.aux.DemoAuxDataSamples;
import ru.tet.aux.swing.DemoBase;

//Шаблон для создания новых демо
public class D_URL extends DemoBase {

	@Override
	protected void doInit() throws Exception {
		options().hlComments = false;
	}

	public void test1() throws Exception {
		/*
		java.net.URL
		Указатель на ресурс в интернете
		
		protocol://host:[port]/[path[?params][#anchor]]
		
		Позвляет обмениваться запросами через http, хотя делать это напрямую неудобно.
		Удобнее использовать HttpClient
		
		Создание
		Конструкторы считаются устаревшими. Сейчас используют URI.toURL()
		Спецсимволы должны быть закодированы
		
		URI(String scheme, String host, String path, String fragment);
		
		
		 */

		//URL url1 = new URI("http://baeldung.com/a-guide-to-java-sockets").toURL();

		URL url1 = new URI("http://java.sun.com:80/docs/books/tutorial/index.html?p1=111#DOWNLOADING").toURL();

		//создание из составных частей
		URL url2 = new URI("http", "baeldung.com", "/guidelines.txt", "myImage").toURL();

		//URL url3 = D_URI.class.getClassLoader().getResource("mytest.txt");

		logEval(

				url1,
				url2,

				//аутентификация
				url1.getUserInfo(),

				url1.getProtocol(),

				//хост
				url1.getHost(),

				//порт
				url1.getPort(),

				//адрес внутри сервера, указывающий на ресурс
				url1.getPath(),

				//параметры запроса. Начинается с символа ?
				url1.getQuery(),

				//anchor
				url1.getRef(),

				//path+query
				url1.getFile(),

				//Состоит из нескольких частей: аутентификация, хост, порт
				url1.getAuthority()

		);

	}

	URL url1, url2, url3, url4, url5;

	public void test2() throws Exception {

		logEval(

				getClass().getClassLoader().getResource("mytest.txt"),
				getClass().getResource("/mytest.txt"),

				url1 = URI.create("http://example.com").toURL()

		);

		//нельзя создать url для не абсолютных адресов - кинет ошибку
		//				url2 = URI.create("/have/only/path").toURL(),
		//				url3 = URI.create("#onlyFragment").toURL(),
		//				url4 = new URI("index.html").toURL()

	}

	public void test3() throws Exception {
		/*
		URL
		
		InputStream openStream()
		
		 */

		URL url1 = URI.create(DemoAuxDataSamples.url_get_example).toURL();

		try (BufferedReader in = new BufferedReader(new InputStreamReader(url1.openStream()))) {
			String line;
			while ((line = in.readLine()) != null) {
				log2(line);
			}
		}

		log2Splitter();

		String contents = IOUtils.toString(url1.openStream());
		log2(contents);

		log2Splitter();

		InputStream input = url1.openStream();
		byte[] buffer = input.readAllBytes();
		contents = new String(buffer);
		log2(contents);

		log2Splitter();
		byte[] bytes = url1.openStream().readAllBytes();
		contents = new String(bytes, StandardCharsets.UTF_8);
		log2(contents);

	}

	public void test4() throws Exception {
		/*
		
		 */

		URL url1 = URI.create("https://example.com").toURL();

		URLConnection cnn = url1.openConnection();

		//заголовки
		log2("headers:");
		cnn.getHeaderFields().keySet().forEach(key -> {

			String value = cnn.getHeaderField(key);
			log2(key, "=", value);
		});

		logExpr1(() -> {
			Instant instant = Instant.ofEpochMilli(cnn.getDate());
			LocalDateTime ldt1 = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
			String dts = dateTimeFormatter.format(ldt1);
			return dts;
		});

		logEval1(

				cnn.getContentLength(),
				cnn.getContentType(),
				cnn.getContentEncoding(),
				cnn.getDate(),
				cnn.getLastModified(),
				cnn.getExpiration(),
				cnn.getConnectTimeout());

		/*
		int 	getContentLength()
		String 	getContentType()
		String 	getContentEncoding()
		long 	getDate()
		long	getLastModified()
		long	getExpiration()
		int	getConnectTimeout() * 		
		 */

		//чтение данных
		InputStream is1 = cnn.getInputStream();
		byte[] bytes = is1.readAllBytes();
		String contents = new String(bytes, StandardCharsets.UTF_8);
		log2(contents);

		/*
		//отправка данных
		connection.setDoOutput(false);
		OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
		out.write("string=" + stringToReverse);
		out.close();
		
		*/

	}

	public static void main(String[] args) {
		DemoBase.run(D_URL.class);
	}

}
