package ru.tet.aux.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import ru.tet.javax.swing.aux.JControlPanelForTests;

public class LogDemoFrame extends JFrame {
	
	// инструментальная панель - содержит кнопки, метки, селекты и прочие рабочие элементы
	protected JControlPanelForTests controlPanel;

	// рабочая панель - полигон для компонентов
	protected JPanel workPanel;
	
	JTextPane textArea1;
	JTextPane textArea2;
	
	
	public LogDemoFrame() {

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Логи для демо");

	}	
	
	Style greenStyle;
	StyledDocument doc1;
	
	public void initWithControlPanelAbove() {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		workPanel = new JPanel();
		workPanel.setBorder(BorderFactory.createTitledBorder("workPanel"));
	    workPanel.setLayout(new BorderLayout()); 
	    
	    Font font = new Font("Serif", Font.PLAIN, 18);
	    
		textArea2 = new JTextPane();
		textArea2.setFont(font);
//		textArea2.setText("ta2");
		
		JScrollPane sp2 = new JScrollPane(textArea2);
		sp2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		textArea1 = new JTextPane();
		textArea1.setFont(font);
		JScrollPane sp1 = new JScrollPane(textArea1);
		sp1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		sp1.setPreferredSize(new Dimension(600, 300));
		
		
		JSplitPane splitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, sp1, sp2);
		workPanel.add(splitPane2);
		
		
//		splitPane2.setDividerLocation(200);
		
		
		addNamedStyles();
		
		controlPanel = new JControlPanelForTests();

		controlPanel.setMinimumSize(new Dimension(500, 100));
		workPanel.setMinimumSize(new Dimension(500, 300));

		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, controlPanel, workPanel);
		splitPane.setDividerLocation(100);
		setContentPane(splitPane);

		setVisible(true);

	}	
	
	
	private void addNamedStyles() {
		doc1 = (StyledDocument) textArea1.getDocument();
		
		greenStyle =doc1.addStyle("green", null);
		StyleConstants.setForeground(greenStyle, new Color(0, 120, 0));
		
		
	}
	
	public void hlGreen(int offset, int length) {
		doc1.setCharacterAttributes(offset, length, greenStyle, true);
	}
	
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(() -> {
			LogDemoFrame f = new LogDemoFrame();
			f.initWithControlPanelAbove();
		});
		
	}
	

}
