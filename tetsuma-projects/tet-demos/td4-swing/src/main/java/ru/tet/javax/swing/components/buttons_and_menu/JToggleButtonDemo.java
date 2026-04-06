package ru.tet.javax.swing.components.buttons_and_menu;

import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

import ru.tet.javax.swing.DemoTemplate;
import ru.tet.javax.swing.aux.JFrameForTests;


/**
 * JToggleButton - предок JRadioButton и JCheckBox
 * 
 * @author tetsuma
 *
 */
public class JToggleButtonDemo extends JFrameForTests {

	JToggleButton toggleButton;

	@Override
	protected void doInit() throws Exception {
		initWithControlPanelAbove();

		demoImages.loadResourceImagesAndIcons(this);
		
		controlPanel.addDebugLabel();

		toggleButton = new JToggleButton("toggleButton");

		toggleButton.setIcon(demoImages.iconInfo);
		toggleButton.setSelectedIcon(demoImages.iconTick);
		toggleButton.setRolloverIcon(demoImages.iconUp);
		toggleButton.setPressedIcon(demoImages.iconDelete);
		
		
		toggleButton.addItemListener(e -> {
			controlPanel.label1.setText(toggleButton.isSelected()?"Button is on.":"Button is off.");
		});

		workPanel.add(toggleButton);

	}

	public static void main(String[] args) throws Exception {
		
		SwingUtilities.invokeLater(() -> {
			JToggleButtonDemo frame = new JToggleButtonDemo();
			frame.init();
		});

		
	}

}
