package logic;

public enum Stage {
	NACIMIENTO("Nacimiento",0), INFANCIA("Infancia",5), ADOLECENCIA("Adolecencia",10), ADULTA("Adulta",15), VEJEZ("Vejez",20), MORIR("Morir",25);
	
	private String name;
	private int time;
	
	private Stage(String pName, int pTime){
		name = pName;
		time = pTime;
	}
	
	public static Stage getNextStage(Stage stage, int pTime) {
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
