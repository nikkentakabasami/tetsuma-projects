package ru.tet.javax.swing.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import ru.tet.javax.swing.aux.DemoDataSamples;
import ru.tet.javax.swing.aux.JFrameForTests;

public class JScrollPaneDemo2 extends JFrameForTests {

	JLabel label;
	JScrollPane sp;
	
	@Override
	protected void doInit() {

		String html = DemoDataSamples.loadClassPathResourceAsText("labelHtmlPage.html");
		label = new JLabel(html);

		sp = new JScrollPane(label);
		sp.setViewportBorder( BorderFactory.createLineBorder(Color.BLACK)); 

	    //Задаём заголовки 
	    JLabel colView = new JLabel("Configuration Center",  SwingConstants.CENTER); 
	    JLabel rowView = new JLabel("<html>C<br>h<br>o<br>o<br>s<br>e", SwingConstants.CENTER); 
	    rowView.setPreferredSize(new Dimension(20, 200)); 
	 
	    sp.setColumnHeaderView(colView); 
	    sp.setRowHeaderView(rowView); 

	    //Задаём уголок
	    JButton myButton=new JButton("..");
	    sp.setCorner(JScrollPane.UPPER_LEFT_CORNER, myButton);		
		
		initWithControlPanelAbove(sp);


	}

	public static void main(String[] args) throws IOException {
		JScrollPaneDemo2 frame = new JScrollPaneDemo2();
		frame.doInit();
	}



}
