#ru.alphatech.tm.filters.<m.entity>Filter.java;
package ru.alphatech.tm.filters;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.alphatech.utils.filters.SUSearchFilter;


@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class <m.entity>Filter extends SUSearchFilter {

<m.propertyList:{f|
	String <f.propertyName>;
}>

}

