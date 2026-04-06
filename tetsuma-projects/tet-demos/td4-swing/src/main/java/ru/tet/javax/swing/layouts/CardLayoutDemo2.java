package ru.tet.javax.swing.layouts;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import ru.tet.javax.swing.aux.JFrameForTests;

public class CardLayoutDemo2 extends JFrameForTests {

	static final String BUTTONPANEL = "BUTTONPANEL";
	static final String TEXTPANEL = "TEXTPANEL";
	static final String LABELPANEL = "LABELPANEL";

	CardLayout cardLayout;
	
	@Override
	protected void doInit() {

		initWithControlPanelAbove();

		controlPanel.addComboBox(new String[]{BUTTONPANEL, TEXTPANEL, LABELPANEL }, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String tabName = (String)controlPanel.comboBox1.getSelectedItem();
				cardLayout.show(workPanel, tabName);
				
			}
		});
		
		
		controlPanel.addButton("next", e->{
			cardLayout.next(workPanel);
			
//			controlPanel.comboBox1.setSelectedItem(e)
			
		});
		controlPanel.addButton("previous", e->{
			cardLayout.previous(workPanel);
		});
		
		
		
		
		
		
		JPanel tabPane1 = new JPanel();
		tabPane1.setBorder(BorderFactory.createTitledBorder("tab1"));
		tabPane1.add(new JButton("Button 1"));
		tabPane1.add(new JButton("Button 2"));
		tabPane1.add(new JButton("Button 3"));

		JPanel tabPane2 = new JPanel();
		tabPane2.setBorder(BorderFactory.createTitledBorder("tab2"));
		tabPane2.add(new JTextField("TextField", 20));

		JPanel tabPane3 = new JPanel();
		tabPane3.setBorder(BorderFactory.createTitledBorder("tab3"));
		tabPane3.setLayout(new BorderLayout());
		tabPane3.add(createDemoLabel(),BorderLayout.NORTH);
		tabPane3.add(createDemoLabel(),BorderLayout.CENTER);
		tabPane3.add(createDemoLabel(),BorderLayout.EAST);
		
		
		
		// Создаем панель
		cardLayout = new CardLayout();
		workPanel.setLayout(cardLayout);
		workPanel.add(tabPane1, BUTTONPANEL);
		workPanel.add(tabPane2, TEXTPANEL);
		workPanel.add(tabPane3, LABELPANEL);



	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			CardLayoutDemo2 demo = new CardLayoutDemo2();
			demo.doInit();
		});
	}

}
