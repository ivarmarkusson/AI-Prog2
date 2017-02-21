package prog2;

public class AlphaBetaSearch {

	Board board;
	
	
	AlphaBetaSearch(Board _board){
		board = _board;
	}
	
	public int search(long time, State state, int alpha, int beta){
		//Time out or terminale state
		if(state.isTerminal() || System.currentTimeMillis() >= time){
			return state.score;
		}
		
		int bestScore = Integer.MIN_VALUE;
		
		//loop through successor states and calls this function recursively
		for(Action action : state.legalActions()){
			
			State succesorState = state.succesorState(action);
			succesorState.setScoreFromAction(action);
			
			int value = -search(time, succesorState, -beta, -alpha);
			
			bestScore = Integer.max(bestScore, value);
			if(bestScore > alpha){
				alpha = bestScore;
				if(alpha >= beta){break;}
			}
		}	
		
		return bestScore;
	}
	

	public Action rootSearch(long time, State state, int alpha, int beta){
		
		//Time out or terminal state
		if(state.isTerminal() || System.currentTimeMillis() >= time){
			System.out.println("OTHER WRONG");
			return null;
		}
		
		int bestScore = Integer.MIN_VALUE;
		
		//loop through successor states and calls this function recursively
		for(Action action : state.legalActions()){
			System.out.println("Action: " + action.toString());
			State succesorState = state.succesorState(action);
			
			succesorState.firstActionToState = action;	
			
			succesorState.setScoreFromAction(action);
			
			int value = -search(time, succesorState, -beta, -alpha);
			System.out.print("Value: " + value);
			bestScore = Integer.max(bestScore, value);
			
			if(bestScore > alpha){
				alpha = bestScore;
				if(alpha >= beta){
					System.out.println("RIGHT");
					return succesorState.firstActionToState;
				}
			}
		}
		
		System.out.println("WRONG");
		//should not happen
		return null;
		
	}
}
