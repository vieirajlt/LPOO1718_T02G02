package logic;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import cli.MapStatusDisplay;

public class Map implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3751192253702198623L;
	/**
	 * Predefine char association for representation of different types of objects/characters
	 * on this Map.
	 */
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
	
	/**
	 * Predefined level indicator designation.
	 */
	private static final int LVL1 = 1;
	private static final int LVL2 = 2;
	private static final int NEWLVL = 0;
	
	/**
	 * Predefine value for ogreNumber.
	 */
	private static final int DEFAULT_OGRECOUNT = 2;
	
	/**
	 * Predefine value for guardPersonality.
	 */
	private static final GuardPersonality DEFAULT_GUARDPERSONALITY = GuardPersonality.ROOKIE;
	
	/**
	 * Predefined standard level size (width and height).
	 */
	private static final int LVLSIZE = 10;

	/**
	 * map is a 2D array that stores all objects/characters positioning information
	 * on the current {@link Game} cycle.
	 */
	private char map[][]; //[Y][X]
	/**
	 * level is an indicator to the type of level that is loaded.
	 */
	private int level;
	/**
	 * doors is an {@link ArrayList} of {@link Door} that stores all possible exits
	 * for level completion.
	 */
	private ArrayList<Door> doors;
	/**
	 * characters is an {@link ArrayList} of {@link Character} that stores
	 * the {@link Hero} and {@link Guard} loaded on this Map.
	 */
	private ArrayList<Character> characters;
	/**
	 * ogres is a {@link LinkedList} of {@link logic.Ogre} that stores all
	 * Ogres loaded on this Map.
	 */
	private LinkedList<Ogre> ogres;
	/**
	 * unlockers is an {@link ArrayList} of {@link logic.Unlocker} that stores all
	 * loaded keys/levers on this Map.
	 */
	private ArrayList<Unlocker> unlockers;
	
	/**
	 * ogreNumber is a helping variable used to initialize levels with {@link logic.Ogre}
	 */
	private int ogreNumber;
	/**
	 * guardPersonality is a helping variable used to initialize levels with {@link Guard}
	 */
	private GuardPersonality guardPersonality;
	
	/**
	 * showCli is the flag indicating if display should be used.
	 */
	private boolean showCli;
	
	/**
	 * display is the user friendly information representing this Map status.
	 */
	static private MapStatusDisplay display = new MapStatusDisplay();
	
	//TODO
	private ArrayList<String> log;

	/*******************CONSTRUCTORS*******************/

	/**
	 * Creates a Map with predefined size, using default Guard and
	 * default number of ogres.
	 * 
	 */
	public Map() {
		map = new char[LVLSIZE][LVLSIZE];
		characters = new ArrayList<Character>(); //hero & guard
		ogres = new LinkedList<Ogre>();
		doors = new ArrayList<Door>();
		unlockers = new ArrayList<Unlocker>();
		showCli = true;
		ogreNumber = DEFAULT_OGRECOUNT;
		guardPersonality = DEFAULT_GUARDPERSONALITY;
		log = null;
	}
	
	/**
	 * Creates a default Map initialized on a specific level.
	 * 
	 * @param lvl the specified level
	 */
	public Map(int lvl) {
		this();
		initializeMap(lvl);
	}
	
	/**
	 * Creates a default Map initialized on a specific level with
	 * given guardPersonality Guard.
	 * 
	 * @param guardPersonality the new value of guardPersonality
	 * @param level the selected level
	 */
	public Map(GuardPersonality guardPersonality, int level)
	{
		this();
		if(level != LVL1)
			return;
		this.guardPersonality = guardPersonality;
		this.ogreNumber = 0;
		ogres = new LinkedList<Ogre>();
		initializeMap(LVL1);
	}
	
	/**
	 * Creates a default Map initialized on a specific level with
	 * given ogreNumber number of ogres.
	 * 
	 * @param ogreNumber the new value of ogreNumber
	 * @param level the selected level
	 */
	public Map(int ogreNumber, int level)
	{
		this();
		if(level != LVL2)
			return;
		this.ogreNumber = ogreNumber;
		initializeMap(LVL2);
	}

	/**
	 * Creates a custom Map using newMap as reference for initial objects/characters
	 * positioning. Additional information for enemies movability, and {@link logic.Unlocker}
	 * is provided by the other parameters.
	 * 
	 * @param newMap the reference array for map creation
	 * @param movableChar the flag for non-Hero {@link Character} ability to move
	 * @param movableWeapon the flag for non-Hero {@link Character} ability to move their {@link Weapon}
	 * @param isLever the flag for {@link logic.Unlocker} indication
	 */
	public Map(char[][] newMap, boolean movableChar, boolean movableWeapon,boolean isLever) {
		this();
		int size = newMap.length;
		map = new char[size][size];
		ogreNumber = 2; //by default the number of ogres is 2
		guardPersonality = GuardPersonality.ROOKIE; //by default the guard's personality is rookie
		initializeMap(newMap, movableChar, movableWeapon,isLever);
	}
	
	/**
	 * Creates a empty custom Map with specified size.
	 * 
	 * @param width the width of this Map map
	 * @param height the height of this Map map
	 * @param isDefaultMap the flag for option verification
	 */
	public Map(int width, int height, boolean isDefaultMap)
	{
		this();
		map = new char[height][width];
		ogreNumber = 2; //by default the number of ogres is 2
		guardPersonality = GuardPersonality.ROOKIE; //by default the guard's personality is rookie
		if (isDefaultMap)
			setDefaultMap();
		else
			initializeMap(LVL1);
		
	}

	/**
	 * Sets this Map map to an empty map only surrounded by Walls. 	
	 */
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

	/**
	 * Selects which level will be loaded to this Map map.
	 * 
	 * @param level the level to initialize
	 */
	public void initializeMap(int level) {
		switch(level) {
		case LVL1:
			initializeLvlOne();
			this.level = LVL1;
			break;
		case LVL2:
			initializeLvlTwo();
			this.level = LVL2;
			break;
		default:
			break;
		}
	}
	
	/**
	 * Extracts info from newMap for objects/characters positioning and the other variables for 
	 * {@link Character} specifications in order to define this Map map as a new custom
	 * level.
	 * 
	 * @param newMap the reference array for map creation
	 * @param movableChar the flag for non-Hero {@link Character} ability to move
	 * @param movableWeapon the flag for non-Hero {@link Character} ability to move their {@link Weapon}
	 * @param isLever the flag for {@link logic.Unlocker} indication
	 */
	public void initializeMap(char [][] newMap, boolean movableChar, boolean movableWeapon, boolean isLever) {
		this.map = newMap;
		characters.add(new Hero(1,1));
		this.level = NEWLVL;
		for(int i = 0; i < map.length; i++)
		{
			for (int j= 0;j < map[i].length; j++ )
			{
				initializeMapComponents(newMap, movableChar, movableWeapon, isLever, i, j);
			}
		}
	}

	/**
	 * Initializes the objects/characters specified on newMap[y][x] accordingly to the other 
	 * parameters, setting this Map to accordingly.
	 * 
	 * @param newMap the reference array for map creation
	 * @param movableChar the flag for non-Hero {@link Character} ability to move
	 * @param movableWeapon the flag for non-Hero {@link Character} ability to move their {@link Weapon}
	 * @param isLever the flag for {@link logic.Unlocker} indication
	 * @param x position on this Map map[]
	 * @param y position on this Map map
	 */
	private void initializeMapComponents(char[][] newMap, boolean movableChar, boolean movableWeapon, boolean isLever,
			int y, int x) {
		switch(map[y][x])
		{
		case HERO :
			characters.get(0).setPosition(x, y);
			break;
		case DOOR:
			doors.add(new Door(x,y));
			break;
		case LEVER:
			unlockers.add(new Unlocker(x,y,isLever));
			break;
		case GUARD:
			if(movableChar)
				characters.add(new Rookie(x,y));
			else
				characters.add(new TestRookie(x,y));
			break;
		case OGRE:
			if(movableChar)
				ogres.add(new Ogre(x,y,movableWeapon));
			else
				ogres.add(new TestOgre(x,y,movableWeapon));
			placeClub(ogres.getLast(), newMap);
			break;
		default:
			break;
		}
	}

	/**
	 * Takes map has reference to search for an available positions for
	 * the {@link logic.Ogre} o's {@link Weapon}.
	 * 
	 * @param o the {@link logic.Ogre} in analysis
	 * @param map the reference for placement check
	 */
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
	}

	
	/**
	 * Calls functions to initialize predefine LVL1.
	 */
	public void initializeLvlOne() {
		initializeLvl1OneObjects();
		initializeLvlOneMapScheme();
	}

	/**
	 * Initializes this Map map accordingly with predefined LVL1.
	 */
	private void initializeLvlOneMapScheme() {
		setDefaultMap();
		initializeLvlOneMapWalls();
		//define door position DOOR
		initializeLvlOneMapDoors();
		//define hero position HERO
		map[1][1] = HERO;
		//define guard position GUARD
		map[1][8] = GUARD;
		//define lever position LEVER
		map[8][7] = LEVER;
	}

	/**
	 * Initializes this Map map doors accordingly with predefined LVL1.
	 */
	private void initializeLvlOneMapDoors() {
		map[1][4] = DOOR;
		map[3][2] = DOOR;
		map[3][4] = DOOR;
		map[5][0] = DOOR;
		map[6][0] = DOOR;
		map[8][2] = DOOR;
		map[8][4] = DOOR;
	}

	/**
	 * Initializes this Map map walls accordingly with predefined LVL1.
	 */
	private void initializeLvlOneMapWalls() {
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
	}

	/**
	 * Initializes this Map objects/characters with predefined LVL1 information.
	 */
	private void initializeLvl1OneObjects() {
		characters.add(new Hero(1,1));
		initializeGuard();
		doors.add(new Door(0,5));
		doors.add(new Door(0,6));
		unlockers.add(new Unlocker(7,8, LEVER, true));
	}

	/**
	 * Initializes this Map Guard accordingly to guardPersonality information.
	 */
	private void initializeGuard() {
		if(guardPersonality == GuardPersonality.SUSPICIOUS) {
			characters.add(new Suspicious(8,1));
		} else if(guardPersonality == GuardPersonality.DRUNKEN) {
			characters.add(new Drunken(8,1));
		} else if(guardPersonality == GuardPersonality.ROOKIE) {
			characters.add(new Rookie(8,1));
		}
	}
	
	/**
	 * Calls functions to initialize predefine LVL2.
	 */	
	public void initializeLvlTwo() {

		initializeLvlTwoObjects();
		initializeLvlTwoMapScheme();
	}

	/**
	 * Initializes this Map map accordingly with predefined LVL2.
	 */
	private void initializeLvlTwoMapScheme() {
		setDefaultMap();

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

	/**
	 * Initializes this Map objects/characters with predefined LVL2 information.
	 */
	private void initializeLvlTwoObjects() {
		characters.clear();
		characters.add(new Hero(1,8,true));
		ogres.clear();
		//all the ogres start at the same position
		for (int i = 0; i < ogreNumber; i++)
			ogres.add(new Ogre(4,1));
		doors.clear();
		doors.add(new Door(0,1));
		unlockers.clear();
		unlockers.add(new Unlocker(8,1, LEVER, false));
	}
	

	/**
	 * Searches for a {@link Character} symbol on this Map map.
	 * 
	 * @param c the Character to look for
	 * @return true if Character is represented on this Map map, else false
	 */
	public boolean searchCharacter(char c)
	{
		for (int i  = 0; i < map.length; i++ )
		{
			if (new String(map[i]).indexOf(c) != -1)
				return true;
		}
		return false;
	}
	
	/**
	 * Searches for a {@link Hero} symbol on this Map map.
	 * 
	 * @return if Hero is represented on this Map map, else false
	 */
	public boolean searchHero()
	{
		return (searchCharacter(HERO) || searchCharacter(ARMEDHERO));
	}
	
	/**
	 * Searches for a {@link logic.Ogre} symbol on this Map map.
	 * 
	 * @return if Ogre is represented on this Map map, else false
	 */
	public boolean searchOgre()
	{
		return searchCharacter(OGRE);
	}
	
	/**
	 * Searches for a {@link Club} symbol on this Map map.
	 * 
	 * @return if Club is represented on this Map map, else false
	 */
	public boolean searchClub()
	{
		return searchCharacter(CLUB);
	}
	
	/**
	 * Searches for a {@link logic.Unlocker} symbol on this Map map.
	 * 
	 * @return if Unlocker is represented on this Map map, else false
	 */
	public boolean searchKey()
	{
		return searchCharacter(LEVER);
	}
	
	/**
	 * Searches for a {@link Door} symbol on this Map map.
	 * 
	 * @return if Door is represented on this Map map, else false
	 */
	public boolean searchDoor()
	{
		return searchCharacter(DOOR);
	}

	/**
	 * Validates if if this Map map has all necessary parameters represented.
	 * 
	 * @return true if parameters represented, else false
	 */
	public boolean validateMapScheme()
	{
		boolean hasHero = searchHero();
		boolean hasKey = searchKey();
		boolean hasDoor = searchDoor();
		boolean hasOgre = searchOgre();
		return (hasDoor && hasKey && hasHero && hasOgre);
	}
	
	/*******************GET FUNCTIONS*******************/
	
	/**
	 * Retrieve the value of this Map map[y][x].
	 * 
	 * @param x the location on this Map map[]
	 * @param y the location on this Map map
	 * @return the char stored on this Map map
	 */
	public char getMapPosition(int x, int y) {
		return map[y][x];
	}
	
	/**
	 * Retrieve the value of this Map characters.
	 * 
	 * @return this Map characters 
	 */
	public ArrayList<Character> getCharacters() {
		return characters;
	}

	/**
	 * Retrieve the value of this Map level.
	 * 
	 * @return this Map level 
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Retrieve the value of this Map unlockers.
	 * 
	 * @return this Map unlockers 
	 */
	public ArrayList<Unlocker> getUnlockers() {
		return unlockers;
	}

	/**
	 * Retrieve the value of this Map map.
	 * 
	 * @return this Map map 
	 */
	public char[][] getMapScheme() {
		return map;
	}
	
	/**
	 * Retrieve the value of this Map showCli.
	 * 
	 * @return this Map showCli 
	 */
	public boolean isShowCli() {
		return showCli;
	}

	/**
	 * Retrieve the value of this Map Guard.
	 * 
	 * @return this Map Guard 
	 */
	public Character getGuard() {
		return characters.get(1);
	}
	
	/**
	 * Retrieve the value of this Map ogres.
	 * 
	 * @return this Map ogres 
	 */
	public LinkedList<Ogre> getOgres() {
		return ogres;
	}
	
	/**
	 * Retrieve the value of this Map doors.
	 * 
	 * @return this Map doors 
	 */
	public ArrayList<Door> getDoors() {
		return doors;
	}
	
	/**
	 * Retrieve the value of this Map width.
	 * 
	 * @return this Map width 
	 */
	public int getMapWidth() {
		return map[0].length;
	}
	
	/**
	 * Retrieve the value of this Map height.
	 * 
	 * @return this Map height 
	 */
	public int getMapHeight() {
		return map.length;
	}
	
	/**
	 * Retrieve the value of this Map guardPersonality.
	 * 
	 * @return this Map guardPersonality 
	 */
	public GuardPersonality getGuardPersonality() {
		return guardPersonality;
	}

	/*******************SET FUNCTIONS*******************/
	
	/**
	 * Set the value of this Map unlockers.
	 * 
	 * @param unlock the new value of unlockers
	 */
	public void setUnlockers(ArrayList<Unlocker> unlock) {
		this.unlockers = unlock;
	}
	
	/**
	 * Set the value of this Map Guard.
	 * 
	 * @param gp the new value of this map guardPersonality
	 * @param move the flag allowing(or not) the Guard to move
	 */
	public void setGuardType(GuardPersonality gp, boolean move) {
		int x = -1;
		int y = -1;
		int index = -1;
		
		for(int i = 0; i < characters.size(); ++i) {
			if(characters.get(i).getSymbol() == GUARD) {
				x = characters.get(i).getX();
				y = characters.get(i).getY();
				index = i;
				break;
			}
		}
		
		if(index == -1)
			return;
		
		guardPersonality = gp;
		
		if(!move) {
			setImmovableGuardType(gp, x, y, index); 
		} else {
			setMovableGuardType(gp, x, y, index); 
		}
		
	}

	/**
	 * @param gp
	 * @param x
	 * @param y
	 * @param index
	 */
	private void setMovableGuardType(GuardPersonality gp, int x, int y, int index) {
		if(gp == GuardPersonality.SUSPICIOUS) {
			characters.set(index, new Suspicious(x,y));
		} else if(gp == GuardPersonality.DRUNKEN) {
			characters.set(index, new Drunken(x,y));
		} else if(gp == GuardPersonality.ROOKIE) {
			characters.set(index, new Rookie(x,y));
		}
	}

	/**
	 * @param gp
	 * @param x
	 * @param y
	 * @param index
	 */
	private void setImmovableGuardType(GuardPersonality gp, int x, int y, int index) {
		if(gp == GuardPersonality.SUSPICIOUS) {
			characters.set(index, new TestSuspicious(x,y));
		} else if(gp == GuardPersonality.DRUNKEN) {
			characters.set(index, new TestDrunken(x,y));
		} else if(gp == GuardPersonality.ROOKIE) {
			characters.set(index, new TestRookie(x,y));
		}
	}

	/**
	 * Set the value of this Map map[y][x].
	 * 
	 * @param x the location on this Map map[]
	 * @param y the location on this Map map
	 * @param c the new value of map[y][x]
	 */
	public void setMapPosition(int x, int y, char c) {
		map[y][x] = c;
	}
	
	/**
	 * Verify if this Map map[y][x] is allowed to be change
	 * 
	 * @param x the location on this Map map[]
	 * @param y the location on this Map map
	 * @param c the new value of map[y][x]
	 * @return true if is allowed to be change, else false
	 */
	public boolean verifyMapPosition(int x, int y, char c)
	{
		if(getMapPosition(x, y) == WALL && c != DOOR && c != WALL)
			return false;
		return true;
	}
	
	/**
	 * Checks if map[y][x] is a corner of the Map map
	 * 
	 * @param x the location on this Map map[]
	 * @param y the location on this Map map
	 * @return true is it is a corner, else false
	 */
	public boolean checkCorners(int x, int y)
	{
		if ( x== 0 && y == 0) return true;
		if (x == 0 && y == map.length-1) return true;
		if ( x== map[0].length-1 && y == 0) return true;
		if ( x== map[0].length-1 && y == map.length-1) return true;
		return false;
		
	}

	/**
	 * Initializes this Map with predefined selected level.
	 * 
	 * @param newLvl the selected level
	 */
	public void setLevel(int newLvl) {
		initializeMap(newLvl);
	}

	/**
	 * Set the value of this Map showCli and all its {@link Character} showCli.
	 * 
	 * @param showCli the new value of showCli
	 */
	public void setShowCli(boolean showCli) {
		this.showCli = showCli;
		
		for(Character c : characters) {
			c.setShowCli(showCli);
		}

		for(Ogre o : ogres) {
			o.setShowCli(showCli);
		}
	}
	
	/**
	 * Set the value of this Map map.
	 * 
	 * @param map the new value of map
	 */
	public void setMapScheme(char[][] map) {
		this.map = map;
	}
	
	/**
	 * Set the value of this Map ogres.
	 * 
	 * @param ogres the new value of ogres
	 */
	public void setOgres(LinkedList<Ogre> ogres) {
		this.ogres = ogres;
	}
	
	/**
	 * Set the value of this Map doors.
	 * 
	 * @param doors the new value of doors
	 */
	public void setDoors(ArrayList<Door> doors) {
		this.doors = doors;
	}
	
	/**
	 * Set the value of this Map map[y][x] to HERO
	 * 
	 * @param x the location on this Map map[]
	 * @param y the location on this Map map
	 */
	public void setHeroPosition(int x, int y)
	{
		setMapPosition(x,y,HERO);
	}
	
	/**
	 * Set the value of this Map map[y][x] to WALL
	 * 
	 * @param x the location on this Map map[]
	 * @param y the location on this Map map
	 */
	public void setWallPosition(int x, int y)
	{
		setMapPosition(x,y,WALL);
	}
	
	/**
	 * Set the value of this Map map[y][x] to DOOR
	 * 
	 * @param x the location on this Map map[]
	 * @param y the location on this Map map
	 */
	public void setDoorPosition(int x, int y)
	{
		setMapPosition(x,y,DOOR);
		
	}
	
	/**
	 * Set the value of this Map map[y][x] to OGRE
	 * 
	 * @param x the location on this Map map[]
	 * @param y the location on this Map map
	 */
	public void setOgrePosition(int x, int y)
	{
		setMapPosition(x,y,OGRE);
	}
	
	/**
	 * Set the value of this Map map[y][x] to LEVER
	 * 
	 * @param x the location on this Map map[]
	 * @param y the location on this Map map
	 */
	public void setKeyPosition(int x, int y)
	{
		setMapPosition(x,y,LEVER);
	}
	
	/**
	 * Set the value of this Map map where{@link Hero} is to ARMEDHERO.
	 */
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

	/**
	 * Checks if {@link Character} c current position is the same as an 
	 * impassable object.
	 * 
	 * @param c the Character to check position
	 * @return true if overlapping, else false
	 */
	public boolean isWallColliding(Character c) {
		int X = c.getX();
		int Y = c.getY();

		if((getMapPosition(X, Y) == WALL) || (getMapPosition(X, Y) == DOOR))
			return true;
		else if((c.getSymbol() == OGRE) && (getMapPosition(X, Y) == LEVER))
			return true;
		return false;
	}

	/**
	 * Checks if {@link Weapon} current position of {@link Character} c 
	 * is the same as an impassable object or a {@link logic.Unlocker}.
	 * 
	 * @param c the Character whose Weapon is checked
	 * @return true if overlapping, else false
	 */
	public boolean isWeaponColliding(Character c) {
		int X = c.getWeaponX();
		int Y = c.getWeaponY();

		if((getMapPosition(X, Y) == WALL) || (getMapPosition(X, Y) == DOOR))
			return true;
		else if(getMapPosition(X, Y) == LEVER) {
			c.setClubAboveKey(true);
			return false;
		} else
			c.setClubAboveKey(false);
		return false;
	}

	/**
	 * Checks if {@link Character} c current position is the same as a
	 * {@link Door}.
	 * 
	 * @param c the Character to check position
	 * @return true if overlapping, else false
	 */
	public boolean isExitColliding(Character c) {
		int X = c.getX();
		int Y = c.getY();

		if(isExit(X, Y))
			return true;
		return false;
	}

	/**
	 * Checks if {@link Character} c current position is the same as a
	 * {@link logic.Unlocker}.
	 * 
	 * @param c the Character to check position
	 * @return Unlocker position on this Map doors, else -1
	 */
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

	/*******************MAP MANAGEMENT FUNCTIONS*******************/


	/**
	 * Checks {@link Hero} c possible collisions and updates
	 * its status accordingly.
	 * 
	 * @param h the Hero to update
	 */
	public void updateHeroMapObjects(Hero h) {
		//Check if exit has been reached
		if(isExitColliding(h)) {
			updateHeroMapObjectsExitColliding(h);
		} 
		//Check general objects collisions
		else if(isWallColliding(h))
			((Hero) h).setWallColliding(true);
		//check if lever/key is reached
		int position = hasReachedLever(h);
		if(position != -1) {
			updateHeroMapObjectsLeverReached(h, position);
		}
	}

	/**
	 * Notifies {@link Hero} c that is colliding with a {@link Door}
	 * and updates this Map if needed.
	 * 
	 * @param h the Hero to update
	 */
	private void updateHeroMapObjectsExitColliding(Hero h) {
		h.setExitColliding(true);
		//if hero is carrying a key, open exit
		if(h.getSymbol() == KEYHERO) {
			openExit();
		}
	}
	
	/**
	 * Notifies {@link Hero} c that is colliding with a {@link logic.Unlocker}
	 * and updates this Map if needed.
	 * 
	 * @param h the Hero to update
	 * @param position
	 */
	private void updateHeroMapObjectsLeverReached(Hero h, int position) {
		h.setObjectColliding(true);
		//if it is not a lever - is a key
		if(!(unlockers.get(position).isLever())) {
			//make hero symbol a KEYHERO symbol
			h.setSymbol(KEYHERO);
		} 
		//if it is a lever
		else {
			openExit();
		}
	}
	
	/**
	 * Checks {@link Ogre} o possible collisions and updates
	 * its status accordingly.
	 * 
	 * @param o the Ogre to update
	 */
	public void updateOgrePosition(Ogre o) {
		if(!isMovePossible(o.getX(), o.getY())) 
			return;
		reputLeverOnPosition(o); 
		updateOgretoValidPosition(o);
		updateClubSymbol(o);
	}

	/**
	 * Checks if {@link Ogre} o is superimposing an {@link logic.Unlocker}
	 * updating its symbol accordingly.
	 * 
	 * @param o the Ogre to update
	 */
	private void updateClubSymbol(Ogre o) {
		if(((Club) o.getWeapon()).isAboveKey()) {
			o.getWeapon().setSymbol(KEYCLUB);
		} else
			o.getWeapon().setSymbol(CLUB);
	}

	/**
	 * Updates {@link Ogre} o to a new random valid position, checking for
	 * collisions and updating this Map accordingly.
	 * 
	 * @param o
	 */
	private void updateOgretoValidPosition(Ogre o) {
		char ogreCommand;
		char clubCommand;
		boolean collision;
		do {
			collision = false;
			ogreCommand = o.getNextMove();
			clubCommand = o.getNextMove();
			o.updatePosition(ogreCommand, clubCommand);
			if(isWallColliding(o) || isWeaponColliding(o)) {
				o.setToPreviousPosition();
				o.getWeapon().setToPreviousPosition();
				collision = true;
			}
		} while(collision);
	}

	/**
	 * Updates Map map resetting {@link Unlocker} symbol when {@link Character} c
	 * is moving its {@link Weapon} from {@link logic.Unlocker} position.
	 * 
	 * @param c the Character moving Weapon
	 */
	private void reputLeverOnPosition(Character c) {
		int X = c.getWeaponX();
		int Y = c.getWeaponY();
		if(c.getWeaponSymbol() == KEYCLUB)
			map[Y][X] = LEVER;
		else
			map[Y][X] = EMPTY;
	}

	/**
	 * Updates all {@link Character} on Map accordingly to the heroCommand
	 * given by the player.
	 * 
	 * @param heroCommand the char moving the Hero
	 */
	public void updateMap(char heroCommand) {
		//updates the hero and the guard positions
		updateMapHeroAndGuard(heroCommand);
		//updates the ogres position
		updateMapOgres();
	}

	
	/**
	 * Updates {@link Hero} and {@link Guard} positions accordingly  to 
	 * the heroCommand given by the player.
	 * 
	 * @param heroCommand the char moving the Hero
	 */
	private void updateMapHeroAndGuard(char heroCommand) {
		for(int i = 0; i < characters.size(); ++i) {
			char symbol = characters.get(i).getSymbol();
			
			switch(symbol) {
			case HERO: case KEYHERO: case ARMEDHERO:
				updateMapHero(heroCommand, i);
				break;
			case GUARD: case SLEEPINGGUARD:
				updateMapGuard(i);
				break;			
			default:
				break;
			}

			removeCharacter(characters.get(i));
		}
	}
	
	/**
	 * Updates the {@link Guard} on index i on this Map characters.
	 * 
	 * @param i the index of Guard
	 */
	private void updateMapGuard(int i) {
		char guardCommand = ((Guard) characters.get(i)).updateGuard();
		characters.get(i).updatePosition(guardCommand);
		if(((Guard) characters.get(i)).isCaptured((Hero) characters.get(0)))
			((Hero) characters.get(0)).setCaptured(true);
	}

	/**
	 * Updates {@link Hero} position accordingly  to the heroCommand given 
	 * by the player.
	 * 
	 * @param heroCommand the char moving the Hero
	 * @param i the index of Hero
	 */
	private void updateMapHero(char heroCommand, int i) {
		characters.get(i).updatePosition(heroCommand);
		updateHeroMapObjects((Hero)characters.get(i));
		((Hero) characters.get(i)).updateHero();
	}
	
	/**
	 * Updates all {@link Ogre} on Map ogres.
	 */
	private void updateMapOgres() {
		for(int i = 0; i < ogres.size(); i++)
		{
			updateOgrePosition(ogres.get(i));

			//if hero gets on club range, game ends || if hero is captured
			if(checkHeroFatality(i))
				((Hero) characters.get(0)).setFatality(true);
			//if hero as weapon, ogre can get stunned
			else if(checkOgreStunned(i)) {
				updateStunnedOgre(i);
			} 
			//else hero gets cought
			else if(checkHeroCaptured(i))
				((Hero) characters.get(0)).setCaptured(true);

			removeCharacter(ogres.get(i));
		}
	}

	/**
	 * Updates {@link Ogre} on index i on this Map ogres, when stunned.
	 * 
	 * @param i the index of the Ogre
	 */
	private void updateStunnedOgre(int i) {
		((Ogre)ogres.get(i)).setStunned(true);
		ogres.get(i).setSymbol(STUNNEDOGRE);
		characters.get(0).setToPreviousPosition();
	}

	/**
	 * Checks if {@link Hero} status indicates that was captured by an enemy.
	 * 
	 * @param i the index of the Hero on this Map characters
	 * @return true if captured, else false
	 */
	private boolean checkHeroCaptured(int i) {
		return ogres.get(i).isCaptured((Hero) characters.get(0));
	}

	/**
	 * Checks if {@link Ogre} was stunned by an {@link Hero}.
	 * 
	 * @param i the index of the Ogre on this Map ogres
	 * @return true if stunned, else false
	 */
	private boolean checkOgreStunned(int i) {
		return characters.get(0).hasWeapon() && ((Hero) characters.get(0)).getWeapon().isHit(ogres.get(i));
	}

	/**
	 * Checks if {@link Hero} was killed by an enemy.
	 * 
	 * @param i the index of the Hero on this Map characters
	 * @return true if killed, else false
	 */
	private boolean checkHeroFatality(int i) {
		return ogres.get(i).getWeapon().isHit(characters.get(0)) || ogres.get(i).isCaptured(characters.get(0));
	}

	/**
	 * Updates all Characters on this Map map.
	 */
	public void updateMapDisplay() {
		//updates in the ogres positions in the map
		for (int i = 0; i < ogres.size(); i++)
			placeCharacter(ogres.get(i));

		//update other characters positions in the map
		for(int i = characters.size(); i > 0; --i) {
			placeCharacter(characters.get(i-1));
		}
	}

	/**
	 * Places character on this Map map.
	 * 
	 * @param c the Character to be placed
	 */
	public void placeCharacter(Character c) {
		placeCharacter(c, c.getSymbol());
	}

	/**
	 * Places character on this Map map with symbol s
	 * 
	 * @param c the Character to be placed
	 * @param s the symbol to be set
	 */
	public void placeCharacter(Character c, char s) {
		int X;
		int Y;
		char symbol;

		if(c.hasWeapon() && c.getWeapon().isVisible()) {
			//Set new position
			X = c.getWeaponX();
			Y = c.getWeaponY();
			symbol = c.getWeaponSymbol();
			setMapPosition(X, Y, symbol);
		}

		//Set new Character position
		X = c.getX();
		Y = c.getY();
		symbol = s;
		setMapPosition(X, Y, symbol);
	}

	/**
	 * Removes character on this Map map.
	 * 
	 * @param c the Character to be removed
	 */
	public void removeCharacter(Character c) {
		//Clear previous Character position
		int X = c.getPrevX();
		int Y = c.getPrevY();
		if(X != - 1 && Y != -1)
		{
			setMapPosition(X, Y, EMPTY);
			if(c.hasWeapon() && c.getWeapon().isVisible() && getMapPosition(X, Y) != WALL) {
				//Clear previous Weapon position
				X = c.getWeaponPrevX();
				Y = c.getWeaponPrevY();
				setMapPosition(X, Y, EMPTY);
			}
		}

	}

	/**
	 * Places STAIRS symbol on every {@link Door} location on this Map map.
	 */
	public void openExit() {
		for(int j = 0; j < doors.size(); j++) {
			setMapPosition(doors.get(j).getX(), doors.get(j).getY(), STAIRS);
		}
	}

	/**
	 * Checks if this Map map[y][x] is the location of a {@link Door}.
	 * 
	 * @param x position on this Map map[]
	 * @param y position on this Map map
	 * @return true if position of Door, else false
	 */
	public boolean isExit(int x, int y) {
		for(int j = 0; j < doors.size(); j++) {
			if((x == doors.get(j).getX()) && (y == doors.get(j).getY()))
				return true;
		}
		return false;
	}
	
	/*******************DISPLAY*******************/
	
	/**
	 * Prints this Map map to console.
	 */
	public void displayMap() {
		display.printMap(map, showCli);
	}
	
	/*******************TO STRING*******************/
	
	/**
	 * Converts this Map map into a {@link String}
	 */
	public String toString()
	{
		String res = "";
		for (int i = 0 ; i < map.length; i++)
		{
			res += String.valueOf(map[i]) + "\n";
		}
		return res;
	}
	
	/**
	 * Checks if a {@link Character} placed on this Map map[y][x] can move in any direction.
	 * 
	  * @param x position on this Map map[]
	 * @param y position on this Map map
	 * @return true if movement is possible, else false
	 */
	boolean isMovePossible(int x, int y) {
		if(getMapPosition(x+1, y) != WALL) {
			return true;
		} else if(getMapPosition(x-1, y) != WALL) {
			return true;
		} else if(getMapPosition(x, y+1) != WALL) {
			return true;
		}  else if(getMapPosition(x, y-1) != WALL) {
			return true;
		}
		
		return false;	
	}

}
