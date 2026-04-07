package ru.tet.aux.swing;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import ru.tet.javax.swing.aux.JControlPanelForTests;

public class AbstractDemoFrame extends JFrame {
	
	// инструментальная панель - содержит кнопки, метки, селекты и прочие рабочие элементы
	protected JControlPanelForTests controlPanel;

	// рабочая панель - содержит логи.
	protected JPanel workPanel;
	
	protected JTextPane textArea1;
	protected JTextPane textArea2;
	

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
	
	
	Style greenStyle;
	StyledDocument doc1;
	
	
	protected void initLog1Styles() {
		doc1 = (StyledDocument) textArea1.getDocument();
		greenStyle =doc1.addStyle("commentGreen", null);
		StyleConstants.setForeground(greenStyle, new Color(0, 120, 0));
	}
	
	public void hlGreen(int offset, int length) {
		doc1.setCharacterAttributes(offset, length, greenStyle, true);
	}
	
	/**
	 * Выделяем комменты зелёным
	 * @throws Exception
	 */
	public void hlComments() {
		
		String text;
		try {
			text = doc1.getText(0, doc1.getLength());
		} catch (BadLocationException e) {
			throw new RuntimeException(e);
		}
		
		int currIndex = 0;
		do {
			
			int ind = text.indexOf("//", currIndex);
			if (ind<0) {
				break;
			}
			
			int ind2 = text.indexOf("\n", ind);
			if (ind<0) {
				break;
			}
			int length = ind2-ind;
			
			hlGreen(ind, length);
			
			currIndex = ind2;
			
		} while (true);
		
	}	

	
}
