package logic;

public class Club extends Weapon {
	private boolean aboveKey ;
	
	/*******************CONSTRUCTORS*******************/
	
	public Club(int newX, int newY, boolean newMove) {
		super(newX, newY, '*',newMove);
		setAboveKey(false);
	}
	
	public Club(int newX, int newY) {
		this(newX,newY,true);
	}

	/*******************GET FUNCTIONS*******************/
	
	public boolean isAboveKey() {
		return aboveKey;
	}

	/*******************SET FUNCTIONS*******************/
	
	public void setAboveKey(boolean aboveKey) {
		this.aboveKey = aboveKey;
	}
		
}
