package ru.tet.javax.swing.panels;

import java.awt.Color;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import ru.tet.javax.swing.aux.DemoDataSamples;
import ru.tet.javax.swing.aux.JFrameForTests;

public class JScrollPaneDemo extends JFrameForTests {

	JLabel label;
	JScrollPane sp;
	
	@Override
	protected void doInit() {

		String html = DemoDataSamples.loadClassPathResourceAsText("labelHtmlPage.html");
		label = new JLabel(html);
		

		// Create a scroll notesEditPane and have it scroll the label.
		sp = new JScrollPane(label);
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		sp.setBorder(BorderFactory.createLineBorder(Color.red));
		
		
		
		initWithControlPanelAbove(sp);
		
		controlPanel.addButton("show horizontal bars", e -> {
			sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		});
		controlPanel.addButton("show viewport", e -> {
			sp.setViewportBorder(BorderFactory.createLineBorder(Color.BLUE));
		});

	}

	public static void main(String[] args) throws IOException {
		JScrollPaneDemo frame = new JScrollPaneDemo();
		frame.doInit();
	}



}
