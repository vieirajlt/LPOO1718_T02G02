package cli;

public class MapStatusDisplay {

	public void printMap(char[][] map, boolean show) {
		if(!show)
			return;
		
		int size = map.length;

		for(int i = 0; i < size; ++i) {
			System.out.println(map[i]);
		}

	}
	
}
