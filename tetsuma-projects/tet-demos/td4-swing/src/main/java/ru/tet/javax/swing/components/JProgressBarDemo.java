package ru.tet.javax.swing.components;

import javax.swing.JProgressBar;

import ru.tet.javax.swing.aux.JFrameForTests;

public class JProgressBarDemo extends JFrameForTests {

	
	  JProgressBar progressBar1; 
	  JProgressBar progressBar2; 
	 
	
	
	@Override
	protected void doInit() throws Exception {
		initWithControlPanelAbove();

		controlPanel.addDebugLabel();
		controlPanel.addButton("push me", e->{

	        int hVal = progressBar1.getValue(); 
	        int vVal = progressBar2.getValue(); 
	 
	        if(hVal >= progressBar1.getMaximum()) 
	          return; 
	        else 
	          progressBar1.setValue(hVal + 10); 
	 
	        if(vVal >= progressBar1.getMaximum()) 
	          return; 
	        else 
	          progressBar2.setValue(vVal + 10); 
	 
	        String s = String.format("horizontal: %d, vertical: %d", hVal, vVal);
	        controlPanel.label1.setText(s);
			
		});
		
		
	    progressBar2 = new JProgressBar(JProgressBar.VERTICAL); 
	    progressBar1 = new JProgressBar(); 
	 
	    progressBar2.setStringPainted(true); 
	 
	    workPanel.add(progressBar1); 
	    workPanel.add(progressBar2); 
		
		
	}

	public static void main(String[] args) throws Exception {
		JProgressBarDemo frame = new JProgressBarDemo();
		frame.doInit();
	}

}
