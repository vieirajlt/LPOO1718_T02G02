package logic;
import java.util.Scanner;

import cli.gameStatusDisplay;

public class guidedProject {
	private static boolean endGame = false;
	private static gameStatusDisplay display = new gameStatusDisplay();
	
	public static void main(String[] args) {
		//initialize map
		int mapSize = 10;
		Map map = new Map(mapSize, 2);

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
		if(((Hero) map.getCharacters()[0]).hasSteppedGuard()) {
			endGame = true;
			display.guardAwoken();
		} else if(((Hero) map.getCharacters()[0]).getCaptured()) {
			display.captured();
			endGame = true;
		} else if(((Hero) map.getCharacters()[0]).getFatality()) {
			display.fatality();
			endGame = true;
		} else if(((Hero) map.getCharacters()[0]).getEscaped()) {
			if(map.getLevel() != map.getMaxLevel()) {
				display.nextLevel();
				map.getToNextLevel();
			} else {
				display.gameWon();
				endGame = true;
			}
		}
	}

}
