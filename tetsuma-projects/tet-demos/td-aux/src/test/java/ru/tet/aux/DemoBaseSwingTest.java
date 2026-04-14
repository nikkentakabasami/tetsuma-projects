package ru.tet.aux;

import ru.tet.aux.swing.DemoBase;
import ru.tet.aux.swing.DemoBaseSwing;

public class DemoBaseSwingTest extends DemoBaseSwing {

	@Override
	protected void doInit() throws Exception {

		log1("first row");
		log1("//Senri no michi mo ippo kara");
		log1("ishou kou narite, ban kotsu karu");
		log2("final", "1", "2", "3", "4");

	}

	@Override
	public void test1() throws Exception {
		log2("test1 result!");
	}

	@Override
	public void test2() throws Exception {
		log2("test2 result!");
	}

	@Override
	protected void doInitControlPanel() throws Exception {
		controlPanel.addButton("button1", e -> {
			log2("button1 result!");
		});

		addButton("test1", e -> {
			test1();
		});

		addTest2Button(null);
	}

	public static void main(String[] args) {
		DemoBase.run(DemoBaseSwingTest.class);

	}

}
