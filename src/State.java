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
	
	public int getScoreFromAction(Action action){
			
			if(board.role.equals("white")){
				if(board.blackPawns.containsKey(action.position2)){
					score++;
					return score;
				}
				if(this.goalState()){
					score += 100;
					return score;
				}
			}
			else{
				if(board.whitePawns.containsKey(action.position2)){
					score++;
					return score;
				}
				if(goalState()){
					score += 100;
					return score;
				}
			}
			
			return score;
			
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
				if(pawn.y == board.length){
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
				if(!board.blackPawns.containsKey(new Coordinate(coord.x, coord.y +1)) || !board.whitePawns.containsKey(new Coordinate(coord.x, coord.y +1))){
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
				if(!board.blackPawns.containsKey(new Coordinate(coord.x, coord.y -1)) || !board.whitePawns.containsKey(new Coordinate(coord.x, coord.y -1))){
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
