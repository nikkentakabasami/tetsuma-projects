package ru.tet.javax.swing.aux;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class GBC extends GridBagConstraints {

	Insets defaultInsets = new Insets(5, 5, 5, 5);
	
	public GBC(int x, int y) {
		gridx = x;
		gridy = y;
		
		fill = GridBagConstraints.BOTH;
		insets = defaultInsets;
		anchor = NORTHWEST;
	}
	
	
	public GBC(int x, int y, int width) {
		this(x, y);
		gridwidth = width;
	}

	public GBC(int x, int y, int width, int height) {
		this(x, y);
		gridwidth = width;
		gridheight = height;
	}

	public GBC(int x, int y, int width, int height, double wx, double wy) {
		this(x, y, width, height);
		weightx = wx;
		weighty = wy;

	}

	public GBC(int x, int y, int width, int height, double wx, double wy, int fill) {
		this(x, y, width, height);
		weightx = wx;
		weighty = wy;
		this.fill = fill;
	}

	public GBC(int x, int y, int width, int height, double wx, double wy, int fill, Insets insets) {
		this(x, y, width, height);
		weightx = wx;
		weighty = wy;
		this.fill = fill;
		this.insets = insets;
	}

	public GBC(int x, int y, int width, int height, double wx, double wy, int fill, Insets insets, int anchor) {
		this(x, y, width, height);
		weightx = wx;
		weighty = wy;
		this.fill = fill;
		this.insets = insets;
		this.anchor = anchor;
	}

	public GBC(int x, int y, int width, int height, double wx, double wy, int fill, int anchor) {
		this(x, y, width, height);
		weightx = wx;
		weighty = wy;
		this.fill = fill;
		this.anchor = anchor;
	}

	public void setDefaultInsets(Insets defaultInsets) {
		this.defaultInsets = defaultInsets;
	}
	
}