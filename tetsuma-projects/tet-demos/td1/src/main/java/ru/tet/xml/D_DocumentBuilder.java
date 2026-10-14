package ru.tet.xml;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.file.Path;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ru.tet.aux.DemoAuxDataSamples;
import ru.tet.aux.swing.DemoBase;

public class D_DocumentBuilder extends DemoBase {

	public void test1() throws Exception {
		/*
		
		Пример считывания xml-данных через DocumentBuilder
		
		 */

		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		InputStream is1 = getClass().getClassLoader().getResourceAsStream("xmls/screen1.xml");
		Document document = builder.parse(is1);

		//или из файла
		//document = builder.parse(Path.of("pom.xml").toFile());

		Node root = document.getFirstChild();

		//считывание атрибутов корневого узла
		log2("root.getAttributes()");

		NamedNodeMap screenAttrs = root.getAttributes();
		for (int i = 0; i < screenAttrs.getLength(); i++) {
			final Node attrNode = screenAttrs.item(i);

			logEval1(
					attrNode.getNodeName(),
					attrNode.getNodeValue());

		}
		log2Splitter();

		//ищем первый таг Buttons, и его дочерние узлы
		Element buttonsEl = (Element) document.getDocumentElement().getElementsByTagName("Buttons").item(0);
		NodeList buttonElements = buttonsEl.getChildNodes();
		for (int j = 0; j < buttonElements.getLength(); j++) {
			Node buttonNode = buttonElements.item(j);
			if (buttonNode.getNodeType() != Node.ELEMENT_NODE) {
				continue;
			}
			Element buttonElement = (Element) buttonNode;

			logEval2(
					buttonElement.getAttribute("Action"),
					buttonElement.getAttribute("Color"),
					buttonElement.getElementsByTagName("T").item(0).getTextContent());
			log2Splitter();
		}

	}

	public void test2() throws Exception {
		/*
		Пример считывания 2
		
		 */

		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

		Document document = builder.parse(Path.of("pom.xml").toFile());

		Node root = document.getFirstChild();

		//Считывает атрибуты корневого тега
		NamedNodeMap attributes = root.getAttributes();
		for (int i = 0; i < attributes.getLength(); i++) {
			final Node attr;
			logEval1(
					attr = attributes.item(i),
					attr.getNodeName(),
					attr.getNodeValue());
			log2Splitter();

		}

		log2("first dependency tag");

		NodeList depsList = document.getDocumentElement().getElementsByTagName("dependency");
		Node dep1 = depsList.item(1);
		NodeList dep1Childs = dep1.getChildNodes();

		for (int i = 0; i < dep1Childs.getLength(); i++) {

			final Node dep1Child = dep1Childs.item(i);
			if (dep1Child.getNodeType() != Node.ELEMENT_NODE) {
				continue;
			}

			logEval2(
					dep1Child.getNodeName(),
					dep1Child.getTextContent());
			log2Splitter();

		}

	}

	public void test3() throws Exception {
		/*
		Пример записи
		 */
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = builder.newDocument();
		Element root = document.createElement("Products");
		document.appendChild(root);

		Element element = document.createElement("potato");
		element.setAttribute("guid", "123456");
		element.setAttribute("sort", "extra big");

		Element criteria = document.createElement("apples");
		
		
		for (String string : DemoAuxDataSamples.apples) {
			Element query = document.createElement("apple");
			criteria.appendChild(query);
			
			query.setAttribute("len", String.valueOf(string.length()));
			
			if (string.indexOf(' ')>0) {
				//для записей текстов со спецсимволами
				CDATASection queryData = document.createCDATASection(string);
				query.appendChild(queryData);
			} else {
				query.setTextContent(string);
			}
		}

		element.appendChild(criteria);
		root.appendChild(element);

		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		Properties properties = new Properties();
		properties.setProperty("method", "xml");
		properties.setProperty("indent", "yes");
		transformer.setOutputProperties(properties);
		
		StringWriter sw = new StringWriter();
		transformer.transform(new DOMSource(document), new StreamResult(sw));
		log2(sw.toString());

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
		DemoBase.run(D_DocumentBuilder.class);
	}

}
