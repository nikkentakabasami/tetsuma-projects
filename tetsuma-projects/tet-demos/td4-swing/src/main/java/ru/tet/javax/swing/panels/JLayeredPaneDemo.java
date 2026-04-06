package ru.tet.javax.swing.panels;

import java.awt.Color;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import ru.tet.javax.swing.aux.JFrameForTests;

public class JLayeredPaneDemo extends JFrameForTests {

	int currentLayer = 0;
	
	
	@Override
	protected void doInit() {
		

		JLabel l1 = createDemoLabelAbsolute("1", Color.GREEN, null, new Point(50,50));
		JLabel l2 = createDemoLabelAbsolute("2", Color.BLUE, null, new Point(70,70));
		JLabel l3 = createDemoLabelAbsolute("3", Color.RED, null, new Point(90,90));
		JLabel l4 = createDemoLabelAbsolute("4", Color.CYAN, null, new Point(80,60));
		
		JLayeredPane lp = new JLayeredPane();
		
		lp.add(l1);
		lp.setLayer(l1, 0);

		lp.add(l2);
		lp.setLayer(l2, 100);

		lp.add(l3);
		lp.setLayer(l3, 200);
		lp.add(l4);
		
		lp.setBorder(BorderFactory.createTitledBorder("JLayeredPane"));
		
		
		initWithControlPanelAbove(lp);
		
		
		
		controlPanel.addButton("change l4 layer", e->{
			currentLayer = ((currentLayer+1)%3)*100;
			lp.setLayer(l4, currentLayer,0);
			controlPanel.getLabel(0).setText("l4 layer:"+currentLayer);
			
		});
		controlPanel.addDebugLabel("0");
		
		
	}
	
	
	
	public static void main(String[] args) {
		JLayeredPaneDemo frame = new JLayeredPaneDemo();
		frame.doInit();
	}


}
