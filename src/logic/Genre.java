package logic;

public enum Genre {

	MACHO("Macho"),HEMRBA("Hembra");
	
	private String name;
	
	private Genre(String pName) {
		name = pName;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
