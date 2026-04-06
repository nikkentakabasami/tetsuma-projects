package ru.tet.javax.swing.components;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;

import ru.tet.javax.swing.aux.DemoDataSamples;
import ru.tet.javax.swing.aux.JFrameForTests;

public class JTreeDemo extends JFrameForTests {

	@Override
	protected void doInit() throws Exception {
		initWithControlPanelAbove();
	    workPanel.setLayout(new BorderLayout()); 

		DefaultMutableTreeNode root = DemoDataSamples.makeTestTreeNodes();

		JTree jtree = new JTree(root);
		JScrollPane jscrlp = new JScrollPane(jtree);

		workPanel.add(jscrlp, BorderLayout.CENTER);

	}


	
	public static void main(String[] args) throws Exception {
		SwingUtilities.invokeLater(() -> {
			JTreeDemo frame = new JTreeDemo();
			frame.init();
		});
	}

}
