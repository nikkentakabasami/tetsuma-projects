package ru.tet.syntax.datatypes.io;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.KeyStroke;

import ru.tet.aux.swing.DemoBase;

public class D_RegexpBase extends DemoBase {

	String regexps1;

	public static String testText = """
			ЛюдовикXV, ЛюдовикXVI, ЛюдовикXVIII,
			ЛюдовикV, ЛюдовикVI, ЛюдовикVIII, ЛюдовикLXVII, ЛюдовикXXL
			aaa aaa
			Сергей Иванов, Игорь Иванов
			text_before satori text_after
			1 индейка стоит 30€
			  1 индейка стоит $50
			ёршик  //comment2
			[some. @text. ($with.) braces.]
			@ hi \\\\double backslash!
			//Tenka musou, 1212
			http://localhost:8090/demo-52
			AbcD,ABCD,aBcD,abcd
			трам-трам-трумтрам-трум-трамтрум.
			----""";

	public D_RegexpBase(String regexps1) {
		this.regexps1 = regexps1;
	}

	void execRegex(String regex) throws Exception {
		execRegex(testText, regex, 0);
	}

	void execRegex(String input, String regex) throws Exception {
		execRegex(input, regex, 0);
	}

	void execRegex(String input, String regex, int flags) throws Exception {

		if (controlPanel.checkbox1.isSelected()) {
			flags = Pattern.MULTILINE | flags;
		}
		if (controlPanel.checkbox2.isSelected()) {
			flags = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE | flags;
		}

		Pattern pattern = Pattern.compile(regex, flags);

		Matcher matcher = pattern.matcher(input);

		textArea1.clearStyles();
		log2Green(regex);

		int counter = 0;
		while (matcher.find()) {

			String r = String.format("'%s' (%d-%d)", matcher.group(), matcher.start(), matcher.end());

			if (matcher.groupCount() > 0) {

				String groups =
						IntStream.rangeClosed(1, matcher.groupCount()).boxed().map(i -> "(" + matcher.group(i) + ")").collect(
								Collectors.joining(","));
				r += " groups: " + groups;
			}

			log2(r);

			textArea1.hlRed(matcher.start(), (matcher.end() - matcher.start()));
			if (++counter > options().maxEntries) {
				break;
			}
		}
		log2Splitter();

	}

	@Override
	protected void doInit() throws Exception {
		options().maxEntries = 5;
		options().logSources = false;
		options().hlComments = false;
		//		log1(testText);
	}

	@Override
	protected void doInitControlPanel() throws Exception {
		controlPanel.addComboBox(regexps1, e -> {
			showCBText();
		}, "curent regExp");
		controlPanel.addTextField();

		//		int mask = InputEvent.CTRL_DOWN_MASK;
		int mask = 0;

		controlPanel.newHorizontalBox();
		controlPanel.addCheckbox("MULTILINE", null);
		controlPanel.addCheckbox("CASE_INSENSITIVE", null);

		frame.addKeyHandler(KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_DOWN, mask), e -> {
			int ind = controlPanel.comboBox1.getSelectedIndex() + 1;
			if (ind < controlPanel.comboBox1.getItemCount()) {
				controlPanel.comboBox1.setSelectedIndex(ind);
			}
		});
		frame.addKeyHandler(KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_UP, mask), e -> {
			int ind = controlPanel.comboBox1.getSelectedIndex() - 1;
			if (ind >= 0) {
				controlPanel.comboBox1.setSelectedIndex(ind);
			}
		});
		controlPanel.textField1.grabFocus();

		controlPanel.textField1.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					execTest(1);
				}
			}
		});

		showCBText();

	}

	void showCBText() {
		Object selectedItem = controlPanel.comboBox1.getSelectedItem();
		controlPanel.textField1.setText(String.valueOf(selectedItem));
	}

	@Override
	public void beforeTest(int testNo) throws Exception {
		super.beforeTest(testNo);
		clearlog1();
		log1(testText);
	}

	public void test1() throws Exception {
		String re = controlPanel.textField1.getText();
		execRegex(testText, re);
	}

}
