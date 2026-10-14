package ru.tet.xml;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.stream.StreamSource;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import ru.tet.aux.Cat;
import ru.tet.aux.swing.DemoBase;
import ru.tet.beans.Course;

public class D_jaxb2 extends DemoBase {

	public void test1() throws Exception {
		/*
		Cat - marshal
		 */

		Cat cat = new Cat(555, "Tom", 5, 15);

		JAXBContext context = JAXBContext.newInstance(Cat.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		StringWriter sw = new StringWriter();
		marshaller.marshal(cat, sw);

		log2(sw.toString());


	}

	public void test2() throws Exception {
		/*
		Cat - unmarshal
		 */

		log2Splitter();
		String xmldata = """
				<cat w="5" age="3">
				    <catname>Fonarik</catname>
				</cat>
								""";
		log2(xmldata);

		JAXBContext context = JAXBContext.newInstance(Cat.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();

		StringReader reader = new StringReader(xmldata);
		Cat cat = (Cat) unmarshaller.unmarshal(reader);
		log2(cat);
		

	}

	public void test3() throws Exception {
		/*
		Course - marshal
		 */

		Course course = Course.makeSampleBean();

		JAXBContext context = JAXBContext.newInstance(Course.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		StringWriter sw = new StringWriter();

		marshaller.marshal(course, sw);

		log2(sw.toString());

		

	}

	public void test4() throws Exception {
		/*
		
		 */
//		JAXBContext context = JAXBContext.newInstance(Course.class);
//		Unmarshaller unmarshaller = context.createUnmarshaller();
//		StringReader stringReader = new StringReader(courseXml);
//		StreamSource streamSource = new StreamSource(stringReader);
//		Course unmarshalledCourse = unmarshaller.unmarshal(streamSource, Course.class).getValue();
//		System.out.println("-----------------\nUnmarshalled course name - " + unmarshalledCourse.getName());
//		stringReader.close();		
	}

	@Override
	protected void doInit() throws Exception {
		options().hlComments = false;
	}

	public static void main(String[] args) {
		DemoBase.run(D_jaxb2.class);
	}

}
