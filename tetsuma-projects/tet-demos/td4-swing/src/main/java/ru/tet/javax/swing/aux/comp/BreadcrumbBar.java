package ru.tet.javax.swing.aux.comp;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;

public class BreadcrumbBar extends JPanel {
	
	ButtonGroup group;
	
	public BreadcrumbBar(int overlap, Color color, String... list) {
		super(new FlowLayout(FlowLayout.LEFT, -overlap, 0));
		
		setBorder(BorderFactory.createEmptyBorder(0, overlap, 0, 0));
		setOpaque(false);
		
		group = new ButtonGroup();
		for (String title : list) {
			boolean first = (getComponentCount() == 0);
			AbstractButton b = new BreadcrumbToggleButton(title, color, first); 
					
			add(b);
			group.add(b);
		}
		
		
		
		
	}
	
	
	
	
	
}
