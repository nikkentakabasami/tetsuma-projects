package ru.tet.javax.swing.aux;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

public class GrayIcon implements Icon {
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		g.setColor(Color.GRAY);
		g.drawOval(x, y, getIconWidth(), getIconHeight());
	}

	@Override
	public int getIconWidth() {
		return 12;
	}

	@Override
	public int getIconHeight() {
		return 12;
	}
}
