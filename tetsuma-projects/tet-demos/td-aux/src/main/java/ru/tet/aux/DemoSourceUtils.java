package ru.tet.aux;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithAnnotations;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class DemoSourceUtils {

	static final String TEST_METHOD_SUFFIX = "test";

	AbstractDemoBase demoBase;

	public DemoSourceUtils(AbstractDemoBase db) {
		this.demoBase = db;
	}

	/**
	 * Находит в текущем проекте java-файл, соответствующий классу cl
	 * 
	 * @param cl
	 * @return
	 */
	public static Path getClassJavaFile(Class cl, boolean main) {
		String cn = cl.getCanonicalName().replaceAll("\\.", File.separator).concat(".java");
		return Paths.get("src",main?"main":"test","java", cn);
		
//		return Paths.get("src/main/java", cn);
	}

	public void logCurrentSources() {
		logCurrentSources(0);
	}

	List<TestSources> sources;

	public void parseCurrentSources() {

		Path path = getClassJavaFile(demoBase.getClass(), true);
		if (!Files.exists(path)) {
			path = getClassJavaFile(demoBase.getClass(), false);
			if (!Files.exists(path)) {
				demoBase.log1("source file not found:", path);
				return;
			}
		}

		sources = new ArrayList<>(10);
		for (int i = 0; i < 8; i++) {
			sources.add(new TestSources(i));
		}

		try {
			CompilationUnit cu2 = StaticJavaParser.parse(Files.newInputStream(path));

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

	private TestSources findTestSourcesByAnnotation(NodeWithAnnotations m) {

		Optional<AnnotationExpr> ann = m.getAnnotationByName(AuxTest.class.getSimpleName());
		if (ann.isEmpty()) {
			return null;
		}

		int testNo = 1;
		String s = ann.get().toString();
		int ind1 = s.indexOf("(");
		int ind2 = s.indexOf(")");
		if (ind1 > 0 && ind2 > 0) {
			testNo = Integer.parseInt(s.substring(ind1 + 1, ind2));
		}
		TestSources testSources = sources.get(testNo);
		return testSources;
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
		
		demoBase.frame.hlComments();

		/*
		
		Path path = getClassJavaFile(db.getClass());
		if (!Files.exists(path)) {
			db.log1("source file not found:", path);
			return;
		}
		
		try {
			CompilationUnit cu2 = StaticJavaParser.parse(Files.newInputStream(path));
		
			String clName = db.getClass().getSimpleName();
			Optional<ClassOrInterfaceDeclaration> mainClass = cu2.getClassByName(clName);
			if (mainClass.isEmpty()) {
				db.log1("class not found:" + clName);
			}
			//			log1("current class:", clName);
		
			boolean afterTests = false;
			List<MethodDeclaration> methodsByName = mainClass.get().getMethods();
			for (MethodDeclaration m : methodsByName) {
				String name = m.getName().getIdentifier();
				BlockStmt body = m.getBody().get();
		
				if (name.contains("test")) {
					afterTests = true;
		
					if (testNo > 0 && !name.equals("test" + testNo)) {
						continue;
					}
		
					db.log1(name);
					db.log1(body);
					db.log1NL();
				} else {
					//методы после тестов не выводить
					if (afterTests) {
						break;
					}
					db.log1(name);
					db.log1(body);
					db.log1NL();
				}
		
			}
		
		} catch (IOException e) {
			db.log2(e.getMessage());
			e.printStackTrace();
		}
		*/

	}

}
