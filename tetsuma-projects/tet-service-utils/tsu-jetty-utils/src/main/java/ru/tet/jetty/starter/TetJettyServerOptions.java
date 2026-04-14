package ru.tet.jetty.starter;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class TetJettyServerOptions {

	@AllArgsConstructor
	public static class AdditionalStatic {
		
		//ResourceServlet mapping
		String pathSpec;
		
		Path dirPath;
	}
	
	
	int port = 8080;
	
	String contextPath = "/";
	
	//Путь к веб-проекту, который надо поднять
	String webAppProjectPath;
	
	//build.finalName веб проекта - имя под которым оно будет собираться в target 
	String webAppProjectFinalName;
	
	
	String[] welcomeFiles = new String[]{"index.html", "welcome.html"};

	//включить поддержку аннотаций и jsp
	//При использовании WebAppContext всё это включено по умолчанию.
	boolean annotationSupport = true;
	boolean jspSupport = true;
	boolean dumpAfterStart = false;
	

	//дополнительные папки и jar-файлы, которые надо добавить в classpath
	List<Path> extraClasspathes = new ArrayList<>(); 
	
	
	//дополнительный статический контент
	//подключается через ResourceServlet
	List<AdditionalStatic> additionalStatic = new ArrayList<>();
	
	
	//дополнительный статический контент в файловой системе
	//комбинируются и подключаются через webAppContext.setBaseResource(r)
	//Отлично подключаются jsp, но не подключаются html
	List<Path> fileSystemBaseResources = new ArrayList<>(); 

	
	List<String> classpathStaticContent = new ArrayList<>();
	List<Path> fileSystemStaticContent = new ArrayList<>(); 
	
	
	
	
	public void addAdditionalStatic(String pathSpec, Path dirPath) {
		additionalStatic.add(new AdditionalStatic(pathSpec, dirPath));
	}

	public void addAdditionalStatic(Path dirPath) {
		additionalStatic.add(new AdditionalStatic(null, dirPath));
	}
	
	String getAdditionalStaticAsString() {
		
		StringBuilder sb = new StringBuilder();
		for(AdditionalStatic as:additionalStatic) {
			sb.append("\n");
			if (as.pathSpec!=null) {
				sb.append(as.pathSpec).append(": ");
			}
			sb.append(as.dirPath);
			
		}
		return sb.toString();
	}
	
	
}
