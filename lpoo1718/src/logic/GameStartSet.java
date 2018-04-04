package logic;

import exception.InvalidBoardSizeException;
import exception.InvalidOgreCountException;

public class GameStartSet {

	private static final int maxOgresCount = 6;
	private static final int maxWidth = 15;
	private static final int maxHeigth = 15;
	
	private int ogresCount;
	private GuardPersonality guardPersonality;
	
	private int width;
	private int heigth;
	
	private boolean isLvlCreation;
	
	public GameStartSet(int w, int h) throws InvalidBoardSizeException {
		if(!isSizeValid(w, h))
			throw new InvalidBoardSizeException();
		
		setWidth(w);
		setHeigth(h);
		isLvlCreation = true;
	}
	
	public GameStartSet() throws InvalidOgreCountException {
		this(0, GuardPersonality.ROOKIE);
	}
	
	public GameStartSet(int oc, GuardPersonality gp) throws InvalidOgreCountException {

		if(!isOgreCountValid(oc))
			throw new InvalidOgreCountException();
		ogresCount = oc;
		guardPersonality = gp;
		isLvlCreation = false;

	}
	
	public Game startNewGame() {
		if(isLvlCreation) {
			return new Game();
		} else {
			return new Game(ogresCount, guardPersonality);
		}
	}
	
	public int getOgresCount() {
		return ogresCount;
	}
	public void setOgresCount(int ogresCount) {
		this.ogresCount = ogresCount;
	}
	public GuardPersonality getGuardPersonality() {
		return guardPersonality;
	}
	public void setGuardPersonality(GuardPersonality guardPersonality) {
		this.guardPersonality = guardPersonality;
	}
	
	public boolean isOgreCountValid(int oc) {
		return ((oc > 0) && (oc < maxOgresCount));
	}
	
	public boolean isSizeValid(int w, int h) {
		return ((w > 0) && (w < maxWidth) && (h > 0) && (h < maxHeigth));
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeigth() {
		return heigth;
	}

	public void setHeigth(int heigth) {
		this.heigth = heigth;
	}

	public boolean isLvlCreation() {
		return isLvlCreation;
	}

	public void setLvlCreation(boolean isLvlCreation) {
		this.isLvlCreation = isLvlCreation;
	}
	
}
