#ru.alphatech.tm.dao.impl.<m.entity>DaoImpl.java
package ru.alphatech.tm.dao.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ru.alphatech.utils.dao.SUAbstractDao;
import ru.alphatech.utils.dao.SUSelectQuery;
import ru.alphatech.utils.misc.SUDaoUtils;
import ru.alphatech.utils.misc.SUPage;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class <m.entity>DaoImpl extends SUAbstractDao implements <m.entity>Dao {
  
  @Override
  public SUPage\<<m.modelClassName>> find<m.entity>s(<m.entity>Filter filter) {
    
    SUSelectQuery selectQuery = new SUSelectQuery("from <m.modelClassName> r");

    TcsConverters.fixSort(filter, null);
    selectQuery.setOrderBy(filter.getSortField(), "order by id");

    selectQuery.setSelectPart("select r");

	<m.propertyList:{p|

		<if(p.typeString)>
		    selectQuery.addLikeConditionToWhere("<p.propertyName>", filter.get<p.propertyNameUpper>());
		<elseif(p.typeDate)>
		    selectQuery.addDateConditionToWhere("<p.propertyName>", filter.get<p.propertyNameUpper>());
		<else>
		    selectQuery.addNumberConditionToWhere("<p.propertyName>", filter.get<p.propertyNameUpper>());
		<endif>
	
	}>

    SUPage\<<m.modelClassName>> result = SUDaoUtils.getPagedSubList(currentSession(), selectQuery, filter,
        <m.modelClassName>.class);

    return result;    
    
  }
  
}
