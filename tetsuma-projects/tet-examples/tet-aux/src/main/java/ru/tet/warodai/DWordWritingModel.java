package ru.tet.warodai;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Один из способов записи слова (через кандзи)
 * 
 * @author tetsuma
 *
 */
@Data
@NoArgsConstructor
public class DWordWritingModel implements Serializable {

	Integer id;
	
	String writing;

	//Запись корня глагола (без спрягаемого окончания)
	String rootWriting;
	
	
	Integer defaultWordId;
	
	public DWordWritingModel(String writing) {
		this.writing = writing;
	}

	
}
