package ru.tet.javax.swing.components;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeNode;

import ru.tet.javax.swing.aux.DemoDataSamples;
import ru.tet.javax.swing.aux.JFrameForTests;

public class JTreeDemo4_CellRenderer extends JFrameForTests {

	JTree jtree;
	DefaultTreeModel treeModel;

	private static final String MARK = "§"; // U+00A7

	@Override
	protected void doInit() throws Exception {
		initWithControlPanelAbove();

		controlPanel.addCheckbox("setAsksAllowsChildren", e -> {

			// Если true - для определения является ли узел листом будет использоваться
			// признак allowsChildren
			// Иначе это будет определяться автоматом
			treeModel.setAsksAllowsChildren(controlPanel.checkbox1.isSelected());
			jtree.repaint();
		});

		workPanel.setLayout(new BorderLayout());

		DefaultMutableTreeNode root = DemoDataSamples.makeTestTreeNodes2();
		treeModel = new DefaultTreeModel(root);

		jtree = new JTree(treeModel);

		
		TreeCellRenderer myCellRenderer = new DefaultTreeCellRenderer() {
			public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
				super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
				
				String s = makeNodeString((DefaultMutableTreeNode) value);
				setText(s);
				return this;
			};
		};
		jtree.setCellRenderer(myCellRenderer);
		
		/*
		Или можно так:
		final TreeCellRenderer defaultCellRenderer = jtree.getCellRenderer();
		
		TreeCellRenderer myCellRenderer = (tree, value, selected, expanded, leaf, row, hasFocus) -> {
			Component c = defaultCellRenderer.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
			if (value instanceof DefaultMutableTreeNode && c instanceof JLabel) {
				String s = makeNodeString((DefaultMutableTreeNode) value);
				((JLabel) c).setText(s);
			}
			return c;
		};
		*/
		


		
		
		JScrollPane jscrlp = new JScrollPane(jtree);

		workPanel.add(jscrlp, BorderLayout.CENTER);

	}

	private static String makeNodeString(DefaultMutableTreeNode node) {
		TreeNode[] path = node.getPath();
		
		if (path.length==1) {
			return (String)node.getUserObject();
		}
		
		String numbers = IntStream.range(1, path.length) // ignore the root node by skipping index 0
				.map(i -> 1 + path[i - 1].getIndex(path[i])).mapToObj(Objects::toString)
				.collect(Collectors.joining("."));
		
		
		return String.format("%s%s %s", MARK, numbers, node.getUserObject());
		
	}
	
	
	public static void main(String[] args) throws Exception {
		SwingUtilities.invokeLater(() -> {
			JTreeDemo4_CellRenderer frame = new JTreeDemo4_CellRenderer();
			frame.init();
		});
	}

}
