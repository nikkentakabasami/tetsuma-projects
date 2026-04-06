package ru.tet.javax.swing.layouts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import ru.tet.javax.swing.aux.JFrameForTests;
import ru.tet.javax.swing.aux.TetBoxPanel;

public class TetBoxLayoutDemo2 extends JFrameForTests {

	@Override
	protected void doInit() {
		initWithControlPanelAbove();

		TetBoxPanel vertBoxPanel = new TetBoxPanel(false);

		workPanel.setLayout(new BorderLayout());
		workPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		
		workPanel.add(vertBoxPanel);

		JLabel label1 = new JLabel("File Name:");
		JLabel label2 = new JLabel("Files of Type:");

		JTextField tf1 = new JTextField();
		JTextField tf2 = new JTextField();

		JLabel label3 = new JLabel("File Name:");
		JLabel label4 = new JLabel("Files of Type:");

		JTextField tf3 = new JTextField();
		JTextField tf4 = new JTextField();

		JButton b = new JButton("ok");

		
		JLabel label5 = new JLabel("Files of Type:");
		JTextField tf5 = new JTextField();
		JLabel label6 = new JLabel("Files of Type:");
		JTextField tf6 = new JTextField();
		
		
		TetBoxPanel hb = vertBoxPanel.addHorizontalBox();
		hb.addComp(label1, 100).addComp(tf1, 200).addComp(label2, 100, 20).addComp(tf2, 100).addComp(b,50,0);
		hb.setBorder(BorderFactory.createLineBorder(Color.RED, 10));
		

		hb = vertBoxPanel.addHorizontalBox();
		hb.setDefaultOffset(20);
		hb.addComp(label3, 100).addComp(tf3, 100).addComp(label4, 100, 50).addComp(tf4);


		
		hb = vertBoxPanel.addHorizontalBox(50);
		
		//не растягивать по размеру ячейки. Использовать предпочтительный размер компонента
		hb.setExpandWidth(false);
		hb.setExpandLength(false);
		tf5.setPreferredSize(new Dimension(20,25));
		tf6.setPreferredSize(new Dimension(70,25));

		//выравнивать компоненты по правому краю
		hb.addGlue().addComp(label5, 100).addComp(tf5, 100).addStrut(100).addComp(label6, 100).addComp(tf6);
		
		vertBoxPanel.add(new JScrollPane(new JTextArea(),ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS));
		
		/*
		*/
		

	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> {
			TetBoxLayoutDemo2 demo = new TetBoxLayoutDemo2();
			demo.doInit();
		});

	}

}
