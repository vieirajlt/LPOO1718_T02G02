package gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import logic.Game;

/**
 * This class represents a specific type of {@link JPanel}.
 * This specific type is used to paint all the current {@link Game} cycle elements
 * in a user friendly way using various images.
 * 
 * @author João Vieira
 * @author Susana Lima
 *
 */
public class GraphicPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6522359064162807356L;
	/**
	 * map is the string that stores all objects/characters positioning information
	 * on the current {@link Game} cycle.
	 */
	private String map;

	/**
	 * blockSize is the value that represents the width and height of an image.
	 */
	protected static int blockSize = 20;

	/**
	 * All images used in this GraphicPanel.
	 * All images used in the program.
	 */
	private BufferedImage wall;
	private BufferedImage openDoor;
	private BufferedImage closeDoor;
	private BufferedImage club;
	private BufferedImage keyClub;
	private BufferedImage floor;
	private BufferedImage guard;
	private BufferedImage sleepingGuard;
	private BufferedImage hero;
	private BufferedImage keyHero;
	private BufferedImage armedHero;
	private BufferedImage key;
	private BufferedImage ogre;
	private BufferedImage stunnedOgre;

	/**
	 * Predefine char association for representation of different types of objects/characters.
	 */
	protected static final char EMPTY = ' ';
	protected static final char WALL = 'X';
	protected static final char DOOR = 'I';
	protected static final char HERO = 'H';
	protected static final char ARMEDHERO = 'A';
	protected static final char GUARD = 'G';
	protected static final char SLEEPINGGUARD = 'g';
	protected static final char LEVER = 'k';
	protected static final char STAIRS = 'S';
	protected static final char OGRE = 'O';
	protected static final char STUNNEDOGRE = '8';
	protected static final char KEYHERO = 'K'; //hero with key
	protected static final char CLUB = '*';
	protected static final char KEYCLUB = '$'; //club that hit key at a certain point

	/**
	 * Create this GraphicPanel.
	 * All this GraphicPanel images are loaded.
	 */
	public GraphicPanel() {
		super();
		try {
			wall = ImageIO.read(new File("./images/wall.png"));
			openDoor = ImageIO.read(new File("./images/opendoor.png"));
			closeDoor = ImageIO.read(new File("./images/closedoor.png"));
			club = ImageIO.read(new File("./images/club.png"));
			keyClub  = ImageIO.read(new File("./images/keyclub.png"));
			floor = ImageIO.read(new File("./images/floor.png"));
			guard = ImageIO.read(new File("./images/guard.png"));
			sleepingGuard = ImageIO.read(new File("./images/guardsleeping.png"));
			hero = ImageIO.read(new File("./images/hero.png"));
			armedHero = ImageIO.read(new File("./images/armedhero.png"));
			keyHero = ImageIO.read(new File("./images/keyhero.png"));
			key = ImageIO.read(new File("./images/key.png"));
			ogre = ImageIO.read(new File("./images/ogre.png"));
			stunnedOgre = ImageIO.read(new File("./images/stunnedogre.png"));

		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		map = "";
	}
	
	/**
	 * Retrieve this GraphicPanel map value.
	 * 
	 * @return the value of this GraphicPanel map.
	 */
	public String getMap() {
		return map;
	}

	/**
	 * Set this GraphicPanel map value.
	 * 
	 * @param map the new value of map
	 */
	public void setMap(String map) {
		this.map = map;
	}

	/**
	 * Set this GraphicPanel map value.
	 * 
	 * @param map the new value of map
	 */
	public void setMap(logic.Map map) {
		this.map = map.toString();
	}

	/**
	 * Paint all the elements from this GraphicPanel map int the respective positions.
	 * 
	 * @param g the original Graphics object
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int x = 0, y = 0;
		for (int i = 0; i < map.length();i++)
		{
			boolean isNewLine = false;

			switch(map.charAt(i))
			{
			case '\n':
				y+=blockSize;
				isNewLine = true;
				x = 0;
				break;
			default:
				if(!paintObjectChar(g, x, y, map.charAt(i)))
					paintCharacterChar(g, x, y, map.charAt(i));
				break;
			}
			if(!isNewLine)
				x+=blockSize;
		}
	}

	/**
	 * Paint a specified image in the given x, y position.
	 * The image is specified by the given symbol.
	 * This function is used to paint regular static objects from the game.
	 * 
	 * @param g the original Graphics object
	 * @param x the x position where to paint the image
	 * @param y the y position where to paint the image
	 * @param symbol char that identifies the image to be painted
	 * @return true if the image based on the given symbol was successfully painted, false otherwise
	 */
	private boolean paintObjectChar(Graphics g, int x, int y, char symbol) {
		switch(symbol)
		{
		case WALL:
			g.drawImage(wall, x, y, null);
			break;
		case STAIRS:
			g.drawImage(openDoor, x, y, null);
			break;
		case DOOR:
			g.drawImage(closeDoor, x, y, null);
			break;
		case CLUB:
			g.drawImage(club, x, y, null);
			break;
		case KEYCLUB:
			g.drawImage(keyClub, x, y, null);
			break;
		case LEVER:
			g.drawImage(key, x, y, null);
			break;
		case EMPTY:
			g.drawImage(floor, x, y, null);
			break;
		default:
			return false;
		}
		return true;
	}

	/**
	 * Paint a specified image in the given x, y position.
	 * The image is specified by the given symbol.
	 * This function is used to paint the game's {@link Character}s.
	 * 
	 * @param g the original Graphics object
	 * @param x the x position where to paint the image
	 * @param y the y position where to paint the image
	 * @param symbol char that identifies the image to be painted
	 * @return true if the image based on the given symbol was successfully painted, false otherwise
	 */
	private boolean paintCharacterChar(Graphics g, int x, int y, char symbol) {
		switch(symbol)
		{
		case GUARD:
			g.drawImage(guard, x, y, null);
			break;
		case SLEEPINGGUARD:
			g.drawImage(sleepingGuard, x, y, null);
			break;
		case HERO:
			g.drawImage(hero, x, y, null);
			break;
		case ARMEDHERO:
			g.drawImage(armedHero, x, y, null);
			break;
		case KEYHERO:
			g.drawImage(keyHero, x, y, null);
			break;
		case OGRE:
			g.drawImage(ogre, x, y, null);
			break;
		case STUNNEDOGRE:
			g.drawImage(stunnedOgre, x, y, null);
			break;
		default:
			return false;
		}
		return true;
	}

}
