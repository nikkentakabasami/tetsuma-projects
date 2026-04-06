package ru.tet.javax.swing.aux.comp;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class CustomLabel extends JLabel {

	final Color bgColor = new Color(0xC8_C8_FF);
	
	public CustomLabel(String text) {
		super(text);
		
		setForeground(Color.BLUE);
	    //setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 2));
	    setBorder(BorderFactory.createLineBorder(Color.BLACK));
//	    System.out.println(getFont().getName());
	    Font font = new Font("Serif", Font.PLAIN, 25);
	    setFont(font);
		
	}

	@Override protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setPaint(new GradientPaint(50f, 0f, Color.WHITE, getWidth(), getHeight(), bgColor));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();
        super.paintComponent(g);
      }
};