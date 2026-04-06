package ru.tet.javax.swing.components;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import ru.tet.javax.swing.aux.DemoDataSamples;
import ru.tet.javax.swing.aux.JFrameForTests;

public class JComboBoxDemo_editable extends JFrameForTests {

	public static final String CB_MY_ACTION_COMMAND = "testComboBox";
	
	JComboBox cb;
	List<String> data;

	@Override
	protected void doInit() {
		initWithControlPanelAbove();

		controlPanel.addDebugLabel("-");
		controlPanel.newHorizontalBox();
		controlPanel.addDebugLabel("-");
		controlPanel.newHorizontalBox();

		controlPanel.addButton("remove selection", e -> {
			String item = (String) cb.getSelectedItem();
			if (item == null)
				return;

			cb.removeItem(item);
			data.remove(item);

			controlPanel.label1.setText("Removed " + item);
		});

		makeComboBox1();
		getWorkPanel().add(cb);
		cb.setSelectedIndex(0);

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JComboBoxDemo_editable frame = new JComboBoxDemo_editable();
			frame.init();
		});
	}

	public JComboBox makeComboBox1() {

		data = DemoDataSamples.makeStringList(10);
		cb = new JComboBox(data.toArray());
		cb.setEditable(true);
		cb.addItemListener(e->{
			if (e.getStateChange() == ItemEvent.SELECTED) {
				String item = (String)e.getItem();
				controlPanel.label1.setText("itemStateChanged: " + item);
			}
		});

		
		
		/*
		cb.addActionListener(e -> {
		    String text = (String) cb.getEditor().getItem();
		    controlPanel.label2.setText(String.format("text:%s, actionCommand:%s ", text, e.getActionCommand()));
		});		
		cb.setActionCommand(CB_MY_ACTION_COMMAND);
			*/
		cb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent le) {

				String item = (String) cb.getSelectedItem();
				if (item == null) {
					return;
				}

				// значение отредактировано вручную
				if ("comboBoxEdited".equals(le.getActionCommand())) {
					JOptionPane.showMessageDialog(JComboBoxDemo_editable.this, "edited: " + item);
				}

				// добавляем новое значение в список
				if (!data.contains(item)) {
					data.add(item);
					cb.addItem(item);
				}

			    controlPanel.label2.setText(String.format("text:%s, actionCommand:%s ", item, le.getActionCommand()));
//				controlPanel.label1.setText("Current selection " + item);

			}
			
		});

		
		
		cb.setPreferredSize(new Dimension(400, 25));
		return cb;

	}

}
