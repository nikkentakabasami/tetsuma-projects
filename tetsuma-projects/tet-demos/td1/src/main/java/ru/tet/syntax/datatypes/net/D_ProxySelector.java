package ru.tet.syntax.datatypes.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.util.Collections;
import java.util.List;

import ru.tet.aux.swing.DemoBase;

public class D_ProxySelector extends DemoBase {

	public void test1() throws Exception {
		/*
		 */

		ProxySelector selector = ProxySelector.getDefault();
		URI uri = URI.create("http://example.com");
		List<Proxy> proxies = selector.select(uri);
		for (Proxy p : proxies) {
			if (p.type() == Proxy.Type.DIRECT) {
				log2("DIRECT соединение без прокси");
			} else {
				InetSocketAddress addr = (InetSocketAddress) p.address();
				log2(p.type() + " через " + addr.getHostName() + ":" + addr.getPort());
			}
		}
	}

	public void test2() throws Exception {
		/*
		
		 */

		ProxySelector mySelector = new ProxySelector() {
			@Override
			public List<Proxy> select(URI uri) {
				// всегда использовать один HTTP прокси 
				return Collections.singletonList(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy.example.com", 8080)));
			}

			@Override
			public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
			}
		};

		ProxySelector.setDefault(mySelector);

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
		DemoBase.run(D_ProxySelector.class);
	}

}
