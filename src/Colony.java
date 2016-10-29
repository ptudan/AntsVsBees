import java.util.Random;

import javax.swing.JOptionPane;

public class Colony {
	private int length;
	private Place[][] colony;
	private Boolean[] queens;
	private int food = 2;
	private int beeCount;
	private Bee[] bees;
	private int hive;
	
	public Colony(int l){
		length = l;
		colony = new Place[length][length];
		queens = new Boolean[length];
		Random rGen = new Random();
		
		for(int x = 0; x<length; x++){
			for(int y = 0; y<length; y++){
				colony[x][y] = new Place(x, y);
			}
			queens[x] = true;
		}
		
		hive = rGen.nextInt(length);
		colony[hive][length-1].makeHive();
		
		boolean retry = true;
		char difficulty = ' ';
		while(retry){
			String temp = JOptionPane.showInputDialog("Choose difficulty: [e] for easy (25 bees)"
					+ " [m] for medium (50 bees) or [h] for hard (75 bees): ");
			if(temp.length()>1){}
			else{
				difficulty = temp.charAt(0);
				if(difficulty == 'h'){
					retry = false;
					
				}
				else if(difficulty == 'm'){
					retry = false;
				}
				else if(difficulty == 'e'){
					retry = false;
				}
			}
		}
		
		if(difficulty == 'e'){
			beeCount = 25;
		}
		else if(difficulty == 'm'){
			beeCount = 50;
		}
		else{
			beeCount = 75;
		}
		makeBees();
	}
	
	
	private void harvFood(){
		for(int x = 0; x<length; x++){
			for(int y = 0; y<length; y++){
				Ant a = colony[x][y].getAnt();
				if(a!=null){
					food += a.getGather();
				}
			}
		}
	}
	
	private void makeBees(){
		bees = new Bee[beeCount];
		for(int i = 0; i<beeCount; i++){
			bees[i] = new Bee(1, hive, length-1);
		}
	}
	
	private void releaseBees(){
		if(beeCount<=0){
			return;
		}
		Random rGen = new Random();
		int release = rGen.nextInt(5)+1;
		while((beeCount-release<0)){
			release = rGen.nextInt(5)+1;
		}
		int direction = rGen.nextInt(2);
		if(hive == 0){
			for(int i = 0; i<release; i++){
				beeCount -=1;
				bees[beeCount].releaseDown();
				colony[1][length-1].addBee();
			}
		}
		else if(hive == length - 1){
			for(int i = 0; i<release; i++){
				beeCount -=1;
				bees[beeCount].releaseUp();
				colony[length-2][length-1].addBee();
			}
		}
		else{
			if(direction == 0){
				for(int i = 0; i<release; i++){
					beeCount -=1;
					bees[beeCount].releaseUp();
					colony[hive-1][length-1].addBee();
				}
			}
			else{
				for(int i = 0; i<release; i++){
					beeCount -=1;
					bees[beeCount].releaseDown();
					colony[hive+1][length-1].addBee();
				}
			}
		}
	}
	
	private void moveBees(){
		for(Bee b : bees){
			if(!b.inHive()){
				if(!b.hasMoved()){
				if(b.alive){
				final int x = b.x;
				final int y = b.y;
				Random rGen = new Random();
				int moveVal = rGen.nextInt(3) - 1;
				int count = 0;
				boolean checking = true;
				while(checking){
					count += 1;
					if(count == 500){
						System.out.println(x);
						System.out.println("fuck");
						break;
					}
					if((x+moveVal)<=4){
						if((x+moveVal) >= 0){
							checking = false;
						}
						else{
							moveVal = rGen.nextInt(3) - 1;
						}
					}
					else if((x+moveVal)>=0){
						if((x+moveVal)<=4){
							checking = false;
						}
						else 
							moveVal = rGen.nextInt(3) - 1;
					}
					else{
						moveVal = rGen.nextInt(3) - 1;
					}
				}
				int newX = x + moveVal;
				if(y==0){//if y is 0, next move will either leave board or kill queen
					queens[newX] = false; //kills queen if there
					colony[x][y].removeBee();
					b.alive = false; //leaves board
				}
				else{
					if(colony[newX]
							[y-1].getAnt()!=null){ //checks for ant
						b.sting(colony[newX][y-1].getAnt());//stings ant
						if(colony[newX][y-1].getAnt().name.equals("Thrower")){
							b.alive = false; //throwers will kill attacking bees
							colony[x][y].removeBee();
						}
						colony[newX][y-1].checkAnt(); //checks to see if ant's armor is 0
					}
					else{
						b.move(moveVal);
						colony[x]
								[y].removeBee();
						b.makeMoved();
						colony[newX]
								[y-1].addBee();
					}
				}
				
			}
			}
			}
		}
	}
	
	private void resetMoves(){
		for(Bee b : bees){
			if(!b.inHive()){
				b.resetMovement();
			}
		}
	}
	
	private void placeAnt(){
		if(food == 0){
			return;
		}
		boolean retry = true;
		char aType = ' ';
		while(retry){
			String temp = JOptionPane.showInputDialog("Enter Ant Type [h] for harvester (1 food)"
				+ " or [t] for thrower (2 food): ");
			if(temp.length()>1){}
			else{
			aType = temp.charAt(0);
			if(aType == 'h'){
				retry = false;
				
			}
			else if(aType == 't'){
				if(food>=2){
					retry = false;
				}
			}
			}
		}
		retry = true;
		while(retry){
			int x = Integer.parseInt(JOptionPane.showInputDialog("Enter an empty row value: "));
			int y = Integer.parseInt(JOptionPane.showInputDialog("Enter an empty col value: "));
			if(x >= length){}
			else if(x<0){}
			else if(y >= length){} //makes sure x and y coords are in array
			else if(y<0){}
			else if(!colony[x][y].isEmpty()){}//makes sure spot is open
			else{
				retry = false;
				if(aType == 'h'){
					food -= 1;
					Harvester a1 = new Harvester(x, y);
					colony[x][y].setAnt(a1);
				}
				else{
					food -= 2;
					ThrowerAnt a1 = new ThrowerAnt(x, y);
					colony[x][y].setAnt(a1);
				}
			}
			
		}
	}
	
	public void printBoard(){
		for(int x = 0; x<length; x++){
			if(queens[x]){
				System.out.print("[ Q ]");
			}
			else{
				System.out.print("[   ]");
			}
			
			for(int y = 0; y<length; y++){
				System.out.print(colony[x][y].toString());
				if(colony[x][y].isH()){
					System.out.print(" Bees left in hive: "+ beeCount);
				}
			}
			System.out.println();
		}
		System.out.println("Food Count: "+ food);
	}
	
	private boolean checkWin(){
		boolean q = false;
		for(int i = 0; i<length; i++){
			if(queens[i]){
				q = true;
			}
		}
		if(q){}
		else{
			System.out.println("BEES WIN!");
			return false;
		}
		if(food == 0){
			boolean a = false;
			for(int x = 0; x<length; x++){
				for(int y = 0; y<length; y++){
					if(colony[x][y].getAnt()!=null){
						a = true;
					}
				}
			}
			if(a){}
			else{
				System.out.println("BEES WIN!");
				return false;
			}
		}
		if(beeCount == 0){
			boolean b = false;
			for(Bee bz : bees){
				if(bz.alive){
					b = true;
				}
			}
			if(b){}
			else{
				System.out.println("ANTS WIN!");
				return false;
			}
		}
		return true;
	}
	
	public void play(){
		while(checkWin()){
			printBoard();
			placeAnt();
			releaseBees();
			moveBees();
			harvFood();
			printBoard();
			resetMoves();
		}
		
	}
}
