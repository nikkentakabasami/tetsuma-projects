package ru.tet.javax.swing.graphics;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.SwingUtilities;

import ru.tet.javax.swing.aux.JFrameForGraphicsTests;

public class AlphaCompositeDemo1 extends JFrameForGraphicsTests {

	float currentAlpha;
	int currentRule;

	@Override
	protected void doInit() throws Exception {
		initWithCanvasComponent();

		controlPanel.addTitleLabel("alpha");
		controlPanel.addComboBox(new String[] { "0.0", "0.25", "0.50", "0.75", "1.0" }, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentAlpha = Float.valueOf((String)controlPanel.comboBox1.getSelectedItem());
//				currentAlpha = option * 0.25;
				canvasComp.repaint();
			}
		});
		controlPanel.comboBox1.setSelectedIndex(3);

		controlPanel.addTitleLabel("rule");
		controlPanel.addComboBox(
				new String[] { "SRC", "DST_IN", "DST_OUT", "DST_OVER", "SRC_IN", "SRC_OVER", "SRC_OUT", "CLEAR" },
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						int option = controlPanel.comboBox2.getSelectedIndex();
						currentRule = getRuleForOption(option);
						canvasComp.repaint();
					}
				});
		controlPanel.comboBox2.setSelectedIndex(5);
		
		
	}

	public int getRuleForOption(int rule) {
		int alphaComp = 0;
		switch (rule) {
		case 0:
			alphaComp = AlphaComposite.SRC;
			break;
		case 1:
			alphaComp = AlphaComposite.DST_IN;
			break;
		case 2:
			alphaComp = AlphaComposite.DST_OUT;
			break;
		case 3:
			alphaComp = AlphaComposite.DST_OVER;
			break;
		case 4:
			alphaComp = AlphaComposite.SRC_IN;
			break;
		case 5:
			alphaComp = AlphaComposite.SRC_OVER;
			break;
		case 6:
			alphaComp = AlphaComposite.SRC_OUT;
			break;
		case 7:
			alphaComp = AlphaComposite.CLEAR;
			break;
		}
		return alphaComp;
	}

	@Override
	public void paintInCanvas(Graphics2D g2) throws Exception {

		Dimension d = canvasComp.getSize();
		g2.setColor(Color.white);
		g2.fillRect(0, 0, d.width, d.height);
		
		AlphaComposite ac = AlphaComposite.getInstance(currentRule, currentAlpha);
		
		Color color1 = new Color(0.0f, 0.0f, 1.0f, 1.0f);
		Color color2 = new Color(1.0f, 0.0f, 0.0f, 1.0f);
		Rectangle2D shape1 = new Rectangle2D.Double(0, 0, 200, 200);
		Ellipse2D shape2 = new Ellipse2D.Double(100, 100, 150, 100);
		
        //paint in canvas
        Composite origComposite = g2.getComposite();
        
        g2.setColor(color1);
        g2.fill(shape1);
		g2.setColor(color2);
		g2.setComposite(ac);
		g2.fill(shape2);
        
        g2.setComposite(origComposite);
        

        //paint in buffered image.
        
		BufferedImage buffImg = new BufferedImage(300, 300, BufferedImage.TYPE_INT_ARGB);
		Graphics2D buffg2 = buffImg.createGraphics();

		buffg2.setColor(color1);
		buffg2.fill(shape1);
		buffg2.setColor(color2);
		buffg2.setComposite(ac);
		buffg2.fill(shape2);
        g2.drawImage(buffImg, null, 400, 0);
        
        
        
        
		
	}

	public static void main(String[] args) throws Exception {

		SwingUtilities.invokeLater(() -> {
			AlphaCompositeDemo1 frame = new AlphaCompositeDemo1();
			frame.init();
		});

	}

}
