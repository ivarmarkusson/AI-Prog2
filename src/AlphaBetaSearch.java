package prog2;

public class AlphaBetaSearch {

	Board board;
	Statistics statistics;
	
	AlphaBetaSearch(Board _board, Statistics _statistics){
		board = _board;
		statistics = _statistics;
	}
	
	public int search(int depth, long time, State state, int alpha, int beta){
		//Time out or terminale state
		
		if(System.currentTimeMillis() >= time){
			throw new TimeOutException();
		}
		
		if(state.isTerminal() || depth <= 0){
			return state.evaluate();
		}
		
		int bestScore = Integer.MIN_VALUE;
		
		//loop through successor states and calls this function recursively
		for(Action action : state.legalActions()){
			statistics.stateExpansions++;
			State succesorState = state.succesorState(action);
			
			int value = -search(depth -1, time, succesorState, -beta, -alpha);
			
			bestScore = Integer.max(bestScore, value);
			if(bestScore > alpha){
				alpha = bestScore;
				if(alpha >= beta){
					break;
				}
			}
		}	
		
		return bestScore;
	}
	

	public Action rootSearch(int depth, long time, State state, int alpha, int beta){
		
		//Time out or terminal state
		if(state.isTerminal() || System.currentTimeMillis() >= time || depth <= 0){
			System.out.println("Terminal");
			System.out.println("terminal: " + state.isTerminal());
			System.out.println("time: " + (System.currentTimeMillis() >= time));
			System.out.println("depth: " + depth);
			return null;
		}
		
		int bestScore = Integer.MIN_VALUE;
		Action bestAction = new Action(null, null);
		
		//loop through successor states and calls this function recursively
		for(Action action : state.legalActions()){
			statistics.stateExpansions++;
			State succesorState = state.succesorState(action);
			
			if(succesorState.goalState()){
				return action;
			}
			
			int value = -search(depth -1, time, succesorState, -beta, -alpha);
			
			bestScore = Integer.max(bestScore, value);
			
			if(bestScore >= alpha){
				alpha = bestScore;
				bestAction = action;
			}

		}
		
		return bestAction;
		
	}
}
