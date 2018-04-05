package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import exception.InvalidBoardSizeException;
import logic.GameStartSet;
import logic.Map;

public class LevelCreationPanel extends JPanel {

	public class MouseCreationPanelMouseAdapter extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			int x = e.getX()/20;
			int y = e.getY()/20;
			
			if (e.getButton() == MouseEvent.BUTTON1) //used to draw an image
			{
				if (!map.checkCorners(x,y) && 
						map.verifyMapPosition(x,y, mapCreationPanel.getNewChar()))
					map.setMapPosition(x,y, mapCreationPanel.getNewChar());
			}
			else if( e.getButton() == MouseEvent.BUTTON3) //used to erase an image
			{
				mapCreationPanel.setNewChar(map.getMapPosition(x, y));
				map.setMapPosition(x, y, mapCreationPanel.getOriginalChar(x, y));
			}
			
			mapCreationPanel.setMap(map.toString());
			mapCreationPanel.repaint();

			checkHeroExistance();

			checkKeyExistance();
			
			checkValidCreatedMap();
		}

		/**
		 * 
		 */
		private void checkValidCreatedMap() {
			if(map.validateMapScheme()) {
				btnPlayCreatedLvl.setEnabled(true);
				btnSaveLevel.setEnabled(true);
			}
			else {
				btnPlayCreatedLvl.setEnabled(false);
				btnSaveLevel.setEnabled(false);
			}
		}

		/**
		 * 
		 */
		private void checkHeroExistance() {
			if (map.searchHero())
			{
				btnAddHero.setEnabled(false);
				btnAddHeroWeapon.setEnabled(false);
				if (mapCreationPanel.isNewCharHero())
					mapCreationPanel.setNewCharEmpty();
			}
			else
			{
				btnAddHero.setEnabled(true);
				btnAddHeroWeapon.setEnabled(true);
			}
		}

		/**
		 * 
		 */
		private void checkKeyExistance() {
			if (map.searchKey())
			{
				btnAddKey.setEnabled(false);
				if (mapCreationPanel.isNewCharKey())
					mapCreationPanel.setNewCharEmpty();
			}
			else
				btnAddKey.setEnabled(true);
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4167531419340067575L;
	private Map map;
	private GameStartSet gss;

	private ArrayList<Map> savedLevels;

	private JFormattedTextField gameWidthTextField;
	private JFormattedTextField gameHeightTextField;

	private MapCreationPanel mapCreationPanel;

	private JLabel statusLabel;


	//Interpanel Buttons
	private JButton btnPlayCreatedLvl;
	private JButton btnSaveLevel;
	private JButton btnRestart;
	private JButton btnReturn;
	private JButton btnCreateLevel;

	//Creation Buttons
	private JButton btnAddWall;
	private JButton btnAddDoor;
	private JButton btnAddKey;
	private JButton btnAddHero;
	private JButton btnAddHeroWeapon;
	private JButton btnAddOgre;

	/**
	 * Create the panel.
	 */
	public LevelCreationPanel() {
		super();
		this.setBounds(0, 0, 594, 451);
		this.setLayout(null);
		this.setVisible(false);

		this.savedLevels = new ArrayList<Map>();

		initialize();
	}

	/**
	 * 
	 */
	private void initialize() {
		initializeComponets();
		initializeListeners();
	}

	private void initializeComponets() {

		initializeLabels();

		initializeTextFields();

		initializeCreationBtns();

		initializePlayBtn();

		initializeSaveBtn();

		initializeRestartBtn();

		initializeReturnBtn();

		initializeCreateLevelBtn();

		initializeMapCreationPanel();

	}

	/**
	 * 
	 */
	private void initializeMapCreationPanel() {
		/********PANEL*MAPCREATION***************************************************************************/

		mapCreationPanel = new MapCreationPanel();
		mapCreationPanel.setBounds(35, 152, 200, 200);
		this.add(mapCreationPanel);
		mapCreationPanel.setVisible(false);
	}

	/**
	 * 
	 */
	private void initializeCreateLevelBtn() {
		/********BTN*CREATELEVEL*********************************************************************************/

		btnCreateLevel = new JButton("Create Level");
		btnCreateLevel.setBounds(38, 170,  200, 53);
		this.add(btnCreateLevel);
	}

	/**
	 * 
	 */
	private void initializeReturnBtn() {
		/********BTN*RETURN*********************************************************************************/

		btnReturn = new JButton("Return");
		btnReturn.setBounds(350, 342, 89, 23);
		this.add(btnReturn);
	}

	/**
	 * 
	 */
	private void initializeRestartBtn() {
		/********BTN*RESTART*********************************************************************************/

		btnRestart = new JButton("Restart");
		btnRestart.setEnabled(false);
		btnRestart.setBounds(38, 100, 89, 23);
		this.add(btnRestart);
	}

	/**
	 * 
	 */
	private void initializeSaveBtn() {
		/********BTN*SAVE**********************************************************************************/

		btnSaveLevel = new JButton("Save");
		btnSaveLevel.setEnabled(false);
		btnSaveLevel.setBounds(451, 42, 89, 23);
		add(btnSaveLevel);
	}

	/**
	 * 
	 */
	private void initializePlayBtn() {
		/********BTN*PLAY**********************************************************************************/

		btnPlayCreatedLvl = new JButton("Play");
		btnPlayCreatedLvl.setBounds(350, 43, 89, 23);
		this.add(btnPlayCreatedLvl);
		btnPlayCreatedLvl.setEnabled(false);
	}

	/**
	 * 
	 */
	private void initializeCreationBtns() {

		initializeObjectsCreationBtns();

		initializeCharactersCreationBtns();
	}

	/**
	 * 
	 */
	private void initializeCharactersCreationBtns() {
		btnAddHero = new JButton("Add Hero");
		btnAddHero.setEnabled(false);
		btnAddHero.setBounds(350, 205, 150, 23);
		this.add(btnAddHero);

		btnAddHeroWeapon = new JButton("Add Weapon");
		btnAddHeroWeapon.setBounds(350, 240, 150, 23);
		this.add(btnAddHeroWeapon);
		btnAddHeroWeapon.setEnabled(false);

		btnAddOgre = new JButton("Add Ogre");
		btnAddOgre.setEnabled(false);
		btnAddOgre.setBounds(350, 275, 150, 23);
		this.add(btnAddOgre);
	}

	/**
	 * 
	 */
	private void initializeObjectsCreationBtns() {
		btnAddWall = new JButton("Add wall");
		btnAddWall.setEnabled(false);
		btnAddWall.setBounds(350, 100, 150, 23);
		this.add(btnAddWall);

		btnAddDoor = new JButton("Add door");
		btnAddDoor.setEnabled(false);
		btnAddDoor.setBounds(350, 135, 150, 23);
		this.add(btnAddDoor);

		btnAddKey = new JButton("Add key");
		btnAddKey.setEnabled(false);
		btnAddKey.setBounds(350, 170, 150, 23);
		this.add(btnAddKey);
	}

	/**
	 * 
	 */
	private void initializeTextFields() {
		/********TEXTFIELD*WIDTH****************************************************************************/

		NumberFormat dimensionFormat = NumberFormat.getIntegerInstance();
		gameWidthTextField = new JFormattedTextField(dimensionFormat);
		gameWidthTextField.setBounds(152, 44, 70, 19);
		this.add(gameWidthTextField);
		gameWidthTextField.setName("");
		gameWidthTextField.setText("10");
		gameWidthTextField.setColumns(10);



		/********TEXTFIELD*HEIGTH***************************************************************************/

		gameHeightTextField = new JFormattedTextField(dimensionFormat);
		gameHeightTextField.setBounds(152, 64, 70, 19);
		this.add(gameHeightTextField);
		gameHeightTextField.setName("");
		gameHeightTextField.setText("10");
		gameHeightTextField.setColumns(10);
	}

	/**
	 * 
	 */
	private void initializeLabels() {
		/********LABEL*WIDTH********************************************************************************/

		JLabel lblWidth = new JLabel("Game's width");
		lblWidth.setBounds(38, 47, 130, 15);
		this.add(lblWidth);

		/********LABEL*HEIGTH*******************************************************************************/

		JLabel lblHeight = new JLabel("Game's height");
		lblHeight.setBounds(38, 67, 130, 15);
		this.add(lblHeight);

		/********LABEL*STATUS********************************************************************************/

		statusLabel = new JLabel("");
		statusLabel.setBounds(350, 387, 210, 41);
		add(statusLabel);
	}

	/**
	 * 
	 */
	private void initializeListeners() {
		/********BTN*CREATION********************************************************************************/

		initializeCreationBtnListeners();

		/********BTN*RESTART*********************************************************************************/

		initializeRestarBtnListener();

		/********BTN*SAVE***********************************************************************************/

		initializeSaveBtnListener();

		/********PANEL*MAPCREATION***************************************************************************/

		initializeMapCreationPanelListener();
	}

	/**
	 * 
	 */
	private void initializeMapCreationPanelListener() {
		mapCreationPanel.addMouseListener(new MouseCreationPanelMouseAdapter());
	}

	/**
	 * 
	 */
	private void initializeSaveBtnListener() {
		btnSaveLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveMap(map);
				statusLabel.setText("Saved.");
			}
		});
	}

	/**
	 * 
	 */
	private void initializeRestarBtnListener() {
		btnRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnCreateLevel.doClick();
			}
		});
	}

	/**
	 * 
	 */
	private void initializeCreationBtnListeners() {
		initializeCreationObjectsBtnListeners();

		initializeCreationCharactersBtnListeners();
	}

	/**
	 * 
	 */
	private void initializeCreationCharactersBtnListeners() {
		btnAddHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mapCreationPanel.setNewCharToHero();
			}
		});

		btnAddHeroWeapon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mapCreationPanel.setNewCharToArmedHero();
				btnAddHero.setEnabled(false);
			}
		});

		btnAddOgre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mapCreationPanel.setNewCharToOgre();
			}
		});
	}

	/**
	 * 
	 */
	private void initializeCreationObjectsBtnListeners() {
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
	}

	public boolean setMapDimensions() {
		try {
			int width = Integer.parseInt(gameWidthTextField.getText());
			int height =  Integer.parseInt(gameHeightTextField.getText());
			gss = new GameStartSet(width, height);
			mapCreationPanel.setBounds(35, 152, width*GraphicPanel.blockSize, height*GraphicPanel.blockSize);
			map = new Map(width, height, true); //create empty map
			mapCreationPanel.setMap(map);
			mapCreationPanel.setOriginalMap(map);
			mapCreationPanel.repaint();
			statusLabel.setText("");
		} catch (InvalidBoardSizeException e) {
			statusLabel.setText("Invalid Size.");
			return false;
		}

		return true;
	}




	public void changeLevelCreationStatus(boolean status) {
		mapCreationPanel.setVisible(status);
		btnPlayCreatedLvl.setEnabled(false);
		btnSaveLevel.setEnabled(false);
		btnRestart.setEnabled(status);
		btnCreateLevel.setVisible(!status);
		btnSaveLevel.setEnabled(!status);
		btnAddWall.setEnabled(status);
		btnAddDoor.setEnabled(status);
		btnAddKey.setEnabled(status);
		btnAddHero.setEnabled(status);
		btnAddHeroWeapon.setEnabled(status);
		btnAddOgre.setEnabled(status);
	}

	public void saveMap(logic.Map map) {
		logic.Map newMap = new logic.Map(0);
		newMap.initializeMap(map.getMapScheme(), true, true, false);
		savedLevels.add(newMap);
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

	public JButton getBtnSaveLevel() {
		return btnSaveLevel;
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

	public ArrayList<Map> getSavedLevels() {
		return savedLevels;
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
