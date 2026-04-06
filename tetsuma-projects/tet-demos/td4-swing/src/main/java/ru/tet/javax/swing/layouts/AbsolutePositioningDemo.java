package ru.tet.javax.swing.layouts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import ru.tet.javax.swing.aux.JFrameForTests;

public class AbsolutePositioningDemo extends JFrameForTests {


	Dimension defaultLabelSize = new Dimension(100, 50);
	
	
	@Override
	protected void doInit() {

		initWithControlPanelAbove(workPanel);
		workPanel.setLayout(null);
		

        
		JLabel l1 = addAbsoluteLabel(Color.GREEN,0, 0);
		JLabel l2 = addAbsoluteLabel(Color.RED,50, 20);
		JLabel l3 = addAbsoluteLabel(Color.BLUE,50, 80);
		JLabel l4 = addAbsoluteLabel(Color.CYAN,100, 200);
		
		
		

//		workPanel = new JPanel();
		
		
		controlPanel.addButton("make random position for l1", e -> {
			l1.setBounds(makeRandomInt(300), makeRandomInt(300), makeRandomInt(200), makeRandomInt(200));
			makeDemoLabelCaption(l1);
		});
	}
	
	private void makeDemoLabelCaption(JLabel l) {
		Dimension size = l.getSize();
		Point location = l.getLocation();
		String s = String.format("<html>coord(%d,%d) <br> size(%d,%d)</html>",location.x,location.y,size.width,size.height);
		l.setText(s);
	}
	
	private JLabel addAbsoluteLabel(Color color, int x, int y) {
		JLabel label = new JLabel("");
		label.setVerticalAlignment(JLabel.TOP);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBorder(BorderFactory.createLineBorder(Color.black));
		label.setSize(defaultLabelSize);
		
		if (color!=null) {
			label.setOpaque(true);
			label.setBackground(color);
			label.setForeground(Color.black);
		}

        Insets insets = workPanel.getInsets();
		
		
		label.setLocation(insets.left+x, insets.top+y);

		workPanel.add(label);
		makeDemoLabelCaption(label);
		
		return label;
	}	
	
	
	
	
	
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(() -> {
    		AbsolutePositioningDemo demo = new AbsolutePositioningDemo();
    		demo.doInit();
		});		
		
	}

}
