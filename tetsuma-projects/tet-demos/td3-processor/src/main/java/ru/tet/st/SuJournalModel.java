package ru.tet.st;

import java.util.List;

import lombok.Data;


/**
 * Модель, которую мы предоставляем string template, чтобы сгенерировать исходники.
 * Содержит данные, необходимые для создания журнала на основе orm-класса. 
 * 
 * @author tetsuma
 *
 */
@Data
public class SuJournalModel {
	
  //класс модели, но без суффикса Model. Например "User"
	String entity;

	//класс модели. Например "UserModel"
	String modelClassName;
  String modelFullClassName;

  //класс для использования на клиенте. Например "UserDto" 
  String dtoClassName;
	String dtoFullClassName;
	
	//атрибуты модели
	List<ClassProperty> propertyList;
	

	String templatesFolder;
	List<String> templateNames;
	
  //entity без заглавной буквы. Например "user"
	public String getEntityLowerCase() {
		return Character.toUpperCase(entity.charAt(0))+entity.substring(1);
	}
	
	
  @Data
  public static class ClassProperty {
    String propertyName;
    String propertyNameUpper;
    String type;
    String modifiers;

    boolean typePrimitiveBoolean;
    boolean typeBoolean;
    
    boolean typeString;
    boolean typeInt;
    boolean typeDate;
    
    
    public ClassProperty(String propertyName, String type, String modifiers) {
      this.propertyName = propertyName;
      
      this.type = type;
      this.modifiers = modifiers;
      
      init();
      
    }

    public ClassProperty() {
    }

    public void init() {
      this.propertyNameUpper = Character.toUpperCase(propertyName.charAt(0))+propertyName.substring(1);
      this.typePrimitiveBoolean = "boolean".equals(type);
      this.typeBoolean = "Boolean".equals(type);
      
      this.typeString = "String".equals(type);
      this.typeDate = "Date".equals(type);
      this.typeInt = "Integer".equals(type) || "Long".equals(type) || "Short".equals(type);
    }
    
    public String getGetter() {
      return (typePrimitiveBoolean?"is":"get")+propertyNameUpper;
    }
    public String getSetter() {
      return "set"+propertyNameUpper;
    }

    public String getTypeForDto() {
      return typeInt?type:"String";
    }
    
  } 	
	
	
}
