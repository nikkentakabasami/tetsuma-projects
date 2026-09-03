package ru.tet.aux;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.ast.expr.LambdaExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.SimpleName;

import lombok.Data;

/**
 * Все исходники по тесту с номером testNo
 */
@Data
public class TestSources {

	static final String LOG_EVAL = "logEval";
	static final String LOG_EXPR = "logExpr";
	static final String EXPR = "expr";

	int testNo = 0;

	public TestSources(int testNo) {
		this.testNo = testNo;
	}

	MethodDeclaration testMethod;

	List<MethodDeclaration> auxMethods = new ArrayList<>();
	List<ClassOrInterfaceDeclaration> auxClasses = new ArrayList<>();

	//Если в тесте вызывается метод logEval - парсит код этих вызовов сюда
	Map<Integer, String[]> logEvals = new HashMap<>();

	//Если в тесте вызывается метод logExpr - парсит код этих вызовов сюда
	Map<Integer, String[]> logExprs = new HashMap<>();

	//	List<String[]> logEvals = new ArrayList<>();
	//	List<String[]> logExprs = new ArrayList<>();

	public boolean isEmpty() {
		return testMethod == null && auxMethods.isEmpty() && auxClasses.isEmpty();
	}

	//  Pattern digitPattern = Pattern.compile("\\d+$");

	public Map<Integer, String[]> findMethodCallExpr(Node node, String prefix) {

		Map<Integer, String[]> result = new HashMap<>();

		List<MethodCallExpr> methods = node.findAll(MethodCallExpr.class);

		for (MethodCallExpr me : methods) {
			String methodName = me.getName().toString();
			if (!methodName.startsWith(prefix)) {
				continue;
			}

			String evalNoStr = methodName.substring(prefix.length());
			
			int evalNo = evalNoStr.length()>0?Integer.parseInt(evalNoStr):1;

		String[] evalExpressions =
					me.getChildNodes().stream()
							.filter(e -> !(e instanceof SimpleName) && !(e instanceof LineComment))
							.map(e -> formatExpression(e))
							.toArray(String[]::new);

			result.put(evalNo, evalExpressions);
		}

		return result;

	}

	public void parseLogEvals() {

		if (testMethod == null) {
			return;
		}

		logEvals = findMethodCallExpr(testMethod, LOG_EVAL);
		logExprs = findMethodCallExpr(testMethod, LOG_EXPR);
	}

	/*
	public void parseLogEvals() {
	
		if (testMethod == null) {
			return;
		}
	
		testMethod.getBody().get().getStatements().forEach(statement -> {
	
			MethodCallExpr me =
					statement.getChildNodes().stream()
							.filter(e -> (e instanceof MethodCallExpr))
							.map(e -> (MethodCallExpr) e)
							.filter(e -> e.getName().toString().equals("logEval"))
							.findFirst().orElse(null);
	
			if (me != null) {
				String[] evalExpressions =
						me.getChildNodes().stream()
								.filter(e -> !(e instanceof SimpleName) && !(e instanceof LineComment))
								.map(e -> e.toString())
								//						.peek(p->{
								//							System.out.println(p.toString());
								//						})
								.toArray(String[]::new);
	
				logEvals.add(evalExpressions);
			}
			
			
			me =
					statement.getChildNodes().stream()
							.filter(e -> (e instanceof MethodCallExpr))
							.map(e -> (MethodCallExpr) e)
							.filter(e -> e.getName().toString().equals("logExpr"))
							.findFirst().orElse(null);
	
			if (me != null) {
				String[] expressions =
						me.getChildNodes().stream()
								.filter(e -> (e instanceof LambdaExpr))
								.map(e -> (LambdaExpr) e)
	//								.map(e -> e.getBody().toString())
								.map(this::formatFunctionBody)
								.toArray(String[]::new);
	
				logExprs.add(expressions);
			}
	
		});
	
	}
	*/

	String formatExpression(Node node) {
		
		if (node instanceof LambdaExpr expr) {
			return formatFunctionBody(expr);
		}
		
		if (node instanceof MethodCallExpr m) {
			if (m.getName().toString().equals(EXPR)) {
				Optional<LambdaExpr> expr = m.findFirst(LambdaExpr.class);
				if (!expr.isEmpty()) {
					return formatFunctionBody(expr.get());
				}
			}
		}
		return node.toString();
	}

	String formatFunctionBody(LambdaExpr expr) {
		String str = expr.getBody().toString();
		

		int ind = str.lastIndexOf("return");
		if (ind>=0) {
			str = str.substring(2, ind);
		} else {
			str = str.substring(2, str.length() - 2);
		}

		/*
		int ind = str.lastIndexOf('\n', str.length() - 4);
		if (ind <= 0) {
			ind = str.length() - 2;
		}
		str = str.substring(2, ind);
		*/
		
		str = str.replaceAll("(?m)^\\s+", "").trim();
		return str;
	}

}
