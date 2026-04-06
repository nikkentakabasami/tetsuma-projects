package ru.tet.javax.swing.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.SwingUtilities;
import javax.swing.event.ChangeListener;

import ru.tet.javax.swing.aux.JFrameForGraphicsTests;

public class AffineTransformDemo extends JFrameForGraphicsTests {

	int selectedOption = 0;

	AffineTransform at;
	boolean useTempG2;
	boolean invert;

	boolean concatenateOperations;
	
	@Override
	protected void doInit() throws Exception {
		initWithCanvasComponent();

		at = new AffineTransform();
		at.translate(100, 100);

		final ChangeListener cl = e -> {
			int xTranslate = controlPanel.slider1.getValue();
			controlPanel.compLabel2.setText("xTranslate:" + xTranslate);

			int yTranslate = controlPanel.slider2.getValue();
			controlPanel.compLabel3.setText("yTranslate: "+yTranslate);
			
			
			double xScale = ((double) controlPanel.slider3.getValue()) / 10;
			controlPanel.compLabel4.setText("xScale: " + xScale);

			double xShear = ((double) controlPanel.slider4.getValue()) / 10;
			controlPanel.compLabel5.setText("xShear: " + xShear);

			double rotate = ((double) controlPanel.slider5.getValue()) / 10;
			controlPanel.compLabel6.setText("rotate: " + rotate);


			int operationSequence = controlPanel.comboBox1.getSelectedIndex();
			
			
//			at = AffineTransform.getRotateInstance(rotate);
//			at.concatenate(AffineTransform.getTranslateInstance(xTranslate, 100));

//			at = AffineTransform.getTranslateInstance(xTranslate, 100);
//			at.concatenate(AffineTransform.getRotateInstance(rotate));

//			at = new AffineTransform();
			at.setToIdentity();
			
			if (operationSequence==0) {
				
				if (concatenateOperations) {
					at = AffineTransform.getTranslateInstance(xTranslate, yTranslate);
					at.concatenate(AffineTransform.getRotateInstance(rotate));
					at.concatenate(AffineTransform.getScaleInstance(xScale, 1));
					at.concatenate(AffineTransform.getShearInstance(xShear, 0));
					
				} else {
					at.translate(xTranslate, yTranslate);
					at.rotate(rotate);
					at.scale(xScale, 1);
					at.shear(xShear, 0);
				}
				
			} else if (operationSequence==1) {
				
				if (concatenateOperations) {
					at.rotate(rotate);
					at.concatenate(AffineTransform.getTranslateInstance(xTranslate, yTranslate));
					at.concatenate(AffineTransform.getScaleInstance(xScale, 1));
					
				} else {
					at.rotate(rotate);
					at.translate(xTranslate, yTranslate);
					at.scale(xScale, 1);
				}
				
				
			} else if (operationSequence==2) {
				at.scale(xScale, 1);
				at.translate(xTranslate, yTranslate);
				at.rotate(rotate);
			} else if (operationSequence==3) {
				at = AffineTransform.getScaleInstance(xScale, 1);
				at.concatenate(AffineTransform.getTranslateInstance(xTranslate, yTranslate));
				at.concatenate(AffineTransform.getRotateInstance(rotate));
			}
			

			if (invert) {
				try {
					at.invert();
				} catch (NoninvertibleTransformException e1) {
					e1.printStackTrace();
				}
			}

//		    at.setToTranslation(xTranslate, 100);
//	        at.setToScale(scale, 1);

			canvasComp.repaint();
		};

		
		controlPanel.addComboBox(new String[] { "translate,rotate,scale,shear", "rotate,translate,scale", "scale,translate,rotate","concatenate roate+translate" }, e -> {
			cl.stateChanged(null);
		}, "Последовательность операций");
		

//		controlPanel.addLabel("yTranslate: 100");
		controlPanel.addSlider(0, 300, 100, "xTranslate: 100", cl);
		controlPanel.addSlider(0, 300, 100, "yTranslate: 100", cl);
		controlPanel.addSlider(10, 40, 10, "xScale: 1", cl);
		controlPanel.addSlider(0, 20, 0, "xShear: 0", cl);
		controlPanel.addSlider(0, 20, 0, "rotate: 0", cl);

		controlPanel.addCheckbox("invert", e -> {
			invert = controlPanel.checkbox1.isSelected();
			cl.stateChanged(null);
		});

		controlPanel.addCheckbox("use temp g2", e -> {
			useTempG2 = controlPanel.checkbox2.isSelected();
			canvasComp.repaint();
		});

		
		controlPanel.addCheckbox("concatenateOperations", e -> {
			concatenateOperations = controlPanel.checkbox3.isSelected();
			cl.stateChanged(null);
		});
		
		
	}

	
	@Override
	public void paintInCanvas(Graphics2D g2) throws Exception {

		Dimension size = canvasComp.getSize();
		
		// закрасим белым наполовину
		g2.setPaint(Color.WHITE);
		g2.fillRect(0, 0, size.width, size.height);

		paintGrid(g2);
		
		//Применить трансформацию можно двумя основными способами 
		if (useTempG2) {
			
			//можно создать временный контекст и применить трансформацию на нём
			Graphics2D g2t = (Graphics2D) g2.create();
			g2t.transform(at);
			paintShapes(g2t);
			g2t.dispose();
		} else {
			
			//можно сохранить трансформацию по умолчанию, применить новую, нарисовать и вернуть исходную трансформацию
			AffineTransform origAt = g2.getTransform();
			g2.transform(at);
			
			paintShapes(g2);

			g2.setTransform(origAt);
		}
	}
	
	
	private void paintShapes(Graphics2D g2) throws Exception {
		BasicStroke wideStroke = new BasicStroke(6.0f);

		g2.setStroke(wideStroke);
		g2.setPaint(Color.ORANGE);

		// или так
//		AffineTransform currentAt = new AffineTransform();
//		currentAt.concatenate(at);
//        g2.transform(currentAt);

		g2.draw(new RoundRectangle2D.Double(0, 0, 100, 100, 10, 10));

		GradientPaint gradPaint = new GradientPaint(0, 0, Color.RED, 500, 500, Color.BLUE);
		g2.setPaint(gradPaint);
		g2.fill(new Rectangle2D.Double(200, 200, 200, 200));
	}
	

	public static void main(String[] args) throws Exception {

		SwingUtilities.invokeLater(() -> {
			AffineTransformDemo frame = new AffineTransformDemo();
			frame.init();
		});

	}

}
