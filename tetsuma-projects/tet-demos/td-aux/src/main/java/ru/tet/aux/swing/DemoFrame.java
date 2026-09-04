package ru.tet.aux.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import ru.tet.aux.AbstractDemoBase;
import ru.tet.aux.DemoOptions;
import ru.tet.javax.swing.aux.JControlPanelForTests;

/**
 * Основа для тестов разных классов.
 */
public class DemoFrame extends AbstractDemoFrame {
	
	public static int INSET = 50;
	
	JComboBox demosComboBox;
	
	
	public DemoFrame(DemoOptions options) {
		super(options);
	}
	
	public void initWithControlPanelAbove() {
		setBounds();
		
		JComponent demoContentPane = createDemoContentPane();
		
		setContentPane(demoContentPane);
		
		setVisible(true);

	}	

	public void initComplex(String[] data, ActionListener listener) {
		setBounds();
		
		createComboBox(data, listener);
		JComponent demoContentPane = createDemoContentPane();

		
	    JPanel p = new JPanel(); 
	    p.setLayout(new BorderLayout()); 

	    p.add(demosComboBox, BorderLayout.NORTH); 
	    p.add(demoContentPane);
	    
		setContentPane(p);
		
		setVisible(true);

	}	
	
	void createComboBox(String[] data, ActionListener listener) {
		
		demosComboBox = new JComboBox(data);
		demosComboBox.addActionListener(listener);
		demosComboBox.setPreferredSize(new Dimension(800, 30));
		
	}
	
	protected void setBounds() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(INSET, INSET, screenSize.width - INSET * 2, screenSize.height - INSET * 2);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	
	protected JComponent createDemoContentPane() {
		
		workPanel = new JPanel();
		workPanel.setBorder(BorderFactory.createTitledBorder("workPanel"));

	    workPanel.setLayout(new BorderLayout()); 

	    
	    Font font = new Font("Serif", Font.PLAIN, 18);
	    
		textArea2 = new LogDemoTextPane(options);
		textArea2.setFont(font);
		
		JScrollPane sp2 = new JScrollPane(textArea2);

		textArea1 = new LogDemoTextPane(options);
		textArea1.setFont(font);
		JScrollPane sp1 = new JScrollPane(textArea1);
		sp1.setPreferredSize(new Dimension(600, 300));
		
		
		JSplitPane splitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, sp1, sp2);
		splitPane2.setDividerLocation(500);
		workPanel.add(splitPane2);	    
		
		controlPanel = new JControlPanelForTests();

		controlPanel.setMinimumSize(new Dimension(500, 100));
		workPanel.setMinimumSize(new Dimension(500, 300));

		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, controlPanel, workPanel);
		splitPane.setDividerLocation(200);
				
		addKeyHandlers();
		
		return splitPane;
		
	}
	
	private void addKeyHandlers() {
		InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = getRootPane().getActionMap();

		inputMap.put(KeyStroke.getKeyStroke("F1"), "prev");
		inputMap.put(KeyStroke.getKeyStroke("F2"), "next");
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "close");

		

		
		AbstractAction testAction = new DemoAction() {
			void onAction(ActionEvent e, AbstractDemoBase demo) {
		  	String name = e.getActionCommand();
		  	int testNo = Integer.parseInt(name.substring(name.length()-1));
		  	try {
		  		demo.test(testNo);
				} catch (Exception e1) {
					demo.log2(e);
					e1.printStackTrace();
				}
		  }
		};
		
		//для быстрого выполнения тестов
		for (int i = 0; i < 6; i++) {
			int testNo = i+1;
			String testName = "test"+testNo;
			inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD1+i, InputEvent.CTRL_DOWN_MASK), testName);
			actionMap.put(testName, testAction);		
			
		}
		
		actionMap.put("close", new DemoAction() {
			void onAction(ActionEvent e, AbstractDemoBase demo) throws Exception {
			  	demo.beforeClose();
				  DemoFrame.this.dispose();
			  }
			});		
		
		actionMap.put("next", new DemoAction() {
			void onAction(ActionEvent e, AbstractDemoBase demo) throws Exception {
			  if (demosComboBox==null) {
				  return;
			  }
			  int ind = demosComboBox.getSelectedIndex();
			  ind++;
			  if (ind>=demosComboBox.getItemCount()) {
				  return;
			  }
			  demosComboBox.setSelectedIndex(ind);
		  }
		});
		actionMap.put("prev", new DemoAction() {
			void onAction(ActionEvent e, AbstractDemoBase demo) throws Exception {
				  if (demosComboBox==null) {
					  return;
				  }
				  int ind = demosComboBox.getSelectedIndex();
				  ind--;
				  if (ind<0) {
					  return;
				  }
				  demosComboBox.setSelectedIndex(ind);
			  }
			});		
	}	
	
			
			
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(() -> {
			DemoFrame f = new DemoFrame(new DemoOptions());
			f.initWithControlPanelAbove();
		});
		
	}
	

}
