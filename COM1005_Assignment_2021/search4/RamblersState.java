import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class RamblersState extends SearchState {
	
	private Coords coordinates;
	private boolean aStar;
	private boolean branchAndBound;
	
	//Branch And Bound Constructor
	public RamblersState(Coords coordinates, int localCost) {
		this.coordinates = coordinates;
		this.localCost = localCost;
		this.branchAndBound = true;
	}
	
	//A* Constructor
	public RamblersState(Coords coordinates, int localCost, int estimatedRemainingCost) {
		this.coordinates = coordinates;
		this.localCost = localCost;
		this.estRemCost = estimatedRemainingCost;
		this.aStar= true;
	}
	
	//accessor Methods
	public Coords getCoordinates() {
		return coordinates;
	}

//	public void setCoordinates(Coords coordinates) {
//		this.coordinates = coordinates;
//	}

	/**
	 * @Override Check if the state is the target/goal?
	 * @param searcher - is an instance of Search
	 * @param mSearcher -is an instance of Rambler Search
	 * @return true if the target have the same coordinates else return false
	 */
	boolean goalPredicate(Search searcher) {
		RamblersSearch mSearcher = (RamblersSearch) searcher;
		Coords target = mSearcher.getTarget();
		 if((target.getx() == coordinates.getx()) && (target.gety() == coordinates.gety())) { 
			return true;
		} else
			return false;
	}

	/**
	 * @Override get all successors of the state
	 * @param searcher - is a Search Instance
	 * @param rSearcher - is a Rambler Search instance
	 * @return  an ArrayList of all successors. 
	 */
	ArrayList<SearchState> getSuccessors(Search searcher) {
		RamblersSearch rSearcher = (RamblersSearch) searcher;
		TerrainMap terrainMap = rSearcher.getTerrainMap();
		List<Coords> valCoordinates = new ArrayList<>();
		ArrayList<SearchState> successors = new ArrayList<SearchState>();
		
		
		// Coordinates of up/down/left and right
		Coords moveUp = 	new Coords(coordinates.gety()-1,coordinates.getx());
		Coords moveDown = 	new Coords(coordinates.gety()+1,coordinates.getx());
		Coords moveRight =  new Coords(coordinates.gety(),coordinates.getx()+1);
		Coords moveLeft =   new Coords(coordinates.gety(),coordinates.getx()-1);
	
		//Condition to move within allowed X Coordinates
		if((coordinates.getx()<terrainMap.getWidth()-1) && (coordinates.getx()>0)) {
			valCoordinates.add(moveLeft);
			valCoordinates.add(moveRight);
		} else if(coordinates.getx() == (terrainMap.getWidth()-1)) {
			valCoordinates.add(moveLeft);
		} else if(coordinates.getx()== 0) {
			valCoordinates.add(moveRight);
		}
		
		//Condition to move within allowed Y Coordinates
		if((coordinates.gety()<terrainMap.getDepth()-1) && (coordinates.gety()>0)) {
			valCoordinates.add(moveUp);
			valCoordinates.add(moveDown);
		} else if(coordinates.gety()== (terrainMap.getDepth()-1)) {
			valCoordinates.add(moveUp);
		} else if(coordinates.gety() == 0) {
			valCoordinates.add(moveDown);
		}
		
		//cost of moving downwards
		int costUpAndDownwards = 1;
		int estimatedCost = 0;	
		
		String heuristicType = rSearcher.getHeuristicType();
		for(Coords cord : valCoordinates ) {
			int currentCoordinates = terrainMap.getTmap()[coordinates.gety()][coordinates.getx()];
			int nextCoordinates = terrainMap.getTmap()[cord.gety()][cord.getx()];
				
				//Cost of moving upwards
				if(nextCoordinates > currentCoordinates) {
					costUpAndDownwards = 1+(nextCoordinates-currentCoordinates);
				}
			
			if(branchAndBound) { 
				successors.add(new RamblersState(cord, costUpAndDownwards));
			} 
			if(aStar){
				if(heuristicType.equalsIgnoreCase("manhattan")) {
					estimatedCost = manhattanDistance(cord,searcher);
				} else if(heuristicType.equalsIgnoreCase("euclidean")) {
					estimatedCost = euclideanDistance(cord, searcher);
				} else if(heuristicType.equalsIgnoreCase("height")) {
					estimatedCost = heightDifference(cord, searcher);
				} successors.add(new RamblersState(cord, costUpAndDownwards, estimatedCost));
				
			}
				
		}
	
		
		return successors;
	}

	/**
	 * @Override Determine if the two states are the same
	 * @return true if the states are the same otherwise false
	 */
	boolean sameState(SearchState search) {
		RamblersState  ramblersState = (RamblersState) search;
		if(coordinates.getx()==ramblersState.getCoordinates().getx() && coordinates.gety() == ramblersState.getCoordinates().gety()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * the String Method
	 * @return a string which shows the position of the coordinates.
	 */
	public String toString() {
		return "Position: ( " +coordinates.getx()+ " , "+coordinates.gety()+ " ) ";
	}
	
	
	/**
	 * Manhattan Distance
	 * @param coordinates - x and y values of the successors
	 * @param search      - is an instance of a Search
	 * @return tDistance  - the total distance between state and target
	 */
	
	public int manhattanDistance(Coords coordinates, Search search ) {
		RamblersSearch rSearch = (RamblersSearch) search;
		Coords target = rSearch.getTarget();
		int xAxis,yAxis;
		
		
		if(target.gety() <= coordinates.gety()) {
			yAxis = coordinates.gety() - target.gety();
		} else { 
			yAxis = target.gety() - coordinates.gety();
			
		}
		
		if(target.getx()<=coordinates.getx()) {
			xAxis = coordinates.getx() - target.getx();
		} else {
			xAxis = target.getx() - coordinates.getx();
		}
		
		int tDistance;
		tDistance = yAxis + xAxis;
		return tDistance;
	}
	
	/**
	 * 
	 * @param coordinates - x and y values of the successors
	 * @param search      - is an instance of a Search
	 * @return result - Euclidean distance between state and target.
	 */
	
	public int euclideanDistance(Coords coordinates, Search search) {
		RamblersSearch rSearch = (RamblersSearch) search;
		Coords target = rSearch.getTarget();
		
		double yAxis = coordinates.gety() - target.gety();
		double xAxis = coordinates.getx() - target.getx();
		
		int  tDistance = (int)(Math.sqrt((yAxis*yAxis)+(xAxis*xAxis)));
		return tDistance;
	}
	
	/**
	 * 
	 * @param coordinates - x and y values of the successors
	 * @param search	  - is an instance of a Search
	 * @return result	  - absolute height between state and target.
	 */
	
	public int heightDifference(Coords coordinates, Search search) {
		RamblersSearch rSearch = (RamblersSearch) search;
		Coords target = rSearch.getTarget();
		TerrainMap terrainMap = rSearch.getTerrainMap();
		
		
		int targetValue = terrainMap.getTmap()[target.gety()][target.getx()];
		int presentValue = terrainMap.getTmap()[coordinates.gety()][coordinates.getx()];
		
		int result = Math.abs((targetValue-presentValue));
		return result;
		
	}

	

}
