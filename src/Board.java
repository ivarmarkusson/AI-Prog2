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
				
		HashMap<Coordinate, Pawn> initialWhite = new HashMap<Coordinate, Pawn>();
		HashMap<Coordinate, Pawn> initialBlack = new HashMap<Coordinate, Pawn>();
		
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < width; j++){
				
				String color;
				int yCoord;
				
				switch(i){
					case 0:
						color = "white";
						yCoord = i;
						break;
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
						break;
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
		currentState = new State(this);
	}
	
	Board(Board copy){
		this.width = copy.width;
		this.length = copy.length;
		this.role = copy.role;
		this.time = copy.time;
		
		State newState = new State(copy);
		this.currentState = newState;
		
		this.whitePawns = new HashMap<Coordinate, Pawn>(copy.whitePawns);
		this.blackPawns = new HashMap<Coordinate, Pawn>(copy.blackPawns);
	}
	
	public Board update(Action action, String playerRole){
		
		Board newBoard = new Board(this);
		Coordinate coord1 = new Coordinate(action.getPosition1().x, action.getPosition1().y);
		Coordinate coord2 = new Coordinate(action.getPosition2().x, action.getPosition2().y);
		
		if(playerRole.equals("white")){
			Pawn curr = newBoard.whitePawns.remove(coord1);
			if(newBoard.blackPawns.containsKey(action.getPosition2())){
				newBoard.blackPawns.remove(coord2);
			}
			curr.setLocation(action.getPosition2());
			newBoard.whitePawns.put(action.getPosition2(), curr);
		}
		else{
			Pawn curr = newBoard.blackPawns.remove(coord1);
			if(newBoard.whitePawns.containsKey(action.getPosition2())){
				newBoard.whitePawns.remove(coord2);
			}
			curr.setLocation(action.getPosition2());
			newBoard.blackPawns.put(action.getPosition2(), curr);
		}
		newBoard.currentState = new State(newBoard);
		return newBoard;
	}
	
	@Override
	public String toString(){
		String[] builder = new String[this.length];
		for(int i = 0; i < this.length; i++){
			String stringBuilder = "";
			for(int j = 0; j < this.width; j++){
				if(this.whitePawns.containsKey(new Coordinate(j,i))){
					stringBuilder += "| W |";
				}
				else if(this.blackPawns.containsKey(new Coordinate(j,i))){
					stringBuilder += "| B |";
				}
				else{
					stringBuilder += "| _ |";
				}
			}
			builder[i] = stringBuilder;
		}
		
		String result = "";
		
		for(int i = this.length -1; i >= 0; i--){
			result += builder[i];
			result += "\n";
		}
		
		return result;
	}
}
