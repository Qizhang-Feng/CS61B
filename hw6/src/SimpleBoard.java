/* SimpleBoard.java */
import dict.*;
/**
 *  Simple class that implements an 8x8 game board with three possible values
 *  for each cell:  0, 1 or 2.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class SimpleBoard {
  private final static int DIMENSION = 8;
  private int[][] grid;

  /**
   *  Invariants:  
   *  (1) grid.length == DIMENSION.
   *  (2) for all 0 <= i < DIMENSION, grid[i].length == DIMENSION.
   *  (3) for all 0 <= i, j < DIMENSION, grid[i][j] >= 0 and grid[i][j] <= 2.
   **/

  /**
   *  Construct a new board in which all cells are zero.
   */

  public SimpleBoard() {
    grid = new int[DIMENSION][DIMENSION];
  }

  /**
   *  Set the cell (x, y) in the board to the given value mod 3.
   *  @param value to which the element should be set (normally 0, 1, or 2).
   *  @param x is the x-index.
   *  @param y is the y-index.
   *  @exception ArrayIndexOutOfBoundsException is thrown if an invalid index
   *  is given.
   **/

  public void setElementAt(int x, int y, int value) {
    grid[x][y] = value % 3;
    if (grid[x][y] < 0) {
      grid[x][y] = grid[x][y] + 3;
    }
  }

  /**
   *  Get the valued stored in cell (x, y).
   *  @param x is the x-index.
   *  @param y is the y-index.
   *  @return the stored value (between 0 and 2).
   *  @exception ArrayIndexOutOfBoundsException is thrown if an invalid index
   *  is given.
   */

  public int elementAt(int x, int y) {
    return grid[x][y];
  }

  /**
   *  Returns true if "this" SimpleBoard and "board" have identical values in
   *    every cell.
   *  @param board is the second SimpleBoard.
   *  @return true if the boards are equal, false otherwise.
   */

  public boolean equals(Object board) {
    // Replace the following line with your solution.  Be sure to return false
    //   (rather than throwing a ClassCastException) if "board" is not
    //   a SimpleBoard.
	  if(this.getClass()==board.getClass()) {
		  boolean b = true;
		  for(int i=0;i<DIMENSION;i++) {
			  for(int j=0;j<DIMENSION;j++) {
				  b=b&&(this.elementAt(i, j)==((SimpleBoard)board).elementAt(i, j));
				  if(b==false) {
					  return b;
				  }
			  }
		  }
		  return b;
	  }
    return false;
  }

  /**
   *  Returns a hash code for this SimpleBoard.
   *  @return a number between Integer.MIN_VALUE and Integer.MAX_VALUE.
   */

  public int hashCode() {
    // Replace the following line with your solution.
	int FUHAO = 0;
	int code = 0;
	for(int i=0;i<DIMENSION;i++) {
		FUHAO+=this.elementAt(i, 0);
	}
	if(FUHAO%2==0) {
		for(int i=0;i<DIMENSION;i++) {
			for(int j=1;j<DIMENSION;j++) {
				code += this.elementAt(i, j)*((int)Math.pow(3, (j-1)*DIMENSION+i));
			}
		}
	}
	else {
		for(int i=0;i<DIMENSION;i++) {
			for(int j=1;j<DIMENSION;j++) {				
				code -= this.elementAt(i, j)*((int)Math.pow(3, (j-1)*DIMENSION+i));
			}
		}
	
	}
    return code;
  }

  public static void main(String[] arg) {
	  SimpleBoard sb1 = new SimpleBoard();
	  SimpleBoard sb2 = new SimpleBoard();
	  sb1.setElementAt(0, 0, 1);
	  sb1.setElementAt(1, 0, 2);
	  sb1.setElementAt(0, 1, 0);
	  sb1.setElementAt(1, 1, 1);
	 
	  sb2.setElementAt(0, 0, 1);
	  sb2.setElementAt(1, 0, 2);
	  sb2.setElementAt(0, 1, 0);
	  sb2.setElementAt(1, 1, 1);
	  System.out.println(sb1.equals(sb2));
	  System.out.println(sb1.hashCode());
	  System.out.println(sb2.hashCode());
  }
  
}
