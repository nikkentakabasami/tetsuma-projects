package ru.tet.javax.swing.aux;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import ru.tet.beans.SuIdNameModel;

/**
 * renderer для JComboBox (для элементов с типом SuIdNameModel)
 * @author tetsuma
 *
 */
public class IdNameListCellRenderer implements ListCellRenderer<SuIdNameModel> {
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
		
		label.setEnabled(list.isEnabled());
		label.setFont(list.getFont());
		label.setOpaque(true);

		return label;
	}
}