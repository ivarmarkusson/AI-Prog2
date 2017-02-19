package prog2;

public class AlphaBetaSearch {

	Board board;
	
	AlphaBetaSearch(Board initialBoard){
		board = initialBoard;
	}
	
	public Action search(long time, State state, int alpha, int beta){
		//Time out
		if(System.currentTimeMillis() <= time){
			return state.firstActionToState;
		}
		
		//no possible move
		if(state.legalActions() == null){
			return new Action(null, null);
		}
		
		int bestScore = Integer.MIN_VALUE;
		
		//loop through successor states and call recursively
		for(Action action : state.legalActions()){
			State succesorState = new State(board.update(action, board.role), state.firstActionToState);
			if(succesorState.firstActionToState == null){
				succesorState.firstActionToState = action;
			}
			
			Action value = search(time-1, succesorState, -beta, -alpha);
			
			bestScore = Integer.max(bestScore, state.getScoreFromAction(value));
			
			if(bestScore > alpha){
				alpha = bestScore;
				if(alpha >= beta){break;}
			}
		}
		
		return state.firstActionToState;
	}
}
