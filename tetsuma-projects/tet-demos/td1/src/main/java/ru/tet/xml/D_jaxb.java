package ru.tet.xml;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.stream.Collectors;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import ru.tet.aux.Cat;
import ru.tet.aux.swing.DemoBase;
import ru.tet.beans.scr.Screen;

public class D_jaxb extends DemoBase {

	public void test1() throws Exception {
		/*
		Screen unmarshal
		 */
		InputStream is1 = getClass().getClassLoader().getResourceAsStream("xmls/screen1.xml");

		BufferedReader br = new BufferedReader(new InputStreamReader(is1));
		String body = br.lines().collect(Collectors.joining());
		StringReader reader = new StringReader(body);
		JAXBContext context = JAXBContext.newInstance(Screen.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Screen screen = (Screen) unmarshaller.unmarshal(reader);

		log2(screen);

	}

	public void test2() throws Exception {
		/*
		Screen marshal
		 */

		Screen screen = new Screen();
		screen.setTitle("Изменили заглавие");

		//		screen.getButtonList().get(0).getDList().get(0).setColor("black");
		//		screen.getButtonList().get(0).getDList().get(0).setD("Цвет кнопки черный");

		JAXBContext context = JAXBContext.newInstance(Screen.class);
		StringWriter sw = new StringWriter();
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(screen, sw);

		log2(sw.toString());

	}

	public void test3() throws Exception {

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
		DemoBase.run(D_jaxb.class);
	}

}
