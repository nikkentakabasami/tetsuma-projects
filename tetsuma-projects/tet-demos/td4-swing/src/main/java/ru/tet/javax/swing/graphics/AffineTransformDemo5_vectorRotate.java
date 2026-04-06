package ru.tet.javax.swing.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.Arrays;
import java.util.List;

import javax.swing.SwingUtilities;

import ru.tet.javax.swing.aux.JFrameForGraphicsTests;

public class AffineTransformDemo5_vectorRotate extends JFrameForGraphicsTests {

	List<Arrow> arrows;

	@Override
	protected void doInit() throws Exception {
		initWithCanvasComponent();

		arrows = Arrays.asList(new Arrow(new Point(50, 50), new Point(100, 150)),
				new Arrow(new Point(250, 50), new Point(150, 50)));

	}

	@Override
	public void paintInCanvas(Graphics2D g2) throws Exception {

		paintGrid(g2);
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setStroke(new BasicStroke(4));
		g2.setColor(Color.BLACK);
		for (Arrow a : arrows) {
			a.draw(g2);
		}

	}

	public static void main(String[] args) throws Exception {

		SwingUtilities.invokeLater(() -> {
			AffineTransformDemo5_vectorRotate frame = new AffineTransformDemo5_vectorRotate();
			frame.init();
		});

	}

}

class Arrow {
	Point start, end;
	Path2D arrowHead;

	protected Arrow(Point start, Point end) {
		this.start = start;
		this.end = end;
		arrowHead = makeArrowHead(new Dimension(8, 8));

		AffineTransform at = AffineTransform.getTranslateInstance(end.getX(), end.getY());
		at.rotate(end.getX() - start.getX(), end.getY() - start.getY());

		//или так
//		AffineTransform at = new AffineTransform();
//	    at.translate(end.getX(), end.getY());
//		at.rotate(end.getX() - start.getX(), end.getY() - start.getY());
		
		
		// translate выполняется в направлении поворота
//	    AffineTransform at = AffineTransform.getRotateInstance(end.getX() - start.getX(), end.getY() - start.getY());
//	    at.translate(end.getX(), end.getY());
//		at.translate(10, 10);

		arrowHead.transform(at);

	}

	protected Path2D makeArrowHead(Dimension size) {
		Path2D path = new Path2D.Double();
		double w = size.width * .5;
		double h = size.height;
		path.moveTo(0d, -w);
		path.lineTo(h, 0d);
		path.lineTo(0d, w);
		path.closePath();
		return path;
	}

	public void draw(Graphics2D g2) {
		g2.drawLine(start.x, start.y, end.x, end.y);

		g2.fill(arrowHead);
		g2.draw(arrowHead);

	}
}
