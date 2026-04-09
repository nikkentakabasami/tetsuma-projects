package ru.tet;

import ru.tet.aux.AuxTest;
import ru.tet.aux.swing.DemoBase;

//Шаблон для создания новых демо
public class TemplateDemo extends DemoBase {

	@AuxTest
	class testClass {}
	
	@Override
	public void test1() throws Exception {
		
	}

	@Override
	protected void doInitControlPanel() throws Exception {
		addTest1Button(null);
	}

	public static void main(String[] args) {
		DemoBase.run(TemplateDemo.class,1);
	}

}
