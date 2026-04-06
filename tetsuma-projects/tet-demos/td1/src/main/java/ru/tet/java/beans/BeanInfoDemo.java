package ru.tet.java.beans;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import ru.tet.aux.DemoBase;
import ru.tet.beans.Course;

public class BeanInfoDemo extends DemoBase {

	@Override
	public void test1() throws Exception {
		//BeanInfo - полное описание класса бина (атрибуты, методы)
		//Может вычисляться автоматом, можно прописывать явно
		//Используется для того, чтобы редактор бинов в NetBeans выглядел прилично (без лишнего, с описаниями атрибутов).
		//Можно даже задать иконку для атрибутов.
		
		//Вычисление BeanInfo автоматически
		BeanInfo beanInfo = Introspector.getBeanInfo(Course.class);
		for (PropertyDescriptor desc : beanInfo.getPropertyDescriptors()) {
			log2(desc.getPropertyType().getName(),desc.getName());
		}
	}

	@Override
	public void test2() throws Exception {

	}

	@Override
	protected void doInit() throws Exception {

		addTest1Button(null);
		addTest2Button(null);

	}

	public static void main(String[] args) {
		DemoBase.run(BeanInfoDemo.class);
	}

}
