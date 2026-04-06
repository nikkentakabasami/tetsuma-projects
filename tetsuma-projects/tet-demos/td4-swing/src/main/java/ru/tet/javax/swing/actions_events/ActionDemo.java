package ru.tet.javax.swing.actions_events;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import ru.tet.javax.swing.aux.JFrameForTests;

/**
 * Задание обработчиков через объекты Action.
 * 
 *  Action -обработчик, который может быть задан для многих компонентов, содержащий собственные атрибуты.
 *  При его задании:
 *	1)Action добавляется к компоненту как обработчик ActionListener
 *	2)Компонент конфигурирует ряд своих атрибутов, в соответствии с атрибутами Action-а
 *	3)Компонент задаёт PropertyChangeListener, чтобы менять свои атрибуты при изменении атрибутов Action-а
 *  Это позволяет удобно задать один обработчик для пункта меню и кнопки инструментальной панели.
 * 
 * 
 * @author tetsuma
 *
 */
public class ActionDemo extends JFrameForTests {

	JTextArea textArea;
	String newline = "\n";

	Action leftAction, middleAction, rightAction;

	JCheckBoxMenuItem[] cbmi;

	JToolBar toolBar;
	JMenuBar menuBar;
	JPopupMenu popupMenu;

	@Override
	protected void doInit() throws Exception {
		initWithControlPanelAbove();

		//При изменении атрибутов action-а - меняются соответствующие атрибуты всех связанных компонентов 
		controlPanel.addButton("change left action name", e->{
			leftAction.putValue(Action.NAME, "left name 2");
		});
		
		createActions();
		createToolBar();
		createMenuBar();
		createPopupMenu();
		
		workPanel.setLayout(new BorderLayout());

		textArea = new JTextArea(5, 30);
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		workPanel.add(scrollPane, BorderLayout.CENTER);
		
		workPanel.add(toolBar, BorderLayout.PAGE_START);
		setJMenuBar(menuBar);

		textArea.setComponentPopupMenu(popupMenu);
		
		
	}

	private void createActions() {
		leftAction = new MyAction("Go left", createClasspathIcon("Back24.gif"), "left",
				KeyEvent.VK_L, KeyEvent.VK_1);
		middleAction = new MyAction("Do something", createClasspathIcon("Up24.gif"), "middle",
				KeyEvent.VK_M, KeyEvent.VK_2);
		rightAction = new MyAction("Go right", createClasspathIcon("Forward24.gif"), "right",
				KeyEvent.VK_R, KeyEvent.VK_3);

	}


	JPopupMenu createPopupMenu() {

		popupMenu = new JPopupMenu();

		popupMenu.add(new JMenuItem(leftAction));
		popupMenu.add(new JMenuItem(middleAction));
		popupMenu.add(new JMenuItem(rightAction));
		
		return popupMenu;
	}	
	
	private JMenuBar createMenuBar() {

		menuBar = new JMenuBar();

		JMenu mainMenu = new JMenu("Main menu");

		mainMenu.add(new JMenuItem(leftAction));
		mainMenu.add(new JMenuItem(middleAction));
		mainMenu.add(new JMenuItem(rightAction));

		menuBar.add(mainMenu);

		//чекбуксы, меняющие активность action-ов
		JMenu ableMenu = new JMenu("Action State menu");
		ableMenu.add(new EnableActionMenuItem("First action enabled",leftAction));
		ableMenu.add(new EnableActionMenuItem("Second action enabled",middleAction));
		ableMenu.add(new EnableActionMenuItem("Third action enabled",rightAction));

		menuBar.add(ableMenu);
		return menuBar;
	}

	public JToolBar createToolBar() {
		toolBar = new JToolBar();

		toolBar.add(new JButton(leftAction));
		toolBar.add(new JButton(middleAction));
		toolBar.add(new JButton(rightAction));

		return toolBar;
	}


	class MyAction extends AbstractAction {
		public MyAction(String name, ImageIcon icon, String actionCommand, Integer mnemonic, int accel) {
			putValue(NAME, name);
			putValue(SMALL_ICON, icon);
			putValue(ACTION_COMMAND_KEY, actionCommand);
			putValue(MNEMONIC_KEY, mnemonic);
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(accel, InputEvent.CTRL_DOWN_MASK));
		}

		public void actionPerformed(ActionEvent e) {
			boolean ctrlDown = (e.getModifiers() & ActionEvent.CTRL_MASK)!=0;
			
			String s = ("Action event: '" + e.getActionCommand() 
					+ "' \n source: " + e.getSource().getClass().getName()
					+ " \n ctrlDown: " + ctrlDown
					+ " \n modifiers: " + e.getModifiers()
					+ newline);
			textArea.append(s);			
		}
	}

	static class EnableActionMenuItem extends JCheckBoxMenuItem {

		Action action;
		
		public EnableActionMenuItem(String text, Action action) {
			super(text);
			this.action = action;
			setSelected(true);
			
			addItemListener(e -> {
				action.setEnabled(isSelected());
			});
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		SwingUtilities.invokeLater(() -> {
			ActionDemo frame = new ActionDemo();
			frame.init();
		});
		
		
	}

}
