package graph;

import list.*;

public class Edge {
	int weight;
	DListNode host,guest,Partner;
	
	Edge(DListNode u,DListNode v,int weight){
		host = u;
		guest = v;
		this.weight = weight;
	}
	

}
