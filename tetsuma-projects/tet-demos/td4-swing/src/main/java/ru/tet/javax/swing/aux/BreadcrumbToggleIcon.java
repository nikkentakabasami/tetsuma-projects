package ru.tet.javax.swing.aux;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

import javax.swing.Icon;

import ru.tet.javax.swing.aux.comp.BreadcrumbToggleButton;

/**
 * Прорисовывает BreadcrumbToggleButton
 * 
 * @author tetsuma
 *
 */
public class BreadcrumbToggleIcon implements Icon {
	
	public static final int TRIANGLE_HEIGHT = 15; // The height of a triangle

	BreadcrumbToggleButton button;
	
	int width,height;
//	boolean first;
	
	//цвет выбранной кнопки
	Color selectedColor;
	
	private Shape bcShape;

	
	public BreadcrumbToggleIcon(BreadcrumbToggleButton button, Color selectedColor) {
		this.button = button;
		this.selectedColor = selectedColor;

		
		Dimension size = button.getPreferredSize();
		this.width = size.width-1;
		this.height = size.height-1;
		
		
		bcShape = makeShape();
		
	}
	
	public Shape getShape() {
		return bcShape;
	}
	
	protected Shape makeShape() {
		
		double h2 = Math.round(height * .5);
		double w2 = TRIANGLE_HEIGHT;
		
		Path2D p = new Path2D.Double();
		p.moveTo(0d, 0d);
		p.lineTo(width - w2, 0d);
		p.lineTo(width, h2);
		p.lineTo(width - w2, height);
		p.lineTo(0d, height);
		if (!button.isFirst()) {
			p.lineTo(w2, h2);
		}
		p.closePath();
		
		return p;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g.create();
		

		Shape shape = bcShape; 
		if (x!=0 || y!=0) {
			shape = AffineTransform.getTranslateInstance(x, y).createTransformedShape(bcShape); 
		}

		Color borderColor;
		Color bgc;
		
		if (button.isSelected()) {
			borderColor = Color.GRAY;
			bgc = selectedColor;
		} else {
			borderColor = Color.GRAY.brighter();
			bgc = c.getParent().getBackground();
		}

		g2.setPaint(bgc);
		g2.fill(shape);
		
		g2.setPaint(borderColor);
		g2.draw(shape);
		g2.dispose();
	}

	@Override
	public int getIconWidth() {
		return width;
	}

	@Override
	public int getIconHeight() {
		return height;
	}
}