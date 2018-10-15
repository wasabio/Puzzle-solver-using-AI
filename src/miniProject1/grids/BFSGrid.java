package miniProject1.grids;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

import miniProject1.heuristics.HammingDistance;
import miniProject1.heuristics.ManhattanDistance;
import miniProject1.heuristics.ManhattanDistanceDiago;
import miniProject1.heuristics.Permutations;
import miniProject1.heuristics.WrongNeighbours;
import miniProject1.utilities.Carre;

/*
 * Best-first search grid
 */
public class BFSGrid extends Grid {

	/* Comparator for sorting the list by lowest heuristic */
    public static Comparator<BFSGrid> CompareHeuristicValue = new Comparator<BFSGrid>() {

		public int compare(BFSGrid g1, BFSGrid g2) {
		   int val1 = g1.heuristic;
		   int val2 = g2.heuristic;
	
		   /* For ascending order */
		   return val1-val2;
	   }
   };
   
	private static PriorityQueue<BFSGrid> open = new PriorityQueue<BFSGrid>(1, CompareHeuristicValue);
	private static ArrayList<BFSGrid> closed = new ArrayList<BFSGrid>();
	private static BFSGrid current;
	private int heuristic;
	
	/* BFS algorithm */
	@Override
	public String execute(String heuristic) {
		double startTime = System.nanoTime();		
		this.grid = initialBoard;
		this.parent = null;
		open.add(this);
		
		while(true) {
			if(open.size() == 0) {
				return "No solution found";
			}
			else {
				current = open.remove();
				if(current.isGoalState()) {
					double endTime = System.nanoTime();
					double duration = ((endTime - startTime)/1000000);  //milliseconds.
					System.out.print(duration + "ms - ");
					return current.getSolutionPath();
				}
			}

			closed.add(current);
			current.generateSuccessors(heuristic);
		}
	}
	
	/*
	 * Generating successors
	 */
	private void generateSuccessors(String heuristic) {
		Carre[] gridUp = this.moveUp();
		if(gridUp != null && (!isInOpenOrClosed(gridUp))) {
			addToOpen(gridUp, heuristic);
		}
		
		Carre[] gridUpRight = this.moveUpRight();
		if(gridUpRight != null && (!isInOpenOrClosed(gridUpRight))) {
			addToOpen(gridUpRight, heuristic);
		}
		
		Carre[] gridRight = this.moveRight();
		if(gridRight != null && (!isInOpenOrClosed(gridRight))) {
			addToOpen(gridRight, heuristic);
		}
		
		Carre[] gridRightDown = this.moveRightDown();
		if(gridRightDown != null && (!isInOpenOrClosed(gridRightDown))) {
			addToOpen(gridRightDown, heuristic);
		}
		
		Carre[] gridDown = this.moveDown();
		if(gridDown != null && (!isInOpenOrClosed(gridDown))) {
			addToOpen(gridDown, heuristic);
		}
		
		Carre[] gridDownLeft = this.moveDownLeft();
		if(gridDownLeft != null && (!isInOpenOrClosed(gridDownLeft))) {
			addToOpen(gridDownLeft, heuristic);
		}
		
		Carre[] gridLeft = this.moveLeft();
		if(gridLeft != null && (!isInOpenOrClosed(gridLeft))) {
			addToOpen(gridLeft, heuristic);
		}
		
		Carre[] gridUpLeft = this.moveUpLeft();
		if(gridUpLeft != null && (!isInOpenOrClosed(gridUpLeft))) {
			addToOpen(gridUpLeft, heuristic);
		}
	}
	
	/*
	 * Evaluating a new grid with the heuristic and adding it to open.
	 */
	private void addToOpen(Carre[] g, String heuristic) {
		/* Choosing Heuristic */
		switch(heuristic) {
			case "h1":
				open.add(new BFSGrid(g, this, WrongNeighbours.h(g)));
				break;
			case "h2":
				open.add(new BFSGrid(g, this, ManhattanDistanceDiago.h(g)));
				break;
			case "h3":
				open.add(new BFSGrid(g, this, HammingDistance.h(g)));
				break;
			case "h4":
				open.add(new BFSGrid(g, this, ManhattanDistance.h(g)));
				break;
			case "h5":
				open.add(new BFSGrid(g, this, Permutations.h(g)));
				break;
		}
	}
	
	private boolean isInOpen(Carre[] grid) {
		for (Iterator<BFSGrid> i = open.iterator(); i.hasNext();) {
		    if(i.next().equals(grid)) {
		    	return true;
		    }
		}
		
		return false;
	}
	
	private boolean isInClosed(Carre[] grid) {
		for (Iterator<BFSGrid> i = closed.iterator(); i.hasNext();) {
		    if(i.next().equals(grid)) {
		    	return true;
		    }
		}
		
		return false;
	}
	
	private boolean isInOpenOrClosed(Carre[] grid) {
		return (  isInOpen(grid) || isInClosed(grid)  ) ? true : false;
	}
	
	public BFSGrid(Carre[] grid, BFSGrid parent, int heuristic) {
		this.grid = grid;
		this.parent = parent;
		this.heuristic = heuristic;
	}

	public BFSGrid() {

	}
   
   @Override
   public void clear() {
	   open.clear();
	   closed.clear();
	   current = null;
   }
}
