package ru.tet.javax.swing.components;

import java.awt.Adjustable;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.BoxLayout;
import javax.swing.JScrollBar;

import ru.tet.javax.swing.aux.JFrameForTests;

public class JScrollBarDemo extends JFrameForTests {

	@Override
	protected void doInit() {
		initWithControlPanelAbove();

		controlPanel.addDebugLabel();
		controlPanel.addDebugLabel();

		JScrollBar jsbVert = new JScrollBar(JScrollBar.VERTICAL, 
				0, // initial value
				5, // extent
				0, // minimum
				500); // maximum
		JScrollBar jsbHoriz = new JScrollBar(Adjustable.HORIZONTAL, 
				250, // initial value
				0, // extent
				0, // minimum
				500); // maximum

		jsbVert.setPreferredSize(new Dimension(20, 200));
		jsbHoriz.setPreferredSize(new Dimension(200, 20));

		jsbHoriz.setBlockIncrement(25);

		jsbVert.addAdjustmentListener(new AdjustmentListener() {
			public void adjustmentValueChanged(AdjustmentEvent ae) {
				// If the scroll bar is in the process of being changed
				if (jsbVert.getValueIsAdjusting())
					return;

				controlPanel.label1.setText("Value of vertical scroll bar: " + ae.getValue());
			}
		});

		jsbHoriz.addAdjustmentListener(new AdjustmentListener() {
			public void adjustmentValueChanged(AdjustmentEvent ae) {
				controlPanel.label2.setText("Value of horizontal scroll bar: " + ae.getValue());
			}
		});

//	    workPanel.setLayout(new BoxLayout(workPanel, BoxLayout.Y_AXIS));
//	    workPanel.setLayout(new GridLayout(1,2)); 
		workPanel.add(jsbVert);
		workPanel.add(jsbHoriz);

	}

	public static void main(String[] args) {
		JScrollBarDemo frame = new JScrollBarDemo();
		frame.doInit();
	}

}
