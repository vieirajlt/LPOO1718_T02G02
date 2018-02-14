import java.util.Scanner;

public class guidedProject {

	public static void main(String[] args) {
		//initialize map
		int mapSize = 10;
		Map map = new Map(mapSize);

		//initialize hero
		int heroX = 1;
		int heroY = 1;
		Hero hero = new Hero(heroX, heroY);
		
		//initialize guard
		int guardX = 8;
		int guardY = 1;
		Guard guard = new Guard(guardX, guardY);

		Scanner scan_command = new Scanner(System.in);
		char command = 0;
		
		while(command != '0') {
			map.printMap();
			
			System.out.println("Where do you wanna go? (u/d/l/r)");
			
			try {
				//Regular Expression for 1 character
				command = scan_command.next(".").charAt(0);
			}
			catch (java.util.InputMismatchException e) {
				command = 'E';
				System.out.println("Exception");
			}
			
			hero.updateHeroPosition(map, command);
			
			if(guard.isCaptured(hero) || hero.escaped)
				break;
		}
		
		System.out.println("Closing...");
		
		scan_command.close();
	}

}
