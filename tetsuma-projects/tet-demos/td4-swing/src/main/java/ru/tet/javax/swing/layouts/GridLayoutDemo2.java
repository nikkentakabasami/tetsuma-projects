package ru.tet.javax.swing.layouts;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import ru.tet.javax.swing.aux.JFrameForTests;

public class GridLayoutDemo2 extends JFrameForTests {

	@Override
	protected void doInit() throws Exception {
		initWithControlPanelAbove();

		
	    workPanel.setLayout(new BorderLayout()); 
		
	    JPanel inputsPanel = makeInputsPanel();
	    Box buttonPanel = createButtonPanel();
		
	    
	    JTextPane log = new JTextPane();
	    JScrollPane scroll = new JScrollPane(log);
	    scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	    scroll.getVerticalScrollBar().setUnitIncrement(25);
	    
	    
		
	    workPanel.add(inputsPanel, BorderLayout.NORTH);
	    workPanel.add(scroll);
	    workPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		
	}

	private Box createButtonPanel() {

		JButton ok = new JButton("Create new");
		JButton clear = new JButton("clear");

		Box box = Box.createHorizontalBox();
		box.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

		box.add(Box.createHorizontalGlue());
		box.add(ok);
		box.add(Box.createHorizontalStrut(5));
		box.add(clear);

		return box;

	}

	private JPanel makeInputsPanel() {

		SpinnerNumberModel model1 = new SpinnerNumberModel(0, 0, 6, 1);
		JSpinner spinner1 = new JSpinner(model1);
		JSpinner.NumberEditor editor1 = new JSpinner.NumberEditor(spinner1, "0");
	    editor1.getTextField().setEditable(false);
	    spinner1.setEditor(editor1);
		
		SpinnerNumberModel model2 = new SpinnerNumberModel(2, 0, 6, 1);
		JSpinner spinner2 = new JSpinner(model2);
		
		JPanel panel = new JPanel(new GridLayout(0, 4, 5, 5));
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 20, 50));
		
		panel.add(new JLabel("Number of backups to keep:", SwingConstants.RIGHT));
		panel.add(spinner1);
		panel.add(new JLabel("Number of backups to delete in order:", SwingConstants.RIGHT));
		panel.add(spinner2);
		panel.add(new JLabel("Some text:", SwingConstants.RIGHT));
		panel.add(new JTextField());
		
		
		panel.add(new JLabel("Total number of backups:", SwingConstants.RIGHT));
		panel.add(new JLabel("3", SwingConstants.RIGHT));

		
		return panel;
	}

	public static void main(String[] args) throws Exception {

		SwingUtilities.invokeLater(() -> {
			GridLayoutDemo2 frame = new GridLayoutDemo2();
			frame.init();
		});

	}

}

