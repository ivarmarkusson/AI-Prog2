package prog2;


public class BreakthroughAgent implements Agent{

	Board board;
	boolean turn;
	
	@Override
	public void init(String role, int width, int height, int playclock) {
		board = new Board(width, height, role, playclock);
		turn = !role.equals("white");
	}

	@Override
	public String nextAction(int[] lastmove) {

		System.out.println(board.toString());
		
		if(lastmove != null && !turn){
			Action lastTurn = new Action(new Coordinate(lastmove[0] -1, lastmove[1] -1), new Coordinate(lastmove[2] -1, lastmove[3] -1));

    		String roleOfLastPlayer;
    		
    		if(board.role.equals("white")){
    			roleOfLastPlayer = "black";
    		} 
    		else{
    			roleOfLastPlayer = "white";
    		}
   			
    		board = board.update(lastTurn, roleOfLastPlayer);
		}
		
		
		turn = !turn;
		if(turn){	
			long endTime = System.currentTimeMillis() + board.time * 1000;
			
			AlphaBetaSearch alphaBetaSearch = new AlphaBetaSearch(board);
			
			int depth = 50;
			//for(int i = 4; )
			Action nextMove = alphaBetaSearch.rootSearch(depth, endTime, board.currentState, 0, 100);
			
			board = board.update(nextMove, board.role);
			
			return nextMove.toString();
		} 
		else{
			return "noop";
		}
	}

	// TODO this method executes after a match, the use of it is to get the agent ready for next match.
	@Override
	public void cleanup() {
		// TODO Auto-generated method stub
		
	}

}
