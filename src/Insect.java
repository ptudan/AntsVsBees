
public class Insect {
	public int armor;
	public int x;
	public int y;
	
	public Insect(int a, int xVal, int yVal){
		armor = a;
		x = xVal;
		y = yVal;
	}
	
	public void reduceArmor(int r){
		armor = armor - r;
		if(armor < 0){
			x = -1;
			y = -1;
		}
	}
	
	public String toString(){
		return("Insect, Armor: " + armor + " x: " + x + " y: " + y);
	}
	
	public boolean isAnt(){
		return false;
	}
}
