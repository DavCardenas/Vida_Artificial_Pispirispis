package logic;

import java.awt.Rectangle;

public class Pispirispi implements Runnable{
	
	public static final double SPEED_IN = 1; // velocidad inicial (respecto al tiempo de entrada) px/dia
	public static final double SPEED_OUT = SPEED_IN/30; // velocidad reducida cada n años
	
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
	}
	
	/**
	 * cambia al siguiente estado de edad
	 * se debe llamar cada vez que pasa un año
	 */
	public void changeStage() {
		stage = Stage.getNextStage(stage);
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
	public void calculateSize() {
		size += stage.getSize();
	}
	
	/**
	 * Permite cambiar las posiciones segun la direccion
	 * @param dir direccion hacia la que se mueve
	 */
	public void move(Direction dir) {
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
		while (start) {
			
			move(direction);
			
			try {
				Thread.sleep(10); // para evitar que consuma todo el procesador
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
