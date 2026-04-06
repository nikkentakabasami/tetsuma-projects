package ru.tet.javax.swing;

import ru.tet.aux.DemoBaseSwing;

public class SwingDemoTemplate extends DemoBaseSwing {

	
	@Override
	protected void doInit() throws Exception {
		
		addTest1Button(null);
		addTest2Button(null);
		
	}
	
	@Override
	public void test1() throws Exception {
	}
	
	@Override
	public void test2() throws Exception {
	}
	
	public static void main(String[] args) {
		DemoBaseSwing.run(SwingDemoTemplate.class);
	}	
	
	
		
	
	
}
