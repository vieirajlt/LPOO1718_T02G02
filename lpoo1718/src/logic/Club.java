package logic;

/**
 * @author João Vieira
 * @author Susana Lima
 */
public class Club extends Weapon {
	/**
	 * 
	 */
	private static final long serialVersionUID = -483781192116984874L;
	private boolean aboveKey ;
	
	/*******************CONSTRUCTORS*******************/
	
	/**
	 * Creates a Club in preferred x and y values. Is possible to choose
	 * if the Club is static relatively to the owner.
	 * 
	 * @param newX
	 * @param newY
	 * @param newMove
	 */
	public Club(int newX, int newY, boolean newMove) {
		super(newX, newY, '*',newMove);
		setAboveKey(false);
	}
	
	/**
	 * Creates a movable Club in preferred position.
	 * 
	 * @param newX
	 * @param newY
	 */
	public Club(int newX, int newY) {
		this(newX,newY,true);
	}

	/*******************GET FUNCTIONS*******************/
	
	/**
	 * Retrieve the value of this Club aboveKey.
	 * 
	 * @return this Club aboveKey
	 */
	public boolean isAboveKey() {
		return aboveKey;
	}

	/*******************SET FUNCTIONS*******************/
	
	/**
	 * Set the value of this Club aboveKey.
	 * 
	 * @param aboveKey
	 */
	public void setAboveKey(boolean aboveKey) {
		this.aboveKey = aboveKey;
	}
		
}
