package miniProject1;


public class Game {
		
	public static void main(String[] args) {
		Grid.generateInitialState();
		
		System.out.println("Initial Grid");
		Grid.print();
		
		/*System.out.print("Executing DFS ...");
		Grid puzzleDFS = new DFSGrid();
		TxtWriter.Generate("puzzleDFS.txt", puzzleDFS.execute(null));
		System.out.println(" DONE");*/
		
		System.out.print("Executing BFS with Hamming distance ...");
		Grid puzzleBFS = new BFSGrid();
		TxtWriter.Generate("puzzleBFS-h1.txt", puzzleBFS.execute("h1"));
		System.out.println(" DONE");
		puzzleBFS.clear();
		
		/*System.out.print("Executing A* with Manhattan distance ...");
		TxtWriter.Generate("puzzleBFS-h2.txt", puzzleBFS.execute("h2"));
		System.out.println(" DONE");*/
		
		System.out.print("Executing BFS with Sum of permutation inversions ...");
		Grid puzzleAs = new AStarGrid();
		TxtWriter.Generate("puzzleAs-h1.txt", puzzleAs.execute("h1"));
		System.out.println(" DONE");
		puzzleAs.clear();
		
		System.out.print("Executing A* with Sum of permutation inversions ...");
		TxtWriter.Generate("puzzleAs-h2.txt", puzzleAs.execute("h2"));
		System.out.println(" DONE");
	}
	
}
