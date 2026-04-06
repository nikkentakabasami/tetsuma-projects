package ru.tet.javax.swing.components;

import javax.swing.JOptionPane;

import ru.tet.javax.swing.aux.JFrameForTests;

public class JOptionPaneDemo extends JFrameForTests {

	@Override
	protected void doInit() throws Exception {
		initWithControlPanelAbove();
		
		
		controlPanel.addDebugLabel();
		
		controlPanel.addButton("options dialog", e->{
			
			String[] connectOpts = { "Modem", "Wireless", "Satelite", "Cable" };

			int response = JOptionPane.showOptionDialog(JOptionPaneDemo.this, "Choose One", "Connection Type",
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, connectOpts, "Wireless");

			controlPanel.label1.setText("");
			if (response!=JOptionPane.CLOSED_OPTION) {
				controlPanel.label1.setText("Connect via "+connectOpts[response]);
			}
			
		});

		
		controlPanel.addButton("message dialog", e->{
	          JOptionPane.showMessageDialog(getRootPane(), "my error message", "Error", JOptionPane.ERROR_MESSAGE);
		});
		
		controlPanel.addButton("confirm dialog", e->{
			int response = JOptionPane.showConfirmDialog( getRootPane(), "Remove unused files?");
			
			switch(response) { 
			  case JOptionPane.YES_OPTION: 
				  controlPanel.label1.setText("You answered Yes."); break; 
			  case JOptionPane.NO_OPTION:  
				  controlPanel.label1.setText("You answered No."); break; 
			  case JOptionPane.CANCEL_OPTION:  
				  controlPanel.label1.setText("Cancel pressed."); break; 
			  case JOptionPane.CLOSED_OPTION:  
				  controlPanel.label1.setText("Dialog closed without response."); break; 
			} 
			
			
		});
		
		
		controlPanel.addButton("input dialog", e->{
			String response = JOptionPane.showInputDialog( "Enter Name"); 
			 
			if(response == null) 
				controlPanel.label1.setText("Dialog Cancelled or Closed"); 
			else if(response.length() == 0) 
				controlPanel.label1.setText("No string entered"); 
			else 
				controlPanel.label1.setText("Hi there " + response); 
		});
		
		
		
		
	}

	public static void main(String[] args) throws Exception {
		JOptionPaneDemo frame = new JOptionPaneDemo();
		frame.doInit();
	}

}
