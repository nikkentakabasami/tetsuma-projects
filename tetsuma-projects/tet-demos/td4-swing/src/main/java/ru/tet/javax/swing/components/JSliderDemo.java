package ru.tet.javax.swing.components;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

import ru.tet.javax.swing.aux.JFrameForTests;

public class JSliderDemo extends JFrameForTests {

	JSlider sliderHoriz;
	JSlider sliderVert;

	@Override
	protected void doInit() throws Exception {
		initWithControlPanelAbove();

		controlPanel.addDebugLabel();

		sliderVert = new JSlider(JSlider.VERTICAL);
		sliderVert.setMinimum(0);
		sliderVert.setMaximum(200);
		sliderVert.setValue(22);
//		sliderVert.setExtent(ABORT);
		sliderVert.setMajorTickSpacing(10);
		sliderVert.setMinorTickSpacing(5);
		sliderVert.setLabelTable(sliderVert.createStandardLabels(40));
		sliderVert.setPaintTicks(true);
		sliderVert.setPaintLabels(true);
		
		
		
		sliderHoriz = new JSlider(100, 200, 133);
		sliderHoriz.setPreferredSize(new Dimension(600, 50));
		sliderHoriz.setMajorTickSpacing(20);

		//надписи каждые 40 единиц
		sliderHoriz.setLabelTable(sliderHoriz.createStandardLabels(40));
		sliderHoriz.setPaintTicks(true);
		sliderHoriz.setPaintLabels(true);
		sliderHoriz.setInverted(true);

		
//		JPanel hp = new JPanel();
//		hp.setLayout(new BoxLayout(hp, BoxLayout.PAGE_AXIS));
		
		Box hp = Box.createVerticalBox();
		hp.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		JLabel sliderLabel = new JLabel("my horizontal slider");
//		sliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		sliderLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		hp.add(sliderLabel);
		hp.add(sliderHoriz);
		
		
		
		
		
		
		ChangeListener cl = e->{

			String s = String.format("horizontal value: %d, vertical value: %d",sliderHoriz.getValue(),sliderVert.getValue());
			controlPanel.label1.setText(s);
			
		};
		
		sliderHoriz.addChangeListener(cl);
		sliderVert.addChangeListener(cl);


		workPanel.add(hp);
		workPanel.add(sliderVert);

		cl.stateChanged(null);
		
	}

	public static void main(String[] args) throws Exception {
		JSliderDemo frame = new JSliderDemo();
		frame.doInit();
	}

}
