package logic;

public class Club extends Character {
	private boolean aboveKey;
	
	//CONSTRUCTORS
	
	public Club(int newX, int newY) {
		super(newX, newY, '*');
		setAboveKey(false);
	}

	//GET FUNCTIONS
	
	public boolean isAboveKey() {
		return aboveKey;
	}

	//SET FUNCTIONS
	
	public void setAboveKey(boolean aboveKey) {
		this.aboveKey = aboveKey;
	}
	
	
}
