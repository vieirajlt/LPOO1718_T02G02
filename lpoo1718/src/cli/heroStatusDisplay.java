package cli;

public class heroStatusDisplay {

	public void wallColliding(boolean show)
	{
		if(show)
			System.out.println("Ouch, try not to faceplant next time!");
	}

	public void keyColliding(boolean show)
	{
		if(show)
			System.out.println("Key aquired.");
	}

	public void doorColliding(boolean show)
	{
		if(show)
			System.out.println("Exit opened, time to escape!");
	}

	public void exitOpen(boolean show)
	{
		if(show)
			System.out.println("Exit opened, time to escape!");
	}
}
