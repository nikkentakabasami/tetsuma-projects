package ru.tet.javax.swing.layouts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingUtilities;

import ru.tet.javax.swing.aux.JFrameForTests;

public class BorderLayoutDemo extends JFrameForTests {

	
	
	@Override
	protected void doInit() {
		initWithControlPanelAbove();
		
		workPanel.setLayout(new BorderLayout()); 
	    
	    workPanel.add(createDemoLabel("BorderLayout.NORTH", Color.GREEN), BorderLayout.NORTH); 
	    workPanel.add(createDemoLabel("BorderLayout.CENTER", Color.RED), BorderLayout.CENTER); 
	    
	    workPanel.add(createDemoLabel("BorderLayout.EAST", Color.GREEN, new Dimension(200,100)), BorderLayout.EAST); 
	    workPanel.add(createDemoLabel("BorderLayout.WEST", Color.GREEN, new Dimension(300,100)), BorderLayout.WEST); 
	    
	    workPanel.add(createDemoLabel("BorderLayout.SOUTH", Color.BLUE), BorderLayout.SOUTH);
		
		
	    workPanel.add(createDemoLabel("LINE_START", Color.BLUE), BorderLayout.LINE_START);
		
		
		
		
		
	}
	
	
	
	
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(() -> {
			BorderLayoutDemo demo = new BorderLayoutDemo();
			demo.doInit();
		});		
		
		
	}
	
	
	
}
