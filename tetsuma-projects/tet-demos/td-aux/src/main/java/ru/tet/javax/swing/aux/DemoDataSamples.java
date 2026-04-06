package ru.tet.javax.swing.aux;

import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.apache.commons.io.IOUtils;

import ru.tet.beans.SuIdNameModel;

public class DemoDataSamples {

	public static String sampleString = "Съешь ещё этих мягких французских булок, да выпей же чаю";
	
	
	public static String[] tableHeadings1 = { "From", "Address", "Subject", "Size" };

	public static Object[][] tableData1 = { { "Wendy", "Wendy@HerbSchildt.com", "Hello Herb", 287 },
			{ "Alex", "Alex@HerbSchildt.com", "Check this out!", 308 },
			{ "Hale", "Hale@HerbSchildt.com", "Found a bug", 887 },
			{ "Todd", "Todd@HerbSchildt.com", "Did you see this?", 223 },
			{ "Steve", "Steve@HerbSchildt.com", "I'm back", 357 },
			{ "Ken", "Ken@HerbSchildt.com", "Arrival time change", 512 } };

	public static String[] tableHeadings2 = { "String", "Integer", "Boolean" };

	public static Object[][] tableData2 = { { "aaa", 12, true }, { "bbb", 5, false }, { "CCC", 92, true },
			{ "DDD", 0, false } };

	
	public static String[] tableHeadings3 = { "propName", "propValue" };

	public static Object[][] tableData3 = { 
			{ "Wendy", "Wendy@HerbSchildt.com"},
			{ "Alex", "Alex@HerbSchildt.com"},
			{ "Hale", "Hale@HerbSchildt.com"}};
	
	
	public static String apples[] = { "Winesap", "Cortland", "Red Delicious", "Golden Delicious", "Gala", "Fuji",
			"Granny Smith", "Jonathan" };

	//расширенный список яблок
	public static List<String> makeApplesList(int size) {
		List<String> data = new ArrayList<>();
		
		for (int i = 0; i < apples.length; i++) {
			String apple = apples[i];
			
			for (int j = 0; j < size; j++) {
				long rand = Math.round(Math.random()*100);
				String val = apple+rand;
				data.add(val);
			}
		}
		return data;
	}
	
	
	public static List<SuIdNameModel> makeItemsList(int size) {
		List<SuIdNameModel> data = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			data.add(new SuIdNameModel(i, "my item " + i));

		}
		return data;
	}

	public static List<String> makeStringList(int size) {
		List<String> data = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			data.add("my item " + i);
		}
		return data;
	}

	/**
	 * простейший html, который можно использовать даже в JLabel
	 * @return
	 */
	public static String loadSimpleHtml() {
		String html = DemoDataSamples.loadClassPathResourceAsText("labelHtmlPage.html");
		return html;
	}
	
	
	public static String loadTestHtml() {
		String html = DemoDataSamples.loadClassPathResourceAsText("testHtmlPage.html");
		return html;
	}
		
	

	public static String loadTestText() {
		String html = DemoDataSamples.loadClassPathResourceAsText("testText.txt");
		return html;
	}

	public static String loadClassPathResourceAsText(String resName) {

		try {
//			URL resource = DemoDataSamples.class.getResource(resName);
			URL resource = DemoDataSamples.class.getClassLoader().getResource(resName);
			InputStream is = resource.openStream();

			StringWriter sw = new StringWriter();

			IOUtils.copy(is, sw);
			is.close();
			return sw.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

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

	
	public static void main(String[] args) {
		String testHtml = loadTestHtml();
		System.out.println(testHtml);
	}
	
}
