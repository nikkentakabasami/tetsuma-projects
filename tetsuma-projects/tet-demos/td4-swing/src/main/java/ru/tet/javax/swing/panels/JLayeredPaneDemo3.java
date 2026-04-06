package ru.tet.javax.swing.panels;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import ru.tet.javax.swing.aux.JFrameForTests;

public class JLayeredPaneDemo3 extends JFrameForTests {

	JLayeredPane layeredPane;
	JLabel dukeLabel;
	JCheckBox onTop;

	public static void main(String[] args) {

		JLayeredPaneDemo3 frame = new JLayeredPaneDemo3();
		frame.doInit();

	}

	@Override
	protected void doInit() {

		layeredPane = new JLayeredPane();

//		layeredPane.setPreferredSize(new Dimension(300, 310));
//		layeredPane.setBorder(BorderFactory.createTitledBorder(
//				"Move the Mouse to Move Duke"));

		layeredPane.addMouseMotionListener(new MouseMotionListener() {
			public void mouseMoved(MouseEvent e) {
				dukeLabel.setLocation(e.getX(), e.getY());
			}

			public void mouseDragged(MouseEvent e) {
			}
		});

		JLabel l1 = JFrameForTests.createDemoLabelAbsolute("L1", Color.GREEN, null, new Point(50, 50));
		JLabel l2 = JFrameForTests.createDemoLabelAbsolute("L2", Color.BLUE, null, new Point(70, 70));
		JLabel l3 = JFrameForTests.createDemoLabelAbsolute("L3", Color.RED, null, new Point(90, 90));

		layeredPane.add(l1);
		layeredPane.setLayer(l1, 0);

		layeredPane.add(l2);
		layeredPane.setLayer(l2, 200);

		layeredPane.add(l3);
		layeredPane.setLayer(l3, 500);

		dukeLabel = new JLabel("my mark");
		dukeLabel.setBounds(50, 15, 30, 30);
		dukeLabel.setOpaque(true);
		dukeLabel.setBackground(Color.BLACK);
		layeredPane.add(dukeLabel);
		layeredPane.setLayer(dukeLabel, 225);

		initWithControlPanelAbove(layeredPane);

	}

}
