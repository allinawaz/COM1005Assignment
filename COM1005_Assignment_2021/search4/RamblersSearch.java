
public class RamblersSearch extends Search {
	private TerrainMap terrainMap;
	private Coords target;
	
	
	public RamblersSearch(TerrainMap terrainMap, Coords target) {
		this.terrainMap = terrainMap;
		this.target = target;
	}
	public TerrainMap getTerrainMap() {
		return terrainMap;
	}
	public void setTerrainMap(TerrainMap terrainMap) {
		this.terrainMap = terrainMap;
	}
	public Coords getTarget() {
		return target;
	}
	public void setTarget(Coords target) {
		this.target = target;
	}
	
	

}
