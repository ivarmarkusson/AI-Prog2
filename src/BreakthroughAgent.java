package prog2;


public class BreakthroughAgent implements Agent{

	Board board;
	boolean turn;
	
	@Override
	public void init(String role, int width, int height, int playclock) {
		// TODO Auto-generated method stub
		board = new Board(width, height, role, playclock);
		turn = !role.equals("white");
		System.out.println(board.toString());
	}

	@Override
	public String nextAction(int[] lastmove) {
		
		if(lastmove != null && !turn){
			Action lastTurn = new Action(lastmove);
    		String roleOfLastPlayer;
    		
    		if(board.role.equals("white")){
    			roleOfLastPlayer = "black";
    		} 
    		else{
    			roleOfLastPlayer = "white";
    		}
   			System.out.println("LAST MOVE: " + roleOfLastPlayer + lastTurn.toString());
   			board = board.update(new Action(lastmove), roleOfLastPlayer);
		}
		
		
		turn = !turn;
		if(turn){	
			long endTime = System.currentTimeMillis() + board.time * 1000;
			
			AlphaBetaSearch alphaBetaSearch = new AlphaBetaSearch();
			Action nextMove = alphaBetaSearch.search(endTime, board.currentState, Integer.MIN_VALUE, Integer.MAX_VALUE);
			
			board = board.update(nextMove, board.role);
			
			System.out.println("next move: " + nextMove.toString());
			System.out.println("Possition1: " + nextMove.position1.toString());
			System.out.println("Possition2: " + nextMove.position2.toString());
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
