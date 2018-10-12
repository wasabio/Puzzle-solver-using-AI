package miniProject1.grids;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

import miniProject1.heuristics.HammingDistance;
import miniProject1.heuristics.ManhattanDistance;
import miniProject1.heuristics.Permutations;
import miniProject1.utilities.Carre;

public class AStarGrid extends Grid {
	
    /* Comparator for sorting the list by lowest cost */
    public static Comparator<AStarGrid> CompareTotalCost = new Comparator<AStarGrid>() {

		public int compare(AStarGrid g1, AStarGrid g2) {
		   int val1 = g1.totalCost;
		   int val2 = g2.totalCost;
	
		   /*For ascending order*/
		   return val1-val2;
	   }
   };
   	
   	private static PriorityQueue<AStarGrid> open = new PriorityQueue<AStarGrid>(1, CompareTotalCost);
	private static ArrayList<AStarGrid> closed = new ArrayList<AStarGrid>();
	private static AStarGrid current;
	private int totalCost;
	
	/* A* algorithm */
	@Override
	public String execute(String heuristic) {
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
					return current.getSolutionPath();
				}
			}
			
			closed.add(current);
			current.generateSuccessors(heuristic);
		}
	}
	
	private void generateSuccessors(String heuristic) {
		
		Carre[] gridUp = this.moveUp();
		if(gridUp != null) {
			addToOpen(gridUp, heuristic);
		}
		
		Carre[] gridUpRight = this.moveUpRight();
		if(gridUpRight != null) {
			addToOpen(gridUpRight, heuristic);
		}
		
		Carre[] gridRight = this.moveRight();
		if(gridRight != null) {
			addToOpen(gridRight, heuristic);
		}
		
		Carre[] gridRightDown = this.moveRightDown();
		if(gridRightDown != null) {
			addToOpen(gridRightDown, heuristic);
		}
		
		Carre[] gridDown = this.moveDown();
		if(gridDown != null) {
			addToOpen(gridDown, heuristic);
		}
		
		Carre[] gridDownLeft = this.moveDownLeft();
		if(gridDownLeft != null) {
			addToOpen(gridDownLeft, heuristic);
		}
		
		Carre[] gridLeft = this.moveLeft();
		if(gridLeft != null) {
			addToOpen(gridLeft, heuristic);
		}
		
		Carre[] gridUpLeft = this.moveUpLeft();
		if(gridUpLeft != null) {
			addToOpen(gridUpLeft, heuristic);
		}
	}
	
	private void addToOpen(Carre[] g, String heuristic) {
		AStarGrid grid = null;
		
		/* Choosing Heuristic */
		switch(heuristic) {
			case "h1":
				grid = new AStarGrid(g, this, HammingDistance.h(g));
				break;
			case "h2":
				grid = new AStarGrid(g, this, ManhattanDistance.h(g));
				break;
			case "h3":
				grid = new AStarGrid(g, this, Permutations.h(g));
				break;
		}
		
		if(!isInOpenOrClosed(g, grid.totalCost)) {
			open.add(grid);
		}
	}
	
	private boolean isInOpen(Carre[] grid, int cost) {
		for (Iterator<AStarGrid> i = open.iterator(); i.hasNext();) {
			AStarGrid old = i.next();
		    if(old.equals(grid)) {
		    	//Replace old grid if its cost is higher than the new grid
		    	if(old.totalCost > cost) {
		    		old.parent = this;
		    		old.totalCost = cost;
		    	}
		    	return true;
		    }
		}
		
		return false;
	}
	
	private boolean isInClosed(Carre[] grid, int cost) {
		for (Iterator<AStarGrid> i = closed.iterator(); i.hasNext();) {
			AStarGrid old = i.next();
		    if(old.equals(grid)) {
		    	//It is not possible to have grid with higher cost in closed
		    	return true;
		    }
		}
		
		return false;
	}
	
	/* Small change here : If there's a duplicate, the function will keep the lowest */
	private boolean isInOpenOrClosed(Carre[] grid, int cost) {
		return (  isInOpen(grid, cost) || isInClosed(grid, cost)  ) ? true : false;
	}

	
	public AStarGrid(Carre[] grid, AStarGrid parent, int h) {
		this.grid = grid;
		this.parent = parent;
		this.totalCost = this.g() + h;
	}

	public AStarGrid() {

	}
	
	private int g() {
		Grid grid = this;
		int cost = 0;
		
		while(grid.parent != null) {
			cost++;
			grid = grid.parent;
		}
		return cost;
	}
   
   @Override
   public void clear() {
	   open.clear();
	   closed.clear();
	   current = null;
   }
}
