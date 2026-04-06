package ru.tet.javax.swing.components;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ru.tet.beans.SuIdNameModel;
import ru.tet.javax.swing.aux.DemoDataSamples;
import ru.tet.javax.swing.aux.IdNameListCellRenderer;
import ru.tet.javax.swing.aux.JFrameForTests;


public class JListDemo extends JFrameForTests {

	JList list1;
	JList list2;
	JList list3;
	
	
	@Override
	protected void doInit() throws Exception {
		initWithControlPanelAbove();
		
		controlPanel.addDebugLabel();
		
	    //-----------------------------
		
	    workPanel.setLayout(new GridLayout(3,2,5,5)); 
	    
	    
	    workPanel.add(new JLabel("MULTIPLE_INTERVAL_SELECTION"));
		
		list1 = new JList(DemoDataSamples.apples); 
	    list1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); 
	 
	    JScrollPane sp = new JScrollPane(list1); 
	    sp.setPreferredSize(new Dimension(220, 150)); 
	    workPanel.add(sp);
	    

	    //-----------------------------
	    
	    workPanel.add(new JLabel("list with model (editable)"));
	    
	    DefaultListModel lm = new DefaultListModel(); 
		lm.addAll(Arrays.asList(DemoDataSamples.apples));
		list2 = new JList(lm); 

	    sp = new JScrollPane(list2); 
	    workPanel.add(sp);
		

	    controlPanel.addButton("add element", e->{
		    lm.addElement("Fuji"); 
	    });
	    controlPanel.addButton("remove element", e->{
	    	int ind = list2.getSelectedIndex();
	    	if (ind>=0) {
			    lm.remove(ind);
	    	}
	    });
	    
	    //-----------------------------
	    workPanel.add(new JLabel("list with ListCellRenderer"));
	    
		list3 = new JList(DemoDataSamples.makeItemsList(10).toArray(new SuIdNameModel[0])); 
	    list3.setCellRenderer(new IdNameListCellRenderer());

	    sp = new JScrollPane(list3); 
	    workPanel.add(sp);

	    
	    
	    
	    
	    

	    
	    
	    list1.addListSelectionListener(new ListSelectionListener() {  
	      public void valueChanged(ListSelectionEvent le) {
	    	  int[] indices = list1.getSelectedIndices();
	    	  String selectedApples = Arrays.stream(indices).mapToObj(i->DemoDataSamples.apples[i]).collect(Collectors.joining(","));
	    	  controlPanel.label1.setText("selected apples:"+selectedApples);
	      }  
	    });  

	    
	 		
		
		
		
//	    workPanel.setLayout(new BorderLayout()); 
//	    workPanel.setLayout(new BoxLayout(workPanel, BoxLayout.Y_AXIS));
	}

	public static void main(String[] args) throws Exception {
		JListDemo frame = new JListDemo();
		frame.doInit();
	}

}
