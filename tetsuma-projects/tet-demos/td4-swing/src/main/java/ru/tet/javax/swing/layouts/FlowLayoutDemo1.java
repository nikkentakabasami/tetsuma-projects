package ru.tet.javax.swing.layouts;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import ru.tet.javax.swing.aux.JFrameForTests;

public class FlowLayoutDemo1 extends JFrameForTests {

	FlowLayout layout;


	@Override
	protected void doInit() {

		initWithControlPanelAbove();
		
		
		layout = new FlowLayout();
		layout.setAlignment(FlowLayout.LEFT);
		layout.setHgap(20);
		workPanel.setLayout(layout);

		JLabel l1 = createDemoLabel("label 1", Color.GREEN);
		JLabel l2 = createDemoLabel("label 2", Color.BLUE);
		JLabel l3 = createDemoLabel("label 3", Color.RED);

		workPanel.add(l1);
		workPanel.add(l2);
		workPanel.add(l3);
		workPanel.add(createDemoLabel("label 4", null));

		
		
		
		controlPanel.addComboBox(new String[] { "LEFT", "CENTER", "RIGHT", "LEADING", "TRAILING" }, e -> {
			int newAlign = controlPanel.comboBox1.getSelectedIndex();
			layout.setAlignment(newAlign);
			workPanel.revalidate();
		}, "change align");

		controlPanel.addComboBox(new String[] { "-60","-20", "0", "20", "100" }, e -> {
			String newGap = (String)controlPanel.comboBox2.getSelectedItem();
			
			layout.setHgap(Integer.valueOf(newGap));
			workPanel.revalidate();
		}, "change hgap");
		controlPanel.comboBox2.setSelectedIndex(3);
		
		
		
		controlPanel.addButton("setBounds for l1", e -> {
			l1.setBounds(40, 40, 200, 200);
		});
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			FlowLayoutDemo1 demo = new FlowLayoutDemo1();
			demo.doInit();
		});
	}

}
