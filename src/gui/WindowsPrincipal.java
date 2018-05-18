package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import logic.Simulation;

public class WindowsPrincipal extends JFrame implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6169301488090101321L;
	private ChartPanel chartPanel;
	
	public static final int WIDTH = 600;
	public static final int HEIGHT = 400;
	public static final String TITLE = "Vida Artificial - Pispirispis";
	
	
	private Simulation simulation;
	private Thread thread;
	
	private JButton btnInitSimulation;
	
	private JLabel lbSize, lbPoblation,lbFood, lbEnergy,
			lbAlicanola,lbRateFood,lbMale,lbFemale,lbInopios,lbTropus, lbTime,lbFifirufas;
	private JTextField txtSize, txtPoblation,txtFood, txtEnergy,
		txtAlicanola,txtRateFood,txtMale,txtFemale,txtInopios,txtTropus, txtTime,txtFifirufas;
	
	private WindowData dialogGraphic;
	
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
	
	public WindowsPrincipal() {
		setSize(new Dimension(WIDTH, HEIGHT));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setTitle(TITLE);
		this.setLayout(new GridBagLayout());
		this.constraints = new GridBagConstraints();
		thread = new Thread(this);
		this.dialogGraphic = new WindowData(this,null);
		this.initComponents();
		this.addComponents();
		this.activateButton();		
	}
	
	public void initComponents() {
		this.initLabels();
		this.initTextFields();
		this.initButtons();
	}
	
	public void initLabels() {
		this.lbSize = new JLabel("Tamaño (m^2): ");
		this.lbPoblation = new JLabel("Población (u): ");
		this.lbFood = new JLabel("Comida (u): ");
		this.lbEnergy = new JLabel("Energía (u): ");
		this.lbAlicanola = new JLabel("Alicanola (u): ");
		this.lbRateFood = new JLabel("Tasa generacion de alimentos (%): ");
		this.lbMale = new JLabel("Machos (%): ");
		this.lbFemale = new JLabel("Hembras (%): ");
		this.lbInopios = new JLabel("Inopios (%): ");
		this.lbTropus = new JLabel("Tropus (%): ");
		this.lbTime = new JLabel("Tiempo (años): ");
		this.lbFifirufas = new JLabel("Desgaste por fifirufs (energía): ");
	}
	
	public void initTextFields() {
		this.txtSize = new JTextField(15);
		this.txtPoblation = new JTextField(15);
		this.txtFood = new JTextField(15);
		this.txtEnergy = new JTextField(15);
		this.txtAlicanola = new JTextField(15);
		this.txtRateFood = new JTextField(15);
		this.txtMale = new JTextField(15);
		this.txtFemale = new JTextField(15);
		this.txtInopios = new JTextField(15);
		this.txtTropus = new JTextField(15);
		this.txtTime = new JTextField(15);
		this.txtFifirufas = new JTextField(15);
	}
	
	public void initButtons() {
		this.btnInitSimulation = new JButton("Iniciar Simulación");
	}
	
	public void addComponent(Component component,int column, int row) {
		this.constraints.weightx = 0.5;
		this.constraints.gridx = column;
		this.constraints.gridy = row;
		this.constraints.weighty = 1.0;
		this.add(component,constraints);
	}
	
	public void addComponents() {
		this.addComponent(this.lbSize, 0, 0);
		this.addComponent(this.txtSize, 1, 0);
		this.addComponent(this.lbPoblation, 0, 1);
		this.addComponent(this.txtPoblation, 1, 1);
	
		this.addComponent(this.lbFood, 0, 2);
		this.addComponent(this.txtFood, 1, 2);
	
		this.addComponent(this.lbEnergy, 0, 3);
		this.addComponent(this.txtEnergy, 1, 3);
	
		this.addComponent(this.lbAlicanola, 0, 4);
		this.addComponent(this.txtAlicanola, 1, 4);
	
		this.addComponent(this.lbRateFood, 0, 5);
		this.addComponent(this.txtRateFood, 1, 5);
	
		this.addComponent(this.lbMale, 0, 6);
		this.addComponent(this.txtMale, 1, 6);
	
		this.addComponent(this.lbFemale, 0, 7);
		this.addComponent(this.txtFemale, 1, 7);
	
		this.addComponent(this.lbInopios, 0, 8);
		this.addComponent(this.txtInopios, 1, 8);
	
		this.addComponent(this.lbTropus, 0, 9);
		this.addComponent(this.txtTropus, 1, 9);
	
		this.addComponent(this.lbTime, 0, 10);
		this.addComponent(this.txtTime, 1, 10);
	
		this.addComponent(this.lbFifirufas, 0, 11);
		this.addComponent(this.txtFifirufas, 1, 11);
		this.constraints.gridwidth = 2;
		this.addComponent(this.btnInitSimulation, 0, 12);
	
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
	
	public void initSimulation() {
		this.simulation = new Simulation(size, init_poblation,init_food,init_energy,
				init_energy_alicanola,rate_food/100,rate_male/100,rate_female/100,rate_inpopios/100,
				rate_tropus/100,time_simulation,init_energy_fifirufa);
		this.simulation.startSimulation();		
	}
	
	public void activateButton() {
		this.btnInitSimulation.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				getValuesFromComponents();
				initSimulation();
				dialogGraphic.setList(simulation.getListAdults());
				JFreeChart freeChart = ChartFactory.createLineChart("Pispirispis","Dìas","Población",dialogGraphic.getDefaultCategoryDataset(),
						PlotOrientation.VERTICAL,true,true,false);
				chartPanel = new ChartPanel(freeChart);
				thread.start();//inicia hilo de pintado
				chartPanel.setPreferredSize(new Dimension(500, 500));
				dialogGraphic.getPanelGraphic().add(chartPanel);
				dialogGraphic.add(dialogGraphic.getPanelGraphic());
				dialogGraphic.addValuesToTable();
				
				dialogGraphic.addScrollPane();
				chartPanel.setVisible(true);
				dialogGraphic.getjScrollPane().setVisible(true);
				dialogGraphic.setVisible(true);		
			}
		});
	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			JFreeChart freeChart = ChartFactory.createLineChart("Pispirispis","Dìas","Población",dialogGraphic.getDefaultCategoryDataset(),
					PlotOrientation.VERTICAL,true,true,false);
						chartPanel.setChart(freeChart);
						dialogGraphic.addValuesToTable();
						chartPanel.repaint();
						dialogGraphic.getPanelGraphic().repaint();
						dialogGraphic.getjScrollPane().repaint();
						dialogGraphic.repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
