package ru.tet.javax.swing.aux.comp;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.HierarchyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.time.LocalTime;
import java.time.ZoneId;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


/**
 * Компонент, прорисовывающий аналоговые часы.
 * 
 * Работает в двух режимах: с использованием BufferedImage для прорисовки фона, и без него.
 * @author tetsuma
 *
 */
public class CustomComponent_AnalogClock extends JComponent {

	// угол поворота стрелок, в радианах
	double secondRot;
	double minuteRot;
	double hourRot;

	Timer timer;

	
	Rectangle innerArea;
	double radius;
	Shape hourMarker,minuteMarker,hourHand,minuteHand,secondHand;
	
	boolean useBackgroundImage = false;
	
	BufferedImage backgroundImage;

	public CustomComponent_AnalogClock() {

		setBorder(BorderFactory.createLineBorder(Color.BLUE, 6));

		timer = new Timer(1000, e -> {
			updateTime();
			repaint();
		});

		addHierarchyListener(e -> {
			if ((e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0) {
				if (e.getComponent().isShowing()) {
					updateTime();
					initAfterResize();
					timer.start();
					System.out.println("start timer");
				} else {
					timer.stop();
					System.out.println("stop timer");
				}
			}
		});

		addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {
				initAfterResize();
				
			}

		});

	}

	public void setUseBackgroundImage(boolean useBackgroundImage) {
		this.useBackgroundImage = useBackgroundImage;
		if (useBackgroundImage) {
			updateBackgroundImage();
		} else {
			backgroundImage = null;
		}
		repaint();
	}
	
	private void updateTime() {
		LocalTime time = LocalTime.now(ZoneId.systemDefault());
		secondRot = time.getSecond() * Math.PI / 30d;
		minuteRot = time.getMinute() * Math.PI / 30d + secondRot / 60d;
		hourRot = time.getHour() * Math.PI / 6d + minuteRot / 12d;
	}
	
	private void initAfterResize() {

		innerArea = SwingUtilities.calculateInnerArea(this, null);
		
		if (innerArea.getWidth()<0) {
			return;
		}
		
		radius = Math.min(innerArea.width, innerArea.height) / 2d - 10d;
		
		double hourMarkerLen = radius / 6d - 10d;
		hourMarker = new Line2D.Double(0d, hourMarkerLen - radius, 0d, -radius);
		minuteMarker = new Line2D.Double(0d, hourMarkerLen / 2d - radius, 0d, -radius);
		
		// Drawing the hour hand
		double hourHandLen = radius * 0.8;
		hourHand = new Line2D.Double(0d, 0d, 0d, -hourHandLen);

		// Drawing the minute hand
		double minuteHandLen = radius * 0.9;
		minuteHand = new Line2D.Double(0d, 0d, 0d, -minuteHandLen);
		
		double secondHandLen = radius * 0.95;
		secondHand = new Line2D.Double(0d, 0d, 0d, -secondHandLen);
	
		if (useBackgroundImage) {
			updateBackgroundImage();
		}
	}
	
	private void updateBackgroundImage() {
		Rectangle rect = new Rectangle(innerArea);
		rect.setLocation(0, 0);
		
		backgroundImage = new BufferedImage(rect.width, rect.height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = backgroundImage.createGraphics();
		paintBackground(g2, rect);
		g2.dispose();		
		
		System.out.println("backgroundImage painted:"+rect);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		//not initiated
		if (hourMarker==null) {
			return;
		}
		
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if (useBackgroundImage && backgroundImage!=null) {
			g2.drawImage(backgroundImage, innerArea.x, innerArea.y, null);
		} else {
			paintBackground(g2, innerArea);
		}
		
		g2.translate(innerArea.getCenterX(), innerArea.getCenterY());

		//рисуем часовую стрелку
		g2.setStroke(new BasicStroke(8f));
		g2.setPaint(Color.LIGHT_GRAY);
		g2.draw(AffineTransform.getRotateInstance(hourRot).createTransformedShape(hourHand));

		//рисуем минутную стрелку
		g2.setStroke(new BasicStroke(4f));
		g2.setPaint(Color.WHITE);
		g2.draw(AffineTransform.getRotateInstance(minuteRot).createTransformedShape(minuteHand));

		//рисуем секундную стрелку
		g2.setPaint(Color.RED);
		g2.setStroke(new BasicStroke(1f));
		g2.draw(AffineTransform.getRotateInstance(secondRot).createTransformedShape(secondHand));
		
		double centerDotWidth = radius / 12d;
		
		g2.fill(new Ellipse2D.Double(-centerDotWidth/2 , -centerDotWidth/2 , centerDotWidth , centerDotWidth ));

		g2.dispose();
	}


	private void paintBackground(Graphics2D g2, Rectangle rect) {
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// заливаем фон
		g2.setColor(Color.BLACK);
		g2.fill(rect);

		
		//применение при рисовании
		AffineTransform origAt = g2.getTransform();
		g2.translate(rect.getCenterX(), rect.getCenterY());

		
		//рисуем маркеры минут и часов
		AffineTransform at = AffineTransform.getRotateInstance(0d);
		g2.setStroke(new BasicStroke(2f));
		g2.setColor(Color.WHITE);
		for (int i = 0; i < 60; i++) {
			if (i % 5 == 0) {
				g2.draw(at.createTransformedShape(hourMarker));
			} else {
				g2.draw(at.createTransformedShape(minuteMarker));
			}
			at.rotate(Math.PI / 30d);
		}
		
		g2.setTransform(origAt);
		
		
	}	
	
}
