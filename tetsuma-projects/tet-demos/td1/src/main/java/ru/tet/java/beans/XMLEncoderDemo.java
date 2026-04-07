package ru.tet.java.beans;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import ru.tet.aux.AuxTest;
import ru.tet.aux.swing.DemoBase;
import ru.tet.beans.User;
import ru.tet.data.BeansSamples;

/**
 * java.beans.XMLEncoder
	Преобразовывает бин в xml для долгосрочного хранения.
	Получается длинно.
 */
public class XMLEncoderDemo extends DemoBase {

	String encodedXml;

	//XMLEncoder - Сериализует бин в xml для долгосрочного хранения.
	//Получается длинно.
	@AuxTest(1)
	public String encodeBean(User user) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(os));
		encoder.writeObject(user);
		encoder.close();
		return new String(os.toByteArray(),StandardCharsets.UTF_8);
	}

	//XMLDecoder - десериализация из xml в бин
	@AuxTest(2)
	public User decodeBean(String xml) {
		InputStream is = new ByteArrayInputStream(encodedXml.getBytes(StandardCharsets.UTF_8));
		XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(is));
		User user = (User) decoder.readObject();
		decoder.close();
		return user;
	}
	
	@Override
	public void test1() throws Exception {
		
		User user = BeansSamples.createTestUserBean();
		log2("user:", user);

		log2Splitter("encodeBean");
		encodedXml = encodeBean(user);
		log2(encodedXml);

	}

	@Override
	public void test2() throws Exception {
		
		if (encodedXml==null) {
			return;
		}
		
		log2Splitter("decodeBean");
		User user = decodeBean(encodedXml);
		log2(user);
	}

	@Override
	protected void doInitControlPanel() throws Exception {

		addTest1Button(null);
		addTest2Button(null);
	}

	public static void main(String[] args) {
		DemoBase.run(XMLEncoderDemo.class);
	}

}
