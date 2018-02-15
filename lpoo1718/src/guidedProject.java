import java.util.Scanner;

public class guidedProject {
	private static boolean endGame = false;
	
	public static void main(String[] args) {
		//initialize map
		int mapSize = 10;
		Map map = new Map(mapSize);

		Scanner scanCommand = new Scanner(System.in);
		char command = 0;
		
		map.printMap();
		
		//game cycle
		while(!endGame) {
			System.out.println("Where do you wanna go? (u/d/l/r)");
			
			try {
				//Regular Expression for 1 character
				command = scanCommand.next(".").charAt(0);
			}
			catch (java.util.InputMismatchException e) {
				command = 'E';
				scanCommand.nextLine();
				System.out.println("Exception");
			}
			
			if(command == '0') {
				endGame = true;
				break;
			}
			
			updateGame(map, command);
			map.printMap();
		}
		
		System.out.println("Closing...");
		
		scanCommand.close();
	}
	
	//GAME MANAGEMENT FUNCTION
	public static void updateGame(Map map, char command) {
		
		map.updateMap(command);
		
		if(((Hero) map.getCharacters()[0]).getCaptured()) {
			System.out.println("You got captured, better luck next time!");
			endGame = true;
		} else if(((Hero) map.getCharacters()[0]).getFatality()) {
			System.out.println("You got deadly hit, better luck next time!");
			endGame = true;
		} else if(((Hero) map.getCharacters()[0]).getEscaped()) {
			if(map.getLevel() != map.getMaxLevel()) {
				System.out.println("Your challenge is not over...");
				map.getToNextLevel();
			} else {
				System.out.println("Congratz, you did it!");
				endGame = true;
			}
		}
	}

}
