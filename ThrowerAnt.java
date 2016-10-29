
public class ThrowerAnt extends Ant {

	public ThrowerAnt(int xVal, int yVal) {
		super(2, xVal, yVal, 1, 0, "Thrower");
	}
	
	public void throwsAt(Bee b){
		b.reduceArmor(damage);
	}
	
	public String toString(){
		return("T " + armor);
	}

}
