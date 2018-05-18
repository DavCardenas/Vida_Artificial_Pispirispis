package logic;

import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.plaf.basic.BasicScrollPaneUI.HSBChangeListener;

public class Simulation implements Runnable {

	public final static int DAYS_FOR_YEAR = 365;
	public final static int DAYS_FOR_MONTH = 30;

	private Habitat habitat; // habitat
	public static int time_simulation; // tiempo que dura la simulacion en años
	private boolean start_simulation; // permite controlar cuando inicia la simulacion
	private Thread simulation; // hilo que controla la simulacion
	private ArrayList<Data> listMale; //Lista de hombres

	public Simulation(double size, int init_poblation, int init_food, double init_energy, double init_energy_alicanola,
			double rate_food, double rate_male, double rate_female, double rate_inpopios, double rate_tropus,
			int ptime_simulation, double pinit_energy_fifirufa) {
		
		habitat = new Habitat(size, init_poblation, init_food, init_energy, init_energy_alicanola, rate_food, rate_male,
				rate_female, rate_inpopios, rate_tropus, pinit_energy_fifirufa);
		
		time_simulation = ptime_simulation;
		start_simulation = false;
		simulation = new Thread(this);
		this.listMale = new ArrayList<Data>();
//		this.listMale.add(new Data(1, 0.1));
//		this.listMale.add(new Data(2, 0.2));
	}
	
	/**
	 * inicia el habitat con los valores de entrada
	 */
	private void startHabitat() {
		habitat.generateInitPoblation();
		habitat.generateInitFood();
		for (int i = 0; i < Habitat.poblation.size(); i++) {
			Habitat.poblation.get(i).startMove();
		}
	}
	
	/**
	 * inicia la simulacion
	 */
	public void startSimulation() {
		//start_simulation = true;
		startHabitat();
		simulation.start();
	}
	
	/**
	 * detiene la simulacion pero no la reinicia
	 */
	public void stopSimulation() {
		start_simulation = false;
		for (int i = 0; i < Habitat.poblation.size(); i++) {
			Habitat.poblation.get(i).stopMove();
		}
	}
	
	/**
	 * resetea la simulacion, se crean todos los objs nuevamente
	 */
	public void resetSimulation(double size, int init_poblation, int init_food, double init_energy, double init_energy_alicanola,
			double rate_food, double rate_male, double rate_female, double rate_inpopios, double rate_tropus,
			int ptime_simulation,  double pinit_energy_fifirufa) {
		
		habitat = new Habitat(size, init_poblation, init_food, init_energy, init_energy_alicanola, rate_food, rate_male,
				rate_female, rate_inpopios, rate_tropus,  pinit_energy_fifirufa);
		
		time_simulation = ptime_simulation;
		start_simulation = false;
		simulation = new Thread(this);
	}

//	public void getAmmount_age_adolecence() {
//		this.habitat.getAmmount_age_adolecence();
//	}
	
	
	
	@Override
	public void run() {
		for (int i = 0; i < (time_simulation*DAYS_FOR_YEAR) && !start_simulation; i++) { // simulacion cada dia
			this.listMale.add(new Data(i, this.habitat.getSize_poblation()));//se agrega los hombres
	
			for (int j = 0; j < Habitat.poblation.size(); j++) {
				Habitat.poblation.get(j).evolution(); // incrementa la edad de cada pispirispi
				if (i%DAYS_FOR_YEAR == 0) { // cada año
					Habitat.poblation.get(j).calculateSpeed();
				}
			}
			
			if (i%DAYS_FOR_MONTH == 0) { // genera comida cada 30 dias
				habitat.generateFood();
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	
	
	public ArrayList<Data> getListMale() {
		return listMale;
	}

	public void setListMale(ArrayList<Data> listMale) {
		this.listMale = listMale;
	}

}
