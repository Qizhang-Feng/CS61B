/* Neighbors.java */

/* DO NOT CHANGE THIS FILE. */
/* YOUR SUBMISSION MUST WORK CORRECTLY WITH _OUR_ COPY OF THIS FILE. */

package graph;

/**
 * The Neighbors class is provided solely as a way to allow the method
 * WUGraph.getNeighbors() to return two arrays at once.  Do NOT use this class
 * for any other purpose.
 *
 * Since this class is NOT an abstract data type, but is merely a collection of
 * data, all fields are public.
 */

public class Neighbors {
  public Object[] neighborList;
  public int[] weightList;
  
  public String toString() {
	  String str = "neightbors are: ";
	  for(int i=0; i<neighborList.length;i++) {
		  str += neighborList[i]+" ";
	  }
	  str+='\n'+"weights are: ";
	  for(int i=0; i<weightList.length;i++) {
		  str += weightList[i]+" ";
	  }
	  return str;
  }
}
