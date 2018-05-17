package logic;

import java.util.Random;

public enum Genre {

	MACHO("Macho"),HEMRBA("Hembra");
	
	private String name;
	private static  Random rnd = new Random();
	
	/**
	 * genera un genero aleatorio
	 * @return
	 */
	public static Genre getGenreRandom() {
		double temp = rnd.nextDouble();
		if (temp <= 0.5) {
			return MACHO;
		}else {
			return HEMRBA;
		}
	}
	
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
