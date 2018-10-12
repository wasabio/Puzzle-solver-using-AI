package miniProject1.grids;

import java.util.Scanner;

import miniProject1.utilities.Carre;

/*
 * This abstract class is inherited by all the grid classes. It is used to store all the utility functions
 * that the different grids have to use. It also provides a contract for other grid classes.
 */
public abstract class Grid {
	
	public static Carre[] goalBoard;
	
	public static Carre[] initialBoard;
	
	protected Grid parent;
	
	protected Carre[] grid;
	
	public abstract String execute(String heuristic);
	
	public abstract void clear();
	
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
