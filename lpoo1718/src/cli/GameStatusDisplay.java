package cli;

public class GameStatusDisplay {

	public void guardAwoken(boolean show)
	{
		if(show)
			System.out.println("If you step the guard like that, how do you expect to escape?");
	}

	public void captured(boolean show)
	{
		if(show)
			System.out.println("You got captured, better luck next time!");
	}

	public void fatality(boolean show)
	{
		if(show)
			System.out.println("You got deadly hit, better luck next time!");
	}

	public void nextLevel(boolean show)
	{
		if(show)
			System.out.println("Your challenge is not over...");
	}

	public void gameWon(boolean show)
	{
		if(show)
			System.out.println("Congratz, you did it!");
	}
}
