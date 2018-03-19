package logic;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import cli.MapStatusDisplay;

public class Map {
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

	private char map[][]; //[Y][X]
	private ArrayList<Door> doors;	
	private ArrayList<Character> characters;
	private LinkedList<Ogre> ogres;
	private ArrayList<Unlocker> unlockers;
	private int level;
	private int maxLevel;
	private int ogreNumber;
	private char guardPersonality;
	
	private boolean showCli;
	static private MapStatusDisplay display = new MapStatusDisplay();

	/*******************CONSTRUCTORS*******************/

	public Map(int size, int lvl) {
		map = new char[size][size];
		level = lvl;
		maxLevel = 2;
		characters = new ArrayList<Character>(); //hero & guard
		ogres = new LinkedList<Ogre>();
		doors = new ArrayList<Door>();
		unlockers = new ArrayList<Unlocker>();
		showCli = true;
		ogreNumber = 2; //by default the number of ogres is 2
		guardPersonality = 'r'; //by default the guard's personality is rookie
		initializeMap();
	}

	public Map(int size) {
		this(size,1);
	}
	
	public Map(int size, int ogreNumber, char guardPersonality)
	{
		map = new char[size][size];
		level = 1;
		maxLevel = 2;
		characters = new ArrayList<Character>(); //hero & guard
		ogres = new LinkedList<Ogre>();
		doors = new ArrayList<Door>();
		unlockers = new ArrayList<Unlocker>();
		showCli = true;
		this.ogreNumber = ogreNumber;
		this.guardPersonality = guardPersonality;
		initializeMap();
	}

	public Map(char[][] newMap,boolean isLever) {
		int size = newMap.length;
		map = new char[size][size];
		level = 1; 
		maxLevel = 1;
		characters = new ArrayList<Character>(); 
		ogres = new LinkedList<Ogre>();
		doors = new ArrayList<Door>();
		unlockers = new ArrayList<Unlocker>();
		showCli = true;
		ogreNumber = 2; //by default the number of ogres is 2
		guardPersonality = 'r'; //by default the guard's personality is rookie
		initializeMap(newMap,isLever);
	}

	public Map(char[][] newMap) {
		this(newMap,true);
	}
	
	public Map(int width, int height, boolean isDefaultMap)
	{
		map = new char[height][width];
		level = 1; 
		maxLevel = 1;
		characters = new ArrayList<Character>(); 
		ogres = new LinkedList<Ogre>();
		doors = new ArrayList<Door>();
		unlockers = new ArrayList<Unlocker>();
		showCli = true;
		ogreNumber = 2; //by default the number of ogres is 2
		guardPersonality = 'r'; //by default the guard's personality is rookie
		if (isDefaultMap)
			setDefaultMap();
		else
			initializeMap();
		
	}

		
	private void setDefaultMap() {
		for(int i = 1; i < map.length-1; ++i) {
			Arrays.fill(map[i], EMPTY);
		}	
		for(int i = 1; i < map.length-1; ++i) {
			map[i][0] = WALL;
			map[i][map[0].length-1] = WALL;
		}
		Arrays.fill(map[0], WALL);
		Arrays.fill(map[map.length-1], WALL);
	}

	/*******************MAP INITIALIZATION FUNCTIONS*******************/

	public void initializeMap() {
		switch(level) {
		case 1:
			initializeLvlOne();
			break;
		case 2:
			initializeLvlTwo();
			break;
		default:
			break;
		}
	}
	
	public void initializeMap(char [][] newMap, boolean isLever) {
		this.map = newMap;
		characters.add(new Hero(1,1));
		for(int i = 0; i < map.length; i++)
		{
			for (int j= 0;j < map[i].length; j++ )
			{
				switch(map[i][j])
				{
				case HERO :
					characters.get(0).setPosition(j, i);
					break;
				case DOOR:
					doors.add(new Door(j,i));
					break;
				case LEVER:
					unlockers.add(new Unlocker(j,i,isLever));
					break;
				case GUARD:
					characters.add(new Guard(j,i, false));
					break;
				case OGRE:
					ogres.add(new Ogre(j,i,false,false));
					placeClub(ogres.getLast(), newMap);
					break;
				default:
					break;
				}
			}
		}
	}
	
	
	public void initializeMap(char [][] newMap) {
		this.map = newMap;
		characters.clear();
		ogres.clear();
		doors.clear();
		unlockers.clear();
		characters.add(new Hero(1,1));
		for(int i = 0; i < map.length; i++)
		{
			for (int j = 0;j < map[i].length; j++ )
			{
				switch(map[i][j])
				{
				case HERO :
					characters.get(0).setPosition(j, i);
					break;
				case ARMEDHERO:
					characters.get(0).setPosition(j, i);
					characters.get(0).setHasWeapon(true);
					break;
				case DOOR:
					doors.add(new Door(j,i));
					break;
				case LEVER:
					unlockers.add(new Unlocker(j,i,false));
					break;
				case GUARD:
					characters.add(new Guard(j,i, true));
					break;
				case OGRE:
					ogres.add(new Ogre(j,i,true,true));
					placeClub(ogres.getLast(), newMap);
					break;
				default:
					break;
				}
			}
		}
	}

	public void placeClub(Ogre o, char[][] map) {
		int x = o.getX();
		int y = o.getY();

		int wX = x, wY = y;

		if(map[y+1][x] == CLUB)
			++wY;
		else if(map[y-1][x] == CLUB)
			--wY;
		else if(map[y][x+1] == CLUB)
			++wX;
		else if(map[y][x-1] == CLUB)
			--wX;

		o.getWeapon().setPosition(wX, wY);
		//ogres.getLast().getWeapon().setPosition(wX, wY);
	}

	public void initializeLvlOne() {

		characters.add(new Hero(1,1));
		switch(guardPersonality)
		{
		case 's' : case  'S':
			characters.add(new Suspicious(8,1));
			break;
		case 'd' : case  'D':
			characters.add(new Drunken(8,1));
			break;
		case 'r' : case  'R':
			characters.add(new Rookie(8,1));
			break;
		default:
			characters.add(new Rookie(8,1));
			break;
		}
		
		doors.add(new Door(0,5));
		doors.add(new Door(0,6));

		unlockers.add(new Unlocker(7,8, LEVER, true));

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

		characters.clear();
		characters.add(new Hero(1,8,true));
		
		System.out.println(ogreNumber);

		//all the ogres start at the same position
		for (int i = 0; i < ogreNumber; i++)
			ogres.add(new Ogre(4,1));
		
		System.out.println(ogres.size());

		doors.clear();
		doors.add(new Door(0,1));

		unlockers.clear();
		unlockers.add(new Unlocker(8,1, LEVER, false));

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

		//define hero position HERO with CLUB
		map[8][1] = ARMEDHERO;

		//define ogre position OGRE
		map[1][4] = OGRE;
		map[1][3] = CLUB;

		//define lever position LEVER
		map[1][8] = LEVER;
	}
	

	public boolean searchCharacter(char c)
	{
		for (int i  = 0; i < map.length; i++ )
		{
			if (new String(map[i]).indexOf(c) != -1)
				return true;
		}
		return false;
	}
	
	public boolean searchHero()
	{
		return (searchCharacter(HERO) || searchCharacter(ARMEDHERO));
	}
	
	public boolean searchOgre()
	{
		return searchCharacter(OGRE);
	}
	
	public boolean searchClub()
	{
		return searchCharacter(CLUB);
	}
	
	public boolean searchKey()
	{
		return searchCharacter(LEVER);
	}
	
	public boolean searchDoor()
	{
		return searchCharacter(DOOR);
	}

	//a level is considered valid if it has 1 hero, at least 1 key and door and at least 1 ogre
	public boolean validateMapScheme()
	{
		boolean hasHero = searchHero();
		boolean hasKey = searchKey();
		boolean hasDoor = searchDoor();
		boolean hasOgre = searchOgre();
		return (hasDoor && hasKey && hasHero && hasOgre);
	}
	
	/*******************GET FUNCTIONS*******************/
	
	public char getMapPosition(int x, int y) {
		return map[y][x];
	}

	public ArrayList<Character> getCharacters() {
		return characters;
	}

	public int getLevel() {
		return level;
	}

	public int getMaxLevel() {
		return maxLevel;
	}

	public ArrayList<Unlocker> getUnlockers() {
		return unlockers;
	}

	public void setUnlockers(ArrayList<Unlocker> unlockers) {
		this.unlockers = unlockers;
	}
	
	public char[][] getMapScheme() {
		return map;
	}
	
	public boolean isShowCli() {
		return showCli;
	}

	public Character getGuard() {
		return characters.get(1);
	}
	
	public LinkedList<Ogre> getOgres() {
		return ogres;
	}
	
	public ArrayList<Door> getDoors() {
		return doors;
	}
	
	/*******************SET FUNCTIONS*******************/
	
	public void setGuardType(char type) {
		if(this.level != 1)
			return;
		int x = characters.get(1).getX();
		int y = characters.get(1).getY();
		characters.remove(1);

		switch(type) {
		case 'd': case 'D':
			characters.add(new Drunken(x, y, false));
			break;
		case 'r': case 'R':
			characters.add(new Rookie(x, y, false));
			break;
		case 's': case 'S':
			characters.add(new Suspicious(x, y, false));
			break;
		default:
			characters.add(new Guard(x, y));
			break;
		}
	}

	public void setMapPosition(int x, int y, char c) {
		map[y][x] = c;
	}
	
	public void setAndVerifyMapPosition(int x, int y, char c)
	{
		if(getMapPosition(x, y) == WALL && c != DOOR && c != WALL)
			return;
		else
			setMapPosition(x,y,c);
	}
	
	public boolean checkCorners(int x, int y)
	{
		if ( x== 0 && y == 0) return true;
		if (x == 0 && y == map.length-1) return true;
		if ( x== map[0].length-1 && y == 0) return true;
		if ( x== map[0].length-1 && y == map.length-1) return true;
		return false;
		
	}

	public void setLevel(int newLvl) {
		level = newLvl;
		initializeMap();
	}

	public void setMaxLevel(int newMax) {
		maxLevel = newMax;
	}

	public void setShowCli(boolean showCli) {
		this.showCli = showCli;
		
		for(Character c : characters) {
			c.setShowCli(showCli);
		}

		for(Ogre o : ogres) {
			o.setShowCli(showCli);
		}
	}

	public void setMapScheme(char[][] map) {
		this.map = map;
	}
	
	public void setOgres(LinkedList<Ogre> ogres) {
		this.ogres = ogres;
	}
	
	public void setDoors(ArrayList<Door> doors) {
		this.doors = doors;
	}
	
	
	public void setHeroPosition(int x, int y)
	{
		setMapPosition(x,y,HERO);
	}
	
	public void setWallPosition(int x, int y)
	{
		setMapPosition(x,y,WALL);
	}
	
	public void setDoorPosition(int x, int y)
	{
		setMapPosition(x,y,DOOR);
		
	}
	
	public void setOgrePosition(int x, int y)
	{
		setMapPosition(x,y,OGRE);
	}
	
	public void setKeyPosition(int x, int y)
	{
		setMapPosition(x,y,LEVER);
	}
	
	
	public void setHerotoArmedHero() {
		for (int i  = 0; i < map.length; i++ )
		{
			for(int j = 0; j < map[i].length;j++)
			{
				if(map[i][j] == HERO)
					map[i][j] = ARMEDHERO;
			}
		}
		
	}
	/*******************COLLISIONS RELATED FUNCTIONS*******************/

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
		int X = c.getWeapon().getX();
		int Y = c.getWeapon().getY();

		if((getMapPosition(X, Y) == WALL) || (getMapPosition(X, Y) == DOOR))
			return true;
		else if(getMapPosition(X, Y) == LEVER) {
			((Club) c.getWeapon()).setAboveKey(true);
			return false;
		} else
			((Club) c.getWeapon()).setAboveKey(false);
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
		for (int i = 0; i < unlockers.size(); i++)
		{
			if (unlockers.get(i).hasReackedUnlocker(X, Y) && this.getMapPosition(X, Y) == LEVER)
				return i;
		}
		return -1;

	}

	/*******************MAP MANAGEMENTS FUNCTIONS*******************/

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
			if(!(unlockers.get(position).isLever())) {
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
		int X = c.getWeapon().getX();
		int Y = c.getWeapon().getY();

		//reput lever on position
		if(c.getWeapon().getSymbol() == KEYCLUB)
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
				c.getWeapon().setToPreviousPosition();
				collision = true;
			}
		} while(collision);

		if(((Club) c.getWeapon()).isAboveKey()) {
			c.getWeapon().setSymbol(KEYCLUB);
		} else
			c.getWeapon().setSymbol(CLUB);

		X = c.getPrevX();
		Y = c.getPrevY();

	}

	public void updateMap(char heroCommand) {
		for(int i = 0; i < characters.size(); ++i) {
			char symbol = characters.get(i).getSymbol();

			switch(symbol) {
			case HERO: case KEYHERO: case ARMEDHERO:
				characters.get(i).updatePosition(heroCommand);
				updateHeroMapObjects(characters.get(i));
				((Hero) characters.get(i)).updateHero();
				break;
			case GUARD: case SLEEPINGGUARD:
				char guardCommand = ((Guard) characters.get(i)).updateGuard();
				characters.get(i).updatePosition(guardCommand);
				if(((Guard) characters.get(i)).isCaptured((Hero) characters.get(0)))
					((Hero) characters.get(0)).setCaptured(true);
				break;			
			default:
				break;
			}

			removeCharacter(characters.get(i));
		}

		//updates the ogres position
		for(int i = 0; i < ogres.size(); i++)
		{
			updateOgrePosition(ogres.get(i));

			//if hero gets on club range, game ends || if hero is captured
			if(ogres.get(i).getWeapon().isHit(characters.get(0)) || ogres.get(i).isCaptured(characters.get(0)))
				((Hero) characters.get(0)).setFatality(true);
			//if hero as weapon, ogre can get stunned
			else if(characters.get(0).hasWeapon() && ((Hero) characters.get(0)).getWeapon().isHit(ogres.get(i))) {
				((Ogre)ogres.get(i)).setStunned(true);
				ogres.get(i).setSymbol(STUNNEDOGRE);
				characters.get(0).setToPreviousPosition();
			} 
			//else hero gets cought
			else if(ogres.get(i).isCaptured((Hero) characters.get(0)))
				((Hero) characters.get(0)).setCaptured(true);

			removeCharacter(ogres.get(i));
		}
	}

	public void updateMapDisplay() {
		//updates in the ogres positions in the map
		for (int i = 0; i < ogres.size(); i++)
			placeCharacter(ogres.get(i));

		//update other characters positions in the map
		for(int i = characters.size(); i > 0; --i) {
			int x = characters.get(i-1).getX();
			int y = characters.get(i-1).getY();
			char s = characters.get(i-1).getSymbol();
			//Prioritizing Club to Ogre Symbol
			if(getMapPosition(x, y) == CLUB)
				s = CLUB;
			placeCharacter(characters.get(i-1), s);
		}
	}

	public void placeCharacter(Character c) {
		placeCharacter(c, c.getSymbol());
	}

	public void placeCharacter(Character c, char s) {
		int X;
		int Y;
		char symbol;

		if(c.hasWeapon() && c.getWeapon().isVisible()) {
			//Set new position
			X = c.getWeapon().getX();
			Y = c.getWeapon().getY();
			symbol = c.getWeapon().getSymbol();
			setMapPosition(X, Y, symbol);
		}

		//Set new Character position
		X = c.getX();
		Y = c.getY();
		symbol = s;
		setMapPosition(X, Y, symbol);
	}

	public void removeCharacter(Character c) {
		//Clear previous Character position
		int X = c.getPrevX();
		int Y = c.getPrevY();
		if(X != - 1 && Y != -1)
			setMapPosition(X, Y, EMPTY);

		if(c.hasWeapon() && c.getWeapon().isVisible()) {
			//Clear previous Weapon position
			X = c.getWeapon().getPrevX();
			Y = c.getWeapon().getPrevY();
			setMapPosition(X, Y, EMPTY);
		}
	}

	public void openExit() {
		for(int j = 0; j < doors.size(); j++) {
			setMapPosition(doors.get(j).getX(), doors.get(j).getY(), STAIRS);
		}
	}

	public boolean isExit(int x, int y) {
		for(int j = 0; j < doors.size(); j++) {
			if((x == doors.get(j).getX()) && (y == doors.get(j).getY()))
				return true;
		}
		return false;
	}
	
	/*******************DISPLAY*******************/
	
	public void displayMap() {
		display.printMap(map, showCli);
	}
	
	/*******************TO STRING*******************/
	
	public String toString()
	{
		String res = "";
		for (int i = 0 ; i < getMapScheme().length; i++)
		{
			res += String.valueOf(getMapScheme()[i]) + "\n";
		}
		return res;
	}

	

}
