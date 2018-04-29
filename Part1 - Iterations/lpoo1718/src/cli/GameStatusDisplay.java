package cli;

/**
 * This class contains all the information regarding a {@link Game} status
 * that will be displayed in a user friendly way.
 * 
 * @author João Vieira
 * @author Susana Lima
 * 
 * @see Game
 */
public class GameStatusDisplay {

	/**
	 * Displays the information regarding a {@link Guard} being stepped on by an {@link Hero}.
	 * 
	 * @param show parameter that specifies if the information will be displayed or not
	 */
	public void guardAwoken(boolean show)
	{
		if(show)
			System.out.println("If you step the guard like that, how do you expect to escape?");
	}

	/**
	 * Displays the informations regarding an {@link Hero} being captured.
	 * 
	 * @param show parameter that specifies if the information will be displayed or not
	 */
	public void captured(boolean show)
	{
		if(show)
			System.out.println("You got captured, better luck next time!");
	}

	/**
	 * Displays the information regarding an {@link Hero} being fatally injured.
	 * 
	 * @param show parameter that specifies if the information will be displayed or not
	 */
	public void fatality(boolean show)
	{
		if(show)
			System.out.println("You got deadly hit, better luck next time!");
	}

	/**
	 * Displays the information when the {@link Hero} transitioning from the game's first to second level.
	 * 
	 * @param show parameter that specifies if the information will be displayed or not
	 */
	public void nextLevel(boolean show)
	{
		if(show)
			System.out.println("Your challenge is not over...");
	}

	/**
	 * Displays the information when the {@link Hero} wins the game.
	 * 
	 * @param show parameter that specifies if the information will be displayed or not
	 */
	public void gameWon(boolean show)
	{
		if(show)
			System.out.println("Congratz, you did it!");
	}
}
