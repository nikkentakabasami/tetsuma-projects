package ru.tet.aux;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import ru.tet.aux.swing.DemoBase;

public class DemoBaseTest1 extends DemoBase {

	List<String> demos;
	
	@Override
	protected void doInit() throws Exception {
		
		//мой коммент
		log1("first row");
		log2("Senri no michi mo ippo kara");
		log2("ishou kou narite, ban kotsu karu");
		log2("final","1","2","3","4");
		
		
	}
	
	@Override
	protected void doInitControlPanel() throws Exception {
		
		demos = new ArrayList<>();
		demos.add("demo1");
		demos.add("my demo 2");
		
		String[] demosArray = demos.toArray(new String[0]);
		
		controlPanel.addComboBox(demosArray, e->{
			int ind = controlPanel.comboBox1.getSelectedIndex();
			
			String val = (String)controlPanel.comboBox1.getSelectedItem();
			log2("ind:",ind,"val:",val);
			
		},"test combobox:");
		controlPanel.comboBox1.setPreferredSize(new Dimension(400, 20));
		
		
		controlPanel.newHorizontalBox();
		
		addTest1Button(null);
		addTest2Button(null);
	}
	
	@Override
	public void test1() throws Exception {
		log2("test1 result!");
	}
	
	@Override
	public void test2() throws Exception {
		log2("test2 result!");
	}
	
	public static void main(String[] args) {
		DemoBase.run(DemoBaseTest1.class);
		
		
	}	
	
	
		
	
	
}
