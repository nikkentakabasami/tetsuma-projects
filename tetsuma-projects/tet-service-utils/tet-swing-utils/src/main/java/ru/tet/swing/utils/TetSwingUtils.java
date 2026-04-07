package ru.tet.swing.utils;

import java.awt.Component;
import java.awt.Container;

public class TetSwingUtils {

	public static void refreshAllComponents(Container container) {
		container.revalidate();
		container.repaint();

		for (Component comp : container.getComponents()) {
			if (comp instanceof Container) {
				refreshAllComponents((Container) comp);
			} else {
				comp.repaint();
			}
		}
	}

}
