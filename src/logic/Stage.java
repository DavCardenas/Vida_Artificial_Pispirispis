package logic;

public enum Stage {
	NACIMIENTO("Nacimiento",0,10), INFANCIA("Infancia",10,15), ADOLECENCIA("Adolecencia",5,20), ADULTA("Adulta",10,25), VEJEZ("Vejez",5,30), MORIR("Morir",999999999,0);
	
	private String name; // nombre de la etapa
	private int time; // duracion en años de la etapa
	private int size; // tamaño
	
	private Stage(String pName, int pTime, int pSize){
		name = pName;
		time = pTime;
		size = pSize;
	}
	
	public static Stage getNextStage(Stage stage) {
		switch (stage) {
		case NACIMIENTO:
				return Stage.INFANCIA;
		case INFANCIA:
				return Stage.ADOLECENCIA;
		case ADOLECENCIA:
				return Stage.ADULTA;
		case ADULTA:
				return Stage.VEJEZ;
		case VEJEZ:
				return Stage.MORIR;
		default:
			return Stage.MORIR;
		}
	}
	
	public static int getSize(Stage stage) {
		switch (stage) {
		case NACIMIENTO:
				return Stage.INFANCIA.getSize();
		case INFANCIA:
				return Stage.ADOLECENCIA.getSize();
		case ADOLECENCIA:
				return Stage.ADULTA.getSize();
		case ADULTA:
				return Stage.VEJEZ.getSize();
		case VEJEZ:
				return Stage.MORIR.getSize();
		default:
			return Stage.MORIR.getSize();
		}
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getTime() {
		return time;
	}
	
	public void setTime(int time) {
		this.time = time;
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return name;
	}
}
