package ru.tet.javax.swing.aux;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.Optional;
import java.util.function.Function;

import javax.swing.Icon;

/**
 * Иконка загрузки. Может быть анимирована.
 * 
 * @author tetsuma
 *
 */
public class LoadingIcon implements Icon {
//	private static final Color ELLIPSE_COLOR = new Color(0xE6_B3_B3);
	private static final Color ELLIPSE_COLOR = new Color(0xD6_A3_A3);

	//число пятен
	private static final int SPOT_QUANTITY = 8;
	
	//угол между пятнами
	private static final double SPOT_ANGLE = 2 * Math.PI / SPOT_QUANTITY;

	
	Shape spot;

//	List<Shape> list;
//	Dimension dim;

	//текущий поворот иконки
	int rotate = 0;

	int spotRadius;
	int iconWidth;
	
	//способ рисовки картинки
	int paintMode = 0;
	

	public LoadingIcon() {
//		list = new ArrayList<>();

		spotRadius = 4;
		
		//пятно в правом нижнем углу (относительно центра координат)
		spot = new Ellipse2D.Double(spotRadius, spotRadius, 2d * spotRadius, 2d * spotRadius);

		iconWidth = spotRadius * 2 * (1 + 3);


	}

	
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setPaint(Optional.ofNullable(c).map(Component::getBackground).orElse(Color.WHITE));
//		g2.setPaint(Color.WHITE);
		g2.fillRect(x, y, getIconWidth(), getIconHeight());
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		if (paintMode==0) {
			paintMethod0(g2, x, y);
		} else if (paintMode==1) {
			paintMethod1(g2, x, y);
		} else if (paintMode==2) {
			paintMethod2(g2, x, y);
		}
		
		g2.dispose();
	}
	
		
	private void paintMethod0(Graphics2D g2, int x, int y) {
		
		g2.setPaint(ELLIPSE_COLOR);
		
		//перемещаем центр координат в центр иконки
		g2.translate(x + iconWidth / 2, y + iconWidth / 2);

		//Задаём начальный угол вращения
		double rotateRads = Math.toRadians(rotate);
		g2.rotate(rotateRads);
		
		for (int i = 0; i < SPOT_QUANTITY; i++) {

			//Задаём прозрачность текущего пятна
			float alpha = (i + 1) / (float)SPOT_QUANTITY;
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

			//вращаем
			g2.rotate(SPOT_ANGLE);
			
			//рисуем
			g2.fill(spot);
		}		
		
	}
	
	//то же что и paintMethod0, но используем цвет вместо прозрачности
	private void paintMethod1(Graphics2D g2, int x, int y) {
		//перемещаем центр координат в центр иконки
		g2.translate(x + iconWidth / 2, y + iconWidth / 2);

		//Задаём начальный угол вращения
		double rotateRads = Math.toRadians(rotate);
		g2.rotate(rotateRads);
		
		Color currentColor = ELLIPSE_COLOR;
		for (int i = 0; i < SPOT_QUANTITY; i++) {
			currentColor = JFrameForGraphicsTests.brighter(currentColor, 0.9);
			g2.setPaint(currentColor);
			
			g2.rotate(SPOT_ANGLE);
			g2.fill(spot);
		}		
	}	

	
	private void paintMethod2(Graphics2D g2, int x, int y) {

		
		int xx = x + iconWidth / 2;
		int yy = y + iconWidth / 2;
		
		double rotateRads = Math.toRadians(rotate);
		
		//Все эти объявления идентичны!!!
		
//		AffineTransform at = new AffineTransform();
//		at.translate(xx, yy);
//		at.rotate(rotateRads);
		
		AffineTransform at = AffineTransform.getRotateInstance(rotateRads, xx, yy);
		at.concatenate(AffineTransform.getTranslateInstance(xx, yy));
		
//		AffineTransform at = AffineTransform.getRotateInstance(rotateRads);
//		at.preConcatenate(AffineTransform.getTranslateInstance(xx, yy));
		
		
//		AffineTransform at = AffineTransform.getTranslateInstance(xx, yy);
//		at.rotate(rotateRads);
		
//		AffineTransform at = AffineTransform.getTranslateInstance(xx, yy);
//		at.concatenate(AffineTransform.getRotateInstance(rotateRads));
		
		Color currentColor = ELLIPSE_COLOR;
		
		for (int i = 0; i < SPOT_QUANTITY; i++) {
			currentColor = JFrameForGraphicsTests.brighter(currentColor, 0.9);
			g2.setPaint(currentColor);
			
//			at.preConcatenate(AffineTransform.getRotateInstance(SPOT_ANGLE, xx, yy));
			at.rotate(SPOT_ANGLE);
			
			g2.fill(at.createTransformedShape(spot));
			
		}		
	}		
	

	
	
	
	public void next() {
		rotate = (rotate + 45) % 360;
	}
	

	@Override
	public int getIconWidth() {
		return iconWidth;
	}

	@Override
	public int getIconHeight() {
		return iconWidth;
	}
	
	public void setPaintMode(int paintMode) {
		this.paintMode = paintMode;
	}
	

}