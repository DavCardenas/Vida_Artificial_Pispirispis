package logic;

public enum Stage {
	NACIMIENTO("Nacimiento",0), INFANCIA("Infancia",10), ADOLECENCIA("Adolecencia",5), ADULTA("Adulta",10), VEJEZ("Vejez",5), MORIR("Morir",999999999);
	
	private String name; // nombre de la etapa
	private int time; // duracion en años de la etapa
	
	private Stage(String pName, int pTime){
		name = pName;
		time = pTime;
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
	
	@Override
	public String toString() {
		return name;
	}
}
