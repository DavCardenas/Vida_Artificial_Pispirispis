package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jfree.data.category.DefaultCategoryDataset;

import logic.Data;

public class WindowData extends JDialog{
	
private JPanel panelGraphic;
	
	private JTable jTable;
	private DefaultTableModel defaultTableModel;
	private JScrollPane jScrollPane;
	
	private ArrayList<Data> list;
	
	public WindowData(JFrame frame,ArrayList<Data> list) {
		// TODO Auto-generated constructor stub
		super(frame,true);
		this.list = list;
		this.setLayout(new FlowLayout());
		this.panelGraphic = new JPanel();
		this.setSize(new Dimension(1000, 700));
		this.panelGraphic.setSize(new Dimension(500, 500));
		this.setLocationRelativeTo(null);
		this.createTable();
	}
	
	public void createTable() {
		this.jTable = new JTable();
		this.defaultTableModel = new DefaultTableModel();
		this.jTable.setModel(defaultTableModel);
		this.defaultTableModel.setColumnIdentifiers(new Object [] {
				   "dia", "Valor"
				});
		jScrollPane = new JScrollPane(jTable);
	}
	
	public void addValuesToTable() {
		this.defaultTableModel = new DefaultTableModel();
		this.jTable.setModel(defaultTableModel);
		this.defaultTableModel.setColumnIdentifiers(new Object [] {
				   "dia", "Valor"
				});
		for (int i = 0; i < this.list.size(); i++) {
			Object [] object = new Object[]{this.list.get(i).getDay(),
					this.list.get(i).getValue()};
			this.defaultTableModel.addRow(object);
		}
	}
	
	public DefaultCategoryDataset getDefaultCategoryDataset() {
		DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
		for (int i = 0; i < this.list.size(); i++) {
			categoryDataset.addValue(this.list.get(i).
					getValue(), "Hombre", ""+this.list.
					get(i).getDay());
		}
		
	return categoryDataset;
	}
	
	public void addScrollPane() {
		add(jScrollPane);
	}

	public JPanel getPanelGraphic() {
		return panelGraphic;
	}

	public void setPanelGraphic(JPanel panelGraphic) {
		this.panelGraphic = panelGraphic;
	}

	public JTable getjTable() {
		return jTable;
	}

	public void setjTable(JTable jTable) {
		this.jTable = jTable;
	}

	public DefaultTableModel getDefaultTableModel() {
		return defaultTableModel;
	}

	public void setDefaultTableModel(DefaultTableModel defaultTableModel) {
		this.defaultTableModel = defaultTableModel;
	}

	public JScrollPane getjScrollPane() {
		return jScrollPane;
	}

	public void setjScrollPane(JScrollPane jScrollPane) {
		this.jScrollPane = jScrollPane;
	}

	public ArrayList<Data> getList() {
		return list;
	}

	public void setList(ArrayList<Data> list) {
		this.list = list;
	}
	
	

}
