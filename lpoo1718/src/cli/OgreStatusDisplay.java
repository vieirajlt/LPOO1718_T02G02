package cli;

/**
 * This class contains all the information regarding an ogre status 
 * that will be displayed in a user friendly way.
 * 
 * @author Jo�o Vieira
 * @author Susana Lima
 * 
 * @see Ogre
 */
public class OgreStatusDisplay {

	/**
	 * Displays the information regarding the number of plays the ogre will be stunted for
	 * 
	 * @param t number of plays that the ogre will be stunted for
	 * @param show parameter that specifies if the information will be displayed or not
	 */
	public void stunned(int t, boolean show)
	{
		if(!show) 
			return;
		
		System.out.print("Ogre stunned for ");
		System.out.print(t);
		
		if(t == 1) 
			System.out.println(" more round.");
		else
			System.out.println(" more rounds.");

	}

	/**
	 * Displays the information regarding a hero stunning the ogre.
	 * 
	 * @param show parameter that specifies if the information will be displayed or not
	 */
	public void justStunned(boolean show)
	{
		if(show)
			System.out.println("Nice hit! You just stunned an Ogre!");
	}

}
