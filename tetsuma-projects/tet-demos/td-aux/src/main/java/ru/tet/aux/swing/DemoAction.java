package ru.tet.aux.swing;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import ru.tet.demos.AbstractDemoBase;

public abstract class DemoAction extends AbstractAction {

	
	
	@Override
	public final void actionPerformed(ActionEvent e) {
  	try {
  		onAction(e, AbstractDemoBase.currentDemo);
		} catch (Exception e1) {
			AbstractDemoBase.currentDemo.logException(e1);
		}		
		
	}

	abstract void onAction(ActionEvent e, AbstractDemoBase demo) throws Exception;
	
}

