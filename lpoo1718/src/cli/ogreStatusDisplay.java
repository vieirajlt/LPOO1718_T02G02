package cli;

public class ogreStatusDisplay {

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

	public void justStunned(boolean show)
	{
		if(show)
			System.out.println("Nice hit! You just stunned an Ogre!");
	}

}
