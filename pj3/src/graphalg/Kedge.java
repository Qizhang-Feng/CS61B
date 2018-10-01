package graphalg;

public class Kedge implements Comparable{
	protected Object u;
	protected Object v;
	protected int weight;

	public Kedge(Object u, Object v, int weight) {
		this.u = u;
		this.v = v;
		this.weight = weight;
	}

	public int compareTo(Object o) {
		if (weight < ((Kedge)o).weight) {
			return -1;
		} else if (weight > ((Kedge)o).weight) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public String toString() {
		return("("+u+","+v+"):"+weight);
	}
}
