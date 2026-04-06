package ru.tet.javax.xml;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.stream.StreamSource;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import ru.tet.beans.Course;

public class JAXBDemo1 {

	public static void main(String[] args) throws Exception {
		doJAXBXml();
	}

	// Create XML from Java object and then vice versa
	public static void doJAXBXml() throws Exception {
		Course course = Course.makeSampleBean();

		JAXBContext context = JAXBContext.newInstance(Course.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		StringWriter stringWriter = new StringWriter();

		marshaller.marshal(course, stringWriter);

		String courseXml = stringWriter.getBuffer().toString();
		stringWriter.close();
		System.out.println(courseXml);

		Unmarshaller unmarshaller = context.createUnmarshaller();
		StringReader stringReader = new StringReader(courseXml);
		StreamSource streamSource = new StreamSource(stringReader);
		Course unmarshalledCourse = unmarshaller.unmarshal(streamSource, Course.class).getValue();
		System.out.println("-----------------\nUnmarshalled course name - " + unmarshalledCourse.getName());
		stringReader.close();
	}

}
