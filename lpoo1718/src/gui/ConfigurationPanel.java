package gui;

import java.text.NumberFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logic.GuardPersonality;

public class ConfigurationPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6441099548808000881L;
	private JFormattedTextField ogresNumberTxtField;
	private JComboBox<GuardPersonality> guardTypeComboBox;

	
	//Interpanel Buttons
	private JButton btnDone;
	
	/**
	 * Create the panel.
	 */
	public ConfigurationPanel() {
		super();
		this.setBounds(0, 0, 594, 451);
		this.setLayout(null);
		this.setVisible(false);
		
		initialize();
	}
	
	private void initialize() {

		/********LABEL*OGRECOUNT****************************************************************************/
		
		JLabel lblOgresCount = new JLabel("Number of Ogres");
		lblOgresCount.setBounds(38, 47, 130, 15);
		this.add(lblOgresCount);
		
		/********LABEL*GUARDPERSONALITY*********************************************************************/

		JLabel lblGuardPersonality = new JLabel("Guard Personality");
		lblGuardPersonality.setBounds(38, 84, 130, 15);
		this.add(lblGuardPersonality);
		
		/********COMBOBOX*GUARDTYPE*************************************************************************/
		
		guardTypeComboBox = new JComboBox<GuardPersonality>();
		guardTypeComboBox.setBounds(180, 79, 100, 24);
		this.add(guardTypeComboBox);
		guardTypeComboBox.setModel(new DefaultComboBoxModel<GuardPersonality>(GuardPersonality.values()));
		
		/********FTEXTFIELD*OGRENUMBER**********************************************************************/
		NumberFormat ogreCountFormat = NumberFormat.getIntegerInstance();
		ogresNumberTxtField = new JFormattedTextField(ogreCountFormat);
		ogresNumberTxtField.setBounds(180, 45, 70, 19);
		this.add(ogresNumberTxtField);
		ogresNumberTxtField.setName("");
		ogresNumberTxtField.setText("1");
		ogresNumberTxtField.setColumns(10);

		/********BTN*DONE***********************************************************************************/
		
		btnDone = new JButton("Done");
		btnDone.setBounds(317, 43, 89, 23);
		this.add(btnDone);
		
		/********BTN*CREATELEVEL****************************************************************************/

		JButton btnCreateLevel = new JButton("Create Level");
		btnCreateLevel.setBounds(38, 170,  200, 53);
		this.add(btnCreateLevel);
		
		/****************************************************************************************************/
		/**********************************************LISTENERS*********************************************/
		
	}

	//GET
	
	public JButton getBtnDone() {
		return btnDone;
	}
	
	public int getOgresNumber() {
		return Integer.parseInt(ogresNumberTxtField.getText());
	}

	public GuardPersonality getGuardType() {
		return (GuardPersonality) guardTypeComboBox.getSelectedItem();
	}
	
	//SET

	public void setBtnDone(JButton btnDone) {
		this.btnDone = btnDone;
	}

}
