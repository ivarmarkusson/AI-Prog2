package prog2;

public class AlphaBetaSearch {

	Board board;
	
	AlphaBetaSearch(){
		//board = initialBoard;
	}
	
	public Action search(long time, State state, int alpha, int beta){
		//Time out
		if(System.currentTimeMillis() >= time){
			System.out.println("TIME OUT");
			return state.firstActionToState;
		}
		
		/*
		//no possible move
		if(state.legalActions() == null){
			return new Action(null, null);
		}
		*/
		
		int bestScore = Integer.MIN_VALUE;
		
		//loop through successor states and call recursively
		for(Action action : state.legalActions()){
			System.out.println("LOOP ENTERED");
			//TODO bug in update it initalizes the board every time
			State succesorState = state.succesorState(action);
			
			if(succesorState.firstActionToState == null){
				System.out.println("1: " + action.toString());
				succesorState.firstActionToState = action;
				
			}
			
			int score = succesorState.getScoreFromAction(action);
			
			//Action value = search(time, succesorState, -beta, -alpha);
			
			bestScore = Integer.max(bestScore, score);
			
			if(bestScore > alpha){
				System.out.println("2");
				alpha = bestScore;
				if(alpha >= beta){break;}
			}
		}
		
		System.out.println(state.board.toString());
		if(state.firstActionToState == null){
			System.out.println("First action is null");
		}
		System.out.println("LAST CALL: " + state.firstActionToState.toString());
		return state.firstActionToState;
	}
}
