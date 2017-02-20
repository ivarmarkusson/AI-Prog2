package prog2;

public class AlphaBetaSearch {


	
	AlphaBetaSearch(){	}
	
	public Action search(long time, State state, int alpha, int beta){
		//Time out
		if(System.currentTimeMillis() >= time){
			System.out.println("TIME OUT");
			return state.firstActionToState;
		}
		
		int bestScore = Integer.MIN_VALUE;
		
		//loop through successor states and calls this function recursively
		for(Action action : state.legalActions()){
			//System.out.println("Action: " + action.toString());
			State succesorState = state.succesorState(action);
			
			if(succesorState.firstActionToState == null){
				succesorState.firstActionToState = action;	
			}
			
			int score = -succesorState.getScoreFromAction(action);
			
			if(state.goalState()){
				//System.out.println(succesorState.board.toString());
				break;
			}
			
			Action value = search(time, succesorState, -beta, -alpha);
			
			bestScore = Integer.max(bestScore, score);
			
			if(bestScore > alpha){
				alpha = bestScore;
				if(alpha >= beta){break;}
			}
		}
		
		
		
		return state.firstActionToState;
	}
}
