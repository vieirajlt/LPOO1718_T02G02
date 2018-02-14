import java.util.Arrays;

public class Map {
	private char map[][];
	
	public Map(int size) {
		map = new char[size][size];
		initializeMap();
	}
	
	public void initializeMap() {

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

	public void printMap() {
		int size = map.length;

		for(int i = 0; i < size; ++i) {
			System.out.println(map[i]);
		}

	}

	public void setMapPosition(int x, int y, char c) {
		map[y][x] = c;
	}

	public char getMapPosition(int x, int y) {
		return map[y][x];
	}

	public void leverReached() {
		map[5][0] = 'S';
		map[6][0] = 'S';
	}

}
