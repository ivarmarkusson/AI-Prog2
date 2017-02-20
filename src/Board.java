package prog2;


import java.util.HashMap;

public class Board {
	
	HashMap<Coordinate, Pawn> whitePawns;
	HashMap<Coordinate, Pawn> blackPawns;
	
	int width;
	int length;
	String role;
	int time;
	State currentState;
	
	Board(int tableWidth, int tableLength, String playersRole, int playClock){
		width = tableWidth;
		length = tableLength;
		role = playersRole;
		time = playClock;
		currentState = new State(this, null, 0);
				
		HashMap<Coordinate, Pawn> initialWhite = new HashMap<Coordinate, Pawn>();
		HashMap<Coordinate, Pawn> initialBlack = new HashMap<Coordinate, Pawn>();
		
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < width; j++){
				
				String color;
				int yCoord;
				
				switch(i){
					case 0:
					case 1:
						color = "white";
						yCoord = i;
						break;
					case 2:
						color = "black";
						yCoord = length-2;
						break;
					case 3:
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
	
	Board(Board copy){
		this.width = copy.width;
		this.length = copy.length;
		this.role = copy.role;
		this.time = copy.time;
		this.currentState = copy.currentState;
		this.whitePawns = new HashMap<Coordinate, Pawn>(copy.whitePawns);
		this.blackPawns = new HashMap<Coordinate, Pawn>(copy.blackPawns);
	}
	
	public Board update(Action action, String playerRole){
		Board newBoard = new Board(this);
		
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
		newBoard.currentState = new State(newBoard, null, 0);
		return newBoard;
	}
	
	/*	Is not used
	public void capture(Pawn pawn, String action){
		if(action.equals("turn_right")){
			pawn.move("turn_right");
			
		}
		else if(action.equals("turn_left")){
			
		}
	}
	*/
	
	@Override
	public String toString(){
		String builder = "";
		for(int i = 0; i < this.length; i++){
			for(int j = 0; j < this.width; j++){
				if(this.whitePawns.containsKey(new Coordinate(j,i))){
					builder += "| W |";
				}
				else if(this.blackPawns.containsKey(new Coordinate(j,i))){
					builder += "| B |";
				}
				else{
					builder += "| _ |";
				}
			}
			builder += "\n";
		}
		return builder;
	}
}
