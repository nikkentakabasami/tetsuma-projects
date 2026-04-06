package ru.tet.javax.swing.components.text;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.tet.javax.swing.aux.DemoDataSamples;
import ru.tet.javax.swing.aux.JFrameForTests;
import ru.tet.javax.swing.images.MissingIcon;

/**
 * Тестирование основных функций: вставка текста со стилем, изменение стилей
 * @author tetsuma
 *
 */
public class JTextPaneDemo1 extends JFrameForTests {

	LoggingTextPane textPane;
	ImageIcon iconInfo;


	public static int[] getRowAndColNo(LoggingTextPane textPane,int pos) {
		
		int[] result = {0,0};
		
		Element root = textPane.getDocument().getDefaultRootElement();
		
		for (int i = 0; i < root.getElementCount(); i++) {
			Element el = root.getElement(i);
			if (pos<el.getEndOffset()) {
				result[0]=i;
				result[1]=pos-el.getStartOffset();
				return result;
			}
		}
		return result;
	}
	
	@Override
	protected void doInit() throws Exception {
		initWithControlPanelAbove();

//		iconInfo = new ImageIcon(this.getClass().getResource("images/info.gif"));
		
		textPane = new LoggingTextPane();

		textPane.addCaretListener(e->{
			
			int pos = e.getDot();
			int[] rc = getRowAndColNo(textPane, pos);
			controlPanel.label1.setText("rowno:"+rc[0]+" colno:"+rc[1]);
			
			
		});
		
		JScrollPane scroll = new JScrollPane(textPane);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.getVerticalScrollBar().setUnitIncrement(25);

		controlPanel.addTextArea();
		controlPanel.newHorizontalBox();
		controlPanel.addDebugLabel();
		controlPanel.newHorizontalBox();
		controlPanel.addButton("mess", e -> {
			String mess = String.valueOf(Math.round(Math.random() * 1000));
			textPane.append(new Message(mess, MessageType.REGULAR));
		});

		controlPanel.addButton("err", e -> {
			String mess = String.valueOf(Math.round(Math.random() * 1000));
			textPane.append(new Message(mess, MessageType.ERROR));
		});
		controlPanel.addButton("blue", e -> {
			String mess = String.valueOf(Math.round(Math.random() * 1000));
			textPane.append(new Message(mess, MessageType.BLUE));
		});

		controlPanel.addButton("set error style", e -> {
			Style style = textPane.getStyledDocument().getStyle(MessageType.ERROR.toString());
			textPane.setCharacterAttributes(style, true);
		});

		controlPanel.addButton("setItalic", e -> {

			SimpleAttributeSet attributes = new SimpleAttributeSet();
			StyleConstants.setItalic(attributes, true);
			
			textPane.setCharacterAttributes(attributes, false);
		});

		controlPanel.newHorizontalBox();
		
		
		controlPanel.addButton("dump", e -> {
			
			AbstractDocument doc = (AbstractDocument)textPane.getDocument();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        PrintStream ps = new PrintStream(baos);
			doc.dump(ps);

			String s = baos.toString(StandardCharsets.UTF_8);
			controlPanel.textArea.setText(s);
			
			
		});

		controlPanel.addButton("print elements", e -> {
			controlPanel.textArea.setText("");
			printElement(textPane.getStyledDocument().getDefaultRootElement(), "");
		});
		
		controlPanel.addButton("setText", e -> {
			try {
				textPane.setContentType("html/text");
				String testHtml = DemoDataSamples.loadTestHtml();				
				textPane.setText(testHtml);
				
				//не работает
//				StringReader sr = new StringReader(DemoDataSamples.loadTestHtml());
//				textPane.read(sr, "html/text");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		});
		
		controlPanel.addButton("setPage", e -> {
			URL url = DemoDataSamples.class.getResource("/testHtmlPage.html");
			try {
				textPane.setPage(url);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		});

		
		
		controlPanel.addButton("insertComponent", e -> {
			JButton button = new JButton("Click Me!");		
			textPane.insertComponent(button);
		});
		
		controlPanel.addButton("insertIcon", e -> {
			
			Icon icon = new MissingIcon();
			textPane.insertIcon(icon);
		});
		
		controlPanel.newHorizontalBox();

		

		controlPanel.addButton("getParagraphElement", e -> {
			int offset = textPane.getCaret().getDot();
			Element el = textPane.getStyledDocument().getParagraphElement(offset);
			controlPanel.textArea.setText("");
			printElement(el, "");
//			Style style = textPane.getStyledDocument().getLogicalStyle(offset);
//			controlPanel.textArea.setText(String.format("caret.dot:%d style:%s",offset,style.getName()));
		});

		controlPanel.addButton("getCharacterElement", e -> {
			int offset = textPane.getCaret().getDot();
			Element el = textPane.getStyledDocument().getCharacterElement(offset);
			controlPanel.textArea.setText("");
			printElement(el, "");
//			Style style = textPane.getStyledDocument().getLogicalStyle(offset);
//			controlPanel.textArea.setText(String.format("caret.dot:%d style:%s",offset,style.getName()));
		});

		workPanel.setLayout(new BorderLayout());
		workPanel.add(scroll, BorderLayout.CENTER);

	}

	private void printElement(Element e, String offset) {

		AttributeSet attributes = e.getAttributes();

		
		Color color = StyleConstants.getForeground(attributes);
		boolean bold = StyleConstants.isBold(attributes);
		
		//почему то не засекает
		boolean italic = StyleConstants.isItalic(attributes);
		
		String text="";
		try {
			if (e.isLeaf()) {
				text=textPane.getText(e.getStartOffset(), e.getEndOffset()-e.getStartOffset()).replace("\n", "\\n");
				text=" '"+text+"' ";
			}
		} catch (BadLocationException e1) {
			throw new RuntimeException(e1);
		}
		
		String s = String.format("[%d,%d]<%s>%s(:%d:%d:%d) %s\n", e.getStartOffset(), e.getEndOffset(),e.getName(), text, color.getRed(), 
				color.getGreen(), color.getBlue(), bold?"bold":"", italic?"italic":"");
		controlPanel.textArea.append(offset + s);
		
		/*
		Enumeration<?> names = attributes.getAttributeNames();
		StringBuilder sb = new StringBuilder();
		while (names.hasMoreElements()) {

			String attrName = names.nextElement().toString();
			Object attr = attributes.getAttribute(attrName);
			sb.append(attrName).append("=").append(attr).append(";");
		}

		String s = String.format("[%d,%d] %s\n", e.getStartOffset(), e.getEndOffset(), sb.toString());
		controlPanel.textArea.append(offset + s);
*/

		for (int i = 0; i < e.getElementCount(); i++) {
			Element child = e.getElement(i);
			printElement(child, offset + "  ");
		}

	}

	public static void main(String[] args) throws Exception {

		SwingUtilities.invokeLater(() -> {
			JTextPaneDemo1 frame = new JTextPaneDemo1();
			frame.init();
		});

	}

}

class LoggingTextPane extends JTextPane {
	protected LoggingTextPane() {
		super();
	}

	@Override
	public void updateUI() {
		super.updateUI();
//    setEditable(false);
		EventQueue.invokeLater(this::initStyle);
	}

	public void append(Message m) {
		StyledDocument doc = getStyledDocument();
		int len = doc.getLength();
		String txt = m.getText() + "\n";
		Style style = doc.getStyle(m.getType().toString());
		try {
			doc.insertString(len, txt, style);
		} catch (BadLocationException ex) {
			// should never happen
			RuntimeException wrap = new StringIndexOutOfBoundsException(ex.offsetRequested());
			wrap.initCause(ex);
			throw wrap;
		}
	}

	private void initStyle() {
//		setBackground(Color.yellow);
		
//		StyledDocument doc = getStyledDocument();
//		Style defaultStyle = doc.getStyle(StyleContext.DEFAULT_STYLE);

		Style defaultStyle = getStyle(StyleContext.DEFAULT_STYLE);

		Style errorStyle = addStyle(MessageType.ERROR.toString(), defaultStyle);

		Style blueStyle = addStyle(MessageType.BLUE.toString(), defaultStyle);

		// Style regular = doc.addStyle(MessageType.REGULAR.toString(), def);
		// StyleConstants.setForeground(error, Color.BLACK);
		// Style error = doc.addStyle(ERROR, regular);
		StyleConstants.setForeground(blueStyle, Color.BLUE);

		StyleConstants.setForeground(errorStyle, Color.RED);
		StyleConstants.setFontFamily(errorStyle, "Arial");
		StyleConstants.setFontSize(errorStyle, 16);
		StyleConstants.setBold(errorStyle, true);

	}
}

enum MessageType {
	REGULAR, ERROR, BLUE
}

@Data
@AllArgsConstructor
class Message {
	String text;
	MessageType type;
}