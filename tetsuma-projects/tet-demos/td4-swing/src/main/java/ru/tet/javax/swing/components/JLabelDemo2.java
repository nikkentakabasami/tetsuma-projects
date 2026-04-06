package ru.tet.javax.swing.components;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import ru.tet.javax.swing.aux.JFrameForTests;

/*
   GridLayout 
  Для показа группы компонентов равного размера.
  Показываются в таблице (в конструкторе указываем количество строк и столбцов)
 */
public class JLabelDemo2 extends JFrameForTests {

	@Override
	protected void doInit() {
		initWithControlPanelAbove();

		JLabel[] jlabs = new JLabel[9];

		jlabs[0] = new JLabel("Left, Top", SwingConstants.LEFT);
		jlabs[0].setVerticalAlignment(SwingConstants.TOP);

		jlabs[1] = new JLabel("Center, Top", SwingConstants.CENTER);
		jlabs[1].setVerticalAlignment(SwingConstants.TOP);

		jlabs[2] = new JLabel("Right, Top", SwingConstants.RIGHT);
		jlabs[2].setVerticalAlignment(SwingConstants.TOP);
		;

		jlabs[3] = new JLabel("Left, Center", SwingConstants.LEFT);

		jlabs[4] = new JLabel("Center, Center", SwingConstants.CENTER);

		jlabs[5] = new JLabel("Right, Center", SwingConstants.RIGHT);

		jlabs[6] = new JLabel("Left, Bottom", SwingConstants.LEFT);
		jlabs[6].setVerticalAlignment(SwingConstants.BOTTOM);

		jlabs[7] = new JLabel("Center, Bottom", SwingConstants.CENTER);
		jlabs[7].setVerticalAlignment(SwingConstants.BOTTOM);

		jlabs[8] = new JLabel("Right, Bottom", SwingConstants.RIGHT);
		jlabs[8].setVerticalAlignment(SwingConstants.BOTTOM);

		workPanel.setLayout(new GridLayout(3, 3, 4, 4));
		Border border = BorderFactory.createEtchedBorder();
		for (int i = 0; i < 9; i++) {
			jlabs[i].setBorder(border);
			workPanel.add(jlabs[i]);
		}

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JLabelDemo2 demo = new JLabelDemo2();
			demo.doInit();
		});
	}

}
