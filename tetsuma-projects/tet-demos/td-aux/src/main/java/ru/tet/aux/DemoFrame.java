package ru.tet.aux;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import ru.tet.javax.swing.aux.JControlPanelForTests;

public class DemoFrame extends JFrame {
	
	public static int INSET = 50;
	
	
	// инструментальная панель - содержит кнопки, метки, селекты и прочие рабочие элементы
	protected JControlPanelForTests controlPanel;

	// рабочая панель - полигон для компонентов
	protected JPanel workPanel;
	
	JTextPane textArea1;
	JTextPane textArea2;
	
	
	public DemoFrame() {

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Мои тесты");

	}	
	
	Style greenStyle;
	StyledDocument doc1;
	
	public void initWithControlPanelAbove() {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		setBounds(INSET, INSET, screenSize.width - INSET * 2, screenSize.height - INSET * 2);

		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		workPanel = new JPanel();
		workPanel.setBorder(BorderFactory.createTitledBorder("workPanel"));

	    workPanel.setLayout(new BorderLayout()); 

	    
	    Font font = new Font("Serif", Font.PLAIN, 18);

	    
	    
		textArea2 = new JTextPane();
		textArea2.setFont(font);
//		textArea2.setText("ta2");
		
		JScrollPane sp2 = new JScrollPane(textArea2);

		textArea1 = new JTextPane();
		textArea1.setFont(font);
		JScrollPane sp1 = new JScrollPane(textArea1);
		sp1.setPreferredSize(new Dimension(600, 300));
		
		
		JSplitPane splitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, sp1, sp2);
		workPanel.add(splitPane2);	    
	    
		/*
		textArea2 = new JTextPane();
		textArea2.setFont(font);
//		textArea1.setText("row1\r\nrow2\r\nrow3");
		
		JScrollPane sp1 = new JScrollPane(textArea2);
		sp1.setPreferredSize(new Dimension(600, 300));
		workPanel.add(sp1, BorderLayout.EAST);

		textArea1 = new JTextPane();
		textArea1.setFont(font);
//		textArea2.setText("center");
		workPanel.add(new JScrollPane(textArea1), BorderLayout.CENTER);
		*/
		
		doc1 = (StyledDocument) textArea1.getDocument();
		
		greenStyle =doc1.addStyle("green", null);
		StyleConstants.setForeground(greenStyle, new Color(0, 120, 0));
		
		controlPanel = new JControlPanelForTests();

		controlPanel.setMinimumSize(new Dimension(500, 200));
		workPanel.setMinimumSize(new Dimension(500, 300));

		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, controlPanel, workPanel);
		splitPane.setDividerLocation(200);
		setContentPane(splitPane);

		setVisible(true);

	}	
	
	
	public void hlGreen(int offset, int length) {
		doc1.setCharacterAttributes(offset, length, greenStyle, true);
	}
	
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(() -> {
			DemoFrame f = new DemoFrame();
			f.initWithControlPanelAbove();
		});
		
	}
	

}
