package ru.tet.syntax.datatypes.net;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

import ru.tet.aux.swing.DemoBase;

public class D_URI extends DemoBase {

	URI uri1, uri2, uri3, uri4;

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

		URI uri2 = URI.create("https://example.com");

		log2Splitter();
		log2(uri1);

		logEval1(

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

		logEval2(
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

		logEval3(
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

		logEval1(
				uri1 = URI.create("https://www.example.com:8080/path/to/resource?param1=hello#bob"),
				uri2 = URI.create("https://www.example.com:8080/path"),
				uri3 = uri2.relativize(uri1),

				uri2.resolve("p1/p2"),
				uri2.resolve("?param1=value1"),
				uri2.resolve("#fragment"),

				URI.create("/path/to/../resource?param1=hello").normalize(),

				uri1 = URI.create("http://example.com"),
				uri2 = URI.create("/have/only/path"),
				uri3 = URI.create("#onlyFragment"),
				uri2.resolve(uri3),
				uri1.resolve(uri2).resolve(uri3)

		);

	}

	public void test3() throws Exception {
		/*
		URI(String scheme, String authority, String path, String query, String fragment)
		*/

		logEval1(
				new URI("http", "localhost:8090", "/demo-52", "p1=hi", "frag1"),
				D_URI.class.getClassLoader().getResource("mytest.txt"),
				Path.of("../pom.xml").toUri()

		);

		
		
	}

	public void test4() throws Exception {

	}

	public static void main(String[] args) {
		DemoBase.run(D_URI.class);
	}

}
