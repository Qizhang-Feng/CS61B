package pj1;

public class Run {
public Run next = null;
public Run prev = null;
int[] RGBL = {0,0,0,1};
Run(int r,int g,int b,int l){
	RGBL[0] = r;
	RGBL[1] = g;
	RGBL[2] = b;
	RGBL[3] = l;
}

public void setL(int newLength) {
	RGBL[3] = newLength;
}

Run(int[] rgbl){
	RGBL[0] = rgbl[0];
	RGBL[1] = rgbl[1];
	RGBL[2] = rgbl[2];
	RGBL[3] = rgbl[3];
}

Run(int l){
	this(0,0,0,l);//set to sole black
}


public String toString() {
	return("RGB:("+RGBL[0]+","+RGBL[1]+","+RGBL[2]+") RunEncodingLength:"+RGBL[3]);
}

}
