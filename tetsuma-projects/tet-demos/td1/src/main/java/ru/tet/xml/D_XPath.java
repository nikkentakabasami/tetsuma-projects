package ru.tet.xml;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ru.tet.aux.swing.DemoBase;

public class D_XPath extends DemoBase {

	public void test1() throws Exception {
		/*
		XPath
		язык запросов к элементам XML. Он позволит создать выражение запроса и получить интересующие данные из документа.
		 */

		Document document = D_DocumentBuilder2.readScreenDocument();

		XPathFactory pathFactory = XPathFactory.newInstance();
		XPath xpath = pathFactory.newXPath();
		XPathExpression expr = xpath.compile("/Screen/Buttons/Button[1]/T");
		NodeList nodeList = (NodeList) expr.evaluate(document, XPathConstants.NODESET);

		Node node = nodeList.item(0);

		logEval2(
				node.getNodeName(),
				node.getTextContent());

	}

	public void test2() throws Exception {
		/*
		
nodename	Selects all nodes with the name "nodename"
/	Selects from the root node
//	Selects nodes in the document from the current node that match the selection no matter where they are
.	Selects the current node
..	Selects the parent of the current node
@	Selects attributes		
				
		 */

		Document document = D_DocumentBuilder2.readTutorialsDocument();

		XPathFactory pathFactory = XPathFactory.newInstance();
		XPath xpath = pathFactory.newXPath();

		/*
		//нумерация с 1
		testXPathNode("/Tutorials/Tutorial[1]", xpath, document);
		
		testXPathNodeset("/Tutorials/Tutorial[position()<2]", xpath, document);

		//we’re looking for every <Tutorial> element that has a descendant <title> with the text 'Guava'
		testXPathNodeset("//Tutorial[descendant::title[text()='Guava']]", xpath, document);
		*/
		
		testXPathNodeset("//Tutorial", xpath, document);
		testXPathNodeset("/Tutorials", xpath, document);
		testXPathNodeset("Tutorials", xpath, document);

		/*
		testXPathNodeset("", xpath, document);
		
//		testXPath("/Tutorials/Tutorial[first()]", xpath, document);
				*/

	}

	
	
	public void test3() throws Exception {
		/*
		
		 */
		

		Document document = D_DocumentBuilder2.readScreenDocument();

		XPathFactory pathFactory = XPathFactory.newInstance();
		XPath xpath = pathFactory.newXPath();

		//первая кнопка. нумерация с 1
		testXPathNodeset("//Buttons/Button[1]", xpath, document);
		
		//последняя кнопка.
		testXPathNodeset("//Buttons/Button[last()]", xpath, document);
		testXPathNodeset("//Buttons/Button[last()-1]", xpath, document);
		
		
		testXPathNodeset("/Screen/Buttons/Button[position()>1]", xpath, document);
		
		//Ищем тэг Button, у которого в потомках есть <T>Возврат</T>
		testXPathNodeset("//Button[descendant::T[text()='2)Возврат']]", xpath, document);
		
		//корневой узел Screen
		testXPathNodeset("/Screen", xpath, document);
		
		//все тэги <Button>
		testXPathNodeset("//Button", xpath, document);
		
		//все тэги <T> внутри всех тегов <Button>
		testXPathNodeset("//Button/T", xpath, document);

		//все дочерние элементы первой кнопки
		testXPathNodeset("//Button[1]/*", xpath, document);
		
		//все элементы документа
		testXPathNodeset("//*", xpath, document);
	
		
		// <Button> с атрибутом Action='HISTORY'
		testXPathNodeset("//Button[@Action='HISTORY']", xpath, document);
		
		// <Button> с элементом <price>, значение которого >12
		testXPathNodeset("//Button[price>12.00]/T", xpath, document);
		
		//все дочерние элементы <Button>, с заданными атрибутами
		testXPathNodeset("//Button/*[@*]", xpath, document);
		
		//объединение путей
		testXPathNodeset("//Attribute/V | //Note/T", xpath, document);
		
		
		testXPathNodeset(".", xpath, document);		
		
		testXPathNodeset("./*", xpath, document);		
		
		//выбрать все узлы-атрибуты первой кнопки
		testXPathNodeset("//Buttons/Button[1]/@*", xpath, document);
		
		//атрибут Action первой кнопки
		testXPathNodeset("//Buttons/Button[1]/@Action", xpath, document);
		
		testXPathNodeset("//Button[1]/..", xpath, document);
		
		
	}

	public void test4() throws Exception {
		/*
		
		 */

		Document document = D_DocumentBuilder2.readScreenDocument();

		XPathFactory pathFactory = XPathFactory.newInstance();
		XPath xpath = pathFactory.newXPath();

		testXPathNodeset(".", xpath, document);		
		testXPathNodeset(".", xpath, document);		
		testXPathNodeset(".", xpath, document);		
		testXPathNodeset(".", xpath, document);		
		
		
	}

	@Override
	public void test5() throws Exception {
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		InputStream is1 = D_DocumentBuilder2.class.getClassLoader().getResourceAsStream("xmls/section.xml");
		Document document = builder.parse(is1);

		XPathFactory pathFactory = XPathFactory.newInstance();
		XPath xpath = pathFactory.newXPath();
		
		//Аналог: /library/section/book
		testXPathNodeset("/library/child::section/child::book", xpath, document);		
		
		//  //book
		testXPathNodeset("/library/descendant::book", xpath, document);		
		testXPathNodeset("/library/section/book/parent::section", xpath, document);		
		testXPathNodeset("/library/section/book/ancestor::section", xpath, document);		
		testXPathNodeset("/library/section/book/title/following-sibling::author", xpath, document);		
		
		
		testXPathNodeset("/library/section/book/title/preceding-sibling::author", xpath, document);		
		
		//находит все книги, которые идут после каждой найденной книги в документе
		testXPathNodeset("//book/following::book", xpath, document);		
		testXPathNodeset("//book/preceding::book", xpath, document);		
		testXPathNodeset("/library/section/book/@id", xpath, document);		
		testXPathNodeset("/library/section/book/title/self::title", xpath, document);
		
		//Что делает: находит заголовки title у всех узлов book, включая сами book как контекст.
		testXPathNodeset("/library/descendant-or-self::book/title", xpath, document);		
	}
	
	/**


Query: //book/preceding::book
Что делает: находит все книги, которые стоят раньше текущей в документном порядке.
Attribute axis (атрибуты узла)

Query: /library/section/book/@id
Что делает: возвращает значения атрибута id у каждого book.
Альтернатива: //book/@id
Self axis (сам текущий узел)

Query: /library/section/book/title/self::title
Что делает: возвращает сам заголовок (иногда полезно в сложных выражениях для явной проверки типа узла).
Descendant-or-self axis (потомки и сам узел)

Query: /library/descendant-or-self::book/title
Что делает: находит заголовки title у всех узлов book, включая сами book как контекст.
	 */
	
	
	
	
	
	
	@Override
	protected void doInit() throws Exception {
		options().hlComments = false;
		options().logSources = false;
	}

	public static void main(String[] args) {
		DemoBase.run(D_XPath.class);
	}


	void testXPathNode(String expression, XPath xpath, Document document) throws Exception {
		log2Splitter();
		log2(expression);
		XPathExpression expr = xpath.compile(expression);
		Node node = (Node) expr.evaluate(document, XPathConstants.NODE);
		if (node!=null) {
			log2(node.getNodeName(), node.getTextContent());
		}
	}

	void testXPathNodeset(String expression, XPath xpath, Document document) throws Exception {
		log2Splitter();
		log2Bold(expression);
		XPathExpression expr = xpath.compile(expression);
		NodeList nodelist = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		for (int i = 0; i < nodelist.getLength(); i++) {
			log2Blue(nodelist.item(i).getNodeName());
			log2("'"+nodelist.item(i).getTextContent()+"'");
		}
	}	
	
}
