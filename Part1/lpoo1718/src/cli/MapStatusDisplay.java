package cli;


/**
 * This class displays in a user friendly way an array of chars representing 
 * the game and its objects current positions.
 * 
 * @author João Vieira
 * @author Susana Lima
 * 
 * @see Map
 */
public class MapStatusDisplay {

	/**
	 * Displays an array of chars representing the game and its objects current positions.
	 * 
	 * @param map array of chars containing the information regarding the game's objects current positions
	 * @param show parameter that specifies if the information will be displayed or not
	 */
	public void printMap(char[][] map, boolean show) {
		if(!show)
			return;
		
		int size = map.length;

		for(int i = 0; i < size; ++i) {
			System.out.println(map[i]);
		}

	}
	
}
