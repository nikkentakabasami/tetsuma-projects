package ru.tet.javax.swing.aux;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Layout, располагающий компоненты в ячейках сетки. Растягивает их до размеров
 * ячейки.
 * 
 * размеры строк и столбцов задаются методами setColWidths, setRowHeights.
 * 
 * 
 * @author tetsuma
 *
 */
@Getter
public class TetGridLayout implements LayoutManager, java.io.Serializable {

	public static final int DEFAULT_COL_WIDTH = 100;
	public static final int DEFAULT_ROW_HEIGHT = 25;

	public static final int MIN_COL_WIDTH = 50;
	
	
	int[] colWidthes = { DEFAULT_COL_WIDTH, DEFAULT_COL_WIDTH };
	int[] rowHeights = { DEFAULT_ROW_HEIGHT };

	int hgap = 10;
	int vgap = 10;

	// значения по умолчанию
//	int rowHeight=50, colWidth=100;

	public void setColWidthes(int... colWidths) {
		this.colWidthes = colWidths;
	}

	public void setRowHeights(int... rowHeights) {
		this.rowHeights = rowHeights;
	}

	public void setHgap(int hgap) {
		this.hgap = hgap;
	}
	
	public void setVgap(int vgap) {
		this.vgap = vgap;
	}
	
	@Override
	public Dimension minimumLayoutSize(Container parent) {
		Dimension result = new Dimension();
		result.width = Arrays.stream(colWidthes).sum();
		result.height = Arrays.stream(rowHeights).sum();
		return result;
	}

	@Override
	public Dimension preferredLayoutSize(Container parent) {
		return minimumLayoutSize(parent);
	}

	
	
	private int[] calcActualWidthes(int[] widthes, int fullWidth) {

		
		int[] result = new int[widthes.length];
		
		List<Integer> autoWidthColumns = new ArrayList<>();
		
		int leftWidth = fullWidth;
		for (int i = 0; i < widthes.length; i++) {
			int width = widthes[i];
			
			if (width>0) {
				result[i] = width;
				leftWidth-=width;
			} else {
				result[i] = MIN_COL_WIDTH;
				autoWidthColumns.add(i);
			}
		}

		if (autoWidthColumns.size()>0 && leftWidth>0) {
			int autoWidth = leftWidth/autoWidthColumns.size();
			if (autoWidth>MIN_COL_WIDTH) {
				for(Integer colIndex:autoWidthColumns) {
					result[colIndex] = autoWidth;
				}
			}
		}
		
		return result;
		
		
	}
	
	
	@Override
	public void layoutContainer(Container parent) {
		int compCount = parent.getComponentCount();

		int colCount = colWidthes.length;
		int rowCount = (compCount+1)/colCount;
		
		
		int gapWidth = hgap*(colCount+1);
		int gapHeight = vgap*(rowCount+1);
		
		int[] actualHeights = Arrays.copyOf(rowHeights, rowCount);
		int lastRowHeight = rowHeights[rowHeights.length-1]; 
		if (lastRowHeight>0 && rowCount>rowHeights.length) {
			for (int i = rowHeights.length-1; i < actualHeights.length; i++) {
				actualHeights[i] = lastRowHeight;
			}
		}
		
		
		int[] actualWidthes = calcActualWidthes(colWidthes, parent.getWidth()-gapWidth);
		actualHeights = calcActualWidthes(actualHeights, parent.getHeight()-gapHeight);
		

		int row = 0;
		int col = 0;
		int rowHeight = actualHeights[0];

		int cx = hgap;
		int cy = vgap;

		for (int compIndex = 0; compIndex < compCount; compIndex++) {
			Component comp = parent.getComponent(compIndex);

			col = compIndex % colCount;
			row = compIndex / colCount;

			// переход на новую строку
			if (compIndex > 0 && col == 0) {
				cx = hgap;
				cy += rowHeight + vgap;
			}

			int colWidth = actualWidthes[col];
			if (row < actualHeights.length) {
				rowHeight = actualHeights[row];
			}

			comp.setSize(colWidth, rowHeight);
			comp.setLocation(cx, cy);

			// переход на следующий столбец
			cx += colWidth + hgap;

		}
	}

	@Override
	public void addLayoutComponent(String name, Component comp) {
	}

	@Override
	public void removeLayoutComponent(Component comp) {
	}

}
