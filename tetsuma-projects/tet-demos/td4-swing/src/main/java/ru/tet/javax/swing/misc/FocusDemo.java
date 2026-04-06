package ru.tet.javax.swing.misc;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import ru.tet.javax.swing.aux.JFrameForTests;

public class FocusDemo extends JFrameForTests {

	static JFrame frame;
	JTextField t1, t2, t3, t4;
	JButton b1, b2, b3, b4;
	JTextArea text1;

	@Override
	protected void doInit() throws Exception {
		initWithControlPanelAbove();

		makeTestPanel();

		controlPanel.addTitleLabel("Ctrl+Q - check current focus");
		controlPanel.newHorizontalBox();
		controlPanel.addDebugLabel();
		
		InputMap inputMap = workPanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		ActionMap actionMap = workPanel.getActionMap();

		inputMap.put(KeyStroke.getKeyStroke("control Q"), "showFocus");
		actionMap.put("showFocus", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showFocus();
			}
		});

		/*
		workPanel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_Q && e.isControlDown()) {
					showFocus();
				}
			}
		});
		*/

	}

	private void showFocus() {
		Component focusedComponent = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
		if (focusedComponent != null) {
			controlPanel.label1.setText("Фокус на компоненте: " + focusedComponent.getClass().getName());
		} else {
			controlPanel.label1.setText("В данный момент ни один компонент не имеет фокуса");
		}
	}

	private void makeTestPanel() {

		workPanel.setLayout(new BorderLayout());

		b1 = new JButton("JButton");
		b2 = new JButton("JButton");
		b3 = new JButton("JButton");
		b4 = new JButton("JButton");

		JPanel buttonPanel = new JPanel(new GridLayout(1, 1));
		buttonPanel.add(b1);
		buttonPanel.add(b2);
		buttonPanel.add(b3);
		buttonPanel.add(b4);

		text1 = new JTextArea("JTextArea", 15, 40);
		JPanel textAreaPanel = new JPanel(new BorderLayout());
		textAreaPanel.add(text1, BorderLayout.CENTER);

		t1 = new JTextField("JTextField");
		t2 = new JTextField("JTextField");
		t3 = new JTextField("JTextField");
		t4 = new JTextField("JTextField");
		JPanel textFieldPanel = new JPanel(new GridLayout(1, 1));
		textFieldPanel.add(t1);
		textFieldPanel.add(t2);
		textFieldPanel.add(t3);
		textFieldPanel.add(t4);

		workPanel.add(buttonPanel, BorderLayout.PAGE_START);
		workPanel.add(textAreaPanel, BorderLayout.CENTER);
		workPanel.add(textFieldPanel, BorderLayout.PAGE_END);
		workPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

	}

	public static void main(String[] args) throws Exception {

		SwingUtilities.invokeLater(() -> {
			FocusDemo frame = new FocusDemo();
			frame.init();
		});

	}

}