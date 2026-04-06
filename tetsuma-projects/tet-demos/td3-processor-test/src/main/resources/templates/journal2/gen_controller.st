#ru.alphatech.tm.controllers.<m.entity>Controller.java
package ru.alphatech.tm.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.alphatech.utils.misc.SUPage;

@Controller
public class <m.entity>Controller extends ArqAbstractController {

  public static final String FILTER_COOKIE_NAME = "<m.entity>Filter";
  
  @Autowired
  <m.entity>Dao dao;

  @Autowired
  <m.entity>Converter converter;
  
  
  private <m.entity>Filter makeDefaultFilter() {
    <m.entity>Filter filter = new <m.entity>Filter();

    return filter;
  }
  
  
  @RequestMapping(value = "/<m.entity>Journal", method = RequestMethod.GET)
  public String <m.entity>Journal(Model model, HttpServletRequest request, HttpSession session
      ) throws Exception {

    
    // начальные условия фильтрации
    <m.entity>Filter filter = readFilterFromUserPreference(FILTER_COOKIE_NAME, makeDefaultFilter());
    
    saveFilterToSession(FILTER_COOKIE_NAME, filter, session);
    model.addAttribute("mainFilter", filter);
    
    
    addHeaderModel(model, "Журнал по таблице <m.modelClassName>", request);

    return "<m.entityLowerCase>Journal";

  }  
  
  
  

  @RequestMapping(value = "/update<m.entity>Filter", method = RequestMethod.POST)
  @ResponseBody
  public <m.entity>Filter update<m.entity>Filter(@ModelAttribute("mainFilter") <m.entity>Filter filter,
      HttpServletResponse response, HttpSession session) throws Exception {

    filter.setRowCount(null);

    addFilterToUserPreference(filter, FILTER_COOKIE_NAME);
    saveFilterToSession(FILTER_COOKIE_NAME, filter, session);

    return filter;
  }

  @RequestMapping(value = "/clear<m.entity>Filter", method = RequestMethod.GET)
  @ResponseBody
  public <m.entity>Filter clear<m.entity>Filter(HttpServletResponse response, HttpSession session) throws Exception {

    <m.entity>Filter filter = makeDefaultFilter();
    
    return update<m.entity>Filter(filter, response, session);
    
  }

  @RequestMapping(value = "/get<m.entity>Json/{startRow}/{pageSize}", method = RequestMethod.GET)
  @ResponseBody
  public SUPage\<<m.dtoClassName>> get<m.entity>Json(HttpSession session,
      @PathVariable Integer startRow, @PathVariable Integer pageSize) {

    <m.entity>Filter filter = (<m.entity>Filter)getFilterFromSession(FILTER_COOKIE_NAME, session);
    if (filter == null) {
      return null;
    }

    filter.setPageSize(pageSize);
    filter.setStartRow(startRow);

    SUPage\<<m.modelClassName>> page = dao.find<m.entity>s(filter);
    SUPage\<<m.dtoClassName>> result = converter.convert(page);
    
    return result;

  }  
  
}

