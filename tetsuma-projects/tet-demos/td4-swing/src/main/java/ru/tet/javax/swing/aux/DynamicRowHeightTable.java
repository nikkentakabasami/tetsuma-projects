package ru.tet.javax.swing.aux;

import java.util.Optional;

import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import javax.swing.table.TableModel;

public class DynamicRowHeightTable extends JTable {
	private int prevHeight = -1;
	private int prevCount = -1;

	public DynamicRowHeightTable(TableModel model) {
		super(model);
	}

	@Override
	public void doLayout() {
		super.doLayout();
		Class<JViewport> clz = JViewport.class;
		Optional.ofNullable(SwingUtilities.getAncestorOfClass(clz, this)).filter(clz::isInstance).map(clz::cast)
				.ifPresent(this::updateRowsHeight);
	}

	/**
	 * Меняет высоту строк в соответствии с текущим размером таблицы
	 * @param viewport
	 */
	private void updateRowsHeight(JViewport viewport) {
		int height = viewport.getExtentSize().height;
		int rowCount = getModel().getRowCount();
		int defaultRowHeight = height / rowCount;
		if ((height != prevHeight || rowCount != prevCount) && defaultRowHeight > 0) {
			int remainder = height % rowCount;
			for (int i = 0; i < rowCount; i++) {
				int a = Math.min(1, Math.max(0, remainder--));
				setRowHeight(i, defaultRowHeight + a);
			}
		}
		prevHeight = height;
		prevCount = rowCount;
	}
}