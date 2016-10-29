
public class Harvester extends Ant {

	public Harvester(int xVal, int yVal) {
		super(1, xVal, yVal, 0, 1, "Harvester");
	}
	
	public String toString(){
		return("H " + armor);
	}

}
