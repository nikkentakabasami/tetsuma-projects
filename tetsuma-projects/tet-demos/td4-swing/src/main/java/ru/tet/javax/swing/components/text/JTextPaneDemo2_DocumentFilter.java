package ru.tet.javax.swing.components.text;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import ru.tet.javax.swing.aux.DemoDataSamples;
import ru.tet.javax.swing.aux.JFrameForTests;

/**
 * Добавляем DocumentFilter, который ограничивает размер текста и выводит действия в лог. 
 * 
 * @author tetsuma
 *
 */
public class JTextPaneDemo2_DocumentFilter extends JFrameForTests {

	JTextPane textPane;
	ImageIcon iconInfo;

	@Override
	protected void doInit() throws Exception {
		initWithControlPanelAbove();

		controlPanel.addTextArea();

		textPane = new JTextPane();

		AbstractDocument doc = (AbstractDocument) textPane.getDocument();
		doc.setDocumentFilter(new DocumentSizeFilter(80, controlPanel.textArea));

		JScrollPane scroll = new JScrollPane(textPane);

		controlPanel.addButton("insert text", e -> {
			try {
				textPane.getDocument().insertString(textPane.getDocument().getLength(),DemoDataSamples.sampleString,null);
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			}
		});

		workPanel.setLayout(new BorderLayout());
		workPanel.add(scroll, BorderLayout.CENTER);

		textPane.setText(DemoDataSamples.sampleString);

	}

	public static void main(String[] args) throws Exception {

		SwingUtilities.invokeLater(() -> {
			JTextPaneDemo2_DocumentFilter frame = new JTextPaneDemo2_DocumentFilter();
			frame.init();
		});

	}

}

/**
 * Фильтр, запрещающий ввод текста больше заданной длины.
 * 
 * @author tetsuma
 *
 */
class DocumentSizeFilter extends DocumentFilter {
	int maxCharacters;
	JTextArea logTextArea;

	public DocumentSizeFilter(int maxChars, JTextArea logTextArea) {
		maxCharacters = maxChars;
		this.logTextArea = logTextArea;
	}

	@Override
	public void insertString(FilterBypass fb, int offset, String string, javax.swing.text.AttributeSet attr)
			throws BadLocationException {

		logTextArea.append(String.format("insert '%s'\n", string));

		int newLength = fb.getDocument().getLength() + string.length();

		if (newLength < maxCharacters)
			super.insertString(fb, offset, string, attr);
		else
			Toolkit.getDefaultToolkit().beep();

	}

	@Override
	public void replace(FilterBypass fb, int offset, int length, String text, javax.swing.text.AttributeSet attrs)
			throws BadLocationException {

		logTextArea.append(String.format("replace offset:%d length:%d    with '%s'\n", offset, length, text));

		int newLength = fb.getDocument().getLength() + text.length() - length;

		if (newLength < maxCharacters)
			super.replace(fb, offset, length, text, attrs);
		else
			Toolkit.getDefaultToolkit().beep();

	}

}
