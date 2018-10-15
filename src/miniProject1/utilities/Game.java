package miniProject1.utilities;

import miniProject1.grids.AStarGrid;
import miniProject1.grids.BFSGrid;
import miniProject1.grids.DFSGrid;
import miniProject1.grids.Grid;
import miniProject1.heuristics.WrongNeighbours;

public class Game {
	
	/*
	 * This class is the single point of entry of the program.
	 * It gets the user inputs, create the initial grid and launch the algorithms.
	 */
	public static void main(String[] args) {
		Grid.generateInitialState();
		
		System.out.println("Initial Grid");
		Grid.print();
		DFS();
		BFS();
		AStar();
	}
	
	/* 
	 * Algorithm Deep First Search is executed with a cutoff
	 */
	public static void DFS() {
		int cutoff = 4;
		Grid puzzleDFS = new DFSGrid(cutoff);
		
		System.out.print("Executing DFS with cutoff " + cutoff + "...");
		TxtWriter.Generate("results/puzzleDFSCutoff.txt", puzzleDFS.execute(null));
		puzzleDFS.clear(); //Cutoff is set to -1 : No cutoff
		
		System.out.print("Executing DFS...");
		TxtWriter.Generate("results/puzzleDFS.txt", puzzleDFS.execute(null));
		
		System.out.println();
	}
	
	/* 
	 * Algorithm Best First Search is executed with all the heuristics
	 */
	public static void BFS() {
		Grid puzzleBFS = new BFSGrid();
		
		System.out.print("Executing BFS with Wrong Neighbours ... ");
		TxtWriter.Generate("results/puzzleBFS-h1.txt", puzzleBFS.execute("h1"));
		puzzleBFS.clear();
		
		System.out.print("Executing BFS with Manhattan distance (diagonals) ... ");
		TxtWriter.Generate("results/puzzleBFS-h2.txt", puzzleBFS.execute("h2"));
		puzzleBFS.clear();
		
		System.out.print("Executing BFS with Hamming distance ... ");
		TxtWriter.Generate("results/puzzleBFS-h3.txt", puzzleBFS.execute("h3"));
		puzzleBFS.clear();
		
		System.out.print("Executing BFS with Manhattan distance ... ");
		TxtWriter.Generate("results/puzzleBFS-h4.txt", puzzleBFS.execute("h4"));
		puzzleBFS.clear();
		
		System.out.print("Executing BFS with Sum of permutation inversions ... ");
		TxtWriter.Generate("results/puzzleBFS-h5.txt", puzzleBFS.execute("h5"));
		
		System.out.println();
	}
	
	/* 
	 * Algorithm A* is executed with all the heuristics
	 */
	public static void AStar() {
		Grid puzzleAs = new AStarGrid();
		
		System.out.print("Executing A* with Wrong Neighbours ...");
		TxtWriter.Generate("results/puzzleAs-h1.txt", puzzleAs.execute("h1"));
		puzzleAs.clear();
		
		System.out.print("Executing A* with Manhattan distance (diagonals) ...");
		TxtWriter.Generate("results/puzzleAs-h2.txt", puzzleAs.execute("h2"));
		puzzleAs.clear();
		
		System.out.print("Executing A* with Hamming distance ...");
		TxtWriter.Generate("results/puzzleAs-h3.txt", puzzleAs.execute("h3"));
		puzzleAs.clear();
		
		System.out.print("Executing A* with Manhattan distance ...");
		TxtWriter.Generate("results/puzzleAs-h4.txt", puzzleAs.execute("h4"));
		puzzleAs.clear();
		
		System.out.print("Executing A* with Sum of permutation inversions ...");
		TxtWriter.Generate("results/puzzleAs-h5.txt", puzzleAs.execute("h5"));
		
		System.out.println();
	}
	
	

}
