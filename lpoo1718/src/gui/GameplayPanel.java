package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import exception.InvalidOgreCountException;
import logic.Game;
import logic.GameStartSet;
import logic.GuardPersonality;

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
	
	/**
	 * Create the panel.
	 */
	public GameplayPanel() {
		super();
		setBounds(0, 0, 630, 451);
		setLayout(null);
		
		game = null;
		try {
			gss = new GameStartSet(1,GuardPersonality.DRUNKEN);
		} catch (InvalidOgreCountException e) {
			gss = null;
		}
		
		initialize();
	}
	
	public GraphicPanel getGameMapPanel()
	{
		return gameMapPanel;
	}
	
	private void initialize() {
		
		/********BTN*NEWGAME********************************************************************************/

		JButton btnNewGame = new JButton("New Game");
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
		
		/****************************************************************************************************/
		/**********************************************LISTENERS*********************************************/
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				gameMapPanel.requestFocusInWindow();
			}

		});
		
		
		
		/********BTN*NEWGAME*********************************************************************************/

		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(gss == null) {
					gameStatusLabel.setText("Invalid number of ogres."); //update label
				} else {
					game = gss.startNewGame(); //starts new game
					gameMapPanel.setMap(game.toString());
					gameMapPanel.repaint();
					gameStatusLabel.setText("You can play now."); //update label
					setEnableBtn(movbuttons, true);
				}
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
			}
		});

		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game.updateGame('d');
				updateGuiGameSettings(gameMapPanel, gameStatusLabel);
			}
		});

		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game.updateGame('l');
				updateGuiGameSettings(gameMapPanel, gameStatusLabel);
			}
		});

		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game.updateGame('r');
				updateGuiGameSettings(gameMapPanel, gameStatusLabel);
			}
		});

	}
	
	public void cleanGameStausLabel() {
		gameStatusLabel.setText("");
	}
	
	public void playMap(logic.Map map) {
		game.getMap().initializeMap(map.getMapScheme());
		gameMapPanel.setBounds(35, 152, map.getMapWidth()*GraphicPanel.blockSize, map.getMapHeight()*GraphicPanel.blockSize);
		gameMapPanel.setMap(game.toString());
		gameMapPanel.repaint();
		gameStatusLabel.setText("You can play now."); //update label
		setEnableBtn(movbuttons, true);
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
	
	//SET
	
	public void setGameStartSet(GameStartSet gss) {
		this.gss = gss;
	}
	
	//AUX
	
	public void setEnableBtn(LinkedList<JButton> jb, boolean value)
	{
		for (int i = 0; i < jb.size(); i++)
			jb.get(i).setEnabled(value);
	}
	
	public void setGame(Game game) {
		this.game = game;
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
