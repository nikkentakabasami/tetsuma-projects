package ru.tet.syntax.datatypes.io;

import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.KeyStroke;

import ru.tet.aux.swing.DemoBase;

public class D_Regexp2 extends DemoBase {

	String currentRegexp;

//	static String[] testRegExps =
//			{
//					"exp1 ssse e asdfasfas dsasdf a sadfasf",
//					"exp2 sss",
//					"exp3 sss",
//					"exp4 sss"
//			};

	public static String regexps1 = """

//

(?i)tenka #поиск без учёта регистра

сергей
(?i)сергей #плохо работает




Людовик
(?m)^Людовик



			""";


	/*
		testRegex("Treehouse", "(?i)tree");
		testRegex("Treehouse", "tree");

		// многострочный поиск
		testRegex("abc\nabc", "(?m)^abc$");

		// комментарии
		testRegex("matter", ".at(?x)#match hat, cat, and so on");
	 * 
	 */
	
	
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
			//Tenka musou
			http://localhost:8090/demo-52
			трам-трам-трумтрам-трум-трамтрум.
			----""";


	void execRegex(String regex) throws Exception {
		execRegex(testText, regex, 0);
	}
	
	
	void execRegex(String input, String regex) throws Exception {
		execRegex(input, regex, 0);
	}
	
	void execRegex(String input, String regex, int flags) throws Exception {

		currentRegexp = regex;

		Pattern pattern = Pattern.compile(regex, Pattern.COMMENTS | flags);
		
		Matcher matcher = pattern.matcher(input);

		log2(regex);
		log2Splitter();
		while (matcher.find()) {
			log2Format("'%s' (%d-%d)", matcher.group(), matcher.start(), matcher.end());
		}
	}	
	
	
	void showCBText() {
		Object selectedItem = controlPanel.comboBox1.getSelectedItem();
		controlPanel.textField1.setText(String.valueOf(selectedItem));
	}
	
	@Override
	protected void doInitControlPanel() throws Exception {
		controlPanel.addComboBox(regexps1, e -> {
			showCBText();
		}, "curent regExp");
		controlPanel.addTextField();

		frame.addKeyHandler(KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_DOWN, InputEvent.CTRL_DOWN_MASK), e -> {
			int ind = controlPanel.comboBox1.getSelectedIndex() + 1;
			if (ind < controlPanel.comboBox1.getItemCount()) {
				controlPanel.comboBox1.setSelectedIndex(ind);
			}
		});
		frame.addKeyHandler(KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_UP, InputEvent.CTRL_DOWN_MASK), e -> {
			int ind = controlPanel.comboBox1.getSelectedIndex() - 1;
			if (ind >= 0) {
				controlPanel.comboBox1.setSelectedIndex(ind);
			}
		});
		controlPanel.textField1.grabFocus();


		
		controlPanel.textField1.addKeyListener(new KeyAdapter(){
		  public void keyPressed(KeyEvent e) {
		    if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		    	execTest(1);
		    }
		  }
		});
		
		showCBText();
		
		
	}



	@Override
	public void beforeTest(int testNo) throws Exception {
		super.beforeTest(testNo);

		clearlog1();
		log1(testText);
	}

	public void test1() throws Exception {
		/*
		 */

		String re = controlPanel.textField1.getText();
		execRegex(testText, re);

	}

	public void test2() throws Exception {
		/*
		
		 */
//		execRegex("Treehouse", "(?i)tree");
		execRegex(testText, "сергей", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

	}

	public void test3() throws Exception {
		/*
		
		 */
	}

	public void test4() throws Exception {
		/*
		
		 */
	}

	public static void main(String[] args) {
		DemoBase.run(D_Regexp2.class);
	}

}
