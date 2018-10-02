package miniProject1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class BFSGrid extends Grid {

	/* Comparator for sorting the list by roll no */
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
		if(gridUp != null && (!isInOpenOrClosed(gridUp))) {
			/* Choosing Heuristic */
			switch(heuristic) {
				case "h1":
					open.add(new BFSGrid(gridUp, this, h1(gridUp)));
					break;
				case "h2":
					open.add(new BFSGrid(gridUp, this, h2(gridUp)));
					break;
				case "h3":
					open.add(new BFSGrid(gridUp, this, h3(gridUp)));
					break;
			}
		}
		
		Carre[] gridUpRight = this.moveUpRight();
		if(gridUpRight != null && (!isInOpenOrClosed(gridUpRight))) {
			/* Choosing Heuristic */
			switch(heuristic) {
				case "h1":
					open.add(new BFSGrid(gridUpRight, this, h1(gridUpRight)));
					break;
				case "h2":
					open.add(new BFSGrid(gridUpRight, this, h2(gridUpRight)));
					break;
				case "h3":
					open.add(new BFSGrid(gridUpRight, this, h3(gridUpRight)));
					break;
			}
		}
		
		Carre[] gridRight = this.moveRight();
		if(gridRight != null && (!isInOpenOrClosed(gridRight))) {
			/* Choosing Heuristic */
			switch(heuristic) {
				case "h1":
					open.add(new BFSGrid(gridRight, this, h1(gridRight)));
					break;
				case "h2":
					open.add(new BFSGrid(gridRight, this, h2(gridRight)));
					break;
				case "h3":
					open.add(new BFSGrid(gridRight, this, h3(gridRight)));
					break;
			}
		}
		
		Carre[] gridRightDown = this.moveRightDown();
		if(gridRightDown != null && (!isInOpenOrClosed(gridRightDown))) {
			/* Choosing Heuristic */
			switch(heuristic) {
				case "h1":
					open.add(new BFSGrid(gridRightDown, this, h1(gridRightDown)));
					break;
				case "h2":
					open.add(new BFSGrid(gridRightDown, this, h2(gridRightDown)));
					break;
				case "h3":
					open.add(new BFSGrid(gridRightDown, this, h3(gridRightDown)));
					break;
			}
		}
		
		Carre[] gridDown = this.moveDown();
		if(gridDown != null && (!isInOpenOrClosed(gridDown))) {
			/* Choosing Heuristic */
			switch(heuristic) {
				case "h1":
					open.add(new BFSGrid(gridDown, this, h1(gridDown)));
					break;
				case "h2":
					open.add(new BFSGrid(gridDown, this, h2(gridDown)));
					break;
				case "h3":
					open.add(new BFSGrid(gridDown, this, h3(gridDown)));
					break;
			}
		}
		
		Carre[] gridDownLeft = this.moveDownLeft();
		if(gridDownLeft != null && (!isInOpenOrClosed(gridDownLeft))) {
			/* Choosing Heuristic */
			switch(heuristic) {
				case "h1":
					open.add(new BFSGrid(gridDownLeft, this, h1(gridDownLeft)));
					break;
				case "h2":
					open.add(new BFSGrid(gridDownLeft, this, h2(gridDownLeft)));
					break;
				case "h3":
					open.add(new BFSGrid(gridDownLeft, this, h3(gridDownLeft)));
					break;
			}
		}
		
		Carre[] gridLeft = this.moveLeft();
		if(gridLeft != null && (!isInOpenOrClosed(gridLeft))) {
			/* Choosing Heuristic */
			switch(heuristic) {
				case "h1":
					open.add(new BFSGrid(gridLeft, this, h1(gridLeft)));
					break;
				case "h2":
					open.add(new BFSGrid(gridLeft, this, h2(gridLeft)));
					break;
				case "h3":
					open.add(new BFSGrid(gridLeft, this, h3(gridLeft)));
					break;
			}
		}
		
		Carre[] gridUpLeft = this.moveUpLeft();
		if(gridUpLeft != null && (!isInOpenOrClosed(gridUpLeft))) {
			/* Choosing Heuristic */
			switch(heuristic) {
				case "h1":
					open.add(new BFSGrid(gridUpLeft, this, h1(gridUpLeft)));
					break;
				case "h2":
					open.add(new BFSGrid(gridUpLeft, this, h2(gridUpLeft)));
					break;
				case "h3":
					open.add(new BFSGrid(gridUpLeft, this, h3(gridUpLeft)));
					break;
			}
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
