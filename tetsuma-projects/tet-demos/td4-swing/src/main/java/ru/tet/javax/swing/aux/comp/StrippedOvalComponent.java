package ru.tet.javax.swing.aux.comp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

public class StrippedOvalComponent extends JComponent {

	@Override
	protected void paintComponent(Graphics g) {

		Dimension size = getSize();
		int x = 0;
		int y = 0;
		int i = 0;
		while (x < size.width && y < size.height) {
			g.setColor(i % 2 == 0 ? Color.red : Color.white);
			g.fillOval(x, y, size.width - (2 * x), size.height - (2 * y));
			x += 10;
			y += 10;
			i++;
		}

	}

}