package ru.tet.javax.swing.aux;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Мой аналог BoxLayout.
 * 
 * 
 * 
 * @author tetsuma
 *
 */
public class TetBoxLayout implements LayoutManager2 {

	public static final int MIN_COL_WIDTH = 5;
	public static final int DEFAULT_OFFSET = 5;

	int defaultOffset = DEFAULT_OFFSET;

	boolean horizontal;

	Map<Component, TetBoxLayoutConstraint> constraints = new HashMap<>();

	private int[] actualWidthes;
	private int[] actualOffsets;

	//не растягивать по длине. Использовать предпочтительный размер компонента
	boolean expandLength = true;

	//не растягивать по ширине. Использовать предпочтительный размер компонента
	boolean expandWidth = true;

	public TetBoxLayout(boolean horizontal) {
		this.horizontal = horizontal;
	}

	public void setDefaultOffset(int defaultOffset) {
		this.defaultOffset = defaultOffset;
	}

	@Override
	public Dimension maximumLayoutSize(Container target) {
		return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
	}

	@Override
	public Dimension minimumLayoutSize(Container parent) {

		int fullWidth = 0;
		int minHeight = 0;

		Insets insets = parent.getInsets();

		if (horizontal) {
			fullWidth += insets.left + insets.right;
		} else {
			fullWidth += insets.top + insets.bottom;
		}

		Component[] comps = parent.getComponents();
		for (int i = 0; i < comps.length; i++) {
			Component component = comps[i];
			TetBoxLayoutConstraint constr = constraints.get(component);

			//			Dimension preferredSize = component.getMinimumSize();
			Dimension preferredSize = component.getPreferredSize();
			int compMinWidth = horizontal ? preferredSize.width : preferredSize.height;
			if (constr != null) {
				compMinWidth = Math.max(constr.width, compMinWidth);
			}

			int offset = calcOffset(constr);
			compMinWidth += offset;

			minHeight = Math.max(minHeight, horizontal ? preferredSize.height : preferredSize.width);

			fullWidth += compMinWidth;
		} //for

		if (horizontal) {
			minHeight += insets.top + insets.bottom;
		} else {
			minHeight += insets.left + insets.right;
		}

		Dimension result = new Dimension();
		if (horizontal) {
			result.width = fullWidth;
			result.height = minHeight;
		} else {
			result.height = fullWidth;
			result.width = minHeight;
		}

		return result;
	}

	@Override
	public Dimension preferredLayoutSize(Container parent) {
		return minimumLayoutSize(parent);
	}

	//	private int calcCompWidths() {
	//	}

	private int calcOffset(TetBoxLayoutConstraint constr) {
		if (constr != null && constr.offset >= 0) {
			return constr.offset;
		} else {
			return defaultOffset;
		}

	}

	private void calcActualWidthes(Component[] comps, int fullWidth) {

		actualWidthes = new int[comps.length];
		actualOffsets = new int[comps.length];

		List<Integer> autoWidthColumns = new ArrayList<>();

		int leftWidth = fullWidth;
		for (int i = 0; i < comps.length; i++) {
			Component component = comps[i];
			TetBoxLayoutConstraint constr = constraints.get(component);

			int colWidth = MIN_COL_WIDTH;
			int colOffset = calcOffset(constr);

			if (constr != null) {

				if (constr.width > 0) {
					colWidth = constr.width;
				} else if (constr.width < 0) {
					colWidth =
							(int) (horizontal
									? component.getPreferredSize().getWidth()
									: component.getPreferredSize().getHeight());
				} else {
					autoWidthColumns.add(i);
				}

				leftWidth -= (colWidth + colOffset);
			} else {
				autoWidthColumns.add(i);
				leftWidth -= colOffset;
			}

			actualWidthes[i] = colWidth;
			actualOffsets[i] = colOffset;

		} //for

		if (autoWidthColumns.size() > 0 && leftWidth > 0) {
			int autoWidth = leftWidth / autoWidthColumns.size();
			if (autoWidth > MIN_COL_WIDTH) {
				for (Integer colIndex : autoWidthColumns) {
					actualWidthes[colIndex] = autoWidth;
				}
			}
		}

	}

	@Override
	public void layoutContainer(Container parent) {

		Component[] comps = parent.getComponents();

		Insets insets = parent.getInsets();

		int x = insets.left;
		int y = insets.top;
		int clientSpaceHeight = parent.getHeight() - insets.top - insets.bottom;
		int clientSpaceWidth = parent.getWidth() - insets.left - insets.right;

		
		if (!horizontal) {
			int temp = x; x = y; y = temp;			
			temp = clientSpaceHeight; clientSpaceHeight = clientSpaceWidth; clientSpaceWidth = temp;			
		}

		calcActualWidthes(comps, clientSpaceWidth);

		for (int i = 0; i < comps.length; i++) {
			Component component = comps[i];

			int width = actualWidthes[i];
			int offset = actualOffsets[i];

			int actualHeight = clientSpaceHeight;
			int actualWidth = width;

			if (!expandWidth) {
				Dimension ps = component.getPreferredSize();
				int h = horizontal ? ps.height : ps.width;
				if (h < clientSpaceHeight) {
					actualHeight = h;
				}
			}

			if (!expandLength) {
				Dimension ps = component.getPreferredSize();
				int w = horizontal ? ps.width : ps.height;
				if (w < width) {
					actualWidth = w;
				}
			}

			x += offset;
			if (horizontal) {
				component.setBounds(x, y, actualWidth, actualHeight);
			} else {
				component.setBounds(y, x, actualHeight, actualWidth);
			}
			x += width;

		}

	}

	public void setExpandLength(boolean expandLength) {
		this.expandLength = expandLength;
	}

	public void setExpandWidth(boolean expandWidth) {
		this.expandWidth = expandWidth;
	}

	@Override
	public void addLayoutComponent(Component comp, Object c) {
		constraints.put(comp, (TetBoxLayoutConstraint) c);

	}

	@Override
	public void removeLayoutComponent(Component comp) {
		constraints.remove(comp);
	}

	@Override
	public void addLayoutComponent(String name, Component comp) {
	}

	@Override
	public void invalidateLayout(Container target) {
	}

	@Override
	public float getLayoutAlignmentX(Container target) {
		return 0.5f;
	}

	@Override
	public float getLayoutAlignmentY(Container target) {
		return 0.5f;
	}
}
