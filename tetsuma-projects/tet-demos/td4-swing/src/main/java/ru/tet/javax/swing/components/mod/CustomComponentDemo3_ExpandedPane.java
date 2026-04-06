package ru.tet.javax.swing.components.mod;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.stream.Stream;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import ru.tet.javax.swing.aux.JFrameForTests;
import ru.tet.javax.swing.aux.comp.ExpansionPanelComponent;

public class CustomComponentDemo3_ExpandedPane extends JFrameForTests {

	ExpansionPanelComponent ep1,ep2,ep3;
	
	private void makePanels() {
	
        final JPanel p1 = new JPanel(new GridLayout(0, 1));
        Stream.of("1111", "222222")
            .map(JCheckBox::new)
            .forEach(b -> {
              b.setOpaque(false);
              p1.add(b);
            });
		ep1 = new ExpansionPanelComponent("myComp1", p1);		

		final JPanel p2 = new JPanel(new GridLayout(0, 1));
        Stream.of("Desktop", "My Network Places", "My Documents", "Shared Documents")
            .map(JLabel::new)
            .forEach(p2::add);
		ep2 = new ExpansionPanelComponent("myComp2", p2);		
		
		
		final JPanel p3 = new JPanel(new GridLayout(0, 1));
        ButtonGroup bg = new ButtonGroup();
        Stream.of("aaa", "bbb", "ccc", "ddd")
            .map(JRadioButton::new)
            .forEach(b -> {
              b.setSelected(p3.getComponentCount() == 0);
              b.setOpaque(false);
              p3.add(b);
              bg.add(b);
            });
		ep3 = new ExpansionPanelComponent("myComp3", p3);		
		
		
		
		
		
	}
	
	
	@Override
	protected void doInit() throws Exception {
		initWithControlPanelAbove();

		
		makePanels();
		
	    Box accordion = Box.createVerticalBox();
	    accordion.setOpaque(true);
	    accordion.setBackground(new Color(0xB4_B4_FF));
	    accordion.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));
	    
	    accordion.add(ep1);
	    accordion.add(Box.createVerticalStrut(5));
	    accordion.add(ep2);
	    accordion.add(Box.createVerticalStrut(5));
	    accordion.add(ep3);
	    accordion.add(Box.createVerticalStrut(5));
	    
	    accordion.add(Box.createVerticalGlue());

	    JScrollPane scroll = new JScrollPane(accordion);
	    scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	    scroll.getVerticalScrollBar().setUnitIncrement(25);		
		
	    
	    workPanel.setLayout(new BorderLayout()); 
		workPanel.add(scroll,BorderLayout.CENTER);
		
		
		
		
//		workPanel.add(ep1);
//		workPanel.add(ep2);
//		workPanel.add(ep3);
		
		
		
		
		
//	    workPanel.setLayout(new BorderLayout()); 
//	    workPanel.setLayout(new GridLayout(1,2)); 
//	    workPanel.setLayout(new BoxLayout(workPanel, BoxLayout.Y_AXIS));
	}

	public static void main(String[] args) throws Exception {

		SwingUtilities.invokeLater(() -> {
			CustomComponentDemo3_ExpandedPane frame = new CustomComponentDemo3_ExpandedPane();
			frame.init();
		});

	}

}
