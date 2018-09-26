package miniProject1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class BFSGrid extends Grid {

	private static ArrayList<BFSGrid> open = new ArrayList<BFSGrid>();
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
				current = open.remove(0);
				if(current.isGoalState()) {
					return current.getSolutionPath();
				}
			}
			
			closed.add(current);
			
			if(heuristic.equals("h1")) {
				current.generateSuccessorsWithH1();
			} else if (heuristic.equals("h2")) {
				current.generateSuccessorsWithH2();
			}
			
			open.sort(CompareHeuristicValue);
		}
	}
	
	private void generateSuccessorsWithH1() {
		
		Carre[] gridUp = this.moveUp();
		if(gridUp != null && (!isInOpenOrClosed(gridUp))) {
			open.add(new BFSGrid(gridUp, this, h1(gridUp)));
		}
		
		Carre[] gridUpRight = this.moveUpRight();
		if(gridUpRight != null && (!isInOpenOrClosed(gridUpRight))) {
			open.add(new BFSGrid(gridUpRight, this, h1(gridUpRight)));
		}
		
		Carre[] gridRight = this.moveRight();
		if(gridRight != null && (!isInOpenOrClosed(gridRight))) {
			open.add(new BFSGrid(gridRight, this, h1(gridRight)));
		}
		
		Carre[] gridRightDown = this.moveRightDown();
		if(gridRightDown != null && (!isInOpenOrClosed(gridRightDown))) {
			open.add(new BFSGrid(gridRightDown, this, h1(gridRightDown)));
		}
		
		Carre[] gridDown = this.moveDown();
		if(gridDown != null && (!isInOpenOrClosed(gridDown))) {
			open.add(new BFSGrid(gridDown, this, h1(gridDown)));
		}
		
		Carre[] gridDownLeft = this.moveDownLeft();
		if(gridDownLeft != null && (!isInOpenOrClosed(gridDownLeft))) {
			open.add(new BFSGrid(gridDownLeft, this, h1(gridDownLeft)));
		}
		
		Carre[] gridLeft = this.moveLeft();
		if(gridLeft != null && (!isInOpenOrClosed(gridLeft))) {
			open.add(new BFSGrid(gridLeft, this, h1(gridLeft)));
		}
		
		Carre[] gridUpLeft = this.moveUpLeft();
		if(gridUpLeft != null && (!isInOpenOrClosed(gridUpLeft))) {
			open.add(new BFSGrid(gridUpLeft, this, h1(gridUpLeft)));
		}
	}
	
	private void generateSuccessorsWithH2() {
		
		Carre[] gridUp = this.moveUp();
		if(gridUp != null && (!isInOpenOrClosed(gridUp))) {
			open.add(new BFSGrid(gridUp, this, h2(gridUp)));
		}
		
		Carre[] gridUpRight = this.moveUpRight();
		if(gridUpRight != null && (!isInOpenOrClosed(gridUpRight))) {
			open.add(new BFSGrid(gridUpRight, this, h2(gridUpRight)));
		}
		
		Carre[] gridRight = this.moveRight();
		if(gridRight != null && (!isInOpenOrClosed(gridRight))) {
			open.add(new BFSGrid(gridRight, this, h2(gridRight)));
		}
		
		Carre[] gridRightDown = this.moveRightDown();
		if(gridRightDown != null && (!isInOpenOrClosed(gridRightDown))) {
			open.add(new BFSGrid(gridRightDown, this, h2(gridRightDown)));
		}
		
		Carre[] gridDown = this.moveDown();
		if(gridDown != null && (!isInOpenOrClosed(gridDown))) {
			open.add(new BFSGrid(gridDown, this, h2(gridDown)));
		}
		
		Carre[] gridDownLeft = this.moveDownLeft();
		if(gridDownLeft != null && (!isInOpenOrClosed(gridDownLeft))) {
			open.add(new BFSGrid(gridDownLeft, this, h2(gridDownLeft)));
		}
		
		Carre[] gridLeft = this.moveLeft();
		if(gridLeft != null && (!isInOpenOrClosed(gridLeft))) {
			open.add(new BFSGrid(gridLeft, this, h2(gridLeft)));
		}
		
		Carre[] gridUpLeft = this.moveUpLeft();
		if(gridUpLeft != null && (!isInOpenOrClosed(gridUpLeft))) {
			open.add(new BFSGrid(gridUpLeft, this, h2(gridUpLeft)));
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
	
    /*Comparator for sorting the list by roll no*/
    public static Comparator<BFSGrid> CompareHeuristicValue = new Comparator<BFSGrid>() {

		public int compare(BFSGrid g1, BFSGrid g2) {
	
		   int val1 = g1.heuristic;
		   int val2 = g2.heuristic;
	
		   /*For ascending order*/
		   return val1-val2;
	   }
   };
   
   @Override
   public void clear() {
	   open.clear();
	   closed.clear();
	   current = null;
   }
}
