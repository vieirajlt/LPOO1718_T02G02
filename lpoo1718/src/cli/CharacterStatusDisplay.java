package cli;


/**
 * This class contains  the information regarding a Character input status 
 * that will be displayed in a user friendly way.
 * 
 * @author João Vieira
 * @author Susana Lima
 * 
 * @see Character
 */
public class CharacterStatusDisplay {
	
	/**
	 * Displays the information when a there is an invalid input.
	 * 
	 * @param show parameter that specifies if the information will be displayed or not
	 */
	public void wrongCommandInput(boolean show)
	{
		if(show)
			System.out.println("Wrong input\n");
	}
}
