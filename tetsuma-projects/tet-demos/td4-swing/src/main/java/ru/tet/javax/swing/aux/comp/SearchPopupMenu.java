package ru.tet.javax.swing.aux.comp;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.function.Function;

import javax.swing.JPopupMenu;

public class SearchPopupMenu extends JPopupMenu {

	SearchPanel searchPanel;
	
	
	public SearchPopupMenu(Function<String, List<String>> searchFunction) {
		searchPanel = new SearchPanel(searchFunction);
	    add(searchPanel);
		
		searchPanel.addActionListener(event->{
			setVisible(false);
		});
	    
	}
	
	public void addActionListener(ActionListener l) {
		searchPanel.addActionListener(l);
	}
	
	
	@Override
	public void show(Component invoker, int x, int y) {
		super.show(invoker, x, y);
		searchPanel.getTextField().grabFocus();
	}
	
	
	
	
	
	
	
	
}
