package ru.tet.javax.swing.aux;

import java.util.List;

import javax.swing.JTextArea;
import javax.swing.SwingWorker;

public class TestSwingWorker1 extends SwingWorker<Integer, String> {

	JTextArea textArea;

	public TestSwingWorker1(JTextArea textArea) {
		this.textArea = textArea;
	}

	@Override
	protected Integer doInBackground() throws Exception {
		
		int progress = 0;
		
		for (int i = 1; i <= 5; i++) {
			Thread.sleep(1000);
			
			progress+=20;
			setProgress(progress);
			
			publish("Шаг " + i + " выполнен");
		}
		
		return 123;
	}

	@Override
	protected void process(List<String> chunks) {
		// Обновляем UI с промежуточными результатами
		for (String message : chunks) {
			textArea.append(message + "\n");
		}
	}

	@Override
	protected void done() {
		
		try {
			int result = get();
			textArea.append("Задача завершена! Результат: "+result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
