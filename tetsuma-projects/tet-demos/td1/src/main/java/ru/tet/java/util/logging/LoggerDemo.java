package ru.tet.java.util.logging;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.invoke.MethodHandles;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import ru.tet.javax.swing.aux.JFrameForTests;
import ru.tet.javax.swing.aux.TextAreaLoggerHandler;

public class LoggerDemo extends JFrameForTests {

//    private JPanel buttonPanel;

	public static final String LOGGER_NAME = MethodHandles.lookup().lookupClass().getName();
	private static final Logger LOGGER = Logger.getLogger(LOGGER_NAME);

	JMenuBar menuBar;
	JTextField textField;
	JTextArea textArea;
	
	@Override
	protected void doInit() throws Exception {
		initWithControlPanelAbove();

		workPanel.setLayout(new BorderLayout());

		
		textArea = new JTextArea();
		workPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);

		textField = makeTextField();
		workPanel.add(textField, BorderLayout.SOUTH);
		
		JButton button = new JButton("test addActionListener");
		button.setActionCommand("buttonAction");
		button.addActionListener(LoggerDemo::myActionListener);
		workPanel.add(button, BorderLayout.NORTH);
		
		LOGGER.setUseParentHandlers(false);
		LOGGER.addHandler(new TextAreaLoggerHandler(textArea));
		

		setJMenuBar(makeMenuBar());
	}

	private static void myActionListener(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		String sourceClass = e.getSource().getClass().getSimpleName();
		
		boolean isShiftDown = (e.getModifiers() & ActionEvent.SHIFT_MASK) != 0;
		boolean isMouseEvent = (e.getModifiers() & AWTEvent.MOUSE_EVENT_MASK) != 0;
		
		LOGGER.info(String.format("sourceClass: %s, actionCommand: %s, shiftDown: %s, isMouseEvent: %s ", sourceClass, actionCommand, isShiftDown, isMouseEvent));
		
	}

	private JMenuBar makeMenuBar() {
		menuBar = new JMenuBar();
		JMenu menu = menuBar.add(new JMenu("Test Menu"));
		menu.setMnemonic(KeyEvent.VK_T);

		JMenuItem item = new JMenuItem(new AbstractAction("beep") {
			@Override
			public void actionPerformed(ActionEvent e) {
				Toolkit.getDefaultToolkit().beep();
			}
		});
		menu.add(item);

		item.addActionListener(LoggerDemo::myActionListener);
		return menuBar;
	}

	private static JTextField makeTextField() {
		JTextField field = new JTextField(20);
		field.addActionListener(LoggerDemo::myActionListener);
		return field;
	}

	public static void main(String[] args) throws Exception {

		SwingUtilities.invokeLater(() -> {
			LoggerDemo frame = new LoggerDemo();
			frame.init();
		});

	}

}
