package ru.tet.javax.swing.aux.comp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.function.Function;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.EventListenerList;

public class SearchPanel extends JPanel {

	JTextField textField;
	JList<String> list;
	DefaultListModel<String> listModel;
	JScrollPane scrollPanel;
	
	
	List<String> data;
	String selectedItem;

	Function<String, List<String>> searchFunction;

//	EventListener valueSelectedListener;

	protected EventListenerList listenerList = new EventListenerList();


	public SearchPanel(Function<String, List<String>> searchFunction) {
		this.searchFunction = searchFunction;

		setPreferredSize(new Dimension(500, 400));
		setLayout(new BorderLayout());

		createList();
		createTextField();

		add(textField, BorderLayout.NORTH);
		add(scrollPanel, BorderLayout.CENTER);

		doSearch();
		
	}

	private void createList() {
		listModel = new DefaultListModel<>();

		list = new JList<>(listModel);

		list.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if ((e.getKeyCode() == KeyEvent.VK_UP) && ((list.getSelectedIndex() == 0) || e.isControlDown())) {
					list.clearSelection();
					textField.grabFocus();
				}
				
				
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					selectedItem = list.getSelectedValue();
					if (selectedItem != null) {
						fireSelected();
					}
				}
			}
		});

		scrollPanel = new JScrollPane(list, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPanel.setFocusable(false);
		scrollPanel.getVerticalScrollBar().setFocusable(false);
		scrollPanel.setBorder(null);
		
	}

	private void createTextField() {
		textField = new JTextField();
//		textField.setPreferredSize(new Dimension(400, 25));
		textField.addFocusListener(null);

		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					doSearch();
				}

				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					list.setSelectedIndex(0);
					list.grabFocus();
				}
			}
		});

	}

	public void doSearch() {
		if (searchFunction != null) {
			data = searchFunction.apply(textField.getText());
		}
		updateList();
	}

	public void setData(List<String> data) {
		this.data = data;
		updateList();
	}

	public void updateList() {
		listModel.clear();
		listModel.addAll(data);
	}
	
	public void addActionListener(ActionListener l) {
		listenerList.add(ActionListener.class, l);
	}

	protected void fireSelected() {
		ActionListener[] listeners = listenerList.getListeners(ActionListener.class);
		for (int i = 0; i < listeners.length; i++) {
			listeners[i].actionPerformed(new ActionEvent(this, i, selectedItem));
		}
	}
	
	

	public JTextField getTextField() {
		return textField;
	}

	public JList<String> getList() {
		return list;
	}

	public String getSelectedItem() {
		return selectedItem;
	}
	
	public List<String> getData() {
		return data;
	}
	
	public void setSearchFunction(Function<String, List<String>> searchFunction) {
		this.searchFunction = searchFunction;
	}

	public Function<String, List<String>> getSearchFunction() {
		return searchFunction;
	}
	
}
