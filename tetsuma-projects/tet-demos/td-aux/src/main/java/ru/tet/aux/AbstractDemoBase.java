package ru.tet.aux;

import java.util.List;
import java.util.function.Supplier;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.fasterxml.jackson.databind.ObjectMapper;

import ru.tet.aux.swing.AbstractDemoFrame;
import ru.tet.aux.swing.LogDemoTextPane;
import ru.tet.javax.swing.aux.JControlPanelForTests;
import ru.tet.sourcebuddy.DemoEvalUtils;
import ru.tet.sourcebuddy.EvalOptions;
import ru.tet.sourcebuddy.EvalResult;

/**
 * Основа для демок.
 * Функции логгирования - в интерфейсе DemoLogFunctions.
 * Функции для работы с тестами - в интерфейсе DemoAuxFunctions.
 * 
 */
public abstract class AbstractDemoBase implements DemoAuxFunctions, DemoLogFunctions {
	
	public static AbstractDemoBase currentDemo;

	DemoOptions options = new DemoOptions();
	
	//содержит инструментальную панель и панель для логов.
	protected AbstractDemoFrame frame;
	protected LogDemoTextPane textArea1;
	protected LogDemoTextPane textArea2;
	protected JControlPanelForTests controlPanel;
	protected JPanel workPanel;
	
	protected int lastTestNo = 0;

	//класс для работы с исходниками демки
	protected DemoSourceUtils sourceUtils;
	
	//исходник выполняемого теста
	TestSources currentSources;
	

	//Результаты демки можно записывать в этот объект
	//Перед каждым тестом он очищается. 
	//И если в него была запись - его содержимое выведется в json формате
	protected DemoResult r = null;
	
	
	
	public AbstractDemoBase() {
		sourceUtils = new DemoSourceUtils(this);
		
	}


	//инициализация демки, вызывается при её запуске
	protected void doInit() throws Exception {
	}

	//ещё одна инициализация - для добавления кнопок и прочих элементов управления
	protected void doInitControlPanel() throws Exception {
	}

	//вызывается перед закрытием демки
	public void beforeClose() throws Exception {
	}
	
	//вызывается перед запуском каждого теста
	public void beforeTest(int testNo) throws Exception {
		clearlog2();
		clearlog1();
		sourceUtils.logCurrentSources(testNo);
		r = new DemoResult();
		
		currentSources = sourceUtils.getSources().get(testNo);
	}

	//вызывается после каждого теста
	public void afterTest(int testNo) throws Exception {
		lastTestNo = testNo;

		//вывод результата r, если он задан в тесте
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
		
		//вывести буферизованные логи
		flushLogs();
		textArea2.hlComments();
		
	}
		
	//вызывается после создания демки, вызывает doInit
	public abstract void init(AbstractDemoFrame frame);
	
	

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




	
	
	@SafeVarargs
	public final void _logEval(Integer no, Object... args) {
		
		String[] expressions = currentSources.logEvals.get(no);

		for (int i = 0; i < expressions.length; i++) {
			String expr = expressions[i];
			Object val = args[i];
			textArea2.logBlue(expr+NL);
			log2(toStr(val)+NL);
		}
		
	}	
	
	@SafeVarargs
	public final void _logExpr(Integer no, Supplier<Object>... args) {
		
		String[] expressions = currentSources.logExprs.get(no);

		for (int i = 0; i < expressions.length; i++) {
			String expr = expressions[i];
			Object val = args[i].get();
			textArea2.logBlue(expr+NL);
			log2(toStr(val)+NL);
		}
		
	}
	
	
	
	@SafeVarargs
	public final void logExpr1(Supplier<Object>... args) {
		_logExpr(1, args);
	}
	
	@SafeVarargs
	public final void logExpr2(Supplier<Object>... args) {
		_logExpr(1, args);
	}

	@SafeVarargs
	public final void logExpr3(Supplier<Object>... args) {
		_logExpr(1, args);
	}

	
	@Override
	public LogDemoTextPane textArea1() {
		return textArea1;
	}

	@Override
	public LogDemoTextPane textArea2() {
		return textArea2;
	}
	
	public DemoOptions options() {
		return options;
	}
	

	/**
	 * Позволяет задать тесты в строковом виде.
	 * Экспериментальная фича.
	 * 
	 * @param code
	 */
	public void logEvalString(String code) {
		try {
			
			List<EvalResult> ers = DemoEvalUtils.evalMultiExpression(code, options.evalStringOptions);
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
	
	
	
	//вспомогательный метод для запуска тестов
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
