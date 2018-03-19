package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import exception.InvalidBoardSizeException;
import logic.GameStartSet;
import logic.Map;

public class LevelCreationPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4167531419340067575L;
	private Map map;
	private GameStartSet gss;	
	
	private JFormattedTextField gameWidthTextField;
	private JFormattedTextField gameHeightTextField;
	
	private MapCreationPanel mapCreationPanel;
	
	private JLabel statusLabel;

	//Interpanel Buttons
	private JButton btnPlayCreatedLvl;
	private JButton btnRestart;
	private JButton btnReturn;
	private JButton btnCreateLevel;

	/**
	 * Create the panel.
	 */
	public LevelCreationPanel() {
		super();
		this.setBounds(0, 0, 594, 451);
		this.setLayout(null);
		this.setVisible(false);

		initialize();
	}

	private void initialize() {

		/********LABEL*WIDTH********************************************************************************/

		JLabel lblWidth = new JLabel("Game's width");
		lblWidth.setBounds(38, 47, 130, 15);
		this.add(lblWidth);

		/********TEXTFIELD*WIDTH****************************************************************************/

		NumberFormat dimensionFormat = NumberFormat.getIntegerInstance();
		gameWidthTextField = new JFormattedTextField(dimensionFormat);
		gameWidthTextField.setBounds(152, 44, 70, 19);
		this.add(gameWidthTextField);
		gameWidthTextField.setName("");
		gameWidthTextField.setText("10");
		gameWidthTextField.setColumns(10);

		/********LABEL*HEIGTH*******************************************************************************/

		JLabel lblHeight = new JLabel("Game's height");
		lblHeight.setBounds(38, 67, 130, 15);
		this.add(lblHeight);

		/********TEXTFIELD*HEIGTH***************************************************************************/

		gameHeightTextField = new JFormattedTextField(dimensionFormat);
		gameHeightTextField.setBounds(152, 64, 70, 19);
		this.add(gameHeightTextField);
		gameHeightTextField.setName("");
		gameHeightTextField.setText("10");
		gameHeightTextField.setColumns(10);

		/********BTN*CREATION********************************************************************************/

		JButton btnAddWall = new JButton("Add wall");
		btnAddWall.setBounds(317, 103, 89, 23);
		this.add(btnAddWall);

		JButton btnAddDoor = new JButton("Add door");
		btnAddDoor.setBounds(317, 143, 89, 23);
		this.add(btnAddDoor);

		JButton btnAddKey = new JButton("Add key");
		btnAddKey.setBounds(317, 183, 89, 23);
		this.add(btnAddKey);

		JButton btnAddHero = new JButton("Add Hero");
		btnAddHero.setBounds(317, 223, 89, 23);
		this.add(btnAddHero);

		JButton btnAddHeroWeapon = new JButton("Add Weapon");
		btnAddHeroWeapon.setBounds(317, 263, 89, 23);
		this.add(btnAddHeroWeapon);
		btnAddHeroWeapon.setEnabled(false);

		JButton btnAddOgre = new JButton("Add Ogre");
		btnAddOgre.setBounds(317, 303, 89, 23);
		this.add(btnAddOgre);
		
		/********BTN*PLAY**********************************************************************************/

		btnPlayCreatedLvl = new JButton("Play");
		btnPlayCreatedLvl.setBounds(317, 43, 89, 23);
		this.add(btnPlayCreatedLvl);
		btnPlayCreatedLvl.setEnabled(false);

		/********BTN*RESTART*********************************************************************************/

		btnRestart = new JButton("Restart");
		btnRestart.setBounds(38, 100, 89, 23);
		this.add(btnRestart);

		/********BTN*RETURN*********************************************************************************/

		btnReturn = new JButton("Return");
		btnReturn.setBounds(317, 343, 89, 23);
		this.add(btnReturn);
		
		/********BTN*CREATELEVEL*********************************************************************************/
		
		btnCreateLevel = new JButton("Create Level");
		btnCreateLevel.setBounds(38, 170,  200, 53);
		this.add(btnCreateLevel);

		/********PANEL*MAPCREATION***************************************************************************/

		mapCreationPanel = new MapCreationPanel();
		mapCreationPanel.setBounds(35, 152, 200, 200);
		this.add(mapCreationPanel);
		
		/********LABEL*STATUS********************************************************************************/
		
		statusLabel = new JLabel("");
		statusLabel.setBounds(42, 394, 326, 15);
		add(statusLabel);
		mapCreationPanel.setVisible(true);

		/****************************************************************************************************/
		/**********************************************LISTENERS*********************************************/

		/********BTN*CREATION********************************************************************************/

		btnAddWall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mapCreationPanel.setNewCharToWall();
			}
		});

		btnAddDoor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mapCreationPanel.setNewCharToDoor();
			}
		});

		btnAddKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mapCreationPanel.setNewCharToKey();
			}
		});

		btnAddHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mapCreationPanel.setNewCharToHero();
			}
		});

		btnAddHeroWeapon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mapCreationPanel.setNewCharToHero();
			}
		});

		btnAddOgre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mapCreationPanel.setNewCharToOgre();
			}
		});

		/********PANEL*MAPCREATION***************************************************************************/

		mapCreationPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getX()/20;
				int y = e.getY()/20;
				// 1- esquerdo, 3- direito
				if (e.getButton() == 1) //used to draw an image
				{
					if (!map.checkCorners(x,y))
						map.setAndVerifyMapPosition(x,y, mapCreationPanel.getNewChar());
				}
				else
				{
					if( e.getButton() == 3) //used to erase an image
					{
						mapCreationPanel.setNewChar(map.getMapPosition(x, y));
						map.setMapPosition(x, y, ' ');
					}

				}
				mapCreationPanel.setMap(map.toString());
				mapCreationPanel.repaint();

				//only one hero
				if (map.searchHero())
				{
					btnAddHero.setEnabled(false);
					if (mapCreationPanel.isNewCharHero())
						mapCreationPanel.setNewChar(' ');
				}
				else
					btnAddHero.setEnabled(true);

				//only lets the player play the created level if it is a valid one
				if(map.validateMapScheme())
					btnPlayCreatedLvl.setEnabled(true);
				else
					btnPlayCreatedLvl.setEnabled(false);
			}
		});

	}
	
	public boolean setMapDimensions() {
		try {
			int width = Integer.parseInt(gameWidthTextField.getText());
			int height =  Integer.parseInt(gameHeightTextField.getText());;
			gss = new GameStartSet(width, height);
			statusLabel.setText("");
		} catch (InvalidBoardSizeException e) {
			statusLabel.setText("Invalid Size.");
			return false;
		}
		
		return true;
	}

	//GET
	
	public JButton getBtnPlayCreatedLvl() {
		return btnPlayCreatedLvl;
	}

	public JButton getBtnRestart() {
		return btnRestart;
	}

	public JButton getBtnReturn() {
		return btnReturn;
	}

	public JButton getBtnCreateLevel() {
		return btnCreateLevel;
	}
	
	public Map getMap() {
		return map;
	}

	public GameStartSet getGss() {
		return gss;
	}
	
	public MapCreationPanel getMapCreationPanel() {
		return mapCreationPanel;
	}
	
	public JLabel getStatusLabel() {
		return statusLabel;
	}

	//SET

	public void setGss(GameStartSet gss) {
		this.gss = gss;
	}

	public void setMapCreationPanel(MapCreationPanel mapCreationPanel) {
		this.mapCreationPanel = mapCreationPanel;
	}

	public void setStatusLabel(JLabel statusLabel) {
		this.statusLabel = statusLabel;
	}
}
