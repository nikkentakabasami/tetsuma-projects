package ru.tet.javax.swing.graphics;

import java.awt.BorderLayout;

import javax.swing.SwingUtilities;

import ru.tet.javax.swing.aux.JFrameForTests;
import ru.tet.javax.swing.aux.comp.CustomComponent_AnalogClock;

public class AffineTransformDemo3_analogClock extends JFrameForTests {

	CustomComponent_AnalogClock analogClock;
	
	@Override
	protected void doInit() throws Exception {
		initWithControlPanelAbove();

		controlPanel.addCheckbox("useBackgroundImage", e->{
			analogClock.setUseBackgroundImage(controlPanel.checkbox1.isSelected());
		});
		
		controlPanel.addButton("hide/show", e->{
			analogClock.setVisible(!analogClock.isVisible());
		});
		
		
	    workPanel.setLayout(new BorderLayout()); 
		
		analogClock = new CustomComponent_AnalogClock(); 
		
	    workPanel.add(analogClock);
		
		
	}


	public static void main(String[] args) throws Exception {

		SwingUtilities.invokeLater(() -> {
			AffineTransformDemo3_analogClock frame = new AffineTransformDemo3_analogClock();
			frame.init();
		});

	}

}
