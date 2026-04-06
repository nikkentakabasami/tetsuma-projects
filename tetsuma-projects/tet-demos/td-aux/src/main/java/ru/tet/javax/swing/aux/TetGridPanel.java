package ru.tet.javax.swing.aux;

import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * Панель с TetGridPanel. Позволяет удобно размещать компоненты в сетке.
 * 
 * Пример:
 * tetGridPanel = new TetGridPanel();
 * tetGridPanel.setColWidthes(150,200).setRowHeights(50,25);
 * tetGridPanel.add(label1,tf1,label2,tf2);
 * 
 * @author tetsuma
 *
 */
public class TetGridPanel extends JPanel {

	TetGridLayout gridLayout = new TetGridLayout();

	public TetGridPanel() {
		setLayout(gridLayout);
	}

	public TetGridPanel setColWidthes(int... colWidths) {
		gridLayout.setColWidthes(colWidths);
		return this;
	}

	public TetGridPanel setRowHeights(int... rowHeights) {
		gridLayout.setRowHeights(rowHeights);
		return this;
	}

	public TetGridPanel setHgap(int hgap) {
		gridLayout.setHgap(hgap);
		return this;
	}

	public TetGridPanel setVgap(int vgap) {
		gridLayout.setVgap(vgap);
		return this;
	}

	public TetGridPanel add(JComponent... components) {
		for (int i = 0; i < components.length; i++) {
			add(components[i]);
		}
		return this;
	}

}
