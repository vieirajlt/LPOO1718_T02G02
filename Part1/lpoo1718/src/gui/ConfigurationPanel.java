package gui;

import java.text.NumberFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logic.GuardPersonality;
import java.awt.Color;
import java.text.Format;

/**
 * This class represents a specific type of {@link JPanel}.
 * This specific type is used for the configuration of some elements of the program.
 * 
 * @author João Vieira
 * @author Susana Lima
 *
 */
public class ConfigurationPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6441099548808000881L;
	/**
	 * ogresNumberTxtField is a JFormattedTextField that stores the number of {@link Ogre} inserted by the user.
	 * Set to 2 by default.
	 */
	private JFormattedTextField ogresNumberTxtField;
	/**
	 * lvlSelectionTextFiels is a JFormattedTextField that stores the level number inserted by the user.
	 * Set to 1 by default.
	 */
	private JFormattedTextField lvlSelectionTextField;
	/**
	 * guardTypeComboBox is a JComboBox that stores the information regarding a {@link GuardPersonality} selected by the user.
	 * Set to {@link GuardPersonality#DRUNKEN} by default.
	 */
	private JComboBox<GuardPersonality> guardTypeComboBox;

	
	/********************Interpanel Buttons***********************/
	/**
	 * btnDone is a JButton used between two panels that allows the user to return to the previous panel.
	 */
	private JButton btnDone;
	
	/**
	 * btnOpenLevelCreationPanel is a JButton used between two panels that allows the user to go to the next panel.
	 */
	private JButton btnOpenLevelCreationPanel;
	
	/**
	 * Create and initialize this ConfiguartionPanel.
	 * This ConfiguarationPanel is set visible.
	 */
	public ConfigurationPanel() {
		super();
		setForeground(Color.WHITE);
		this.setBounds(0, 0, 594, 451);
		this.setLayout(null);
		this.setVisible(false);
		
		initialize();
	}
	
	/**
	 * Initializes this ConfigurationPanel components.
	 */
	private void initialize() {

		initializeLabels();
		
		initializeGuardTypeComboBox();

		initializeBtns();
		
		initializeTextFields();
		
	}

	/**
	 * Initialize this ConfigurationPanel btnDone and btnOpenLevelCreationPanel.
	 */
	private void initializeBtns() {
		/********BTN*DONE***********************************************************************************/
		
		btnDone = new JButton("Done");
		btnDone.setBounds(317, 43, 89, 23);
		this.add(btnDone);
		
		/********BTN*CREATELEVEL****************************************************************************/

		btnOpenLevelCreationPanel = new JButton("Create Level");
		btnOpenLevelCreationPanel.setBounds(38, 170,  200, 53);
		this.add(btnOpenLevelCreationPanel);
	}

	/**
	 * Initialize this ConfigurationPanel ogresNumberTxtField and lvlSelectionTextField.
	 */
	private void initializeTextFields() {
		NumberFormat ogreCountFormat = NumberFormat.getIntegerInstance();
		ogresNumberTxtField = new JFormattedTextField(ogreCountFormat);
		ogresNumberTxtField.setBounds(180, 45, 70, 19);
		this.add(ogresNumberTxtField);
		ogresNumberTxtField.setName("");
		ogresNumberTxtField.setText("2");
		ogresNumberTxtField.setColumns(10);
		
		lvlSelectionTextField = new JFormattedTextField((Format) null);
		lvlSelectionTextField.setText("1");
		lvlSelectionTextField.setName("");
		lvlSelectionTextField.setColumns(10);
		lvlSelectionTextField.setBounds(180, 123, 70, 19);
		add(lvlSelectionTextField);
	}

	/**
	 * Initialize this ConfiguartionPanel guardTypeComboBox.
	 */
	private void initializeGuardTypeComboBox() {
		guardTypeComboBox = new JComboBox<GuardPersonality>();
		guardTypeComboBox.setBounds(180, 79, 100, 24);
		this.add(guardTypeComboBox);
		guardTypeComboBox.setModel(new DefaultComboBoxModel<GuardPersonality>(GuardPersonality.values()));
	}

	/**
	 *Create and initialize lblOgresCount, lblGuardPersonality and lblLevelSelection labels.
	 */
	private void initializeLabels() {
		/********LABEL*OGRECOUNT****************************************************************************/
		
		JLabel lblOgresCount = new JLabel("Number of Ogres");
		lblOgresCount.setBounds(38, 47, 130, 15);
		this.add(lblOgresCount);
		
		/********LABEL*GUARDPERSONALITY*********************************************************************/

		JLabel lblGuardPersonality = new JLabel("Guard Personality");
		lblGuardPersonality.setBounds(38, 84, 130, 15);
		this.add(lblGuardPersonality);
		
		/********LABEL*LVLSELECTION*************************************************************************/
		
		JLabel lblLevelSelection = new JLabel("Level Selection");
		lblLevelSelection.setBounds(38, 125, 130, 15);
		add(lblLevelSelection);
	}

	/*******************GET FUNCTIONS*******************/
	
	/**
	 * Retrieve this ConfigurationPanel btnDone value.
	 * 
	 * @return the value of this ConfigurationPanel btnDone
	 */
	public JButton getBtnDone() {
		return btnDone;
	}
	
	/**
	 * Retrieve the number of ogres inserted in this ConfigurationPanel ogresNumberTxtField.
	 * 
	 * @return the number of ogres inserted in this ConfigurationPanel ogresNumberTxtField
	 */
	public int getOgresNumber() {
		return Integer.parseInt(ogresNumberTxtField.getText());
	}

	/**
	 * Retrieve the type of guard selected in this ConfigurationPanel guardTypeComboBox.
	 * 
	 * @return the type of guard selected in this ConfigurationPanel guardTypeComboBox
	 */
	public GuardPersonality getGuardType() {
		return (GuardPersonality) guardTypeComboBox.getSelectedItem();
	}
	
	/**
	 * Retrieve this ConfigurationPanel btnOpenLevelCreationPanel value.
	 * 
	 * @return the value of this ConfigurationPanel btnOpenLevelCreationPanel
	 */
	public JButton getBtnOpenLevelCreationPanel() {
		return btnOpenLevelCreationPanel;
	}
	
	
	/**
	 * Retrieve the level's number inserted in this ConfigurationPanel lvlSelectionTextField.
	 * 
	 * @return the level's number inserted in this ConfigurationPanel lvlSelectionTextField
	 */
	public int getLvlSelected() {
		return Integer.parseInt(lvlSelectionTextField.getText());
	}
	
}
