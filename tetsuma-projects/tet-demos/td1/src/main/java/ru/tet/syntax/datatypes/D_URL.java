package ru.tet.syntax.datatypes;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;

import ru.tet.aux.swing.DemoBase;

//Шаблон для создания новых демо
public class D_URL extends DemoBase {

	public void test1() throws Exception {
		/*
		java.net.URI
		Uniform Resource Identifier - унифицированный идентификатор ресурса.
		URL - это частный случай URI.
		URI не обязательно указывает КАК получить ресурс.
		

		Примеры:
		urn:isbn:0451450523	URN - книга с определённым ISBN
		tel:+1-816-555-1212
		mailto:user@example.com 	почта
		https://www.example.com/folder/page.html?q=example
		file:///home/tetsuma/work/mytest.txt
		
		 */

		URI uri1 = new URI("abc://admin:admin@geeksforgeeks.org:1234/path/data?key=value&key2=value2#fragid1");

		log2Splitter();
		log2(uri1);

		logEval(

				//Схема (Scheme) - протокол, ассоциированный с URI
				//Например "http, https, ftp, mailto, file"
				//После неё стоит разделитель ":" или "://" для URL
				uri1.getScheme(),

				//аутентификация
				uri1.getUserInfo(),

				//хост
				uri1.getHost(),

				//порт
				uri1.getPort(),

				//Состоит из нескольких частей: аутентификация, хост, порт
				uri1.getAuthority(),

				//адрес внутри сервера, указывающий на ресурс
				uri1.getPath(),

				//параметры запроса. Начинается с символа ?
				uri1.getQuery(),

				//идентификатор вторичных ресурсов
				uri1.getFragment(),

				//всё между : и #. То есть между хостом и фрагментом.
				uri1.getSchemeSpecificPart(),

				//содержит схему
				uri1.isAbsolute(),

				//абсолютный адрес, где после схемы идет произвольный текст, а не путь с наклонными чертами.
				uri1.isOpaque()

		);

		uri1 = new URI("mailto:test@test.com");
		log2Splitter();
		log2(uri1);

		logEval(
				uri1.getScheme(),
				uri1.getUserInfo(),
				uri1.getHost(),
				uri1.getPort(),
				uri1.getAuthority(),
				uri1.getPath(),
				uri1.getQuery(),
				uri1.getFragment(),
				uri1.getSchemeSpecificPart(),
				uri1.isAbsolute(),
				uri1.isOpaque());

		uri1 = new URI("string:///my/resource.java");
		log2Splitter();
		log2(uri1);

		logEval(
				uri1.getScheme(),
				uri1.getUserInfo(),
				uri1.getHost(),
				uri1.getPort(),
				uri1.getAuthority(),
				uri1.getPath(),
				uri1.getQuery(),
				uri1.getFragment(),
				uri1.getSchemeSpecificPart(),
				uri1.isAbsolute(),
				uri1.isOpaque());

	}

	public void test2() throws Exception {
		/*
		java.net.URL
		Указатель на ресурс в интернете
		
		protocol://host:[port]/[path[?params][#anchor]]
		
		Позвляет обмениваться запросами через http, хотя делать это напрямую неудобно.
		Удобнее использовать HttpClient
		
		Создание
		Конструкторы считаются устаревшими. Сейчас используют URI.toURL()
		Спецсимволы должны быть закодированы
		
		
		 */

		//URL url1 = new URI("http://baeldung.com/a-guide-to-java-sockets").toURL();

		URL url1 = new URI("http://java.sun.com:80/docs/books/tutorial/index.html?p1=111#DOWNLOADING").toURL();

		//создание из составных частей
		String protocol = "http";
		String host = "baeldung.com";
		String file = "/guidelines.txt";
		String fragment = "myImage";
		URL url = new URI(protocol, host, file, fragment).toURL();

		log2(url1);

		logEval(

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

	public void test3() throws Exception {
		/*
		URL
		
		InputStream openStream()
		
		 */

		URL url1 = URI.create("https://example.com").toURL();

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

	}

	public void test4() throws Exception {
		/*
		
		 */

		URL url1 = URI.create("https://example.com").toURL();

		URLConnection connection = url1.openConnection();
		connection.setDoOutput(true);

		// получили поток для отправки данных
		OutputStream output = connection.getOutputStream();
		output.write(1); // отправляем данные

		// получили поток для чтения данных
		InputStream input = connection.getInputStream();
		int data = input.read(); // читаем данные		

	}

	@Override
	protected void doInitControlPanel() throws Exception {
		addTest1Button(null);
		addTest2Button(null);
		addTest3Button(null);
		addTest4Button(null);
	}

	public static void main(String[] args) {
		DemoBase.run(D_URL.class);
	}

}
