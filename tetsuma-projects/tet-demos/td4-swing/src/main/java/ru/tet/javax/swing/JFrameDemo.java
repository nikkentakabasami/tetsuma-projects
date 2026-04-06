package ru.tet.javax.swing;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import ru.tet.javax.swing.aux.GBC;

public class JFrameDemo {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> {
			makeFrame();
		});

	}

	private static void makeFrame() {

		JFrame frame = new JFrame("Hello world swing");
		frame.setPreferredSize(new Dimension(600, 500));
//	    frame.setSize(600, 500);

//		JLabel label = new JLabel("hello label");
		
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new GridBagLayout());
		
		JButton b1 = new JButton("expand");
		b1.addActionListener(e->{
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		});

		JButton b2 = new JButton("pack");
		b2.addActionListener(e->{
			frame.pack();
		});
		
		
		
	    JCheckBox check = new JCheckBox("Always On Top", true);
	    check.addActionListener(e -> {
	      JCheckBox c = (JCheckBox) e.getSource();
    	  frame.setAlwaysOnTop(c.isSelected());
	      
	    });
		
		contentPane.add(b1, new GBC(0, 0));
		contentPane.add(b2, new GBC(1, 0));
		contentPane.add(check, new GBC(0, 1));
		
		
		
		
		
		
		
		frame.setContentPane(contentPane);
	    frame.setAlwaysOnTop(true);
		
		
//		contentPane.add(label);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		
		
		frame.setVisible(true);

//		frame.setUndecorated(true);
		
		
	}

}
