package ru.tet.javax.swing.layouts;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import ru.tet.javax.swing.aux.JFrameForTests;
import ru.tet.javax.swing.aux.TetGridLayout;

public class TetGridLayoutDemo extends JFrameForTests {

	@Override
	protected void doInit() {
		initWithControlPanelAbove();

		TetGridLayout gridLayout = new TetGridLayout();
		gridLayout.setColWidthes(150,200);
		gridLayout.setRowHeights(50,25);
		workPanel.setLayout(gridLayout);


		JLabel label1 = new JLabel("File Name:");
		JLabel label2 = new JLabel("Files of Type:");

		JTextField tf1 = new JTextField();
		JTextField tf2 = new JTextField();

		JLabel label3 = new JLabel("File Name:");
		JLabel label4 = new JLabel("Files of Type:");

		JTextField tf3 = new JTextField();
		JTextField tf4 = new JTextField();
		
		workPanel.add(label1);
		workPanel.add(tf1);
		workPanel.add(label2);
		workPanel.add(tf2);

		workPanel.add(label3);
		workPanel.add(tf3);
		
		workPanel.add(label4);
		workPanel.add(tf4);

		workPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		controlPanel.addButton("make 4 cols", e->{
			gridLayout.setColWidthes(150,100,150,100);
			workPanel.revalidate();
		});

		controlPanel.addButton("make autosize 1", e->{
			gridLayout.setColWidthes(150,0);
			gridLayout.setRowHeights(25,0,25);
			workPanel.revalidate();
		});

		controlPanel.addButton("make autosize 2", e->{
			gridLayout.setColWidthes(150,100,150,0);
			gridLayout.setRowHeights(25);
			workPanel.revalidate();
		});
		
		
	}


	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> {
			TetGridLayoutDemo demo = new TetGridLayoutDemo();
			demo.init();
		});

	}

}
