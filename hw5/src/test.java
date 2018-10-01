
public class test {
	public static void main(String[] arg) {
	Set s = new Set();
	Set s1 = new Set();

	s.insert(2);
	s.insert(4);
	s.insert(7);

	
	
	s1.insert(1);
	s1.insert(2);
	s1.insert(3);
	s1.insert(4);
	s1.insert(5);
	s1.insert(6);
	s1.insert(7);
	s1.insert(8);
	
	System.out.println(s);
	System.out.println(s1);
	s.intersect(s1);;
	System.out.println(s);
	
	}	
}
