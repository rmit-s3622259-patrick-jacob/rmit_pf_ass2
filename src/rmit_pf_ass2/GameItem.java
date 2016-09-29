package rmit_pf_ass2;

import java.util.Random;

public class GameItem {
	
	private String[][] board; 
 //   static String X = "X";
 //   static String O = "O";    
    
    public GameItem()
    {
        // initialize instance variables
        board = new String[4][4]; 
  }
    
    public void setBoard(){
		  GameItem test = new GameItem();
			test.display();
		}
    
public void display() {
 
//need to get gold position and read out of the array
//Gold positionGold = new Gold(); 
//int [][] pg = positionGold.nextInt[][](); 
//if (pg[1])
	
System.out.println();
for (int i = 0; i < board.length; i++) {
 for (int j = 0; j < board[i].length; j++) {
     if (board[i][j] == null) {
         System.out.print("_");
     } else {
         System.out.print(board[i][j]);
     }
     if (j < 3) {
         System.out.print("|");
     } else {
         System.out.println();
     } 
  }
}
System.out.println();
}
}
