package ru.tet.javax.swing.components;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.lang3.StringUtils;

import ru.tet.javax.swing.aux.DemoDataSamples;
import ru.tet.javax.swing.aux.JFrameForTests;

public class JTableDemo1_DefaultTableModel extends JFrameForTests {

	JTable table;
	DefaultTableModel tableModel;

	@Override
	protected void doInit() {
		initWithControlPanelAbove();

		controlPanel.addDebugLabel();

		tableModel = new DefaultTableModel(DemoDataSamples.tableData1, DemoDataSamples.tableHeadings1);

		table = new JTable(tableModel);

		JScrollPane scrollPane = new JScrollPane(table);
//		table.setPreferredScrollableViewportSize(new Dimension(450, 80));

		table.setColumnSelectionAllowed(true);
//		table.setRowSelectionAllowed(false);

		table.getSelectionModel().addListSelectionListener(le -> {
			int[] cols = table.getSelectedColumns();
			int[] rows = table.getSelectedRows();
			String s = String.format("selected cols:%s, rows:%s", 
					StringUtils.join(cols, ','),
					StringUtils.join(rows, ','));
			controlPanel.label1.setText(s);
		});

//		table.getColumnModel().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//			public void valueChanged(ListSelectionEvent le) {
//			}
//		});

		workPanel.setLayout(new BorderLayout());
		workPanel.add(scrollPane, SwingConstants.CENTER);

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JTableDemo1_DefaultTableModel frame = new JTableDemo1_DefaultTableModel();
			frame.init();
		});
	}

}
