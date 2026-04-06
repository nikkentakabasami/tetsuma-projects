package ru.tet.javax.swing.aux;

import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.function.Supplier;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextField;

public class TableEditPopupMenu extends JPopupMenu {
	



	JMenuItem deleteMenuItem;
	JMenuItem editMenuItem;

	int currentColumn;
	
	JTextField field;
	
	
	JTable table;
	DynamicTableModel<Object> model;
	
	Supplier<Object> genNewRow;
	
	public TableEditPopupMenu(JTable table, Supplier<Object> genNewRow) {
		super();
		this.table = table;
		this.genNewRow = genNewRow;
		model = (DynamicTableModel)table.getModel();
		
		
		field = new JTextField(24);
		
		add("add").addActionListener(e -> addRow());
		addSeparator();
		deleteMenuItem = add("delete");
		deleteMenuItem.addActionListener(e -> removeRows());
		
		editMenuItem = add("edit");
		editMenuItem.addActionListener(e -> EditRow());
		
		
	}

	
	void addRow() {
		Object newRow = genNewRow.get();
		model.addRow(newRow);
		Rectangle r = table.getCellRect(model.getRowCount() - 1, 0, true);
		table.scrollRectToVisible(r);
	}

	void removeRows() {
		int[] selection = table.getSelectedRows();
		for (int i = selection.length - 1; i >= 0; i--) {
			int viewRowIndex = selection[i];
			int modelRowIndex = table.convertRowIndexToModel(viewRowIndex);
			model.removeRow(modelRowIndex);
		}
	}
	
	void EditRow(){
		
		int modelRowIndex = table.convertRowIndexToModel(table.getSelectedRow());
		
		Object val = model.getValueAt(modelRowIndex, currentColumn);
		
		field.setText(val.toString());
		int ret = JOptionPane.showConfirmDialog(table, field, "edit", JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
		
		if (ret == JOptionPane.OK_OPTION) {
			model.setValueAt(field.getText(), modelRowIndex, currentColumn);
		}
	}
	
	
	@Override
	public void show(Component c, int x, int y) {
		
		Point point = new Point(x, y);
		currentColumn = table.columnAtPoint(point);
		boolean rowSelected = (table.getSelectedRowCount()>0);
		
		
		deleteMenuItem.setEnabled(rowSelected);
		editMenuItem.setEnabled(rowSelected);
		super.show(c, x, y);
	}
}