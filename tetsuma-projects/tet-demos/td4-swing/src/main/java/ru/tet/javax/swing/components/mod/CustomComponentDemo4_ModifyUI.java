package ru.tet.javax.swing.components.mod;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.BoundedRangeModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicScrollBarUI;

import ru.tet.javax.swing.aux.DemoDataSamples;
import ru.tet.javax.swing.aux.JFrameForTests;

public class CustomComponentDemo4_ModifyUI extends JFrameForTests {

	@Override
	protected void doInit() throws Exception {

		initWithControlPanelAbove();
		workPanel.setLayout(new GridLayout(1, 0));

		//модифицируем JScrollPane чеерз настройки
		UIManager.put("ScrollBar.width", 40);
		UIManager.put("ScrollBar.thumbHeight", 20); // SynthLookAndFeel(GTK, Nimbus)
		UIManager.put("ScrollBar.minimumThumbSize", new Dimension(30, 30));
		UIManager.put("ScrollBar.incrementButtonGap", 0);
		UIManager.put("ScrollBar.decrementButtonGap", 0);

		String testText = DemoDataSamples.loadTestText();

		// Задаём для ScrollBar-ов кастомный ui
		JScrollPane sp1 = new JScrollPane(new JTextArea(testText)) {
			@Override
			public void updateUI() {
				super.updateUI();
				getVerticalScrollBar().setUI(new ArrowButtonlessScrollBarUI());
				getHorizontalScrollBar().setUI(new ArrowButtonlessScrollBarUI());
			}
		};

		//обычный JScrollPane - для сравнения
		JScrollPane sp2 = new JScrollPane(new JTextArea(testText));

		workPanel.add(sp1);
		workPanel.add(sp2);

		controlPanel.addButton("-", e -> {

		});

	}

	public static void main(String[] args) throws Exception {

		SwingUtilities.invokeLater(() -> {
			CustomComponentDemo4_ModifyUI frame = new CustomComponentDemo4_ModifyUI();
			frame.init();
		});

	}

}

class ZeroSizeButton extends JButton {
	private static final Dimension ZERO_SIZE = new Dimension();

	@Override
	public Dimension getPreferredSize() {
		return ZERO_SIZE;
	}
}

/**
 * ScrollBar без кнопок, с изменённой рисовкой.
 * 
 * @author tetsuma
 *
 */
class ArrowButtonlessScrollBarUI extends BasicScrollBarUI {
	@Override
	protected JButton createDecreaseButton(int orientation) {
		return new ZeroSizeButton();
	}

	@Override
	protected JButton createIncreaseButton(int orientation) {
		return new ZeroSizeButton();
	}

	// прорисовка полосы
	@Override
	protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setPaint(Color.red);
//    g2.setPaint(trackColor);
		g2.fill(r);
		g2.dispose();
	}

	// прорисовка кнопки
	@Override
	protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
		JScrollBar sb = (JScrollBar) c;
		if (!sb.isEnabled()) {
			return;
		}
		BoundedRangeModel m = sb.getModel();
		if (m.getMaximum() - m.getMinimum() - m.getExtent() > 0) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			Color color;
			if (isDragging) {
//        color = thumbDarkShadowColor;
				color = Color.GREEN;
			} else if (isThumbRollover()) {
//        color = thumbLightShadowColor;
				color = Color.BLUE;
			} else {
				color = thumbColor;
			}
			g2.setPaint(color);
			g2.fillRect(r.x + 1, r.y + 1, r.width - 2, r.height - 2);
			g2.dispose();
		}
	}
}
