package ru.tet.st;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupDir;
import org.stringtemplate.v4.STRawGroupDir;

import lombok.Data;

/**
 * Утилита для генерации класса конвертера на основе SUAbstractConverter.
 * Использует StringTemplate. В качестве шаблона используется файл:
 * converterTemplate.st
 * 
 * Пример использования: String result =
 * ConverterTemplateWriter.makeConverter("User", "UserModel", "UserDTO",
 * "ru.alphatech.orm.UserModel", "ru.alphatech.dto.UserDTO" );
 * 
 * 
 * @author tetsuma
 *
 */
public class SuJournalTemplateWriter {

  public static String makeConverterCode(SuJournalModel model) {

    STGroup group = new STGroupDir("templates/journal");
    ST st = group.getInstanceOf("converterTemplate");
    st.add("m", model);

    String result = st.render();

    return result;

  }

  public static Map<String, String> makeJournalCode(SuJournalModel model) {

    Map<String, String> result = new HashMap<String, String>();

    STGroup group = new STGroupDir("templates/journal");

    ST st = group.getInstanceOf("converter").add("m", model);
    result.put(model.entity + "Converter.txt", st.render());

//		st = group.getInstanceOf("daoTest");
    st = group.getInstanceOf("dao").add("m", model);
    result.put(model.entity + "Dao.txt", st.render());

    st = group.getInstanceOf("filterAndDto").add("m", model);
    result.put(model.entity + "filterAndDto.txt", st.render());

    st = group.getInstanceOf("controller").add("m", model);
    result.put(model.entity + "controller.txt", st.render());

    st = group.getInstanceOf("jspAndJs").add("m", model);
    result.put(model.entity + "jspAndJs.txt", st.render());

    st = group.getInstanceOf("tests").add("m", model);
    result.put(model.entity + "tests.txt", st.render());

//		st = group.getInstanceOf("debugTest").add("m", model);
//		result.put(model.entity + "DebugTest.txt", st.render());

    return result;

  }

  public static List<SuGeneratedFile> makeJournalCode2(SuJournalModel model) throws IOException {

    
    
    
    List<SuGeneratedFile> result = new ArrayList<SuJournalTemplateWriter.SuGeneratedFile>();

    STGroup group = new STRawGroupDir(model.templatesFolder);

    for (String templateName : model.templateNames) {
      if (!templateName.startsWith("gen_") || !templateName.endsWith(".st")) {
        continue;
      }
      //убираем расширение
      templateName = templateName.substring(0,templateName.length()-3);

      ST st = group.getInstanceOf(templateName).add("m", model);
      SuGeneratedFile gf = new SuGeneratedFile();
      gf.code = st.render();
      gf.templateName = templateName;
      
      readFileName(gf,model);
      result.add(gf);
      
    }

    return result;

  }

  private static void readFileName(SuGeneratedFile gf, SuJournalModel model) throws IOException {

    try (StringReader reader = new StringReader(gf.code);
        BufferedReader br = new BufferedReader(reader);
        StringWriter writer = new StringWriter();
        BufferedWriter bw = new BufferedWriter(writer);

    ) {

      boolean fileNameFound = false;
      String line;
      while ((line = br.readLine())!=null) {

        // ищем строку, описывающую имя файла
        if (!fileNameFound && line.matches("^[/# ]+.+")) {
          String fullFileName = line.replaceAll("^[/# ]+", "");

          int ind = fullFileName.lastIndexOf('.');
          if (ind > 1) {

            gf.fileExtension = fullFileName.substring(ind);
            ind = fullFileName.lastIndexOf('.', ind - 1);
            gf.packagePath = fullFileName.substring(0, ind);
            gf.fileName = fullFileName.substring(ind + 1);
            fileNameFound = true;
            continue;
          }
        }

        // записываем код в writer
        bw.write(line);
        bw.newLine();

      } ;

      if (fileNameFound) {
        bw.close();
        gf.code = writer.toString();
      } else {
        gf.fileExtension = ".java";
        gf.packagePath = "";
        gf.fileName = model.entity+gf.templateName+gf.fileExtension;
      }
      
      
    }

  }

  @Data
  public static class SuGeneratedFile {

    String templateName;
    String packagePath;
    String fileName;
    String fileExtension;
    String code;

  }

}
