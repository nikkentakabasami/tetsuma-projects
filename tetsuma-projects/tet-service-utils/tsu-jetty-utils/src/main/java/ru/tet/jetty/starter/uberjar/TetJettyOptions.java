package ru.tet.jetty.starter.uberjar;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class TetJettyOptions {

	int port = 8080;
	
	String contextPath = "/";
	
	String[] welcomeFiles = new String[]{"index.html", "welcome.html"};

	//включить поддержку аннотаций и jsp
	//При использовании WebAppContext всё это включено по умолчанию.
	boolean annotationSupport = true;
	boolean jspSupport = true;
	boolean dumpAfterStart = false;
	

	
	//дополнительный статический контент (в classpath)
	List<String> additionalStatic = new ArrayList<>();
	
	
	
	
	public void addAdditionalStatic(String path) {
		additionalStatic.add(path);
	}
	
	
}
