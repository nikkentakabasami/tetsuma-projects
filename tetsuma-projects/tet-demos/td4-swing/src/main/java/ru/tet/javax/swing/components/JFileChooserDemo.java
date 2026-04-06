package ru.tet.javax.swing.components;

import java.io.File;

import javax.swing.JFileChooser;

import ru.tet.javax.swing.aux.JFrameForTests;

public class JFileChooserDemo extends JFrameForTests {

	JFileChooser fileChooser;

	@Override
	protected void doInit() {
		initWithControlPanelAbove();

		fileChooser = new JFileChooser();

		controlPanel.addButton("choose file", e -> {
			int result = fileChooser.showOpenDialog(null);

			if (result == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				
				controlPanel.label1.setText("Selected file is: " + file.getName());
			} else {
				controlPanel.label1.setText("No file selected");
			}

		});
		controlPanel.addDebugLabel();

	}

	public static void main(String[] args) {
		JFileChooserDemo frame = new JFileChooserDemo();
		frame.doInit();
	}

}
