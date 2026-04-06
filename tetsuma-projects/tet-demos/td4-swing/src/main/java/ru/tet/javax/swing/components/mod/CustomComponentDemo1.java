package ru.tet.javax.swing.components.mod;

import java.awt.BorderLayout;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import ru.tet.javax.swing.aux.JFrameForTests;
import ru.tet.javax.swing.aux.comp.StrippedOvalComponent;

public class CustomComponentDemo1 extends JFrameForTests {

	StrippedOvalComponent comp;
	
	@Override
	protected void doInit() {
		initWithControlPanelAbove();

		comp = new StrippedOvalComponent();
//		comp.setPreferredSize(new Dimension(200, 100));
		
		workPanel.setLayout(new BorderLayout());
		workPanel.add(comp,SwingConstants.CENTER);
		
		
	}

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(() -> {
			CustomComponentDemo1 frame = new CustomComponentDemo1();
			frame.init();
		});
	}

	
}
