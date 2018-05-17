package logic;

public class Pispirispi {
	
	public static final double SIZE_IN = 10; // tamaño inicial 10 px
	public static final double GROWTH_FACTOR_SIZE = SIZE_IN * 0.3; // factor de crecimiento en tamaño en px
	public static final double SPEED_IN = 0.5; // velocidad inicial 0.5px/s (respecto al tiempo de entrada)
	

	private double energy; // energia que les permite moverse, si llega a 0 mueren
	private String genre; // generero macho o hembra
	private String clase; // clase inopios o tropus
	private double size; // tamaño que aumenta con el tiempo
	private String direction; // direccion en la que se mueve (N, S, E, O, NE, NO, SE, SO) Norte, Sur, Este, Oeste
	private String stage; // etapa de madurez (nacimiento, infancia, adolecencia, adulta, vejez, morir)
	private double speed; // velocidad con la que se mueven, depende de la energia y de la etapa, entre mayor etapa menor velocidad
	private double position_X; // almacena la posicion en X.
	private double position_Y; // almacena la posicion en Y;
	private double age; // edad
	
	public Pispirispi() {
		
	}
	

	public Pispirispi(double energy, String genre, String clase, double posX, double posY) {
		super();
		this.position_X = posX;
		this.position_Y = posY;
		this.energy = energy;
		this.genre = genre;
		this.clase = clase;
		this.size = SIZE_IN;
		this.direction = Direction.nextDirection().toString(); // asigna una direccion al azar
		this.stage = Stage.NACIMIENTO.toString();
		this.speed = SPEED_IN;
		this.age = 0;
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

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
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
	
	
	
	
}
