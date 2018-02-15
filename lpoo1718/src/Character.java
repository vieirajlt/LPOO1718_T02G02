
public class Character {
	private int X;
	private int Y;
	private int prevX;
	private int prevY;
	private char symbol;
	
	public Character(int newX, int newY, char newSymbol) {
		X = newX;
		Y = newY;
		symbol = newSymbol;
		prevX = -1;
		prevY = -1;
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
			moveRigth();
			break;
		default:
			System.out.println("Wrong input\n");
			return;
		}
	}
	
	public char getSymbol() {
		return symbol;
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
	
	public void moveRigth() {
		prevY = Y;
		prevX = X;
		++X;
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
