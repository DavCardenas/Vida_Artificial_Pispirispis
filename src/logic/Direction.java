package logic;

import java.util.Random;

public enum Direction {

	NORTE("Norte"),SUR("Sur"),ESTE("Este"),OESTE("Oeste"),NORESTE("Noreste"),NOROESTE("Noroeste"),SURESTE("Sureste"),SUROESTE("Suroeste");
	
	private String name;
	private static  Random rnd = new Random();
	
	private Direction(String pName) {
		name = pName;
	}
	
	public static Direction nextDirection() {
		double random = rnd.nextDouble();
		if (random >= 0 && random <= 0.125) {
			return NORTE;
		}else if (random > 0.125 && random <= 0.25) {
			return SUR;
		}else if (random > 0.25 && random <= 0.375) {
			return Direction.ESTE;
		}else if (random > 0.375 && random <= 0.5) {
			return Direction.OESTE;
		}else if (random > 0.5 && random <= 0.625) {
			return Direction.NORESTE;
		}else if (random > 0.625 && random <= 0.75) {
			return NOROESTE;
		}else if (random > 0.75 && random <= 0.875) {
			return SURESTE;
		}else {
			return Direction.SUROESTE;
		}
		
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
