package ru.tet.javax.swing.layouts;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import ru.tet.javax.swing.aux.JFrameForTests;

public class CardLayoutDemo extends JFrameForTests {

	static final String BUTTONPANEL = "BUTTONPANEL";
	static final String TEXTPANEL = "TEXTPANEL";

	@Override
	protected void doInit() {

		initWithControlPanelAbove();

		JPanel comboBoxPanel = new JPanel();
		String comboBoxItems[] = { BUTTONPANEL, TEXTPANEL };
		JComboBox cb = new JComboBox(comboBoxItems);
		cb.setEditable(false);
		comboBoxPanel.add(cb);

		JPanel tabPane1 = new JPanel();
		tabPane1.add(new JButton("Button 1"));
		tabPane1.add(new JButton("Button 2"));
		tabPane1.add(new JButton("Button 3"));

		JPanel tabPane2 = new JPanel();
		tabPane2.add(new JTextField("TextField", 20));

		// Создаем панель
		JPanel cardsPanel = new JPanel(new CardLayout());
		cardsPanel.add(tabPane1, BUTTONPANEL);
		cardsPanel.add(tabPane2, TEXTPANEL);

		workPanel.setLayout(new BorderLayout());
		workPanel.add(comboBoxPanel, BorderLayout.NORTH);
		workPanel.add(cardsPanel, BorderLayout.CENTER);

		cb.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				CardLayout cl = (CardLayout) (cardsPanel.getLayout());
				cl.show(cardsPanel, (String) e.getItem());
			}
		});

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			CardLayoutDemo demo = new CardLayoutDemo();
			demo.doInit();
		});
	}

}
