package ru.tet.javax.swing.panels;

import java.awt.Font;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JTabbedPane;

import ru.tet.javax.swing.aux.JFrameForTests;

public class JTabbedPaneDemo extends JFrameForTests {

	@Override
	protected void doInit() {

		JTabbedPane tabPane = new JTabbedPane();

		// Add tabs to the tabbed notesEditPane
		tabPane.addTab("File Manager", new JLabel(" This is the File Manager tab."));
		tabPane.addTab("Performance", new JLabel(" This is the Performance tab."));
		tabPane.addTab("Reports", new JLabel(" This is the Reports tab."));
		tabPane.addTab("Customize", new JLabel(" This is the Customize tab."));

		//шрифт закладок побольше
		Font tabFont = new Font("Arial", 0, 20);
		tabPane.setFont(tabFont);		
		
		initWithControlPanelAbove(tabPane);

	}

	public static void main(String[] args) throws IOException {
		JTabbedPaneDemo frame = new JTabbedPaneDemo();
		frame.doInit();
	}

}
