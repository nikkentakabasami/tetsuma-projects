package ru.tet.aux;

import ru.tet.aux.swing.DemoBase;


public class DemoBaseTest2 extends DemoBase {

	
	@Override
	protected void doInit() throws Exception {
		//my second test
	}
	
	@Override
	protected void doInitControlPanel() throws Exception {
		addTest1Button(null);
	}
	
	@Override
	public void test1() throws Exception {
		//вызов демки DemoBaseTest1 из этой
		//обычно такое не практикуется!
		DemoBaseTest1 bt1 = new DemoBaseTest1();
		bt1.init(frame);
		log2("using test1!");
	}
	
	public static void main(String[] args) {
		DemoBase.run(DemoBaseTest2.class);
		
		
	}	
	
	
		
	
	
}
