package ru.tet.javax.swing.components.buttons_and_menu;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import ru.tet.javax.swing.aux.JFrameForTests;

public class JPopupMenuDemo extends JFrameForTests {

	JPopupMenu popupMenu;
	
	
	JPopupMenu createPopupMenu() {

		popupMenu = new JPopupMenu();

		JMenuItem miCut = new JMenuItem("Вырезать");
		JMenuItem miCopy = new JMenuItem("Копировать");
		JMenuItem miPaste = new JMenuItem("Paste");
		popupMenu.add(miCut);
		popupMenu.add(miCopy);
		popupMenu.add(miPaste);
		
		miCut.setActionCommand("cut_action");
		miCopy.setActionCommand("copy_action");
		
		
		ActionListener al = e->{
			controlPanel.label1.setText(e.getActionCommand());
		};
		
		miCut.addActionListener(al);
		miCopy.addActionListener(al);
		miPaste.addActionListener(al);
		
		
		return popupMenu;
	}
	
	@Override
	protected void doInit() {
		initWithControlPanelAbove();
		
		controlPanel.addDebugLabel();
		
		
		createPopupMenu();
		
		//popup можно добавить так
		workPanel.setComponentPopupMenu(popupMenu);
		
		//или так
		controlPanel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				if (me.isPopupTrigger())
					popupMenu.show(me.getComponent(), me.getX()-20, me.getY()-10);
			}
		});
		
		
	}

	public static void main(String[] args) {
		JPopupMenuDemo frame = new JPopupMenuDemo();
		frame.doInit();
	}

}
