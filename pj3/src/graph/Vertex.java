package graph;

import list.DList;
import list.DListNode;

class Vertex {
	Object outvertex;
	DList EdgeList;
	
	Vertex(Object o){
		outvertex = o;
		EdgeList = new DList();
	}
	
	int degree() {
		return EdgeList.length();
	}
	
}
