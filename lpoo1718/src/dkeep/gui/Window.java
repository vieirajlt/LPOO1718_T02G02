package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

import logic.Game;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Window {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{118, 139, 19, 0, 0, 0, 75, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNumberOfOgres = new JLabel("Number of Ogres");
		GridBagConstraints gbc_lblNumberOfOgres = new GridBagConstraints();
		gbc_lblNumberOfOgres.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberOfOgres.gridx = 0;
		gbc_lblNumberOfOgres.gridy = 0;
		frame.getContentPane().add(lblNumberOfOgres, gbc_lblNumberOfOgres);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		frame.getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblGuardPersonality = new JLabel("Guard Personality");
		GridBagConstraints gbc_lblGuardPersonality = new GridBagConstraints();
		gbc_lblGuardPersonality.insets = new Insets(0, 0, 5, 5);
		gbc_lblGuardPersonality.gridx = 0;
		gbc_lblGuardPersonality.gridy = 1;
		frame.getContentPane().add(lblGuardPersonality, gbc_lblGuardPersonality);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Rookie", "Suspicious", "Drunken"}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.anchor = GridBagConstraints.WEST;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 1;
		frame.getContentPane().add(comboBox, gbc_comboBox);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Courier New", Font.PLAIN, 13));
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridheight = 7;
		gbc_textArea.gridwidth = 4;
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 2;
		frame.getContentPane().add(textArea, gbc_textArea);
		
			
			JButton btnUp = new JButton("Up");
			btnUp.setEnabled(false);
			GridBagConstraints gbc_btnUp = new GridBagConstraints();
			gbc_btnUp.anchor = GridBagConstraints.NORTH;
			gbc_btnUp.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnUp.insets = new Insets(0, 0, 5, 5);
			gbc_btnUp.gridx = 5;
			gbc_btnUp.gridy = 4;
			frame.getContentPane().add(btnUp, gbc_btnUp);
		
		JButton btnLeft = new JButton("Left");
		btnLeft.setEnabled(false);
		GridBagConstraints gbc_btnLeft = new GridBagConstraints();
		gbc_btnLeft.anchor = GridBagConstraints.NORTH;
		gbc_btnLeft.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLeft.insets = new Insets(0, 0, 5, 5);
		gbc_btnLeft.gridx = 4;
		gbc_btnLeft.gridy = 5;
		frame.getContentPane().add(btnLeft, gbc_btnLeft);
		
		JButton btnRight = new JButton("Right");
		btnRight.setEnabled(false);
		GridBagConstraints gbc_btnRight = new GridBagConstraints();
		gbc_btnRight.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnRight.insets = new Insets(0, 0, 5, 0);
		gbc_btnRight.gridx = 6;
		gbc_btnRight.gridy = 5;
		frame.getContentPane().add(btnRight, gbc_btnRight);
		
		JButton btnDown = new JButton("Down");
		btnDown.setEnabled(false);
		GridBagConstraints gbc_btnDown = new GridBagConstraints();
		gbc_btnDown.anchor = GridBagConstraints.NORTH;
		gbc_btnDown.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDown.insets = new Insets(0, 0, 5, 5);
		gbc_btnDown.gridx = 5;
		gbc_btnDown.gridy = 6;
		frame.getContentPane().add(btnDown, gbc_btnDown);
		
		JButton btnExit = new JButton("Exit");
		GridBagConstraints gbc_btnExit = new GridBagConstraints();
		gbc_btnExit.anchor = GridBagConstraints.NORTH;
		gbc_btnExit.insets = new Insets(0, 0, 5, 5);
		gbc_btnExit.gridx = 5;
		gbc_btnExit.gridy = 8;
		frame.getContentPane().add(btnExit, gbc_btnExit);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 System.exit(0);	
			}
		});
		
		
		JLabel lblYouVaPlay = new JLabel("You can start a new game");
		GridBagConstraints gbc_lblYouVaPlay = new GridBagConstraints();
		gbc_lblYouVaPlay.insets = new Insets(0, 0, 0, 5);
		gbc_lblYouVaPlay.gridx = 0;
		gbc_lblYouVaPlay.gridy = 9;
		frame.getContentPane().add(lblYouVaPlay, gbc_lblYouVaPlay);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnUp.setEnabled(true);
				btnDown.setEnabled(true);
				btnLeft.setEnabled(true);
				btnRight.setEnabled(true);
				Game game = new Game(); //starts new game
				textArea.setText(game.toString()); //print game
				lblYouVaPlay.setText("You can play now"); //update label
			}
		});
		GridBagConstraints gbc_btnNewGame = new GridBagConstraints();
		gbc_btnNewGame.anchor = GridBagConstraints.EAST;
		gbc_btnNewGame.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewGame.gridx = 5;
		gbc_btnNewGame.gridy = 2;
		frame.getContentPane().add(btnNewGame, gbc_btnNewGame);
	}

}
