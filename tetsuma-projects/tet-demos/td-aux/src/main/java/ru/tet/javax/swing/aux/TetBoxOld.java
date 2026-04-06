package ru.tet.javax.swing.aux;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.border.Border;


/**
 * Панель с BoxLayout, содержит удобные методы размещения компонентов.
 * В конструктор помещается типа панели: horizontal и широты компонентов.
 *
 * Пример использования:
	TetBox box1 = new TetHorizontalBox(true);
	box1.addComp(label1, 100).addComp(tf1, 200).addStrut(10).addComp(label2, 100).addComp(tf2, 300, false);
	
	TetBox box2 = new TetHorizontalBox(true,100,100,10,100,100);
	box2.addComp(label3).addComp(tf3).addStrut().addComp(label4).addComp(tf4).addGlue();
	
	TetBox vbox = new TetBox(false);
	vbox.addComp(box1, 35).addComp(box2, 35).addGlue();
 * 
 */
public class TetBoxOld extends Box {

	int[] widths = null;
	boolean horizontal;

	public TetBoxOld(boolean horizontal, int... widths) {
		super(horizontal?BoxLayout.X_AXIS:BoxLayout.Y_AXIS);
		this.horizontal = horizontal;
		Border innerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(innerBorder);
		if (widths!=null && widths.length>0) {
			this.widths = widths;
		}
		
	}
	
	
	public TetBoxOld addStrut(int width) {
		add(Box.createHorizontalStrut(width));
		return this;
	}

	public TetBoxOld addStrut() {
		int ind = getComponentCount();
		int size = 10;
		if (widths!=null) {
			size = widths[ind];
		}
		
		add(Box.createHorizontalStrut(size));
		return this;
	}
	
	
	public TetBoxOld addComp(JComponent comp) {
		int ind = getComponentCount();
		if (widths!=null) {
			int size = widths[ind];
			addComp(comp,size);
		} else {
			add(comp);
		}
		return this;
	}
	
	public TetBoxOld addComp(JComponent comp, int width) {
		return addComp(comp, width, true);
	}
	
	/**
	 * 
	 * @param comp
	 * @param width - желаемая ширина компонента
	 * @param setMaxSize - false, если нужно чтобы компонент растягивался
	 * @return
	 */
	public TetBoxOld addComp(JComponent comp, int width, boolean setMaxSize) {
		Dimension size = fixSize(comp.getPreferredSize(),width);
		comp.setPreferredSize(size);
		
		if (setMaxSize) {
			size = fixSize(comp.getMaximumSize(),width);
			comp.setMaximumSize(size);
		}
		
		add(comp);
		return this;
	}

	private Dimension fixSize(Dimension size,int width) {
		if (horizontal) {
			size.width = width;
		} else {
			size.height = width;
		}
		return size;
	}
	
	public TetBoxOld addGlue() {
		add(Box.createHorizontalGlue());
		return this;
	}
	
	
	
	
}
