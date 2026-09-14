package ru.tet.aux.swing;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import ru.tet.demos.AbstractDemoBase;
import ru.tet.demos.DemoOptions;
import ru.tet.javax.swing.aux.JControlPanelForTests;

public class AbstractDemoFrame extends JFrame {
	
	// инструментальная панель - содержит кнопки, метки, селекты и прочие рабочие элементы
	protected JControlPanelForTests controlPanel;

	// рабочая панель - содержит логи.
	protected JPanel workPanel;
	
	protected LogDemoTextPane textArea1;
	protected LogDemoTextPane textArea2;

	DemoOptions options;
	int actionCounter;

	public AbstractDemoFrame(DemoOptions options) throws HeadlessException {
		if (options==null) {
			options = new DemoOptions();
		}
		this.options = options;
	}

	/**
	 * Очищает все добавленные компоненты и текст в логах.
	 * После этого можно добавлять новый контент для тестов.
	 */
	public void clearContent() {
		controlPanel.clearContent();
		textArea1.setText(null);
		textArea2.setText(null);
	}
	
	public AbstractDemoFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Мои тесты");
	}	
	
	/**
	 * Добавление дополнительных обработчиков нажатия клавиш
	 * @param ks
	 * @param action
	 */
	public void addKeyHandler(KeyStroke ks, ActionListener action) {
		InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = getRootPane().getActionMap();
		actionCounter++;
		String actionName = "addAction"+actionCounter;
		
		inputMap.put(ks, actionName);
		actionMap.put(actionName, new DemoAction() {
			void onAction(ActionEvent event, AbstractDemoBase demo) {
		  	try {
		  		action.actionPerformed(event);
				} catch (Exception e) {
					demo.logException(e);
				}
		  }
		});
	}
	

	
}
