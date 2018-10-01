package dict;

import list.SList;

public class test {
	public static void main(String arg[]) {
		HashTableChained h = new HashTableChained(4);
		Entry PYS;
		Entry ZZH;
		PYS = h.insert("PYS", "Lover");
		ZZH = PYS;

		System.out.println(PYS.equals(ZZH));
	}
}
