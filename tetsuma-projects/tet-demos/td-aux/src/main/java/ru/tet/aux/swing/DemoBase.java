package ru.tet.aux.swing;

import ru.tet.demos.AbstractDemoBase;
import ru.tet.demos.TestSources;
import ru.tet.swing.utils.TetSwingUtils;

/**
 * Основа для тестов java-классов.
 * Пример использования: DemoBaseTest1
 * 
 */
public class DemoBase extends AbstractDemoBase {

	@Override
	public final void init(AbstractDemoFrame generalFrame)  throws Exception {

		if (generalFrame != null) {
			frame = generalFrame;
			frame.clearContent();

		} else {
			DemoFrame fr = new DemoFrame(options());
			fr.initWithControlPanelAbove();
			frame = fr;
//			frame.initLog1Styles();
		}

		this.controlPanel = frame.controlPanel;
		this.workPanel = frame.workPanel;
		this.textArea1 = frame.textArea1;
		this.textArea2 = frame.textArea2;

		sourceUtils.parseCurrentSources();

		try {
			doInitControlPanel();
			doInit();

			if (options().logSources) {
				sourceUtils.logCurrentSources();
			}
			
			if (options().addTestButtons) {
				//добавляем кнопки для тестов
				for (int i = 1; i < sourceUtils.getSources().size(); i++) {
					TestSources sources = sourceUtils.getSources().get(i);
					if (!sources.isEmpty()) {
						addTestButton(null, i);
					}
				}
			}
			
		} catch (Exception e) {
			currentDemo.logException(e);
		}


		//нормально не перерисовывается. Приходится так.
		if (generalFrame != null) {
			TetSwingUtils.refreshAllComponents(frame.controlPanel);
		}
		

	}

	
	
}
