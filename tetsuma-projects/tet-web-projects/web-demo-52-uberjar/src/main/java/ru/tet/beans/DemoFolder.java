package ru.tet.beans;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class DemoFolder {

	String name;
	
	String desc;
	
	List<DemoPage> pages;
	

	public String[] findSiblingPages(String pageName) {
		
		int ind = findPage(pageName);
		if (ind<0) {
			return null;
		}
		
		String[] result = new String[2];

		//prev page
		if (ind>0) {
			result[0] = pages.get(ind-1).getName();
		}
		
		//next page
		ind++;
		if (ind<pages.size()) {
			result[1] = pages.get(ind).getName();
		}
		
		return result;
		
	}
	
	public int findPage(String pageName) {
		
		for (int i = 0; i < pages.size(); i++) {
			DemoPage page = pages.get(i);
			if (page.getName().equals(pageName)) {
				return i;
			}
		}
		return -1;
	}	
	
	public DemoPage getNextPage(String pageName) {
		int ind = findPage(pageName);
		if (ind<0 || ind>=pages.size()) {
			return null;
		}
		return pages.get(ind+1);
	}

	public DemoPage getPrevPage(String pageName) {
		int ind = findPage(pageName);
		if (ind<0 || ind<=0) {
			return null;
		}
		return pages.get(ind-1);
	}
	
	
	
}
