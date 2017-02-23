//package prog2;

public class Coordinate {
	
	Coordinate(int _x, int _y){
		x = _x;
		y = _y;
	}
	
	public int x;
	public int y;
	
	/*
	public Coordinate forward(String orientation){
		Coordinate next = new Coordinate(this.x, this.y);
		
		if(orientation == "NORTH"){
			next.y++;
		}
		else if(orientation == "SOUTH"){
			next.y--;
		}
		else if(orientation == "EAST"){
			next.x++;
		}
		else if(orientation == "WEST"){
			next.x--;
		}
		
		return next;
	}
	*/
	
	@Override
	public String toString(){
		return "( " + this.x + ", " + this.y + ")";
	}
	
	@Override
	public int hashCode(){
		return (this.x*21143) ^ (this.y*45127);
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj == null){
			return false;
		}
		else if(!Coordinate.class.isAssignableFrom(obj.getClass())){
	        return false;
	    }
		
		Coordinate comparison = (Coordinate) obj;
		
		if(this.x != comparison.x){
			return false;
		}
		else if(this.y != comparison.y){
			return false;
		}
		else{
			return true;
		}
	}
	
}
