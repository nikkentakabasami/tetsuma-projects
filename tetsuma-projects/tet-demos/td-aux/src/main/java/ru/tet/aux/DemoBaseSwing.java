package ru.tet.aux;

public class DemoBaseSwing extends AbstractDemoBase {

	protected DemoFrameSwing frame;

	
	public void setSize(int width, int height) {
		frame.setSize(width, height);
		frame.setLocation(50, 50);
	}

	public void setSmallSize() {
		setSize(300, 500);
	}
	
	
	@Override
	public void init() {

		frame = new DemoFrameSwing();
		frame.initWithControlPanelAbove();
		this.controlPanel = frame.controlPanel;
		this.workPanel = frame.workPanel;
		this.textArea1 = frame.logFrame.textArea1;
		this.textArea2 = frame.logFrame.textArea2;

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
