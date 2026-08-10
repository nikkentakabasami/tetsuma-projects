package ru.tet.warodai;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Укороченный вариант DWordWriting
 * 
 * @author tetsuma
 *
 */
@Data
@NoArgsConstructor
public class DWordWritingShortModel implements Comparable<DWordWritingShortModel> {

	Integer id;

	String writing;

	public int getLength() {
		return writing.length();
	}

	@Override
	public int compareTo(DWordWritingShortModel o) {
		return o.getLength() - getLength();
	}

}
