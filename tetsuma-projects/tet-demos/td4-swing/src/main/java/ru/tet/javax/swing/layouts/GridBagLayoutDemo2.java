package ru.tet.javax.swing.layouts;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import ru.tet.javax.swing.aux.JFrameForTests;

public class GridBagLayoutDemo2 extends JFrameForTests {

	
	
	@Override
	protected void doInit() {
		initWithControlPanelAbove();

	    GridBagLayout gbag = new GridBagLayout(); 
	    workPanel.setLayout(gbag);     


	    JLabel jlabOne = new JLabel("Button Group One"); 
	    JLabel jlabTwo = new JLabel("Button Group Two"); 
	    JLabel jlabThree = new JLabel("Check Box Group"); 
	 
	    JButton jbtnOne = new JButton("One"); 
	    JButton jbtnTwo = new JButton("Two"); 
	    JButton jbtnThree = new JButton("Three"); 
	    JButton jbtnFour = new JButton("Four"); 
	 
	    Dimension btnDim = new Dimension(100, 25); 
	    jbtnOne.setPreferredSize(btnDim); 
	    jbtnTwo.setPreferredSize(btnDim); 
	    jbtnThree.setPreferredSize(btnDim); 
	    jbtnFour.setPreferredSize(btnDim); 
	 
	    JCheckBox jcbOne = new JCheckBox("Option One"); 
	    JCheckBox jcbTwo = new JCheckBox("Option Two"); 
	 
	    GridBagConstraints gbc = new GridBagConstraints(); 
	    gbc.weightx = 1.0; 
	 
	    //строка 0
	    gbc.gridx = 0; 
	    gbc.gridy = 0; 
	    gbag.setConstraints(jlabOne, gbc); 
	 
	    gbc.gridx = 1; 
	    gbc.gridy = 0; 
	    gbag.setConstraints(jlabTwo, gbc); 

	    
	    //строка 1
	    gbc.insets = new Insets(4, 4, 4, 4); 
	    gbc.gridx = 0; 
	    gbc.gridy = 1; 
	    gbag.setConstraints(jbtnOne, gbc); 
	 
	    gbc.gridx = 1; 
	    gbc.gridy = 1; 
	    gbag.setConstraints(jbtnTwo, gbc); 
	 
	    //строка 2
	    gbc.gridx = 0; 
	    gbc.gridy = 2; 
	    gbag.setConstraints(jbtnThree, gbc); 
	 
	    gbc.gridx = 1; 
	    gbc.gridy = 2; 
	    gbag.setConstraints(jbtnFour, gbc); 


	    //занимать всё пространство (все 2 ячейки)
	    gbc.gridwidth = GridBagConstraints.REMAINDER; 
	 
	    gbc.insets = new Insets(10, 0, 0, 0); 
	    gbc.gridx = 0; 
	    gbc.gridy = 3; 
	    gbag.setConstraints(jlabThree, gbc); 
	 
	    gbc.insets = new Insets(0, 0, 0, 0); 
	    gbc.gridx = 0; 
	    gbc.gridy = 4; 
	    gbag.setConstraints(jcbOne, gbc); 
	 
	    gbc.gridx = 0; 
	    gbc.gridy = 5; 
	    gbag.setConstraints(jcbTwo, gbc); 
	 
	    workPanel.add(jlabOne); 
	    workPanel.add(jlabTwo); 
	    workPanel.add(jbtnOne); 
	    workPanel.add(jbtnTwo); 
	    workPanel.add(jbtnThree); 
	    workPanel.add(jbtnFour); 
	    workPanel.add(jlabThree); 
	    workPanel.add(jcbOne); 
	    workPanel.add(jcbTwo); 	  
		
		
		
		
		
        
	}
	
	
	
	
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(() -> {
			GridBagLayoutDemo2 demo = new GridBagLayoutDemo2();
			demo.doInit();
		});		
		
		
	}
	
	
	
}
