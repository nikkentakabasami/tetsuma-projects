package ru.tet.javax.swing.components;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPasswordField;
import javax.swing.KeyStroke;

import ru.tet.javax.swing.aux.JFrameForTests;

public class JDialogDemo extends JFrameForTests {

	JDialog dialog1;
	JDialog dialog2;

	JPasswordField tfPassword;

	@Override
	protected void doInit() {
		initWithControlPanelAbove();

		createDialog1();
		controlPanel.addDebugLabel();
		controlPanel.addButton("show dialog1", e -> {
			dialog1.setVisible(true);
		});

		createDialog2();
		controlPanel.addButton("show password dialog", e -> {
			dialog2.setVisible(true);

			char[] result = tfPassword.getPassword();
			controlPanel.label1.setText("password is: " + new String(result));

			dialog2.dispose();

		});

	}

	void createDialog1() {

		dialog1 = new JDialog(this, "Direction");
		dialog1.setSize(200, 100);

		/*
		//не работает
		dialog1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					dialog1.setVisible(false);
				}
			}
		});
*/

		//закрывать при нажатии на esc
		InputMap inputMap = dialog1.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = dialog1.getRootPane().getActionMap();

		inputMap.put(KeyStroke.getKeyStroke("ESCAPE"), "closeDialog");
		actionMap.put("closeDialog", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog1.setVisible(false);
			}
		});

		
		Container dialogPanel = dialog1.getContentPane();
		dialogPanel.setLayout(new FlowLayout());
		JButton jbtnUp = new JButton("Up");
		JButton jbtnDown = new JButton("Down");
		dialogPanel.add(jbtnUp);
		dialogPanel.add(jbtnDown);

		jbtnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent le) {
				controlPanel.label1.setText("Direction is Up");
				dialog1.setVisible(false);
			}
		});

		jbtnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent le) {
				controlPanel.label1.setText("Direction is Down");
				dialog1.setVisible(false);
			}
		});

	}

	void createDialog2() {

		dialog2 = new JDialog(this);

		dialog2.setTitle("Введите пароль");
		dialog2.setSize(475, 50);
		dialog2.setModal(true);
		dialog2.setDefaultCloseOperation(HIDE_ON_CLOSE);

		// центрируем
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		dialog2.setBounds((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2,
				dialog2.getWidth(), dialog2.getHeight());

		tfPassword = new JPasswordField();
		dialog2.getContentPane().add(tfPassword);

		tfPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					dialog2.setVisible(false);

			}
		});

	}

	public static void main(String[] args) {
		JDialogDemo frame = new JDialogDemo();
		frame.doInit();
	}

}
