package miniProject1.heuristics;

import miniProject1.grids.Grid;
import miniProject1.utilities.Carre;

public class HammingDistance {

	/* The SIMPLEST heuristic / Lowest value is the best */
	/* Hamming distance : count number of tiles out of place when compared with goal */
	public static int h(Carre[] n) {
		int val = 0;
		
		for(int i = 0; i < n.length; i++) {
			if(n[i].getNumber() != Grid.goalBoard[i].getNumber() && n[i].getNumber() != 0) {
				val++;
			}
		}
		return val;
	}
}
