package ru.tet.javax.swing.components;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import ru.tet.javax.swing.aux.JFrameForTests;

public class JLabelDemo extends JFrameForTests {

	@Override
	protected void doInit() {
		initWithControlPanelAbove();

		workPanel.setLayout(new GridLayout(0, 1));

		ImageIcon myIcon = new ImageIcon("images/about.gif");

		Border border = BorderFactory.createLineBorder(Color.BLACK);
		
		JLabel label = new JLabel(myIcon);
		label.setBorder(border);
		workPanel.add(label);

		label = new JLabel("Default Text Position, horizontalAlignment:CENTER", myIcon, SwingConstants.CENTER);
		label.setBorder(border);
		workPanel.add(label);

		label = new JLabel("Default Text Position, horizontalAlignment:LEFT", myIcon, SwingConstants.LEFT);
		label.setBorder(border);
		workPanel.add(label);
		
		
		label = new JLabel("horizontalTextPosition=LEFT, horizontalAlignment:CENTER", myIcon, SwingConstants.CENTER);
		label.setHorizontalTextPosition(SwingConstants.LEFT);
		workPanel.add(label);

		label = new JLabel("VerticalTextPosition=TOP, horizontalTextPosition=CENTER, horizontalAlignment:CENTER", myIcon, SwingConstants.CENTER);
		label.setVerticalTextPosition(SwingConstants.TOP);
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		workPanel.add(label);


//	    workPanel.setLayout(new BoxLayout(workPanel, BoxLayout.Y_AXIS));
	}

	public static void main(String[] args) {
		JLabelDemo frame = new JLabelDemo();
		frame.doInit();
	}

}
