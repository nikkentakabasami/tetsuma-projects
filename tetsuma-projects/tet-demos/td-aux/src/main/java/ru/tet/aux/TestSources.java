package ru.tet.aux;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.LambdaExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.SimpleName;

import lombok.Data;

/**
 * Все исходники по тесту с номером testNo
 */
@Data
public class TestSources {

	int testNo = 0;

	public TestSources(int testNo) {
		this.testNo = testNo;
	}

	MethodDeclaration testMethod;

	List<MethodDeclaration> auxMethods = new ArrayList<>();
	List<ClassOrInterfaceDeclaration> auxClasses = new ArrayList<>();

	//Если в тесте вызывается метод logEval - парсит код этих вызовов сюда
	List<String[]> logEvals = new ArrayList<>();

	//Если в тесте вызывается метод logExpr - парсит код этих вызовов сюда
	List<String[]> logExprs = new ArrayList<>();
	
	
	public boolean isEmpty() {
		return testMethod == null && auxMethods.isEmpty() && auxClasses.isEmpty();
	}

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
								.filter(e -> !(e instanceof SimpleName))
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
	
	String formatFunctionBody(LambdaExpr expr) {
		String str = expr.getBody().toString();
		int ind = str.lastIndexOf('\n', str.length()-4);
		if (ind<=0) {
			ind = str.length()-2;
		}
		str = str.substring(2, ind);
		str = str.replaceAll("(?m)^\\s+", "");
		return str;
	}
	

}
