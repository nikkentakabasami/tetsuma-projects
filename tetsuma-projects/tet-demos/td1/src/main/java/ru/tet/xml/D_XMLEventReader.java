package ru.tet.xml;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.Namespace;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import ru.tet.aux.swing.DemoBase;
import ru.tet.jaxbmodels.Screen;
import ru.tet.jaxbmodels.Screen.Button;

public class D_XMLEventReader extends DemoBase {

	public void test1() throws Exception {
		/*
		Пример считывания данных в объект.
		 */

		Screen screen = new Screen();
		screen.setButtonList(new ArrayList<>());

		InputStream is1 = getClass().getClassLoader().getResourceAsStream("xmls/screen1.xml");

		XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
		XMLEventReader reader = xmlInputFactory.createXMLEventReader(is1);

		Button button = null;

		while (reader.hasNext()) {
			XMLEvent nextEvent = reader.nextEvent();
			if (nextEvent.isStartElement()) {
				StartElement startElement = nextEvent.asStartElement();

				String tagName = startElement.getName().getLocalPart();

				switch (tagName) {
				case "Screen":
					Attribute stage = startElement.getAttributeByName(new QName("Stage"));
					screen.setStage(stage.getValue());
					break;
				case "Title":
					screen.setTitle(reader.getElementText());
					break;
				case "Description":
					screen.setDescription(reader.getElementText());
					break;
				case "Description2":
					//считываем XML CDATA
					log2("desc2:",reader.getElementText());
					break;

				case "Button":
					button = new Button();
					button.setAction(startElement.getAttributeByName(new QName("Action")).getValue());
					button.setColor(startElement.getAttributeByName(new QName("Color")).getValue());
					button.setCondition(startElement.getAttributeByName(new QName("Condition")).getValue());

					break;

				case "T":
					if (button != null) {
						button.setT(reader.getElementText());
					}
					break;

				}
			}

			if (nextEvent.isEndElement()) {
				EndElement endElement = nextEvent.asEndElement();
				if (endElement.getName().getLocalPart().equals("Button")) {
					screen.getButtonList().add(button);
					button = null;
				}
			}

		}

		log2(screen);

	}

	public void test2() throws Exception {
		/*
		Простой парсинг.
		 */

		InputStream is1 = getClass().getClassLoader().getResourceAsStream("xmls/screen1.xml");

		XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
		XMLEventReader reader = xmlInputFactory.createXMLEventReader(is1);

		while (reader.hasNext()) {
			XMLEvent nextEvent = reader.nextEvent();
			if (nextEvent.isStartElement()) {
				StartElement startElement = nextEvent.asStartElement();

				String tagName = startElement.getName().getLocalPart();
				StringJoiner sj = new StringJoiner(", ", " [", "]");
				Iterator<Attribute> iterator = startElement.getAttributes();
				while (iterator.hasNext()) {
					Attribute attr = iterator.next();
					sj.add(attr.getName() + "=" + attr.getValue());
				}
				String atts = sj.length() > 3 ? sj.toString() : "";
				log2Blue(tagName + atts);

			} else if (nextEvent.isEndElement()) {
				EndElement endElement = nextEvent.asEndElement();
				String tagName = endElement.getName().getLocalPart();
				log2Blue("/" + tagName);

			}

			if (nextEvent.isCharacters()) {
				log2("'" + nextEvent.asCharacters() + "'");
			}

		}

	}

	public void test3() throws Exception {
		/*
		XMLEventWriter
		
		 */
		StringWriter sw = new StringWriter();

		XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
		XMLEventWriter eventWriter = outputFactory.createXMLEventWriter(sw);
		XMLEventFactory eventFactory = XMLEventFactory.newInstance();

		eventWriter.add(eventFactory.createStartDocument());
		eventWriter.add(eventFactory.createStartElement("", "", "root"));
		eventWriter.add(eventFactory.createCharacters("Привет, мир!"));
		eventWriter.add(eventFactory.createEndElement("", "", "root"));
		eventWriter.add(eventFactory.createEndDocument());

		eventWriter.close();

		log2(sw.toString());

	}

	public void test4() throws Exception {
		/*
		XMLEventWriter
		Создание xml.
		 */
    StringWriter sw = new StringWriter();

		 XMLOutputFactory factory = XMLOutputFactory.newInstance();
     XMLEventWriter writer = factory.createXMLEventWriter(sw);
     XMLEventFactory eventFactory = XMLEventFactory.newInstance();

     writer.add(eventFactory.createStartDocument("UTF-8", "1.0"));

     Attribute a1,a2,a3;
     
     
     List<Namespace> emptyNSList = List.of();
     List<Attribute> emptyAttrList = List.of();
     
     a1 = eventFactory.createAttribute("Stage", "Main");
     StartElement startElement = eventFactory.createStartElement(
             new QName("Screen"),
             List.of(a1).iterator(),
             emptyNSList.iterator()
     );
     writer.add(startElement);

     startElement = eventFactory.createStartElement(
             new QName("Title"),
             emptyAttrList.iterator(),
             emptyNSList.iterator()
     );
     writer.add(startElement);
     writer.add(eventFactory.createCharacters("Личный кабинет"));
     writer.add(eventFactory.createEndElement(new QName("Title"),emptyNSList.iterator()));

     startElement = eventFactory.createStartElement(
             new QName("Buttons"),
             emptyAttrList.iterator(),
             emptyNSList.iterator()
     );
     writer.add(startElement);

     a1 = eventFactory.createAttribute("Action", "CREATE_NEW_OFFER");
     a2 = eventFactory.createAttribute("Color", "blue");
     a3 = eventFactory.createAttribute("Condition", "true");
     startElement = eventFactory.createStartElement(
             new QName("Button"),
             List.of(a1,a2,a3).iterator(),
             emptyNSList.iterator()
     );
     writer.add(startElement);

     startElement = eventFactory.createStartElement(
             new QName("T"),
             emptyAttrList.iterator(),
             emptyNSList.iterator()
     );
     writer.add(startElement);
     writer.add(eventFactory.createCharacters("Новый заказ"));
     writer.add(eventFactory.createEndElement(new QName("T"),emptyNSList.iterator()));

     a1 = eventFactory.createAttribute("Color", "red");
     StartElement dStart = eventFactory.createStartElement(
             new QName("D"),
             List.of(a1).iterator(),
             emptyNSList.iterator()
     );
     writer.add(dStart);
     writer.add(eventFactory.createCharacters("Заказать книгу"));
     writer.add(eventFactory.createEndElement(new QName("D"),emptyNSList.iterator()));

     // <price>10</price>
     StartElement priceStart = eventFactory.createStartElement(
             new QName("price"),
             emptyAttrList.iterator(),
             emptyNSList.iterator()
     );
     writer.add(priceStart);
     writer.add(eventFactory.createCharacters("10"));
     writer.add(eventFactory.createEndElement(new QName("price"),emptyNSList.iterator()));
     writer.add(eventFactory.createEndElement(new QName("Button"),emptyNSList.iterator()));
     writer.add(eventFactory.createEndElement(new QName("Buttons"),emptyNSList.iterator()));
     writer.add(eventFactory.createEndElement(new QName("Screen"),emptyNSList.iterator()));
     writer.add(eventFactory.createEndDocument());
     writer.close();

     // Результат
     log2(sw.toString());

	}

	@Override
	protected void doInit() throws Exception {
		options().hlComments = false;
	}

	public static void main(String[] args) {
		DemoBase.run(D_XMLEventReader.class);
	}

}
