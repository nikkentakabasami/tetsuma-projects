package ru.tet.aux.swing;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class LogDemoTextPane extends JTextPane {

	static String NL = "\n";

	Style greenStyle;
	StyledDocument doc;

	StringWriter logWriter;

	boolean bufferedMode;

	public LogDemoTextPane(boolean bufferedMode) {

		doc = (StyledDocument) getDocument();
		greenStyle = doc.addStyle("commentGreen", null);
		StyleConstants.setForeground(greenStyle, new Color(0, 120, 0));

		this.bufferedMode = bufferedMode;

		initWriters();

	}

	private void initWriters() {
		if (bufferedMode) {
			logWriter = new StringWriter();
		}
	}

	public void log(String s) {

		try {
			if (bufferedMode) {
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

	public void clear() {
		setText(null);
		initWriters();
	}

	public void newLine() {
		logNL(null);
		System.out.println();
	}

	public void flush() throws IOException {
		if (bufferedMode) {
			setText(logWriter.toString());
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

}
