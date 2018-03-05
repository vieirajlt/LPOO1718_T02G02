package logic;
import java.util.Scanner;

public class GuidedProject {

	public static void main(String[] args) {
		//initialize game
		Game game = new Game();
		

		Scanner scanCommand = new Scanner(System.in);
		char command = 0;
		
		game.getMap().displayMap();
		
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
			game.getMap().displayMap();
		}
		
		System.out.println("Closing...");
		
		scanCommand.close();
	}
	
}
