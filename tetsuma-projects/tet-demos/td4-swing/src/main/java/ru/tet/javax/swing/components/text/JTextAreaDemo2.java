package ru.tet.javax.swing.components.text;

import java.awt.BorderLayout;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicTextAreaUI;
import javax.swing.text.Caret;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.Keymap;
import javax.swing.text.NavigationFilter;

import ru.tet.aux.AuxTest;
import ru.tet.aux.swing.DemoBaseSwing;

public class JTextAreaDemo2 extends DemoBaseSwing {

	JTextArea textArea;

	@AuxTest(0)
	private void addDocumentListener() {

		textArea.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
//				controlPanel.textArea.setText(textArea.getText());
				log2("remove! e.offset:", e.getOffset(), "e.length", e.getLength());
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				log2("insert! e.offset:", e.getOffset(), "e.length", e.getLength());
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				log2("change! e.offset:", e.getOffset(), "e.length", e.getLength());
			}
		});

	}

	@Override
	protected void doInit() throws Exception {
		workPanel.setLayout(new BorderLayout());

		textArea = new JTextArea();
		workPanel.add(new JScrollPane(textArea));
		textArea.setText("row1\r\nrow2\r\nrow3");

		addDocumentListener();


	}

	@Override
	protected void doInitControlPanel() throws Exception {
		setSmallSize();
//		controlPanel.addTextArea();
		addTest1Button(null);
//		addTest2Button(null);
	}

	//textArea Dissection
	@Override
	public void test1() throws Exception {

		ActionMap actionMap = textArea.getActionMap();
		InputMap inputMap = textArea.getInputMap();
		log2(String.format("actionMap:%d, inputMap:%d", actionMap.getParent().size(), inputMap.getParent().size()));

		Keymap keymap = textArea.getKeymap();
		log2(keymap);

		Document document = textArea.getDocument();
		log2(document.getClass());

		Caret caret = textArea.getCaret();
		log2(caret.getClass());

		NavigationFilter nf = textArea.getNavigationFilter();
		log2(nf);

		Highlighter highlighter = textArea.getHighlighter();

		BasicTextAreaUI ui = (BasicTextAreaUI) textArea.getUI();
		log2(ui.getClass());

		DefaultEditorKit editorKit = (DefaultEditorKit) textArea.getUI().getEditorKit(textArea);

		log2(editorKit.getClass());

		editorKit.getViewFactory();

	}

	public static void main(String[] args) {
		DemoBaseSwing.run(JTextAreaDemo2.class);
	}

}
