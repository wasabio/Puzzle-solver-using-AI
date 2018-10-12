package miniProject1.heuristics;

import miniProject1.grids.Grid;
import miniProject1.utilities.Carre;

public class ManhattanDistance {

	/* A BETTER heuristic / Lowest value is the best */
	/* Manhattan distance :  sum up all the distances by which tiles are out of place */
	public static int h(Carre[] n) {
		int val = 0;

		for(int i = 0; i < n.length; i++) {
			if(n[i].getNumber() != 0 && n[i].getNumber() != Grid.goalBoard[i].getNumber()) {
				for(int goalIndex = 0; goalIndex < n.length; goalIndex++) {
					if(Grid.goalBoard[goalIndex].getNumber() == n[i].getNumber()) {
						int x = (i%4);
						int y = (i/4);
						int xGoal = (goalIndex%4);
						int yGoal = (goalIndex/4);
						val = (val + Math.abs(x - xGoal) + Math.abs(y - yGoal));//Math.max( Math.abs(x - xGoal), Math.abs(y - yGoal) );	//Max between difference of X and Y positions to allow diagonal moves

						break;
					}
				}
			}
		}
		
		return val;
	}
}
