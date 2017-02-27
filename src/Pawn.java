package prog2;

public class Pawn {

	private Coordinate location;
	private String color;
	
	public void setLocation(Coordinate coord){
		this.location = coord;
	}
	
	public void setColor(String col){
		this.color = col;
	}
	
	public Coordinate getLocation(){
		return this.location;
	}
	
	public String getColor(){
		return this.color;
	}
	
	Pawn(Coordinate coordinate, String pawnColor){
		location = coordinate;
		color = pawnColor;
	}
	/*	Is not used
	public void move(String action){
		if(color.equals("white")){
		
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
		else{
			if(action.equals("forward")){
				int xCoord = this.location.x;
				int yCoord = this.location.y -1;
				
				this.location = new Coordinate(xCoord, yCoord);
			}
			else if(action.equals("capture_right")){
				int xCoord = this.location.x +1;
				int yCoord = this.location.y -1;
				
				this.location = new Coordinate(xCoord, yCoord);
			}
			else if(action.equals("capture_left")){
				int xCoord = this.location.x -1;
				int yCoord = this.location.y -1;
				
				this.location = new Coordinate(xCoord, yCoord);
			}
		}
	}
	*/
	
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
		return this.location.hashCode()*21143 ^ this.color.hashCode()*45127;
	}
	
}
