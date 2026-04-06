package ru.tet.javax.swing.layouts;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import ru.tet.javax.swing.aux.JFrameForTests;
import ru.tet.javax.swing.aux.TetBoxOld;

public class TetBoxLayoutDemo extends JFrameForTests {

	@Override
	protected void doInit() {
		initWithControlPanelAbove();

		workPanel.setLayout(new BorderLayout());


		JLabel label1 = new JLabel("File Name:");
		JLabel label2 = new JLabel("Files of Type:");

		JTextField tf1 = new JTextField();
		JTextField tf2 = new JTextField();

		TetBoxOld box1 = new TetBoxOld(true);
//		box1.addComp(label1, 100).addComp(tf1, 200).addStrut(10).addComp(label2, 100).addComp(tf2, 300,false).addGlue();
		box1.addComp(label1, 100).addComp(tf1, 200).addStrut(10).addComp(label2, 100).addComp(tf2, 300,false);
//		box1.addGlue().add(label1, 100).add(tf1, 200).addStrut(10).add(label2, 100).add(tf2, 100);


		JLabel label3 = new JLabel("File Name:");
		JLabel label4 = new JLabel("Files of Type:");

		JTextField tf3 = new JTextField();
		JTextField tf4 = new JTextField();

		TetBoxOld box2 = new TetBoxOld(true,100,100,10,100,100);
		box2.addComp(label3).addComp(tf3).addStrut().addComp(label4).addComp(tf4).addGlue();
//		box2.add(label3, 100).add(tf3, 100).addStrut(10).add(label4, 100).add(tf4, 100).addGlue();
		
		
		
		TetBoxOld vbox = new TetBoxOld(false);
		vbox.addComp(box1, 35).addComp(box2, 35).addGlue();
//		vbox.add(Box.createVerticalStrut(200));
//		vbox.add(Box.createVerticalGlue());

		workPanel.add(vbox, BorderLayout.CENTER);
		workPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

	}

	private static Box makeVerticalBox(JComponent box1, JComponent box2) {
		Box box = Box.createVerticalBox();
		box.add(box1);
		box.add(Box.createVerticalStrut(10));
		box.add(box2);
		return box;
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> {
			TetBoxLayoutDemo demo = new TetBoxLayoutDemo();
			demo.doInit();
		});

	}

}
