package ru.tet.javax.swing.layouts;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import ru.tet.javax.swing.aux.JFrameForTests;
import ru.tet.javax.swing.aux.TetGridPanel;

public class TetGridPanelDemo extends JFrameForTests {

	TetGridPanel tetGridPanel;
	
	@Override
	protected void doInit() {
		
		tetGridPanel = new TetGridPanel();
		tetGridPanel.setColWidthes(150,200).setRowHeights(50,25);
		
		initWithControlPanelAbove(tetGridPanel,null);

		JLabel label1 = new JLabel("File Name:");
		JLabel label2 = new JLabel("Files of Type:");

		JTextField tf1 = new JTextField();
		JTextField tf2 = new JTextField();

		JLabel label3 = new JLabel("File Name:");
		JLabel label4 = new JLabel("Files of Type:");

		JTextField tf3 = new JTextField();
		JTextField tf4 = new JTextField();
		
		tetGridPanel.add(label1,tf1,label2,tf2,label3,tf3,label4,tf4);

		tetGridPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		controlPanel.addButton("make 4 cols", e->{
			tetGridPanel.setColWidthes(150,100,150,100).revalidate();
		});

		controlPanel.addButton("make autosize 1", e->{
			tetGridPanel.setColWidthes(150,0).setRowHeights(25,0,25).revalidate();
		});

		controlPanel.addButton("make autosize 2", e->{
			tetGridPanel.setColWidthes(150,100,150,0).setRowHeights(25).revalidate();
		});
		
		
	}


	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> {
			TetGridPanelDemo demo = new TetGridPanelDemo();
			demo.init();
		});

	}

}
