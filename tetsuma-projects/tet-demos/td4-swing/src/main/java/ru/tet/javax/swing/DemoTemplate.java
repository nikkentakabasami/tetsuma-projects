package ru.tet.javax.swing;

import javax.swing.SwingUtilities;

import ru.tet.javax.swing.aux.JFrameForTests;

//Шаблон для создания новых демок
public class DemoTemplate extends JFrameForTests {

	@Override
	protected void doInit() throws Exception {
		initWithControlPanelAbove();

//	    workPanel.setLayout(new BorderLayout()); 
//	    workPanel.setLayout(new GridLayout(1,2)); 
//	    workPanel.setLayout(new BoxLayout(workPanel, BoxLayout.Y_AXIS));
		
//	    workPanel.setLayout(new GridBagLayout()); 
//		workPanel.add(label, new GBC(0, 0));
//		workPanel.add(Box.createVerticalGlue(), new GBC(0, 1,1,1,1,1));
		
		
	}

	public static void main(String[] args) throws Exception {

		SwingUtilities.invokeLater(() -> {
			DemoTemplate frame = new DemoTemplate();
			frame.init();
		});

	}

}
