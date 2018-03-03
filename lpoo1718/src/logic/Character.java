package logic;


public class Character {
	private int X;
	private int Y;
	private int prevX;
	private int prevY;
	private char symbol;

	private boolean hasWeapon;
	private Weapon weapon;
	private boolean showCli;

	//CONSTRUCTORS

	public Character(int newX, int newY, char newSymbol, boolean hasWeapon) {
		X = newX;
		Y = newY;
		symbol = newSymbol;
		prevX = -1;
		prevY = -1;
		this.hasWeapon = hasWeapon;
		setShowCli(true);
	}

	//SET FUNCTIONS
	public void setShowCli(boolean showCli) {
		this.showCli = showCli;
	}

	public void setSymbol(char newSymbol) {
		symbol = newSymbol;
	}

	public void setX(int newX) {
		prevX = X;
		X = newX;
	}

	public void setY(int newY) {
		prevY = Y;
		Y = newY;
	}

	public void setPosition(int newX, int newY) {
		setX(newX);
		setY(newY);
	}

	public void setHasWeapon(boolean hasWeapon) {
		this.hasWeapon = hasWeapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	//GET FUNCTIONS
	public boolean isShowCli() {
		return showCli;
	}

	public int getX() {
		return X;
	}

	public int getY() {
		return Y;
	}

	public int getPrevX() {
		return prevX;
	}

	public int getPrevY() {
		return prevY;
	}

	public char getSymbol() {
		return symbol;
	}

	public boolean hasWeapon() {
		return hasWeapon;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	//POSITION COMMANDS

	public void moveUp() {
		prevY = Y;
		prevX = X;
		--Y;
	}

	public void moveDown() {
		prevY = Y;
		prevX = X;
		++Y;
	}

	public void moveLeft() {
		prevY = Y;
		prevX = X;
		--X;
	}

	public void moveRight() {
		prevY = Y;
		prevX = X;
		++X;
	}

	public void updatePosition(char command) {
		switch(command) {
		case 'u': case 'U':
			moveUp();
			break;
		case 'd': case 'D':
			moveDown();
			break;
		case 'l': case 'L':
			moveLeft();
			break;
		case 'r': case 'R':
			moveRight();
			break;
		case 'E':
			break;
		default:
			System.out.println("Wrong input\n");
			return;
		}
	}

	public void setToPreviousPosition() {
		X = prevX;
		Y = prevY;
	}

	protected char reverseCommand(char command) {
		switch(command)
		{
		case 'u': case 'U':
			return 'd';
		case 'd': case 'D':
			return 'u';
		case 'l': case 'L':
			return 'r';
		case 'r': case 'R':
			return 'l';
		default :
			return 'E';
		}
	}



	public boolean isCaptured(Character c) {
		//if c has weapon, he will stun this, instead of being captured
		if(!c.hasWeapon())
			//if distance to this is equal or inferior to 1, get captured
			if((Math.abs(c.getY()-this.getY()) + Math.abs(c.getX()-this.getX())) <= 1) {
				return true;
			}
		return false;
	}
}

