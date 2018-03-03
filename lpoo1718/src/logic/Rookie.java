package logic;

public class Rookie extends Guard{
	
	public Rookie(int x, int y, boolean move)
	{
		super(x, y, move);
	}
	
	public Rookie(int x, int y)
	{
		this(x, y, true);
	}

}
