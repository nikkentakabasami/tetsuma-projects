package ru.tet.beans.scr;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlValue;
import lombok.Data;

@Data
@XmlRootElement(name = "Screen")
@XmlAccessorType(XmlAccessType.FIELD)
public class Screen {
	@XmlAttribute(name = "Stage")
	String stage;
	@XmlElement(name = "Title")
	String title;
	@XmlElement(name = "Description")
	String description;

	@XmlElementWrapper(name = "Buttons")
	@XmlElement(name = "Button")
	List<Button> buttonList;

	@XmlElementWrapper(name = "Attributes")
	@XmlElement(name = "Attribute")
	List<Attribute> attributeList;

	@Data
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Attribute {
		@XmlAttribute(name = "A")
		String a;
		@XmlElement(name = "V")
		String v;
	}

	@Data
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Button {
		@XmlAttribute(name = "Action")
		String action;
		@XmlAttribute(name = "Color")
		String color;
		@XmlAttribute(name = "Condition")
		String condition;
		@XmlElement(name = "T")
		String t;
		@XmlElement(name = "D")
		List<D> dList = new ArrayList<>();
	}

	@Data
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class D {
		@XmlAttribute(name = "Color")
		String color;
		@XmlValue
		String d;
	}

}
