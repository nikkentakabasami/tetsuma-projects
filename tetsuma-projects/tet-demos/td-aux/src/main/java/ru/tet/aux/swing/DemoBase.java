package ru.tet.aux.swing;

import java.awt.Component;
import java.awt.Container;

import javax.swing.SwingUtilities;

import ru.tet.aux.AbstractDemoBase;
import ru.tet.swing.utils.TetSwingUtils;

/**
 * Основа для тестов java-классов.
 * 
 */
public class DemoBase extends AbstractDemoBase {

	@Override
	public final void init(AbstractDemoFrame generalFrame) {

		if (generalFrame != null) {
			frame = generalFrame;
			frame.clearContent();

		} else {
			DemoFrame fr = new DemoFrame();
			fr.initWithControlPanelAbove();
			frame = fr;
			frame.initLog1Styles();
		}

		this.controlPanel = frame.controlPanel;
		this.workPanel = frame.workPanel;
		this.textArea1 = frame.textArea1;
		this.textArea2 = frame.textArea2;

		sourceUtils.parseCurrentSources();
		sourceUtils.logCurrentSources();

		try {
			doInitControlPanel();
			doInit();
		} catch (Exception e) {
			e.printStackTrace();
		}


		//нормально не перерисовывается. Приходится так.
		if (generalFrame != null) {
			TetSwingUtils.refreshAllComponents(frame.controlPanel);
		}
		

	}

	
	
}
