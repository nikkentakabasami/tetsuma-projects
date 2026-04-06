package ru.tet.javax.swing.aux.comp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

public class CustomComponent2 extends JComponent {

	public CustomComponent2() {
		
	    setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
	}
	
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		Font f = new Font("SansSerif", Font.BOLD, 18);
		g2.setFont(f);

		String message = "My Component string";
		FontRenderContext context = g2.getFontRenderContext();
		Rectangle2D bounds = f.getStringBounds(message, context);

		double paddingHoriz = (getWidth() - bounds.getWidth()) / 2;
		double paddingVert = (getHeight() - bounds.getHeight()) / 2;
		double ascent = -bounds.getY();
		double baseY = paddingVert + ascent;

		g2.drawString(message, (int) paddingHoriz, (int) baseY);
		g2.setPaint(Color.LIGHT_GRAY);
		
		//подчёркивание
		Line2D.Double line = new Line2D.Double(paddingHoriz, baseY, paddingHoriz + bounds.getWidth(), baseY);
		g2.draw(line);
		
		//рамка
		Rectangle2D rect = new Rectangle2D.Double(paddingHoriz, paddingVert, bounds.getWidth(), bounds.getHeight());
		g2.draw(rect);
	}

	public Dimension getPreferredSize() {
		return new Dimension(400, 200);
	}

}
