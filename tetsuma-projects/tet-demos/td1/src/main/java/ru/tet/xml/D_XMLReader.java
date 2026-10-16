package ru.tet.xml;

import java.io.InputStream;
import java.util.StringJoiner;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import ru.tet.aux.swing.DemoBase;

public class D_XMLReader extends DemoBase {

	public void test1() throws Exception {
		/*
		org.xml.sax.XMLReader
		
		SAX - Simple API for XML
		Потоковый парсер XML, основанный на обработке событий
		Он последовательно читает XML вызывает методы обработчика для событий: startElement, endElement, characters
		Не строит полного дерева документа в памяти, поэтому быстрый и нетребовательный.
		 */

		//Создаём XMLReader
		SAXParserFactory spf = SAXParserFactory.newInstance();
		spf.setNamespaceAware(true);
		SAXParser saxParser = spf.newSAXParser();
		XMLReader xmlReader = saxParser.getXMLReader();


		xmlReader.setContentHandler(new SaxHandler1());

		InputStream is1 = getClass().getClassLoader().getResourceAsStream("xmls/screen1.xml");

		xmlReader.parse(new InputSource(is1));

		//Считать можно и из URI
		//    xmlReader.parse("http://localhost/datahub/myData.xml");

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

	//Обработчик, который выводит встретившиеся элементы, и текст внутри них
	class SaxHandler1 extends DefaultHandler {

		public void startElement(String nsURI, String localName, String rawName, Attributes attributes)
				throws SAXException {
			//выводим атрибуты тега
			StringJoiner sj = new StringJoiner(", "," [","]");
			for (int i = 0; i < attributes.getLength(); i++) {
				sj.add(attributes.getLocalName(i)+"="+attributes.getValue(i));
			}
			log2Blue(localName + sj.toString());
		}

		public void endElement(String uri, String localName, String qName) throws SAXException {
			log2Blue("/" + localName);

		}

		public void characters(char[] ch, int start, int length) {
			log2("'" + new String(ch, start, length) + "'");
		}
	}

	@Override
	protected void doInit() throws Exception {
		options().hlComments = false;
	}

	public static void main(String[] args) {
		DemoBase.run(D_XMLReader.class);
	}

}
