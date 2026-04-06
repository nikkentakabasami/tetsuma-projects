package ru.tet.javax.swing.aux;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.function.Function;

import javax.imageio.ImageIO;
import javax.swing.SwingConstants;

import ru.tet.javax.swing.images.DemoImages;

/**
 * Основа тестов для рисования графики
 * @author tetsuma
 *
 */
public abstract class JFrameForGraphicsTests extends JFrameForTests {

	protected MyCanvasComponent canvasComp;
	
	
	
	public void initWithCanvasComponent() {
		initWithControlPanelAbove();

		canvasComp = new MyCanvasComponent();
		canvasComp.setPainter(g2->{
			
			try {
				paintInCanvas(g2);				
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			
		});
		
		workPanel.setLayout(new BorderLayout());
		workPanel.add(canvasComp, SwingConstants.CENTER);
		
		
		
	}
	
	
	public abstract void paintInCanvas(Graphics2D g2) throws Exception;
	
	
	/**
	 * Загружает BufferedImage из картинки в classpath-е.
	 * Пример: loadResourceImage("/images/weather-cloud.png")
	 * @param resourceName
	 * @return
	 */
	public static BufferedImage loadResourceImage(String resourceName) {
		try {
	        URL url = JFrameForGraphicsTests.class.getResource(resourceName);
	        BufferedImage img =  ImageIO.read(url);
	        return img;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	
	/**
	 * Меняет цвет, делая его ярче/темнее
	 * @param c
	 * @param factor
	 * @return
	 */
	public static Color brighter(Color c, double factor) {
		
        Function<Integer, Integer> changeColorComp = (cc) -> {
        	int result = (int)(cc*factor);
        	result = Math.max(Math.min(result,255),0);
            return result;
        };        

        int r = changeColorComp.apply(c.getRed());
        int g = changeColorComp.apply(c.getGreen());
        int b = changeColorComp.apply(c.getBlue());
        
        return new Color(r,g,b,c.getAlpha());
		
	}	
	
	
	
	
	protected void paintGrid(Graphics2D g2) throws Exception {
		g2.setPaint(Color.GRAY);
		g2.setStroke(new BasicStroke(1.0f));
		
		Dimension size = canvasComp.getSize();
		
		for (int i = 0; i < size.width; i+=100) {
			g2.drawLine(i, 0, i, size.height);
		}
		
		for (int i = 0; i < size.height; i+=100) {
			g2.drawLine(0, i, size.width,i);
		}
	
	}
	
	
}
