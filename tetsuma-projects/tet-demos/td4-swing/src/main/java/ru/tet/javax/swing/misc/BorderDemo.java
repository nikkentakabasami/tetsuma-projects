package ru.tet.javax.swing.misc;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import ru.tet.javax.swing.aux.JFrameForTests;

public class BorderDemo extends JFrameForTests {


	@Override
	protected void doInit() {

		initWithControlPanelAbove();
		
//		addLabel(, );
		
		addLabel(" This uses a line border. ",BorderFactory.createLineBorder(Color.BLACK));
		addLabel(" This uses an etched border. ",BorderFactory.createEtchedBorder());
		addLabel(" This uses an empty border. ",BorderFactory.createEmptyBorder());
		addLabel(" This uses an titled border. ",BorderFactory.createTitledBorder("my title") );
		addLabel(" This uses an bevel border. ",BorderFactory.createBevelBorder(BevelBorder.RAISED));
		
	}

	private void addLabel(String title, Border border) {
		JLabel l = new JLabel(title);
		l.setBorder(border);
		workPanel.add(l);
	}
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			BorderDemo demo = new BorderDemo();
			demo.doInit();
		});		
	}

}
