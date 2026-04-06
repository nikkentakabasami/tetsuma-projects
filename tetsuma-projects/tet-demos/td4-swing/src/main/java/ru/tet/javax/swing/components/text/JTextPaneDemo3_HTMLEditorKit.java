package ru.tet.javax.swing.components.text;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.html.HTMLEditorKit;

import ru.tet.javax.swing.aux.DemoDataSamples;
import ru.tet.javax.swing.aux.JFrameForTests;
import ru.tet.javax.swing.aux.MyHTMLEditorKit;
import ru.tet.javax.swing.images.MissingIcon;

/**
 * тестирование HTMLEditorKit - считывание/запись html.
 * 
 * @author tetsuma
 *
 */
public class JTextPaneDemo3_HTMLEditorKit extends JFrameForTests {

	JTextPane textPane;
	ImageIcon iconInfo;
	HTMLEditorKit htmlEditorKit;

	@Override
	protected void doInit() throws Exception {
		initWithControlPanelAbove();

		controlPanel.addTextArea();

		textPane = new JTextPane();
//		HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
		htmlEditorKit = new MyHTMLEditorKit();
				
		
		textPane.setEditorKit(htmlEditorKit);
		
		
		JScrollPane scroll = new JScrollPane(textPane);

		
		
		controlPanel.addButton("change font style", e -> {

			SimpleAttributeSet attributes = new SimpleAttributeSet();
			StyleConstants.setItalic(attributes, true);
			StyleConstants.setBold(attributes, true);
//			StyleConstants.setForeground(attributes, Color.RED);
			StyleConstants.setBackground(attributes, Color.RED);
			StyleConstants.setFontSize(attributes, 20);
			
			textPane.setCharacterAttributes(attributes, false);
		});
		
		controlPanel.addButton("insertIcon", e -> {
			
			Icon icon = new MissingIcon();
			textPane.insertIcon(icon);
		});		
		
		
		controlPanel.addButton("read html page", e -> {
			try {
				StringReader sr = new StringReader(DemoDataSamples.loadTestHtml());
//				textPane.read(sr, "html/text");
				textPane.read(sr, null);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		});
		
		controlPanel.addButton("write text to html", e -> {
			try {
				StringWriter sw = new StringWriter();
				textPane.write(sw);
				controlPanel.textArea.setText(sw.toString());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		});

		
		controlPanel.addButton("write selected text to html", e -> {
			try {
				
				int start = textPane.getSelectionStart();
				int length = textPane.getSelectionEnd()-textPane.getSelectionStart();
				
				if (length==0) {
					return;
				}
				
				StringWriter sw = new StringWriter();
				htmlEditorKit.write(sw,textPane.getDocument(), start, length);
				
				controlPanel.textArea.setText(sw.toString());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		});
		
		
		
		controlPanel.addButton("tests", e -> {
			try {
				String text = textPane.getDocument().getText(0, 20);
				controlPanel.textArea.setText(text);
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			}
		});
		
		
		
		
		
		controlPanel.addButton("setPage", e -> {
			URL url = JFrameForTests.class.getClassLoader().getResource("testHtmlPage.html");
			try {
				textPane.setPage(url);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		});

		workPanel.setLayout(new BorderLayout());
		workPanel.add(scroll, BorderLayout.CENTER);

		textPane.setText(DemoDataSamples.sampleString);

	}

	public static void main(String[] args) throws Exception {

		SwingUtilities.invokeLater(() -> {
			JTextPaneDemo3_HTMLEditorKit frame = new JTextPaneDemo3_HTMLEditorKit();
			frame.init();
		});

	}

}
