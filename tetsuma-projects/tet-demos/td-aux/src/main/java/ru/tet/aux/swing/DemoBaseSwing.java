package ru.tet.aux.swing;

import ru.tet.aux.AbstractDemoBase;

public class DemoBaseSwing extends AbstractDemoBase {

	
	public void setSize(int width, int height) {
		frame.setSize(width, height);
		frame.setLocation(50, 50);
	}

	public void setSmallSize() {
		setSize(300, 500);
	}
	
	
	@Override
	public final void init(AbstractDemoFrame generalFrame) {

		if (generalFrame!=null) {
			frame = generalFrame;
			frame.clearContent();
		} else {
			DemoFrameSwing fr = new DemoFrameSwing();
			fr.initWithControlPanelAbove();
			frame = fr;
			frame.initLog1Styles();
		}		
		
		this.controlPanel = frame.controlPanel;
		this.workPanel = frame.workPanel;
		this.textArea1 = frame.textArea1;
		this.textArea2 = frame.textArea2;

		try {
			sourceUtils.parseCurrentSources();
			sourceUtils.logCurrentSources();
			doInitControlPanel();
			doInit();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
	}
	
	
}
