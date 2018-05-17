package gui;

import java.awt.Dimension;

import javax.swing.JFrame;

import logic.Habitat;
import logic.Simulation;

public class WindowsPrincipal extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6169301488090101321L;
	
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 600;
	public static final String TITLE = "Vida Artificial - Pispirispis";
	
	private Simulation simulation;
	
	public WindowsPrincipal() {
		setSize(new Dimension(WIDTH, HEIGHT));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setTitle(TITLE);
		//me esta sirviendo el commit sebastiannnnnnnn
		
	}
	
	public void initSimulation() {
		this.simulation = new Simulation(100, 10,10,6,2,0.3,0.6,0.4,0.2,0.8,3,3);
		this.simulation.startSimulation();
		for (int i = 0; i < Habitat.poblation.size(); i++) {
			System.out.println("Imprime: "+Habitat.poblation.get(i).getPosition_X());
			System.out.println();
		}
	}
	

}
