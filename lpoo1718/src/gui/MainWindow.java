package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.NumberFormat;
import java.util.LinkedList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logic.Game;
import logic.GuardPersonality;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MainWindow implements KeyListener, MouseListener {

	private JFrame frmGuidedProjectGui;
	private JFormattedTextField ogresNumberTxtField;
	private Game game;
	private LinkedList<JButton> movbuttons = new LinkedList<JButton>(); //has the 4 movement related buttons (up, down, left, right)
	private GraphicPanel gameMapPanel;
	
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
		this.gameMapPanel.addKeyListener(this);
		this.gameMapPanel.requestFocusInWindow();
		this.gameMapPanel.addMouseListener(this);
		
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
		
		frmGuidedProjectGui.getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				gameMapPanel.requestFocusInWindow();
			}
			
		});
		frmGuidedProjectGui.getContentPane().setFont(new Font("Dialog", Font.PLAIN, 18));
		frmGuidedProjectGui.setTitle("Guided Project GUI");
		frmGuidedProjectGui.setResizable(false);
		frmGuidedProjectGui.setBounds(100, 100, 600, 480);
		frmGuidedProjectGui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmGuidedProjectGui.getContentPane().setLayout(null);
		NumberFormat ogreCountFormat = NumberFormat.getIntegerInstance();
		NumberFormat dimensionFormat = NumberFormat.getIntegerInstance();
		JPanel mainPanel = new JPanel();
			
		
		mainPanel.setBounds(0, 0, 630, 451);
		frmGuidedProjectGui.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);

		JButton btnNewGame = new JButton("New Game");
		btnNewGame.setBounds(420, 100, 120, 25);
		mainPanel.add(btnNewGame);

		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(420, 400, 120, 25);
		mainPanel.add(btnExit);

		JButton btnUp = new JButton("Up");
		btnUp.setBounds(439, 206, 80, 25);
		mainPanel.add(btnUp);
		btnUp.setEnabled(false);
		this.movbuttons.add(btnUp);

		JButton btnLeft = new JButton("Left"); 
		btnLeft.setBounds(388, 252, 80, 25);
		mainPanel.add(btnLeft);
		btnLeft.setEnabled(false);
		this.movbuttons.add(btnLeft);

		JButton btnRight = new JButton("Right");
		btnRight.setBounds(495, 252, 80, 25);
		mainPanel.add(btnRight);
		btnRight.setEnabled(false);
		this.movbuttons.add(btnRight);

		JButton btnDown = new JButton("Down");
		btnDown.setBounds(436, 300, 80, 25);
		mainPanel.add(btnDown);
		btnDown.setEnabled(false);
		this.movbuttons.add(btnDown);

		JLabel gameStatusLabel = new JLabel("You can start a new game.");
		gameStatusLabel.setBounds(38, 394, 275, 15);
		mainPanel.add(gameStatusLabel);

		gameMapPanel = new GraphicPanel();
		gameMapPanel.setBounds(35, 152, 200, 200);
		mainPanel.add(gameMapPanel);

		JButton btnConfig = new JButton("Configurations");
		btnConfig.setBounds(35, 35, 200, 53);
		mainPanel.add(btnConfig);

		JPanel configPanel = new JPanel();
		configPanel.setBounds(0, 0, 594, 451);
		frmGuidedProjectGui.getContentPane().add(configPanel);
		configPanel.setLayout(null);
		configPanel.setVisible(false);

		JLabel lblOgresCount = new JLabel("Number of Ogres");
		lblOgresCount.setBounds(38, 47, 130, 15);
		configPanel.add(lblOgresCount);

		JLabel lblGuardPersonality = new JLabel("Guard Personality");
		lblGuardPersonality.setBounds(38, 84, 130, 15);
		configPanel.add(lblGuardPersonality);
		ogresNumberTxtField = new JFormattedTextField(ogreCountFormat);
		ogresNumberTxtField.setBounds(152, 44, 70, 19);
		configPanel.add(ogresNumberTxtField);
		ogresNumberTxtField.setName("");
		ogresNumberTxtField.setText("1");
		ogresNumberTxtField.setColumns(10);

		JComboBox<GuardPersonality> guardTypeComboBox = new JComboBox<GuardPersonality>();
		guardTypeComboBox.setBounds(152, 73, 100, 24);
		configPanel.add(guardTypeComboBox);
		guardTypeComboBox.setModel(new DefaultComboBoxModel<GuardPersonality>(GuardPersonality.values()));

		JButton btnDone = new JButton("Done");
		btnDone.setBounds(317, 43, 89, 23);
		configPanel.add(btnDone);
		
		JButton btnCreateLevel = new JButton("Create Level");
		btnCreateLevel.setBounds(38, 170,  200, 53);
		configPanel.add(btnCreateLevel);
		gameMapPanel.setVisible(true);
		
	
		JPanel createLevelPanel = new JPanel();
		
		
		createLevelPanel.setBounds(0, 0, 594, 451);
		frmGuidedProjectGui.getContentPane().add(createLevelPanel);
		createLevelPanel.setLayout(null);
		createLevelPanel.setVisible(false);
		
		JLabel lblWidth = new JLabel("Game's width");
		lblWidth.setBounds(38, 47, 130, 15);
		createLevelPanel.add(lblWidth);
		
		JFormattedTextField gameWidthTextField = new JFormattedTextField(dimensionFormat);
		gameWidthTextField.setBounds(152, 44, 70, 19);
		createLevelPanel.add(gameWidthTextField);
		gameWidthTextField.setName("");
		gameWidthTextField.setText("10");
		gameWidthTextField.setColumns(10);
		
		JLabel lblHeight = new JLabel("Game's height");
		lblHeight.setBounds(38, 67, 130, 15);
		createLevelPanel.add(lblHeight);
		
		
		JFormattedTextField gameHeightTextField = new JFormattedTextField(dimensionFormat);
		gameHeightTextField.setBounds(152, 64, 70, 19);
		createLevelPanel.add(gameHeightTextField);
		gameHeightTextField.setName("");
		gameHeightTextField.setText("10");
		gameHeightTextField.setColumns(10);
		
		JButton btnPlayCreatedLvl = new JButton("Play");
		btnPlayCreatedLvl.setBounds(317, 43, 89, 23);
		createLevelPanel.add(btnPlayCreatedLvl);
		btnPlayCreatedLvl.setEnabled(false);
		
		JButton btnRestart = new JButton("Restart");
		btnRestart.setBounds(38, 100, 89, 23);
		createLevelPanel.add(btnRestart);
		
		JButton btnAddWall = new JButton("Add wall");
		btnAddWall.setBounds(317, 103, 89, 23);
		createLevelPanel.add(btnAddWall);
		
		JButton btnAddDoor = new JButton("Add door");
		btnAddDoor.setBounds(317, 143, 89, 23);
		createLevelPanel.add(btnAddDoor);
		
		
		JButton btnAddKey = new JButton("Add key");
		btnAddKey.setBounds(317, 183, 89, 23);
		createLevelPanel.add(btnAddKey);
		
		JButton btnAddHero = new JButton("Add Hero");
		btnAddHero.setBounds(317, 223, 89, 23);
		createLevelPanel.add(btnAddHero);
		
		JButton btnAddHeroWeapon = new JButton("Add Weapon");
		btnAddHeroWeapon.setBounds(317, 263, 89, 23);
		createLevelPanel.add(btnAddHeroWeapon);
		btnAddHeroWeapon.setEnabled(false);
		
		JButton btnAddOgre = new JButton("Add Ogre");
		btnAddOgre.setBounds(317, 303, 89, 23);
		createLevelPanel.add(btnAddOgre);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.setBounds(317, 343, 89, 23);
		createLevelPanel.add(btnReturn);
	
		
		GraphicPanel createMapPanel = new GraphicPanel();
		createMapPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getX()/20;
				int y = e.getY()/20;
				// 1- esquerdo, 3- direito
				if (e.getButton() == 1) //used to draw an image
				{
					if (!game.getMap().checkCorners(x,y))
						game.getMap().setAndVerifyMapPosition(x,y, createMapPanel.getNewChar());
				}
				else
				{
					if( e.getButton() == 3) //used to erase an image
					{
						createMapPanel.setNewChar(game.getMap().getMapPosition(x, y));
						game.getMap().setMapPosition(x, y, ' ');
					}
					
				}
				createMapPanel.setMap(game.toString());
				createMapPanel.repaint();
				
				//only one hero
				if (game.getMap().searchHero())
				{
					btnAddHero.setEnabled(false);
					if (createMapPanel.isNewCharHero())
					createMapPanel.setNewChar(' ');
				}
				else
					btnAddHero.setEnabled(true);
				
				//only lets the player play the created level if it is a valid one
				if(game.getMap().validateMapScheme())
					btnPlayCreatedLvl.setEnabled(true);
				else
					btnPlayCreatedLvl.setEnabled(false);
			}
		});
		createMapPanel.setBounds(35, 152, 200, 200);
		createLevelPanel.add(createMapPanel);

	
		
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainPanel.setVisible(true);
				configPanel.setVisible(false);
			}
		});
		
		btnPlayCreatedLvl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainPanel.setVisible(true);
				configPanel.setVisible(false);
				createLevelPanel.setVisible(false);
				game.getMap().initializeMap(game.getMap().getMapScheme());
				gameMapPanel.setMap(game.toString());
				gameMapPanel.repaint();
				gameStatusLabel.setText("You can play now."); //update label
				setEnableBtn(movbuttons, true);
			}
		});


		btnConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainPanel.setVisible(false);
				configPanel.setVisible(true);
			}
		});
		
		btnCreateLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				configPanel.setVisible(false);
				createLevelPanel.setVisible(true);
				int width = Integer.parseInt(gameWidthTextField.getText());
				int height =  Integer.parseInt(gameHeightTextField.getText());;
				game = new Game(width,height,true);
				createMapPanel.setMap(game.toString());
				createMapPanel.repaint();
			}
		});
		
		btnRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnCreateLevel.doClick();
			}
		});
		
		btnAddWall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createMapPanel.setNewCharToWall();
			}
		});
		
		btnAddDoor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createMapPanel.setNewCharToDoor();
			}
		});
		
		btnAddKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createMapPanel.setNewCharToKey();
			}
		});
		
		btnAddHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createMapPanel.setNewCharToHero();
				btnAddHeroWeapon.setEnabled(true);
			}
		});
		
		
		btnAddOgre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createMapPanel.setNewCharToOgre();
			}
		});
		
		
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createLevelPanel.setVisible(false);
				configPanel.setVisible(true);	
			}
		});
		
		
	
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game.updateGame('d');
				updateGuiGameSettings(gameMapPanel, gameStatusLabel);
			}
		});

		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game.updateGame('r');
				updateGuiGameSettings(gameMapPanel, gameStatusLabel);
			}
		});

		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game.updateGame('l');
				updateGuiGameSettings(gameMapPanel, gameStatusLabel);
			}
		});

		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game.updateGame('u');
				updateGuiGameSettings(gameMapPanel, gameStatusLabel);
			}
		});

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
					gameMapPanel.setMap(game.toString());
					gameMapPanel.repaint();
					gameStatusLabel.setText("You can play now."); //update label
				} else {
					gameStatusLabel.setText("Invalid number of ogres."); //update label
				}
			}
		});


		/**********************Button listener**************************/

	}


	public int getMoveButton(int c)
	{
		String btn = "";
		switch(c)
		{
		case 37: 	//leftarrow
			btn = "Left";
			break;
		case 38:	//uparrow
			btn = "Up";
			break;
		case 39: 	//rightarrow
			btn = "Right";
			break;
		case 40:	//downarrow
			btn = "Down";
			break;
		default:
			return -1;
		}
		
		for (int i = 0; i < this.movbuttons.size(); i++)
		{
			if (movbuttons.get(i).getText().equals(btn))
				return i;
		}
		
		return -1;
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		int c = e.getKeyCode();
		
		int index = getMoveButton(c);
		if (index != -1)
		{
			this.movbuttons.get(index).doClick();
		}
	}


	/**********************Aux Functions**************************/

	public boolean isOgreCountValid(int oc) {
		return ((oc > 0) && (oc < 6));
	}

	public void updateGuiGameSettings(GraphicPanel map, JLabel status) {
		map.setMap(game.toString());
		map.repaint(); 
		if (game.isEndGame()) {
			setEnableBtn(movbuttons, false);
			status.setText("Game Over."); //update label
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}
