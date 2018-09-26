package miniProject1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class AStarGrid extends Grid {
	private static ArrayList<AStarGrid> open = new ArrayList<AStarGrid>();
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
			
			open.sort(CompareTotalCost);
			//System.out.println(open.get(0).g());
			/*System.out.println();
			System.out.println(open.get(0).g() + "  " + open.get(0).totalCost);
			System.out.println(open.get(0).grid[0].getNumber() + "  " + open.get(0).grid[1].getNumber() + "  " + open.get(0).grid[2].getNumber() + "  " +  open.get(0).grid[3].getNumber());
			System.out.println(open.get(0).grid[4].getNumber() + "  " + open.get(0).grid[5].getNumber() + "  " + open.get(0).grid[6].getNumber() + "  " +  open.get(0).grid[7].getNumber());
			System.out.println(open.get(0).grid[8].getNumber() + "  " + open.get(0).grid[9].getNumber() + "  " + open.get(0).grid[10].getNumber() + "  " +  open.get(0).grid[11].getNumber());
			*/
		}
	}
	
	private void generateSuccessorsWithH1() {
		
		Carre[] gridUp = this.moveUp();
		if(gridUp != null) {
			AStarGrid g = new AStarGrid(gridUp, this, h1(gridUp));
			if(!isInOpenOrClosed(gridUp, g.totalCost)) {
				open.add(g);
			}
		}
		
		Carre[] gridUpRight = this.moveUpRight();
		if(gridUpRight != null) {
			AStarGrid g = new AStarGrid(gridUpRight, this, h1(gridUpRight));
			if(!isInOpenOrClosed(gridUpRight, g.totalCost)) {
				open.add(g);
			}
		}
		
		Carre[] gridRight = this.moveRight();
		if(gridRight != null) {
			AStarGrid g = new AStarGrid(gridRight, this, h1(gridRight));
			if(!isInOpenOrClosed(gridRight, g.totalCost)) {
				open.add(g);
			}
		}
		
		Carre[] gridRightDown = this.moveRightDown();
		if(gridRightDown != null) {
			AStarGrid g = new AStarGrid(gridRightDown, this, h1(gridRightDown));
			if(!isInOpenOrClosed(gridRightDown, g.totalCost)) {
				open.add(g);
			}
		}
		
		Carre[] gridDown = this.moveDown();
		if(gridDown != null) {
			AStarGrid g = new AStarGrid(gridDown, this, h1(gridDown));
			if(!isInOpenOrClosed(gridDown, g.totalCost)) {
				open.add(g);
			}
		}
		
		Carre[] gridDownLeft = this.moveDownLeft();
		if(gridDownLeft != null) {
			AStarGrid g = new AStarGrid(gridDownLeft, this, h1(gridDownLeft));
			if(!isInOpenOrClosed(gridDownLeft, g.totalCost)) {
				open.add(g);
			}
		}
		
		Carre[] gridLeft = this.moveLeft();
		if(gridLeft != null) {
			AStarGrid g = new AStarGrid(gridLeft, this, h1(gridLeft));
			if(!isInOpenOrClosed(gridLeft, g.totalCost)) {
				open.add(g);
			}
		}
		
		Carre[] gridUpLeft = this.moveUpLeft();
		if(gridUpLeft != null) {
			AStarGrid g = new AStarGrid(gridUpLeft, this, h1(gridUpLeft));
			if(!isInOpenOrClosed(gridUpLeft, g.totalCost)) {
				open.add(g);
			}
		}
	}
	
	private void generateSuccessorsWithH2() {
		
		Carre[] gridUp = this.moveUp();
		if(gridUp != null) {
			AStarGrid g = new AStarGrid(gridUp, this, h2(gridUp));
			if(!isInOpenOrClosed(gridUp, g.totalCost)) {
				open.add(g);
			}
		}
		
		Carre[] gridUpRight = this.moveUpRight();
		if(gridUpRight != null) {
			AStarGrid g = new AStarGrid(gridUpRight, this, h2(gridUpRight));
			if(!isInOpenOrClosed(gridUpRight, g.totalCost)) {
				open.add(g);
			}
		}
		
		Carre[] gridRight = this.moveRight();
		if(gridRight != null) {
			AStarGrid g = new AStarGrid(gridRight, this, h2(gridRight));
			if(!isInOpenOrClosed(gridRight, g.totalCost)) {
				open.add(g);
			}
		}
		
		Carre[] gridRightDown = this.moveRightDown();
		if(gridRightDown != null) {
			AStarGrid g = new AStarGrid(gridRightDown, this, h2(gridRightDown));
			if(!isInOpenOrClosed(gridRightDown, g.totalCost)) {
				open.add(g);
			}
		}
		
		Carre[] gridDown = this.moveDown();
		if(gridDown != null) {
			AStarGrid g = new AStarGrid(gridDown, this, h2(gridDown));
			if(!isInOpenOrClosed(gridDown, g.totalCost)) {
				open.add(g);
			}
		}
		
		Carre[] gridDownLeft = this.moveDownLeft();
		if(gridDownLeft != null) {
			AStarGrid g = new AStarGrid(gridDownLeft, this, h2(gridDownLeft));
			if(!isInOpenOrClosed(gridDownLeft, g.totalCost)) {
				open.add(g);
			}
		}
		
		Carre[] gridLeft = this.moveLeft();
		if(gridLeft != null) {
			AStarGrid g = new AStarGrid(gridLeft, this, h2(gridLeft));
			if(!isInOpenOrClosed(gridLeft, g.totalCost)) {
				open.add(g);
			}
		}
		
		Carre[] gridUpLeft = this.moveUpLeft();
		if(gridUpLeft != null) {
			AStarGrid g = new AStarGrid(gridUpLeft, this, h2(gridUpLeft));
			if(!isInOpenOrClosed(gridUpLeft, g.totalCost)) {
				open.add(g);
			}
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
	
    /*Comparator for sorting the list by roll no*/
    public static Comparator<AStarGrid> CompareTotalCost = new Comparator<AStarGrid>() {

		public int compare(AStarGrid g1, AStarGrid g2) {
		   int val1 = g1.totalCost;
		   int val2 = g2.totalCost;
	
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
