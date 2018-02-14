import java.util.Arrays;
import java.util.Scanner;

public class guidedProject {

	public static void initializeMap(char[][] map) {

		for(int i = 1; i < map.length-1; ++i) {
			Arrays.fill(map[i], ' ');
		}

		//define wall position 'X'
		Arrays.fill(map[0], 'X');
		Arrays.fill(map[9], 'X');

		int size = map.length;

		for(int i = 1; i < size-1; ++i) {
			map[i][0] = 'X';
			map[i][9] = 'X';
		}

		map[1][0] = 'X';
		map[1][6] = 'X';
		map[2][1] = 'X';
		map[2][2] = 'X';
		map[2][4] = 'X';
		map[2][5] = 'X';
		map[2][6] = 'X';
		map[3][6] = 'X';
		map[4][1] = 'X';
		map[4][2] = 'X';
		map[4][4] = 'X';
		map[4][5] = 'X';
		map[4][6] = 'X';
		map[7][1] = 'X';
		map[7][2] = 'X';
		map[7][4] = 'X';
		map[7][5] = 'X';
		map[7][6] = 'X';
		map[7][7] = 'X';
		map[8][6] = 'X';


		//define door position 'I'
		map[1][4] = 'I';
		map[3][2] = 'I';
		map[3][4] = 'I';
		map[5][0] = 'I';
		map[6][0] = 'I';
		map[8][2] = 'I';
		map[8][4] = 'I';

		//define hero position 'H'
		map[1][1] = 'H';

		//define guard position 'G'
		map[1][8] = 'G';

		//define lever position 'K'
		map[8][7] = 'k';
	}

	public static void printMap(char[][] map) {
		int size = map.length;

		for(int i = 0; i < size; ++i) {
			System.out.println(map[i]);
		}

	}

	public static boolean isColliding(char[][] map, short[] heroPosition) {
		short heroY = heroPosition[0];
		short heroX = heroPosition[1];
		if((map[heroY][heroX] == 'X') || (map[heroY][heroX] == 'I'))
			return true;
		return false;
	}
	
	public static void updateHeroPosition(char[][] map, short[] heroPosition, char command) {

		switch(command) {
		case 'u': case 'U':
			++heroPosition[0];
			if(isColliding(map, heroPosition))
				--heroPosition[0];
			else {
				map[heroPosition[0]-1][heroPosition[1]] = ' ';
				map[heroPosition[0]][heroPosition[1]] = 'H';
			}
			break;
		case 'd': case 'D':
			--heroPosition[0];
			if(isColliding(map, heroPosition))
				++heroPosition[0];
			else {
				map[heroPosition[0]+1][heroPosition[1]] = ' ';
				map[heroPosition[0]][heroPosition[1]] = 'H';
			}
			break;
		case 'l': case 'L':
			--heroPosition[1];
			if(isColliding(map, heroPosition))
				++heroPosition[1];
			else {
				map[heroPosition[0]][heroPosition[1]+1] = ' ';
				map[heroPosition[0]][heroPosition[1]] = 'H';
			}
			break;
		case 'r': case 'R':
			++heroPosition[1];
			if(isColliding(map, heroPosition))
				--heroPosition[1];
			else {
				map[heroPosition[0]][heroPosition[1]-1] = ' ';
				map[heroPosition[0]][heroPosition[1]] = 'H';
			}
			break;
		default:
			System.out.println("Wrong input\n");
			break;
		}
			
	}

	public static void main(String[] args) {
		int size = 10;
		char[][] map = new char[size][size];

		initializeMap(map);
		short[] heroPosition = new short[2];
		heroPosition[0] = 1; //Y
		heroPosition[1] = 1; //X

		Scanner scan_command = new Scanner(System.in);
		char command = 0;
		
		while(command != '0') {
			printMap(map);
			
			System.out.println("Where do you wanna go? (u/d/l/r)");
			
			try {
				//Regular Expression for 1 character
				command = scan_command.next(".").charAt(0);
			}
			catch (java.util.InputMismatchException e) {
				command = 'E';
				System.out.println("Exception");
			}
			
			updateHeroPosition(map, heroPosition, command);

		}
		
		System.out.println("Closing...");
		
		scan_command.close();
	}

}
