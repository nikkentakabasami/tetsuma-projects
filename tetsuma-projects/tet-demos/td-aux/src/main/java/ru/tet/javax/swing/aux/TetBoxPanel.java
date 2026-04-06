package ru.tet.javax.swing.aux;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Панель с моим кастомным лейаутом TetBoxLayout.
 * Аналог Box. Располагает компоненты по горизонтали или вертикали. 
 * Содержит кучу вспомогательных методов.
 * 
 * TetBoxPanel vertBoxPanel = new TetBoxPanel(false);
 * TetBoxPanel hb = vertBoxPanel.addHorizontalBox();
 * hb.addComp(label1, 100).addComp(tf1, 200).addComp(label2, 100, 20).addComp(tf2, 100).addComp(b,-1,0);
 * 
 * Горизонтальный TetBoxPanel разбивается на ячейки, расположенные в ряд.
 * Каждая ячейка содержит свой компонент.
 * При добавлении компонента можно задать фиксированную ширину ячейки.
 * hb.addComp(tf1, 200)
 * 
 * Если задать нулевую ширину - компонент будет растягиваться по оставшемуся пространсту:
 * hb.addComp(tf1, 0)
 * или
 * hb.addGlueComp(tf1)
 * 
 * Если задать отрицательную ширину - компонент будет иметь предпочтительный размер
 * hb.addComp(tf1, -1)
 * или
 * hb.addComp(tf1)
 * 
 * Между ячейками есть промежуток - offset. По умолчанию 5 пикселей.
 * Его можно изменить так:
 * hb.setDefaultOffset(20);
 * или можно задать его для конкретного компонента:
 * hb.addComp(label2, 100, 20)
 * 
 * По умолчанию компоненты растягиваются по размерам ячейки.
 * Это можно отключить задав атрибуты expandLength, expandWidth. 
 * 	hb.setExpandWidth(false);
 * 	hb.setExpandLength(false);
 * Тогда все компоненты будут иметь предпочтительный размер.
 * 
 * @author tetsuma
 *
 */
public class TetBoxPanel extends JPanel {

	TetBoxLayout layout;

	public TetBoxPanel(boolean horizontal) {
		layout = new TetBoxLayout(horizontal);
		setLayout(layout);
	}

	public void setDefaultOffset(int defaultOffset) {
		layout.setDefaultOffset(defaultOffset);
	}

	/**
	 * не растягивать по длине ячейки. Использовать предпочтительный размер компонента
	 * @param expandWidth
	 */
	public void setExpandLength(boolean expandLength) {
		layout.setExpandLength(expandLength);
	}

	/**
	 * не растягивать по ширине ячейки. Использовать предпочтительный размер компонента
	 * @param expandWidth
	 */
	public void setExpandWidth(boolean expandWidth) {
		layout.setExpandWidth(expandWidth);
	}

	/**
	 * Добавление горизонтальной панели, высота будет рассчитана на основе предпочтительного размера вложенных компонентов.
	 * @param height
	 * @return
	 */
	public TetBoxPanel addHorizontalBox() {
		return addHorizontalBox(-1);
	}

	/**
	 * Добавление вертикальной панели, ширина будет рассчитана на основе предпочтительного размера вложенных компонентов.
	 * @param height
	 * @return
	 */
	public TetBoxPanel addVerticalBox() {
		return addVerticalBox(-1);
	}

	/**
	 * Добавление горизонтальной панели с заданной высотой
	 * @param height
	 * @return
	 */
	public TetBoxPanel addHorizontalBox(int height) {
		TetBoxPanel box = new TetBoxPanel(true);
		addComp(box, height);
		return box;
	}

	/**
	 * Добавление вертикальной панели с заданной высотой
	 * @param height
	 * @return
	 */
	public TetBoxPanel addVerticalBox(int width) {
		TetBoxPanel box = new TetBoxPanel(false);
		addComp(box, width);
		return box;
	}

	/*
	public TetBoxPanel addComp(JComponent comp, int width, int offset, boolean expandLength, boolean expandWidth) {
		TetBoxLayoutConstraint c = TetBoxLayoutConstraint.build(width).withOffset(offset);
		c.setExpandLength(expandLength);
		c.setExpandWidth(expandWidth);
		add(comp, c);
		return this;
	}	
	*/

	/**
	 * 
	 * @param comp
	 * @param width  ширина ячейки. 0 - ячейка будет растянута как glue, -1 - ячейка будет иметь предпочтительную ширину компонента
	 * @param offset сдвиг относительно предыдущей ячейки. По умолчанию =defaultOffset
	 * @return
	 */
	public TetBoxPanel addComp(JComponent comp, int width, int offset) {
		add(comp, TetBoxLayoutConstraint.build(width).withOffset(offset));
		return this;
	}

	public TetBoxPanel addComp(JComponent comp, int width) {
		add(comp, TetBoxLayoutConstraint.build(width));
		return this;
	}

	public TetBoxPanel addComp(JComponent comp) {
		return addComp(comp, -1);
	}

	
	public TetBoxPanel addGlueComp(JComponent comp) {
		return addComp(comp, 0);
	}

	/**
	 * Добавить компонент, чья длина будет растянута
	 * @param comp
	 * @param offset
	 * @return
	 */
	public TetBoxPanel addGlueComp(JComponent comp, int offset) {
		return addComp(comp, 0, offset);
	}

	/**
	 * Необязательный метод.
	 * Проще задать offset
	 * 
	 * @param width
	 * @return
	 */
	public TetBoxPanel addStrut(int width) {
		Component strut;
		if (layout.horizontal) {
			strut = Box.createHorizontalStrut(width);
		} else {
			strut = Box.createVerticalStrut(width);
		}

		addComp((JComponent) strut, width);
		return this;
	}

	/**
	 * Добавление растягивающегося промежутка.
	 * @return
	 */
	public TetBoxPanel addGlue() {
		Component strut;
		if (layout.horizontal) {
			strut = Box.createHorizontalStrut(0);
		} else {
			strut = Box.createVerticalStrut(0);
		}

		addComp((JComponent) strut, 0);
		return this;
	}

//	public TetBoxPanel add(JComponent... components) {
//		for (int i = 0; i < components.length; i++) {
//			add(components[i]);
//		}
//		return this;
//	}
	
	public TetBoxPanel addLabel(String title) {
		return addComp(new JLabel(title), -1);
	}
	
	

}
