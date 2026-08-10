package ru.tet;

import java.util.stream.IntStream;

import ru.tet.aux.AuxTest;
import ru.tet.aux.swing.DemoBase;

//Шаблон для создания новых демо
public class TemplateDemo extends DemoBase {

	@AuxTest
	class testClass {
	}

	public void test1() throws Exception {
		/*
		
		 */
	}

	public void test2() throws Exception {
		r.s1 = IntStream.of(55).toArray();
		r.s3 = IntStream.of(5, 7, 11, 13).toArray();
	}

	public void test3() throws Exception {
		/*
		
		 */
	}

	public void test4() throws Exception {
		/*
		
		 */
	}

	@Override
	protected void doInitControlPanel() throws Exception {
		addTest1Button(null);
		addTest2Button(null);
		addTest3Button(null);
		addTest4Button(null);
	}

	public static void main(String[] args) {
		DemoBase.run(TemplateDemo.class);
	}

}
