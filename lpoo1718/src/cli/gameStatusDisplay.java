package cli;

public class gameStatusDisplay {

	public void guardAwoken()
	{
		System.out.println("If you step the guard like that, how do you expect to escape?");
	}
	
	public void captured()
	{
		System.out.println("You got captured, better luck next time!");
	}
	
	public void fatality()
	{
		System.out.println("You got deadly hit, better luck next time!");
	}
	
	public void nextLevel()
	{
		System.out.println("Your challenge is not over...");
	}
	
	public void gameWon()
	{
		System.out.println("Congratz, you did it!");
	}
}
