package ru.tet.aux.swing;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import ru.tet.aux.AbstractDemoBase;

public abstract class DemoAction extends AbstractAction {

	
	
	@Override
	public final void actionPerformed(ActionEvent e) {

		
  	try {
  		onAction(e, AbstractDemoBase.currentDemo);
		} catch (Exception e1) {
			AbstractDemoBase.currentDemo.log2(e);
			e1.printStackTrace();
		}		
		
	}

	abstract void onAction(ActionEvent e, AbstractDemoBase demo) throws Exception;
	
}

