package ru.tet.javax.swing.components;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import ru.tet.javax.swing.aux.DemoDataSamples;
import ru.tet.javax.swing.aux.JFrameForTests;

public class JTreeDemo2_Events extends JFrameForTests {

	@Override
	protected void doInit() throws Exception {
		initWithControlPanelAbove();
		workPanel.setLayout(new BorderLayout());
		controlPanel.addDebugLabel();

		DefaultMutableTreeNode root = DemoDataSamples.makeTestTreeNodes();

		JTree jtree = new JTree(root);
		JScrollPane jscrlp = new JScrollPane(jtree);

		jtree.setEditable(true);
		jtree.setRootVisible(false);

		TreeSelectionModel tsm = jtree.getSelectionModel();
		tsm.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

		jtree.addTreeExpansionListener(new TreeExpansionListener() {
			public void treeExpanded(TreeExpansionEvent tse) {
				TreePath tp = tse.getPath();
				controlPanel.label1.setText("Expansion: " + tp.getLastPathComponent());
			}

			public void treeCollapsed(TreeExpansionEvent tse) {
				TreePath tp = tse.getPath();
				controlPanel.label1.setText("Collapse: " + tp.getLastPathComponent());
			}
		});

		jtree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent tse) {
				TreePath tp = tse.getPath();
				controlPanel.label1.setText("Selection event: " + tp.getLastPathComponent());
			}
		});

		
		//edit listener
		jtree.getModel().addTreeModelListener(new TreeModelListener() {
			public void treeNodesChanged(TreeModelEvent tse) {
				TreePath tp = tse.getTreePath();
				TreeNode node = (TreeNode) tse.getChildren()[0];
				controlPanel.label1.setText("changed:" + tp + "/" + node.toString());
			}

			public void treeNodesInserted(TreeModelEvent tse) {
			}

			public void treeNodesRemoved(TreeModelEvent tse) {
			}

			public void treeStructureChanged(TreeModelEvent tse) {
			}
		});

		workPanel.add(jscrlp, BorderLayout.CENTER);

	}



	public static void main(String[] args) throws Exception {
		SwingUtilities.invokeLater(() -> {
			JTreeDemo2_Events frame = new JTreeDemo2_Events();
			frame.init();
		});
	}

}
