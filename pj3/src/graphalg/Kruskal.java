/* Kruskal.java */

package graphalg;

import dict.HashTableChained;
import graph.*;
import list.LinkedQueue;
import list.QueueEmptyException;
import list.SListNode;
import set.*;
import test.*;

/**
 * The Kruskal class contains the method minSpanTree(), which implements
 * Kruskal's algorithm for computing a minimum spanning tree of a graph.
 */

public class Kruskal {

  /**
   * minSpanTree() returns a WUGraph that represents the minimum spanning tree
   * of the WUGraph g.  The original WUGraph g is NOT changed.
   *
   * @param g The weighted, undirected graph whose MST we want to compute.
   * @return A newly constructed WUGraph representing the MST of g.
   */
  public static WUGraph minSpanTree(WUGraph g) {
	  WUGraph t = new WUGraph();
	  int NumOfVertices = g.vertexCount();
	  Object[] Vertices = g.getVertices();
	  LinkedQueue q = new LinkedQueue();
	  HashTableChained vhash = new HashTableChained(NumOfVertices);
	  for(int i=0; i<NumOfVertices;i++) {
		  t.addVertex(Vertices[i]);
		  vhash.insert(Vertices[i],i);
		  Neighbors n = g.getNeighbors(Vertices[i]);
		  for(int j=0; j<n.neighborList.length;j++) {
			  Kedge e = new Kedge(Vertices[i],n.neighborList[j],n.weightList[j]);
			  q.enqueue(e);
		  }
	  }
	  
	  ListSorts.quickSort(q);
	  //System.out.println(q);
	  DisjointSets s = new DisjointSets(NumOfVertices);
	  
	  while(!q.isEmpty()) {
		  Kedge e = null;
		  try {
			e = (Kedge) q.dequeue();
		} catch (QueueEmptyException ee) {
			// TODO Auto-generated catch block
			ee.printStackTrace();
		}
		  int root1 = s.find((int)vhash.find(e.u).value());
		  int root2 = s.find((int)vhash.find(e.v).value());
		  if(root1!=root2) {
			  s.union(root1, root2);
			  t.addEdge(e.u, e.v, e.weight);
		  }
	  }
	  
	  return t;
  };

  public static void main(String[] arg) {
	  WUGraph testgraph = new WUGraph();
		 Object w = "w";
		 Object u = "u";
		 Object v = "v";
		 Object x = "x";
		 testgraph.addVertex(w);
		 testgraph.addVertex(u);
		 testgraph.addVertex(v);
		 testgraph.addVertex(x);
		 testgraph.addEdge(w, w, 1);
		 testgraph.addEdge(w, u, 2);
		 testgraph.addEdge(u, u, 3);
		 testgraph.addEdge(u, v, 4);
		 testgraph.addEdge(v, x, 5);
		 
		 WUGraph t = Kruskal.minSpanTree(testgraph);
		 
		 System.out.println(t.vertexCount());
		 System.out.println(t.edgeCount());
		 System.out.println(testgraph.getVertices()[0]+""+testgraph.getVertices()[1]+""+testgraph.getVertices()[2]+""+testgraph.getVertices()[3]);
  }
  
}
