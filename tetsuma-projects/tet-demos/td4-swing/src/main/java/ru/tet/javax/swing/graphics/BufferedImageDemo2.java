package ru.tet.javax.swing.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.SwingUtilities;

import ru.tet.javax.swing.aux.JFrameForGraphicsTests;

public class BufferedImageDemo2 extends JFrameForGraphicsTests {

	int selectedOption = 0;

	BufferedImage testImage1;

	@Override
	protected void doInit() throws Exception {
		initWithCanvasComponent();

		controlPanel.addComboBox(new String[] { "shapes", "lines", "-" }, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedOption = controlPanel.comboBox1.getSelectedIndex();
				canvasComp.repaint();
			}
		});

		testImage1 = createTestImage();

		Color c = new Color(testImage1.getRGB(25, 35));
		System.out.println(" Red: " + c.getRed() + "  Green: " + c.getGreen() + " Blue: " + c.getBlue());

//		canvasComp.setBackground(Color.WHITE);
//		canvasComp.setOpaque(true);

	}

	@Override
	public void paintInCanvas(Graphics2D g2) throws Exception {

		g2.drawImage(testImage1, 50, 20, this);

		makeImageDarker(testImage1);
		g2.drawImage(testImage1, 300, 20, this);
		
		makeImageGray(testImage1);
		g2.drawImage(testImage1, 550, 20, this);

		
//		g2.translate(300, 0);

	}

	private void makeImageGray(BufferedImage image) {

		int width = image.getWidth();
		int height = image.getHeight();

		for (int i = 0; i < height; i++) {

			for (int j = 0; j < width; j++) {
				Color c = new Color(image.getRGB(j, i));
				int red = (int) (c.getRed() * 0.299);
				int green = (int) (c.getGreen() * 0.587);
				int blue = (int) (c.getBlue() * 0.114);
				
				int gray = red + green + blue;

				Color newColor = new Color(gray,gray,gray);

				image.setRGB(j, i, newColor.getRGB());
			}
		}
	}

	private void makeImageDarker(BufferedImage image) {

		int width = image.getWidth();
		int height = image.getHeight();

		for (int i = 0; i < height; i++) {

			for (int j = 0; j < width; j++) {
				Color c = new Color(image.getRGB(j, i));
				image.setRGB(j, i, c.darker().getRGB());
			}
		}
	}
	
	
	private BufferedImage createTestImage() {

		int imageWidth = 200, imageHeight = 200;

		BufferedImage bufferedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);

		Graphics2D g2 = bufferedImage.createGraphics();

		// закрасим белым
		g2.setPaint(Color.WHITE);
		g2.fillRect(0, 0, imageWidth, imageHeight);

		Rectangle2D rect1 = new Rectangle2D.Double(20, 30, 150, 120);
		Ellipse2D ell1 = new Ellipse2D.Double(40, 40, 100, 120);

		GradientPaint gradPaint = new GradientPaint(0, 0, Color.RED, 0, 150, Color.WHITE);
		g2.setPaint(gradPaint);
		g2.fill(rect1);

		gradPaint = new GradientPaint(0, 0, Color.GREEN, 50, 100, Color.BLUE, true);
		g2.setPaint(gradPaint);
		g2.fill(ell1);

		g2.setColor(Color.BLACK);
		g2.draw(rect1);

		Font font = new Font("TimesRoman", Font.BOLD, 20);
		g2.setFont(font);
		g2.drawString("my image", 10, 20);

		g2.dispose();

		return bufferedImage;
	}

	public static void main(String[] args) throws Exception {

		SwingUtilities.invokeLater(() -> {
			BufferedImageDemo2 frame = new BufferedImageDemo2();
			frame.init();
		});

	}

}
