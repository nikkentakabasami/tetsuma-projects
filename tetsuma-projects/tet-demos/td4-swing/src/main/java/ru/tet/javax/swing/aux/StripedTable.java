package ru.tet.javax.swing.aux;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

/**
 * Таблица с полосатыми строками
 * 
 * @author tetsuma
 *
 */
public class StripedTable extends JTable {

	private final Color evenColor = new Color(0xFA_FA_FA);

	
	
	
	public StripedTable(TableModel dm) {
		super(dm);
	}




	@Override
	public Component prepareRenderer(TableCellRenderer tcr, int row, int column) {
		Component c = super.prepareRenderer(tcr, row, column);
		if (isRowSelected(row)) {
			c.setForeground(getSelectionForeground());
			c.setBackground(getSelectionBackground());
		} else {
			c.setForeground(getForeground());
			c.setBackground(row % 2 == 0 ? evenColor : getBackground());
		}
		return c;
	}
	
	
}
