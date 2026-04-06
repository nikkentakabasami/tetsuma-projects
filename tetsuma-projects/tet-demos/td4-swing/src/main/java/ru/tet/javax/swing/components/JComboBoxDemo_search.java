package ru.tet.javax.swing.components;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import ru.tet.javax.swing.aux.DemoDataSamples;
import ru.tet.javax.swing.aux.JFrameForTests;

public class JComboBoxDemo_search extends JFrameForTests {

	JComboBox cb;
	
	DefaultComboBoxModel cbModel;
	
	List<String> data;
	List<String> foundList;
	
	@Override
	protected void doInit() {
		initWithControlPanelAbove();

		
		controlPanel.addDebugLabel();

		controlPanel.addButton("remove selection", e -> {
			String item = (String) cb.getSelectedItem();
			if (item == null)
				return;

			cb.removeItem(item);
			data.remove(item);

			controlPanel.label1.setText("Removed " + item);
		});
		controlPanel.addDebugLabel();

		cb = makeComboBox1();
		getWorkPanel().add(cb);

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JComboBoxDemo_search frame = new JComboBoxDemo_search();
			frame.init();
		});
	}

	private boolean match(String item, String searchString) {
		return item.toLowerCase().contains(searchString.toLowerCase());
	}
	
	public JComboBox makeComboBox1() {

		data = Arrays.asList(DemoDataSamples.apples);
		
		
		
		
		cb = new JComboBox(data.toArray());
	 	cbModel = (DefaultComboBoxModel)cb.getModel();
		cb.setEditable(true);

		cb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent le) {

				String cbText = (String) cb.getSelectedItem();
				if (cbText == null) {
					return;
				}
				
				controlPanel.label1.setText(le.getActionCommand()+" : " + cbText);
				
				
				if ("comboBoxEdited".equals(le.getActionCommand())) {

					cbModel.removeAllElements();
					
					if (cbText.length()>0) {
						String searchText = cbText.trim();
						
						if (foundList!=null && foundList.contains(searchText)) {
							controlPanel.label2.setText("selected: "+searchText);
							return;
						}
						
						foundList = data.stream().filter(el->match(el,searchText)).collect(Collectors.toList());
						cbModel.addAll(foundList);
						cb.showPopup();
					} else {
						cbModel.addAll(data);
					}
					
					return;
				}
				
				
				
				
				


			}
		});

		cb.setPreferredSize(new Dimension(400, 25));
		return cb;

	}

}
