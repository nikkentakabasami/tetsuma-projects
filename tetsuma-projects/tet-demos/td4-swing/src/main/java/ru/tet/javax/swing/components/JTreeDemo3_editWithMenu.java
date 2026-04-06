package ru.tet.javax.swing.components;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import ru.tet.javax.swing.DemoTemplate;
import ru.tet.javax.swing.aux.JFrameForTests;

public class JTreeDemo3_editWithMenu extends JFrameForTests {

	JTree jtree;
	DefaultTreeModel treeModel;

	@Override
	protected void doInit() throws Exception {
		initWithControlPanelAbove();
		workPanel.setLayout(new BorderLayout());
		controlPanel.addDebugLabel();

		// покажет getDefaultTreeModel()
		jtree = new JTree();
		jtree.setComponentPopupMenu(new TreePopupMenu());

		JScrollPane jscrlp = new JScrollPane(jtree);
		workPanel.add(jscrlp, BorderLayout.CENTER);

		treeModel = (DefaultTreeModel) jtree.getModel();

		
		jtree.addTreeSelectionListener(e->showSelection());
		
	}

	private void showSelection() {
		TreePath[] tsp = jtree.getSelectionPaths();
		if (tsp!=null) {
			String s = Arrays.stream(tsp).map(p->p.toString()).collect(Collectors.joining(","));
			controlPanel.label1.setText(s);
		}
	}
	
	final class TreePopupMenu extends JPopupMenu {

		JTextField field;
		TreePath path;

		TreePopupMenu() {
			super();
			field = new JTextField(24);

			add("add").addActionListener(e -> addNode());
			add("add & reload").addActionListener(e -> addAndReload());
			add("edit").addActionListener(e -> edit());
			addSeparator();
			add("remove").addActionListener(e -> remove());
		}

		private void addNode() {
			DefaultMutableTreeNode parent = (DefaultMutableTreeNode) path.getLastPathComponent();
			DefaultMutableTreeNode child = new DefaultMutableTreeNode("New node");
			treeModel.insertNodeInto(child, parent, parent.getChildCount());
			jtree.scrollPathToVisible(new TreePath(child.getPath()));
		}

		private void addAndReload() {
			DefaultMutableTreeNode parent = (DefaultMutableTreeNode) path.getLastPathComponent();
			DefaultMutableTreeNode child = new DefaultMutableTreeNode("New node");
			parent.add(child);
			treeModel.reload(parent);
			jtree.scrollPathToVisible(new TreePath(child.getPath()));
		}

		private void edit() {
			Object node = path.getLastPathComponent();
			if (!(node instanceof DefaultMutableTreeNode)) {
				return;
			}
			DefaultMutableTreeNode leaf = (DefaultMutableTreeNode) node;
			field.setText(leaf.getUserObject().toString());
			int ret = JOptionPane.showConfirmDialog(jtree, field, "edit", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE);
			if (ret == JOptionPane.OK_OPTION) {
				treeModel.valueForPathChanged(path, field.getText());
				// leaf.setUserObject(str);
				// model.nodeChanged(leaf);
			}
		}

		private void remove() {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
			if (!node.isRoot()) {
				treeModel.removeNodeFromParent(node);
			}
		}

		//при показе меню
		@Override
		public void show(Component c, int x, int y) {
			path = jtree.getPathForLocation(x, y);
			if (path!=null) {
				jtree.setSelectionPath(path);
			}
			super.show(c, x, y);
		}
	}

	public static void main(String[] args) throws Exception {
		SwingUtilities.invokeLater(() -> {
			JTreeDemo3_editWithMenu frame = new JTreeDemo3_editWithMenu();
			frame.init();
		});
		
	}

}
