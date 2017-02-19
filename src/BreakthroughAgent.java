package prog2;


public class BreakthroughAgent implements Agent{

	Board board;
	boolean turn;
	
	@Override
	public void init(String role, int width, int height, int playclock) {
		// TODO Auto-generated method stub
		board = new Board(width, height, role, playclock);
		turn = !role.equals("white");
	}

	@Override
	public String nextAction(int[] lastmove) {
		
		if(lastmove != null){
			Action lastTurn = new Action(lastmove);
    		String roleOfLastPlayer;
    		
    		if(turn && board.role.equals("white") || !turn && board.role.equals("black")){
    			roleOfLastPlayer = "white";
    		} 
    		else{
    			roleOfLastPlayer = "black";
    		}
   			System.out.println(roleOfLastPlayer + lastTurn.toString());
    		
   			board.update(new Action(lastmove), roleOfLastPlayer);
		}
		
		
		turn = !turn;
		if(turn){	
			long endTime = System.currentTimeMillis() + board.time * 1000;
			AlphaBetaSearch alphaBetaSearch = new AlphaBetaSearch(board);
			Action nextMove = alphaBetaSearch.search(endTime, board.initialState, Integer.MIN_VALUE, Integer.MAX_VALUE);
			
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
