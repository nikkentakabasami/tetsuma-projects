#ru.alphatech.tm.converters.<m.entity>Converter.java
package ru.converters.impl;

import javax.annotation.Resource;
import <m.dtoFullClassName>;
import <m.modelFullClassName>;
import ru.alphatech.utils.converters.spi.SUConversionException;
import ru.alphatech.utils.converters.spi.impl.SUAbstractConverter;

public class <m.entity>Converter extends SUAbstractConverter\<<m.modelClassName>, <m.dtoClassName>> {
	
	public <m.entity>Converter() {
		super(<m.modelClassName>.class, <m.dtoClassName>.class);
	}
	
	@Override
	public void populate(<m.modelClassName> source, <m.dtoClassName> target) throws SUConversionException {

		//found properties: <m.propertyList:{f|<f.type> <f.propertyName>;}>

		<m.propertyList:{f|target.<f.setter>(source.<f.getter>());
		}>

	
	}
	
	@Override
	public void reversePopulate(<m.dtoClassName> source, <m.modelClassName> target) throws SUConversionException {
	
	}
	
	
	
	
}

