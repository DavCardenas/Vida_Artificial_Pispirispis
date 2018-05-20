package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logic.OptionsGraphics;

public class PanelChooseGraphic extends JPanel{

	private JComboBox<OptionsGraphics> cmbChooseGraphic;
	private JButton btnShowGraphic;
	private JLabel lbText;
	
	public PanelChooseGraphic() {
		// TODO Auto-generated constructor stub
		this.setSize(100,100);
		this.setLayout(new FlowLayout());
		this.initComponents();
	}
	
	public void initComponents() {
		this.lbText = new JLabel("Ver gráfica de: ");
		this.add(this.lbText);
		this.cmbChooseGraphic = new JComboBox<>();
		this.cmbChooseGraphic.setSize(getWidth(),200);
		this.cmbChooseGraphic.setModel(new DefaultComboBoxModel<OptionsGraphics>(OptionsGraphics.values()));
		this.add(cmbChooseGraphic);
		this.btnShowGraphic = new JButton("Mostrar");
		this.btnShowGraphic.setEnabled(false);
		this.add(this.btnShowGraphic);
	}

	public JComboBox<OptionsGraphics> getCmbChooseGraphic() {
		return cmbChooseGraphic;
	}

	public void setCmbChooseGraphic(JComboBox<OptionsGraphics> cmbChooseGraphic) {
		this.cmbChooseGraphic = cmbChooseGraphic;
	}

	public JButton getBtnShowGraphic() {
		return btnShowGraphic;
	}

	public void setBtnShowGraphic(JButton btnShowGraphic) {
		this.btnShowGraphic = btnShowGraphic;
	}

	public JLabel getLbText() {
		return lbText;
	}

	public void setLbText(JLabel lbText) {
		this.lbText = lbText;
	}
	
	
}
