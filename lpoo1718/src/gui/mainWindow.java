package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Component;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class mainWindow {

	private JFrame frmGuidedProjectGui;
	private JTextField ogresNumberTxtField;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainWindow window = new mainWindow();
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
	public mainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGuidedProjectGui = new JFrame();
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
		
		ogresNumberTxtField = new JTextField();
		ogresNumberTxtField.setName("");
		ogresNumberTxtField.setText("1");
		ogresNumberTxtField.setBounds(190, 38, 70, 19);
		frmGuidedProjectGui.getContentPane().add(ogresNumberTxtField);
		ogresNumberTxtField.setColumns(10);
		
		JComboBox<String> guardTypeComboBox = new JComboBox<String>();
		guardTypeComboBox.addItem("Drunken");
		guardTypeComboBox.addItem("Rookie");
		guardTypeComboBox.addItem("Suspicious");
		guardTypeComboBox.setBounds(190, 62, 100, 24);
		frmGuidedProjectGui.getContentPane().add(guardTypeComboBox);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.setBounds(420, 115, 120, 25);
		frmGuidedProjectGui.getContentPane().add(btnNewGame);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(420, 390, 120, 25);
		frmGuidedProjectGui.getContentPane().add(btnExit);
		
		JButton btnUp = new JButton("Up");
		btnUp.setBounds(440, 200, 80, 25);
		frmGuidedProjectGui.getContentPane().add(btnUp);
		
		JButton btnLeft = new JButton("Left"); 
		btnLeft.setBounds(395, 237, 80, 25);
		frmGuidedProjectGui.getContentPane().add(btnLeft);
		
		JButton btnRigth = new JButton("Rigth");
		btnRigth.setBounds(485, 237, 80, 25);
		frmGuidedProjectGui.getContentPane().add(btnRigth);
		
		JButton btnDown = new JButton("Down");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnDown.setBounds(440, 270, 80, 25);
		frmGuidedProjectGui.getContentPane().add(btnDown);
		
		JTextPane infoTextPane = new JTextPane();
		infoTextPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		infoTextPane.setText("You can start a new game.");
		infoTextPane.setBounds(29, 417, 330, 21);
		frmGuidedProjectGui.getContentPane().add(infoTextPane);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "SwingAction_1");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
