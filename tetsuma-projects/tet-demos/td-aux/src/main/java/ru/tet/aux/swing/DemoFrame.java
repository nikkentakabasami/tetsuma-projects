package ru.tet.aux.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import ru.tet.beans.SuIdNameModel;
import ru.tet.javax.swing.aux.IdNameListCellRenderer;
import ru.tet.javax.swing.aux.JControlPanelForTests;

/**
 * Основа для тестов разных классов.
 */
public class DemoFrame extends AbstractDemoFrame {
	
	public static int INSET = 50;
	
	JComboBox demosComboBox;
	
	public void initWithControlPanelAbove() {
		setBounds();
		
		JComponent demoContentPane = createDemoContentPane();
		
		setContentPane(demoContentPane);
		
		setVisible(true);

	}	

	public void initComplex(String[] data, ActionListener listener) {
		setBounds();
		
		createComboBox(data, listener);
		JComponent demoContentPane = createDemoContentPane();

		
	    JPanel p = new JPanel(); 
	    p.setLayout(new BorderLayout()); 

	    p.add(demosComboBox, BorderLayout.NORTH); 
	    p.add(demoContentPane);
	    
		setContentPane(p);
		
		setVisible(true);

	}	
	
	void createComboBox(String[] data, ActionListener listener) {
		
		demosComboBox = new JComboBox(data);
		demosComboBox.addActionListener(listener);
		demosComboBox.setPreferredSize(new Dimension(800, 30));
		
	}
	
	protected void setBounds() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(INSET, INSET, screenSize.width - INSET * 2, screenSize.height - INSET * 2);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	
	protected JComponent createDemoContentPane() {
		
		workPanel = new JPanel();
		workPanel.setBorder(BorderFactory.createTitledBorder("workPanel"));

	    workPanel.setLayout(new BorderLayout()); 

	    
	    Font font = new Font("Serif", Font.PLAIN, 18);
	    
		textArea2 = new JTextPane();
		textArea2.setFont(font);
		
		JScrollPane sp2 = new JScrollPane(textArea2);

		textArea1 = new JTextPane();
		textArea1.setFont(font);
		JScrollPane sp1 = new JScrollPane(textArea1);
		sp1.setPreferredSize(new Dimension(600, 300));
		
		
		JSplitPane splitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, sp1, sp2);
		workPanel.add(splitPane2);	    
		
		controlPanel = new JControlPanelForTests();

		controlPanel.setMinimumSize(new Dimension(500, 200));
		workPanel.setMinimumSize(new Dimension(500, 300));

		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, controlPanel, workPanel);
		splitPane.setDividerLocation(200);
				
		return splitPane;
		
	}
	
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(() -> {
			DemoFrame f = new DemoFrame();
			f.initWithControlPanelAbove();
		});
		
	}
	

}
