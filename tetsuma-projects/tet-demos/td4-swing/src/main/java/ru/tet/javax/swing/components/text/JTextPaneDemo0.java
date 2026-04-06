package ru.tet.javax.swing.components.text;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.DefaultEditorKit.InsertTabAction;
import javax.swing.text.Keymap;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyledEditorKit;

import org.apache.commons.lang3.StringUtils;

import ru.tet.javax.swing.aux.JFrameForTests;

public class JTextPaneDemo0 extends JFrameForTests {

	JTextPane textPane;
	
	@Override
	protected void doInit() throws Exception {
		initWithControlPanelAbove();
		
	    workPanel.setLayout(new BorderLayout()); 
		
		
		textPane = new JTextPane();
		textPane.setForeground(Color.blue);
		textPane.setBackground(Color.cyan);

		JScrollPane scrollPane = new JScrollPane(textPane);
		scrollPane = new JScrollPane(textPane);
		workPanel.add(scrollPane, BorderLayout.CENTER);
		

		//задаём стиль для вновь введённого текста
		SimpleAttributeSet set = new SimpleAttributeSet();
		StyleConstants.setItalic(set, true);
		textPane.setCharacterAttributes(set, true);
		Font font = new Font("Verdana", Font.PLAIN, 18);
		textPane.setFont(font);
		textPane.setText("This is our text pane! ");
		
		//именованный стиль
		StyledDocument doc = (StyledDocument) textPane.getDocument();
		Style style = doc.addStyle("StyleName", null);
		StyleConstants.setBold(style, true);
		doc.insertString(doc.getLength(), "Demo text\n", style);		
		doc.insertString(doc.getLength(), "styless text", null);		
		
		
		Style greenStyle = doc.addStyle("green", null);
		StyleConstants.setForeground(greenStyle, new Color(0, 120, 0));
		doc.setCharacterAttributes(4, 10, greenStyle, true);
		
		
		//теперь клавиша TAB будет делать текст жирным
		Keymap keymap = textPane.getKeymap();
//		keymap.addActionForKeyStroke(KeyStroke.getKeyStroke("TAB"), new StyledEditorKit.BoldAction());
		keymap.addActionForKeyStroke(KeyStroke.getKeyStroke("TAB"), new MyTabAction("\t"));
		keymap.addActionForKeyStroke(KeyStroke.getKeyStroke("shift TAB"), new MyTabAction("  "));
		
		
		
//		InputMap shared = (InputMap)DefaultLookup.get(textPane, textPane.getUI(),"TextPane.focusInputMap");
		
		
		
//	    workPanel.setLayout(new GridLayout(1,2)); 
//	    workPanel.setLayout(new BoxLayout(workPanel, BoxLayout.Y_AXIS));
	}

	
	
	public static void main(String[] args) throws Exception {
		SwingUtilities.invokeLater(() -> {
			JTextPaneDemo0 frame = new JTextPaneDemo0();
			frame.init();
		});
		
	}

}

class MyTabAction extends InsertTabAction {
	
	String tabChars;
	
	public MyTabAction(String tabChars) {
		super();
		this.tabChars = tabChars;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JTextPane target = (JTextPane)getTextComponent(e);
        if (target != null) {
            if ((! target.isEditable()) || (! target.isEnabled())) {
                UIManager.getLookAndFeel().provideErrorFeedback(target);
                return;
            }
            
            int start = target.getSelectionStart();
            
            String s = target.getSelectedText();
            if (s.trim().length()==0) {
                s = tabChars;
            } else {
                s = tabShift(s, tabChars);
            }
            target.replaceSelection(s);
            
            int end = start+s.length();
    		SwingUtilities.invokeLater(() -> {
                target.select(start, end);
    		});
            
            
        }
		
		
		super.actionPerformed(e);
	}
	
	public static String tabShift(String s, String tabChars) {
		
		String[] lines = s.split("\n");
		
		for (int i = 0; i < lines.length; i++) {
			String line = lines[i];
			if (line.trim().length()==0) {
				continue;
			}
			
			lines[i] = tabChars+line;
		}
		s = StringUtils.join(lines,"\n");
		return s;
		
		
	}
	
}

