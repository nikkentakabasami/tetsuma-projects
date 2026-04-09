package ru.tet.aux.swing;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ru.tet.javax.swing.aux.JControlPanelForTests;

public class AbstractDemoFrame extends JFrame {
	
	// инструментальная панель - содержит кнопки, метки, селекты и прочие рабочие элементы
	protected JControlPanelForTests controlPanel;

	// рабочая панель - содержит логи.
	protected JPanel workPanel;
	
	protected LogDemoTextPane textArea1;
	protected LogDemoTextPane textArea2;
	

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
	
	

	
}
