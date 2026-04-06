package ru.tet.javax.swing.misc;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import ru.tet.javax.swing.DemoTemplate;
import ru.tet.javax.swing.aux.JFrameForTests;

public class LookAndFeelsDemo extends JFrameForTests {

	@Override
	protected void doInit() throws Exception {
		initWithControlPanelAbove();

		for (UIManager.LookAndFeelInfo i : UIManager.getInstalledLookAndFeels()) {
			controlPanel.addButton(i.getName(), e -> {
				try {
					UIManager.setLookAndFeel(i.getClassName());
					SwingUtilities.updateComponentTreeUI(LookAndFeelsDemo.this);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			});
		}

	}

	public static void main(String[] args) throws Exception {

		SwingUtilities.invokeLater(() -> {
			LookAndFeelsDemo frame = new LookAndFeelsDemo();
			frame.init();
		});

	}

}
