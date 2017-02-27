package prog2;


public class BreakthroughAgent implements Agent{

	Board board;
	boolean turn;
	private int depthEstimate;
	Statistics statistics;
	
	@Override
	public void init(String role, int width, int height, int playclock) {
		board = new Board(width, height, role, playclock);
		turn = !role.equals("white");
		depthEstimate = ((board.length -1) * 2 + (board.length -2) * 2) * board.width +1;
		statistics = new Statistics();
	}

	@Override
	public String nextAction(int[] lastmove) {
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
    		depthEstimate--;
		}
		
		
		turn = !turn;
		if(turn){	
			
			long startTime = System.currentTimeMillis();
			long endTime = startTime + board.time * 1000;
						
			AlphaBetaSearch alphaBetaSearch = new AlphaBetaSearch(board, statistics);
			Action nextMove = new Action(null, null);
			
			try{
				for(int depth = 1; depth <= depthEstimate; depth++){
					statistics.currentDepthLimit = depth;
					long iterationStartTime = System.currentTimeMillis();
					Action temp = alphaBetaSearch.rootSearch(depth, endTime, board.currentState, 0, 100);
					
					if(!(temp.getPosition1() == null) && !(temp.getPosition2() == null)){
						nextMove = temp;
					}
					
					statistics.currentIterationSearchTime = (System.currentTimeMillis() - iterationStartTime) / 1000;
				}
				
				
				statistics.totalRuntime = (System.currentTimeMillis() - startTime) / 1000;
				board = board.update(nextMove, board.role);
				depthEstimate--;
				return nextMove.toString();
			}
			catch(TimeOutException e){
				statistics.totalRuntime = (System.currentTimeMillis() - startTime) / 1000;				
				board = board.update(nextMove, board.role);
				depthEstimate--;
				return nextMove.toString();
			}
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
