package logic;

import java.util.ArrayList;

public class Habitat {

	public static ArrayList<Pispirispi> poblation; // poblacion total de pispirispis
	public static ArrayList<Alicanola> resource; // recursos alimenticios alicanolas
	public static ArrayList<Fifirufa> residue; // residuos que quedan por alimentos fifirufas
	
	private double rate_init_food = 0.2; // el 20% del espacion total del habitat
	
	// entradas
	
	// ninguna de las tasas pueden ser mayores a 1
	public static double size; // superficie en metros cuadrados
	private int init_poblation; // poblacion inicial
	private int init_food; // cantidad comida inicial
	private double init_energy; // cantidad de energia total para los pispirispis
	private double init_energy_alicanola; // cantidad de energia que aportan las alicanolas
	public static double init_energy_fifirufa; // cantidad de energia que quitan las fifirufas
	private double rate_food; // tasa de generacion de alimentos
	private double rate_male; // porcentaje de machos
	private double rate_female; // porcentaje de hembras
	private double rate_inpopios; // porcentaje de inopios
	private double rate_tropus; // porcentaje de tropus
	
	private double size_alicanola; // alacena el tamaño para las anicanolas, es variable
	
	// salidas
	private int ammount_class_inopio; // cantidad de pispirispis con clase inopio porcentaje
	private int ammount_class_tropus; // cantidad de pispirispis con clase tropus porcentaje
	private int ammount_age_birth; // cantidad de pispirispis que nacieron porcentaje
	private int ammount_age_childhood; // cantidad de pispirispis que son infantes porcentaje
	private int ammount_age_adolecence; // cantidad de pispirispis que son adolecentes porcentaje
	private int ammount_age_adult; // cantidad de pispirispis que son adultos porcentaje
	private int ammount_age_old; // cantidad de pispirispis que son viejos porcentaje
	private int ammount_age_die; // cantidad de pispirispis que estan muertos porcentaje
	private int ammout_genre_male; // cantidad de pispirispis machos porcentaje
	private int ammout_genre_female; // cantidad de pispirispis hembras porcentaje
	private int size_poblation; // poblacion total
	
	
	public Habitat(double psize, int init_poblation, int init_food, double init_energy, double init_energy_alicanola,
			double rate_food, double rate_male, double rate_female,double rate_inpopios, double rate_tropus,  double pinit_energy_fifirufa) {
		size = psize;
		this.init_poblation = init_poblation;
		this.init_food = init_food;
		this.init_energy = init_energy;
		this.init_energy_alicanola = init_energy_alicanola;
		this.rate_food = rate_food;
		this.rate_male = rate_male;
		this.rate_female = rate_female;
		this.rate_inpopios = rate_inpopios;
		this.rate_tropus = rate_tropus;
		init_energy_fifirufa = pinit_energy_fifirufa;
		
		poblation = new ArrayList<>();
		resource = new ArrayList<>();
		residue = new ArrayList<>();
	}
	
	
	
	/**
	 * crea la poblacion inicial a partir de los parametros ingresados
	 * 
	 */
	public void generateInitPoblation() {
		Pispirispi temp; // sirve para crear varios
		int ammount_inopios = (int)(init_poblation*rate_inpopios);
		int ammount_tropus = (int)(init_poblation*rate_tropus);
		int ammount_male = (int)(init_poblation*rate_male);
		int ammount_female = (int)(init_poblation*rate_female);
		
		for (int i = 0; i < init_poblation; i++) {
			
			if (ammount_inopios > 0) {
				if (ammount_female > 0) {
					double[] pos = generatePositionRandom(); // calcula la posicion
					temp = new Pispirispi(init_energy, Genre.HEMRBA.toString(), Class.INOPIOS.toString(), pos[0], pos[1]); // crea el pispirispi
					poblation.add(temp); // lo agrega a la poblacion
					ammount_female--; // resta
				}
				if (ammount_male > 0) {
					double[] pos = generatePositionRandom();
					temp = new Pispirispi(init_energy, Genre.MACHO.toString(), Class.INOPIOS.toString(), pos[0], pos[1]);
					poblation.add(temp);
					ammount_male--;
				}
				
				ammount_inopios--;
			}
			
			if (ammount_tropus > 0) {
				if (ammount_female > 0) {
					double[] pos = generatePositionRandom(); // calcula la posicion
					temp = new Pispirispi(init_energy, Genre.HEMRBA.toString(), Class.INOPIOS.toString(), pos[0], pos[1]); // crea el pispirispi
					poblation.add(temp); // lo agrega a la poblacion
					ammount_female--; // resta
				}
				if (ammount_male > 0) {
					double[] pos = generatePositionRandom();
					temp = new Pispirispi(init_energy, Genre.MACHO.toString(), Class.INOPIOS.toString(), pos[0], pos[1]);
					poblation.add(temp);
					ammount_male--;
				}
				
				ammount_tropus--;
			}
			
		}
	}
	
	
	/**
	 * genera alimentos constatemente en dos zonas, se debe llamar cada determinado tiempo, no siempre
	 */
	public void generateFood() {
		double ammount_food = resource.size(); // cantidad de comida actual
		ammount_food *= rate_food;
		rate_init_food += 0.05; // aumenta un 5 % cada determinado tiempo
		double rate_food_generation = size * rate_init_food; // radio de generacion de comida
		double[] zoneFood = generatePositionRandom();
		Alicanola temp;
		double[] positionFood;
		
		for (int i = 0; i < ammount_food; i++) {
			if (i <= (ammount_food/2)) { // zona 1
				positionFood = generatePositionFood(zoneFood[0], zoneFood[1], rate_food_generation);
				calculateSizeFood();
				temp = new Alicanola(positionFood[0], positionFood[1], init_energy_alicanola, size_alicanola);
				resource.add(temp);
			}else { // zona 2
				zoneFood = generatePositionRandom();
				positionFood = generatePositionFood(zoneFood[0], zoneFood[1], rate_food_generation);
				calculateSizeFood();
				temp = new Alicanola(positionFood[0], positionFood[1], init_energy_alicanola, size_alicanola);
				resource.add(temp);
			}
		}
	}
	
	/**
	 * se crean dos zonas de comida aleatorias y se genera la cantidad inicial de alimentos
	 * se divide el total de alimentos entre las dos zonas, las zonas son radiales
	 */
	public void generateInitFood() {
		double rate_food_generation = size * rate_init_food; // radio de generacion de comida
		double[] zoneFood = generatePositionRandom();
		Alicanola temp;
		double[] positionFood;
		for (int i = 0; i < init_food; i++) {
			if (i <= (init_food/2)) { // zona 1
				positionFood = generatePositionFood(zoneFood[0], zoneFood[1], rate_food_generation);
				calculateSizeFood();
				temp = new Alicanola(positionFood[0], positionFood[1], init_energy_alicanola, size_alicanola);
				resource.add(temp);
			}else { // zona 2
				zoneFood = generatePositionRandom();
				positionFood = generatePositionFood(zoneFood[0], zoneFood[1], rate_food_generation);
				calculateSizeFood();
				temp = new Alicanola(positionFood[0], positionFood[1], init_energy_alicanola, size_alicanola);
				resource.add(temp);
			}
		}
		
	}
	
	/**
	 * genera un tamaño aleatorio desde 5 hasta la mitad del pispirispi mas grande
	 */
	public void calculateSizeFood() {
		double maxSizePoblation = 0;
		
		for (Pispirispi pispirispi : poblation) {
			if (maxSizePoblation < pispirispi.getSize()) {
				maxSizePoblation = pispirispi.getSize();
			}
		}
		
		size_alicanola = (Math.random() * (maxSizePoblation/2)) + 5;
	}
	
	/**
	 * calula la posicion para las alicanolas de forma radial desde el centro del origen de la zona
	 * @param posX posicion en X del centro de la zona
	 * @param posY posicion en Y del centro de la zona
	 * @param rate radio para la generacion de alimentos
	 * @return posiciones dentro del radio en un vector 
	 */
	public double[] generatePositionFood(double posX, double posY, double rate) {
		double[] positions = new double[2]; // alamcenas las posiciones dentro del radio
		boolean correctPos = false; // permite controlar cuando detener la operacion
		double distance = 0;
		double tempPosX = 0;
		double tempPosY = 0;
		while (!correctPos) {
			tempPosX = (Math.random() * (posX+rate)) + posX; // genera una posicion X desde el punto central hasta el radio
			tempPosY = (Math.random() * (posY+rate)) + posY; // genera una posicion Y desde el punto central hasta el radio
			
			distance = Math.sqrt(Math.pow(tempPosX-posX, 2)+Math.pow(tempPosY-posY, 2)); // calcula la distancia con pitagoras
			
			if (distance <= rate) {
				positions[0] = tempPosX;
				positions[1] = tempPosY;
				correctPos = true;
			}
		}
		
		return positions;
	}
	
	/**
	 * genera posiciones aleatorias a apartir de la superficie
	 * se plantea una superficie cuadrada por eso se calcula
	 * como la raiz de la superficie
	 * @return un vector con las posiciones X e Y en los espacion 0 y 1 respectivamente
	 */
	public double[] generatePositionRandom() {
		double[] positions = new double[2];
		positions[0] = (Math.random() * Math.sqrt(size)) + 1; // asigna posisciones en X aleatorias que comprenden el tamaño del habitad
		positions[1] = (Math.random() * Math.sqrt(size)) + 1; // asigna posisciones en Y aleatorias que comprenden el tamaño del habitad
		
		return positions;
	}

	/**
	 * retorna la cantidad de elementos que son de tipo inopio
	 * @return
	 */
	public int getAmmount_class_inopio() {
		ammount_class_inopio = 0;
		
		for (Pispirispi pispirispi : poblation) {
			if (pispirispi.getClase().toString().equals(Class.INOPIOS.toString())) {
				ammount_class_inopio++;
			}
		}
		return ammount_class_inopio;
	}


	public void setAmmount_class_inopio(int ammount_class_inopio) {
		this.ammount_class_inopio = ammount_class_inopio;
	}

	/**
	 * retorna la cantidad de elementos de tipo tropus
	 * @return
	 */
	public int getAmmount_class_tropus() {
		ammount_class_tropus = 0;
		for (Pispirispi pispirispi : poblation) {
			if (pispirispi.getClase().toString().equals(Class.INOPIOS.toString())) {
				ammount_class_tropus++;
			}
		}
		return ammount_class_tropus;
	}


	public void setAmmount_class_tropus(int ammount_class_tropus) {
		this.ammount_class_tropus = ammount_class_tropus;
	}


	/**
	 * devuelve el porcentaje de elementos que se encuentran en estado
	 * de nacimiento
	 * @return
	 */
	public int getAmmount_age_birth() {
		ammount_age_birth = 0;
		for (Pispirispi pispirispi : poblation) {
			if (pispirispi.getStage().toString().equals(Stage.NACIMIENTO.toString())) {
				ammount_age_birth++;
			}
		}
		
		return ammount_age_birth/poblation.size();
	}


	public void setAmmount_age_birth(int ammount_age_birth) {
		this.ammount_age_birth = ammount_age_birth;
	}


	public int getAmmount_age_childhood() {
		ammount_age_childhood = 0;
		for (Pispirispi pispirispi : poblation) {
			if (pispirispi.getStage().toString().equals(Stage.INFANCIA.toString())) {
				ammount_age_childhood++;
			}
		}
		
		return ammount_age_childhood/poblation.size();
	}


	public void setAmmount_age_childhood(int ammount_age_childhood) {
		this.ammount_age_childhood = ammount_age_childhood;
	}


	public int getAmmount_age_adolecence() {
		ammount_age_adolecence = 0;
		for (Pispirispi pispirispi : poblation) {
			if (pispirispi.getStage().toString().equals(Stage.ADOLECENCIA.toString())) {
				ammount_age_adolecence++;
			}
		}
		return ammount_age_adolecence/poblation.size();
	}


	public void setAmmount_age_adolecence(int ammount_age_adolecence) {
		this.ammount_age_adolecence = ammount_age_adolecence;
	}



	public int getAmmount_age_adult() {
		ammount_age_adult = 0;
		for (Pispirispi pispirispi : poblation) {
			if (pispirispi.getStage().toString().equals(Stage.ADULTA.toString())) {
				ammount_age_adult++;
			}
		}
		return ammount_age_adult/poblation.size();
	}



	public void setAmmount_age_adult(int ammount_age_adult) {
		this.ammount_age_adult = ammount_age_adult;
	}



	public int getAmmount_age_old() {
		ammount_age_old = 0;
		for (Pispirispi pispirispi : poblation) {
			if (pispirispi.getStage().toString().equals(Stage.VEJEZ.toString())) {
				ammount_age_old++;
			}
		}
		return ammount_age_old/poblation.size();
	}



	public void setAmmount_age_old(int ammount_age_old) {
		this.ammount_age_old = ammount_age_old;
	}



	public int getAmmount_age_die() {
		ammount_age_die = 0;
		for (Pispirispi pispirispi : poblation) {
			if (pispirispi.getStage().toString().equals(Stage.MORIR.toString())) {
				ammount_age_die++;
			}
		}
		return ammount_age_die/poblation.size();
	}



	public void setAmmount_age_die(int ammount_age_die) {
		this.ammount_age_die = ammount_age_die;
	}



	public int getAmmout_genre_male() {
		ammout_genre_male = 0;
		for (Pispirispi pispirispi : poblation) {
			if (pispirispi.getGenre().equals(Genre.MACHO.toString())) {
				ammout_genre_male++;
			}
		}
		return ammout_genre_male/poblation.size();
	}



	public void setAmmout_genre_male(int ammout_genre_male) {
		this.ammout_genre_male = ammout_genre_male;
	}


	public int getAmmout_genre_female() {
		ammout_genre_female = 0;
		for (Pispirispi pispirispi : poblation) {
			if (pispirispi.getGenre().equals(Genre.HEMRBA.toString())) {
				ammout_genre_female++;
			}
		}
		return ammout_genre_female/poblation.size();
	}


	public void setAmmout_genre_female(int ammout_genre_female) {
		this.ammout_genre_female = ammout_genre_female;
	}
	
	public int getSize_poblation() {
		size_poblation = poblation.size();
		return size_poblation;
	}
	
	public void setSize_poblation(int size_poblation) {
		this.size_poblation = size_poblation;
	}

	public ArrayList<Pispirispi> getPoblation() {
		return poblation;
	}

	public ArrayList<Alicanola> getResource() {
		return resource;
	}

	public ArrayList<Fifirufa> getResidue() {
		return residue;
	}

}
