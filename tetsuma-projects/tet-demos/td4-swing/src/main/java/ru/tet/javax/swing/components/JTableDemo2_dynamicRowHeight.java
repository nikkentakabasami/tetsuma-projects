package ru.tet.javax.swing.components;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import ru.tet.javax.swing.aux.DemoDataSamples;
import ru.tet.javax.swing.aux.DynamicRowHeightTable;
import ru.tet.javax.swing.aux.JFrameForTests;

public class JTableDemo2_dynamicRowHeight extends JFrameForTests {

	DynamicRowHeightTable table;

	DefaultTableModel model;

	@Override
	protected void doInit() {
		
		
		
		initWithControlPanelAbove(null,new Dimension(320, 400));

		controlPanel.addDebugLabel();

		model = new DefaultTableModel(DemoDataSamples.tableData2, DemoDataSamples.tableHeadings2) {
	        @Override public Class<?> getColumnClass(int column) {
	          return getValueAt(0, column).getClass();
	        }
		};
		
		table = new DynamicRowHeightTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    
	    
	    controlPanel.addButton("addRow", e -> model.addRow(new Object[] {"", 0, false}));
		
		
		workPanel.setLayout(new BorderLayout());
		workPanel.add(scrollPane,SwingConstants.CENTER);

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JTableDemo2_dynamicRowHeight frame = new JTableDemo2_dynamicRowHeight();
			frame.init();
		});
	}

}
