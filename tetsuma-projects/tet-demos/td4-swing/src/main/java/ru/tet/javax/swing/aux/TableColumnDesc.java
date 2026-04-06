package ru.tet.javax.swing.aux;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TableColumnDesc {
	
	String columnName;
	Class<?> columnClass;
	boolean editable;
	String title;

}