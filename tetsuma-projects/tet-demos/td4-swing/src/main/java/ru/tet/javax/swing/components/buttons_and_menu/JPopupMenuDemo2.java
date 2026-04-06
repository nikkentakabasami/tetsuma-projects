package ru.tet.javax.swing.components.buttons_and_menu;

import javax.swing.JLabel;
import javax.swing.JPopupMenu;

import ru.tet.javax.swing.aux.JFrameForTests;

public class JPopupMenuDemo2 extends JFrameForTests {

	JPopupMenu popupMenu;
	
	
	JPopupMenu createPopupMenu() {

		popupMenu = new JPopupMenu();

		
		JLabel label = new JLabel(demoImages.missingIcon);
		popupMenu.add(label);
		
		return popupMenu;
	}
	
	@Override
	protected void doInit() {
		initWithControlPanelAbove();
		
		controlPanel.addDebugLabel();
		
		
		createPopupMenu();
		
		//popup можно добавить так
		workPanel.setComponentPopupMenu(popupMenu);
		
		
		
	}

	public static void main(String[] args) {
		JPopupMenuDemo2 frame = new JPopupMenuDemo2();
		frame.doInit();
	}

}
