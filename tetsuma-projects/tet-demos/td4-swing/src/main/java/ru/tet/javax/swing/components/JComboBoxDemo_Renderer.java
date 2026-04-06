package ru.tet.javax.swing.components;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import ru.tet.beans.SuIdNameModel;
import ru.tet.javax.swing.aux.DemoDataSamples;
import ru.tet.javax.swing.aux.JFrameForTests;

public class JComboBoxDemo_Renderer extends JFrameForTests {

	JComboBox<SuIdNameModel> cb;
	
	@Override
	protected void doInit() {
		initWithControlPanelAbove();

		controlPanel.addDebugLabel();

		cb = makeComboBox1();
		getWorkPanel().add(cb);
		cb.setSelectedIndex(0);

		controlPanel.addButton("add new item", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Random random = new Random();
				int newId = Math.abs(random.nextInt())%1000;
				
				SuIdNameModel newRow = new SuIdNameModel(newId, "new item "+newId);
				cb.addItem(newRow);
				cb.setSelectedItem(newRow);
			}
		});
		
		
		
		controlPanel.addButton("popup", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cb.setPopupVisible(true);
				
			}
		});		
		
		
		
		
		
	}


	
	
	
	
	
	private JComboBox<SuIdNameModel> makeComboBox1() {
		

		List<SuIdNameModel> data = DemoDataSamples.makeItemsList(10);
		JComboBox<SuIdNameModel> cb = new JComboBox<>(data.toArray(new SuIdNameModel[0]));
//		cb.setEditable(true);

		cb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent le) {

				SuIdNameModel item = (SuIdNameModel) cb.getSelectedItem();

				if (item == null)
					return;

				controlPanel.label1.setText("Current selection " + item);

			}
		});

		cb.setRenderer(new MyComboBoxRenderer());
		cb.setPreferredSize(new Dimension(400, 25));
		return cb;
		
	}
	
	class MyComboBoxRenderer implements ListCellRenderer<SuIdNameModel> {
		@Override
		public Component getListCellRendererComponent(JList<? extends SuIdNameModel> list, SuIdNameModel value,
				int index, boolean isSelected, boolean cellHasFocus) {
			JLabel label = new JLabel(value.getName());
			label.setOpaque(true);

			if (isSelected) {
				label.setBackground(list.getSelectionBackground());
				label.setForeground(list.getSelectionForeground());
			} else {
				label.setBackground(list.getBackground());
				label.setForeground(list.getForeground());
			}

			return label;
		}
	}

	public static void main(String[] args) {
		JComboBoxDemo_Renderer frame = new JComboBoxDemo_Renderer();
		frame.doInit();
	}
	
	
}
