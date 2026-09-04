package ru.tet.aux.swing;

import java.awt.Color;
import java.io.StringWriter;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import ru.tet.aux.DemoOptions;

public class LogDemoTextPane extends JTextPane {

	DemoOptions options;
	

	final static String NL = "\n";

//	static final char BLUE_CHAR = '\u0086';
//	static final char GREEN_CHAR = '\u0087';
	
	
	//спецсимволы для выделения текста
	static final char BLUE_CHAR = '\u270E';
	static final char GREEN_CHAR = '\u270F';
	static final char BOLD_CHAR = '\u2710';

	
	public enum LogStyle {
		BLUE("stBlue",BLUE_CHAR), GREEN("stGreen",GREEN_CHAR), BOLD("stBold",BOLD_CHAR);

		String styleName;
		char markerChar;
		
		private LogStyle(String styleName, char markerChar) {
			this.styleName = styleName;
			this.markerChar = markerChar;
		}
		
	}
	
	
	
	Style greenStyle;
	Style blueStyle;
	Style boldStyle;
	
	
	StyledDocument doc;

	StringWriter logWriter;

	public LogDemoTextPane(DemoOptions options) {

		this.options = options;
		
		doc = (StyledDocument) getDocument();
		
		greenStyle = doc.addStyle(LogStyle.GREEN.styleName, null);
		StyleConstants.setForeground(greenStyle, new Color(0, 120, 0));

		blueStyle = doc.addStyle(LogStyle.BLUE.styleName, null);
		StyleConstants.setForeground(blueStyle, Color.BLUE);
		StyleConstants.setForeground(blueStyle, new Color(0x4c,0x6a,0xc3));
		//4c6ac3
		
		boldStyle = doc.addStyle(LogStyle.BOLD.styleName, null);
		StyleConstants.setBold(boldStyle, true);
		
		
		initWriters();

//		initStyles();

	}

//	private void initStyles() {
//
//		Style defaultStyle = getStyle(StyleContext.DEFAULT_STYLE);
//
//		Style blueStyle = addStyle(TextStyle.BLUE.toString(), defaultStyle);
//		StyleConstants.setForeground(blueStyle, Color.BLUE);
//
//	}

	private void initWriters() {
		if (options.bufferLogs) {
			logWriter = new StringWriter();
		}
	}

	public void log(String s) {

		try {
			if (options.bufferLogs) {
				logWriter.write(s);
			} else {
				append(s);
			}
			System.out.print(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void logNL(String s) {
		if (s == null) {
			s = NL;
		} else {
			s += NL;
		}
		log(s);
	}

	public void log(String s, LogStyle style) {
		log(style.markerChar+s+style.markerChar);
	}
	
	public void logBlue(String s) {
		log(s, LogStyle.BLUE);
	}
	
	public void logGreen(String s) {
		log(s, LogStyle.GREEN);
	}
	
	
	public void clear() {
		setText(null);
		initWriters();
	}

	public void newLine() {
		logNL(null);
		System.out.println();
	}

	public void flush() throws Exception {
		if (options.bufferLogs) {
			setText(logWriter.toString());

			hlStyle(blueStyle, LogStyle.BLUE.markerChar);
			hlStyle(greenStyle, LogStyle.GREEN.markerChar);
			hlStyle(boldStyle, LogStyle.BOLD.markerChar);
			
			initWriters();
		}
	}

	public void append(String s) {
		Document document = getDocument();
		try {
			document.insertString(document.getLength(), s, null);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	
	public void removeChar(char c1) throws BadLocationException {
		
		String text = getText();
		
		int currIndex = text.length();
		do {
			
			int ind = text.lastIndexOf(c1,currIndex);
			if (ind < 0) {
				break;
			}
			doc.remove(ind, 1);
			currIndex = ind-1;
		} while (true);		
		
		text = getText();
		System.out.println(text);
		
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
			if (ind < 0) {
				break;
			}

			int ind2 = text.indexOf("\n", ind);
			if (ind2 < 0) {
				break;
			}
			int length = ind2 - ind;

			hlGreen(ind, length);

			currIndex = ind2;

		} while (true);

	}
	
	public void hlStyle(Style style, char c1) throws BadLocationException {

		String text;
		try {
			text = doc.getText(0, doc.getLength());
		} catch (BadLocationException e) {
			throw new RuntimeException(e);
		}

		int currIndex = 0;
		do {

			int ind = text.indexOf(c1, currIndex);
			if (ind < 0) {
				break;
			}

			int ind2 = text.indexOf(c1, ind+1);
			if (ind2 < 0) {
				break;
			}
			int length = ind2 - ind;

			doc.setCharacterAttributes(ind, length, style, true);

			currIndex = ind2+1;

		} while (true);

		if (options.removeMarkerChars) {
			removeChar(c1);
		}
		
		
	}	
	

}
