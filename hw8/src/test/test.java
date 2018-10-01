package test;
import list.*;
import test.*;
public class test {
	public static void main(String arg[]) {
		LinkedQueue q = new LinkedQueue();
		q.enqueue(10);
		q.enqueue(5);
		q.enqueue(3);
		q.enqueue(7);
		q.enqueue(9);
		q.enqueue(0);
		System.out.println(q);
	}
}
