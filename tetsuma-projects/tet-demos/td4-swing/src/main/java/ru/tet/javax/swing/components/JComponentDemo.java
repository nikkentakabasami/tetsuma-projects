package ru.tet.javax.swing.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import ru.tet.javax.swing.aux.JFrameForTests;

public class JComponentDemo extends JFrameForTests {

	JLabel l1,l2,l3;
	JPopupMenu popupMenu;
	
	JTextField tf1,tf2;
	

	
	@Override
	protected void doInit() throws Exception {
		initWithControlPanelAbove();
		
		controlPanel.addDebugLabel();
		controlPanel.addTextArea();
		workPanel.setLayout(new BoxLayout(workPanel, BoxLayout.Y_AXIS));
		
		
		createPopupMenu();

		makeLabels();
		makeTextFields();
		
		
//		l1.setAlignmentX(1);
		
//		tf1.setPreferredSize(new Dimension(100,100));
		tf1.setAlignmentX(0);
		tf1.setMaximumSize(new Dimension(200,100));
		tf1.setMinimumSize(new Dimension(200,50));
		
		
		workPanel.add(l1);
		workPanel.add(l2);
		workPanel.add(l3);
		workPanel.add(createDemoLabel("label 4", null));

		workPanel.add(tf1);
		workPanel.add(tf2);
		
		
		controlPanel.addButton("setBounds for l1", e -> {
			l1.setBounds(40, 40, 200, 200);
		});		
		
		
		l1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Point point = e.getPoint();
				boolean contains = l1.contains(point);
				controlPanel.textArea.append("point:"+point+" contains:"+contains+"\n");
				
			}
		});
		
		
	}

	
	
	/**
	 * Тестируем обработчики
	 */
	private void makeTextFields() {
		
		tf1 = new JTextField("make focus with alt+1");
		tf2 = new JTextField("make focus with alt+2");
		
	    ChangeFocusAction firstTabAction = new ChangeFocusAction("firstSelection", tf1);
	    ChangeFocusAction secondTabAction = new ChangeFocusAction("secondSelection", tf2);


	    
	    //ActionMap Связывает action-ы с их именами
	    workPanel.getActionMap().put(firstTabAction.getName(), firstTabAction);
	    workPanel.getActionMap().put(secondTabAction.getName(), secondTabAction);

	    //InputMap 	связывает имена action-ов с комбинациями клавиш
	    workPanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_1, Event.ALT_MASK), firstTabAction.getName());
	    workPanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_2, Event.ALT_MASK), secondTabAction.getName());
		
	}
	
	
	private void makeLabels() {
		l1 = createDemoLabel("label with tooltip", Color.GREEN);
		l2 = createDemoLabel("label with popup menu", Color.BLUE);
		l3 = createDemoLabel("label 3", Color.RED);
		
		//ToolTip
		l1.setToolTipText("my tooltip");
		
		//На компонент можно назначить всплывающее меню - JPopupMenu
		l2.setComponentPopupMenu(popupMenu);
		
	}
		
	
	JPopupMenu createPopupMenu() {

		popupMenu = new JPopupMenu();

		JMenuItem jmiCut = new JMenuItem("Cut");
		JMenuItem jmiCopy = new JMenuItem("Copy");
		JMenuItem jmiPaste = new JMenuItem("Paste");
		popupMenu.add(jmiCut);
		popupMenu.add(jmiCopy);
		popupMenu.add(jmiPaste);
		
		ActionListener al = e->{
			controlPanel.label1.setText(e.getActionCommand());
		};
		
		jmiCut.addActionListener(al);
		jmiCopy.addActionListener(al);
		jmiPaste.addActionListener(al);
		
		
		return popupMenu;
	}
		
	
	
	class ChangeFocusAction extends AbstractAction {

		  JComponent focusComp;

		  public ChangeFocusAction(String name, JComponent c) {
		    super(name);
		    focusComp = c;
		  }

		  public String getName() {
		    return (String) getValue(NAME);
		  }


		  @Override
		  public void actionPerformed(ActionEvent e) {
		    focusComp.grabFocus();
		  }


		}	
	
	
	
	
	public static void main(String[] args) throws Exception {
		JComponentDemo frame = new JComponentDemo();
		frame.doInit();
	}

}
