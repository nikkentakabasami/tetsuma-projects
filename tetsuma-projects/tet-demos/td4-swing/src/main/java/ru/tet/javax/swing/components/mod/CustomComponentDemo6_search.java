package ru.tet.javax.swing.components.mod;

import java.util.List;
import java.util.stream.Collectors;

import javax.swing.SwingUtilities;

import org.apache.commons.lang3.StringUtils;

import ru.tet.javax.swing.aux.DemoDataSamples;
import ru.tet.javax.swing.aux.JFrameForTests;
import ru.tet.javax.swing.aux.comp.SearchPanel;
import ru.tet.javax.swing.aux.comp.SearchPopupMenu;

public class CustomComponentDemo6_search extends JFrameForTests {

	SearchPopupMenu menu;
	
	SearchPanel searchPanel;
	
	List<String> data;

	
	private boolean match(String item, String searchString) {
		return StringUtils.containsIgnoreCase(item, searchString);
//		return item.toLowerCase().contains(searchString.toLowerCase());
	}
	
	
	@Override
	protected void doInit() {
		initWithControlPanelAbove();

		controlPanel.addDebugLabel();
		
		data =DemoDataSamples.makeApplesList(5);
		
		searchPanel = new SearchPanel(searchText->{
			if (StringUtils.isBlank(searchText)) {
				return data;
			}
			return data.stream().filter(el->match(el,searchText)).collect(Collectors.toList());
		});
		
		
		searchPanel.addActionListener(event->{
			controlPanel.label1.setText("selected value:"+event.getActionCommand());
		});
		
		workPanel.add(searchPanel);

		
		
		menu = new SearchPopupMenu(searchPanel.getSearchFunction());
		workPanel.setComponentPopupMenu(menu);
		menu.addActionListener(event->{
			controlPanel.label1.setText("selected value:"+event.getActionCommand());
		});
		
		
//		menu.show(workPanel, 100, 100);
		
		
		
	}

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(() -> {
			CustomComponentDemo6_search frame = new CustomComponentDemo6_search();
			frame.init();
		});
	}

	
}
