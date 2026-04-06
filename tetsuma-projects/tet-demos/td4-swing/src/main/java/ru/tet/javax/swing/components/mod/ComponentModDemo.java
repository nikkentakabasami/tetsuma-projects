package ru.tet.javax.swing.components.mod;

import javax.swing.SwingUtilities;

import ru.tet.javax.swing.aux.JFrameForTests;
import ru.tet.javax.swing.aux.comp.CustomLabel;

public class ComponentModDemo extends JFrameForTests {

	@Override
	protected void doInit() throws Exception {
		initWithControlPanelAbove();

		CustomLabel label = new CustomLabel("My Label");
		
		workPanel.add(label);
		
		
		
//	    workPanel.setLayout(new BorderLayout()); 
//	    workPanel.setLayout(new GridLayout(1,2)); 
//	    workPanel.setLayout(new BoxLayout(workPanel, BoxLayout.Y_AXIS));
	}

	


	public static void main(String[] args) throws Exception {

		SwingUtilities.invokeLater(() -> {
			ComponentModDemo frame = new ComponentModDemo();
			frame.init();
		});

	}

}
