package prog2;

public class Action {
	
	Coordinate position1;
	Coordinate position2;
	
	Action(int[] locations){
		position1 = new Coordinate(locations[0], locations[1]);
		position2 = new Coordinate(locations[2], locations[3]);
	}
	Action(Coordinate pos1, Coordinate pos2){
		position1 = pos1;
		position2 = pos2;
	}
	
	@Override
	public String toString(){
		if(this.position1 != null && this.position2 != null){
			return "(move " + this.position1.x + " " + this.position1.y + " " + this.position2.x + " " + this.position2.y + ")";
		}
		return "noop";
	}
	
	public int[] tolist(){
		int[] list = new int[4];
		
		list[0] = this.position1.x;
		list[1] = this.position1.y;
		list[2] = this.position2.x;
		list[3] = this.position2.y;
		
		return list;
	}
	@Override
	public boolean equals(Object obj){
		if(obj == null){
			return false;
		}
		if(!this.getClass().isAssignableFrom(obj.getClass())){
			return false;
		}
		
		final Action comparison = (Action) obj;
		
		if(!this.position1.equals(comparison.position1) || !this.position2.equals(comparison.position2)){
			return false;
		}
		
		return true;
	}
	@Override
	public int hashCode(){
		return this.position1.hashCode()*21143 ^ this.position2.hashCode()*45127;
	}
}
