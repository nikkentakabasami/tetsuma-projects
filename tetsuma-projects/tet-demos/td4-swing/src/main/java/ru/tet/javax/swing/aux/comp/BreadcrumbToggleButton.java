package ru.tet.javax.swing.aux.comp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JToggleButton;

import ru.tet.javax.swing.aux.BreadcrumbToggleIcon;
import ru.tet.javax.swing.aux.GrayIcon;

/**
 * Пример того, как можно изменить вид и форму компонента.
 * 
 * @author tetsuma
 *
 */
public class BreadcrumbToggleButton extends JToggleButton {
	
	BreadcrumbToggleIcon icon;
	
	//кнопка - первая в панели
	boolean first;

	protected BreadcrumbToggleButton(String title, Color color, boolean first) {
		super(title, new GrayIcon());
		this.first = first;
		icon = new BreadcrumbToggleIcon(this,color);
		
	}

	@Override
	public void updateUI() {
		super.updateUI();
		
		setBorder(BorderFactory.createEmptyBorder(0, first?0:15, 0, 5));

		//чтобы задний план на закрашивался цветом фона
		setContentAreaFilled(false);
		
		//чтобы не прорисовывал рамку, если кнопка в фокусе
		setFocusPainted(false);
//		setOpaque(false);
		
		//содержимое текста в кнопке
		setHorizontalAlignment(LEFT);
		// setIcon(new TestIcon());
	}

	@Override
	public boolean contains(int x, int y) {
		return icon.getShape().contains(x, y);
//		return Optional.ofNullable(icon.getShape()).map(s -> s.contains(x, y)).orElseGet(() -> super.contains(x, y));
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(150, 25);
	}

	@Override
	protected void paintComponent(Graphics g) {
		icon.paintIcon(this, g, 0, 0);

		//прорисовка иконки и текста
		super.paintComponent(g);
	}

	public boolean isFirst() {
		return first;
	}
	

}