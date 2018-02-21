package logic;

public class Character {
	private int X;
	private int Y;
	private int prevX;
	private int prevY;
	private char symbol;
	
	//CONSTRUCTORS
	
	public Character(int newX, int newY, char newSymbol) {
		X = newX;
		Y = newY;
		symbol = newSymbol;
		prevX = -1;
		prevY = -1;
	}

	//SET FUNCTIONS
	
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

	//GET FUNCTIONS
	
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
		int tmp = prevX;
		prevX = X;
		X = tmp;
		tmp = prevY;
		prevY = Y;
		Y = tmp;
	}
}
