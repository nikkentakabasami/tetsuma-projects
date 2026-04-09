package ru.tet.aux.swing;

import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class LogDemoTextPane extends JTextPane {

	
	Style greenStyle;
	StyledDocument doc;
	

	public LogDemoTextPane() {
		
		doc = (StyledDocument) getDocument();
		greenStyle =doc.addStyle("commentGreen", null);
		StyleConstants.setForeground(greenStyle, new Color(0, 120, 0));
		
	}
	
	
	public void hlGreen(int offset, int length) {
		doc.setCharacterAttributes(offset, length, greenStyle, true);
	}
	
	

	/**
	 * Выделяем комменты зелёным
	 * @throws Exception
	 */
	public void hlComments() {
		
		String text;
		try {
			text = doc.getText(0, doc.getLength());
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
