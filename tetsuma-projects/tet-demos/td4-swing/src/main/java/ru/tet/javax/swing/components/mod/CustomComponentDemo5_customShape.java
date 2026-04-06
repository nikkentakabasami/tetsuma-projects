package ru.tet.javax.swing.components.mod;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;

import ru.tet.javax.swing.aux.JFrameForTests;
import ru.tet.javax.swing.aux.comp.BreadcrumbBar;
import ru.tet.javax.swing.aux.comp.BreadcrumbToggleButton;


/**
 * 
 * BreadcrumbToggleButton - Компонент с кастомной формой и рисовкой.
 * 
 * @author tetsuma
 *
 */
public class CustomComponentDemo5_customShape extends JFrameForTests {

	  private static final String TXT = "***********************";

	@Override
	protected void doInit() {
		initWithControlPanelAbove();


		workPanel.setLayout(new GridLayout(0, 1));
//		workPanel.setBackground(Color.LIGHT_GRAY);
		
		workPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 0));
		
		
		
		
		workPanel.add(new BreadcrumbBar(0, Color.PINK, "overlap1:", "0px", TXT));
		workPanel.add(new BreadcrumbBar(5, Color.CYAN, "overlap2:", "5px", TXT));
		workPanel.add(new BreadcrumbBar(10, Color.ORANGE, "overlap3:", "10px", TXT));
	    setPreferredSize(new Dimension(320, 240));
		
		
		
		
		
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> {
			CustomComponentDemo5_customShape frame = new CustomComponentDemo5_customShape();
			frame.init();
		});
	}

	

}
