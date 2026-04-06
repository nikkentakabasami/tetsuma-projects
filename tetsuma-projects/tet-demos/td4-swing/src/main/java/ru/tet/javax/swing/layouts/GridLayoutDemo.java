package ru.tet.javax.swing.layouts;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import ru.tet.javax.swing.aux.JFrameForTests;


/*
   GridLayout 
  Для показа группы компонентов равного размера.
  Показываются в таблице (в конструкторе указываем количество строк и столбцов)
 */
public class GridLayoutDemo extends JFrameForTests {

	
	
	@Override
	protected void doInit() {
		initWithControlPanelAbove();
		
//		workPanel.setLayout(new BorderLayout()); 
	    
		controlPanel.addButton("new GridLayout(0,1)", e->{
		    workPanel.setLayout(new GridLayout(0,1)); 
			workPanel.revalidate();
		});

		controlPanel.addButton("new GridLayout(3,3,5,5)", e->{
		    workPanel.setLayout(new GridLayout(3,3,5,5)); 
			workPanel.revalidate();
		});

		controlPanel.addButton("new GridLayout(2,0)", e->{
		    workPanel.setLayout(new GridLayout(2,0)); 
			workPanel.revalidate();
		});
		
		
		
	    workPanel.setLayout(new GridLayout(3,3,5,5)); 

	    workPanel.add(new JButton("start 1")); 
	    workPanel.add(new JButton("start 2")); 
	    workPanel.add(new JButton("start 3")); 
	    workPanel.add(new JButton("start 4")); 
	    workPanel.add(new JButton("start 5")); 
	    workPanel.add(new JButton("start 6")); 
	    workPanel.add(new JButton("Okay")); 		

	    
	}
	
	
	
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			GridLayoutDemo demo = new GridLayoutDemo();
			demo.doInit();
		});		
	}
	
	
	
}
