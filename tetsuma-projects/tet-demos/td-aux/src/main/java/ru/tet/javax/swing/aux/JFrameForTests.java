package ru.tet.javax.swing.aux;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import ru.tet.javax.swing.images.DemoImages;

/**
 * простейший фрейм для создания тестов swing-компонентов.
 * 
 * Пример использования: JFrameForTests frame = new JFrameForTests();
 * frame.initWithControlPanelAbove(null);
 * 
 * frame.getWorkPanel().add(myComponent);
 * 
 * 
 * @author tetsuma
 *
 */
public class JFrameForTests extends JFrame {

	public static enum FrameMode {
		SIMPLE, WITH_CONTROL_PANEL
	}

	public static int INSET = 50;
	public static Dimension DEFAULT_LABEL_SIZE = new Dimension(100, 50);

	// contentPane этого фрейма
	protected JPanel contentPane;

	// инструментальная панель - содержит кнопки, метки, селекты и прочие рабочие элементы
	protected JControlPanelForTests controlPanel;

	// рабочая панель - полигон для компонентов
	protected JPanel workPanel;

	//тестовые картинки и иконки
	protected DemoImages demoImages;

	public JFrameForTests() {

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Мои тесты");
		demoImages = new DemoImages();

	}

	/**
	 * Метод для переопределения
	 */
	protected void doInit() throws Exception {
	}

	public void init() {
		try {
			doInit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Инициализация Простейший вариант - фрейм с одной панелью
	 * 
	 * @param wp
	 */
	public void initSimple(JPanel wp) {

		// центрируем
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(INSET, INSET, screenSize.width - INSET * 2, screenSize.height - INSET * 2);

		if (wp != null) {
			contentPane = wp;
			setContentPane(wp);
		} else {
			contentPane = new JPanel();
			setContentPane(contentPane);
		}

		setVisible(true);

	}

	public void initWithControlPanelAbove(JComponent wp) {
		initWithControlPanelAbove(wp, null);
	}

	/**
	 * Инициализация Создаёт инструментальную и рабочую панели. Инициализирует и
	 * показывает фрейм.
	 * 
	 * @param wp - позволяет задать свою рабочую панель
	 */
	public void initWithControlPanelAbove(JComponent wp, Dimension frameSize) {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		if (frameSize != null) {
			setBounds((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2,
					frameSize.width, frameSize.height);
		} else {
			setBounds(INSET, INSET, screenSize.width - INSET * 2, screenSize.height - INSET * 2);
		}

		if (wp == null) {
			workPanel = new JPanel();
			workPanel.setBorder(BorderFactory.createTitledBorder("workPanel"));
			wp = workPanel;
		} else {
			if (wp.getClass().isAssignableFrom(JPanel.class)) {
				workPanel = (JPanel) wp;
			}
		}

		controlPanel = new JControlPanelForTests();

		controlPanel.setMinimumSize(new Dimension(500, 200));
		wp.setMinimumSize(new Dimension(500, 300));

		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, controlPanel, wp);
		splitPane.setDividerLocation(200);
		setContentPane(splitPane);

		setVisible(true);

	}

	public void initWithControlPanelAbove() {
		initWithControlPanelAbove(null, null);
	}

	static int labelCounter = 0;

	public static JLabel createDemoLabel() {
		JLabel label = createDemoLabel("label " + labelCounter, Color.MAGENTA);
		return label;
	}

	public static JLabel createDemoLabel(String text, Color color) {
		labelCounter++;
		JLabel label = new JLabel(text);
		label.setVerticalAlignment(JLabel.TOP);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBorder(BorderFactory.createLineBorder(Color.black));
		label.setPreferredSize(DEFAULT_LABEL_SIZE);

		if (color != null) {
			label.setOpaque(true);
			label.setBackground(color);
			label.setForeground(Color.black);
		}

		return label;
	}

	public static JLabel createDemoLabel(String text, Color color, Dimension size) {

		JLabel label = createDemoLabel(text, color);
		label.setPreferredSize(size != null ? size : DEFAULT_LABEL_SIZE);

		return label;
	}

	/**
	 * Создание JLabel с абсолютным позициионированием
	 * 
	 * @param text
	 * @param color
	 * @param size
	 * @param location
	 * @return
	 */
	public static JLabel createDemoLabelAbsolute(String text, Color color, Dimension size, Point location) {
		JLabel label = createDemoLabel(text, color);

		label.setSize(size != null ? size : DEFAULT_LABEL_SIZE);
		label.setLocation(location);

		return label;
	}

	public static int makeRandomInt(int max) {
		return (int) Math.round(Math.random() * max);
	}

	public static ImageIcon createClasspathIcon(String imageName) {
		String imgLocation = "/images/" + imageName;
		java.net.URL imageURL = JFrameForTests.class.getResource(imgLocation);
		if (imageURL == null) {
			System.err.println("Resource not found: " + imgLocation);
			return null;
		} else {
			return new ImageIcon(imageURL);
		}
	}

	public JControlPanelForTests getControlPanel() {
		return controlPanel;
	}

	public JPanel getWorkPanel() {
		return workPanel;
	}

}
