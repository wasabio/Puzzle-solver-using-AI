package miniProject1.heuristics;

import miniProject1.grids.Grid;
import miniProject1.utilities.Carre;

public class WrongNeighbours {
	/* 
	 * Lowest value is the best
	 * This heuristic will sum the number of wrong neighbours (not considering diagonal neighbours)
	 *  of each tile 
	 */
	public static int h(Carre[] n) {
		int val = 0;
		
		for(int i = 0; i < n.length; i++) {
			int number = n[i].getNumber();
			
			if(number != 0) {
				if(Grid.isUpPossible(i)) {
					int up = n[i-4].getNumber();
					if(up != (number-4) && up != 0) {
						val++;
					}
				}
				if(Grid.isRightPossible(i)) {
					int right = n[i+1].getNumber();
					if(right != (number+1) && right != 0) {
						val++;
					}
				}
				if(Grid.isDownPossible(i)) {
					int down = n[i+4].getNumber();
					if(down != (number+4) && down != 0) {
						val++;
					}
				}
				if(Grid.isLeftPossible(i)) {
					int left = n[i-1].getNumber();
					if(left != (number-1) && left != 0) {
						val++;
					}
				}
			}
		}
		return val;
	}
}
