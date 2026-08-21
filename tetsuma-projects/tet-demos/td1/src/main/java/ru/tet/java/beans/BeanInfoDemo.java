package ru.tet.java.beans;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import ru.tet.aux.swing.DemoBase;
import ru.tet.beans.Course;

public class BeanInfoDemo extends DemoBase {

	//java.beans.BeanInfo
	//Содержит полное описание класса бина (атрибуты, методы)
	//Может вычисляться автоматом, можно прописывать явно
	//Используется для того, чтобы редактор бинов в NetBeans выглядел прилично (без лишнего, с описаниями атрибутов).
	//Можно даже задать иконку для атрибутов.
	@Override
	public void test1() throws Exception {
		//Introspector - вычисляет BeanInfo автоматически
		BeanInfo beanInfo = Introspector.getBeanInfo(Course.class);
		for (PropertyDescriptor desc : beanInfo.getPropertyDescriptors()) {
			log2(desc.getName(),"-",desc.getPropertyType().getName());
		}
	}


	public static void main(String[] args) {
		DemoBase.run(BeanInfoDemo.class);
	}

}
