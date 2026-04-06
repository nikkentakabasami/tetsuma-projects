package ru.tet.javax.swing.layouts;

import java.awt.ComponentOrientation;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import ru.tet.javax.swing.aux.JFrameForTests;

public class GridBagLayoutDemo extends JFrameForTests {

	
	
	@Override
	protected void doInit() {
		initWithControlPanelAbove();

	    workPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

	    JButton button;
	    workPanel.setLayout(new GridBagLayout());

	    GridBagConstraints c = new GridBagConstraints();
	    c.fill = GridBagConstraints.BOTH; //кнопки будут растягиваться
	    c.weightx = 0.5; //при растягивании окна в ширину кнопки будут растягиваться равномерно

	    button = new JButton("Button 1");
	    c.gridx = 0;
	    c.gridy = 0;
//	    c.ipadx = 30;
	    workPanel.add(button, c);

	    button = new JButton("Button 2");
	    c.gridx = 1;
	    c.gridy = 0;
	    c.insets = new Insets(0, 10, 0, 10); //Отступ по бокам 
	    c.ipadx = 0;
	    workPanel.add(button, c);

	    button = new JButton("Button 3");
	    c.gridx = 2;
	    c.gridy = 0;
	    c.gridwidth = 1;
	    c.insets = new Insets(0, 0, 0, 0);

	    workPanel.add(button, c);

	    button = new JButton("Long-Named Button 4");
	    c.gridx = 0;
	    c.gridy = 1;
	    c.gridwidth = 3;
	    c.ipady = 40; // сделать эту кнопку высокой 
	    workPanel.add(button, c);

	    button = new JButton("5");
	    c.gridx = 1;
	    c.gridy = 2;
	    c.gridwidth = 2;
	    c.ipady = 0;
	    c.weighty = 1.0; //при растягивании окна в высоту будет расти только эта кнопка
	    c.insets = new Insets(10, 0, 0, 0); //Отступ сверху 
	    workPanel.add(button, c);
		
		
		
		
		
		
        
	}
	
	
	
	
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(() -> {
			GridBagLayoutDemo demo = new GridBagLayoutDemo();
			demo.doInit();
		});		
		
		
	}
	
	
	
}
