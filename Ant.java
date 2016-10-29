
public class Ant extends Insect {
	public int damage;
	public String name;
	public int gather;

	public Ant(int a, int xVal, int yVal, int d, int g, String n) {
		super(a, xVal, yVal);
		damage = d;
		gather = g;
		name = n;
	}
	
	public int getGather(){
		return gather;
	}
	
	public int getDamage(){
		return damage;
	}
	
	public boolean isAnt(){
		return true;
	}

}
