package miniProject1.heuristics;

import miniProject1.utilities.Carre;

public class Permutations {

	/* The BEST heuristic / Lowest value is the best */
	/* sum of permutation inversions */
	public static int h(Carre[] n) {
		int val = 0;

		for(int i = 0; i < (n.length-1); i++) {
			if(n[i].getNumber() != 0) {
				for(int rightNumber = (i+1); rightNumber < n.length; rightNumber++) {
					if(n[i].getNumber() > n[rightNumber].getNumber() && n[rightNumber].getNumber() != 0) {
						val++;
					}
				}
			}
		}
		return val;
	}
}
