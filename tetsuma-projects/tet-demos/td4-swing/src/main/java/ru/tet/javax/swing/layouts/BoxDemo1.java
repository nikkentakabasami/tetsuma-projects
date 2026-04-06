package ru.tet.javax.swing.layouts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import ru.tet.javax.swing.aux.JFrameForTests;

public class BoxDemo1 extends JFrameForTests {

	
	
	@Override
	protected void doInit() {
		initWithControlPanelAbove();
		
		
	    workPanel.setLayout(new GridLayout(0,1)); 



	    Box box1 = Box.createVerticalBox();
	    Box box2 = Box.createVerticalBox();
	    Box box3 = Box.createHorizontalBox();

	    box1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    box2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    box3.setBorder(BorderFactory.createLineBorder(Color.BLACK));

	    JButton b = new JButton("11");
	    b.setPreferredSize(new Dimension(300, 200));
	    b.setMinimumSize(new Dimension(300, 100));
//	    b.setSize(new Dimension(300, 100));
	    box1.add(b);
	    box1.add(Box.createVerticalStrut(4));
	    box1.add(new JButton("22"));
	    box1.add(Box.createVerticalStrut(4));
	    box1.add(new JButton("33"));

	    box2.add(new JButton("21"));
	    box2.add(Box.createVerticalGlue());
	    box2.add(new JButton("22"));
	    box2.add(Box.createRigidArea(new Dimension(0, 4)));
	    box2.add(new JButton("23"));

	    box3.add(Box.createHorizontalGlue());
	    box3.add(new JButton("31"));
	    box3.add(new JButton("32"));
	    box3.add(new JButton("33"));	    
	    
	    
	    
	    
	    
	    
	    
	    workPanel.add(box1);
	    workPanel.add(box2);
	    workPanel.add(box3);
	    
	    
		
        
	}
	
	
	
	
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(() -> {
			BoxDemo1 demo = new BoxDemo1();
			demo.doInit();
		});		
		
		
	}
	
	
	
}
