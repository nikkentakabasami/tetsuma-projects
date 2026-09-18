package ru.tet.syntax.datatypes.net;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import ru.tet.aux.DemoAuxDataSamples;
import ru.tet.aux.swing.DemoBase;

public class D_HttpRequest extends DemoBase {

	public void test1() throws Exception {
		/*
		HttpRequest
		запрос, который будет отправлен через HttpClient
		
		Создаётся через Builder.
		
		Builder	HttpRequest.newBuilder()
		Builder	HttpRequest.newBuilder(URI uri)
		Builder	HttpRequest.newBuilder(HttpRequest request, BiPredicate<String,String> filter)
		
		
		 */

		//простой get-запрос
		HttpRequest r1 =
				HttpRequest.newBuilder()
						.uri(URI.create(DemoAuxDataSamples.url_get_example))
						.build();

		HttpRequest r2 =
				HttpRequest.newBuilder(r1, (name, value) -> !name.equalsIgnoreCase("Foo-Bar"))
						.build();

		HttpRequest.newBuilder(new URI("https://postman-echo.com/get"));

		HttpRequest.newBuilder()
				.uri(URI.create(DemoAuxDataSamples.url_get_example));

	}

	public void test2() throws Exception {
		/*
		
		 */

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
		DemoBase.run(D_HttpRequest.class);
	}

}
