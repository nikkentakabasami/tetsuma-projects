package ru.tet.javax.swing.layouts;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import ru.tet.javax.swing.aux.AlignedLabel;
import ru.tet.javax.swing.aux.GBC;
import ru.tet.javax.swing.aux.JFrameForTests;

public class GridBagLayoutDemo3_inputPanels extends JFrameForTests {

	
	
	@Override
	protected void doInit() {
		initWithControlPanelAbove();

	    workPanel.setLayout(new GridBagLayout());
		
		MyTextFieldLabel l1 = new MyTextFieldLabel("File Name:");
		MyTextFieldLabel l2 = new MyTextFieldLabel("Files of Type:");
		MyTextFieldLabel l3 = new MyTextFieldLabel("Host:");
		MyTextFieldLabel l4 = new MyTextFieldLabel("Port:");
		MyTextFieldLabel l5 = new MyTextFieldLabel("User Name:");
		MyTextFieldLabel l6 = new MyTextFieldLabel("Password:");

		MyTextField tf1 = new MyTextField();
		MyTextField tf2 = new MyTextField();
		MyTextField tf3 = new MyTextField();
		MyTextField tf4 = new MyTextField();
		MyTextField tf5 = new MyTextField();
		MyTextField tf6 = new MyTextField();		
		
		workPanel.add(l1, new GBC(0, 0));
		workPanel.add(tf1, new GBC(1, 0));
		workPanel.add(l2, new GBC(0, 1));
		workPanel.add(tf2, new GBC(1, 1));
		workPanel.add(l3, new GBC(0, 2));
		workPanel.add(tf3, new GBC(1, 2));
		workPanel.add(l6, new GBC(0, 3));
		workPanel.add(tf6, new GBC(1, 3));

		workPanel.add(l4, new GBC(2, 0));
		workPanel.add(tf4, new GBC(3, 0,1,1,0.1,0));
		workPanel.add(l5, new GBC(2, 1));
		workPanel.add(tf5, new GBC(3, 1,1,1,0.1,0));
		
		
//		workPanel.add(new JLabel("fill"), new GBC(0, 4,4,1,1,1));
		workPanel.add(Box.createVerticalGlue(), new GBC(0, 4,4,1,1,1));
        
	}
	
	
	
	
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(() -> {
			GridBagLayoutDemo3_inputPanels demo = new GridBagLayoutDemo3_inputPanels();
			demo.doInit();
		});		
		
		
	}
	
	
	
}

class MyTextField extends JTextField {
	
	@Override
	public Dimension getPreferredSize() {
	    Dimension d = super.getPreferredSize();
	    d.width = 100;
	    return d;
	}
	
}

class MyTextFieldLabel extends JLabel {

	  public MyTextFieldLabel(String text) {
	    super(text, RIGHT);
	  }

	  @Override public Dimension getPreferredSize() {
	    Dimension d = super.getPreferredSize();
//	    d.width = 100;
	    return d;
	  }
	}
