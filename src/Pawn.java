package prog2;

public class Pawn {

	Coordinate location;
	String color;
	
	Pawn(Coordinate coordinate, String pawnColor){
		location = coordinate;
		color = pawnColor;
	}
	
	public void moves(String action){
		if(action.equals("forward")){
			int xCoord = this.location.x;
			int yCoord = this.location.y +1;
			
			this.location = new Coordinate(xCoord, yCoord);
		}
		else if(action.equals("capture_right")){
			int xCoord = this.location.x +1;
			int yCoord = this.location.y +1;
			
			this.location = new Coordinate(xCoord, yCoord);
		}
		else if(action.equals("capture_left")){
			int xCoord = this.location.x -1;
			int yCoord = this.location.y +1;
			
			this.location = new Coordinate(xCoord, yCoord);
		}
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj == null){
			return false;
		}
		if(!Pawn.class.isAssignableFrom(obj.getClass())){
			return false;
		}
		
		final Pawn comparison = (Pawn) obj;
		
		if(!comparison.location.equals(this.location)){
			return false;
		}
		if(!comparison.color.equals(this.color)){
			return false;
		}
		return true;
	}
	
	@Override
	public int hashCode(){
		return this.location.hashCode() * this.color.hashCode();
	}
	
}
