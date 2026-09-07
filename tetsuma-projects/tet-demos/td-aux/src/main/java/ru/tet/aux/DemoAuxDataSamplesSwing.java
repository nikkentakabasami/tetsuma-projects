package ru.tet.aux;

import javax.swing.tree.DefaultMutableTreeNode;

public class DemoAuxDataSamplesSwing {


	// JTree data

	/**
	 * Тестовые данные для JTree
	 * 
	 * @return
	 */
	public static DefaultMutableTreeNode makeTestTreeNodes() {

		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Food");

		// Fruit
		DefaultMutableTreeNode fruit = new DefaultMutableTreeNode("Fruit");
		root.add(fruit);

		DefaultMutableTreeNode apples = new DefaultMutableTreeNode("Apples");
		fruit.add(apples);

		apples.add(new DefaultMutableTreeNode("Jonathan"));
		apples.add(new DefaultMutableTreeNode("Winesap"));

		DefaultMutableTreeNode pears = new DefaultMutableTreeNode("Pears");
		fruit.add(pears);

		pears.add(new DefaultMutableTreeNode("Bartlett"));

		// Vegetables
		DefaultMutableTreeNode veg = new DefaultMutableTreeNode("Vegetables");
		root.add(veg);

		veg.add(new DefaultMutableTreeNode("Beans"));
		veg.add(new DefaultMutableTreeNode("Corn"));
		veg.add(new DefaultMutableTreeNode("Potatoes"));
		veg.add(new DefaultMutableTreeNode("Rice"));

		return root;

	}

	public static DefaultMutableTreeNode makeTestTreeNodes2() {

		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
		DefaultMutableTreeNode parent;

		parent = new DefaultMutableTreeNode("colors");
		root.add(parent);
		parent.add(new DefaultMutableTreeNode("blue", false));
		parent.add(new DefaultMutableTreeNode("violet", false));
		parent.add(new DefaultMutableTreeNode("red", false));
		parent.add(new DefaultMutableTreeNode("yellow", false));

		parent = new DefaultMutableTreeNode("sports");
		root.add(parent);
		parent.add(new DefaultMutableTreeNode("basketball", false));
		parent.add(new DefaultMutableTreeNode("soccer", false));
		parent.add(new DefaultMutableTreeNode("football", false));
		parent.add(new DefaultMutableTreeNode("hockey", false));

		parent = new DefaultMutableTreeNode("food");
		root.add(parent);
		parent.add(new DefaultMutableTreeNode("hot dogs", false));
		parent.add(new DefaultMutableTreeNode("pizza", false));
		parent.add(new DefaultMutableTreeNode("ravioli", false));
		parent.add(new DefaultMutableTreeNode("bananas", false));

		parent = new DefaultMutableTreeNode("test");
		root.add(parent);

		return root;

	}
	
	
}
