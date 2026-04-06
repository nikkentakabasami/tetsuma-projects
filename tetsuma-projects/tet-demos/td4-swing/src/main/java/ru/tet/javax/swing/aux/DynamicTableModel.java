package ru.tet.javax.swing.aux;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * Модель в которую данные можно задавать как список бинов.
 * Описание столбцов задаётся списком их дескрипторов.
 * @author tetsuma
 *
 * @param <E>
 */
public class DynamicTableModel<E> extends AbstractTableModel {

	TableColumnDesc[] columns;

	List<E> rows;

	public DynamicTableModel(TableColumnDesc[] columns) {
		this.columns = columns;
	}

	public void setRows(List<E> rows) {
		this.rows = rows;
		fireTableStructureChanged();
	}
	
	public void addRow(E row) {
		/*
		Object[] rowArray = new Object[columns.length];
		for (int i = 0; i < columns.length; i++) {
			TableColumnDesc colDesc = columns[i];
			rowArray[i] = getRowProperty(row, colDesc.columnName);
		}
		*/
		rows.add(row);
//		fireTableStructureChanged();
    	fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		return rows.size();
	}

	public E getRow(int rowIndex) {
		return rows.get(rowIndex);

	}
	
    public void removeRow(int rowIndex) {
    	rows.remove(rowIndex);
    	fireTableDataChanged();
    }
	

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		E row = getRow(rowIndex);
		TableColumnDesc colDesc = columns[columnIndex];
		return getRowProperty(row, colDesc.columnName);
	}

    public void setValueAt(Object value, int rowIndex, int columnIndex) {
		E row = getRow(rowIndex);
		TableColumnDesc colDesc = columns[columnIndex];
		setRowProperty(row, colDesc.columnName, value);
    }
	
	
	@Override
	public boolean isCellEditable(int row, int col) {
		return columns[col].isEditable();
	}

	@Override
	public Class<?> getColumnClass(int column) {
		return columns[column].columnClass;
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public String getColumnName(int column) {
		return columns[column].title;
	}

	public static Object getRowProperty(Object row, String columnName) {
		try {
			return PropertyUtils.getSimpleProperty(row, columnName);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public static void setRowProperty(Object row, String columnName, Object value) {
		try {
			PropertyUtils.setSimpleProperty(row, columnName, value);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}