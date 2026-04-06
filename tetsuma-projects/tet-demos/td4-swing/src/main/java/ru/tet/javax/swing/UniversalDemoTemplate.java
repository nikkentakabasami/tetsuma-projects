package ru.tet.javax.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import ru.tet.javax.swing.aux.JFrameForTests;

//Шаблон для создания новых демок
public class UniversalDemoTemplate extends JFrameForTests {

	JTextArea textArea1;
	JTextArea textArea2;
	
	@Override
	protected void doInit() throws Exception {
		initWithControlPanelAbove();

	    workPanel.setLayout(new BorderLayout()); 
	    
		textArea1 = new JTextArea();
		textArea1.setText("row1\r\nrow2\r\nrow3");
		
		JScrollPane sp1 = new JScrollPane(textArea1);
		sp1.setPreferredSize(new Dimension(600, 300));
		workPanel.add(sp1, BorderLayout.EAST);

		textArea2 = new JTextArea();
		textArea2.setText("center");
		workPanel.add(new JScrollPane(textArea2), BorderLayout.CENTER);
	    
	    
	    
	    
//	    workPanel.setLayout(new GridLayout(1,2)); 
//	    workPanel.setLayout(new BoxLayout(workPanel, BoxLayout.Y_AXIS));
		
//	    workPanel.setLayout(new GridBagLayout()); 
//		workPanel.add(label, new GBC(0, 0));
//		workPanel.add(Box.createVerticalGlue(), new GBC(0, 1,1,1,1,1));
		
		
	}

	public static void main(String[] args) throws Exception {

		SwingUtilities.invokeLater(() -> {
			UniversalDemoTemplate frame = new UniversalDemoTemplate();
			frame.init();
		});

	}

}
