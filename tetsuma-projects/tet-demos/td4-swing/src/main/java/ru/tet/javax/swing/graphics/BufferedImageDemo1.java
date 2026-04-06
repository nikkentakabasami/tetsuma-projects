package ru.tet.javax.swing.graphics;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

import ru.tet.javax.swing.aux.JFrameForGraphicsTests;
import ru.tet.javax.swing.images.DemoImages;

public class BufferedImageDemo1 extends JFrameForGraphicsTests {

	Image toolkitImage1;
	Image toolkitImage2;

	MediaTracker mediaTracker;

	int selectedOption = 0;

	@Override
	protected void doInit() throws Exception {
		initWithCanvasComponent();

//		controlPanel.setLayout(new BoxLayout(controlPanel,BoxLayout.Y_AXIS));

		demoImages.loadResourceImagesAndIcons(this);

		String writerNames[] = ImageIO.getWriterFormatNames();
		String names = Arrays.stream(writerNames).collect(Collectors.joining(","));
		controlPanel.addDebugLabel("supported formats:" + names);

		controlPanel.addComboBox(new String[] { "draw test images", "draw clouds", "draw clouds and snow" },
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						selectedOption = controlPanel.comboBox1.getSelectedIndex();
						canvasComp.repaint();
					}
				});

//		cloudsImage = loadResourceImage("/images/weather-cloud.png");
//		snowImage = loadResourceImage("/images/weather-snow.png");
//		testImage = createTestBufferedImage();
		
		
		//ToolkitImage - Загрузка изображений через Toolkit
		toolkitImage1 = Toolkit.getDefaultToolkit().getImage("images/about.gif");

		URL url = JFrameForGraphicsTests.class.getResource("/images/weather-cloud.png");
		toolkitImage2 = Toolkit.getDefaultToolkit().getImage(url);

		// подождать загрузки изображений
		mediaTracker = new MediaTracker(this);
		mediaTracker.addImage(toolkitImage1, 1);
		mediaTracker.addImage(toolkitImage2, 1);
		try {
			mediaTracker.waitForID(1);
//			mediaTracker.waitForID(2);
		} catch (InterruptedException e) {
			return;
		}
		
		if (mediaTracker.isErrorID(0)) {
            System.out.println("Ошибка при загрузке изображения");
        }		
		

		controlPanel.addButton("save test image to files", e -> {

			try {
				boolean result = ImageIO.write(demoImages.testImage1, "PNG", getGenImgFile("test1.png"));
				System.out.println(result);
				result = ImageIO.write(demoImages.testImage1, "gif", getGenImgFile("test1.gif"));
				System.out.println(result);

				// jpeg и bmp - не поддерживаются. Нужны доп библиотеки-патчи вроде JDeli
				result = ImageIO.write(demoImages.testImage1, "JPG", getGenImgFile("test1.jpg"));
				System.out.println(result);
				result = ImageIO.write(demoImages.testImage1, "BMP", getGenImgFile("test1.bmp"));
				System.out.println(result);
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		});

	}

	@Override
	public void paintInCanvas(Graphics2D g2) throws Exception {

		Dimension size = canvasComp.getSize();

		if (selectedOption == 0) {

			// image1
			g2.drawImage(demoImages.testImage1, 10, 10, null);

			// image2
			BufferedImage bi = createTestBufferedImage2();
			g2.drawImage(bi, 310, 10, null);

			// image3
			g2.drawImage(toolkitImage1, 610, 10, null);
			g2.drawImage(toolkitImage2, 10, 310, null);

			g2.drawImage(toolkitImage2, 10, 310, null);

			demoImages.iconInfo.paintIcon(null, g2, 10, 500);
			demoImages.iconTick.paintIcon(null, g2, 50, 500);
			demoImages.iconUp.paintIcon(null, g2, 100, 500);

		} else if (selectedOption == 1) {

			g2.drawImage(demoImages.cloudsImage, 0, 0, size.width, size.height, 0, 0,
					demoImages.cloudsImage.getWidth(null), demoImages.cloudsImage.getHeight(null), null);

		} else if (selectedOption == 2) {

			AlphaComposite alpha0 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f);
			AlphaComposite alpha1 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f);

			Composite origComposite = g2.getComposite();

			g2.setComposite(alpha0);
			g2.drawImage(demoImages.cloudsImage, 0, 0, size.width, size.height, 0, 0,
					demoImages.cloudsImage.getWidth(null), demoImages.cloudsImage.getHeight(null), null);

			g2.setComposite(alpha1);
			g2.drawImage(demoImages.snowImage, 0, 0, size.width, size.height, 0, 0,
					demoImages.cloudsImage.getWidth(null), demoImages.cloudsImage.getHeight(null), null);

			g2.setComposite(origComposite);

			Font font = new Font("Serif", Font.PLAIN, 36);
			g2.setFont(font);

			String tempString = "feels cold";
			FontRenderContext frc = g2.getFontRenderContext();
			Rectangle2D boundsTemp = font.getStringBounds(tempString, frc);

			int wText = (int) boundsTemp.getWidth();
			int hText = (int) boundsTemp.getHeight();
			int rx = (size.width - wText) / 2;
			int ry = (size.height - hText) / 2;

			g2.setColor(Color.LIGHT_GRAY);
			g2.fillRect(rx, ry, wText, hText);

			g2.setColor(Color.RED);
			int xTextTemp = rx - (int) boundsTemp.getX();
			int yTextTemp = ry - (int) boundsTemp.getY();
			g2.drawString(tempString, xTextTemp, yTextTemp);

		}

	}

	private static BufferedImage createTestBufferedImage2() throws Exception {

		BufferedImage bi = new BufferedImage(300, 300, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = bi.createGraphics();

		Rectangle2D rect1 = new Rectangle2D.Double(0, 0, 150, 200);
		g2.setColor(Color.RED);
		g2.draw(rect1);
		g2.dispose();
		return bi;
	}

	private static File getGenImgFile(String filename) throws Exception {

		Path path = Paths.get("target/generatedImages");
		Files.createDirectories(path);
		path = path.resolve(filename);
		System.out.println(path.toString());
		return path.toFile();

	}

	public static void main(String[] args) throws Exception {

		SwingUtilities.invokeLater(() -> {
			BufferedImageDemo1 frame = new BufferedImageDemo1();
			frame.init();
		});

	}

}
