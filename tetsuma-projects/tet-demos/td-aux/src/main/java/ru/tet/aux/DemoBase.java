package ru.tet.aux;

public class DemoBase extends AbstractDemoBase {

	protected DemoFrame frame;
	

	@Override
	public void init() {

		frame = new DemoFrame();
		frame.initWithControlPanelAbove();
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
