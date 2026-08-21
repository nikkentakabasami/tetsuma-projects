package ru.tet;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Random;

import ru.tet.aux.swing.DemoBase;

//Шаблон для создания новых демо
public class D_template extends DemoBase {

	public void test1() throws Exception {
		/*
		 */
		
		logExpr(() -> {
			return 1;
		}, () -> {
			return 1;
		});		

	}

	public void test2() throws Exception {
		/*
		
		 */


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
		DemoBase.run(D_template.class);
	}

}
