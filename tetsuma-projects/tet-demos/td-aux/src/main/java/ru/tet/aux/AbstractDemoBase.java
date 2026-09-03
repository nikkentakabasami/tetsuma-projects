package ru.tet.aux;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import com.fasterxml.jackson.databind.ObjectMapper;

import ru.tet.aux.swing.AbstractDemoFrame;
import ru.tet.aux.swing.LogDemoTextPane;
import ru.tet.javax.swing.aux.JControlPanelForTests;
import ru.tet.sourcebuddy.DemoEvalUtils;
import ru.tet.sourcebuddy.EvalOptions;
import ru.tet.sourcebuddy.EvalResult;

public abstract class AbstractDemoBase {

	public static AbstractDemoBase currentDemo;
	
	static DecimalFormat createSimpleDecimalFormat() {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator('.');
		symbols.setGroupingSeparator('_');
		DecimalFormat f = new DecimalFormat("###,##0.####", symbols);
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

	//исходним выполняемого теста
	TestSources currentSources;
	int logEvalNo = 0;
	int logExprNo = 0;
	
	//результат теста, будет выводиться в json формате
	protected DemoResult r = null;
	
	
	public AbstractDemoBase() {
		sourceUtils = new DemoSourceUtils(this);
	}


	//инициализация демки, вызывается при её запуске
	protected void doInit() throws Exception {
	}

	//ещё одна инициализация, доп. метод
	protected void doInitControlPanel() throws Exception {
	}

	//вызывается перед закрытием демки
	public void beforeClose() throws Exception {
	}

	
	

	//заготовки под тесты
	public void test1() throws Exception {
	}

	public void test2() throws Exception {
	}

	public void test3() throws Exception {
	}

	public void test4() throws Exception {
	}
	public void test5() throws Exception {
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
		Method method = null;
		try {
			method = AbstractDemoBase.class.getMethod(testName);
		} catch (NoSuchMethodException e) {
			log2("method not found: "+testName);
			return;
		}
		
		beforeTest(testNo);
		method.invoke(this);
		lastTestName = testName;
		afterTest(testNo);
	}
	
	

	private String nvl(String s, String mn) {
		return s != null ? s : mn;
	}

	protected JButton addTestButton(String title, int testNo) {
		String testName = "test" + testNo;
		return addButton(nvl(title, testName), event -> {
			test(testNo);
		});
	}
	
	
	
	//вызывается после создания демки, вызывает doInit
	public abstract void init(AbstractDemoFrame frame);
	

	protected void beforeTest(int testNo) throws Exception {
		clearlog2();
		clearlog1();
		sourceUtils.logCurrentSources(testNo);
		r = new DemoResult();
		
		currentSources = sourceUtils.getSources().get(testNo);
		logEvalNo = 0;
		logExprNo = 0;
		
	}

	protected void afterTest(int testNo) throws Exception {
		lastTestNo = testNo;

		//вывод результата
		if (r.s1!=null) {
			
			fixResult(r);
			
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(r);
			json = json.replaceAll("  \"", "")
					.replaceAll("(?<=\\d)\" :", ":")
					.replaceAll("\\[ ", "[").replaceAll("\\ ]", "]")  //лишние пробелы из массивов
					.replaceAll("(?m),$", "");
			
			log2(json);
		}
		
//		log2Splitter();
//		log2(lastTestName, "finished");
		textArea2.hlComments();
		
		
		
	}

	
	/**
	 * фиксит параметры результата, приводя их к типам, которые можно вывести в json
	 * @param r
	 * @throws Exception
	 */
	public void fixResult(DemoResult r) throws Exception {

		Field[] fields = DemoResult.class.getFields();
		for (Field field : fields) {
			String name = field.getName();

			if (!name.matches("s\\d")) {
				continue;
			}

			Object value = field.get(r);

			if (value == null) {
				continue;
			}
			
			Object value2 = fixResultValue(value);

			if (value2!=value) {
				field.set(r, value2);
			}

			System.out.println("r." + name + " = " + value2.toString());

		}

	}

	
	
	
	/**
	 * Правка/Форматирование значений результатов (перед выводом их в лог)
	 * @param value
	 * @return
	 */
	public Object fixResultValue(Object value) throws Exception {
		
		if (value instanceof Stream s) {
			return s.toArray();
		}
		
		if (value instanceof Integer i) {
			return DECIMAL_FORMAT.format(i);
		}
		
		if (value instanceof Long l) {
			return DECIMAL_FORMAT.format(l);
		}
		
		
		
		return value;
		
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
	public JButton addTest5Button(String title) {
		return addTestButton(title, 5);
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

	
	@SafeVarargs
	public final void logExpr(Supplier<Object>... args) {
		
		String[] expressions = currentSources.logExprs.get(logExprNo);

		for (int i = 0; i < expressions.length; i++) {
			String expr = expressions[i];
			Object val = args[i].get();

			//log2(expr+"\n---\n",val,"\n---\n");
			log2(expr+"\n",val,"\n");
		}
		
		logExprNo++;		
		
	}
	
	
	@SafeVarargs
	public final void logEval(Object... args) {

		
		String[] expressions = currentSources.logEvals.get(logEvalNo);

		for (int i = 0; i < expressions.length; i++) {
			String expr = expressions[i];
			Object val = args[i];

			log2(expr+"\n",val,"\n");
		}
		
		logEvalNo++;
		
	}
	
	@Deprecated
	public void logEvalString(String code) {
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
		System.out.println(text);
		append(ta,text);
	}


	private String toStr(Object o) {
		if (o == null) {
			return "";
		}

		try {
			o = fixResultValue(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if (o instanceof Double) {
			return DECIMAL_FORMAT.format((Double)o);
		}
		
		String s = o.toString();
		s = s.replaceAll("\t", "  ");

		return s;
	}
	

	public static void run(Class<? extends AbstractDemoBase> cl) {
		run(cl,0);
	}
	
	public static void run(Class<? extends AbstractDemoBase> cl, int startTestOnLaunch) {
		SwingUtilities.invokeLater(() -> {
			try {
				currentDemo = cl.getDeclaredConstructor().newInstance();
				currentDemo.init(null);
				currentDemo.test(startTestOnLaunch);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

	}

}
