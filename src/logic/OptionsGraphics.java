package logic;

public enum OptionsGraphics {

	POBLATION("Poblaci�n"),MALES("Hombres"),FEMALES("Mujeres"),ADULTS("Adultos"),ADOLECENCES("Adolescentes"),
		CHILHOOD("Ni�ez"),BIRTH("Nacimientos"),TROPUS("Tropus"),INOPIOS("Inopios"),DIES("Muertes"),OLDS("Viejos");
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
