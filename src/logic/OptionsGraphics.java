package logic;

public enum OptionsGraphics {

	POBLATION("Población"),MALES("Hombres"),FEMALES("Mujeres"),ADULTS("Adultos"),ADOLECENCES("Adolescentes"),
		CHILHOOD("Niñez"),BIRTH("Nacimientos"),TROPUS("Tropus"),INOPIOS("Inopios"),DIES("Muertes"),OLDS("Viejos");
	private final String value;
	
	private OptionsGraphics(String s) {
		this.value = s;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.value;
	}
}
