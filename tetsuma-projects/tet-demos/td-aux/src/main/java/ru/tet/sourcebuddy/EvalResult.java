package ru.tet.sourcebuddy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvalResult {

	String expression;
	
	String comments;
	
	Object result;
	
}
