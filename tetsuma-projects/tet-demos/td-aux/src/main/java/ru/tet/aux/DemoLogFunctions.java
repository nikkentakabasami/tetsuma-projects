package ru.tet.aux;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import ru.tet.aux.swing.LogDemoTextPane;

/**
 * Все функции демок, связанные с выводом в лог
 */
public interface DemoLogFunctions {

	static DecimalFormat createSimpleDecimalFormat() {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator('.');
		symbols.setGroupingSeparator('_');
		DecimalFormat f = new DecimalFormat("###,##0.####", symbols);
		return f;
	}

	public static final DecimalFormat DECIMAL_FORMAT = createSimpleDecimalFormat();

	LogDemoTextPane textArea1();

	LogDemoTextPane textArea2();

	/**
	 * фиксит параметры результата, приводя их к типам, которые можно вывести в json
	 * @param r
	 * @throws Exception
	 */
	default void fixResult(DemoResult r) throws Exception {

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

			if (value2 != value) {
				field.set(r, value2);
			}

			System.out.println("r." + name + " = " + value2.toString());
		}
	}

	/**
	 * Правка/Форматирование значений результатов перед выводом их в лог или преобразованием в JSON
	 * @param value
	 * @return
	 */
	default Object fixResultValue(Object value) throws Exception {

		if (value instanceof Stream s) {
			return s.toArray();
		}

		if (value instanceof Number i) {
			return DECIMAL_FORMAT.format(i);
		}

		return value;

	}

	/**
	 * Преобразование объекта в строку, перед выводом в лог.
	 * @param o
	 * @return
	 */
	default String toStr(Object o) {
		if (o == null) {
			return "";
		}

		try {
			o = fixResultValue(o);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String s = o.toString();
		s = s.replaceAll("\t", "  ");

		return s;
	}


	/*
	default void append1(String s) {
		append(textArea1(), s);
	}

	default void append2(String s) {
		append(textArea2(), s);
	}

	default void append(JTextPane ta, String s) {
		Document document = ta.getDocument();
		try {
			document.insertString(document.getLength(), s, null);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	*/
	
	
	
	default void logSplitter(LogDemoTextPane ta, Object... args) {
		String text = Stream.of(args).map(this::toStr).collect(Collectors.joining(" "));
		log(ta,"-------------"+text+"------------\n\n");
	}

	default void log(LogDemoTextPane ta, Object... args) {
		String text = Stream.of(args).map(this::toStr).collect(Collectors.joining(" "));
		ta.logNL(text);
	}
	
	//вывод без перехода на новую строку
	default void logInline(LogDemoTextPane ta, Object... args) {
		String text = Stream.of(args).map(this::toStr).collect(Collectors.joining(" "));
		ta.log(text);
	}
	
	
	default void clearlog1() {
		textArea1().clear();
	}

	default void clearlog2() {
		textArea2().clear();
	}

	default void log1(Object... args) {
		log(textArea1(), args);
	}

	default void log2(Object... args) {
		log(textArea2(), args);
	}

	//вывод без перехода на новую строку
	default void log1_(Object... args) {
		logInline(textArea1(), args);
	}

	//вывод без перехода на новую строку
	default void log2_(Object... args) {
		logInline(textArea2(), args);
	}
	
	default void log1Splitter(Object... args) {
		logSplitter(textArea1(), args);
	}

	default void log2Splitter(Object... args) {
		logSplitter(textArea2(), args);
	}
	
	default void log1NL() {
		textArea1().newLine();
	}

	default void log2NL() {
		textArea2().newLine();
	}
	
	//вывод логов
	default void flushLogs() throws IOException {
		textArea2().flush();
		
	}
	
	default Object expr(Supplier<Object> arg) {
		return arg.get();
	}
	
	
//	void logExpr(Supplier<Object>... args);
//	void logEval(Object... args);
	
	void _logEval(Integer no, Object... args);
	void _logExpr(Integer no, Supplier<Object>... args);

	
	default void logEval(Object... args) {
		_logEval(1, args);
	}
	default void logEval1(Object... args) {
		_logEval(1, args);
	}
	default void logEval2(Object... args) {
		_logEval(2, args);
	}
	default void logEval3(Object... args) {
		_logEval(3, args);
	}

	default void logExpr(Supplier<Object>... args) {
		_logExpr(1, args);
	}
	
	default void logExpr1(Supplier<Object>... args) {
		_logExpr(1, args);
	}
	default void logExpr2(Supplier<Object>... args) {
		_logExpr(2, args);
	}
	default void logExpr3(Supplier<Object>... args) {
		_logExpr(3, args);
	}
	
	
	

}
