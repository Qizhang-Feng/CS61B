package pj1;

public class Pixel {
private int x,y;
private short r,g,b;//0~255
private byte type;//corner:1,2,3,4; edge:5,6,7,8; inner:9

public static boolean RGBisValid(short rgb) {
	if (rgb<=255 && rgb>=0) {
		return true;
	}else return false;
}

public void setRGB(short r, short g, short b) {
	if(RGBisValid(r) && RGBisValid(g) && RGBisValid(b)) {
		this.setR(r);
		this.setG(g);
		this.setB(b);
	}else System.out.println("RGB is not valid, do nothing");
}

public void setR(short r) {
	if(RGBisValid(r)) {
		this.r = r;
	}else System.out.println("R is not valid, do nothing");
}

public void setG(short g) {
	if(RGBisValid(g)) {
		this.g= g;
	}else System.out.println("G is not valid, do nothing");
}

public void setB(short b) {
	if(RGBisValid(b)) {
		this.b = b;
	}else System.out.println("B is not valid, do nothing");
}

public short getR() {
	return r;
}

public short getG() {
	return g;
}

public short getB() {
	return b;
}

public int getX() {
	return x;
}

public int getY() {
	return y;
}

public byte getType() {
	return type;
}

private void setType(int x,int y,int h,int w) {
	byte type = 9;
	if(x==0 && y==0) {
		type = 1;
	}else if(x==w-1 && y==0) {
		type = 2;
	}else if(x==0 && y==h-1) {
		type = 3;
	}else if(x==w-1 && y==h-1) {
		type = 4;
	}else if(x>=1 && x<=w-2 && y==0) {
		type = 5;
	}else if(x==w-1 && y>=1 && y<=h-2) {
		type = 6;
	}else if(x>=1 && x<=w-2 && y==h-1) {
		type = 7;
	}else if(x==0 && y>=1 && y<=h-2) {
		type = 8;
	}
	this.type = type;
}

public Pixel(int x,int y,int h,int w) {//set to black
	this.x = x;
	this.y = y;
	this.setR((short)0);
	this.setG((short)0);
	this.setB((short)0);
	this.setType(x, y, h, w);
}

public Pixel(int x,int y,int h,int w,short r,short g,short b) {
	this(x,y,h,w);
	if(RGBisValid(r) && RGBisValid(g) && RGBisValid(b)) {
		this.setR(r);
		this.setG(g);
		this.setB(b);	
	}else System.out.println("RGB is not valid, set pixel to black");
}

public String toString() {
	return("Location: "+"("+x+","+y+")"+" RGB: "+"("+r+","+g+","+b+")"+" Type:"+type);
}
}
