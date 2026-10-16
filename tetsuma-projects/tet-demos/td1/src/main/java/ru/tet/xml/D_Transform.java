package ru.tet.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import ru.tet.aux.swing.DemoBase;

public class D_Transform extends DemoBase {

	public void test1() throws Exception {
		/*
		 */
		InputStream isXml = getClass().getClassLoader().getResourceAsStream("xmls/staff-simple.xml");
		InputStream isXslt = getClass().getClassLoader().getResourceAsStream("xmls/staff-xml-html.xslt");

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(isXml);

		try (FileOutputStream output = new FileOutputStream("target/staff.html")) {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer(new StreamSource(isXslt));
			transformer.transform(new DOMSource(doc), new StreamResult(output));
		}

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
		DemoBase.run(D_Transform.class);
	}

}
