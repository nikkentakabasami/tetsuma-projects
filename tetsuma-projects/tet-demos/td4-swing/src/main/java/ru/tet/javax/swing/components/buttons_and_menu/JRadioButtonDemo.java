package ru.tet.javax.swing.components.buttons_and_menu;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import ru.tet.javax.swing.aux.JFrameForTests;

public class JRadioButtonDemo extends JFrameForTests {

	JLabel jlabOptions;
	JCheckBox jcbOptions;
	JRadioButton jrbSpeed;
	JRadioButton jrbSize;
	JRadioButton jrbDebug;
	
	
	@Override
	protected void doInit() throws Exception {
		initWithControlPanelAbove();
		
		controlPanel.addDebugLabel("Option selected: Speed");
		
		workPanel.setLayout(new GridLayout(6, 1));
		
		jlabOptions = new JLabel("Choose Option:");
		jcbOptions = new JCheckBox("Enable NotesOptions");

		jrbSpeed = new JRadioButton("Maximize Speed", true);
		jrbSize = new JRadioButton("Minimize Size");
		jrbDebug = new JRadioButton("Debug");

		ButtonGroup bg = new ButtonGroup();
		bg.add(jrbSpeed);
		bg.add(jrbSize);
		bg.add(jrbDebug);

		jrbSpeed.setEnabled(false);
		jrbSize.setEnabled(false);
		jrbDebug.setEnabled(false);

		jcbOptions.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				if (jcbOptions.isSelected()) {
					jrbSpeed.setEnabled(true);
					jrbSize.setEnabled(true);
					jrbDebug.setEnabled(true);
				} else {
					jrbSpeed.setEnabled(false);
					jrbSize.setEnabled(false);
					jrbDebug.setEnabled(false);
				}
			}
		});

		
		ActionListener al = e->{
			String opts = "";
			if (jrbSpeed.isSelected())
				opts = "Speed ";
			else if (jrbSize.isSelected())
				opts = "Size ";
			else
				opts = "Debug";
			controlPanel.label1.setText("Option selected: " + opts);
		};
		
		jrbSpeed.addActionListener(al);
		jrbSize.addActionListener(al);
		jrbDebug.addActionListener(al);

		workPanel.add(jcbOptions);
		workPanel.add(jlabOptions);

		workPanel.add(jrbSpeed);
		workPanel.add(jrbSize);
		workPanel.add(jrbDebug);
	}

	public static void main(String[] args) throws Exception {
		JRadioButtonDemo frame = new JRadioButtonDemo();
		frame.doInit();
	}

}
