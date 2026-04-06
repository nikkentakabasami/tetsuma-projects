package ru.tet.javax.swing.components.buttons_and_menu;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import ru.tet.javax.swing.aux.JFrameForTests;

public class JMenuBarDemo2 extends JFrameForTests {

	//action commands
	public static final String AC_OPEN = "open";
	public static final String AC_CLOSE = "close";
	public static final String AC_SAVE = "save";
	public static final String AC_EXIT = "exit";
//	public static final String AC_ = "";
	
	
	JMenuBar menuBar;

	JToolBar toolBar;

	JPopupMenu popupMenu;

	DebugAction setAct;
	DebugAction clearAct;
	DebugAction resumeAct;

	MyActionListener myActionListener;

	@Override
	protected void doInit() {
		initWithControlPanelAbove();

		controlPanel.addDebugLabel();

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		myActionListener = new MyActionListener();
		
		makeFileMenu();
		makeActions();
		makeToolBar();
		makeOptionsMenu();
		makeHelpMenu();
		makeEditPUMenu();

		workPanel.setLayout(new BorderLayout());
		workPanel.add(toolBar, BorderLayout.NORTH);

		workPanel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				if (me.isPopupTrigger())
					popupMenu.show(me.getComponent(), me.getX(), me.getY());
			}

			public void mouseReleased(MouseEvent me) {
				if (me.isPopupTrigger())
					popupMenu.show(me.getComponent(), me.getX(), me.getY());
			}
		});


	}

	// Create the File menu with mnemonics and accelerators.
	void makeFileMenu() {
		
		JMenu jmFile = new JMenu("File");
		
		//Задание мнемонической клавиши (кнопка будет нажата при нажатии Alt+F )
		jmFile.setMnemonic(KeyEvent.VK_F);

		JMenuItem jmiOpen = new JMenuItem("Открыть");
		jmiOpen.setActionCommand(AC_OPEN);
		jmiOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));

		JMenuItem jmiClose = new JMenuItem("Закрыть", KeyEvent.VK_C);
		jmiClose.setActionCommand(AC_CLOSE);
		jmiClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));

		JMenuItem jmiSave = new JMenuItem("Сохранить", KeyEvent.VK_S);
		jmiSave.setActionCommand(AC_SAVE);
		jmiSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));

		JMenuItem jmiExit = new JMenuItem("Выйти", KeyEvent.VK_E);
		jmiExit.setActionCommand(AC_EXIT);
		jmiExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));

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

	// Create the NotesOptions menu.
	void makeOptionsMenu() {
		JMenu jmOptions = new JMenu("NotesOptions");

		// Create the Colors submenu.
		JMenu jmColors = new JMenu("Colors");

		// Use check boxes for colors. This allows
		// the user to select more than one color.
		JCheckBoxMenuItem jmiRed = new JCheckBoxMenuItem("Red");
		JCheckBoxMenuItem jmiGreen = new JCheckBoxMenuItem("Green");
		JCheckBoxMenuItem jmiBlue = new JCheckBoxMenuItem("Blue");

		// Add the items to the Colors menu.
		jmColors.add(jmiRed);
		jmColors.add(jmiGreen);
		jmColors.add(jmiBlue);
		jmOptions.add(jmColors);

		// Create the Priority submenu.
		JMenu jmPriority = new JMenu("Priority");

		// Use radio buttons for the priority setting.
		// This lets the menu show which priority is used
		// but also ensures that one and only one priority
		// can be selected at any one time. Notice thatt
		// the High radio button is initially selected.
		JRadioButtonMenuItem jmiHigh = new JRadioButtonMenuItem("High", true);
		JRadioButtonMenuItem jmiLow = new JRadioButtonMenuItem("Low");

		// Add the items to the Priority menu.
		jmPriority.add(jmiHigh);
		jmPriority.add(jmiLow);
		jmOptions.add(jmPriority);

		// Create a button group for the radio button
		// menu items.
		ButtonGroup bg = new ButtonGroup();
		bg.add(jmiHigh);
		bg.add(jmiLow);

		// Now, create a Debug submenu that goes under
		// the NotesOptions menu bar item. Use actions to
		// create the items.
		JMenu jmDebug = new JMenu("Debug");
		JMenuItem jmiSetBP = new JMenuItem(setAct);
		JMenuItem jmiClearBP = new JMenuItem(clearAct);
		JMenuItem jmiResume = new JMenuItem(resumeAct);

		// Add the items to the Debug menu.
		jmDebug.add(jmiSetBP);
		jmDebug.add(jmiClearBP);
		jmDebug.add(jmiResume);
		jmOptions.add(jmDebug);

		// Create the Reset menu item.
		JMenuItem jmiReset = new JMenuItem("Reset");
		jmOptions.addSeparator();
		jmOptions.add(jmiReset);

		// Finally, add the entire options menu to
		// the menu bar
		menuBar.add(jmOptions);

		// Add the action listeners for the NotesOptions menu,
		// except for those supported by the Debug menu.
		jmiRed.addActionListener(myActionListener);
		jmiGreen.addActionListener(myActionListener);
		jmiBlue.addActionListener(myActionListener);
		jmiHigh.addActionListener(myActionListener);
		jmiLow.addActionListener(myActionListener);
		jmiReset.addActionListener(myActionListener);
	}

	// Create the Help menu.
	void makeHelpMenu() {
		JMenu jmHelp = new JMenu("Help");

		// Add an icon to the About menu item.
		ImageIcon icon = new ImageIcon("AboutIcon.gif");

		JMenuItem jmiAbout = new JMenuItem("About", icon);
		jmiAbout.setToolTipText("Info about the MenuDemo program.");
		jmHelp.add(jmiAbout);
		menuBar.add(jmHelp);

		// Add action listener for About.
		jmiAbout.addActionListener(myActionListener);
	}

	void makeActions() {
		ImageIcon setIcon = new ImageIcon("images/about.gif");
		ImageIcon clearIcon = new ImageIcon("images/add_file.png");
		ImageIcon resumeIcon = new ImageIcon("images/add_file.png");
				
		
		setAct = new DebugAction("Set Breakpoint", setIcon, KeyEvent.VK_S, KeyEvent.VK_B, "Set a break point.");
		clearAct = new DebugAction("Clear Breakpoint", clearIcon, KeyEvent.VK_C, KeyEvent.VK_L, "Clear a break point.");
		resumeAct = new DebugAction("Resume", resumeIcon, KeyEvent.VK_R, KeyEvent.VK_R,"Resume execution after breakpoint.");

		clearAct.setEnabled(false);
	}

	void makeToolBar() {
		JButton jbtnSet = new JButton(setAct);
		JButton jbtnClear = new JButton(clearAct);
		JButton jbtnResume = new JButton(resumeAct);

		toolBar = new JToolBar("Breakpoints");

		toolBar.add(jbtnSet);
		toolBar.add(jbtnClear);
		toolBar.add(jbtnResume);
	}

	// Create the Edit popup menu.
	void makeEditPUMenu() {
		popupMenu = new JPopupMenu();

		// Create the popup menu items
		JMenuItem jmiCut = new JMenuItem("Cut");
		JMenuItem jmiCopy = new JMenuItem("Copy");
		JMenuItem jmiPaste = new JMenuItem("Paste");

		// Add the menu items to the popup menu.
		popupMenu.add(jmiCut);
		popupMenu.add(jmiCopy);
		popupMenu.add(jmiPaste);

		// Add the Edit popup menu action listeners.
		jmiCut.addActionListener(myActionListener);
		jmiCopy.addActionListener(myActionListener);
		jmiPaste.addActionListener(myActionListener);
	}

	class DebugAction extends AbstractAction {
		public DebugAction(String name, Icon image, int mnem, int accel, String tTip) {
			super(name, image);
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(accel, InputEvent.CTRL_DOWN_MASK));
			putValue(MNEMONIC_KEY, new Integer(mnem));
			putValue(SHORT_DESCRIPTION, tTip);
		}

		// Handle events for both the tool bar and the
		// Debug menu.
		public void actionPerformed(ActionEvent ae) {
			String comStr = ae.getActionCommand();

			controlPanel.label1.setText(comStr + " Selected");

			// Toggle the enabled status of the
			// Set and Clear Breakpoint options.
			if (comStr.equals("Set Breakpoint")) {
				clearAct.setEnabled(true);
				setAct.setEnabled(false);
			} else if (comStr.equals("Clear Breakpoint")) {
				clearAct.setEnabled(false);
				setAct.setEnabled(true);
			}
		}
	}

	class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			// Get the action command from the menu selection.
			String comStr = ae.getActionCommand();

			// If user chooses Exit, then exit the program.
			if (comStr.equals("Exit"))
				System.exit(0);

			// Otherwise, display the selection.
			controlPanel.label1.setText(comStr + " Selected");

		}

	}

	public static void main(String[] args) {
		JMenuBarDemo2 frame = new JMenuBarDemo2();
		frame.doInit();
	}

}
