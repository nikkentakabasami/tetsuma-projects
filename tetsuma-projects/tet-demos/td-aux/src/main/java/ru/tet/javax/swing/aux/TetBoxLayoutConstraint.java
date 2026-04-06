package ru.tet.javax.swing.aux;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TetBoxLayoutConstraint {
	
	// =0 - растянуть, заняв оставшееся место; <0 - использовать preferredSize компонента
	int width;
	
	//  <0 - default offset
	int offset = -1;
	
//	boolean expandLength = true;
//	boolean expandWidth = true;
	
	
	
	public static TetBoxLayoutConstraint build(int width) {
		TetBoxLayoutConstraint result = new TetBoxLayoutConstraint();
		result.setWidth(width);
		return result;
	}
	
	public TetBoxLayoutConstraint withOffset(int offset) {
		setOffset(offset);
		return this;
	}

//	public TetBoxLayoutConstraint withExpand(boolean expand) {
//		setExpand(expand);
//		return this;
//	}
	
	
}
