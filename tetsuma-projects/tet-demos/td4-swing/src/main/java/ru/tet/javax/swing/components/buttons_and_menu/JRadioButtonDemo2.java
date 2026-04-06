package ru.tet.javax.swing.components.buttons_and_menu;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

import ru.tet.javax.swing.aux.JFrameForTests;

public class JRadioButtonDemo2 extends JFrameForTests {

	ButtonGroup bg;
	List<JRadioButton> radioButtons = new ArrayList<>();
	
	ActionListener actionListener;
	

	
	@Override
	protected void doInit() throws Exception {
		initWithControlPanelAbove();
		
		controlPanel.addDebugLabel();
		

		bg = new ButtonGroup();
		actionListener = e->{
			System.out.println(e.getSource());
			
			int ind = getSelectedIndex();
			
			controlPanel.label1.setText("Option selected: " + e.getActionCommand()+" - "+ind);
		};
		
		workPanel.setLayout(new GridLayout(5, 1));
		addRadioButton("Maximize Speed", "max");
		addRadioButton("Minimize Speed", "min");
		addRadioButton("Debug", "deb");
		
		
	}
	
	private int getSelectedIndex() {
	
		//метода для получения индекса нет, так что придётся получать его так
		OptionalInt selectedIndexOpt = IntStream.range(0, radioButtons.size())
			    .filter(i -> radioButtons.get(i).isSelected())
			    .findFirst();
		return selectedIndexOpt.getAsInt();
	}
	
	
	private JRadioButton addRadioButton(String title,String actionCommand) {
		JRadioButton rb = new JRadioButton("Maximize Speed", true);
		rb.setActionCommand(actionCommand);
		rb.addActionListener(actionListener);
		
		workPanel.add(rb);
		
		
		bg.add(rb);
		radioButtons.add(rb);
		
		return rb;
	}	

	public static void main(String[] args) throws Exception {
		
		SwingUtilities.invokeLater(() -> {
			JRadioButtonDemo2 frame = new JRadioButtonDemo2();
			frame.init();
		});
	}

}
