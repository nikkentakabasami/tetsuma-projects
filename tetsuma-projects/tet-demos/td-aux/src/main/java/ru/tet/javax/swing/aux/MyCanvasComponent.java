package ru.tet.javax.swing.aux;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

/**
 * Кастомный компонент для рисования.
 * 
 * @author tetsuma
 *
 */
public class MyCanvasComponent extends JComponent {

	public static interface Painter {
		public void paint(Graphics2D g2);
	}

	Painter painter;

	int tileX, tileY, tileWidth, tileHeight, tilePadding;
	AffineTransform at;
	AffineTransform origAt;

	boolean drawBorder = false;
	boolean tileMode = false;

	public void setDrawBorder(boolean drawBorder) {
		this.drawBorder = drawBorder;
	}

	public void initTilesMode(int tileWidth, int tileHeight, Graphics2D g2) {
		this.tileMode = true;
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		
		tilePadding = 20;
		tileX = tilePadding;
		tileY = tilePadding;
		
    	origAt = g2.getTransform();
    	
	    at = new AffineTransform();
        at.translate(tilePadding, tilePadding);
        g2.transform(at);
        
        if (drawBorder) {
        	drawCurrentTileBorder(g2);
        }
	}
	
	public void translateToNextTile(Graphics2D g2) {
		at.setToIdentity();
		
		double canvasWidth = getSize().getWidth();

		int xShift = tileWidth+tilePadding;
		tileX+= xShift;
		
		
		if (canvasWidth>(tileX+tileWidth+tilePadding)) {
			at.translate(xShift, 0);
		} else {
	        g2.setTransform(origAt);
			tileX = tilePadding;
	        tileY+=tileHeight+tilePadding;
			at.translate(tileX, tileY);
		}
		
        g2.transform(at);
		
        if (drawBorder) {
        	drawCurrentTileBorder(g2);
        }
        
	}
	
	protected void drawCurrentTileBorder(Graphics2D g2) {
		
		Stroke cs = g2.getStroke();
		Color cc = g2.getColor();
		
        g2.setStroke(new BasicStroke(1.0f));
        g2.setColor(Color.gray);
		g2.draw(new Rectangle2D.Double(0, 0, tileWidth, tileHeight));
        
        g2.setStroke(cs);
        g2.setColor(cc);
        
	}
	
	public void finishTilesMode(Graphics2D g2) {
		this.tileMode = false;
        g2.setTransform(origAt);
	}
	
	
	public MyCanvasComponent() {
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}

	public void setPainter(Painter painter) {
		this.painter = painter;
	}

//    public void paint(Graphics g) {
//    }

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		if (painter!=null) {
			painter.paint(g2);
		}
	}

	public Dimension getPreferredSize() {
		return new Dimension(400, 200);
	}

}
