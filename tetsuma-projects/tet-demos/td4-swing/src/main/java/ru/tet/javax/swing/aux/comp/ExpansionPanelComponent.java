package ru.tet.javax.swing.aux.comp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * Панель с заголовком, которая умеет разворачиваться.
 * 
 * @author tetsuma
 *
 */
public class ExpansionPanelComponent extends JPanel {

	String title;
	CustomLabel label;
	JPanel panel;

	public ExpansionPanelComponent(String title, JPanel panel) {
		this.title = title;
		this.panel = panel;
		setLayout(new BorderLayout());

		label = new CustomLabel("");
		updateLabelTitle();
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				initPanel();
			}
		});

		add(label, BorderLayout.NORTH);

		panel.setVisible(false);
		panel.setOpaque(true);
		panel.setBackground(new Color(0xF0_F0_FF));
		Border outBorder = BorderFactory.createMatteBorder(0, 2, 2, 2, Color.WHITE);
		Border inBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		Border border = BorderFactory.createCompoundBorder(outBorder, inBorder);
		panel.setBorder(border);
		add(panel, BorderLayout.CENTER);

	}

	@Override
	public Dimension getPreferredSize() {
		Dimension d = label.getPreferredSize();
		if (panel.isVisible()) {
			d.height += panel.getPreferredSize().height;
		}
		return d;
	}

	//чтобы панель не распухала без нужды
	@Override
	public Dimension getMaximumSize() {
		Dimension d = getPreferredSize();
		d.width = Short.MAX_VALUE;
		return d;
	}

	protected void updateLabelTitle() {
		label.setText(String.format("%s %s", panel.isVisible() ? "△" : "▼", title));
	}

	protected void initPanel() {
		panel.setVisible(!panel.isVisible());
		updateLabelTitle();
		revalidate();
		// fireExpansionEvent();

		//прокрутить вышестоящий JScrollPane так, чтобы вся панель оказалась полностью видимой.
		EventQueue.invokeLater(() -> panel.scrollRectToVisible(panel.getBounds()));
	}

}

/*


 * 
 * 
 */
