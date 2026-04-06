package ru.tet.javax.swing.components.text;

import java.awt.BorderLayout;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicTextAreaUI;
import javax.swing.text.Caret;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.Keymap;
import javax.swing.text.NavigationFilter;

import ru.tet.javax.swing.aux.JFrameForTests;

public class JTextAreaDemo1  extends JFrameForTests {

	JTextArea textArea;
	
	@Override
	protected void doInit() throws Exception {
		initWithControlPanelAbove();

	    workPanel.setLayout(new BorderLayout()); 
		
		textArea = new JTextArea();
		textArea.setText("row1\r\nrow2\r\nrow3");
		workPanel.add(new JScrollPane(textArea));

		controlPanel.addTextArea();
		
		textArea.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				controlPanel.textArea.setText(textArea.getText());
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				controlPanel.textArea.setText(textArea.getText());
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				controlPanel.textArea.setText(textArea.getText());
				
			}
		});
		
		
		
		textAreaDissection();
		
		
		
		
		
//	    workPanel.setLayout(new GridLayout(1,2)); 
//	    workPanel.setLayout(new BoxLayout(workPanel, BoxLayout.Y_AXIS));
		
//	    workPanel.setLayout(new GridBagLayout()); 
//		workPanel.add(label, new GBC(0, 0));
//		workPanel.add(Box.createVerticalGlue(), new GBC(0, 1,1,1,1,1));
		
		
	}

	
	private void textAreaDissection() {
		
		ActionMap actionMap = textArea.getActionMap();
		InputMap inputMap = textArea.getInputMap();
		System.out.println(String.format("actionMap:%d, inputMap:%d",actionMap.getParent().size(),inputMap.getParent().size()));
		
		
		Keymap keymap = textArea.getKeymap();
		System.out.println(keymap);
		
		Document document = textArea.getDocument();
		System.out.println(document.getClass());
		
		Caret caret = textArea.getCaret();
		System.out.println(caret.getClass());
		
		NavigationFilter nf = textArea.getNavigationFilter();
		System.out.println(nf);
		
		Highlighter highlighter = textArea.getHighlighter();
		
		
		BasicTextAreaUI ui = (BasicTextAreaUI)textArea.getUI();
		System.out.println(ui.getClass());
		
		DefaultEditorKit editorKit = (DefaultEditorKit)textArea.getUI().getEditorKit(textArea);
		
		System.out.println(editorKit.getClass());
		
		editorKit.getViewFactory();		
		
		
	}
	
	public static void main(String[] args) throws Exception {

		SwingUtilities.invokeLater(() -> {
			JTextAreaDemo1 frame = new JTextAreaDemo1();
			frame.init();
		});

	}

}
