package gui;

import java.awt.Dimension;

import javax.swing.JFrame;

public class WindowsPrincipal extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6169301488090101321L;
	
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 600;
	public static final String TITLE = "Vida Artificial - Pispirispis";
	
	public WindowsPrincipal() {
		setSize(new Dimension(WIDTH, HEIGHT));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setTitle(TITLE);
		
	}

}
