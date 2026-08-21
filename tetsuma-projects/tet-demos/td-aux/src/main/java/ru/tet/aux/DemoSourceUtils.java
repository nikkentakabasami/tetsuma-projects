package ru.tet.aux;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.javaparser.ParserConfiguration.LanguageLevel;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithAnnotations;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import ru.tet.utils.TetSourceUtils;


/**
 * Содержит исходники текущей демки (demoBase)
 */
public class DemoSourceUtils {

	static final String TEST_METHOD_SUFFIX = "test";

	AbstractDemoBase demoBase;
	List<TestSources> sources;

	
	
	public DemoSourceUtils(AbstractDemoBase db) {
		this.demoBase = db;
	}


	/**
	 * Находит исходники текущей демки (demoBase) и парсит их, заполняя sources.
	 */
	public void parseCurrentSources() {
		
		sources = new ArrayList<>(10);
		for (int i = 0; i < 8; i++) {
			sources.add(new TestSources(i));
		}

		try {
			InputStream javaFileIS = TetSourceUtils.findSource(demoBase.getClass());
			
			StaticJavaParser.getParserConfiguration().setLanguageLevel(LanguageLevel.JAVA_25);
			
			CompilationUnit cu2 = StaticJavaParser.parse(javaFileIS);

			cu2.accept(new VoidVisitorAdapter<Object>() {

				@Override
				public void visit(ClassOrInterfaceDeclaration n, Object arg) {
					TestSources testSources = findTestSourcesByAnnotation(n);
					if (testSources != null) {
						testSources.getAuxClasses().add(n);
					}
					n.getAnnotations().clear();
					super.visit(n, arg);
				}

				@Override
				public void visit(MethodDeclaration m, Object arg) {
					super.visit(m, arg);
					String name = m.getName().getIdentifier();

										
					if ("doInit".equals(name)) {
						TestSources testSources = sources.get(0);
						testSources.getAuxMethods().add(m);
					} else if (name.startsWith(TEST_METHOD_SUFFIX)) {
						int testNo = Integer.parseInt(name.substring(TEST_METHOD_SUFFIX.length()));

						TestSources testSources = sources.get(testNo);
						testSources.setTestMethod(m);
						testSources.parseLogEvals();
						
					} else {
						TestSources testSources = findTestSourcesByAnnotation(m);
						if (testSources != null) {
							testSources.getAuxMethods().add(m);
						}
					}
					
					m.getAnnotations().clear();
					m.getModifiers().clear();
					m.getThrownExceptions().clear();
					
					

				}

			}, null);

		} catch (IOException e) {
			demoBase.log2(e.getMessage());
			e.printStackTrace();
		}

	}

	
  Pattern auxTestNoPattern = Pattern.compile("\\d+");
	
	
	/**
	 * Если на метод назначена аннотация AuxTest, возвращает TestSources, к которому привязан этот метод
	 */
	private TestSources findTestSourcesByAnnotation(NodeWithAnnotations m) {

		Optional<AnnotationExpr> ann = m.getAnnotationByName(AuxTest.class.getSimpleName());
		if (ann.isEmpty()) {
			return null;
		}

		int testNo = 1;
		String s = ann.get().toString();
		
	  Matcher matcher = auxTestNoPattern.matcher(s);
		if (matcher.find()) {
			testNo = Integer.parseInt(matcher.group());
		}
		
		TestSources testSources = sources.get(testNo);
		return testSources;
	}

	public void logCurrentSources() {
		logCurrentSources(0);
	}	
	/**
	 * Выводит в первый лог исходники всех тестов.
	 * Или только теста с номером testNo, если он задан
	 * 
	 * @param testNo
	 */
	public void logCurrentSources(int testNo) {

		for (TestSources testSources : sources) {
			if (testSources.isEmpty()) {
				continue;
			}

			if (testNo > 0 && testSources.getTestNo() != testNo && testSources.getTestNo()>0) {
				continue;
			}

			for (ClassOrInterfaceDeclaration m : testSources.getAuxClasses()) {
				demoBase.log1(m);
				demoBase.log1NL();
			}

			for (MethodDeclaration m : testSources.getAuxMethods()) {
				//				m.getAnnotations().clear();
				demoBase.log1(m);
				demoBase.log1NL();
			}

			//			ts.getTestMethod().getAnnotations().clear();
			demoBase.log1(testSources.getTestMethod());
			demoBase.log1NL();

		}
		
		demoBase.textArea1.hlComments();

	}

	public List<TestSources> getSources() {
		return sources;
	}
	
}
