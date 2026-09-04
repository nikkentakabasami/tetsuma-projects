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

	public boolean isEmpty() {
		return testMethod == null && auxMethods.isEmpty() && auxClasses.isEmpty();
	}

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

	static final String RETURN = "return ";
	
	String formatFunctionBody(LambdaExpr expr) {
		String str = expr.getBody().toString();
		
		//что возвращается в return
		String returnVal = null;

		int ind = str.lastIndexOf(RETURN);
		if (ind>=0) {
			
			int valInd = ind+RETURN.length();
			int scInd = str.indexOf(';', valInd);

			if (scInd>0) {
				returnVal = str.substring(valInd,scInd).trim();
			}
			
			str = str.substring(2, ind);
			//str = str.substring(ind);
			
		} else {
			str = str.substring(2, str.length() - 2);
		}

		str = str.replaceAll("(?m)^\\s+", "").trim();
		
		if (returnVal!=null) {
			str+="\n"+returnVal;
		}
		
		return str;
	}

}
