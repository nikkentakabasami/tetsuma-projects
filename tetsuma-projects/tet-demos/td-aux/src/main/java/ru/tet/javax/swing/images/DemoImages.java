package ru.tet.javax.swing.images;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.MediaTracker;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Набор картинок для создания демок.
 * 
 * @author tetsuma
 *
 */
public class DemoImages {

	//загружены по умолчанию
	public BufferedImage testImage1;
	public BufferedImage missingImage;
	public MissingIcon missingIcon;

	
	public BufferedImage cloudsImage;
	public BufferedImage snowImage;
	
	public ImageIcon iconInfo;
	public ImageIcon iconTick;
	public ImageIcon iconUp;
	
	public ImageIcon iconDelete;
	public ImageIcon iconForward;
	public ImageIcon iconEnet;
	
	
	public DemoImages() {
		testImage1 = createTestBufferedImage1();
		missingIcon = new MissingIcon();
		missingImage = makeMissingImage();
	}
	
	
	public void loadResourceImagesAndIcons(Component comp) {

		try {
			ClassLoader cl = Thread.currentThread().getContextClassLoader();
//			ClassLoader cl = TestImages.class.getClassLoader();
			
			cloudsImage = ImageIO.read(cl.getResource("images/weather-cloud.png"));
			snowImage = ImageIO.read(cl.getResource("images/weather-snow.png"));
			
			iconInfo = new ImageIcon(cl.getResource("images/info.gif"));
			iconTick = new ImageIcon(cl.getResource("images/tick.png"));
			iconUp = new ImageIcon(cl.getResource("images/Up24.gif"));

			iconDelete = new ImageIcon(cl.getResource("images/delete.png"));
			iconForward = new ImageIcon(cl.getResource("images/Forward24.gif"));
			iconEnet = new ImageIcon(cl.getResource("images/headericon16.png"));
			

			//ждём загрузки
			MediaTracker mediaTracker = new MediaTracker(comp);
			mediaTracker.addImage(cloudsImage, 1);
			mediaTracker.addImage(snowImage, 1);
			mediaTracker.addImage(iconInfo.getImage(), 1);
			mediaTracker.addImage(iconTick.getImage(), 1);
			mediaTracker.addImage(iconUp.getImage(), 1);
			mediaTracker.addImage(iconDelete.getImage(), 1);
			mediaTracker.addImage(iconForward.getImage(), 1);
			mediaTracker.addImage(iconEnet.getImage(), 1);
			mediaTracker.waitForID(1);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	private static BufferedImage createTestBufferedImage1() {

		int width = 200, height = 200;
		String message = "my test image";

		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = bi.createGraphics();

		RoundRectangle2D roundRect1 = new RoundRectangle2D.Double(10, 10, 180, 180, 10, 10);

		GradientPaint gradPaint = new GradientPaint(0, 0, Color.RED, 0, 200, Color.WHITE);
		g2.setPaint(gradPaint);
		g2.fill(roundRect1);

		g2.setPaint(Color.BLUE);
		g2.setStroke(new BasicStroke(3.0f));
		g2.draw(roundRect1);

		Font font = new Font("TimesRoman", Font.BOLD, 20);
		g2.setFont(font);
		FontMetrics fontMetrics = g2.getFontMetrics();
		int stringWidth = fontMetrics.stringWidth(message);
		int stringHeight = fontMetrics.getAscent();
		g2.setPaint(Color.black);
		g2.drawString(message, (width - stringWidth) / 2, height / 2 + stringHeight / 4);
		g2.dispose();
		return bi;
	}

	  private BufferedImage makeMissingImage() {
		    int w = missingIcon.getIconWidth();
		    int h = missingIcon.getIconHeight();
		    BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		    Graphics2D g2 = bi.createGraphics();
		    missingIcon.paintIcon(null, g2, 0, 0);
		    g2.dispose();
		    return bi;
		  }	
	
}
