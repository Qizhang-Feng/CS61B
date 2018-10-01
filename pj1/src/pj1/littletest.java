package pj1;

public class littletest {
public static void main(String[] str) {
	int[] red = {1,1,2,5,7,3};
	int[] green = {2,3,3,6,8,4};
	int[] blue = {3,4,4,7,9,5};
	int[] runLengths = {2,2,3,1,1,3};
	RunLengthEncoding r = new RunLengthEncoding(3,4,red,green,blue,runLengths);
	System.out.println(r.toString());
	PixImage p = r.toPixImage();
	System.out.println(p);
	RunLengthEncoding rr = new RunLengthEncoding(p);
	System.out.println(rr);
	System.out.println(rr.toPixImage());
	System.out.println("test");
	rr.setPixel(1, 0, (short)1, (short)1, (short)1);
}
}
