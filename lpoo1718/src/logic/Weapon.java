package logic;

public class Weapon {

	private int X;
	private int Y;
	private int prevX;
	private int prevY;
	private char symbol;
	private boolean move;

	private boolean visible;

	/*******************CONSTRUCTORS*******************/

	public Weapon(int newX, int newY, char newSymbol, boolean newMove) {
		X = newX;
		Y = newY;
		symbol = newSymbol;
		prevX = -1;
		prevY = -1;
		visible = true;
		move = newMove;
	}

	public Weapon(int newX, int newY, char newSymbol) {
		this(newX,newY,newSymbol,true);
	}

	/*******************GET FUNCTIONS*******************/

	public boolean isMove() {
		return move;
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

	public boolean isVisible() {
		return visible;
	}

	/*******************SET FUNCTIONS*******************/

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

	public void setMove(boolean move) {
		this.move = move;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/*******************UPDATES MANAGEMENT*******************/

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
		if(!move)
		{
			setToPreviousPosition();
			return;
		}
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

	public char reverseCommand(char command) {
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

	public boolean isHit(Character c) {
		//if distance to weapon is equal or inferior to 1, get fatality
		if((Math.abs(c.getY()-this.getY()) + Math.abs(c.getX()-this.getX())) <= 1) {
			return true;
		}
		return false;
	}

}
