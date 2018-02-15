import java.util.Arrays;

public class Map {
	private static final char WALL = 'X';
	private static final char DOOR = 'I';
	private static final char HERO = 'H';
	private static final char GUARD = 'G';
	private static final char LEVER = 'k';
	private static final char STAIRS = 'S';
	private static final char OGRE = 'O';
	private static final char KEYHERO = 'K';

	private char map[][];
	private int exitPosition[]; //Position in pairs (Y,X)
	private int level;
	private int maxLevel;
	private Character characters[];
	boolean isLever; //or Key

	public Map(int size, int lvl) {
		map = new char[size][size];
		level = lvl;
		maxLevel = 2;
		isLever = true;
		initializeMap();
	}

	public Map(int size) {
		map = new char[size][size];
		level = 1; 
		maxLevel = 2;
		isLever = true;
		initializeMap();
	}

	public boolean getIsLever() {
		return isLever;
	}

	public void setIsLever(boolean newOp) {
		isLever = newOp;
	}

	public void getToNextLevel() {
		++level;
		initializeMap();
	}

	public int getMaxLevel() {
		return maxLevel;
	}

	public void setMaxLevel(int newMax) {
		maxLevel = newMax;
	}

	public boolean isWallColliding(Character c) {
		int X = c.getX();
		int Y = c.getY();

		if((getMapPosition(X, Y) == WALL) || (getMapPosition(X, Y) == DOOR))
			return true;
		else if((c.getSymbol() == OGRE) && (getMapPosition(X, Y) == LEVER))
			return true;
		return false;
	}

	public boolean isExitColliding(Character c) {
		int X = c.getX();
		int Y = c.getY();

		if(isExit(X, Y))
			return true;
		return false;
	}

	public boolean asReachedLever(Character c) {
		int X = c.getX();
		int Y = c.getY();

		if(getMapPosition(X, Y) == LEVER)
			return true;
		return false;
	}

	public void initializeMap() {
		switch(level) {
		case 1:
			initializeLvlOne();
			isLever = true;
			characters = new Character[2]; //hero & guard
			characters[0] = new Hero(1,1);
			characters[1] = new Guard(8,1);
			break;
		case 2:
			initializeLvlTwo();
			isLever = false;
			characters = new Character[2]; //hero & ogre
			characters[0] = new Hero(1,8);
			characters[1] = new Ogre(4,1);
			break;
		}
	}

	public Character[] getCharacters() {
		return characters;
	}

	public void updateHeroMapObjects(Character c) {
		//Check if exit has been reached
		if(isExitColliding(c)) {
			((Hero) c).setExitColliding(true);
			//if hero is carrying a key, open exit
			if(c.getSymbol() == KEYHERO) {
				openExit();
			}
		} 
		//Check general objects collisions
		else if(isWallColliding(c))
			((Hero) c).setWallColliding(true);
		//check if lever/key is reached
		if(asReachedLever(c)) {
			((Hero) c).setObjectColliding(true);
			//if it is not a lever - is a key
			if(!(isLever)) {
				//make hero symbol a KEYHERO symbol
				c.setSymbol(KEYHERO);
			} 
			//if it is a lever
			else {
				openExit();
			}
		}
	}

	public void updateOgrePosition(Character c) {
		char ogreCommand;
		boolean collision;
		do {
			collision = false;
			ogreCommand = ((Ogre) c).getNextMove();
			c.updatePosition(ogreCommand);
			if(isWallColliding(c)) {
				c.setToPreviousPosition();
				collision = true;
			}
		} while(collision);
	}

	public void updateMap(char heroCommand) {
		for(int i = 0; i < characters.length; ++i) {
			char symbol = characters[i].getSymbol();
			int X = characters[i].getX();
			int Y = characters[i].getY();
			setMapPosition(X, Y, ' ');

			switch(symbol) {
			case HERO: case KEYHERO:
				characters[i].updatePosition(heroCommand);
				updateHeroMapObjects(characters[i]);
				((Hero) characters[i]).updateHero();
				break;
			case GUARD:
				char guardCommand = ((Guard) characters[i]).updateGuard();
				characters[i].updatePosition(guardCommand);
				if(((Guard) characters[i]).isCaptured((Hero) characters[0]))
					((Hero) characters[0]).setCaptured(true);
				break;
			case OGRE:
				updateOgrePosition(characters[i]);
				if(((Ogre) characters[i]).isCaptured((Hero) characters[0]))
					((Hero) characters[0]).setCaptured(true);
				break;
			default:
				break;
			}

			X = characters[i].getX();
			Y = characters[i].getY();
			symbol = characters[i].getSymbol();
			setMapPosition(X, Y, symbol);
		}
	}

	public void openExit() {
		for(int j = 0; j < exitPosition.length; j+=2) {
			setMapPosition(exitPosition[j+1], exitPosition[j], STAIRS);
		}
	}

	public boolean isExit(int x, int y) {
		for(int j = 0; j < exitPosition.length; j+=2) {
			if((x == exitPosition[j+1]) && (y == exitPosition[j]))
				return true;
		}
		return false;
	}

	public void initializeLvlOne() {

		exitPosition = new int[4];
		exitPosition[0] = 5;
		exitPosition[1] = 0;
		exitPosition[2] = 6;
		exitPosition[3] = 0;

		for(int i = 1; i < map.length-1; ++i) {
			Arrays.fill(map[i], ' ');
		}

		int size = map.length;

		//define wall position WALL
		Arrays.fill(map[0], WALL);
		Arrays.fill(map[size-1], WALL);

		for(int i = 1; i < size-1; ++i) {
			map[i][0] = WALL;
			map[i][size-1] = WALL;
		}

		map[1][0] = WALL;
		map[1][6] = WALL;
		map[2][1] = WALL;
		map[2][2] = WALL;
		map[2][4] = WALL;
		map[2][5] = WALL;
		map[2][6] = WALL;
		map[3][6] = WALL;
		map[4][1] = WALL;
		map[4][2] = WALL;
		map[4][4] = WALL;
		map[4][5] = WALL;
		map[4][6] = WALL;
		map[7][1] = WALL;
		map[7][2] = WALL;
		map[7][4] = WALL;
		map[7][5] = WALL;
		map[7][6] = WALL;
		map[7][7] = WALL;
		map[8][6] = WALL;


		//define door position DOOR
		map[1][4] = DOOR;
		map[3][2] = DOOR;
		map[3][4] = DOOR;
		map[5][0] = DOOR;
		map[6][0] = DOOR;
		map[8][2] = DOOR;
		map[8][4] = DOOR;

		//define hero position HERO
		map[1][1] = HERO;

		//define guard position GUARD
		map[1][8] = GUARD;

		//define lever position LEVER
		map[8][7] = LEVER;
	}

	public void initializeLvlTwo() {

		exitPosition = new int[2];
		exitPosition[0] = 1;
		exitPosition[1] = 0;

		for(int i = 1; i < map.length-1; ++i) {
			Arrays.fill(map[i], ' ');
		}

		int size = map.length;

		//define wall position WALL
		Arrays.fill(map[0], WALL);
		Arrays.fill(map[size-1], WALL);

		for(int i = 1; i < size-1; ++i) {
			map[i][0] = WALL;
			map[i][size-1] = WALL;
		}

		//define door position DOOR
		map[1][0] = DOOR;

		//define hero position HERO
		map[8][1] = HERO;

		//define ogre position OGRE
		map[1][4] = OGRE;

		//define lever position LEVER
		map[1][8] = LEVER;
	}

	public void printMap() {
		int size = map.length;

		for(int i = 0; i < size; ++i) {
			System.out.println(map[i]);
		}

	}

	public void setMapPosition(int x, int y, char c) {
		map[y][x] = c;
	}

	public char getMapPosition(int x, int y) {
		return map[y][x];
	}

	public void setLevel(int newLvl) {
		level = newLvl;
		initializeMap();
	}

	public int getLevel() {
		return level;
	}
}
