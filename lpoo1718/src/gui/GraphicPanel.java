package gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GraphicPanel extends JPanel{

	private String map;

	private static int blockSize = 20;

	private BufferedImage wall;
	private BufferedImage openDoor;
	private BufferedImage closeDoor;
	private BufferedImage club;
	private BufferedImage floor;
	private BufferedImage guard;
	private BufferedImage hero;
	private BufferedImage key;
	private BufferedImage ogre;

	private static final char EMPTY = ' ';
	private static final char WALL = 'X';
	private static final char DOOR = 'I';
	private static final char HERO = 'H';
	private static final char ARMEDHERO = 'A';
	private static final char GUARD = 'G';
	private static final char SLEEPINGGUARD = 'g';
	private static final char LEVER = 'k';
	private static final char STAIRS = 'S';
	private static final char OGRE = 'O';
	private static final char STUNNEDOGRE = '8';
	private static final char KEYHERO = 'K'; //hero with key
	private static final char CLUB = '*';
	private static final char KEYCLUB = '$'; //club that hit key at a certain point

	public GraphicPanel() {
		super();
		try {
			//URL resource = getClass().getResource(name)
			wall = ImageIO.read(new File("./images/wall.png"));
			openDoor = ImageIO.read(new File("./images/opendoor.png"));
			closeDoor = ImageIO.read(new File("./images/closedoor.png"));
			club = ImageIO.read(new File("./images/club.png"));
			floor = ImageIO.read(new File("./images/floor.png"));
			guard = ImageIO.read(new File("./images/guard.png"));
			hero = ImageIO.read(new File("./images/hero.png"));
			key = ImageIO.read(new File("./images/key.png"));
			ogre = ImageIO.read(new File("./images/ogre.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		/*File directory = new File("./");
		System.out.println(directory.getAbsolutePath());*/
		map = "";
	}



	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int x = 0, y = 0;
		for (int i = 0; i < map.length();i++)
		{
			boolean isNewLine = false;
			switch(map.charAt(i))
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
			case EMPTY:
				g.drawImage(floor, x, y, null);
				break;
			case GUARD:
				g.drawImage(guard, x, y, null);
				break;
			case HERO:
				g.drawImage(hero, x, y, null);
				break;
			case LEVER:
				g.drawImage(key, x, y, null);
				break;
			case OGRE:
				g.drawImage(ogre, x, y, null);
				break;
			case '\n':
				y+=blockSize;
				isNewLine = true;
				x = 0;
				break;
			}
			if(!isNewLine)
				x+=blockSize;
		}
	}
}
