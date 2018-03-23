package logic;

import java.io.Serializable;

public class Unlocker implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -932204565800543432L;
	private int x;
	private int y;
	private char symbol;
	private boolean isLever;
	
	/*******************CONSTRUCTORS*******************/
	
	public Unlocker(int x, int y, char symbol, boolean isLever)
	{
		this.x = x;
		this.y = y;
		this.symbol = symbol;
		this.isLever = isLever;
	}
	
	public Unlocker(int x, int y,boolean isLever)
	{
		this(x,y,'k',isLever);
	}
	
	
	/*******************GET FUNCTIONS*******************/
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public char getSymbol() {
		return symbol;
	}
	
	public boolean isLever() {
		return isLever;
	}
	
	/*******************SET FUNCTIONS*******************/
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
	
	/*******************UPDATES MANAGEMENT*******************/
	
	public boolean hasReackedUnlocker(int x, int y)
	{
		return (this.x == x && this.y == y);
	}
	
}
