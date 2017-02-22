package prog2;

import java.util.ArrayList;
import java.util.List;

public class State {
	public Board board;
	public Action firstActionToState;
	private String results;
	
	State(Board b){
		board = b;
	}
	
	public boolean isTerminal(){
		if(this.goalState()){
			this.results = "win";
			return true;
		}
		
		if(this.legalActions().isEmpty() || this.board.blackPawns.isEmpty() || this.board.whitePawns.isEmpty()){
			this.results = "draw";
			return true;
		}
		
		if(board.role.equals("white")){
			for(Coordinate pawn : board.blackPawns.keySet()){
				if(pawn.y == 0){
					this.results = "lost";
					return true;
				}
			}
		}
		else{
			for(Coordinate pawn : board.whitePawns.keySet()){
				if(pawn.y +1 == board.length){
					this.results = "lost";
					return true;
				}
			}
		}
		
		return false;
	}
	
	public int evaluate(){
		if(this.isTerminal()){
			if(this.results == "won"){
				return 100;
			}
			else if(this.results == "lost"){
				return 0;
			}
			else if(this.results == "draw"){
				return 50;
			}
		}
		
		int maxWhite = 0;
		int maxBlack = 100;
		
		for(Coordinate coord : this.board.whitePawns.keySet()){
			if(coord.y > maxWhite){
				maxWhite = coord.y;
			}
		}
		
		for(Coordinate coord : this.board.blackPawns.keySet()){
			if(coord.y < maxBlack){
				maxBlack = coord.y;
			}
		}
		
		maxBlack = (board.length -1) - maxBlack;
		
		if(this.board.role.equals("white")){
			return 50 + maxWhite - maxBlack;
		}
		else{
			return 50 + maxBlack - maxWhite;
		}
		

	}

	public State succesorState(Action action){
		
		//TODO Need to implement this within this state not by updating board!?
		State succesorState = new State(board.update(action, board.role));
		
		
		return succesorState;
	}
	
	public boolean goalState(){
		if(board.role.equals("white")){
			for(Coordinate pawn : board.whitePawns.keySet()){
				if(pawn.y == board.length -1){
					return true;
				}
			}
			return false;
		}
		else{
			for(Coordinate pawn : board.blackPawns.keySet()){
				if(pawn.y == 0){
					return true;
				}
			}
			return false;
		}
	}
	
	public List<Action> legalActions(){
		String role = board.role;
		List<Action> legalActions = new ArrayList<Action>();
		
		if(role.equals("white")){	//White
			for(Coordinate coord : board.whitePawns.keySet()){	//move forward
				if(this.board.length -1 <= coord.y){	//If pawn goes out of the board
					//do nothing
					continue;
				}
				if(!board.blackPawns.containsKey(new Coordinate(coord.x, coord.y +1)) && !board.whitePawns.containsKey(new Coordinate(coord.x, coord.y +1))){
					Action forward = new Action(coord, new Coordinate(coord.x, coord.y +1));
					legalActions.add(forward);
				}
				if(board.blackPawns.containsKey(new Coordinate(coord.x +1, coord.y +1))){	//capture right pawn
					Action right = new Action(coord, new Coordinate(coord.x +1, coord.y +1));
					legalActions.add(right);
				}
				if(board.blackPawns.containsKey(new Coordinate(coord.x -1, coord.y +1))){	//capture left pawn
					Action left = new Action(coord, new Coordinate(coord.x -1, coord.y +1));
					legalActions.add(left);
				}
			}
		}
		else{	//Black
			for(Coordinate coord : board.blackPawns.keySet()){	//move forward
				if(0 >= coord.y){	//If pawn goes out of the board
					//do nothing
					continue;
				}
				if(!board.blackPawns.containsKey(new Coordinate(coord.x, coord.y -1)) && !board.whitePawns.containsKey(new Coordinate(coord.x, coord.y -1))){
					Action forward = new Action(coord, new Coordinate(coord.x, coord.y -1));
					legalActions.add(forward);
				}
				if(board.whitePawns.containsKey(new Coordinate(coord.x +1, coord.y -1))){	//capture right pawn
					Action right = new Action(coord, new Coordinate(coord.x +1, coord.y -1));
					legalActions.add(right);
				}
				if(board.whitePawns.containsKey(new Coordinate(coord.x -1, coord.y -1))){	//capture left pawn
					Action left = new Action(coord, new Coordinate(coord.x -1, coord.y -1));
					legalActions.add(left);
				}
			}
		}
		
		return legalActions;
	}
}
