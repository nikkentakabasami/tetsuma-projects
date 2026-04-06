package ru.tet.javax.swing.layouts;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

import ru.tet.javax.swing.aux.AlignedLabel;
import ru.tet.javax.swing.aux.JFrameForTests;

public class BoxDemo4_inputPanels extends JFrameForTests {

	@Override
	protected void doInit() {
		initWithControlPanelAbove();

		workPanel.setLayout(new BorderLayout());

		/*
		JLabel fileName = new JLabel("File Name:");
		JLabel filesOfType = new JLabel("Files of Type:");
		JLabel host = new JLabel("Host:");
		JLabel port = new JLabel("Port:");
		JLabel user = new JLabel("User Name:");
		JLabel password = new JLabel("Password:");
*/
		//labesl
		AlignedLabel fileName = new AlignedLabel("File Name:");
		AlignedLabel filesOfType = new AlignedLabel("Files of Type:");
		AlignedLabel host = new AlignedLabel("Host:");
		AlignedLabel port = new AlignedLabel("Port:");
		AlignedLabel user = new AlignedLabel("User Name:");
		AlignedLabel password = new AlignedLabel("Password:");
		AlignedLabel.groupLabels(fileName, filesOfType, host, port, user, password);

		JTextField tf1 = new JTextField();
		JTextField tf2 = new JTextField();
		JTextField tf3 = new JTextField();
		JTextField tf4 = new JTextField();
		JTextField tf5 = new JTextField();
		JTextField tf6 = new JTextField();
		
		

		//borders
		Border innerBorder = BorderFactory.createEmptyBorder(5, 2, 5, 5);

		TitledBorder border1 = BorderFactory.createTitledBorder("FileChooser");
		border1.setTitlePosition(TitledBorder.ABOVE_TOP);
		CompoundBorder compBorder1 = BorderFactory.createCompoundBorder(border1, innerBorder); 

		TitledBorder border2 = BorderFactory.createTitledBorder("HTTP Proxy");
		border2.setTitlePosition(TitledBorder.ABOVE_TOP);
		CompoundBorder compBorder2 = BorderFactory.createCompoundBorder(border2, innerBorder); 

		//box1
		Box box1 = Box.createVerticalBox();
		box1.setBorder(compBorder1);
		box1.add(makeHorizontalBox(fileName, tf5));
		box1.add(Box.createVerticalStrut(5));
		box1.add(makeHorizontalBox(filesOfType, tf6));

		//box2
		Box box2 = Box.createVerticalBox();
		box2.setBorder(compBorder2);
		box2.add(makeHorizontalBox(host, tf1));
		box2.add(Box.createVerticalStrut(5));
		box2.add(makeHorizontalBox(port, tf2));
		box2.add(Box.createVerticalStrut(5));
		box2.add(makeHorizontalBox(user, tf3));
		box2.add(Box.createVerticalStrut(5));
		box2.add(makeHorizontalBox(password, tf4));

		workPanel.add(makeVerticalBox(box1, box2), BorderLayout.NORTH);
		workPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));


	}

	private static Box makeHorizontalBox(Component label, Component c) {
		Box box = Box.createHorizontalBox();
		box.add(label);
		box.add(Box.createHorizontalStrut(5));
		box.add(c);
		return box;
	}
	
	  private static Box makeVerticalBox(Box box1, Box box2) {
		    Box box = Box.createVerticalBox();
		    box.add(box1);
		    box.add(Box.createVerticalStrut(10));
		    box.add(box2);
		    return box;
		  }
	

	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> {
			BoxDemo4_inputPanels demo = new BoxDemo4_inputPanels();
			demo.doInit();
		});

	}

}
