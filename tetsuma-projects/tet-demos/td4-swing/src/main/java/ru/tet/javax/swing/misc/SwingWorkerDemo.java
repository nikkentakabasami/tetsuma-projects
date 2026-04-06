package ru.tet.javax.swing.misc;

import java.awt.GridBagLayout;

import javax.swing.Box;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import ru.tet.javax.swing.aux.GBC;
import ru.tet.javax.swing.aux.JFrameForTests;
import ru.tet.javax.swing.aux.TestSwingWorker1;

public class SwingWorkerDemo extends JFrameForTests {

	TestSwingWorker1 worker1;

	JProgressBar progressBar;

	@Override
	protected void doInit() throws Exception {
		initWithControlPanelAbove();

		progressBar = new JProgressBar();

		JTextArea textArea = new JTextArea(10, 30);
		JScrollPane scrollPane = new JScrollPane(textArea);

		worker1 = new TestSwingWorker1(textArea);

	    workPanel.setLayout(new GridBagLayout());
	    
		workPanel.add(progressBar, new GBC(0, 0));
	    
		workPanel.add(scrollPane, new GBC(0, 1));
		workPanel.add(Box.createVerticalGlue(), new GBC(0, 2,1,1,1,1));
		
		
//		workPanel.setLayout(new BorderLayout());
//		workPanel.add(scrollPane, BorderLayout.CENTER);

		controlPanel.addButton("Start Long Task", e -> {
			worker1.execute();
		});
		
		worker1.addPropertyChangeListener(evt -> {
		    if ("progress".equals(evt.getPropertyName())) {
		        int progress = (Integer) evt.getNewValue();
		        progressBar.setValue(progress);
		    }
		});		
		
		

	}

	public static void main(String[] args) throws Exception {

		SwingUtilities.invokeLater(() -> {
			SwingWorkerDemo frame = new SwingWorkerDemo();
			frame.init();
		});

	}

}

