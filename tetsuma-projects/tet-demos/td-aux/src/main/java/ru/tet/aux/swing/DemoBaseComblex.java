package ru.tet.aux.swing;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.reflections.Reflections;

import ru.tet.aux.AbstractDemoBase;

/**
 * Демка, объединяющая набор демок в единое целое
 * 
 */
public class DemoBaseComblex extends AbstractDemoBase {

	String packageToScan;
	
	List<Class<? extends DemoBase>> demos;
	int currentDemoIndex;
	
	public DemoBaseComblex(String packageToScan) {
		this.packageToScan = packageToScan;
	}
	
	
	
	@Override
	public final void init(AbstractDemoFrame generalFrame) {

		
		demos = findDemos();
		List<String> demoNames = demos.stream().map(cl->cl.getName()).collect(Collectors.toList());
		String[] demoNamesArray = demoNames.toArray(new String[0]);

		
		DemoFrame fr = new DemoFrame();
		
		fr.initComplex(demoNamesArray, e->{
			currentDemoIndex = fr.demosComboBox.getSelectedIndex();
			
			String val = (String)fr.demosComboBox.getSelectedItem();
			log2("ind:",currentDemoIndex,"val:",val);
			showCurrentDemo();
			
			
		});
		frame = fr;
		frame.initLog1Styles();
		
		
		
		this.controlPanel = frame.controlPanel;
		this.workPanel = frame.workPanel;
		this.textArea1 = frame.textArea1;
		this.textArea2 = frame.textArea2;

		
		/*
		controlPanel.addLabel("demos:");
		controlPanel.addComboBox(demoNamesArray, e->{
			currentDemoIndex = controlPanel.comboBox1.getSelectedIndex();
			
			String val = (String)controlPanel.comboBox1.getSelectedItem();
			log2("ind:",currentDemoIndex,"val:",val);
			showCurrentDemo();
			
			
		});
		controlPanel.comboBox1.setPreferredSize(new Dimension(400, 20));
		controlPanel.newHorizontalBox();
		
		*/
		showCurrentDemo();

	}

	private void showCurrentDemo() {
		frame.clearContent();
		
		Class<? extends DemoBase> cl = demos.get(currentDemoIndex);
		
		try {
			cl.getDeclaredConstructor().newInstance().init(frame);
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		
	}
	
	
	List<Class<? extends DemoBase>> findDemos() {
		Reflections reflections = new Reflections(packageToScan);
		return new ArrayList<>(reflections.getSubTypesOf(DemoBase.class));
		
		
	}
	
	
	
}
