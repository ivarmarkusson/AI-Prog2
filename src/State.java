package prog2;

import java.util.ArrayList;
import java.util.List;

public class State {
	public Board board;
	public Action firstActionToState;
	public int score;
	
	State(Board b, Action action, int stateScore){
		board = b;
		firstActionToState = action;
		score = stateScore;
	}
	
	public boolean isTerminal(){
		if(this.goalState() || this.legalActions().isEmpty() || this.board.blackPawns.isEmpty() || this.board.whitePawns.isEmpty()){
			return true;
		}
		
		if(this.board.role.equals("white")){
			for(Coordinate coord : this.board.whitePawns.keySet()){
				if(coord.y == this.board.length -1){
					return true;
				}
			}
		}
		else{
			for(Coordinate coord : this.board.blackPawns.keySet()){
				if(coord.y == 0){
					return true;
				}
			}
		}
		return false;
	}
	public int setScoreFromAction(Action action){
			
			if(board.role.equals("white")){
				if(board.blackPawns.containsKey(action.position2)){
					this.score++;
					return this.score;
				}
				if(this.goalState()){
					this.score += 100;
					return this.score;
				}
			}
			else{
				if(board.whitePawns.containsKey(action.position2)){
					this.score++;
					return this.score;
				}
				if(goalState()){
					this.score += 100;
					return this.score;
				}
			}
			
			return this.score;
			
			// TODO 
			// +1 if capture
			// -1 if captured
			// + 100 if won
			// - 100 if lost
			// + 50 if draw
			// returns highest score
	}
	
	public State succesorState(Action action){
		
		//TODO Need to implement this within this state not by updating board!?
		State succesorState = new State(board.update(action, board.role), this.firstActionToState, this.score);
		
		
		return succesorState;
	}
	
	public boolean goalState(){
		if(board.role.equals("white")){
			for(Coordinate pawn : board.whitePawns.keySet()){
				if(pawn.y +1 == board.length){
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
				if(this.board.length <= coord.y +1){	//If pawn goes out of the board
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
				if(0 >= coord.y +1){	//If pawn goes out of the board
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
