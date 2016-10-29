import java.util.LinkedList;

public class Place {
	private int x;
	private int y;
	private Ant a;
	private int bc;
	private Boolean isHive = false;
	
	public Place(int x1, int y1){
		x = x1;
		y = y1;
	}
	
	public void setAnt(Ant a1){
		a = a1;
	}
	
	public void addBee(){
		bc +=1;
	}
	
	public void checkAnt(){
		//checks if ant armor is 0, removes
		if(a.armor==0){
			a = null;
		}
	}
	
	public void makeHive(){
		isHive = true;
	}
	
	public boolean isH(){
		return isHive;
	}
	
	public Ant getAnt(){
		if(a!=null){
			return a;
		}
		return null;
	}
	
	public void removeBee(){
		bc -=1;
	}
	
	public boolean isEmpty(){
		if(bc == 0){
			if(a==null){
				if(isHive == false){
					return true;
				}
			}
		}
		return false;
	}
	
	public String toString(){
		if(isHive){
			return("[ H ]");
		}
		else if(a!=null){
			return "["+a.toString()+"]";
		}
		else if(bc != 0){
			String ret = "B";
			ret += bc;
			if(ret.length()<3){
				ret = "[B " + bc+"]";
			}
			else{
				ret = "[B"+ bc +"]";
			}
			return ret;
		}
		else{
			return("[   ]");
		}
	}
	
}
