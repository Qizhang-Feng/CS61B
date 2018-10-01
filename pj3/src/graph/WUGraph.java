/* WUGraph.java */

package graph;

import dict.*;
import list.*;

/**
 * The WUGraph class represents a weighted, undirected graph.  Self-edges are
 * permitted.
 */

public class WUGraph {
	DList VertexList;
	HashTableChained VertexDictionary,EdgeDictionary;
	
  /**
   * WUGraph() constructs a graph having no vertices or edges.
   *
   * Running time:  O(1).
   */
  public WUGraph() {
	  VertexList = new DList();
	  VertexDictionary = new HashTableChained();
	  EdgeDictionary = new HashTableChained();
  }

  /**
   * vertexCount() returns the number of vertices in the graph.
   *
   * Running time:  O(1).
   */
  public int vertexCount() {
	  return VertexDictionary.size();
  }

  /**
   * edgeCount() returns the total number of edges in the graph.
   *
   * Running time:  O(1).
   */
  public int edgeCount() {
	  return EdgeDictionary.size();
  }

  /**
   * getVertices() returns an array containing all the objects that serve
   * as vertices of the graph.  The array's length is exactly equal to the
   * number of vertices.  If the graph has no vertices, the array has length
   * zero.
   *
   * (NOTE:  Do not return any internal data structure you use to represent
   * vertices!  Return only the same objects that were provided by the
   * calling application in calls to addVertex().)
   *
   * Running time:  O(|V|).
   */
  public Object[] getVertices() {
	  Object[] Vertices = new Object[VertexDictionary.size()];
	  ListNode node = VertexList.front();
	  try {
		  int i = 0;
		  while(node.isValidNode()) {
			  Vertices[i] = ((Vertex)node.item()).outvertex;
			  node = node.next();
			  i++;
		  }
	  }catch (InvalidNodeException e) {
		  e.printStackTrace();
	  }
	  return Vertices;
  }

  /**
   * addVertex() adds a vertex (with no incident edges) to the graph.
   * The vertex's "name" is the object provided as the parameter "vertex".
   * If this object is already a vertex of the graph, the graph is unchanged.
   *
   * Running time:  O(1).
   */
  public void addVertex(Object vertex) {
	 if( VertexDictionary.find(vertex)==null) {
		 Vertex newvertex = new Vertex(vertex);
		 VertexList.insertFront(newvertex);
		 VertexDictionary.insert(vertex, VertexList.front());
	 }else {
		 System.out.println("this vertex is in graph");
	 }
  }

  /**
   * removeVertex() removes a vertex from the graph.  All edges incident on the
   * deleted vertex are removed as well.  If the parameter "vertex" does not
   * represent a vertex of the graph, the graph is unchanged.
   *
   * Running time:  O(d), where d is the degree of "vertex".
   */
  public void removeVertex(Object vertex) {
	  if(VertexDictionary.find(vertex)==null) {
		  System.out.println("this vertex is not in graph");
	  }else {
		  DListNode removeNode = (DListNode)VertexDictionary.remove(vertex).value();//remove from dictionary
		  try {
			  DListNode removeEdge;
			while(!((Vertex)removeNode.item()).EdgeList.isEmpty()) {
				removeEdge = (DListNode) ((Vertex)removeNode.item()).EdgeList.front();
				Object o = ((Vertex)((Edge)removeEdge.item()).guest.item()).outvertex;
				VertexPair p = new VertexPair(vertex,o);
				EdgeDictionary.remove(p);
				((Edge)removeEdge.item()).Partner.remove();
				if(removeEdge.isValidNode()) {
					removeEdge.remove();
				}
				
			}
			removeNode.remove();//remove from VertexDList
			//////////////////////////////////////////pppppp
			
			
			
			
		} catch (InvalidNodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
  }

  /**
   * isVertex() returns true if the parameter "vertex" represents a vertex of
   * the graph.
   *
   * Running time:  O(1).
   */
  public boolean isVertex(Object vertex) {
	  return VertexDictionary.find(vertex)!=null;
  }

  /**
   * degree() returns the degree of a vertex.  Self-edges add only one to the
   * degree of a vertex.  If the parameter "vertex" doesn't represent a vertex
   * of the graph, zero is returned.
   *
   * Running time:  O(1).
   */
  public int degree(Object vertex) {
	  if(this.isVertex(vertex)) {
		  try {
			return ((Vertex)((DListNode)VertexDictionary.find(vertex).value()).item()).degree();
		} catch (InvalidNodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	return 0;
  }

  /**
   * getNeighbors() returns a new Neighbors object referencing two arrays.  The
   * Neighbors.neighborList array contains each object that is connected to the
   * input object by an edge.  The Neighbors.weightList array contains the
   * weights of the corresponding edges.  The length of both arrays is equal to
   * the number of edges incident on the input vertex.  If the vertex has
   * degree zero, or if the parameter "vertex" does not represent a vertex of
   * the graph, null is returned (instead of a Neighbors object).
   *
   * The returned Neighbors object, and the two arrays, are both newly created.
   * No previously existing Neighbors object or array is changed.
   *
   * (NOTE:  In the neighborList array, do not return any internal data
   * structure you use to represent vertices!  Return only the same objects
   * that were provided by the calling application in calls to addVertex().)
   *
   * Running time:  O(d), where d is the degree of "vertex".
   */
  public Neighbors getNeighbors(Object vertex) {
	 
	  Neighbors n = new Neighbors();
	  DListNode node = (DListNode)VertexDictionary.find(vertex).value();
	  try {
		if(((Vertex)node.item()).degree()==0) return null;
	} catch (InvalidNodeException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	  int i = 0;
	  try {
		n.neighborList = new Object[((Vertex)node.item()).degree()];
		n.weightList = new int[((Vertex)node.item()).degree()];
		node = (DListNode)((Vertex)node.item()).EdgeList.front();
		while(node.isValidNode()) {
			n.neighborList[i] = ((Vertex)((Edge)node.item()).guest.item()).outvertex;
			n.weightList[i] = ((Edge)node.item()).weight;
			node = (DListNode) node.next();
			i++;
		}
	} catch (InvalidNodeException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return n;
	  
  }

  /**
   * addEdge() adds an edge (u, v) to the graph.  If either of the parameters
   * u and v does not represent a vertex of the graph, the graph is unchanged.
   * The edge is assigned a weight of "weight".  If the graph already contains
   * edge (u, v), the weight is updated to reflect the new value.  Self-edges
   * (where u.equals(v)) are allowed.
   *
   * Running time:  O(1).
   */
  public void addEdge(Object u, Object v, int weight) {
	  if(VertexDictionary.find(u)!=null && VertexDictionary.find(v)!=null) {
		  VertexPair p = new VertexPair(u,v);
		  if(EdgeDictionary.find(p)!=null) {
			 try {
				((Edge)((DListNode)EdgeDictionary.find(p).value()).item()).weight = weight;
				((Edge)((Edge)((DListNode)EdgeDictionary.find(p).value()).item()).Partner.item()).weight = weight;
			} catch (InvalidNodeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }else {
			  if(u.equals(v)) {
				  Edge e = new Edge((DListNode)VertexDictionary.find(u).value(),(DListNode)VertexDictionary.find(v).value(),weight);
				  try {
					((Vertex)((DListNode)VertexDictionary.find(u).value()).item()).EdgeList.insertFront(e);
					DListNode newfront = (DListNode)((Vertex)((DListNode)VertexDictionary.find(u).value()).item()).EdgeList.front();
					((Edge)newfront.item()).Partner = (DListNode) newfront;
					EdgeDictionary.insert(p, newfront);
				} catch (InvalidNodeException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			  }else {
				  Edge e1 = new Edge((DListNode)VertexDictionary.find(u).value(),(DListNode)VertexDictionary.find(v).value(),weight);
				  Edge e2 = new Edge((DListNode)VertexDictionary.find(v).value(),(DListNode)VertexDictionary.find(u).value(),weight);
				  try {
					((Vertex)((DListNode)VertexDictionary.find(u).value()).item()).EdgeList.insertFront(e1);
					DListNode unewfront = (DListNode)((Vertex)((DListNode)VertexDictionary.find(u).value()).item()).EdgeList.front();
					((Vertex)((DListNode)VertexDictionary.find(v).value()).item()).EdgeList.insertFront(e2);
					DListNode vnewfront = (DListNode)((Vertex)((DListNode)VertexDictionary.find(v).value()).item()).EdgeList.front();
					((Edge)unewfront.item()).Partner = vnewfront;
					((Edge)vnewfront.item()).Partner = unewfront;
					EdgeDictionary.insert(p, unewfront); //key:(u,v) value:(u,v) partner:(v,u)
				} catch (InvalidNodeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		  }
	  }
  }

  /**
   * removeEdge() removes an edge (u, v) from the graph.  If either of the
   * parameters u and v does not represent a vertex of the graph, the graph
   * is unchanged.  If (u, v) is not an edge of the graph, the graph is
   * unchanged.
   *
   * Running time:  O(1).
   */
  public void removeEdge(Object u, Object v) {
	  if(isEdge(u,v)) {
		  VertexPair p = new VertexPair(u,v);
		  DListNode removeEdge = (DListNode)EdgeDictionary.find(p).value();
		  try {
			((Edge)removeEdge.item()).Partner.remove();
			if(removeEdge.isValidNode()) {
				removeEdge.remove();
			}
			EdgeDictionary.remove(p);
		} catch (InvalidNodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  EdgeDictionary.remove(p);
	  }else {
		  System.out.println("Edge dosn't exist");
	  }
  }
  /**
   * isEdge() returns true if (u, v) is an edge of the graph.  Returns false
   * if (u, v) is not an edge (including the case where either of the
   * parameters u and v does not represent a vertex of the graph).
   *
   * Running time:  O(1).
   */
  public boolean isEdge(Object u, Object v) {
	  VertexPair p = new VertexPair(u,v);
	  return EdgeDictionary.find(p) != null;
  };

  /**
   * weight() returns the weight of (u, v).  Returns zero if (u, v) is not
   * an edge (including the case where either of the parameters u and v does
   * not represent a vertex of the graph).
   *
   * (NOTE:  A well-behaved application should try to avoid calling this
   * method for an edge that is not in the graph, and should certainly not
   * treat the result as if it actually represents an edge with weight zero.
   * However, some sort of default response is necessary for missing edges,
   * so we return zero.  An exception would be more appropriate, but also more
   * annoying.)
   *
   * Running time:  O(1).
   */
  public int weight(Object u, Object v) {
	  if(isEdge(u,v)) {
		  VertexPair p = new VertexPair(u,v);
		  try {
			return ((Edge)((DListNode)EdgeDictionary.find(p).value()).item()).weight;
		} catch (InvalidNodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }else {
		  return 0;
	  }
	return 0;
  }

  
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
		 
		 System.out.println(testgraph.vertexCount());
		 System.out.println(testgraph.edgeCount());
		 System.out.println(testgraph.degree(u));
		 System.out.println(testgraph.isEdge(x, v));
		 System.out.println(testgraph.isVertex(w));
		 System.out.println(testgraph.weight(u, v));
		 System.out.println(testgraph.getVertices()[0]+""+testgraph.getVertices()[1]+""+testgraph.getVertices()[2]+""+testgraph.getVertices()[3]);
		 

		 testgraph.addEdge(u, x, 6);
		 
		 System.out.println(testgraph.vertexCount());
		 System.out.println(testgraph.edgeCount());
		 System.out.println(testgraph.degree(u));
		 System.out.println(testgraph.isEdge(u, v));
		 System.out.println(testgraph.isVertex(w));
		 System.out.println(testgraph.weight(u, v));
		 System.out.println(testgraph.getVertices()[0]+""+testgraph.getVertices()[1]+""+testgraph.getVertices()[2]+""+testgraph.getVertices()[3]);
		 System.out.println(testgraph.isEdge(u, x));
		 
		testgraph.getNeighbors(u);
	  }
  
  
}
