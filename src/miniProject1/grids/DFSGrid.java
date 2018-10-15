package miniProject1.grids;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import miniProject1.utilities.Carre;

public class DFSGrid extends Grid {
	
	private static Stack<DFSGrid> open = new Stack<DFSGrid>();
	private static ArrayList<DFSGrid> closed = new ArrayList<DFSGrid>();
	private static DFSGrid current;
	private static int cutoff = -1;
	private int depth;
	
	/* DFS algorithm */
	@Override
	public String execute(String h) {
		this.depth = 0;
		this.grid = initialBoard;
		this.parent = null;
		open.push(this);
		
		while(true) {
			if(open.size() == 0) {
				System.out.println(" 0 move");
				return "No solution found";
			}
			else {
				current = open.pop();

				if(current.isGoalState()) {
					return current.getSolutionPath();
				}
			}
			
			closed.add(current);
			current.generateSuccessors();
		}
	}
	
	/* Successors are pushed in a counter-clockwise direction so they are popped clockwise because of the stack */
	private void generateSuccessors() {		
		
		if(cutoff == -1 || this.depth < cutoff) {
			
			Carre[] gridUpLeft = this.moveUpLeft();
			if(gridUpLeft != null && (!isInOpenOrClosed(gridUpLeft))) {
				open.push(new DFSGrid(gridUpLeft, this, (this.depth+1)));
			}
			
			Carre[] gridLeft = this.moveLeft();
			if(gridLeft != null && (!isInOpenOrClosed(gridLeft))) {
				open.push(new DFSGrid(gridLeft, this, this.depth+1));
			}
			
			Carre[] gridDownLeft = this.moveDownLeft();
			if(gridDownLeft != null && (!isInOpenOrClosed(gridDownLeft))) {
				open.push(new DFSGrid(gridDownLeft, this, this.depth+1));
			}
			
			Carre[] gridDown = this.moveDown();
			if(gridDown != null && (!isInOpenOrClosed(gridDown))) {
				DFSGrid g = new DFSGrid(gridDown, this, this.depth+1);
				open.push(g);
			}
			
			Carre[] gridRightDown = this.moveRightDown();
			if(gridRightDown != null && (!isInOpenOrClosed(gridRightDown))) {
				open.push(new DFSGrid(gridRightDown, this, this.depth+1));
			}
			
			Carre[] gridRight = this.moveRight();
			if(gridRight != null && (!isInOpenOrClosed(gridRight))) {
				open.push(new DFSGrid(gridRight, this, this.depth+1));
			}
			
			Carre[] gridUpRight = this.moveUpRight();
			if(gridUpRight != null && (!isInOpenOrClosed(gridUpRight))) {
				open.push(new DFSGrid(gridUpRight, this, (this.depth+1)));
			}
			
			Carre[] gridUp = this.moveUp();
			if(gridUp != null && (!isInOpenOrClosed(gridUp))) {
				open.push(new DFSGrid(gridUp, this, this.depth+1));
			}
		}
	}

	private boolean isInOpen(Carre[] grid) {
		for (Iterator<DFSGrid> i = open.iterator(); i.hasNext();) {
		    if(i.next().equals(grid)) {
		    	return true;
		    }
		}
		
		return false;
	}
	
	private boolean isInClosed(Carre[] grid) {
		for (Iterator<DFSGrid> i = closed.iterator(); i.hasNext();) {
		    if(i.next().equals(grid)) {
		    	return true;
		    }
		}
		
		return false;
	}
	
	private boolean isInOpenOrClosed(Carre[] grid) {
		return (  isInOpen(grid) || isInClosed(grid)  ) ? true : false;
	}

	public DFSGrid(Carre[] grid, DFSGrid parent, int depth) {
		this.grid = grid;
		this.parent = parent;
		this.depth = depth;
	}

	public DFSGrid(int cutoff) {
		DFSGrid.cutoff = cutoff;
	}
	
	@Override
	public void clear() {
		open.clear();
		closed.clear();
		cutoff = -1;
		current = null;
	}
}
