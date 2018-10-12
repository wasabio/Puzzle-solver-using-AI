package miniProject1.utilities;

import miniProject1.grids.AStarGrid;
import miniProject1.grids.BFSGrid;
import miniProject1.grids.DFSGrid;
import miniProject1.grids.Grid;

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
	 * */
	public static void DFS() {
		Grid puzzleDFS = new DFSGrid();
		
		System.out.print("Executing DFS ...");
		TxtWriter.Generate("puzzleDFS.txt", puzzleDFS.execute(null));
		System.out.println(" DONE");
	}
	
	/* 
	 * Algorithm Best First Search is executed with all the heuristics
	 * */
	public static void BFS() {
		Grid puzzleBFS = new BFSGrid();
		
		System.out.print("Executing BFS with Hamming distance ...");
		TxtWriter.Generate("puzzleBFS-h1.txt", puzzleBFS.execute("h1"));
		System.out.println(" DONE");
		puzzleBFS.clear();
		
		System.out.print("Executing BFS with Manhattan distance ...");
		TxtWriter.Generate("puzzleBFS-h2.txt", puzzleBFS.execute("h2"));
		System.out.println(" DONE");
		puzzleBFS.clear();
		
		System.out.print("Executing BFS with Sum of permutation inversions ...");
		TxtWriter.Generate("puzzleBFS-h3.txt", puzzleBFS.execute("h3"));
		System.out.println(" DONE");
	}
	
	/* 
	 * Algorithm A* is executed with all the heuristics
	 * */
	public static void AStar() {
		Grid puzzleAs = new AStarGrid();
		
		System.out.print("Executing A* with Hamming distance ...");
		TxtWriter.Generate("puzzleAs-h1.txt", puzzleAs.execute("h1"));
		System.out.println(" DONE");
		puzzleAs.clear();
		
		System.out.print("Executing A* with Manhattan distance ...");
		TxtWriter.Generate("puzzleAs-h2.txt", puzzleAs.execute("h2"));
		System.out.println(" DONE");
		puzzleAs.clear();
		
		System.out.print("Executing A* with Sum of permutation inversions ...");
		TxtWriter.Generate("puzzleAs-h3.txt", puzzleAs.execute("h3"));
		System.out.println(" DONE");
	}

}
