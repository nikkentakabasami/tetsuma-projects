package ru.tet.javax.swing.components.mod;

import java.awt.BorderLayout;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import ru.tet.javax.swing.aux.JFrameForTests;
import ru.tet.javax.swing.aux.comp.CustomComponent2;

public class CustomComponentDemo2 extends JFrameForTests {

	CustomComponent2 comp;

	@Override
	protected void doInit() {
		initWithControlPanelAbove();

		comp = new CustomComponent2();
//		comp.setPreferredSize(new Dimension(200, 100));

		workPanel.setLayout(new BorderLayout());
		workPanel.add(comp, SwingConstants.CENTER);

	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> {
			CustomComponentDemo2 frame = new CustomComponentDemo2();
			frame.init();
		});
	}

	

}
