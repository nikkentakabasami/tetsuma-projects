package ru.tet.xml;

import java.io.InputStream;
import java.nio.file.Path;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ru.tet.aux.swing.DemoBase;
import ru.tet.jaxbmodels.ScreenDom;
import ru.tet.jaxbmodels.ScreenDom.AttributeDom;
import ru.tet.jaxbmodels.ScreenDom.ButtonDom;
import ru.tet.jaxbmodels.ScreenDom.DDom;

public class D_DocumentBuilder2 extends DemoBase {

	public static Document readScreenDocument() throws Exception {
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		InputStream is1 = D_DocumentBuilder2.class.getClassLoader().getResourceAsStream("xmls/screen1.xml");
		Document document = builder.parse(is1);
		return document;
	}
	public static Document readTutorialsDocument() throws Exception {
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		InputStream is1 = D_DocumentBuilder2.class.getClassLoader().getResourceAsStream("xmls/tutorials1.xml");
		Document document = builder.parse(is1);
		return document;
	}

	public void test1() throws Exception {
		/*
		Большой пример по считыванию данных в объект
		
		 */

		ScreenDom screen = new ScreenDom();

		Document document = readScreenDocument();
		Node root = document.getFirstChild();

		//считывание атрибутов корневого узла
		log2("root.getAttributes()");

		NamedNodeMap screenAttrs = root.getAttributes();
		for (int i = 0; i < screenAttrs.getLength(); i++) {
			final Node attrNode = screenAttrs.item(i);

			logEval1(
					attrNode.getNodeName(),
					attrNode.getNodeValue());

			screen.getAttributes().put(attrNode.getNodeName(), attrNode.getNodeValue());
		}
		log2Splitter();

		//считывание дочерних элементов
		NodeList el1Childs = root.getChildNodes();
		for (int i = 0; i < el1Childs.getLength(); i++) {
			Node child = el1Childs.item(i);

			if (child.getNodeName().equals("Title")) {
				screen.setTitle(child.getTextContent());
			}
			if (child.getNodeName().equals("Description")) {
				screen.setDescription(child.getTextContent());
			}
		}

		logEval2(
				document.getDocumentElement().getNodeName(),
				document.getDocumentElement().getNodeValue());

		NodeList buttonElements = document.getDocumentElement().getElementsByTagName("Button");
		for (int j = 0; j < buttonElements.getLength(); j++) {
			Node buttonElement = buttonElements.item(j);
			NamedNodeMap buttonAttrs = buttonElement.getAttributes();

			ButtonDom buton = new ButtonDom();
			screen.getButtonList().add(buton);
			for (int i = 0; i < buttonAttrs.getLength(); i++) {
				final Node buttonAttr = buttonAttrs.item(i);

				buton.getAttributes().put(buttonAttr.getNodeName(), buttonAttr.getNodeValue());
			}

			el1Childs = buttonElement.getChildNodes();

			for (int i = 0; i < el1Childs.getLength(); i++) {
				Node node1 = el1Childs.item(i);

				if (node1.getNodeName().equals("T")) {
					buton.setT(node1.getTextContent());
				}
				if (node1.getNodeName().equals("D")) {
					DDom d = new DDom();
					d.setD(node1.getTextContent());

					NamedNodeMap node1Attrs = node1.getAttributes();
					if (node1Attrs.getLength() != 0) {
						for (int k = 0; k < node1Attrs.getLength(); k++) {
							Node node1Attr = node1Attrs.item(k);
							d.getAttributes().put(node1Attr.getNodeName(), node1Attr.getNodeValue());
						}
					}

					buton.getDList().add(d);
				}
			}
		}

		NodeList elements = document.getDocumentElement().getElementsByTagName("Attribute");
		for (int j = 0; j < elements.getLength(); j++) {
			Node el1 = elements.item(j);
			NamedNodeMap el1Attrs = el1.getAttributes();

			AttributeDom attributeDom = new AttributeDom();

			for (int i = 0; i < el1Attrs.getLength(); i++) {
				Node el1Attr = el1Attrs.item(i);
				attributeDom.getAttributes().put(el1Attr.getNodeName(), el1Attr.getNodeValue());
			}

			el1Childs = el1.getChildNodes();
			for (int i = 0; i < el1Childs.getLength(); i++) {
				Node el1Child = el1Childs.item(i);
				if (el1Child.getNodeName().equals("V")) {
					attributeDom.setV(el1Child.getTextContent());
				}
			}
			screen.getAttributeList().add(attributeDom);
			//Итоговым объектом является заполненная сущность screen класса Screen
		}

		log2(screen);

	}

	@Override
	public void test2() throws Exception {

		/*
		Тест по считыванию данных 
		 */
		
		Document document = readScreenDocument();
		Node root = document.getFirstChild();

		//атрибуты корневого тега
		log2("root.getAttributes()");

		NamedNodeMap screenAttrs = root.getAttributes();
		for (int i = 0; i < screenAttrs.getLength(); i++) {
			final Node attrNode = screenAttrs.item(i);

			logEval1(
					attrNode.getNodeName(),
					attrNode.getNodeValue());

		}
		log2Splitter();
		log2("root.getChildNodes()");

		//Создается список всех дочерних узлов
		NodeList el1Childs = root.getChildNodes();

		//В цикле выполняется поиск и присвоение значений согласно заданным условиям
		for (int i = 0; i < el1Childs.getLength(); i++) {
			Node child = el1Childs.item(i);

			log2(
					child.getNodeType(),
					child.getNodeName(),
					"'" + child.getTextContent() + "'");

			//			logEval2(
			//					child.getNodeType(),
			//					child.getNodeName(),
			//					child.getTextContent());

		}

	}
	public void test3() throws Exception {
		/*
		Document - методы
		 */

		Document document = readScreenDocument();
		logEval1(
				document.getDocumentURI(),
				document.getDomConfig().getParameterNames().item(0),
				document.getXmlEncoding(),
				document.getXmlVersion());

		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		document = builder.parse(Path.of("pom.xml").toFile());

		logEval2(
				document.getDoctype(),
				document.getDocumentURI(),
				document.getDomConfig(),
				document.getXmlEncoding(),
				document.getXmlVersion());

		log2Splitter();
		for (int i = 0; i < document.getDomConfig().getParameterNames().getLength(); i++) {
			String pn = document.getDomConfig().getParameterNames().item(i);
			String pv = String.valueOf(document.getDomConfig().getParameter(pn));

			log2(pn, pv);

		}

	}

	public void test4() throws Exception {
		/*
		Node
		 */

		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = builder.parse(Path.of("pom.xml").toFile());

		Element root = document.getDocumentElement();
		NodeList buttonElements = root.getElementsByTagName("properties");

		Node propsNode = buttonElements.item(0);

		for (int i = 0; i < propsNode.getChildNodes().getLength(); i++) {
			Node node = propsNode.getChildNodes().item(i);

			logEval1(
					node.getNodeType(),
					node.getNodeName(),
					node.getTextContent(),
					node.getLocalName());
			log2Splitter();

		}

	}



	@Override
	protected void doInit() throws Exception {
		options().hlComments = false;
	}

	public static void main(String[] args) {
		DemoBase.run(D_DocumentBuilder2.class);
	}

}
