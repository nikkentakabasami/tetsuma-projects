package ru.tet.syntax.datatypes.string;

import java.text.ChoiceFormat;
import java.text.Format;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import ru.tet.aux.swing.DemoBase;
import ru.tet.demos.AuxTest;

public class D_MessageFormat2 extends DemoBase {


	public void test1() throws Exception {
		/*
		java.text.ChoiceFormat
		Позволяет менять вид параметра в зависимости от значения переменной
		 */

		double[] filelimits = { 0, 2, 5 };
		String[] filepart = { "{0} раз", "{0} раза", "{0} раз" };
		ChoiceFormat fileform = new ChoiceFormat(filelimits, filepart);

		MessageFormat form = new MessageFormat("Я могу {1} {0}.");
		form.setFormatByArgumentIndex(0, fileform);

		Object[] testArgs = { 2, "подтянуться" };
		log2(form.format(testArgs));

		Object[] testArgs2 = { 10, "прыгнуть" };
		log2(form.format(testArgs2));

	}

	@AuxTest(2)
	void displayMessages(Locale currentLocale) {

		log2("currentLocale = " + currentLocale.toString());

		ResourceBundle bundle = ResourceBundle.getBundle("ChoiceBundle", currentLocale);

		MessageFormat mf = new MessageFormat("");
		mf.setLocale(currentLocale);

		double[] fileLimits = { 0, 1, 2 };

		String[] fileStrings =
				{
						bundle.getString("noFiles"),
						bundle.getString("oneFile"),
						bundle.getString("multipleFiles")
				};

		ChoiceFormat choiceForm = new ChoiceFormat(fileLimits, fileStrings);

		String pattern = bundle.getString("pattern");
		Format[] formats = { choiceForm, null, NumberFormat.getInstance() };

		mf.applyPattern(pattern);
		mf.setFormats(formats);

		Object[] messageArguments = { null, "XDisk", null };

		for (int numFiles = 0; numFiles < 4; numFiles++) {
			messageArguments[0] = Integer.valueOf(numFiles);
			messageArguments[2] = Integer.valueOf(numFiles);
			String result = mf.format(messageArguments);
			log2(result);
		}
	}

	public void test2() throws Exception {
		/*
		
		 */
		displayMessages(Locale.ENGLISH);

	}

	@Override
	protected void doInit() throws Exception {
		options().hlComments = false;
	}

	public static void main(String[] args) {
		DemoBase.run(D_MessageFormat2.class);
	}

}
