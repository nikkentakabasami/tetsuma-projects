package ru.tet.javax.swing.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import ru.tet.javax.swing.aux.DemoDataSamples;
import ru.tet.javax.swing.aux.GBC;
import ru.tet.javax.swing.aux.JFrameForTests;

public class JSpinnerDemo extends JFrameForTests {

	JSpinner spinner;
	JSpinner spinner2;
	JSpinner spinner3;

	JLabel label1;

	@Override
	protected void doInit() throws Exception {
		initWithControlPanelAbove();

		workPanel.setLayout(new BorderLayout());
//		workPanel.setLayout(new GridBagLayout());

		label1 = new JLabel("-");

		// Для выбора целочисленного значения
		SpinnerNumberModel spm1 = new SpinnerNumberModel(1, 1, 10, 1); // value, minimum, maximum, stepSize

		spinner = new JSpinner(spm1);

		spinner.addChangeListener(ce -> {

			Integer bSize = (Integer) spinner.getValue();

			label1.setText("border width:" + bSize);
			label1.setBorder(BorderFactory.createLineBorder(Color.BLACK, bSize));

		});
		spinner.setPreferredSize(new Dimension(300, 30));
		label1.setPreferredSize(new Dimension(300, 30));

		// Для выбора дат
		GregorianCalendar g = new GregorianCalendar();
		g.add(Calendar.MONTH, -1);
		Date begin = g.getTime();
		g.add(Calendar.YEAR, 2);
		Date end = g.getTime();

		// Величина приращения может не приниматься во внимание в некоторых стилях!!!
//	    SpinnerDateModel spm2 = new SpinnerDateModel(new Date(), begin, end, Calendar.DAY_OF_MONTH); 
		SpinnerDateModel spm2 = new SpinnerDateModel(new Date(), begin, end, Calendar.MONTH);

		spinner2 = new JSpinner(spm2);

		JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner2, "dd.MM.yyyy");
		spinner2.setEditor(editor);

		spinner2.addChangeListener(ce -> {
			Date date = (Date) spinner2.getValue();
			label1.setText(" Selected date is: " + date + " ");
		});

		SpinnerListModel spm3 = new SpinnerListModel(Arrays.asList(DemoDataSamples.tableHeadings1));
		spinner3 = new JSpinner(spm3);

		JPanel np = new JPanel(new GridLayout(0, 2, 20, 20));

		np.add(spinner);
		np.add(label1);
		np.add(spinner2);
		np.add(Box.createVerticalBox());
		np.add(spinner3);
		np.add(Box.createVerticalBox());
		workPanel.add(np, BorderLayout.NORTH);


	}

	public static void main(String[] args) throws Exception {

		SwingUtilities.invokeLater(() -> {
			JSpinnerDemo frame = new JSpinnerDemo();
			frame.init();
		});

	}

}
