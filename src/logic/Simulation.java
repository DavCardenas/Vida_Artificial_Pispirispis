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
	private ArrayList<Data> listPoblationSize; //Lista de tamaño de poblacion
	
	private ArrayList<Data> listMale; //Lista de tamaño de Machos
	private ArrayList<Data> listFemale; //Lista de tamaño de Feminas
	private ArrayList<Data> listAdults; //Lista de tamaño de Adultos
	private ArrayList<Data> listAdolecences; //Lista de tamaño de Adolescentes
	private ArrayList<Data> listChildhood; //Lista de tamaño de Niños
	private ArrayList<Data> listBirth; //Lista de tamaño de Nacimientos
	private ArrayList<Data> listTropus; //Lista de tamaño de Tropus
	private ArrayList<Data> listInopios; //Lista de tamaño de Inopios
	private ArrayList<Data> listDies; //Lista de tamaño de Muertes
	private ArrayList<Data> listOlds; //Lista de tamaño de Ancionos

	public Simulation(double size, int init_poblation, int init_food, double init_energy, double init_energy_alicanola,
			double rate_food, double rate_male, double rate_female, double rate_inpopios, double rate_tropus,
			int ptime_simulation, double pinit_energy_fifirufa) {
		
		habitat = new Habitat(size, init_poblation, init_food, init_energy, init_energy_alicanola, rate_food, rate_male,
				rate_female, rate_inpopios, rate_tropus, pinit_energy_fifirufa);
		
		time_simulation = ptime_simulation;
		start_simulation = false;
		simulation = new Thread(this);
		this.listPoblationSize = new ArrayList<Data>();
		
		this.listMale = new ArrayList<Data>();
		this.listFemale = new ArrayList<Data>();
		this.listAdults = new ArrayList<Data>();
		this.listAdolecences = new ArrayList<Data>();
		this.listChildhood = new ArrayList<Data>();
		this.listBirth = new ArrayList<Data>();
		this.listTropus = new ArrayList<Data>();
		this.listInopios = new ArrayList<Data>();
		this.listDies = new ArrayList<Data>();
		this.listOlds = new ArrayList<Data>();
		
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
	
			for (int j = 0; j < Habitat.poblation.size(); j++) {
				Habitat.poblation.get(j).evolution(); // incrementa la edad de cada pispirispi
				if (i%DAYS_FOR_YEAR == 0) { // cada año
					Habitat.poblation.get(j).calculateSpeed();
				}
			}
			
			if (i%DAYS_FOR_MONTH == 0) { // genera comida cada 30 dias
				this.listPoblationSize.add(new Data(i, this.habitat.getSize_poblation()));//se agrega poblacion
				
				this.listMale.add(new Data(i, this.habitat.getAmmout_genre_male()));//se agrega Hombres
				this.listFemale.add(new Data(i, this.habitat.getAmmout_genre_female()));//se agrega Mujeres
				this.listAdults.add(new Data(i, this.habitat.getAmmount_age_adult()));//se agrega Adultos
				this.listAdolecences.add(new Data(i, this.habitat.getAmmount_age_adolecence()));//se agrega Adolescentes
				this.listChildhood.add(new Data(i, this.habitat.getAmmount_age_childhood()));//se agrega Niños
				this.listBirth.add(new Data(i, this.habitat.getAmmount_age_birth()));//se agrega Nacimientos
				this.listTropus.add(new Data(i, this.habitat.getAmmount_class_tropus()));//se agrega Tropus
				this.listInopios.add(new Data(i, this.habitat.getAmmount_class_inopio()));//se agrega Inopios
				this.listDies.add(new Data(i, this.habitat.getAmmount_age_die()));//se agrega Muertes
				this.listOlds.add(new Data(i, this.habitat.getAmmount_age_old()));//se agrega Anciono
				
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
		return listPoblationSize;
	}

	public void setListMale(ArrayList<Data> listMale) {
		this.listPoblationSize = listMale;
	}

	public ArrayList<Data> getListPoblationSize() {
		return listPoblationSize;
	}

	public void setListPoblationSize(ArrayList<Data> listPoblationSize) {
		this.listPoblationSize = listPoblationSize;
	}

	public ArrayList<Data> getListFemale() {
		return listFemale;
	}

	public void setListFemale(ArrayList<Data> listFemale) {
		this.listFemale = listFemale;
	}

	public ArrayList<Data> getListAdults() {
		return listAdults;
	}

	public void setListAdults(ArrayList<Data> listAdults) {
		this.listAdults = listAdults;
	}

	public ArrayList<Data> getListAdolecences() {
		return listAdolecences;
	}

	public void setListAdolecences(ArrayList<Data> listAdolecences) {
		this.listAdolecences = listAdolecences;
	}

	public ArrayList<Data> getListChildhood() {
		return listChildhood;
	}

	public void setListChildhood(ArrayList<Data> listChildhood) {
		this.listChildhood = listChildhood;
	}

	public ArrayList<Data> getListBirth() {
		return listBirth;
	}

	public void setListBirth(ArrayList<Data> listBirth) {
		this.listBirth = listBirth;
	}

	public ArrayList<Data> getListTropus() {
		return listTropus;
	}

	public void setListTropus(ArrayList<Data> listTropus) {
		this.listTropus = listTropus;
	}

	public ArrayList<Data> getListInopios() {
		return listInopios;
	}

	public void setListInopios(ArrayList<Data> listInopios) {
		this.listInopios = listInopios;
	}

	public ArrayList<Data> getListDies() {
		return listDies;
	}

	public void setListDies(ArrayList<Data> listDies) {
		this.listDies = listDies;
	}

	public ArrayList<Data> getListOlds() {
		return listOlds;
	}

	public void setListOlds(ArrayList<Data> listOlds) {
		this.listOlds = listOlds;
	}
	
	

}
