package ru.tet.aux;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;

import lombok.Data;

@Data
public class TestSources {

	int testNo = 0;

	public TestSources(int testNo) {
		this.testNo = testNo;
	}

	MethodDeclaration testMethod;

	List<MethodDeclaration> auxMethods = new ArrayList<>();
	List<ClassOrInterfaceDeclaration> auxClasses = new ArrayList<>();

	public boolean isEmpty() {
		return testMethod==null && auxMethods.isEmpty() && auxClasses.isEmpty();
	}
	
}
