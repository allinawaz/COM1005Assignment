import java.util.ArrayList;
import java.util.List;

public class RamblersState extends SearchState {
	
	private Coords coordinates;
	
	public RamblersState(Coords coordinates, int localCost) {
		this.coordinates = coordinates;
		this.localCost = localCost;
	}

	public Coords getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coords coordinates) {
		this.coordinates = coordinates;
	}

	public RamblersState(Coords coordinates) {
		
		this.coordinates = coordinates;
	}

	@Override
	boolean goalPredicate(Search searcher) {
		RamblersSearch mSearcher = (RamblersSearch) searcher;
		Coords target = mSearcher.getTarget();
		if(coordinates.getx() == target.getx() && coordinates.gety() == target.gety()) {
			return true;
		} else
			return false;
	}

	@Override
	ArrayList<SearchState> getSuccessors(Search searcher) {
		RamblersSearch rSearcher = (RamblersSearch) searcher;
		TerrainMap terrainMap = rSearcher.getTerrainMap();
		List<Integer> succXCoordinates = new ArrayList<>();
		List<Integer> succYCoordinates = new ArrayList<>();
		ArrayList<SearchState> successors = new ArrayList<SearchState>();
		
		int[][] tMap = terrainMap.getTmap();
		int xPredecessor = coordinates.getx()-1;
		int yPredecessor = coordinates.gety()-1;
		int xSuccessor = coordinates.getx()+1;
		int ySuccessor = coordinates.gety()+1;
		
		if(xPredecessor>=0) {
			succXCoordinates.add(xPredecessor);
		}
		if(xSuccessor<terrainMap.getWidth()) {
			succXCoordinates.add(xSuccessor);
		}
		
		if(yPredecessor>=0) {
			succYCoordinates.add(yPredecessor);
		}
		
		if(ySuccessor<terrainMap.getHeight()) {
			succYCoordinates.add(ySuccessor);
		}
			
		for(int yAxis : succYCoordinates) {
			int distanceY = tMap[yAxis][coordinates.getx()] - tMap[coordinates.gety()][coordinates.getx()];
			if((Integer)distanceY!=0) {
				successors.add((SearchState) new RamblersState(new Coords(yAxis,coordinates.getx()), distanceY+1));
			} else {
				successors.add((SearchState) new RamblersState(new Coords(yAxis,coordinates.gety()),1));
			}
		}
		for(int xAxis : succXCoordinates) {
			int distanceX = tMap[coordinates.gety()][xAxis] - tMap[coordinates.gety()][coordinates.getx()];
			if((Integer)distanceX!=0) {
				successors.add((SearchState) new RamblersState(new Coords(coordinates.gety(),xAxis), distanceX+1 ));
			} else {
				successors.add((SearchState) new RamblersState(new Coords(coordinates.gety(),xAxis),1));
			}
		}
		
		return successors;
	}

	@Override
	boolean sameState(SearchState n2) {
		RamblersState  ramblersState = (RamblersState) n2;
		if(coordinates.getx()==ramblersState.getCoordinates().getx() && coordinates.gety() == ramblersState.getCoordinates().gety()) {
			return true;
		} else {
			return false;
		}
	}
	
	String ToString() {
		return "Position: ( " +coordinates.getx()+ " , "+coordinates.gety()+ " ) ";
	}

}
