package ru.tet.javax.swing.panels;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import ru.tet.javax.swing.aux.DemoDataSamples;
import ru.tet.javax.swing.aux.JFrameForTests;

public class JSplitPaneDemo extends JFrameForTests {

	@Override
	protected void doInit() {

		String html = DemoDataSamples.loadClassPathResourceAsText("labelHtmlPage.html");
		
		JLabel label1 = new JLabel(" Left side: ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		JLabel label2 = new JLabel(html);

		label1.setMinimumSize(new Dimension(200, 30));
		label2.setMinimumSize(new Dimension(90, 30));

		JSplitPane jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, false, label1, new JScrollPane(label2));
		jsp.setOneTouchExpandable(true);

		initWithControlPanelAbove(jsp);

	}

	public static void main(String[] args) throws IOException {
		JSplitPaneDemo frame = new JSplitPaneDemo();
		frame.doInit();
	}

}
