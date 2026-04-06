package ru.tet.javax.swing.aux;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.Icon;

/**
 * Иконка загрузки. Может быть анимирована.
 * 
 * @author tetsuma
 *
 */
public class LoadingIcon2 implements Icon {
	private static final Color ELLIPSE_COLOR = new Color(0xE6_B3_B3);
	List<Shape> list;
//	Dimension dim;
	boolean running;
	int rotate = 0;

	int spotRadius;
	int iconWidth;
	
	
	public LoadingIcon2() {
		list = new ArrayList<>();

		spotRadius = 4;
		Shape spot = new Ellipse2D.Double(0d, 0d, 2d * spotRadius, 2d * spotRadius);
		
		for (int i = 0; i < 8; i++) {
			//круг сначала сдвинется правее и ниже, а затем повернётся
			AffineTransform at = AffineTransform.getRotateInstance(i * 2 * Math.PI / 8);
			at.concatenate(AffineTransform.getTranslateInstance(spotRadius, spotRadius));
			list.add(at.createTransformedShape(spot));
		}
		
		iconWidth = spotRadius * 2 * (1 + 3);
//		dim = new Dimension(d, d);
	}

	@Override
	public int getIconWidth() {
		return iconWidth;
	}

	@Override
	public int getIconHeight() {
		return iconWidth;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setPaint(Optional.ofNullable(c).map(Component::getBackground).orElse(Color.WHITE));
		g2.fillRect(x, y, getIconWidth(), getIconHeight());
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setPaint(ELLIPSE_COLOR);
		int xx = x + iconWidth / 2;
		int yy = y + iconWidth / 2;
		
		//
		AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(rotate), xx, yy);
//		AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(0), xx, yy);
		at.concatenate(AffineTransform.getTranslateInstance(xx, yy));
		
		
		int size = list.size();
		for (int i = 0; i < size; i++) {
			float alpha = running ? (i + 1) / (float) size : .5f;
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
			g2.fill(at.createTransformedShape(list.get(i)));
		}
		g2.dispose();
	}

	public void next() {
		if (running) {
			rotate = (rotate + 45) % 360; // 45 = 360 / 8
		}
	}

	public void setRunning(boolean isRunning) {
		running = isRunning;
	}
}