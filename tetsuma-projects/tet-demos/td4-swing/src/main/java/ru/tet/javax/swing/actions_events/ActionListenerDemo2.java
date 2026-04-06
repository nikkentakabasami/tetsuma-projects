package ru.tet.javax.swing.actions_events;

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

public class ActionListenerDemo2 extends JFrameForTests {

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
		JPanel p = new JPanel(new GridLayout(0, 1, 5, 5));
		p.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		p.add(textField);
		JButton button = new JButton("test addActionListener");
		button.setActionCommand("buttonAction");
		p.add(button);
		workPanel.add(p, BorderLayout.NORTH);
		
		LOGGER.setUseParentHandlers(false);
		LOGGER.addHandler(new TextAreaLoggerHandler(textArea));
		
		button.addActionListener(this::myActionListener);

		/*
		EventQueue.invokeLater(() -> {
			JRootPane root = getRootPane();
			root.setJMenuBar(makeMenuBar());
			root.setDefaultButton(button);
		});
*/

		setJMenuBar(makeMenuBar());

		

	}

	void myActionListener(ActionEvent e) {
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

		item.addActionListener(this::myActionListener);
		item.setAccelerator(KeyStroke.getKeyStroke("shift 1"));
		item.setMnemonic(KeyEvent.VK_I);
		return menuBar;
	}

	private JTextField makeTextField() {
		JTextField field = new JTextField(20);
		String key = "beep";
		
		//бибикнет при нажатии shift+B, shift+N
		field.getInputMap().put(KeyStroke.getKeyStroke("shift B"), key);
		field.getActionMap().put(key, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Toolkit.getDefaultToolkit().beep();
			}
		});

		field.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_N && e.isShiftDown()) {
					//синоним .beep()
					UIManager.getLookAndFeel().provideErrorFeedback(e.getComponent());
				}
			}
		});
		field.addActionListener(this::myActionListener);
		
		return field;
	}

	public static void main(String[] args) throws Exception {

		SwingUtilities.invokeLater(() -> {
			ActionListenerDemo2 frame = new ActionListenerDemo2();
			frame.init();
		});

	}

}
