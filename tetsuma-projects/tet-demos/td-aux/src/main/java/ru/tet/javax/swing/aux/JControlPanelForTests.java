package ru.tet.javax.swing.aux;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.event.ChangeListener;

import ru.tet.beans.SuIdNameModel;

/**
 * Инструментальная панель. FlowLayout
 * 
 * @author tetsuma
 *
 */
public class JControlPanelForTests extends TetBoxPanel {

	
	protected TetBoxPanel currentHorizontalBox;

	List<JButton> buttons = new ArrayList<>();
	List<JLabel> labels = new ArrayList<>();
//	List<JCheckBox> checkboxes = new ArrayList<>();

	public JLabel label1;
	public JLabel label2;
	public JLabel label3;

	public JButton button1;
	public JButton button2;
	public JButton button3;

	public JComboBox comboBox1;
	public JComboBox comboBox2;

	public JSlider slider1;
	public JSlider slider2;
	public JSlider slider3;
	public JSlider slider4;
	public JSlider slider5;


	public JLabel compLabel1;
	public JLabel compLabel2;
	public JLabel compLabel3;
	public JLabel compLabel4;
	public JLabel compLabel5;
	public JLabel compLabel6;
	public JLabel compLabel7;
	public JLabel compLabel8;
	public JLabel compLabel9;
	public JLabel compLabel10;
	
	public JCheckBox checkbox1;
	public JCheckBox checkbox2;
	public JCheckBox checkbox3;
	
	
	public JTextArea textArea;
	
	
	public void clearContent() {
		buttons.clear();
		labels.clear();

		for (int i = 0; i < getComponentCount(); i++) {
			Component comp = getComponent(i);
			remove(comp);
		}
		newHorizontalBox();

		/*
		for (int i = 0; i < currentHorizontalBox.getComponentCount(); i++) {
			Component comp = currentHorizontalBox.getComponent(i);
			currentHorizontalBox.remove(comp);
		}
		 */
		
	}
	
	
	public JControlPanelForTests() {
		super(false);
		
		setBorder(BorderFactory.createTitledBorder("Test controls"));
		setPreferredSize(new Dimension(1000, 300));
		
		newHorizontalBox();
		
		
//		hb.addComp(label1, 100).addComp(tf1, 200).addComp(label2, 100, 20).addComp(tf2, 100).addComp(b,50,0);
//		hb.setBorder(BorderFactory.createLineBorder(Color.RED, 10));

	}
	
	public TetBoxPanel newHorizontalBox() {
		currentHorizontalBox = addHorizontalBox();
		return currentHorizontalBox;
	}
	
	
	/*
	@Override
    public Component add(Component comp) {
//		super.add(comp)
		currentHorizontalBox.addComp((JComponent)comp);
		return comp;
    }
    */
	
	
	public TetBoxPanel addTitleLabel(String title) {
		JLabel label = new JLabel(title);
		currentHorizontalBox.addComp(label);
		return currentHorizontalBox;
	}
	
	
	
		
	
	
	
	public JSlider addSlider(int min, int max, int value, String title,ChangeListener cl) {

		JSlider slider = new JSlider(min, max, value);
//		sliderHoriz.setPreferredSize(new Dimension(600, 50));
//		slider.setMajorTickSpacing(20);

		// надписи каждые 40 единиц
//		slider.setLabelTable(slider.createStandardLabels(40));
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);

		slider.addChangeListener(cl);
		
		Box hp = createTitledBox(slider, title);
		currentHorizontalBox.addComp(hp);

		if (slider1 == null) {
			slider1 = slider;
		} else if (slider2 == null) {
			slider2 = slider;
		} else if (slider3 == null) {
			slider3 = slider;
		} else if (slider4 == null) {
			slider4 = slider;
		} else if (slider5 == null) {
			slider5 = slider;
		}

		return slider;
	}

	
	
	public void addTextArea() {
		textArea = new JTextArea();
		JScrollPane sp = new JScrollPane(textArea);
		sp.setPreferredSize(new Dimension(300, 200));
		currentHorizontalBox.addComp(sp,0);
	}	
	
	public JCheckBox addCheckbox(String title, ActionListener al) {
		JCheckBox b = new JCheckBox(title);
		if (al != null) {
			b.addActionListener(al);
		}
		currentHorizontalBox.addComp(b);
		
		if (checkbox1 == null) {
			checkbox1 = b;
		} else if (checkbox2 == null) {
			checkbox2 = b;
		} else if (checkbox3 == null) {
			checkbox3 = b;
		}		
		
		return b;
	}	
		
	public <E> JComboBox<E> addComboBox(E[] data, ActionListener listener) {
		return addComboBox(data, listener, null);
	}
	
	public <E> JComboBox<E> addComboBox(E[] data, ActionListener listener, String title) {
		JComboBox cb = new JComboBox(data);
		cb.addActionListener(listener);

		Class<?> elementClass = data.getClass().getComponentType();
		if (elementClass.isAssignableFrom(SuIdNameModel.class)) {
			cb.setRenderer(new IdNameListCellRenderer());
		}

		if (title!=null) {
			Box hp = createTitledBox(cb, title);
			currentHorizontalBox.addComp(hp);
		} else {
			currentHorizontalBox.addComp(cb);
		}

		if (comboBox1 == null) {
			comboBox1 = cb;
		} else if (comboBox2 == null) {
			comboBox2 = cb;
		}

		return cb;
	}

	public JButton addButton(String title, ActionListener al) {
		JButton b = new JButton(title);
		if (al != null) {
			b.addActionListener(al);
		}
		currentHorizontalBox.addComp(b);
		buttons.add(b);

		switch (buttons.size()) {
		case 1:
			button1 = b;
			break;
		case 2:
			button2 = b;
			break;
		case 3:
			button3 = b;
		}
		return b;
	}

	public JLabel addDebugLabel() {
		return addDebugLabel(null);
	}

	public JLabel addDebugLabel(String title) {
		JLabel label = new JLabel(title != null ? title : "");
		currentHorizontalBox.addComp(label);
		labels.add(label);

		switch (labels.size()) {
		case 1:
			label1 = label;
			break;
		case 2:
			label2 = label;
			break;
		case 3:
			label3 = label;
		}

		return label;
	}

	public JButton getButton(int ind) {
		return buttons.get(ind);
	}

	public JLabel getLabel(int ind) {
		return labels.get(ind);
	}

	
	
	

	private JLabel createCompLabel(String title) {

		JLabel label = new JLabel(title, JLabel.CENTER);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		if (compLabel1==null) {
			compLabel1 = label;			
		} else if (compLabel2==null) {
			compLabel2 = label;			
		} else if (compLabel3==null) {
			compLabel3 = label;			
		} else if (compLabel4==null) {
			compLabel4 = label;			
		} else if (compLabel5==null) {
			compLabel5 = label;			
		} else if (compLabel6==null) {
			compLabel6 = label;			
		} else if (compLabel7==null) {
			compLabel7 = label;			
		} else if (compLabel8==null) {
			compLabel8 = label;			
		} else if (compLabel9==null) {
			compLabel9 = label;			
		} else if (compLabel10==null) {
			compLabel10 = label;			
		};
		return label;
		
	}	
	
	private Box createTitledBox(Component comp, String title) {
		
		JLabel label = createCompLabel(title);
		
		Box box = Box.createVerticalBox();
		box.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		box.add(label);
		box.add(comp);
		
		return box;
	}
	
}
