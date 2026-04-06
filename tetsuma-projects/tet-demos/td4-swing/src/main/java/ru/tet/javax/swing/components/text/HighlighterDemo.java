package ru.tet.javax.swing.components.text;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

import ru.tet.javax.swing.aux.JFrameForTests;

public class HighlighterDemo extends JFrameForTests {

	JTextArea textArea;

	Highlighter.HighlightPainter painter;

	@Override
	protected void doInit() throws Exception {
		initWithControlPanelAbove();

		workPanel.setLayout(new BorderLayout());

		textArea = new JTextArea();
		textArea.setText("test\nwhatever there\nСтрока 3\nСтрока 4");
		workPanel.add(new JScrollPane(textArea));

		painter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);

		controlPanel.addButton("highlight", e -> {
			textArea.getHighlighter().removeAllHighlights();
			try {
				int start = textArea.getText().indexOf("whatever");
				int end = start + "whatever".length();
				Object hl = textArea.getHighlighter().addHighlight(start, end, painter);
				System.out.println(hl);
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			}
		});

		controlPanel.addButton("highlight odd rows", e -> {
			textArea.getHighlighter().removeAllHighlights();
			for (int line = 0; line < textArea.getLineCount(); line++) {
				if (line % 2 == 1) {
					try {
						int startOffset = textArea.getLineStartOffset(line);
						int endOffset = textArea.getLineEndOffset(line);
						textArea.getHighlighter().addHighlight(startOffset, endOffset - 1, painter);
					} catch (BadLocationException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

//	    workPanel.setLayout(new GridLayout(1,2)); 
//	    workPanel.setLayout(new BoxLayout(workPanel, BoxLayout.Y_AXIS));

//	    workPanel.setLayout(new GridBagLayout()); 
//		workPanel.add(label, new GBC(0, 0));
//		workPanel.add(Box.createVerticalGlue(), new GBC(0, 1,1,1,1,1));

	}

	public static void main(String[] args) throws Exception {

		SwingUtilities.invokeLater(() -> {
			HighlighterDemo frame = new HighlighterDemo();
			frame.init();
		});

	}

}
