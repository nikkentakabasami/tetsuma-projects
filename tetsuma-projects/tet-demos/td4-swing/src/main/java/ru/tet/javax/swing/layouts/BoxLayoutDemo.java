package ru.tet.javax.swing.layouts;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import ru.tet.javax.swing.aux.JFrameForTests;

public class BoxLayoutDemo extends JFrameForTests {

	
	
	@Override
	protected void doInit() {
		initWithControlPanelAbove();
		
        workPanel.setLayout(new BoxLayout(workPanel, BoxLayout.Y_AXIS));

		JLabel l1 = createDemoLabel("label 1", Color.GREEN);
		JLabel l2 = createDemoLabel("label fffffff 2", Color.BLUE);
		JLabel l3 = createDemoLabel("label 3", Color.RED);
		workPanel.add(l1);
		workPanel.add(Box.createRigidArea(new Dimension(0,50)));
		workPanel.add(l2);
		workPanel.add(l3);
        l1.setSize(new Dimension(200, 200));
        
	}
	
	
	
	
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(() -> {
			BoxLayoutDemo demo = new BoxLayoutDemo();
			demo.doInit();
		});		
		
		
	}
	
	
	
}
