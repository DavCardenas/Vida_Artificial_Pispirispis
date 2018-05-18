package logic;

import java.awt.Rectangle;

public class Pispirispi implements Runnable{
	
	public static final double SPEED_IN = 1; // velocidad inicial (respecto al tiempo de entrada) px/dia
	public static final double SPEED_OUT = SPEED_IN/Simulation.time_simulation; // velocidad reducida cada año
	
	private double energy; // energia que les permite moverse, si llega a 0 mueren
	private String genre; // generero macho o hembra
	private String clase; // clase inopios o tropus
	private double size; // tamaño que aumenta con el tiempo
	private Direction direction; // direccion en la que se mueve (N, S, E, O, NE, NO, SE, SO) Norte, Sur, Este, Oeste
	private Stage stage; // etapa de madurez (nacimiento, infancia, adolecencia, adulta, vejez, morir)
	private double speed; // velocidad con la que se mueven, depende de la edad, entre mayor edad menor velocidad
	private double position_X; // almacena la posicion en X.
	private double position_Y; // almacena la posicion en Y;
	private double age; // edad medida en dias
	private Thread move; // hilo que permite moverse
	private boolean start; // permite iniciar o detener el hilo
	private boolean canEvolution; // controla si puede o no evolucionar
	
	public Pispirispi() {
		
	}
	

	public Pispirispi(double energy, String genre, String clase, double posX, double posY) {
		super();
		this.position_X = posX;
		this.position_Y = posY;
		this.energy = energy;
		this.genre = genre;
		this.clase = clase;
		this.size = Stage.NACIMIENTO.getSize();
		this.direction = Direction.nextDirection(); // asigna una direccion al azar
		this.stage = Stage.NACIMIENTO;
		this.speed = SPEED_IN;
		this.age = 0;
		start = false;
		move = new Thread(this);
		canEvolution = false;
	}
	
	/**
	 * controla el cambio de etapa y la edad
	 */
	public void evolution() {
		age++;
		changeStage();
		calculateSize();
		
	}
	
	/**
	 * cambia al siguiente estado de edad
	 * se debe llamar cada vez que pasa un año
	 */
	private void changeStage() {
		double time = stage.getTime()*Simulation.DAYS_FOR_YEAR;
//		System.out.println("Primero " + stage.getTime());
		if (stage != Stage.NACIMIENTO) {
			if (age >= time) {
				if (canEvolution) {
					stage = Stage.getNextStage(stage);
					canEvolution = false;
				}else {
//					System.out.println("Segundo se Muere");
					stage = Stage.MORIR;
				}
			}
		}else {
			stage = Stage.getNextStage(stage);
		}
	}

	
	/**
	 * recalcula la velocidad a partir de la edad
	 * se debe llamar cada vez que pasa un año
	 */
	public void calculateSpeed() {
		speed -= SPEED_OUT;
	}
	
	/**
	 * recalcula el tamaño a partir de la edad
	 * se debe llamar cada vez que pasa un año
	 */
	private void calculateSize() {
		size = stage.getSize();
	}
	
	/**
	 * Permite cambiar las posiciones segun la direccion
	 * @param dir direccion hacia la que se mueve
	 */
	private void move(Direction dir) {
		switch (dir) {
		case NORTE:
			position_Y -= speed; // se resta porque va hacia arriba
			break;
		case SUR:
			position_Y += speed; // se suma porque va hacia abajo
			break;
		case ESTE:
			position_X += speed; // se suma porque va hacia la derecha
			break;
		case OESTE:
			position_X -= speed; // se resta porque va hacia la izquierda
			break;
		case NORESTE:
			position_Y -= speed; // se resta porque va hacia arriba
			position_X += speed; // se suma porque va hacia la derecha
			break;
		case NOROESTE:
			position_Y -= speed; // se resta porque va hacia arriba
			position_X -= speed; // se resta porque va hacia la izquierda
			break;
		case SURESTE:
			position_Y += speed; // se suma porque va hacia abajo
			position_X += speed; // se suma porque va hacia la derecha
			break;
		case SUROESTE:
			position_Y += speed; // se suma porque va hacia abajo
			position_X -= speed; // se resta porque va hacia la izquierda
			break;
		default:
			break;
		}
		
		energy -= 1; // reduccion de energia por moverse
	}
	
	/**
	 * inicia el hilo move
	 */
	public void startMove() {
		start = true;
		move.start();
	}
	
	/**
	 * detiene el hilo
	 */
	public void stopMove() {
		start = false;
	}
	
	
	/**
	 * Devuelve verdadero si hay una interseccion con el 
	 * parametro de entrada
	 * 
	 * @param element Tipo Rectangle para realizar la colision
	 * @return boolean
	 */
	public boolean collision(Rectangle element) {
		return getBounds().intersects(element);
	}

	/**
	 * Devuelve un rectangulo con la posicion y dimensiones
	 * del pispirispi para calcular las colisiones
	 * 
	 * @return Rectangle 
	 */
	public Rectangle getBounds() {
		return new Rectangle((int)position_X, (int)position_Y, (int)size, (int)size);
	}

	public double getAge() {
		return age;
	}


	public void setAge(double age) {
		this.age = age;
	}


	public double getEnergy() {
		return energy;
	}

	public void setEnergy(double energy) {
		this.energy = energy;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getPosition_X() {
		return position_X;
	}

	public void setPosition_X(double position_X) {
		this.position_X = position_X;
	}

	public double getPosition_Y() {
		return position_Y;
	}

	public void setPosition_Y(double position_Y) {
		this.position_Y = position_Y;
	}


	@Override
	public void run() {
		Pispirispi child;
		Fifirufa fifirufa;
		
		while (start) {
			
			if (stage != Stage.MORIR) {
				move(direction);
			}else {
				stopMove(); // si esta muerto se detiene el hilo
			}
			
			// calculo de colisiones para cada elemento de la poblacion
			for (int i = 0; i < Habitat.poblation.size(); i++) {
				
				if (Habitat.poblation.get(i) != this) {
					if (Habitat.poblation.get(i).getStage() != Stage.MORIR) { // si no esta muerto
						if (collision(Habitat.poblation.get(i).getBounds())) { // si existe la colision
							
							if (!Habitat.poblation.get(i).getGenre().equals(genre)) { // si son de diferente genero
								if (Habitat.poblation.get(i).getClase().equals(clase)) { // si son de la misma clase
									if (Habitat.poblation.get(i).getStage().equals(Stage.ADULTA)
											&& stage.equals(Stage.ADULTA)) { // si son adultos
										child = new Pispirispi(energy, Genre.getGenreRandom().toString(), clase, position_X, position_Y);
										Habitat.poblation.add(child);
									}
								}
							}
							
							if (Habitat.poblation.get(i).getGenre().equals(genre)) { // si son de igual genero
								if (!Habitat.poblation.get(i).getClase().equals(clase)) { // si son de diferente clase
									if (Habitat.poblation.get(i).getStage().equals(Stage.ADOLECENCIA)
											&& stage.equals(Stage.ADOLECENCIA)) { // si son adolecentes
										if (Habitat.poblation.get(i).getEnergy() > energy) { // muere el de menor energia
											stage = Stage.MORIR; 
										}else {
											Habitat.poblation.get(i).setStage(Stage.MORIR);
										}
										
									}
								}
							}
							
							if (Habitat.poblation.get(i).getAge() < age) {// si es menor a la edad
								if (!Habitat.poblation.get(i).getClase().equals(clase)) {// si son de diferente clase
									canEvolution = true; // absorvio a otro y puede evolucionar
								}
							}
							
							direction = Direction.nextDirection(); // rebotan a una direccion al azar
							Habitat.poblation.get(i).setDirection(Direction.nextDirection());
						}
					}
				}
			}
			
			// calculo de colisiones para los alimentos
			
			for (int i = 0; i < Habitat.resource.size(); i++) {
				if (collision(Habitat.resource.get(i).getBounds())) { // si colisionan
					if (Habitat.resource.get(i).getSize() <= (size/2)) { // si la alicanola es la mitad o menos del pispirispi
						energy += Habitat.resource.get(i).getEnergy(); // agrega energia
						fifirufa = new Fifirufa(position_X, position_Y, Habitat.resource.get(i).getSize(), Habitat.init_energy_fifirufa);
						Habitat.residue.add(fifirufa); // se crea una fifirufa
						Habitat.resource.remove(i); // se elimina la alicanola
					}
				}
			}
			
			// calculo colisiones para fifirufas
			
			for (int i = 0; i < Habitat.residue.size(); i++) {
				if (collision(Habitat.residue.get(i).getBounds())) { // si colisionan
					energy -= Habitat.residue.get(i).getEnergy(); // reduce energia
				}
			}
			
			if(!collision(Habitat.getBounds())) {
				direction = Direction.nextDirection();
			}
			
			try {
				Thread.sleep(100); // para evitar que consuma todo el procesador
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
