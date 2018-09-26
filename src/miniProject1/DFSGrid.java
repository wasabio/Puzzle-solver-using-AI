package miniProject1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class DFSGrid extends Grid {
	
	private static Stack<DFSGrid> open = new Stack<DFSGrid>();
	private static ArrayList<DFSGrid> closed = new ArrayList<DFSGrid>();
	private static DFSGrid current;
	
	/* DFS algorithm */
	@Override
	public String execute(String h) {
		this.grid = initialBoard;
		this.parent = null;
		open.push(this);
		
		while(true) {
			if(open.size() == 0) {
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
	
	/* Successors are pushed in a counter-clockwise direction so they are handled clockwise because of the stack */
	private void generateSuccessors() {
		
		Carre[] gridUpLeft = this.moveUpLeft();
		if(gridUpLeft != null && (!isInOpenOrClosed(gridUpLeft))) {
			open.push(new DFSGrid(gridUpLeft, this));
		}
		
		Carre[] gridLeft = this.moveLeft();
		if(gridLeft != null && (!isInOpenOrClosed(gridLeft))) {
			open.push(new DFSGrid(gridLeft, this));
		}
		
		Carre[] gridDownLeft = this.moveDownLeft();
		if(gridDownLeft != null && (!isInOpenOrClosed(gridDownLeft))) {
			open.push(new DFSGrid(gridDownLeft, this));
		}
		
		Carre[] gridDown = this.moveDown();
		if(gridDown != null && (!isInOpenOrClosed(gridDown))) {
			open.push(new DFSGrid(gridDown, this));
		}
		
		Carre[] gridRightDown = this.moveRightDown();
		if(gridRightDown != null && (!isInOpenOrClosed(gridRightDown))) {
			open.push(new DFSGrid(gridRightDown, this));
		}
		
		Carre[] gridRight = this.moveRight();
		if(gridRight != null && (!isInOpenOrClosed(gridRight))) {
			open.push(new DFSGrid(gridRight, this));
		}
		
		Carre[] gridUpRight = this.moveUpRight();
		if(gridUpRight != null && (!isInOpenOrClosed(gridUpRight))) {
			open.push(new DFSGrid(gridUpRight, this));
		}
		
		Carre[] gridUp = this.moveUp();
		if(gridUp != null && (!isInOpenOrClosed(gridUp))) {
			open.push(new DFSGrid(gridUp, this));
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

	
	public DFSGrid(Carre[] grid, DFSGrid parent) {
		this.grid = grid;
		this.parent = parent;
	}

	public DFSGrid() {

	}
	
	@Override
	public void clear() {
		
	}
}
