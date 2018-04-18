package logic;
import java.util.Scanner;

/**
 * This class contains the game cycle of the console based
 * game. It is responsible for printing the current {@link Game}
 * map and managing player input for game status update, from
 * the console.
 * 
 * @author João Vieira
 * @author Susana Lima
 * @see Weapon
 */
public class ConsoleController {

	/**
	 * This function initializes a predefined Game with two levels
	 * that are playable.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Game game = new Game();

		Scanner scanCommand = new Scanner(System.in);
		char command = 0;
		
		game.getCurrentMap().displayMap();
		
		//game cycle
		while(!(game.isEndGame())){
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
				game.setEndGame(true);
				break;
			}
			
			game.updateGame(command);
			game.getCurrentMap().displayMap();
		}
		
		System.out.println("Closing...");
		
		scanCommand.close();
	}
	
}
