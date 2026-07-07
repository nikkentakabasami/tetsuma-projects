package ru.tet.beans;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DemoPage {

	//числовой идентификатор
	String id;

	String name;

	String desc;

	String sortNumber;

	public String getSortValue() {
		
		if (sortNumber!=null) {
			return StringUtils.leftPad(sortNumber, 6, '0');
		}
		String r = id!=null?id:name;
		return r;
	}
	
}
