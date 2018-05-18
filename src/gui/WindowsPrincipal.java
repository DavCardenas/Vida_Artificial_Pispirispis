package gui;

import java.awt.Dimension;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import logic.Habitat;
import logic.Simulation;

public class WindowsPrincipal extends JFrame implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6169301488090101321L;
	private ChartPanel chartPanel;
	
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 600;
	public static final String TITLE = "Vida Artificial - Pispirispis";
	
	
	private Simulation simulation;
	private Thread thread;
	
	public WindowsPrincipal() {
		setSize(new Dimension(WIDTH, HEIGHT));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setTitle(TITLE);
		this.initSimulation();
		JFreeChart freeChart = ChartFactory.createLineChart("Pispirispis","Dìas","Hombres",getDefaultCategoryDataset(),
				PlotOrientation.VERTICAL,true,true,false);
		this.chartPanel = new ChartPanel(freeChart);
		chartPanel.setPreferredSize(new Dimension(500, 500));
		setContentPane(chartPanel);
		chartPanel.setVisible(true);
		this.thread = new Thread(this);
		this.thread.start();
				
	}
	
	public void initSimulation() {
		this.simulation = new Simulation(100, 10,10,6,2,0.3,0.6,0.4,0.2,0.8,2,3);
		this.simulation.startSimulation();		
	}
	
	
	public DefaultCategoryDataset getDefaultCategoryDataset() {
		DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
		for (int i = 0; i < this.simulation.
				getListMale().size(); i++) {
			categoryDataset.addValue(this.simulation.getListMale().get(i).
					getValue(), "Hombre", ""+this.simulation.getListMale().
					get(i).getDay());
		}
		
	return categoryDataset;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			JFreeChart freeChart = ChartFactory.createLineChart("Pispirispis","Dìas","Hombres",getDefaultCategoryDataset(),
					PlotOrientation.VERTICAL,true,true,false);
//			chartPanel = new ChartPanel(freeChart);
						chartPanel.setChart(freeChart);
						chartPanel.repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
