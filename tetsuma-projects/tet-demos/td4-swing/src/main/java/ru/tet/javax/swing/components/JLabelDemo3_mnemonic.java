package ru.tet.javax.swing.components;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import ru.tet.javax.swing.aux.JFrameForTests;

public class JLabelDemo3_mnemonic extends JFrameForTests {

	@Override
	protected void doInit() {
		initWithControlPanelAbove();

	    JLabel jlab1 = new JLabel("E-mail Address"); 
	    JLabel jlab2 = new JLabel("Name"); 
	    jlab1.setDisplayedMnemonic('e');  
	    jlab2.setDisplayedMnemonic('n');  
	 
	    JTextField jtf1 = new JTextField(20);  
	    JTextField jtf2 = new JTextField(20);  
	    jlab1.setLabelFor(jtf1); 
	    jlab2.setLabelFor(jtf2); 
	 
	    jtf1.setActionCommand("jtf1");  
	    jtf2.setActionCommand("jtf2");  
	  
	    jtf1.setAction(null);
	    
	    
	    workPanel.add(jlab1);  
	    workPanel.add(jtf1);   
	    workPanel.add(jlab2);  
	    workPanel.add(jtf2);   		
		
	    controlPanel.addTitleLabel("press alt+e and  alt+n to switch focus");
	    

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JLabelDemo3_mnemonic demo = new JLabelDemo3_mnemonic();
			demo.doInit();
		});
	}

}
