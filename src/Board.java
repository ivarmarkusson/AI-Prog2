package prog2;


import java.util.HashMap;

public class Board {
	
	HashMap<Coordinate, Pawn> whitePawns;
	HashMap<Coordinate, Pawn> blackPawns;
	int width;
	int length;
	String role;
	int time;
	State initialState;
	
	Board(int tableWidth, int tableLength, String playersRole, int playClock){
		width = tableWidth;
		length = tableLength;
		role = playersRole;
		time = playClock;
		initialState = new State(this, null);
				
		HashMap<Coordinate, Pawn> initialWhite = new HashMap<Coordinate, Pawn>();
		HashMap<Coordinate, Pawn> initialBlack = new HashMap<Coordinate, Pawn>();
		
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < width; j++){
				
				String color;
				int yCoord;
				
				switch(i){
					case 1:
					case 2:
						color = "white";
						yCoord = i;
						break;
					case 3:
						color = "black";
						yCoord = length-2;
						break;
					case 4:
						color = "black";
						yCoord = length-1;
						break;
					default:
						color = "";
						yCoord = -1;
				}
				
				Coordinate location = new Coordinate(j, yCoord);
				Pawn next = new Pawn(location, color);
				
				if(color.equals("white")){
					initialWhite.put(location, next);
				}
				else{
					initialBlack.put(location, next);
				}
				
				
			}
		}
		whitePawns = initialWhite;
		blackPawns = initialBlack;
	}
	
	public Board update(Action action, String playerRole){
		Board newBoard = new Board(this.width, this.length, this.role, this.time);
		
		if(playerRole.equals("white")){
			Pawn curr = newBoard.whitePawns.remove(action.position1);
			if(newBoard.blackPawns.containsKey(action.position2)){
				newBoard.blackPawns.remove(action.position2);
			}
			curr.location = action.position2;
			newBoard.whitePawns.put(action.position2, curr);
		}
		else{
			Pawn curr = newBoard.blackPawns.remove(action.position1);
			if(newBoard.whitePawns.containsKey(action.position2)){
				newBoard.whitePawns.remove(action.position2);
			}
			curr.location = action.position2;
			newBoard.blackPawns.put(action.position2, curr);
		}
		return newBoard;
	}
	
	public void capture(Pawn pawn, String action){
		if(action.equals("turn_right")){
			pawn.moves("turn_right");
			
		}
		else if(action.equals("turn_left")){
			
		}
	}
	
	
}
