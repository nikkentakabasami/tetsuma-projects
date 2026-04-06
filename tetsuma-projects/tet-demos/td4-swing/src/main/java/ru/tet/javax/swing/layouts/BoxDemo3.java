package ru.tet.javax.swing.layouts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;

import ru.tet.javax.swing.aux.JFrameForTests;

public class BoxDemo3 extends JFrameForTests {

	@Override
	protected void doInit() {
		initWithControlPanelAbove();

//		workPanel.setLayout(new GridLayout(5, 1));

		JLabel jlabOne = new JLabel("Button Group One");
		jlabOne.setPreferredSize(new Dimension(300, 200));
		jlabOne.setMinimumSize(new Dimension(300, 100));
		
		
		JLabel jlabTwo = new JLabel("Button Group Two");
		JLabel jlabThree = new JLabel("Check Box Group");

		JButton jbtnOne = new JButton("One");
		jbtnOne.setPreferredSize(new Dimension(300, 200));
//		jbtnOne.setMinimumSize(new Dimension(300, 100));
		
		
		JButton jbtnTwo = new JButton("Two");
		JButton jbtnThree = new JButton("Three");
		JButton jbtnFour = new JButton("Four");

		Dimension btnDim = new Dimension(100, 25);
		jbtnOne.setMinimumSize(btnDim);
		jbtnOne.setMaximumSize(btnDim);
		jbtnTwo.setMinimumSize(btnDim);
		jbtnTwo.setMaximumSize(btnDim);
		jbtnThree.setMinimumSize(btnDim);
		jbtnThree.setMaximumSize(btnDim);
		jbtnFour.setMinimumSize(btnDim);
		jbtnFour.setMaximumSize(btnDim);

		JCheckBox jcbOne = new JCheckBox("Option One");
		JCheckBox jcbTwo = new JCheckBox("Option Two");

		Box box1 = Box.createVerticalBox();
		Box box2 = Box.createVerticalBox();
		Box box3 = Box.createVerticalBox();

		box1.setBorder(BorderFactory.createRaisedBevelBorder());
		box2.setBorder(BorderFactory.createRaisedBevelBorder());
		box3.setBorder(BorderFactory.createRaisedBevelBorder());
//		box2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//		box3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		box1.add(jlabOne);
		box1.add(Box.createVerticalStrut(4));
		box1.add(jbtnOne);
		box1.add(Box.createVerticalStrut(4));
		box1.add(jbtnTwo);

		box2.add(jlabTwo);
		box2.add(Box.createRigidArea(new Dimension(0, 4)));
		box2.add(jbtnThree);
		box2.add(Box.createRigidArea(new Dimension(0, 4)));
		box2.add(jbtnFour);

		box3.add(jlabThree);
		box3.add(jcbOne);
		box3.add(jcbTwo);

		box1.setPreferredSize(new Dimension(300, 200));
		
//	    add(box1); 
//	    add(box2); 
//	    add(box3); 
		
		
		workPanel.add(box1);
		workPanel.add(box2);
		workPanel.add(box3);

	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> {
			BoxDemo3 demo = new BoxDemo3();
			demo.doInit();
		});

	}

}
