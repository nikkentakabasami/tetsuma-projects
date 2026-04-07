package ru.tet;

import ru.tet.aux.swing.DemoBase;

//Шаблон для создания новых демо
public class TemplateDemo extends DemoBase {

	
	@Override
	public void test1() throws Exception {
		
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
		DemoBase.run(TemplateDemo.class);
	}

}
