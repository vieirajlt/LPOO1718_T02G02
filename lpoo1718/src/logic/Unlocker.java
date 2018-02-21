package logic;

public class Unlocker {

	private int x;
	private int y;
	private char symbol;
	private boolean isLever;
	
	public Unlocker(int x, int y, char symbol, boolean isLever)
	{
		this.x = x;
		this.y = y;
		this.symbol = symbol;
		this.isLever = isLever;
	}
	
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public char getSymbol() {
		return symbol;
	}
	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
	
	public boolean isLever() {
		return isLever;
	}
	
	public boolean hasReackedUnlocker(int x, int y)
	{
		return (this.x == x && this.y == y);
	}
	
	
}
