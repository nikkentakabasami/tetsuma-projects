package ru.tet.st;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Ищет ресурсы, находящиеся по заданному пути в класспазе.
 * 
 * @author tetsuma
 *
 */
public class SuClasspathResourceScanner {


  public List<String> getResourceFilesViaClassLoader(String templatesPackage) throws IOException {
    List<String> filenames = new ArrayList<>();

    try (InputStream in = getResourceStreamViaClassLoader(templatesPackage);
        BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
      String resource;

      while ((resource = br.readLine()) != null) {
        filenames.add(resource);
      }
    }

    return filenames;
  }

  private InputStream getResourceStreamViaClassLoader(String resource) {
    final InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
    return in == null ? getClass().getResourceAsStream(resource) : in;
  }

  
  
  public List<String> getResourceFilesForClassesDir(File classesDir, String templatesPackage) throws IOException {
    
    List<String> result = new ArrayList<>();
    
    File dir = classesDir;
    String[] packageParts = templatesPackage.split("[./]");
    for(String part:packageParts) {
      dir = new File(dir,part);
      if (!dir.exists() || !dir.isDirectory()) {
        System.out.println("dir not found: "+dir.getCanonicalPath());
        return result;
      }
    }
    String[] files= dir.list();
    
    for(String fn:files) {
      if (fn.endsWith(".st")) {
        result.add(fn);
      }
    }
    return result;
  }
  
  
  

  /**
   * Тест.
   * 
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    
    SuClasspathResourceScanner scanner = new SuClasspathResourceScanner();
//    List<String> list = scanner.getResourceFilesViaClassLoader("templates/journal2");
    List<String> list = scanner.getResourceFilesForClassesDir(new File("/home/tetsuma/work/workspaces/ri/EAMServiceUtils/target/classes"), "templates.journal2");
    for(String r:list) {
      System.out.println(r);
    }

    
  }
  
  
  
}
