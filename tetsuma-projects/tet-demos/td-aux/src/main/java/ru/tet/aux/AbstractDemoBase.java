package ru.tet.aux;

import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import ru.tet.aux.swing.AbstractDemoFrame;
import ru.tet.aux.swing.LogDemoTextPane;
import ru.tet.javax.swing.aux.JControlPanelForTests;
import ru.tet.sourcebuddy.DemoEvalUtils;
import ru.tet.sourcebuddy.EvalOptions;
import ru.tet.sourcebuddy.EvalResult;

public abstract class AbstractDemoBase {

	static DecimalFormat createSimpleDecimalFormat() {
		DecimalFormatSymbols simpleDot = new DecimalFormatSymbols();
	    simpleDot.setDecimalSeparator('.');
		DecimalFormat f = new DecimalFormat("#0.##",simpleDot);
		return f;
	}
	
	public static final DecimalFormat DECIMAL_FORMAT = createSimpleDecimalFormat();

	//классы, которые нужно будет добавить в секцию import
	protected List<Class<?>> importClasses = new ArrayList<>();
	
	//содержит инструментальную панель и панель для логов.
	protected AbstractDemoFrame frame;
	
	protected LogDemoTextPane textArea1;
	protected LogDemoTextPane textArea2;
	
	protected DemoSourceUtils sourceUtils;

	protected JControlPanelForTests controlPanel;

	protected JPanel workPanel;
	
	protected int lastTestNo = 0;
	String lastTestName;

	public AbstractDemoBase() {
		sourceUtils = new DemoSourceUtils(this);
	}

	protected void doInit() throws Exception {
	}

	protected void doInitControlPanel() throws Exception {
	}
	
	
	public abstract void init(AbstractDemoFrame frame);

	//заготовки под тесты
	public void test1() throws Exception {
	}

	public void test2() throws Exception {
	}

	public void test3() throws Exception {
	}

	public void test4() throws Exception {
	}

	public JButton addButton(String title, DemoActionListener al) {
		return controlPanel.addButton(title, event -> {
			try {
				al.actionPerformed(event);
			} catch (Exception e) {
				log2(e);
				e.printStackTrace();
			}
		});
	}

	/**
	 * Запуск теста с заданным номером.
	 * 
	 * @param testNo
	 * @throws Exception
	 */
	public void test(int testNo) throws Exception {
		if (testNo<=0) {
			return;
		}
		String testName = "test" + testNo;
		beforeTest(testNo);
		Method method = AbstractDemoBase.class.getMethod(testName);
		method.invoke(this);
		lastTestName = testName;
		afterTest(testNo);
	}
	
	

	private String nvl(String s, String mn) {
		return s != null ? s : mn;
	}

	private JButton addTestButton(String title, int testNo) {
		String testName = "test" + testNo;
		return addButton(nvl(title, testName), event -> {
			test(testNo);
		});
	}

	protected void beforeTest(int testNo) throws Exception {
		clearlog2();
		clearlog1();
		sourceUtils.logCurrentSources(testNo);
	}

	protected void afterTest(int testNo) throws Exception {
		lastTestNo = testNo;
		log2Splitter();
		log2(lastTestName, "finished");
		textArea2.hlComments();
	}

	public JButton addTest1Button(String title) {
		return addTestButton(title, 1);
	}

	public JButton addTest2Button(String title) {
		return addTestButton(title, 2);
	}

	public JButton addTest3Button(String title) {
		return addTestButton(title, 3);
	}

	public JButton addTest4Button(String title) {
		return addTestButton(title, 4);
	}

	public void clearlog1() {
		textArea1.setText(null);
	}

	public void clearlog2() {
		textArea2.setText(null);
	}

	public void log1(Object... args) {
		log(textArea1, args);
	}

	public void log2(Object... args) {
		log(textArea2, args);
	}

	public void log1Splitter(Object... args) {
		logSplitter(textArea1, args);
	}

	public void log2Splitter(Object... args) {
		logSplitter(textArea2, args);
	}

	public void logEval2(String code) {
		try {
			
			EvalOptions options = new EvalOptions();
			options.getImportClasses().addAll(importClasses);
			
			List<EvalResult> ers = DemoEvalUtils.evalMultiExpression(code, options);
			for(EvalResult er:ers) {
				if (er.getComments()!=null) {
					log2(er.getComments());
				}
				log2(er.getExpression(),"\t=",er.getResult(),"\n");
				
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public void log1NL() {
		append1("\n");
	}
	
	public void append1(String s) {
		append(textArea1, s);
	}
	public void append2(String s) {
		append(textArea2, s);
	}	
	public void append(JTextPane ta, String s) {
		Document document = ta.getDocument();
		try {
			document.insertString(document.getLength(), s, null);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}	
	
	
	public void log2NL() {
		append2("\n");
	}
	
	public void logSplitter(JTextPane ta, Object... args) {
		String text = Stream.of(args).map(this::toStr).collect(Collectors.joining(" "));
		append(ta,"-------------"+text+"------------\n\n");
	}

	public void log(JTextPane ta, Object... args) {
		String text = Stream.of(args).map(this::toStr).collect(Collectors.joining(" "));
		text += "\n";
		append(ta,text);
	}


	private String toStr(Object o) {
		if (o == null) {
			return "";
		}
		
		if (o instanceof Double) {
			return DECIMAL_FORMAT.format((Double)o);
		}
		
		return o.toString();
	}
	

	public static void run(Class<? extends AbstractDemoBase> cl) {
		run(cl,0);
	}
	
	public static void run(Class<? extends AbstractDemoBase> cl, int startTestOnLaunch) {
		SwingUtilities.invokeLater(() -> {
			try {
				AbstractDemoBase demo = cl.getDeclaredConstructor().newInstance();
				demo.init(null);
				demo.test(startTestOnLaunch);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

	}

}
