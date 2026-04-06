package ru.tet.javax.swing.aux;

import java.awt.Dimension;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.swing.JLabel;

/**
 * Подпись к текстовому полю.
 * Умеет синхронизировать свой размер с другими метками.
 * 
 * @author tetsuma
 *
 */
public class AlignedLabel extends JLabel {
	  private static final int INDENT = 10;
	  private List<AlignedLabel> group;
	  private int maxWidth;

	  public AlignedLabel(String text) {
	    super(text, RIGHT);
	  }

	  @Override public Dimension getPreferredSize() {
	    Dimension d = super.getPreferredSize();
	    d.width = getMaxWidth() + INDENT;
	    return d;
	  }

	  private int getMaxWidth() {
	    if (maxWidth == 0 && Objects.nonNull(group)) {
	      int max = group.stream()
	          .map(AlignedLabel::getSuperPreferredWidth)
	          .reduce(0, Integer::max);
	      group.forEach(al -> al.maxWidth = max);
	    }
	    return maxWidth;
	  }

	  private int getSuperPreferredWidth() {
	    return super.getPreferredSize().width;
	  }

	  public static void groupLabels(AlignedLabel... list) {
	    List<AlignedLabel> group = Arrays.asList(list);
	    group.forEach(al -> al.group = group);
	  }
	}