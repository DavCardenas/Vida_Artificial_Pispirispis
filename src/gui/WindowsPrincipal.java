package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
	
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 700;
	public static final String TITLE = "Vida Artificial - Pispirispis";
	
	
	private Simulation simulation;
	private Thread thread;
	
	private String titleGraphic = "";	
	
	private WindowData dialogGraphic;
	
	private PanelRegister panelRegister;
	private PanelChooseGraphic panelChooseGraphic;
	
	private boolean openGraphics = false;//para saber si se ha iniciado antes un hilo
	
	private JPanel panelAnim;//cambie este por un panel donde pinte los pispirispis
	
	public WindowsPrincipal() {
//		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setTitle(TITLE);
		this.pack();
		this.setLayout(new BorderLayout());
		this.panelRegister = new PanelRegister();
		this.panelChooseGraphic =new PanelChooseGraphic();
		this.panelAnim = new JPanel();
		thread = new Thread(this);
		this.dialogGraphic = new WindowData(this,null);
		this.add(this.panelRegister, BorderLayout.WEST);
		this.add(this.panelChooseGraphic, BorderLayout.SOUTH);
		this.panelAnim.setBackground(Color.blue);
		this.add(this.panelAnim, BorderLayout.CENTER);
		this.panelRegister.setVisible(true);
		this.activateButtonInitSimulation();		
		this.activateShowGraphicButton();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}	
	
	public void initSimulation() {
		this.simulation = new Simulation(panelRegister.getSizeC(), panelRegister.getInit_poblation(),panelRegister.getInit_food(),panelRegister.getInit_energy(),
				panelRegister.getInit_energy_alicanola(),panelRegister.getRate_food()/100,panelRegister.getRate_male()/100,panelRegister.getRate_female()/100,panelRegister.getRate_inpopios()/100,
				panelRegister.getRate_tropus()/100,panelRegister.getTime_simulation(),panelRegister.getInit_energy_fifirufa());
		this.simulation.startSimulation();		
	}
	
	public void activateButtonInitSimulation() {
		this.panelRegister.getBtnInitSimulation().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				panelRegister.getValuesFromComponents();
				initSimulation();
				panelChooseGraphic.getBtnShowGraphic().setEnabled(true);
				openGraphics = false;
			}
		});
	}
	
	public void activateShowGraphicButton() {
		this.panelChooseGraphic.getBtnShowGraphic().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (panelChooseGraphic.getCmbChooseGraphic().getSelectedItem().toString()) {
				case "Población":
					dialogGraphic.setList(simulation.getListPoblationSize());
					break;
				case "Hombres":
					dialogGraphic.setList(simulation.getListMale());
					break;
				case "Mujeres":
					dialogGraphic.setList(simulation.getListFemale());
					break;
				case "Adultos":
					dialogGraphic.setList(simulation.getListAdults());
					break;
				case "Adolescentes":
					dialogGraphic.setList(simulation.getListAdolecences());
					break;
				case "Niñez":
					dialogGraphic.setList(simulation.getListChildhood());
					break;
				case "Nacimientos":
					dialogGraphic.setList(simulation.getListBirth());
					break;
				case "Tropus":
					dialogGraphic.setList(simulation.getListTropus());
					break;
				case "Inopios":
					dialogGraphic.setList(simulation.getListInopios());
					break;
				case "Muertes":
					dialogGraphic.setList(simulation.getListDies());
					break;
				case "Viejos":
					dialogGraphic.setList(simulation.getListOlds());
					break;
				default:
					break;
				}
				titleGraphic = panelChooseGraphic.getCmbChooseGraphic().getSelectedItem().toString();
				if(!openGraphics) {
					JFreeChart freeChart = ChartFactory.createLineChart("Pispirispis","Dìas",
							titleGraphic,dialogGraphic.getDefaultCategoryDataset(titleGraphic),
							PlotOrientation.VERTICAL,true,true,false);
					chartPanel = new ChartPanel(freeChart);
					thread.start();//inicia hilo de pintado
					openGraphics = true;
					dialogGraphic.getPanelGraphic().add(chartPanel);
					dialogGraphic.add(dialogGraphic.getPanelGraphic());
					dialogGraphic.addValuesToTable();
					dialogGraphic.addScrollPane();
					dialogGraphic.addButton();//agregar el boton de salir
					chartPanel.setPreferredSize(new Dimension(500, 500));
					chartPanel.setVisible(true);
					dialogGraphic.getjScrollPane().setVisible(true);
				}else {
//					JFreeChart freeChart2 = ChartFactory.createLineChart("Pispirispis","Dìas",
//							titleGraphic,dialogGraphic.getDefaultCategoryDataset(titleGraphic),
//							PlotOrientation.VERTICAL,true,true,false);
//					chartPanel.setChart(freeChart2);
//					dialogGraphic.addValuesToTable();
//					chartPanel.repaint();
//					dialogGraphic.getPanelGraphic().repaint();
//					dialogGraphic.getjScrollPane().repaint();
//					dialogGraphic.repaint();
				}
				dialogGraphic.setVisible(true);		
			}
		});
	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			JFreeChart freeChart = ChartFactory.createLineChart("Pispirispis","Dìas",
					titleGraphic,dialogGraphic.getDefaultCategoryDataset(titleGraphic),
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
