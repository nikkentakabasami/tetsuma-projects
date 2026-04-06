package ru.tet.javax.swing.components.buttons_and_menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import ru.tet.javax.swing.aux.JFrameForTests;

public class JMenuBarDemo extends JFrameForTests {

	@Override
	protected void doInit() {
		initWithControlPanelAbove();

		JMenuBar jmb = new JMenuBar();

		JMenu jmOptions = new JMenu("NotesOptions");
		jmb.add(jmOptions);

		JMenu jmColors = new JMenu("Colors");
		JMenu jmPriority = new JMenu("Priority");
		JMenuItem jmiReset = new JMenuItem("Reset");
		
		
		jmOptions.add(jmColors);
		jmOptions.add(jmPriority);
		jmOptions.addSeparator();
		jmOptions.add(jmiReset);
		
		
		
		JCheckBoxMenuItem jmiRed = new JCheckBoxMenuItem("Red");
		JCheckBoxMenuItem jmiGreen = new JCheckBoxMenuItem("Green");
		JCheckBoxMenuItem jmiBlue = new JCheckBoxMenuItem("Blue");
		jmColors.add(jmiRed);
		jmColors.add(jmiGreen);
		jmColors.add(jmiBlue);


		JRadioButtonMenuItem jmiHigh = new JRadioButtonMenuItem("High", true);
		JRadioButtonMenuItem jmiLow = new JRadioButtonMenuItem("Low");
		jmPriority.add(jmiHigh);
		jmPriority.add(jmiLow);

		// Create button group for the radio button menu items.
		ButtonGroup bg = new ButtonGroup();
		bg.add(jmiHigh);
		bg.add(jmiLow);

		
		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		

		// Add action listeners for the menu items.
		jmiRed.addActionListener(al);
		jmiGreen.addActionListener(al);
		jmiBlue.addActionListener(al);
		jmiHigh.addActionListener(al);
		jmiLow.addActionListener(al);
		jmiReset.addActionListener(al);

		
		setJMenuBar(jmb);

		
	}

	
	
	
	
	
	
	
	public static void main(String[] args) {
		JMenuBarDemo frame = new JMenuBarDemo();
		frame.doInit();
	}

}
