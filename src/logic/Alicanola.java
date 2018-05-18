package logic;

import java.awt.Rectangle;

public class Alicanola {

	private double position_X; // posicion X
	private double position_Y; // posicion Y
	private double size; // tamaño
	private double energy; // cantidad de energia que aporta


	public Alicanola(double position_X, double position_Y, double energy, double size) {
		super();
		this.position_X = position_X;
		this.position_Y = position_Y;
		this.energy = energy;
		this.size = size;
	}

	/**
	 * retorna un rectangle para calcular la colision
	 * @return
	 */
	public Rectangle getBounds() {
		return new Rectangle((int) position_X, (int) position_Y, (int) size, (int) size);
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
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

	public double getEnergy() {
		return energy;
	}

	public void setEnergy(double energy) {
		this.energy = energy;
	}



}
