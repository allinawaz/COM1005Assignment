import java.util.Random;

public class RunRamblersAstart {

	public static void main(String[] args) {

		TerrainMap terrainMap = new TerrainMap("search4/tmc.pgm");
		Coords target = new Coords(14,14);
		RamblersSearch searcher = new RamblersSearch(terrainMap, target);
		SearchState initState = (SearchState) new RamblersState(new Coords(0,0),0,0);
		String resultAstart = searcher.runSearch(initState, "AStar");
		System.out.println(resultAstart);
		
	}

}
