package ru.tet.javax.swing.panels;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;

import ru.tet.javax.swing.aux.JFrameForTests;

public class JTabbedPaneDemo2 extends JFrameForTests {

	JTabbedPane tabPane;
	
	JCheckBox jcbDVD;
	JCheckBox jcbScanner;
	JCheckBox jcbNtwrkRdy;

	JCheckBox jcbWordProc;
	JCheckBox jcbCompiler;
	JCheckBox jcbDatabase;

	JRadioButton jrbTower;
	JRadioButton jrbNotebook;
	JRadioButton jrbHandheld;

	@Override
	protected void doInit() {

		jrbTower = new JRadioButton("Tower");
		jrbNotebook = new JRadioButton("Notebook");
		jrbHandheld = new JRadioButton("Handheld");
		ButtonGroup bg = new ButtonGroup();
		bg.add(jrbTower);
		bg.add(jrbNotebook);
		bg.add(jrbHandheld);

		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(3, 1));
		panel1.setOpaque(true);

		panel1.add(jrbTower);
		panel1.add(jrbNotebook);
		panel1.add(jrbHandheld);

		jcbDVD = new JCheckBox("DVD Burner");
		jcbScanner = new JCheckBox("Scanner");
		jcbNtwrkRdy = new JCheckBox("Network Ready");

		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(3, 1));
		panel2.setOpaque(true);

		panel2.add(jcbDVD);
		panel2.add(jcbScanner);
		panel2.add(jcbNtwrkRdy);

		jcbWordProc = new JCheckBox("Word Processing");
		jcbCompiler = new JCheckBox("Program Development");
		jcbDatabase = new JCheckBox("Database");

		JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayout(3, 1));
		panel3.setOpaque(true);

		panel3.add(jcbWordProc);
		panel3.add(jcbCompiler);
		panel3.add(jcbDatabase);

		tabPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);

		tabPane.addTab("Style", panel1);
		tabPane.addTab("NotesOptions", panel2);
		tabPane.addTab("Software", panel3);

		tabPane.setBackgroundAt(1, Color.GREEN);

		//Задаёт клавишу перехода к вкладке (Alt+key). Соответствующая буква в заголовке подчёркивается.
		tabPane.setMnemonicAt(0, KeyEvent.VK_S);
		tabPane.setMnemonicAt(1, KeyEvent.VK_N);
		tabPane.setMnemonicAt(2, KeyEvent.VK_F);
		
		
		initWithControlPanelAbove(tabPane);
		
		String[] placements = {"TOP","LEFT","RIGHT","BOTTOM"};
		
		controlPanel.addDebugLabel("tab placement");
		controlPanel.addComboBox(placements, e->{
			int ind = controlPanel.comboBox1.getSelectedIndex();
			
			int placement = 0;
			switch (ind) {
				case 0: placement=JTabbedPane.TOP;break;
				case 1: placement=JTabbedPane.LEFT;break;
				case 2: placement=JTabbedPane.RIGHT;break;
				case 3: placement=JTabbedPane.BOTTOM;break;
			}
			
			
			tabPane.setTabPlacement(placement);
		});
		

	}

	public static void main(String[] args) throws IOException {
		JTabbedPaneDemo2 frame = new JTabbedPaneDemo2();
		frame.doInit();
	}

}
