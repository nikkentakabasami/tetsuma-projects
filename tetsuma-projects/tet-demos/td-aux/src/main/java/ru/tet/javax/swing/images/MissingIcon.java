package ru.tet.javax.swing.images;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.Icon;

public class MissingIcon implements Icon {
	
	int width = 320;
	int height = 240;
	
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g.create();
		int gap = width / 5;
		g2.translate(x, y);
		
		
		RoundRectangle2D roundRect1 = new RoundRectangle2D.Double(2, 2, width-4, height-4, 10, 10);
		g2.setColor(Color.WHITE);
		g2.fill(roundRect1);
		g2.setPaint(Color.BLUE);
		g2.setStroke(new BasicStroke(2.0f));
		g2.draw(roundRect1);
		
		g2.setColor(Color.RED);
		g2.setStroke(new BasicStroke(width / 8f));
		g2.drawLine(gap, gap, gap, height - gap);
		g2.drawLine(gap, height - gap, width - gap, gap);
		
		
		g2.setColor(Color.GRAY);
		Font font = new Font("TimesRoman", Font.BOLD, 20);
		g2.setFont(font);
		g2.drawString("my icon", 10, 30);
		
		
		g2.dispose();
	}

	public int getIconWidth() {
		return width;
	}

	public int getIconHeight() {
		return height;
	}
}
