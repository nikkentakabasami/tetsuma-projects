package ru.tet.javax.swing.actions_events;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import ru.tet.javax.swing.aux.JFrameForTests;

/**
 * Задание обработчиков через ActionListener.
 * Альтернатива использованию Action.
 * 
 * Можно использовать только один обработчик на все элементы, получая событие через ActionCommand.
 * 
 * 
 * 
 * @author tetsuma
 *
 */
public class ActionListenerDemo extends JFrameForTests {

	//action commands
	public static final String AC_OPEN = "open";
	public static final String AC_CLOSE = "close";
	public static final String AC_SAVE = "save";
	public static final String AC_EXIT = "exit";
	
	
	JMenuBar menuBar;
	JToolBar toolBar;
	JPopupMenu popupMenu;


	MyActionListener myActionListener;

	@Override
	protected void doInit() {
		initWithControlPanelAbove();

		controlPanel.addDebugLabel();


		myActionListener = new MyActionListener();
		
		makeMenuBar();
		makePopupMenu();

		workPanel.setLayout(new BorderLayout());

		workPanel.setComponentPopupMenu(popupMenu);
		


	}

	void makeMenuBar() {
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu jmFile = new JMenu("File");
		
		//Задание мнемонической клавиши (кнопка будет нажата при нажатии Alt+F )
		jmFile.setMnemonic(KeyEvent.VK_F);

		JMenuItem jmiOpen = new JMenuItem("Открыть");
		jmiOpen.setMnemonic(KeyEvent.VK_O);
		jmiOpen.setActionCommand(AC_OPEN);
		
		//Задание комбинации клавиш: Ctrl+1
		jmiOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.CTRL_DOWN_MASK));
		
		
		JMenuItem jmiClose = new JMenuItem("Закрыть", KeyEvent.VK_C);
		jmiClose.setActionCommand(AC_CLOSE);
		jmiClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.CTRL_DOWN_MASK));

		JMenuItem jmiSave = new JMenuItem("Сохранить", KeyEvent.VK_S);
		jmiSave.setActionCommand(AC_SAVE);
		jmiSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, InputEvent.CTRL_DOWN_MASK));

		JMenuItem jmiExit = new JMenuItem("Выйти", KeyEvent.VK_E);
		jmiExit.setActionCommand(AC_EXIT);
		jmiExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, InputEvent.CTRL_DOWN_MASK));

		jmFile.add(jmiOpen);
		jmFile.add(jmiClose);
		jmFile.add(jmiSave);
		jmFile.addSeparator();
		jmFile.add(jmiExit);
		menuBar.add(jmFile);


		// Add the action listeners for the File menu.
		jmiOpen.addActionListener(myActionListener);
		jmiClose.addActionListener(myActionListener);
		jmiSave.addActionListener(myActionListener);
		jmiExit.addActionListener(myActionListener);
	}


	void makePopupMenu() {
		popupMenu = new JPopupMenu();

		JMenuItem jmiCut = new JMenuItem("Cut");
		JMenuItem jmiCopy = new JMenuItem("Copy");
		JMenuItem jmiPaste = new JMenuItem("Paste");

		popupMenu.add(jmiCut);
		popupMenu.add(jmiCopy);
		popupMenu.add(jmiPaste);

		jmiCut.addActionListener(myActionListener);
		jmiCopy.addActionListener(myActionListener);
		jmiPaste.addActionListener(myActionListener);
	}



	class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			String comStr = ae.getActionCommand();
			controlPanel.label1.setText(comStr + " Selected");
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			ActionListenerDemo frame = new ActionListenerDemo();
			frame.init();
		});
		
	}

}
