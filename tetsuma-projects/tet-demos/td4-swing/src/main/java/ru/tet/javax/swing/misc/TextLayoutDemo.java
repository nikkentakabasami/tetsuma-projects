package ru.tet.javax.swing.misc;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import javax.swing.SwingUtilities;

import ru.tet.javax.swing.aux.JFrameForGraphicsTests;

public class TextLayoutDemo extends JFrameForGraphicsTests {

	@Override
	protected void doInit() throws Exception {
		initWithCanvasComponent();

		controlPanel.addTextArea();
		
	}

	@Override
	public void paintInCanvas(Graphics2D g2) throws Exception {

		paintGrid(g2);

		String text = "Hello, World!";
		Font font = new Font("Serif", Font.PLAIN, 44);
		FontRenderContext frc = g2.getFontRenderContext();

		TextLayout textLayout = new TextLayout(text, font, frc);

		// Получение размеров текста
//		float advance = textLayout.getAdvance();
//		float height = textLayout.getAscent() + textLayout.getDescent();

		int x = 100, y = 100;

		// draw bounds
		Rectangle2D bounds = textLayout.getBounds();
		controlPanel.textArea.append(String.format("bounds:"+bounds+"\n"));		
		
		
		bounds.setRect(bounds.getX() + x, bounds.getY() + y, bounds.getWidth(), bounds.getHeight());
		g2.setColor(Color.BLUE);
		g2.draw(bounds);

		// draw text
		g2.setColor(Color.BLACK);
		textLayout.draw(g2, x, y);

		// draw Outline
		AffineTransform at = AffineTransform.getTranslateInstance(100, 200);
		Shape outline = textLayout.getOutline(at);
		g2.draw(outline);


		controlPanel.textArea.append(String.format("ascent=%.1f, descent=%.1f, leading=%.1f\n",
				textLayout.getAscent(),
				textLayout.getDescent(),
				textLayout.getLeading()
				));		

		
		
		
		// draw rotated text
		at = AffineTransform.getTranslateInstance(100, 300);
		at.rotate(Math.PI / 12);
//		at.rotate(Math.PI / 12);
//		at.preConcatenate(AffineTransform.getTranslateInstance(0, 100));
		outline = textLayout.getOutline(at);
		g2.setColor(Color.RED);
		g2.fill(outline);

		
		
		// draw text aligned to center
		double tx = -textLayout.getAdvance()/2;
//		double ty = textLayout.getAscent()/2;
		double ty = (textLayout.getAscent()-textLayout.getDescent())/2;  //почему то выдаётся неправильный ascent

		//смещаем центр надписи в начало координат
		outline = textLayout.getOutline(AffineTransform.getTranslateInstance(tx, ty));

		AffineTransform savedTransform = g2.getTransform();
		
		//показываем центрированный текст
		g2.translate(200, 400);
		g2.fill(outline);
		
		//
//		outline = AffineTransform.getTranslateInstance(tx, ty).createTransformedShape(outline);
		
		//показываем повернутый текст
		Shape s1 = AffineTransform.getRotateInstance(Math.PI / 8).createTransformedShape(outline);
		Shape s2 = AffineTransform.getRotateInstance(-Math.PI / 8).createTransformedShape(outline);
		
		g2.translate(0, 100);
		g2.fill(s1);
		g2.fill(s2);
		
		
		
		//показываем циферблат
		g2.setTransform(savedTransform);
		g2.translate(700, 300);
		
		String[] EARTHLY_BRANCHES = { "子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥" };

//		at = AffineTransform.getTranslateInstance(900, 300);
//		at = AffineTransform.getTranslateInstance(0, 0);
		
		AffineTransform rotateTransform = AffineTransform.getRotateInstance(Math.PI / 6d);
		

		double radius = 200;
		
		
		
		
		for (String txt : EARTHLY_BRANCHES) {

			textLayout = new TextLayout(txt, font, frc);
			outline = textLayout.getOutline(null);
			bounds = outline.getBounds2D();
			tx = bounds.getCenterX();
			ty = radius - bounds.getHeight() + bounds.getCenterY();
			AffineTransform translateNumberTransform = AffineTransform.getTranslateInstance(-tx, -ty);

			
			
			Shape t = translateNumberTransform.createTransformedShape(outline);
//			t = rotateTransform.createTransformedShape(outline);
			
			
//			g2.fill(at.createTransformedShape(t));
			g2.fill(t);
			
			g2.transform(rotateTransform);
			
//			at.rotate(Math.PI / 6d);
		}

		g2.setTransform(savedTransform);
		
		
	}

	public static void main(String[] args) throws Exception {

		SwingUtilities.invokeLater(() -> {
			TextLayoutDemo frame = new TextLayoutDemo();
			frame.init();
		});

	}

}
