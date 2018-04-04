package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import exception.InvalidOgreCountException;
import logic.Game;
import logic.GameStartSet;
import logic.GuardPersonality;
import logic.Map;

public class GameplayPanel extends JPanel implements KeyListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8349617969917306068L;
	private Game game;
	private GameStartSet gss;	
	private LinkedList<JButton> movbuttons = new LinkedList<JButton>(); //has the 4 movement related buttons (up, down, left, right)
	private GraphicPanel gameMapPanel;
	private JLabel gameStatusLabel;

	//Interpanel Buttons
	private JButton btnConfig;
	private JButton btnNewGame;
	private JButton btnSaveGame;
	private JButton btnLoadGame;

	/**
	 * Create the panel.
	 */
	public GameplayPanel() {
		super();
		setBounds(0, 0, 630, 451);
		setLayout(null);

		try {
			gss = new GameStartSet(2 ,GuardPersonality.DRUNKEN);
		} catch (InvalidOgreCountException e) {
			gss = null;
		}

		game = gss.startNewGame();

		initialize();
	}

	public GraphicPanel getGameMapPanel()
	{
		return gameMapPanel;
	}

	private void initialize() {

		/********BTN*NEWGAME********************************************************************************/

		btnNewGame = new JButton("New Game");
		btnNewGame.setBounds(420, 100, 120, 25);
		this.add(btnNewGame);

		/********BTN*EXIT***********************************************************************************/

		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(420, 400, 120, 25);
		this.add(btnExit);

		/********MOVEMENT*BUTTONS***************************************************************************/

		JButton btnUp = new JButton("Up");
		btnUp.setBounds(439, 206, 80, 25);
		this.add(btnUp);
		btnUp.setEnabled(false);
		this.movbuttons.add(btnUp);

		JButton btnDown = new JButton("Down");
		btnDown.setBounds(436, 300, 80, 25);
		this.add(btnDown);
		btnDown.setEnabled(false);
		this.movbuttons.add(btnDown);

		JButton btnLeft = new JButton("Left"); 
		btnLeft.setBounds(388, 252, 80, 25);
		this.add(btnLeft);
		btnLeft.setEnabled(false);
		this.movbuttons.add(btnLeft);

		JButton btnRight = new JButton("Right");
		btnRight.setBounds(495, 252, 80, 25);
		this.add(btnRight);
		btnRight.setEnabled(false);
		this.movbuttons.add(btnRight);

		/********BTN*CONFIG**********************************************************************************/

		btnConfig = new JButton("Configurations");
		btnConfig.setBounds(35, 35, 200, 53);
		this.add(btnConfig);

		/********GAME*STATUS*LABEL***************************************************************************/

		gameStatusLabel = new JLabel("You can start a new game.");
		gameStatusLabel.setBounds(287, 54, 275, 15);
		this.add(gameStatusLabel);

		/********GAME*MAP*PANEL******************************************************************************/

		gameMapPanel = new GraphicPanel();
		gameMapPanel.setBounds(35, 152, 200, 200);
		this.add(gameMapPanel);
		gameMapPanel.setVisible(true);

		/********BTN*SAVEGAME********************************************************************************/

		btnSaveGame = new JButton("Save");
		btnSaveGame.setBounds(388, 137, 80, 25);
		add(btnSaveGame);
		btnSaveGame.setVisible(true);
		btnSaveGame.setEnabled(false);

		/********BTN*LOADGAME********************************************************************************/

		btnLoadGame = new JButton("Load");
		btnLoadGame.setBounds(495, 137, 80, 25);
		add(btnLoadGame);
		btnLoadGame.setVisible(true);
		btnLoadGame.setEnabled(true);

		initializeListeners(btnNewGame, btnExit, btnUp, btnDown, btnLeft, btnRight);

	}

	/**
	 * @param btnNewGame
	 * @param btnExit
	 * @param btnUp
	 * @param btnDown
	 * @param btnLeft
	 * @param btnRight
	 */
	private void initializeListeners(JButton btnNewGame, JButton btnExit, JButton btnUp, JButton btnDown,
			JButton btnLeft, JButton btnRight) {

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				gameMapPanel.requestFocusInWindow();
			}

		});

		/********BTN*EXIT***********************************************************************************/

		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		/********MOVEMENT*BUTTONS***************************************************************************/

		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game.updateGame('u');
				updateGuiGameSettings(gameMapPanel, gameStatusLabel);
				btnSaveGame.setEnabled(true);
			}
		});

		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game.updateGame('d');
				updateGuiGameSettings(gameMapPanel, gameStatusLabel);
				btnSaveGame.setEnabled(true);
			}
		});

		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game.updateGame('l');
				updateGuiGameSettings(gameMapPanel, gameStatusLabel);
				btnSaveGame.setEnabled(true);
			}
		});

		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game.updateGame('r');
				updateGuiGameSettings(gameMapPanel, gameStatusLabel);
				btnSaveGame.setEnabled(true);
			}
		});

		/********BTN*SAVEGAME********************************************************************************/

		btnSaveGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(saveGame())
					gameStatusLabel.setText("Game Successfully Saved.");
				else
					gameStatusLabel.setText("Sorry, it is impossible to save your game.");
			}
		});

		/********BTN*LOADGAME********************************************************************************/

		btnLoadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(loadGame())
					gameStatusLabel.setText("Game Successfully Loaded.");
				else
					gameStatusLabel.setText("Sorry, it is impossible to load your game.");
			}
		});
	}

	public boolean saveGame() {
		boolean completed = true;

		try {
			FileOutputStream fOut;
			fOut = new FileOutputStream("./saves/gameSave.ser");
			ObjectOutputStream oOut = new ObjectOutputStream(fOut);
			oOut.writeObject(game);
			oOut.close();
			fOut.close();
		} catch (Exception e) {
			completed = false;
			e.printStackTrace();
		}

		return completed;
	}

	public boolean loadGame() {
		boolean completed = true;

		try {
			FileInputStream fIn = new FileInputStream("./saves/gameSave.ser");
			ObjectInputStream oIn = new ObjectInputStream(fIn);
			game = (Game) oIn.readObject();
			oIn.close();
			fIn.close();
			updateGuiGameSettings(gameMapPanel, gameStatusLabel);
			gameMapPanel.repaint();
		}catch (Exception e){
			completed = false;
			e.printStackTrace();
		}

		return completed;
	}

	public void cleanGameStausLabel() {
		gameStatusLabel.setText("");
	}

	public void playMap(logic.Map map) {
		Map newLevel = new Map(1);
		newLevel.initializeMap(map.getMapScheme(), true, true, false);
		game.addLevel(newLevel);
		game.setToLastLevel();
		//game.getCurrentMap().initializeMap(map.getMapScheme());
		gameMapPanel.setBounds(35, 152, map.getMapWidth()*GraphicPanel.blockSize, map.getMapHeight()*GraphicPanel.blockSize);
		gameMapPanel.setMap(game.toString());
		gameMapPanel.repaint();
		gameStatusLabel.setText("You can play now."); //update label
		setEnableMovementBtn(true);
	}

	public int getMoveButton(int code)
	{
		String btn = "";
		switch(code)
		{
		case KeyEvent.VK_LEFT: 	//leftarrow
			btn = "Left";
			break;
		case KeyEvent.VK_UP:	//uparrow
			btn = "Up";
			break;
		case KeyEvent.VK_RIGHT: 	//rightarrow
			btn = "Right";
			break;
		case KeyEvent.VK_DOWN:	//downarrow
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
		int code = e.getKeyCode();

		int index = getMoveButton(code);
		if (index != -1)
		{
			this.movbuttons.get(index).doClick();
		}
	}

	//GET

	public Game getGame() {
		return game;
	}

	public JButton getBtnConfig() {
		return btnConfig;
	}

	public GameStartSet getGameStartSet() {
		return gss;
	}

	public JButton getBtnNewGame() {
		return btnNewGame;
	}

	public JLabel getGameStatusLabel() {
		return gameStatusLabel;
	}

	//SET

	public void setGameStartSet(GameStartSet gss) {
		this.gss = gss;
	}

	//AUX

	public JButton getBtnSaveGame() {
		return btnSaveGame;
	}

	public JButton getBtnLoadGame() {
		return btnLoadGame;
	}

	public void setEnableMovementBtn(boolean value)
	{
		for (int i = 0; i < movbuttons.size(); i++)
			movbuttons.get(i).setEnabled(value);
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public void updateGuiGameSettings(GraphicPanel map, JLabel status) {
		map.setMap(game.toString());
		map.repaint(); 
		if (game.isEndGame()) {
			setEnableMovementBtn(false);
			status.setText("Game Over."); //update label
		} else
			setEnableMovementBtn(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
