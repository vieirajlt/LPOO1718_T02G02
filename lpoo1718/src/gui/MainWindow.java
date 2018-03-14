package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Component;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

import logic.Game;
import logic.GuardPersonality;

import javax.swing.JFormattedTextField;
import java.awt.Font;

public class MainWindow {

	private JFrame frmGuidedProjectGui;
	private JFormattedTextField ogresNumberTxtField;
	private Game game;
	private LinkedList<JButton> movbuttons = new LinkedList<JButton>(); //has the 4 movement related buttons (up, down, left, right)

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmGuidedProjectGui.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	public void setEnableBtn(LinkedList<JButton> jb, boolean value)
	{
		for (int i = 0; i < jb.size(); i++)
			jb.get(i).setEnabled(value);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGuidedProjectGui = new JFrame();
		frmGuidedProjectGui.getContentPane().setFont(new Font("Dialog", Font.PLAIN, 18));
		frmGuidedProjectGui.setTitle("Guided Project GUI");
		frmGuidedProjectGui.setResizable(false);
		frmGuidedProjectGui.setBounds(100, 100, 600, 480);
		frmGuidedProjectGui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmGuidedProjectGui.getContentPane().setLayout(null);

		JLabel lblOgresCount = new JLabel("Number of Ogres");
		lblOgresCount.setBounds(50, 40, 130, 15);
		frmGuidedProjectGui.getContentPane().add(lblOgresCount);

		JLabel lblGuardPersonality = new JLabel("Guard Personality");
		lblGuardPersonality.setBounds(50, 67, 130, 15);
		frmGuidedProjectGui.getContentPane().add(lblGuardPersonality);

		NumberFormat ogreCountFormat = NumberFormat.getIntegerInstance();
		ogresNumberTxtField = new JFormattedTextField(ogreCountFormat);
		ogresNumberTxtField.setName("");
		ogresNumberTxtField.setText("1");
		ogresNumberTxtField.setBounds(190, 38, 70, 19);
		frmGuidedProjectGui.getContentPane().add(ogresNumberTxtField);
		ogresNumberTxtField.setColumns(10);

		JComboBox<GuardPersonality> guardTypeComboBox = new JComboBox<GuardPersonality>();
		guardTypeComboBox.setModel(new DefaultComboBoxModel<GuardPersonality>(GuardPersonality.values()));
		guardTypeComboBox.setBounds(190, 62, 100, 24);
		frmGuidedProjectGui.getContentPane().add(guardTypeComboBox);

		JButton btnNewGame = new JButton("New Game");
		btnNewGame.setBounds(420, 115, 120, 25);
		frmGuidedProjectGui.getContentPane().add(btnNewGame);

		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(420, 390, 120, 25);
		frmGuidedProjectGui.getContentPane().add(btnExit);

		JButton btnUp = new JButton("Up");
		btnUp.setEnabled(false);
		btnUp.setBounds(440, 200, 80, 25);
		frmGuidedProjectGui.getContentPane().add(btnUp);
		this.movbuttons.add(btnUp);

		JButton btnLeft = new JButton("Left"); 
		btnLeft.setEnabled(false);
		btnLeft.setBounds(395, 237, 80, 25);
		frmGuidedProjectGui.getContentPane().add(btnLeft);
		this.movbuttons.add(btnLeft);

		JButton btnRight = new JButton("Right");
		btnRight.setEnabled(false);
		btnRight.setBounds(485, 237, 80, 25);
		frmGuidedProjectGui.getContentPane().add(btnRight);
		this.movbuttons.add(btnRight);

		JButton btnDown = new JButton("Down");
		btnDown.setEnabled(false);
		btnDown.setBounds(440, 270, 80, 25);
		frmGuidedProjectGui.getContentPane().add(btnDown);
		this.movbuttons.add(btnDown);

		JTextPane mapTextPane = new JTextPane();
		mapTextPane.setFont(new Font("Courier 10 Pitch", Font.PLAIN, 18));
		mapTextPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		mapTextPane.setBounds(50, 115, 145, 240);
		frmGuidedProjectGui.getContentPane().add(mapTextPane);

		JLabel gameStatusLabel = new JLabel("You can start a new game.");
		gameStatusLabel.setBounds(52, 395, 275, 15);
		frmGuidedProjectGui.getContentPane().add(gameStatusLabel);


		/**********************Button listener**************************/

		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});


		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setEnableBtn(movbuttons, true);
				int ogreCount = Integer.parseInt(ogresNumberTxtField.getText()); //converts the string to an integer then to an int
				if(isOgreCountValid(ogreCount)) {
					game = new Game(ogreCount,(GuardPersonality)guardTypeComboBox.getSelectedItem()); //starts new game
					mapTextPane.setText(game.toString()); //print game
					gameStatusLabel.setText("You can play now."); //update label
				} else {
					gameStatusLabel.setText("Invalid number of ogres."); //update label
				}
			}
		});

		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game.updateGame('u');
				updateGuiGameSettings(mapTextPane, gameStatusLabel);
			}
		});

		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game.updateGame('d');
				updateGuiGameSettings(mapTextPane, gameStatusLabel);
			}
		});

		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game.updateGame('r');
				updateGuiGameSettings(mapTextPane, gameStatusLabel);
			}
		});

		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game.updateGame('l');
				updateGuiGameSettings(mapTextPane, gameStatusLabel);
			}
		});

	}

	/**********************Aux Functions**************************/

	public boolean isOgreCountValid(int oc) {
		return ((oc > 0) && (oc < 6));
	}
	
	public void updateGuiGameSettings(JTextPane map, JLabel status) {
		map.setText(game.toString()); //print game
		if (game.isEndGame()) {
			setEnableBtn(movbuttons, false);
			status.setText("Game Over."); //update label
		}
	}

}
