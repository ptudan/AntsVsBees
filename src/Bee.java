
public class Bee extends Insect {
	public String name;
	private boolean inHive;
	private boolean moved = false;
	public boolean alive = true;

	public Bee(int a, int xVal, int yVal) {
		super(a, xVal, yVal);
		name = "Bee";
		inHive = true;
		
	}
	
	public void sting(Ant a){
		a.reduceArmor(1);
	}
	
	public void move(int y1){
		x += y1;
		y -= 1;
		moved = true;
	}
	
	public void releaseDown(){
		x +=1;
		inHive = false;
		moved = true;
	}
	
	public void releaseUp(){
		x -=1;
		inHive = false;
		moved = true;
	}
	
	public boolean inHive(){
		return inHive;
	}
	
	public void makeMoved(){
		moved = true;
	}
	
	public void resetMovement(){
		moved = false;
	}
	
	public boolean hasMoved(){
		return moved;
	}
	
	public String toString(){
		return("Bee, Armor: " + armor);
	}

}
