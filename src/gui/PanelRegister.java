package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelRegister extends JPanel {

	private JLabel lbTitle,lbSize, lbPoblation,lbFood, lbEnergy,
			lbAlicanola,lbRateFood,lbMale,lbFemale,lbInopios,lbTropus, lbTime,lbFifirufas;
	private JTextField txtSize, txtPoblation,txtFood, txtEnergy,
		txtAlicanola,txtRateFood,txtMale,txtFemale,txtInopios,txtTropus, txtTime,txtFifirufas;
	private JButton btnInitSimulation;
	
	public double size; // superficie en metros cuadrados
	private int init_poblation; // poblacion inicial
	private int init_food; // cantidad comida inicial
	private double init_energy; // cantidad de energia total para los pispirispis
	private double init_energy_alicanola; // cantidad de energia que aportan las alicanolas
	public double init_energy_fifirufa; // cantidad de energia que quitan las fifirufas
	private double rate_food; // tasa de generacion de alimentos
	private double rate_male; // porcentaje de machos
	private double rate_female; // porcentaje de hembras
	private double rate_inpopios; // porcentaje de inopios
	private double rate_tropus; // porcentaje de tropus
	public  int time_simulation; // tiempo que dura la simulacion en años
	
	
	private GridBagConstraints constraints;
	
	public PanelRegister() {
		// TODO Auto-generated method stub
		this.setSize(600,500);
		
		this.constraints = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		this.initComponents();
		this.addComponents();
	}
	
	public void initComponents() {
		this.initLabels();
		this.initTextFields();
		this.initButtons();		
	}
	
	public void initButtons() {
		this.btnInitSimulation = new JButton("Iniciar Simulación");
	}
	
	public void initLabels() {
		this.lbTitle = new JLabel("Valores Iniciales");
		this.lbTitle.setFont(new Font("Tahoma", 0, 20));
		this.lbSize = new JLabel("Tamaño (m^2): ");
		this.lbPoblation = new JLabel("Población (u): ");
		this.lbFood = new JLabel("Comida (u): ");
		this.lbEnergy = new JLabel("Energía (u): ");
		this.lbAlicanola = new JLabel("Alicanola (u): ");
		this.lbRateFood = new JLabel("<html><body>Tasa generacion <br> de alimentos (%):  </body></html>");
		this.lbMale = new JLabel("Machos (%): ");
		this.lbFemale = new JLabel("Hembras (%): ");
		this.lbInopios = new JLabel("Inopios (%): ");
		this.lbTropus = new JLabel("Tropus (%): ");
		this.lbTime = new JLabel("Tiempo (años): ");
		this.lbFifirufas = new JLabel("<html><body>Desgaste por <br> fifirufas (energía):    </body></html>");
	}
	
	public void initTextFields() {
		this.txtSize = new JTextField(7);
		this.txtPoblation = new JTextField(7);
		this.txtFood = new JTextField(7);
		this.txtEnergy = new JTextField(7);
		this.txtAlicanola = new JTextField(7);
		this.txtRateFood = new JTextField(7);
		this.txtMale = new JTextField(7);
		this.txtFemale = new JTextField(7);
		this.txtInopios = new JTextField(7);
		this.txtTropus = new JTextField(7);
		this.txtTime = new JTextField(7);
		this.txtFifirufas = new JTextField(7);
	}
	
	public void addComponent(Component component,int column, int row) {
		this.constraints.gridx = column;
		this.constraints.gridy = row;
		this.add(component,constraints);
	}
	
	public void addComponents() {
		this.constraints.gridwidth=2;
		this.addComponent(this.lbTitle, 0, 0);
		this.constraints.gridwidth=1;
		this.constraints.weighty = 0.5;
		this.addComponent(this.lbSize, 0, 1);
		this.addComponent(this.txtSize, 1, 1);
		this.addComponent(this.lbPoblation, 0, 2);
		this.addComponent(this.txtPoblation, 1, 2);
	
		this.addComponent(this.lbFood, 0, 3);
		this.addComponent(this.txtFood, 1, 3);
	
		this.addComponent(this.lbEnergy, 0, 4);
		this.addComponent(this.txtEnergy, 1, 4);
	
		this.addComponent(this.lbAlicanola, 0, 5);
		this.addComponent(this.txtAlicanola, 1, 5);
	
		this.addComponent(this.lbRateFood, 0, 6);
		this.addComponent(this.txtRateFood, 1, 6);
	
		this.addComponent(this.lbMale, 0, 7);
		this.addComponent(this.txtMale, 1, 7);
	
		this.addComponent(this.lbFemale, 0, 8);
		this.addComponent(this.txtFemale, 1, 8);
	
		this.addComponent(this.lbInopios, 0, 9);
		this.addComponent(this.txtInopios, 1, 9);
	
		this.addComponent(this.lbTropus, 0, 10);
		this.addComponent(this.txtTropus, 1, 10);
	
		this.addComponent(this.lbTime, 0, 11);
		this.addComponent(this.txtTime, 1, 11);
	
		this.addComponent(this.lbFifirufas, 0, 12);
		this.addComponent(this.txtFifirufas, 1, 12);
		
		this.constraints.gridwidth=2;
		this.addComponent(this.btnInitSimulation, 0, 13);
	}
	
	/**
	 * Toma los valores de los componentes de las cajas de tezto
	 * Esta validado que pueda hacer casteo
	 * 	 */
	public void getValuesFromComponents() {
		try {
			size = Double.parseDouble(this.txtSize.getText());
			init_poblation=Integer.parseInt(this.txtPoblation.getText()); 
			init_food=Integer.parseInt(this.txtFood.getText());
			init_energy=Double.parseDouble(this.txtEnergy.getText());
			init_energy_alicanola=Double.parseDouble(this.txtAlicanola.getText());
			init_energy_fifirufa=Double.parseDouble(this.txtFifirufas.getText()); 
			rate_food=Double.parseDouble(this.txtRateFood.getText()); 
			rate_male=Double.parseDouble(this.txtMale.getText()); 
			rate_female=Double.parseDouble(this.txtFemale.getText());
			rate_inpopios=Double.parseDouble(this.txtInopios.getText());
			rate_tropus=Double.parseDouble(this.txtTropus.getText());
			time_simulation=Integer.parseInt(this.txtTime.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en los datos");
		}
	}

	public JLabel getLbSize() {
		return lbSize;
	}

	public void setLbSize(JLabel lbSize) {
		this.lbSize = lbSize;
	}

	public JLabel getLbPoblation() {
		return lbPoblation;
	}

	public void setLbPoblation(JLabel lbPoblation) {
		this.lbPoblation = lbPoblation;
	}

	public JLabel getLbFood() {
		return lbFood;
	}

	public void setLbFood(JLabel lbFood) {
		this.lbFood = lbFood;
	}

	public JLabel getLbEnergy() {
		return lbEnergy;
	}

	public void setLbEnergy(JLabel lbEnergy) {
		this.lbEnergy = lbEnergy;
	}

	public JLabel getLbAlicanola() {
		return lbAlicanola;
	}

	public void setLbAlicanola(JLabel lbAlicanola) {
		this.lbAlicanola = lbAlicanola;
	}

	public JLabel getLbRateFood() {
		return lbRateFood;
	}

	public void setLbRateFood(JLabel lbRateFood) {
		this.lbRateFood = lbRateFood;
	}

	public JLabel getLbMale() {
		return lbMale;
	}

	public void setLbMale(JLabel lbMale) {
		this.lbMale = lbMale;
	}

	public JLabel getLbFemale() {
		return lbFemale;
	}

	public void setLbFemale(JLabel lbFemale) {
		this.lbFemale = lbFemale;
	}

	public JLabel getLbInopios() {
		return lbInopios;
	}

	public void setLbInopios(JLabel lbInopios) {
		this.lbInopios = lbInopios;
	}

	public JLabel getLbTropus() {
		return lbTropus;
	}

	public void setLbTropus(JLabel lbTropus) {
		this.lbTropus = lbTropus;
	}

	public JLabel getLbTime() {
		return lbTime;
	}

	public void setLbTime(JLabel lbTime) {
		this.lbTime = lbTime;
	}

	public JLabel getLbFifirufas() {
		return lbFifirufas;
	}

	public void setLbFifirufas(JLabel lbFifirufas) {
		this.lbFifirufas = lbFifirufas;
	}

	public JTextField getTxtSize() {
		return txtSize;
	}

	public void setTxtSize(JTextField txtSize) {
		this.txtSize = txtSize;
	}

	public JTextField getTxtPoblation() {
		return txtPoblation;
	}

	public void setTxtPoblation(JTextField txtPoblation) {
		this.txtPoblation = txtPoblation;
	}

	public JTextField getTxtFood() {
		return txtFood;
	}

	public void setTxtFood(JTextField txtFood) {
		this.txtFood = txtFood;
	}

	public JTextField getTxtEnergy() {
		return txtEnergy;
	}

	public void setTxtEnergy(JTextField txtEnergy) {
		this.txtEnergy = txtEnergy;
	}

	public JTextField getTxtAlicanola() {
		return txtAlicanola;
	}

	public void setTxtAlicanola(JTextField txtAlicanola) {
		this.txtAlicanola = txtAlicanola;
	}

	public JTextField getTxtRateFood() {
		return txtRateFood;
	}

	public void setTxtRateFood(JTextField txtRateFood) {
		this.txtRateFood = txtRateFood;
	}

	public JTextField getTxtMale() {
		return txtMale;
	}

	public void setTxtMale(JTextField txtMale) {
		this.txtMale = txtMale;
	}

	public JTextField getTxtFemale() {
		return txtFemale;
	}

	public void setTxtFemale(JTextField txtFemale) {
		this.txtFemale = txtFemale;
	}

	public JTextField getTxtInopios() {
		return txtInopios;
	}

	public void setTxtInopios(JTextField txtInopios) {
		this.txtInopios = txtInopios;
	}

	public JTextField getTxtTropus() {
		return txtTropus;
	}

	public void setTxtTropus(JTextField txtTropus) {
		this.txtTropus = txtTropus;
	}

	public JTextField getTxtTime() {
		return txtTime;
	}

	public void setTxtTime(JTextField txtTime) {
		this.txtTime = txtTime;
	}

	public JTextField getTxtFifirufas() {
		return txtFifirufas;
	}

	public void setTxtFifirufas(JTextField txtFifirufas) {
		this.txtFifirufas = txtFifirufas;
	}

	public double getSizeC() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public int getInit_poblation() {
		return init_poblation;
	}

	public void setInit_poblation(int init_poblation) {
		this.init_poblation = init_poblation;
	}

	public int getInit_food() {
		return init_food;
	}

	public void setInit_food(int init_food) {
		this.init_food = init_food;
	}

	public double getInit_energy() {
		return init_energy;
	}

	public void setInit_energy(double init_energy) {
		this.init_energy = init_energy;
	}

	public double getInit_energy_alicanola() {
		return init_energy_alicanola;
	}

	public void setInit_energy_alicanola(double init_energy_alicanola) {
		this.init_energy_alicanola = init_energy_alicanola;
	}

	public double getInit_energy_fifirufa() {
		return init_energy_fifirufa;
	}

	public void setInit_energy_fifirufa(double init_energy_fifirufa) {
		this.init_energy_fifirufa = init_energy_fifirufa;
	}

	public double getRate_food() {
		return rate_food;
	}

	public void setRate_food(double rate_food) {
		this.rate_food = rate_food;
	}

	public double getRate_male() {
		return rate_male;
	}

	public void setRate_male(double rate_male) {
		this.rate_male = rate_male;
	}

	public double getRate_female() {
		return rate_female;
	}

	public void setRate_female(double rate_female) {
		this.rate_female = rate_female;
	}

	public double getRate_inpopios() {
		return rate_inpopios;
	}

	public void setRate_inpopios(double rate_inpopios) {
		this.rate_inpopios = rate_inpopios;
	}

	public double getRate_tropus() {
		return rate_tropus;
	}

	public void setRate_tropus(double rate_tropus) {
		this.rate_tropus = rate_tropus;
	}

	public int getTime_simulation() {
		return time_simulation;
	}

	public void setTime_simulation(int time_simulation) {
		this.time_simulation = time_simulation;
	}

	public GridBagConstraints getConstraints() {
		return constraints;
	}

	public void setConstraints(GridBagConstraints constraints) {
		this.constraints = constraints;
	}

	public JButton getBtnInitSimulation() {
		return btnInitSimulation;
	}

	public void setBtnInitSimulation(JButton btnInitSimulation) {
		this.btnInitSimulation = btnInitSimulation;
	}
	
	
	
}
