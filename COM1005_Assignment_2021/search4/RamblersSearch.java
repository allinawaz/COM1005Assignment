
public class RamblersSearch extends Search {
	private TerrainMap terrainMap;	// map to explore
	private Coords target; 			// goal coordinates
	private String heuristicType = "manhattan";
	//Ramblers Search Constructor
	public RamblersSearch(TerrainMap terrainMap, Coords target) {
		this.terrainMap = terrainMap;
		this.target = target;
	}
	
	//accessor methods of RamblerSearch
	public TerrainMap getTerrainMap() {
		return terrainMap;
	}
	
	public Coords getTarget() {
		return target;
	}
	

	public String getHeuristicType() {
		return heuristicType;
	}
	
	public void setHeuristicType(String heuristicType) {
		this.heuristicType = heuristicType;
	}
	

}
