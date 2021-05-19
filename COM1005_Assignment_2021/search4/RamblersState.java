import java.util.ArrayList;

public class RamblersState extends SearchState {
	
	private Coords coordinates;

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	ArrayList<SearchState> getSuccessors(Search searcher) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	boolean sameState(SearchState n2) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
