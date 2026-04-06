package ru.tet.javax.swing.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.RadialGradientPaint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.SwingUtilities;

import ru.tet.javax.swing.aux.JFrameForGraphicsTests;

public class GraphicsDemo1 extends JFrameForGraphicsTests {

	int selectedOption = 0;

	BasicStroke stroke = new BasicStroke(2.0f);
	BasicStroke wideStroke = new BasicStroke(6.0f);
	
	@Override
	protected void doInit() throws Exception {
		initWithCanvasComponent();

		
		controlPanel.addComboBox(new String[] { "shapes", "misc", "Lines, Path2D" , "paints", "all shapes"},
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						selectedOption = controlPanel.comboBox1.getSelectedIndex();
						canvasComp.repaint();
					}
				});

		controlPanel.comboBox1.setSelectedIndex(4);
		
		controlPanel.addCheckbox("draw tile border", e->{
			canvasComp.setDrawBorder(controlPanel.checkbox1.isSelected());
			canvasComp.repaint();
		});
		
		controlPanel.addCheckbox("ANTIALIASING", e->{
			canvasComp.repaint();
		});
		
		
//		canvasComp.setBackground(Color.WHITE);
//		canvasComp.setOpaque(true);
		
	}

	

	@Override
	public void paintInCanvas(Graphics2D g2) throws Exception {
		
		if (controlPanel.checkbox2.isSelected()) {
		
	        // Установка антиалиасинга для плавности
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
		}
		
		
		//shapes
		if (selectedOption == 0) {
	        paintShapes(g2);
		} else if (selectedOption == 1) {
			paintMisc(g2);
		} else if (selectedOption == 2) {
			paintLines(g2);
		} else if (selectedOption == 3) {
			paintGradients(g2);
		} else if (selectedOption == 4) {
			paintAllShapes(g2);
		}

	}	
	
	
	
	


	private void paintAllShapes(Graphics2D g2) {

		
		Rectangle2D rect1 = new Rectangle2D.Double(0, 0, 150, 200);
		RoundRectangle2D roundRect1 = new RoundRectangle2D.Double(0, 0, 150, 200, 10, 10);
		Ellipse2D ell1 = new Ellipse2D.Double(0, 0, 100, 200);
		Ellipse2D ell2 = new Ellipse2D.Double(0, 50, 200, 100);
		
        Line2D line1 = new Line2D.Double(10, 100, 150,120);
        Point2D point1 = new Point2D.Double(100, 100);
        
		Arc2D arc1 = new Arc2D.Double(0, 0, 150, 200, 90,135, Arc2D.OPEN);
		Arc2D arc2 = new Arc2D.Double(0, 0, 150, 200, 90,135, Arc2D.CHORD);
		Arc2D arc3 = new Arc2D.Double(0, 0, 150, 200, 90,135, Arc2D.PIE);
		
		Path2D path1 = new Path2D.Double();
		path1.moveTo(50, 50);
		path1.lineTo(150, 50);
		path1.quadTo(200, 100, 150, 200);
		path1.curveTo(120, 250, 80, 150, 50, 200);
		path1.closePath();
		
        // Создаем квадратичную кривую
        QuadCurve2D quadCurve1 = new QuadCurve2D.Double(
                0, 100,   // точка начала (x1, y1)
                100, 0,   // контрольная точка (cx, cy)
                200, 100   // точка конца (x2, y2)
        );

		CubicCurve2D cubicCurve = new CubicCurve2D.Double(
                0, 100,   // начальная точка (x1, y1)
                50, 0,   // первая контрольная точка (cx1, cy1)
                150, 200,  // вторая контрольная точка (cx2, cy2)
                200, 100   // конечная точка (x2, y2)
        );
		
        Area area1 = new Area(rect1);
        Area areaEllipse = new Area(ell2);
        area1.add(areaEllipse); // объединение
		
        
		
		Font f = new Font("SansSerif", Font.BOLD, 18);
		g2.setFont(f);
		
		//закрасим белым
		Dimension canvasSize = canvasComp.getSize();
		g2.setPaint(Color.WHITE);
		g2.fillRect(0, 0, canvasSize.width, canvasSize.height);
		
		g2.setPaint(Color.BLUE);
        g2.setStroke(stroke);
		
		
		canvasComp.initTilesMode(200, 200, g2);
		g2.draw(rect1);
        g2.drawString("Rectangle2D", 0, 0);

		canvasComp.translateToNextTile(g2);
        g2.draw(roundRect1);
        g2.drawString("RoundRectangle2D", 0, 0);

		canvasComp.translateToNextTile(g2);
        g2.draw(ell1);
        g2.drawString("Ellipse2D", 0, 0);
        
		canvasComp.translateToNextTile(g2);
        g2.draw(line1);
        g2.drawString("Line2D", 0, 0);

//Нельзя 
//		canvasComp.translateToNextTile(g2);
//        g2.draw(point1);
//        g2.drawString("Point2D", 0, 0);
        
		canvasComp.translateToNextTile(g2);
        g2.draw(arc1);
        g2.drawString("Arc2D OPEN", 0, 0);

		canvasComp.translateToNextTile(g2);
        g2.draw(arc2);
        g2.drawString("Arc2D CHORD", 0, 0);

		canvasComp.translateToNextTile(g2);
        g2.draw(arc3);
        g2.drawString("Arc2D PIE", 0, 0);
        
		canvasComp.translateToNextTile(g2);
        g2.draw(path1);
        g2.drawString("Path2D", 0, 0);
		
		canvasComp.translateToNextTile(g2);
        g2.draw(quadCurve1);
        g2.drawString("QuadCurve2D", 0, 0);
		
		canvasComp.translateToNextTile(g2);
        g2.draw(cubicCurve);
        g2.drawString("CubicCurve2D", 0, 0);
		
		canvasComp.translateToNextTile(g2);
        g2.draw(area1);
        g2.drawString("Area", 0, 0);
        
		canvasComp.finishTilesMode(g2);
		
	}
	
	
	
	
	
	
	

	private void paintShapes(Graphics2D g2) {
		
		Dimension canvasSize = canvasComp.getSize();
		
		Font f = new Font("SansSerif", Font.BOLD, 18);
		g2.setFont(f);
		
		//закрасим белым
		g2.setPaint(Color.WHITE);
		g2.fillRect(0, 0, canvasSize.width, canvasSize.height);


		float dash1[] = {10.0f};
	    BasicStroke dashedStroke = new BasicStroke(1.0f, 
	                                                      BasicStroke.CAP_BUTT, 
	                                                      BasicStroke.JOIN_MITER, 
	                                                      10.0f, dash1, 0.0f);

	    
	    
	    //Rectangle2D
		canvasComp.initTilesMode(200, 200, g2);
		
		g2.setPaint(Color.GREEN);
        g2.setStroke(stroke);
		g2.draw(new Rectangle2D.Double(0, 0, 150, 200));
		
        g2.setStroke(wideStroke);
		g2.draw(new Rectangle2D.Double(10, 10, 130, 180));
		
		drawString(g2, 20, 20, "Rectangle2D");

		
		//RoundRectangle2D
		canvasComp.translateToNextTile(g2);
		g2.setPaint(Color.ORANGE);
        g2.setStroke(dashedStroke);
        g2.draw(new RoundRectangle2D.Double(0, 0, 150, 200, 10, 10));
		drawString(g2, 0, 20, "RoundRectangle2D");

		

		//Arc2D
		canvasComp.translateToNextTile(g2);
		g2.setPaint(Color.RED);
        g2.setStroke(wideStroke);
        g2.draw(new Arc2D.Double(0, 0, 150, 200, 90, 
                                 135, Arc2D.OPEN));
		drawString(g2, 20, 20, "Arc2D OPEN");
		
		canvasComp.translateToNextTile(g2);
        g2.draw(new Arc2D.Double(0,0,150,200, 0, 
                220, Arc2D.CHORD));
        drawString(g2, 20, 20, "Arc2D CHORD");

		canvasComp.translateToNextTile(g2);
        g2.draw(new Arc2D.Double(0,0,150,200, 0, 
                220, Arc2D.PIE));
        drawString(g2, 20, 20, "Arc2D PIE");

        //Ellipse2D
		canvasComp.translateToNextTile(g2);
        g2.setStroke(stroke);
        g2.draw(new Ellipse2D.Double(0, 0, 100, 200));
        drawString(g2, 20, 20, "Ellipse2D");		
        
        //Path2D
		canvasComp.translateToNextTile(g2);
		g2.setPaint(Color.BLUE);
        int x1Points[] = {100, 200, 0, 150};
        int y1Points[] = {0, 50, 100, 150};
        GeneralPath genPath = new GeneralPath(GeneralPath.WIND_EVEN_ODD,
                                              x1Points.length);
        genPath.moveTo(x1Points[0], y1Points[0]);
        for ( int index = 1; index < x1Points.length; index++ ) {
            genPath.lineTo(x1Points[index], y1Points[index]);
        };
        genPath.closePath();
        g2.draw(genPath);
        g2.drawString("Path2D", 0, 0);
        
		canvasComp.translateToNextTile(g2);
        genPath.reset();
        genPath.moveTo(x1Points[0], y1Points[0]);
        for ( int index = 1; index < x1Points.length; index++ ) {
            genPath.lineTo(x1Points[index], y1Points[index]);
        };
        g2.draw(genPath);
        g2.drawString("Path2D open", 0, 0);
        
        //GeneralPath
		canvasComp.translateToNextTile(g2);
		g2.setPaint(Color.BLUE);
        genPath = new GeneralPath(GeneralPath.WIND_EVEN_ODD,
                                              x1Points.length);
        genPath.moveTo(x1Points[0], y1Points[0]);
        for ( int index = 1; index < x1Points.length; index++ ) {
            genPath.lineTo(x1Points[index], y1Points[index]);
        };
        genPath.closePath();
        
        
		g2.setPaint(Color.ORANGE);
        g2.fill(genPath);
		g2.setPaint(Color.BLACK);
        g2.draw(genPath);        
        g2.drawString("Path2D filled", 0, 0);
                
        
        
        
        
		//Rectangle2D gradient fill
		canvasComp.translateToNextTile(g2);
		
        Rectangle2D rect = new Rectangle2D.Double(0,0,150,200);
		
        GradientPaint gradPaint = new GradientPaint(-50,-50,Color.RED,0, 150,Color.WHITE);
        g2.setPaint(gradPaint);
		g2.fill(rect);
        
        g2.setColor(Color.BLACK);
		g2.draw(rect);
        g2.drawString("GradientPaint fill", 0, 0);
		
		
        //Ellipse2D gradient fill
		canvasComp.translateToNextTile(g2);
		
		Ellipse2D ell = new Ellipse2D.Double(0, 0, 100, 200); 
		
		gradPaint = new GradientPaint(50,50,Color.ORANGE,200, 200,Color.GREEN);
        g2.setPaint(gradPaint);
        g2.fill(ell);
		
        g2.setStroke(wideStroke);
        g2.setColor(Color.BLUE);
		g2.draw(ell);
        g2.drawString("GradientPaint fill 2", 0, 0);
        

        
        //QuadCurve2D
		canvasComp.translateToNextTile(g2);

        // Создаем квадратичную кривую
        QuadCurve2D quadCurve = new QuadCurve2D.Double(
                0, 100,   // точка начала (x1, y1)
                100, 0,   // контрольная точка (cx, cy)
                200, 100   // точка конца (x2, y2)
        );

        g2.setColor(Color.BLUE);
        g2.draw(quadCurve);
        g2.drawString("QuadCurve2D", 0, 0);
        
        
        //QuadCurve2D
		canvasComp.translateToNextTile(g2);

		CubicCurve2D cubicCurve = new CubicCurve2D.Double(
                0, 100,   // начальная точка (x1, y1)
                50, 0,   // первая контрольная точка (cx1, cy1)
                150, 200,  // вторая контрольная точка (cx2, cy2)
                200, 100   // конечная точка (x2, y2)
        );
        g2.draw(cubicCurve);
        g2.drawString("CubicCurve2D", 0, 0);        
        
		canvasComp.translateToNextTile(g2);

        //Area - фигура, в которой можно объединить несколько других фигур
        rect = new Rectangle2D.Double(20, 20, 200, 150);
        Ellipse2D ellipse = new Ellipse2D.Double(50, 50, 150, 200);

        Area areaRect = new Area(rect);
        Area areaEllipse = new Area(ellipse);
        areaRect.add(areaEllipse); // объединение

        g2.fill(areaRect);
        g2.setColor(Color.BLACK);
        g2.draw(areaRect);		
        g2.drawString("Area", 0, 0);        
		
		
        
		canvasComp.finishTilesMode(g2);
		
		
	}
	


	private void paintGradients(Graphics2D g2) {
		
		Dimension canvasSize = canvasComp.getSize();

		//закрасим белым
		g2.setPaint(Color.WHITE);
		g2.fillRect(0, 0, canvasSize.width, canvasSize.height);		
		
        Rectangle2D rect1 = new Rectangle2D.Double(0,0,150,200);
        

	    //GradientPaint
		canvasComp.initTilesMode(200, 200, g2);
		
        GradientPaint gradPaint = new GradientPaint(-50,-50,Color.RED,0, 150,Color.WHITE);
        g2.setPaint(gradPaint);
		g2.fill(rect1);
        
        g2.setColor(Color.BLACK);
		g2.draw(rect1);
        g2.drawString("GradientPaint fill", 0, 0);
		
        
	    //GradientPaint
		canvasComp.translateToNextTile(g2);
		
		gradPaint = new GradientPaint(0,0,Color.GREEN,50, 100,Color.BLUE,true);
        g2.setPaint(gradPaint);
		g2.fill(rect1);
        
        g2.setColor(Color.BLACK);
		g2.draw(rect1);
        g2.drawString("GradientPaint cyclic", 0, 0);
        
        
        
		canvasComp.translateToNextTile(g2);
		
        Point2D start = new Point2D.Float(0, 0);
        Point2D end = new Point2D.Float(150, 150);
        float[] dist = {0.0f, 0.2f, 1.0f};
        Color[] colors = {Color.RED, Color.WHITE, Color.BLUE};
        LinearGradientPaint lgp =
            new LinearGradientPaint(start, end, dist, colors);        
        g2.setPaint(lgp);
		g2.fill(rect1);
        
        g2.setColor(Color.BLACK);
		g2.draw(rect1);
        g2.drawString("LinearGradientPaint", 0, 0);
		
		
		canvasComp.translateToNextTile(g2);
		
		Point2D center = new Point2D.Float(100, 100);
		float radius = 100;
		float[] dist2 = { 0.0f, 0.2f, 1.0f };
		Color[] colors2 = { Color.RED, Color.WHITE, Color.BLUE };
		RadialGradientPaint rgp = new RadialGradientPaint(center, radius, dist2, colors2);		
        g2.setPaint(rgp);
		g2.fill(rect1);
		
        g2.setColor(Color.BLACK);
		g2.draw(rect1);
        g2.drawString("RadialGradientPaint", 0, 0);
        
        
        
        
        
        
        
        
		canvasComp.translateToNextTile(g2);
        
		BufferedImage cloudsImage = loadResourceImage("/images/weather-cloud.png");
        Rectangle anchorRect = new Rectangle(0, 0, cloudsImage.getWidth(), cloudsImage.getHeight());
        TexturePaint texturePaint = new TexturePaint(cloudsImage, anchorRect);
        g2.setPaint(texturePaint);
        
		g2.fill(rect1);
        
        g2.setColor(Color.BLACK);
		g2.draw(rect1);
        g2.drawString("TexturePaint fill", 0, 0);
		
		
		canvasComp.translateToNextTile(g2);
        
		anchorRect = new Rectangle(0, 0, 500, 500);
        texturePaint = new TexturePaint(cloudsImage, anchorRect);
        g2.setPaint(texturePaint);
        
		g2.fill(rect1);
        
        g2.setColor(Color.BLACK);
        g2.drawString("TexturePaint expanded", 0, 0);
        
		
		canvasComp.finishTilesMode(g2);
		
		
		
	}	
	
	
	private void paintMisc(Graphics2D g2) {

		Font f = new Font("SansSerif", Font.BOLD, 14);
		g2.setFont(f);

		
		canvasComp.initTilesMode(200, 200, g2);
		
		//fill3DRect raised
		g2.setPaint(Color.lightGray);
		g2.fill3DRect(0, 0, 200, 200, true);
//		g2.draw3DRect(0, 0, 200, 200, true);
		g2.setColor(Color.BLACK);
        g2.drawString("fill3DRect", 20, 20);

		
		
		canvasComp.translateToNextTile(g2);
//	    g2.fill3DRect(0, 0, 100, 50, true);
	    g2.setColor(Color.BLUE);
	    g2.draw3DRect(10, 10, 100, 50, true);
	    
	    g2.setColor(Color.RED);
	    g2.draw3DRect(0, 100, 100, 50, false);		
	    
	    g2.setColor(Color.BLACK);
        g2.drawString("draw3DRect", 0, 0);
		
        g2.drawString("raised", 20, 40);
        g2.drawString("not raised", 20, 120);

        
		canvasComp.translateToNextTile(g2);
//	    g2.fill3DRect(0, 0, 100, 50, true);
	    g2.setColor(Color.BLUE);
	    g2.fill3DRect(10, 10, 100, 50, true);
	    
	    g2.setColor(Color.RED);
	    g2.fill3DRect(0, 100, 100, 50, false);		
	    
	    g2.setColor(Color.BLACK);
        g2.drawString("fill3DRect", 0, 0);
		
        g2.drawString("raised", 20, 40);
        g2.drawString("not raised", 20, 120);
        
        
		
        //Line2D
		canvasComp.translateToNextTile(g2);
		g2.setColor(Color.BLACK);
        g2.draw(new Line2D.Double(10, 10, 50, 200));
        g2.drawString("Line2D", 0, 0);

        
        //Ellipse2D
		canvasComp.translateToNextTile(g2);
		
		Ellipse2D ell = new Ellipse2D.Double(0, 0, 150, 200);
		
		g2.setColor(Color.RED);
		g2.setClip(0,50,300,100);
        g2.fill(ell);

		g2.setColor(Color.BLACK);
		g2.setClip(null);

		g2.draw(ell);
		g2.drawString("clip test",0,0);		
        
        
        
        
		canvasComp.finishTilesMode(g2);		
		
	}
	
	
	private void paintLines(Graphics2D g2) {
		
		Path2D testPath = createTestPath();
		
		g2.setColor(Color.BLUE);
		

		
		
		
		
		//stroke default : CAP_SQUARE, JOIN_MITER
		canvasComp.initTilesMode(200, 300, g2);
		
		BasicStroke stroke = new BasicStroke(10.0f);
        g2.setStroke(stroke);
        paintTestLines(g2, testPath);
        
        g2.drawString("default: CAP_SQUARE, JOIN_MITER",0,240);		
        
		
		canvasComp.translateToNextTile(g2);
		
		stroke = new BasicStroke(10.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
        g2.setStroke(stroke);
        paintTestLines(g2, testPath);
        g2.drawString("CAP_BUTT, JOIN_BEVEL",0,240);		

        
		canvasComp.translateToNextTile(g2);
		
		stroke = new BasicStroke(10.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        g2.setStroke(stroke);
        paintTestLines(g2, testPath);
        g2.drawString("CAP_ROUND, JOIN_ROUND",0,240);		
        

		canvasComp.translateToNextTile(g2);
		
		float dash1[] = {10.0f, 20.0f};
		stroke = new BasicStroke(10.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL, 10.0f, dash1, 0.0f);
        g2.setStroke(stroke);
        paintTestLines(g2, testPath);
        g2.drawString("dash line rounded",0,240);		
        
		canvasComp.finishTilesMode(g2);		
        
        
//        iteratePath(path);
        
		
	}

	
	private void paintTestLines(Graphics2D g2, Path2D testPath) {
        g2.draw(testPath);
        g2.draw(new Line2D.Double(10, 100, 150,120));        
        g2.draw(new Line2D.Double(10, 150, 150,80));        
	}
	
	private Path2D createTestPath() {
        Path2D path = new Path2D.Double();
        path.moveTo(0, 0);
        path.lineTo(100, 0);
        path.lineTo(50, 50);
        path.lineTo(100, 80);
        
        path.quadTo(200, 100, 150, 200);
        path.curveTo(120, 250, 80, 150, 50, 200);
        path.closePath();
        return path;
	}
	
	
	private void iteratePath(Path2D path) {

		PathIterator pi = path.getPathIterator(null);
		double[] coords = new double[6];

		while (!pi.isDone()) {
			int type = pi.currentSegment(coords);
			switch (type) {
			case PathIterator.SEG_MOVETO:
				System.out.printf("Move to: (%.2f, %.2f)%n", coords[0], coords[1]);
				break;
			case PathIterator.SEG_LINETO:
				System.out.printf("Line to: (%.2f, %.2f)%n", coords[0], coords[1]);
				break;
			case PathIterator.SEG_QUADTO:
				System.out.printf("Quad to: control (%.2f, %.2f), end (%.2f, %.2f)%n", coords[0], coords[1], coords[2],
						coords[3]);
				break;
			case PathIterator.SEG_CUBICTO:
				System.out.printf("Cubic to: control1 (%.2f, %.2f), control2 (%.2f, %.2f), end (%.2f, %.2f)%n",
						coords[0], coords[1], coords[2], coords[3], coords[4], coords[5]);
				break;
			case PathIterator.SEG_CLOSE:
				System.out.println("Close path");
				break;
			}
			pi.next();
		}

	}
		
	
	private void drawString(Graphics2D g2, int x, int y, String message) {
		FontRenderContext context = g2.getFontRenderContext();
		Rectangle2D bounds = g2.getFont().getStringBounds(message, context);
		y = y-(int)bounds.getY();
		g2.drawString(message, x, y);
	}
	

	public static void main(String[] args) throws Exception {

		SwingUtilities.invokeLater(() -> {
			GraphicsDemo1 frame = new GraphicsDemo1();
			frame.init();
		});

	}

}
