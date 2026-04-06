package ru.tet.javax.annotation.processing;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.tools.Diagnostic.Kind;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

/**
 * Логгер для процессоров аннотаций.
 * Из за глюков явы - обычные сообщения через getMessager() просто не выводятся в лог.
 * 
 * @author tetsuma
 *
 */
public class SuProcessorLog {

  public static final String CLASSES_PATH_PART = "/target/";
  
	PrintWriter printWriter = null;
	Messager messager;	
  FileObject logFileObject;
	
	public SuProcessorLog(ProcessingEnvironment processingEnv, String logFileName) {
		
		try {
      logFileObject = processingEnv.getFiler().createResource(StandardLocation.SOURCE_OUTPUT, "", logFileName);
			printWriter = new PrintWriter(logFileObject.openWriter());
			messager = processingEnv.getMessager();
		} catch (IOException e) {
			processingEnv.getMessager().printMessage(Kind.ERROR,e.getMessage());
		}  
		
		
	}
	
	
  public File getClassesDir() {
    
    File file = new File(logFileObject.toUri());
    while (file!=null) {
      
      if (file.getName().equals("target")) {
        break;
      }
      file = file.getParentFile();
    }
    if (file==null) {
      return null;
    }
    
    return new File(file,"classes");
    

  } 	
	
	
	/*
  public File getClassesDir2() {
    
    String path = logFileObject.toUri().getPath();
    
    int ind = path.indexOf(CLASSES_PATH_PART);
    if (ind<0) {
      log("cannot find dir '"+CLASSES_PATH_PART+"' in path '"+path+"'");
      return null;
    }
    String classesPath = path.substring(0, ind+CLASSES_PATH_PART.length());
    
    
    File file = new File(classesPath);
    if (!file.exists() || !file.isDirectory()) {
      log("cannot find dir '"+classesPath+"'");
      return null;
    }
    
    return file;
    

  }	
	*/
	
	public void log(String s) {
		messager.printMessage(Kind.NOTE,s);
		if (printWriter!=null) {
			printWriter.println(s);
			printWriter.flush();
		}
	}
	
	public void log(Exception ex) {
		if (printWriter!=null) {
			ex.printStackTrace(printWriter);
			printWriter.flush();
		}
		
	}
	
}
