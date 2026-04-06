package ru.tet.javax.swing.layouts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import ru.tet.javax.swing.aux.JFrameForTests;

public class BoxDemo2 extends JFrameForTests {

	
	
	@Override
	protected void doInit() {
		initWithControlPanelAbove();
		
		
	    workPanel.setLayout(new GridLayout(5,1)); 



	    Box box1 = Box.createVerticalBox();
	    Box box2 = Box.createVerticalBox();
	    Box box3 = Box.createHorizontalBox();

	    box1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    box2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    box3.setBorder(BorderFactory.createLineBorder(Color.BLACK));

	    JLabel l1 = createDemoLabel();
	    JLabel l2 = createDemoLabel();
	    JLabel l3 = createDemoLabel();
	    JLabel l4 = createDemoLabel();
	    JLabel l5 = createDemoLabel();
	    JLabel l6 = createDemoLabel();
	    
	    l1.setMinimumSize(new Dimension(200, 200));
	    box1.add(l1);
	    box1.add(Box.createVerticalStrut(4));
	    box1.add(l2);
	    box1.add(Box.createVerticalStrut(4));
	    box1.add(l3);

	    box2.add(l4);
	    box2.add(Box.createVerticalGlue());
	    box2.add(l5);
	    box2.add(Box.createRigidArea(new Dimension(0, 4)));
	    box2.add(l6);

	    box3.add(Box.createHorizontalGlue());
	    box3.add(createDemoLabel());
	    box3.add(createDemoLabel());
	    box3.add(createDemoLabel());
	    
	    
	    
	    
	    
	    
	    
	    workPanel.add(box1);
	    workPanel.add(box2);
	    workPanel.add(box3);
	    
	    
		
        
	}
	
	
	
	
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(() -> {
			BoxDemo2 demo = new BoxDemo2();
			demo.doInit();
		});		
		
		
	}
	
	
	
}
