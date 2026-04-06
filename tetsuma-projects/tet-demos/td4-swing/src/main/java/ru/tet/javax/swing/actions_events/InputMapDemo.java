package ru.tet.javax.swing.actions_events;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import ru.tet.javax.swing.aux.JFrameForTests;

public class InputMapDemo extends JFrameForTests {

//    private JPanel buttonPanel;
	
	
	@Override
	protected void doInit() throws Exception {
		initWithControlPanelAbove();

//		workPanel.setLayout(new BoxLayout(workPanel, BoxLayout.Y_AXIS));
		
		controlPanel.addButton("asd sdf asdfdddddddd", null);
		
//	    workPanel.setLayout(new GridLayout(2,3)); 

        ColorAction yellowAction = new ColorAction("Yellow (ctrl Y)", new ImageIcon("green-ball.gif"), Color.YELLOW);
        ColorAction blueAction = new ColorAction("Blue (ctrl B)", new ImageIcon("blue-ball.gif"), Color.BLUE);
        ColorAction greenAction = new ColorAction("Green (ctrl G)", new ImageIcon("green-ball.gif"), Color.GREEN);

        
        Dimension buttonSize = new Dimension(150, 50);
        JButton b = new JButton(yellowAction);
        b.setPreferredSize(buttonSize);
        workPanel.add(b);

        b = new JButton(blueAction);
        b.setPreferredSize(buttonSize);
        workPanel.add(b);

        b = new JButton(greenAction);
        b.setPreferredSize(buttonSize);
        workPanel.add(b);
        
        
//        InputMap imap = workPanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        InputMap imap = workPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        
        
        imap.put(KeyStroke.getKeyStroke("ctrl Y"), "panel.yellow");
        imap.put(KeyStroke.getKeyStroke("ctrl B"), "panel.blue");
        imap.put(KeyStroke.getKeyStroke("ctrl G"), "panel.green");

        ActionMap amap = workPanel.getActionMap();
        amap.put("panel.yellow", yellowAction);
        amap.put("panel.blue", blueAction);
        amap.put("panel.green", greenAction);

//        workPanel.add(new NotHelloWorldComponent());
//        for(UIManager.LookAndFeelInfo i : UIManager.getInstalledLookAndFeels()) {
//            themeButton(i.getName(), i.getClassName());
//        }		
		
		
		
		
		
		
	}

	
	
	
	
	
    public class ColorAction extends AbstractAction {
        public ColorAction(String name, Icon icon, Color c) {
            putValue(Action.NAME, name);
            putValue(Action.SMALL_ICON, icon);
            putValue("color", c);
            putValue(Action.SHORT_DESCRIPTION, "Set panel color to " + name.toLowerCase());
        }

        public void actionPerformed(ActionEvent event) {
            Color c = (Color) getValue("color");
            workPanel.setBackground(c);
        }
    }	
	
	
	public static void main(String[] args) throws Exception {

		SwingUtilities.invokeLater(() -> {
			InputMapDemo frame = new InputMapDemo();
			frame.init();
		});

	}

}
