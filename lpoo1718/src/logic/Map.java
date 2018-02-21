package logic;
import java.util.Arrays;

public class Map {
	private static final char EMPTY = ' ';
	private static final char WALL = 'X';
	private static final char DOOR = 'I';
	private static final char HERO = 'H';
	private static final char GUARD = 'G';
	private static final char SLEEPINGGUARD = 'g';
	private static final char LEVER = 'k';
	private static final char STAIRS = 'S';
	private static final char OGRE = 'O';
	private static final char KEYHERO = 'K'; //hero with key
	private static final char CLUB = '*';
	private static final char KEYCLUB = '$'; //club that hit key at a certain point

	private char map[][];
	private Door doors[]; //Position in pairs (Y,X)
	private Character characters[];
	private Unlocker unlockers[];
	private int level;
	private int maxLevel;
	
	//CONSTRUCTORS
	
	public Map(int size, int lvl) {
		map = new char[size][size];
		level = lvl;
		maxLevel = 2;
		initializeMap();
	}

	public Map(int size) {
		map = new char[size][size];
		level = 1; 
		maxLevel = 2;
		initializeMap();
	}
	
	//GET FUNCTIONS
	public char getMapPosition(int x, int y) {
		return map[y][x];
	}
	
	public Character[] getCharacters() {
		return characters;
	}

	public int getLevel() {
		return level;
	}

	public int getMaxLevel() {
		return maxLevel;
	}
	
	
	
	//SET FUNCTIONS
	public void setMapPosition(int x, int y, char c) {
		map[y][x] = c;
	}

	public void setLevel(int newLvl) {
		level = newLvl;
		initializeMap();
	}
	
	public void setMaxLevel(int newMax) {
		maxLevel = newMax;
	}



	//COLLISIONS RELATED FUNCTIONS
	
	public boolean isWallColliding(Character c) {
		int X = c.getX();
		int Y = c.getY();

		if((getMapPosition(X, Y) == WALL) || (getMapPosition(X, Y) == DOOR))
			return true;
		else if((c.getSymbol() == OGRE) && (getMapPosition(X, Y) == LEVER))
			return true;
		return false;
	}
	
	public boolean isClubColliding(Character c) {
		int X = ((Ogre) c).getClub().getX();
		int Y = ((Ogre) c).getClub().getY();

		if((getMapPosition(X, Y) == WALL) || (getMapPosition(X, Y) == DOOR))
			return true;
		else if(getMapPosition(X, Y) == LEVER) {
			((Club) (((Ogre) c).getClub())).setAboveKey(true);
			return false;
		} else
			((Club) (((Ogre) c).getClub())).setAboveKey(false);
		return false;
	}

	public boolean isExitColliding(Character c) {
		int X = c.getX();
		int Y = c.getY();

		if(isExit(X, Y))
			return true;
		return false;
	}

	public int hasReachedLever(Character c) {
		int X = c.getX();
		int Y = c.getY();
		for (int i = 0; i < unlockers.length; i++)
		{
		  if (unlockers[i].hasReackedUnlocker(X, Y))
			  return i;
		}
		return -1;

	}

	//MAP INITIALIZATION FUNCTIONS
	
	public void initializeMap() {
		switch(level) {
		case 1:
			initializeLvlOne();
			
			break;
		case 2:
			initializeLvlTwo();
			
			break;
		}
	}

	public void initializeLvlOne() {

		characters = new Character[2]; //hero & guard
		characters[0] = new Hero(1,1);
		characters[1] = new Drunken(8,1);
		
		doors = new Door[2];
		doors[0] = new Door(0,5);
		doors[1] = new Door(0,6);
		
		unlockers = new Unlocker[1];
		unlockers[0] = new Unlocker(7,8, LEVER, true);

		for(int i = 1; i < map.length-1; ++i) {
			Arrays.fill(map[i], EMPTY);
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

		characters = new Character[2]; //hero & ogre
		characters[0] = new Hero(1,8);
		characters[1] = new Ogre(4,1);
		
		doors = new Door[1];
		doors[0] = new Door(0,1);
		
		unlockers = new Unlocker[1];
		unlockers[0] = new Unlocker(8,1, LEVER, false);

		for(int i = 1; i < map.length-1; ++i) {
			Arrays.fill(map[i], EMPTY);
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
		map[1][3] = CLUB;

		//define lever position LEVER
		map[1][8] = LEVER;
	}
	
	//MAP MANAGEMENTS FUNCTIONS
	
	public void getToNextLevel() {
		++level;
		initializeMap();
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
		int position = hasReachedLever(c);
		if(position != -1) {
			((Hero) c).setObjectColliding(true);
			//if it is not a lever - is a key
			if(!(unlockers[position].isLever())) {
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
		int X = ((Ogre) c).getClub().getX();
		int Y = ((Ogre) c).getClub().getY();
		
		//reput lever on position
		if(((Ogre) c).getClub().getSymbol() == KEYCLUB)
			map[Y][X] = LEVER;
		else
			map[Y][X] = EMPTY;
		
		char ogreCommand;
		char clubCommand;
		boolean collision;
		do {
			collision = false;
			ogreCommand = ((Ogre) c).getNextMove();
			clubCommand = ((Ogre) c).getNextMove();
			((Ogre) c).updatePosition(ogreCommand, clubCommand);
			if(isWallColliding(c) || isClubColliding(c)) {
				c.setToPreviousPosition();
				((Ogre) c).getClub().setToPreviousPosition();
				collision = true;
			}
		} while(collision);
		
		if(((Club) (((Ogre) c).getClub())).isAboveKey()) {
			((Ogre) c).getClub().setSymbol(KEYCLUB);
		} else
			((Ogre) c).getClub().setSymbol(CLUB);
		
		X = ((Ogre) c).getClub().getX();
		Y = ((Ogre) c).getClub().getY();
		map[Y][X] = ((Ogre) c).getClub().getSymbol();
	}

	public void updateMap(char heroCommand) {
		for(int i = 0; i < characters.length; ++i) {
			char symbol = characters[i].getSymbol();
			int X = characters[i].getX();
			int Y = characters[i].getY();
			setMapPosition(X, Y, EMPTY);

			switch(symbol) {
			case HERO: case KEYHERO:
				characters[i].updatePosition(heroCommand);
				updateHeroMapObjects(characters[i]);
				((Hero) characters[i]).updateHero();
				break;
			case GUARD: case SLEEPINGGUARD:
				char guardCommand = ((Guard) characters[i]).updateGuard();
				characters[i].updatePosition(guardCommand);
				if(((Guard) characters[i]).isCaptured((Hero) characters[0]))
					((Hero) characters[0]).setCaptured(true);
				break;
			case OGRE:
				updateOgrePosition(characters[i]);
				if(((Ogre) characters[i]).isCaptured((Hero) characters[0]))
					((Hero) characters[0]).setCaptured(true);
				if(((Ogre) characters[i]).isHit((Hero) characters[0]))
					((Hero) characters[0]).setFatality(true);
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
		for(int j = 0; j < doors.length; j++) {
			setMapPosition(doors[j].getX(), doors[j].getY(), STAIRS);
		}
	}

	public boolean isExit(int x, int y) {
		for(int j = 0; j < doors.length; j++) {
			if((x == doors[j].getX()) && (y == doors[j].getY()))
				return true;
		}
		return false;
	}

	//MAP PRINTING
	
	public void printMap() {
		int size = map.length;

		for(int i = 0; i < size; ++i) {
			System.out.println(map[i]);
		}

	}

	
	
	
}
