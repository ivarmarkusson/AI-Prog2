package prog2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class State {
	public Board board;
	private String results;
	
	State(Board b){
		board = b;
	}
	
	public boolean isTerminal(){
		if(this.goalState()){
			this.results = "won";
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
				if(pawn.y == board.length -1){
					this.results = "lost";
					return true;
				}
			}
		}
		
		return false;
	}
	
	private boolean opponentCanWinInNextMove(String colorOfOpponent, Coordinate pawn){
		if(colorOfOpponent.equals("white")){
			if(pawn.y == (board.length -1) -1){
				Coordinate forward = new Coordinate(pawn.x, pawn.y +1);
				Coordinate right = new Coordinate(pawn.x +1, pawn.y +1);
				Coordinate left = new Coordinate(pawn.x -1, pawn.y +1);
				
				if(!board.blackPawns.containsKey(forward)){	//Case: there is no pawn in the way
					return true;
				}
				else if(board.blackPawns.containsKey(right) || board.blackPawns.containsKey(left)){ //case: there is a pawn that the enemy can capture in next turn
					return true;
				}
				else{	//case: there is a pawn in the way
					return false;
				}
			}
		}
		else{
			if(pawn.y == 1){
				Coordinate forward = new Coordinate(pawn.x, pawn.y -1);
				Coordinate right = new Coordinate(pawn.x +1, pawn.y -1);
				Coordinate left = new Coordinate(pawn.x -1, pawn.y -1);
				
				if(!board.whitePawns.containsKey(forward)){	//Case: there is no pawn in the way
					return true;
				}
				else if(board.whitePawns.containsKey(right) || board.whitePawns.containsKey(left)){ //case: there is a pawn that the enemy can capture in next turn
					return true;
				}
				else{	//case: there is a pawn in the way
					return false;
				}
			}
		}
		
		return false;
	}
	public int evaluate(){
		if(this.isTerminal()){
			if(this.results.equals("won")){
				return 100;
			}
			else if(this.results.equals("lost")){
				return 0;
			}
			else if(this.results.equals("draw")){
				return 50;
			}
		}
		
		int maxWhite = 0;
		int maxBlack = 100;
		
		for(Coordinate coord : this.board.whitePawns.keySet()){
			if(board.role.equals("black") && opponentCanWinInNextMove("white", coord)){
				return 1;
			}
			if(coord.y > maxWhite){
				maxWhite = coord.y;
			}
		}
		
		for(Coordinate coord : this.board.blackPawns.keySet()){
			if(board.role.equals("white") && opponentCanWinInNextMove("black", coord)){
				return 1;
			}
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
		Board newBoard = board.update(action, board.role);
		State succesorState = new State(newBoard);
		
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
			for(Coordinate coordinate : board.whitePawns.keySet()){	//move forward
				if(coordinate.y >= board.length){
					continue;
				}
				
				Coordinate forwardCoord = new Coordinate(coordinate.x, coordinate.y +1);
				
				if(!board.blackPawns.containsKey(forwardCoord) && !board.whitePawns.containsKey(forwardCoord)){
					Coordinate currentCoord = new Coordinate(coordinate.x, coordinate.y);
					Action forward = new Action(currentCoord, forwardCoord);
					legalActions.add(forward);
				}
				
				Coordinate rightCoord = new Coordinate(coordinate.x +1, coordinate.y +1);
				
				if(board.blackPawns.containsKey(rightCoord)){	//capture right pawn
					Coordinate currentCoord = new Coordinate(coordinate.x, coordinate.y);
					Action right = new Action(currentCoord, rightCoord);
					legalActions.add(right);
				}
				
				Coordinate leftCoord = new Coordinate(coordinate.x -1, coordinate.y +1);
				
				if(board.blackPawns.containsKey(leftCoord)){	//capture left pawn
					Coordinate currentCoord = new Coordinate(coordinate.x, coordinate.y);
					Action left = new Action(currentCoord, leftCoord);
					legalActions.add(left);
				}
			}
		}
		else{	//Black
			for(Coordinate coordinate : board.blackPawns.keySet()){	//move forward
				
				if(coordinate.y < 0){
					continue;
				}
				
				Coordinate forwardCoord = new Coordinate(coordinate.x, coordinate.y -1);
				
				if(!board.blackPawns.containsKey(forwardCoord) && !board.whitePawns.containsKey(forwardCoord)){
					Coordinate currCoord = new Coordinate(coordinate.x, coordinate.y);
					Action forward = new Action(currCoord, forwardCoord);
					legalActions.add(forward);
				}
				
				Coordinate rightCoord = new Coordinate(coordinate.x +1, coordinate.y -1);
				
				if(board.whitePawns.containsKey(rightCoord)){	//capture right pawn
					Coordinate currCoord = new Coordinate(coordinate.x, coordinate.y);
					Action right = new Action(currCoord, rightCoord);
					legalActions.add(right);
				}
				
				Coordinate leftCoord = new Coordinate(coordinate.x -1, coordinate.y -1);
				
				if(board.whitePawns.containsKey(leftCoord)){	//capture left pawn
					Coordinate currCoord = new Coordinate(coordinate.x, coordinate.y);
					Action left = new Action(currCoord, leftCoord);
					legalActions.add(left);
				}
			}
		}
		
		if(role.equals("white")){
			Collections.sort(legalActions, new Comparator<Action>(){
				public int compare(Action a, Action b){
					 if(a == null || b == null){
						 return 0;
					 }
					 
					 if(a.getPosition1().y == b.getPosition1().y){
						return 0;
					 }
					 else if(a.getPosition1().y < b.getPosition1().y){
							return -1;
					 }
					 else if(a.getPosition1().y > b.getPosition1().y){
							return 1;
					 }
					 //should not happen
					 return 0;
				}
			});
		}
		else{
			Collections.sort(legalActions, new Comparator<Action>(){
				public int compare(Action a, Action b){
					 if(a == null || b == null){
						 return 0;
					 }
					 
					 if(a.getPosition1().y == b.getPosition1().y){
						return 0;
					 }
					 else if(a.getPosition1().y < b.getPosition1().y){
							return 1;
					 }
					 else if(a.getPosition1().y > b.getPosition1().y){
							return -1;
					 }
					 //should not happen
					 return 0;
				}
			});
		}
		return legalActions;
	}
}
