package ru.tet.aux;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.text.Style;
import javax.swing.text.StyledDocument;

import ru.tet.javax.swing.aux.JControlPanelForTests;

public class DemoFrameSwing extends JFrame {

	public static int INSET = 150;

	LogDemoFrame logFrame;

	// инструментальная панель - содержит кнопки, метки, селекты и прочие рабочие элементы
	protected JControlPanelForTests controlPanel;

	// рабочая панель - полигон для компонентов
	protected JPanel workPanel;

	public DemoFrameSwing() {

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Мои тесты");
		

	}

	Style greenStyle;
	StyledDocument doc1;

	public void initWithControlPanelAbove() {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(INSET, INSET, screenSize.width - INSET * 2, screenSize.height - INSET * 2);

		workPanel = new JPanel();
		workPanel.setBorder(BorderFactory.createTitledBorder("workPanel"));
		workPanel.setLayout(new BorderLayout());

		controlPanel = new JControlPanelForTests();
		controlPanel.setMinimumSize(new Dimension(500, 200));
		workPanel.setMinimumSize(new Dimension(500, 300));

		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, controlPanel, workPanel);
		splitPane.setDividerLocation(200);
		setContentPane(splitPane);

		logFrame = new LogDemoFrame();
		logFrame.initWithControlPanelAbove();
		logFrame.setVisible(true);

		addKeyHandlers();
		
		//		setAlwaysOnTop(true);
		setVisible(true);

	}

	
	private void addKeyHandlers() {
		InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = getRootPane().getActionMap();

		inputMap.put(KeyStroke.getKeyStroke("F1"), "toBack");
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "toBack");
		actionMap.put("toBack", new AbstractAction() {
		  @Override
		  public void actionPerformed(ActionEvent e) {
				DemoFrameSwing.this.toBack();
				logFrame.toFront();
		  }
		});
		
		
		inputMap = logFrame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		actionMap = logFrame.getRootPane().getActionMap();

		

		
		inputMap.put(KeyStroke.getKeyStroke("F1"), "toBack");
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "toBack");
		actionMap.put("toBack", new AbstractAction() {
		  @Override
		  public void actionPerformed(ActionEvent e) {
				DemoFrameSwing.this.toFront();
//				logFrame.toBack();
		  }
		});			
		
	}
	
	
	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> {
			DemoFrameSwing f = new DemoFrameSwing();
			f.initWithControlPanelAbove();
		});

	}

}
