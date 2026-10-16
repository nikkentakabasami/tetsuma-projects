package ru.tet.jaxbmodels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lombok.Data;

@Data
public class ScreenDom {
	HashMap<String, String> attributes = new HashMap<>();
	String title;
	String description;
	List<ButtonDom> buttonList = new ArrayList<>();
	List<AttributeDom> attributeList = new ArrayList<>();

	@Data
	public static class ButtonDom {
		HashMap<String, String> attributes = new HashMap<>();
		String t;
		List<DDom> dList = new ArrayList<>();
	}

	@Data
	public static class AttributeDom {
		HashMap<String, String> attributes = new HashMap<>();
		String v;
	}

	@Data
	public static class DDom {
		HashMap<String, String> attributes = new HashMap<>();
		String d;

		public DDom withD(String value) {
			this.d = value;
			return this;
		}
	}

}
