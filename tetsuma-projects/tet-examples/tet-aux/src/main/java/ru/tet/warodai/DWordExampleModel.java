package ru.tet.warodai;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DWordExampleModel implements Serializable {
	
	Integer id;
	

	Integer translationId;
	
	
	String example;
	
}
