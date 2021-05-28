

public class RunRamblersBB {

	public static void main(String[] args) {
		
		TerrainMap terrainMap = new TerrainMap("search4/tmc.pgm");
		Coords  target = new Coords(10,10);

		RamblersSearch searcher = new RamblersSearch(terrainMap, target);
		SearchState initState = (SearchState) new RamblersState(new Coords(0,1),3);
		String resultBranchAndBound = searcher.runSearch(initState,"branchAndBound");
		System.out.println("Total Cost is: "+resultBranchAndBound);

	}

}
