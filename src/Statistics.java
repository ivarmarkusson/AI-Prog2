package prog2;

public class Statistics {
	
	public int stateExpansions;
	public int currentDepthLimit;
	public long currentIterationSearchTime;
	public long totalRuntime;
	
	
	public Statistics(){
		stateExpansions = 0;
		currentDepthLimit = 0;
		currentIterationSearchTime = 0;
		totalRuntime = 0;
	}
	
	public double nodesPerSecound(){
		return stateExpansions / totalRuntime;
	}
	
}


//Keep track of and output the number of state expansions, current depth limit of your iterative
//deepening loop and runtime of the search for each iteration of iterative deepening and in
//total. These numbers are very useful to compare with others or between different version
//of your code to see whether your search is fast (many nodes per second) and the pruning
//works well (high depth, but fewer expanded nodes).