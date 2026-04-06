package ru.tet.javax.swing.aux;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import javax.swing.table.AbstractTableModel;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Модель для показа и редактирования объекта Properties.
 * 
 * @author tetsuma
 *
 * @param <E>
 */
public class PropertiesTableModel extends AbstractTableModel {

	@Data
	@AllArgsConstructor
	public static class PropertieRow {
		String key;
		String value;
	}

	String[] columns = { "Настройка", "Значение" };
	List<PropertieRow> rows;
//	Properties properties;

	public PropertiesTableModel() {
	}

	public PropertiesTableModel(Properties properties) {
		loadData(properties);
	}

	public void loadData(Properties properties) {
//		this.properties = properties;
		this.rows = new ArrayList<>(properties.size());
		for (Entry<Object, Object> p : properties.entrySet()) {
			rows.add(new PropertieRow(p.getKey().toString(), p.getValue().toString()));
		}
		fireTableDataChanged();
	}

	public void loadData(Object[][] data) {
		this.rows = new ArrayList<>(data.length);
		for (int i = 0; i < data.length; i++) {
			Object[] row = data[i];
			rows.add(new PropertieRow(row[0].toString(), row[1].toString()));
		}
		fireTableDataChanged();
	}

	public void loadData(File f) throws FileNotFoundException, IOException {
		Properties props = new Properties();
		props.load(new FileInputStream(f));
		loadData(props);
	}

	public Properties getDataAsProperties() {
		Properties result = new Properties();
		rows.stream().forEach(row -> result.setProperty(row.getKey(), row.getValue()));
		return result;
	}

	public void saveData(File f) throws FileNotFoundException, IOException {
		Properties prop = getDataAsProperties();
		prop.store(new FileOutputStream(f), "Notes properties");
	}

	public void addRow(PropertieRow row) {
		rows.add(row);
		fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		return rows.size();
	}

	public PropertieRow getRow(int rowIndex) {
		return rows.get(rowIndex);
	}

	public void removeRow(int rowIndex) {
		rows.remove(rowIndex);
		fireTableDataChanged();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		PropertieRow row = getRow(rowIndex);
		return columnIndex == 0 ? row.getKey() : row.getValue();
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		PropertieRow row = getRow(rowIndex);
		if (columnIndex == 0) {
			row.setKey(aValue.toString());
		} else {
			row.setValue(aValue.toString());
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return col == 1;
	}

	@Override
	public Class<?> getColumnClass(int column) {
		return String.class;
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public String getColumnName(int column) {
		return columns[column];
	}

	public void setRows(List<PropertieRow> rows) {
		this.rows = rows;
		fireTableDataChanged();
	}

	public List<PropertieRow> getRows() {
		return rows;
	}

}