package miniProject1;


public class Game {
		
	public static void main(String[] args) {
		Grid.generateInitialState();
		
		
		/*Grid puzzleDFS = new DFSGrid();
		TxtWriter.Generate("puzzleDFS.txt", puzzleDFS.execute(null));*/
		
		
		Grid puzzleBFS = new BFSGrid();
		TxtWriter.Generate("puzzleBFS-h1.txt", puzzleBFS.execute("h1"));
		puzzleBFS.clear();
		TxtWriter.Generate("puzzleBFS-h2.txt", puzzleBFS.execute("h2"));
		System.out.println("found");

		Grid puzzleAs = new AStarGrid();
		TxtWriter.Generate("puzzleAs-h1.txt", puzzleAs.execute("h1"));
		puzzleAs.clear();
		TxtWriter.Generate("puzzleAs-h2.txt", puzzleAs.execute("h2"));
	}
	
}
