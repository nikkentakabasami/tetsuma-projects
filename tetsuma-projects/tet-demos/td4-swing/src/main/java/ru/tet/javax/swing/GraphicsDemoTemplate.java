package ru.tet.javax.swing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.SwingUtilities;

import ru.tet.javax.swing.aux.JFrameForGraphicsTests;

//Шаблон для создания демок для работы с графикой
public class GraphicsDemoTemplate extends JFrameForGraphicsTests {

	@Override
	protected void doInit() throws Exception {
		initWithCanvasComponent();

		
	}


	@Override
	public void paintInCanvas(Graphics2D g2) throws Exception {

		Color color1 = new Color(0.0f, 0.0f, 1.0f, 1.0f);
		Color color2 = new Color(1.0f, 0.0f, 0.0f, 1.0f);
		Rectangle2D shape1 = new Rectangle2D.Double(0, 0, 200, 200);
		Ellipse2D shape2 = new Ellipse2D.Double(100, 100, 150, 100);
		
        
        g2.setColor(color1);
        g2.fill(shape1);
		g2.setColor(color2);
		g2.fill(shape2);
        
        
        
		
	}

	public static void main(String[] args) throws Exception {

		SwingUtilities.invokeLater(() -> {
			GraphicsDemoTemplate frame = new GraphicsDemoTemplate();
			frame.init();
		});

	}

}
