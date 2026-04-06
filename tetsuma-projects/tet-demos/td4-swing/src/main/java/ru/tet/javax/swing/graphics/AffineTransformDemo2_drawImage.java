package ru.tet.javax.swing.graphics;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.SwingUtilities;

import ru.tet.javax.swing.aux.JFrameForGraphicsTests;

public class AffineTransformDemo2_drawImage extends JFrameForGraphicsTests {

	@Override
	protected void doInit() throws Exception {
		initWithCanvasComponent();
		
	}

	
	
	
	@Override
	public void paintInCanvas(Graphics2D g2) throws Exception {

		BufferedImage image = demoImages.missingImage;
		
	      g2.setColor(getBackground());
	      g2.fillRect(0, 0, getWidth(), getHeight());
	      int w = image.getWidth();
	      int h = image.getHeight();

	      //рисуем картинку
          g2.drawImage(image, 10, 10, this);
	      
	      //VERTICAL flip
          AffineTransform at1 = AffineTransform.getScaleInstance(1d, -1d);
          at1.translate(350, -10-h);
          // AffineTransform at1 = new AffineTransform(1d, 0d, 0d, -1d, 0d, h);
          Graphics2D g2t = (Graphics2D) g2.create();
          g2t.drawImage(image, at1, this);
          g2t.dispose();

	      //HORIZONTAL flip
          at1 = AffineTransform.getScaleInstance(-1d, 1d);
          at1.translate(-700-w, 10);
          // AffineTransform at1 = new AffineTransform(1d, 0d, 0d, -1d, 0d, h);
          g2t = (Graphics2D) g2.create();
          g2t.drawImage(image, at1, this);
          g2t.dispose();
		
	}

	public static void main(String[] args) throws Exception {

		SwingUtilities.invokeLater(() -> {
			AffineTransformDemo2_drawImage frame = new AffineTransformDemo2_drawImage();
			frame.init();
		});

	}

}
