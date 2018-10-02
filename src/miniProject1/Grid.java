package miniProject1;

import java.util.Scanner;

public abstract class Grid {
	
	protected static Carre[] goalBoard;
	
	protected static Carre[] initialBoard;
	
	protected Grid parent;
	
	protected Carre[] grid;
	
	public abstract String execute(String heuristic);
	
	public abstract void clear();
	
	/* The SIMPLEST heuristic / Lowest value is the best */
	/* Hamming distance : count number of tiles out of place when compared with goal */
	protected int h1(Carre[] n) {
		int val = 0;
		
		for(int i = 0; i < n.length; i++) {
			if(n[i].getNumber() != goalBoard[i].getNumber() && n[i].getNumber() != 0) {
				val++;
			}
		}
		return val;
	}
	
	/* A BETTER heuristic / Lowest value is the best */
	/* Manhattan distance :  sum up all the distances by which tiles are out of place */
	protected int h2(Carre[] n) {
		int val = 0;

		for(int i = 0; i < n.length; i++) {
			if(n[i].getNumber() != 0 && n[i].getNumber() != goalBoard[i].getNumber()) {
				for(int goalIndex = 0; goalIndex < n.length; goalIndex++) {
					if(goalBoard[goalIndex].getNumber() == n[i].getNumber()) {
						int x = (i/4);
						int y = (i%4);
						int xGoal = (goalIndex/4);
						int yGoal = (goalIndex%4);
						val = val + Math.max( Math.abs(x - xGoal), Math.abs(y - yGoal) );	//Max between difference of X and Y positions to allow diagonal moves

						break;
					}
				}
			}
		}
		
		return val;
	}
	
	/* The BEST heuristic / Lowest value is the best */
	/* sum of permutation inversions */
	protected int h3(Carre[] n) {
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
	
	public static void generateInitialState()
	{
		Scanner sc = new Scanner(System.in);
		String[] line = sc.nextLine(). split(" ");
		
		initialBoard = new Carre[line.length];
		goalBoard = new Carre[line.length];
		
		for(int i = 0; i < line.length; i++) {
			int number = Integer.parseInt(line[i]);
			String letter = Character.toString ((char) (97 + i));
			
			initialBoard[i] = new Carre(number, letter);
			goalBoard[i] = new Carre((i+1), letter);
		}
		
		goalBoard[line.length-1].setNumber(0);
		sc.close();
	}
	
	protected boolean equals(Carre[] gridToCompare) {
		for(int i = 0; i < this.grid.length; i++) {
			if(this.grid[i].getNumber() != gridToCompare[i].getNumber()) {
				return false;
			}
		}
		
		return true;
	}
	
	protected boolean isGoalState() {
		for(int i = 0; i < this.grid.length; i++) {
			if(this.grid[i].getNumber() != goalBoard[i].getNumber()) {
				return false;
			}
		}
		
		return true;
	}
	
	private int getEmptyTileIndex() {
		for(int i = 0; i < this.grid.length; i++) {
			if(this.grid[i].getNumber() == 0) {
				return i;
			}
		}
		return -1;
	}
	
	protected Carre[] getCopyOf(Carre[] origin) {
		Carre[] copy = new Carre[(origin.length)];
		
		for(int i = 0; i < origin.length; i++) {
			copy[i] = new Carre(origin[i].getNumber(), origin[i].getLetter());
		}
		
		return copy;
	}
	
	protected String getSolutionPath() {
		String solutionPath = "";
		Grid grid = this;
		
		while(grid.parent != null)
		{
				solutionPath = System.lineSeparator() + grid.getMoveLetter() + " " + grid.toString() + solutionPath;
				grid = grid.parent;
		}
		
		solutionPath = "0 " + grid.toString() + solutionPath;
		
		return solutionPath;
	}
	
	private String getMoveLetter() {
		return this.grid[getEmptyTileIndex()].getLetter();
	}
	
	public String toString() {
		if(this.grid == null) {
			return null;
		} else {
			String str = "[";
			
			for(int i = 0; i < (this.grid.length-1); i++) {
				str = str + this.grid[i].getNumber() + ", ";
			}
			str = str + this.grid[this.grid.length-1].getNumber() + "]";
			
			return str;
		}
	}
	
	/*		MOVES		*/
	protected Carre[] moveUp() {
		int emptyTileIndex = getEmptyTileIndex();
		
		if(isUpPossible(emptyTileIndex)) {
			int upTileIndex = (emptyTileIndex - 4);
			int UpNumber = this.grid[upTileIndex].getNumber();
			Carre[] successorGrid = getCopyOf(this.grid);
			
			successorGrid[emptyTileIndex].setNumber(UpNumber);
			successorGrid[upTileIndex].setNumber(0);
			
			return successorGrid;
		}
		
		return null;
	}
	
	protected Carre[] moveUpRight() {
		int emptyTileIndex = getEmptyTileIndex();
		
		if(isUpPossible(emptyTileIndex) && isRightPossible(emptyTileIndex)) {
			int upRightTileIndex = (emptyTileIndex - 3);
			int UpRightNumber = this.grid[upRightTileIndex].getNumber();
			Carre[] successorGrid = getCopyOf(this.grid);
			
			successorGrid[emptyTileIndex].setNumber(UpRightNumber);
			successorGrid[upRightTileIndex].setNumber(0);
			
			return successorGrid;
		}
		
		return null;
	}
	
	protected Carre[] moveRight() {
		int emptyTileIndex = getEmptyTileIndex();
		
		if(isRightPossible(emptyTileIndex)) {
			int rightTileIndex = (emptyTileIndex + 1);
			int rightNumber = this.grid[rightTileIndex].getNumber();
			Carre[] successorGrid = getCopyOf(this.grid);
			
			successorGrid[emptyTileIndex].setNumber(rightNumber);
			successorGrid[rightTileIndex].setNumber(0);
			
			return successorGrid;
		}
		
		return null;
	}
	
	protected Carre[] moveRightDown() {
		int emptyTileIndex = getEmptyTileIndex();
		
		if(isRightPossible(emptyTileIndex) && isDownPossible(emptyTileIndex)) {
			int rightDownTileIndex = (emptyTileIndex + 5);
			int rightDownNumber = this.grid[rightDownTileIndex].getNumber();
			Carre[] successorGrid = getCopyOf(this.grid);
			
			successorGrid[emptyTileIndex].setNumber(rightDownNumber);
			successorGrid[rightDownTileIndex].setNumber(0);
			
			return successorGrid;
		}
		
		return null;
	}
	
	protected Carre[] moveDown() {
		int emptyTileIndex = getEmptyTileIndex();
		
		if(isDownPossible(emptyTileIndex)) {
			int downTileIndex = (emptyTileIndex + 4);
			int downNumber = this.grid[downTileIndex].getNumber();
			Carre[] successorGrid = getCopyOf(this.grid);
			
			successorGrid[emptyTileIndex].setNumber(downNumber);
			successorGrid[downTileIndex].setNumber(0);
			
			return successorGrid;
		}
		
		return null;
	}
	
	protected Carre[] moveDownLeft() {
		int emptyTileIndex = getEmptyTileIndex();
		
		if(isDownPossible(emptyTileIndex) && isLeftPossible(emptyTileIndex)) {
			int downLeftTileIndex = (emptyTileIndex + 3);
			int downLeftNumber = this.grid[downLeftTileIndex].getNumber();
			Carre[] successorGrid = getCopyOf(this.grid);
			
			successorGrid[emptyTileIndex].setNumber(downLeftNumber);
			successorGrid[downLeftTileIndex].setNumber(0);
			
			return successorGrid;
		}
		
		return null;
	}
	
	protected Carre[] moveLeft() {
		int emptyTileIndex = getEmptyTileIndex();
		
		if(isLeftPossible(emptyTileIndex)) {
			int leftTileIndex = (emptyTileIndex - 1);
			int leftNumber = this.grid[leftTileIndex].getNumber();
			Carre[] successorGrid = getCopyOf(this.grid);
			
			successorGrid[emptyTileIndex].setNumber(leftNumber);
			successorGrid[leftTileIndex].setNumber(0);
			
			return successorGrid;
		}
		
		return null;
	}
	
	protected Carre[] moveUpLeft() {
		int emptyTileIndex = getEmptyTileIndex();
		
		if(isUpPossible(emptyTileIndex) && isLeftPossible(emptyTileIndex)) {
			int upLeftTileIndex = (emptyTileIndex - 5);
			int upLeftNumber = this.grid[upLeftTileIndex].getNumber();
			Carre[] successorGrid = getCopyOf(this.grid);
			
			successorGrid[emptyTileIndex].setNumber(upLeftNumber);
			successorGrid[upLeftTileIndex].setNumber(0);
			
			return successorGrid;
		}
		
		return null;
	}	
	
	/* Check possible moves */
	private boolean isDownPossible(int emptyTileIndex) {
		return ((emptyTileIndex + 4) <= 11) ? true : false;
	}
	
	private boolean isUpPossible(int emptyTileIndex) {
		return ((emptyTileIndex - 4) >= 0) ? true : false;
	}
	
	private boolean isLeftPossible(int emptyTileIndex) {
		return ((emptyTileIndex % 4) != 0) ? true : false;
	}
	
	private boolean isRightPossible(int emptyTileIndex) {
		return ((emptyTileIndex % 4) != 3) ? true : false;
	}
	
	/* Print initial grid */
	public static void print() {
		System.out.println(" ___________ ");
		System.out.print("|");
		for(int i = 0; i <= 3; i++) {
			if(initialBoard[i].getNumber() < 10) {
				System.out.print(" ");
			}
			System.out.print(initialBoard[i].getNumber() + "|");
		}
		System.out.println();
		System.out.println(" ___________ ");
		System.out.print("|");
		for(int i = 4; i <= 7; i++) {
			if(initialBoard[i].getNumber() < 10) {
				System.out.print(" ");
			}
			System.out.print(initialBoard[i].getNumber() + "|");
		}
		System.out.println();
		System.out.println(" ___________ ");
		System.out.print("|");
		for(int i = 8; i <= 11; i++) {
			if(initialBoard[i].getNumber() < 10) {
				System.out.print(" ");
			}
			System.out.print(initialBoard[i].getNumber() + "|");
		}
		System.out.println();
		System.out.println(" ___________ ");
		System.out.println();
	}
}
