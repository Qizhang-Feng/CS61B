package pj1;


public class PixImage {
private int Height,Width;
private Pixel[][] PixelArray=null;

private boolean isValidLocation(int x, int y) {
	if(x>-1 && x<Width && y>-1 && y<Height) {
		return true;
	}
	else return false;
}

public int getHeight() {
	return Height;
}

public int getWidth() {
	return Width;
}

public PixImage(int w, int h) {//set to a black image
	if(h>0 && w>0) {
		Height = h;
		Width = w;
		PixelArray = new Pixel[w][h];
		for(int j = 0;j<h;j++) {
			for(int k = 0;k<w;k++) {
				PixelArray[k][j] = new Pixel(k,j,h,w);
			}
		}
	}
	else {
	System.out.println("Height & Width should be positive!");
	return;
	}
}

public Pixel getPixel(int x,int y) {
	if(isValidLocation(x,y)) {
		return PixelArray[x][y];
	}
	else {
		System.out.println("point:("+x+","+y+") is not valid!");
		return null;
	}
}

public void setPixel(int x, int y, short r, short g, short b) {
	if(isValidLocation(x,y)) {
		PixelArray[x][y].setRGB(r, g, b);
	}
	else {
		System.out.println("point:("+x+","+y+") is not valid!");
		return;
	}
}

private static boolean isValidRGB(int rgb){
	if(rgb>=0 && rgb<=255) {
		return true;
	}
	else return false;
}

public void setPixel(int x,int y, int r, int g,int b) {
	if(isValidRGB(r) && isValidRGB(g) && isValidRGB(g)) {
		short R = (short)r;
		short G = (short)g;
		short B = (short)b;
		this.setPixel(x, y, R, G, B);
	}
	else {
		System.out.println("RGB is not valid!Do nothing!");
	}
}

public short getRed(int x, int y) {
	if(isValidLocation(x,y)) {
		return PixelArray[x][y].getR();
	}
	else {
		System.out.println("point:("+x+","+y+") is not valid!");
		return -1;
	}
}

public short getGreen(int x, int y) {
	if(isValidLocation(x,y)) {
		return PixelArray[x][y].getG();
	}
	else {
		System.out.println("point:("+x+","+y+") is not valid!");
		return -1;
	}
}

public short getBlue(int x, int y) {
	if(isValidLocation(x,y)) {
		return PixelArray[x][y].getB();
	}
	else {
		System.out.println("point:("+x+","+y+") is not valid!");
		return -1;
	}
}

public String toString() {
	String str="";
	for(int j = 0;j<Height;j++) {
		for(int k = 0;k<Width;k++) {
			str = str + PixelArray[k][j].toString()+" | ";
		}
		str = str+"\n";
	}
	return str;
}

public PixImage boxBlur(int numIterations) {
	if(numIterations == 0) {
		return this;
	}
	else if(numIterations == 1) {
		PixImage one = this;
		PixImage two = new PixImage(Width,Height);
		short r,g,b;
			for(int j=0;j<Height;j++) {
				for(int k=0;k<Width;k++) {
					if(one.PixelArray[k][j].getType() == 1) {
						r = (short)((one.PixelArray[k][j].getR()+one.PixelArray[k+1][j].getR()+one.PixelArray[k][j+1].getR()+one.PixelArray[k+1][j+1].getR())/4);
						g = (short)((one.PixelArray[k][j].getG()+one.PixelArray[k+1][j].getG()+one.PixelArray[k][j+1].getG()+one.PixelArray[k+1][j+1].getG())/4);
						b = (short)((one.PixelArray[k][j].getB()+one.PixelArray[k+1][j].getB()+one.PixelArray[k][j+1].getB()+one.PixelArray[k+1][j+1].getB())/4);
					}
					else if(one.PixelArray[k][j].getType() == 2) {
						r = (short)((one.PixelArray[k][j].getR()+one.PixelArray[k-1][j].getR()+one.PixelArray[k][j+1].getR()+one.PixelArray[k-1][j+1].getR())/4);
						g = (short)((one.PixelArray[k][j].getG()+one.PixelArray[k-1][j].getG()+one.PixelArray[k][j+1].getG()+one.PixelArray[k-1][j+1].getG())/4);
						b = (short)((one.PixelArray[k][j].getB()+one.PixelArray[k-1][j].getB()+one.PixelArray[k][j+1].getB()+one.PixelArray[k-1][j+1].getB())/4);
					}
					else if(one.PixelArray[k][j].getType() == 3) {
						r = (short)((one.PixelArray[k][j].getR()+one.PixelArray[k+1][j].getR()+one.PixelArray[k][j-1].getR()+one.PixelArray[k+1][j-1].getR())/4);
						g = (short)((one.PixelArray[k][j].getG()+one.PixelArray[k+1][j].getG()+one.PixelArray[k][j-1].getG()+one.PixelArray[k+1][j-1].getG())/4);
						b = (short)((one.PixelArray[k][j].getB()+one.PixelArray[k+1][j].getB()+one.PixelArray[k][j-1].getB()+one.PixelArray[k+1][j-1].getB())/4);
					}
					else if(one.PixelArray[k][j].getType() == 4) {
						r = (short)((one.PixelArray[k][j].getR()+one.PixelArray[k-1][j].getR()+one.PixelArray[k][j-1].getR()+one.PixelArray[k-1][j-1].getR())/4);
						g = (short)((one.PixelArray[k][j].getG()+one.PixelArray[k-1][j].getG()+one.PixelArray[k][j-1].getG()+one.PixelArray[k-1][j-1].getG())/4);
						b = (short)((one.PixelArray[k][j].getB()+one.PixelArray[k-1][j].getB()+one.PixelArray[k][j-1].getB()+one.PixelArray[k-1][j-1].getB())/4);
					}
					else if(one.PixelArray[k][j].getType() == 5) {
						r = (short)((one.PixelArray[k-1][j].getR()+one.PixelArray[k][j].getR()+one.PixelArray[k+1][j].getR()+one.PixelArray[k-1][j+1].getR()+one.PixelArray[k][j+1].getR()+one.PixelArray[k+1][j+1].getR())/6);
						g = (short)((one.PixelArray[k-1][j].getG()+one.PixelArray[k][j].getG()+one.PixelArray[k+1][j].getG()+one.PixelArray[k-1][j+1].getG()+one.PixelArray[k][j+1].getG()+one.PixelArray[k+1][j+1].getG())/6);
						b = (short)((one.PixelArray[k-1][j].getB()+one.PixelArray[k][j].getB()+one.PixelArray[k+1][j].getB()+one.PixelArray[k-1][j+1].getB()+one.PixelArray[k][j+1].getB()+one.PixelArray[k+1][j+1].getB())/6);
					}
					else if(one.PixelArray[k][j].getType() == 6) {
						r = (short)((one.PixelArray[k-1][j-1].getR()+one.PixelArray[k-1][j].getR()+one.PixelArray[k-1][j+1].getR()+one.PixelArray[k][j-1].getR()+one.PixelArray[k][j].getR()+one.PixelArray[k][j+1].getR())/6);
						g = (short)((one.PixelArray[k-1][j-1].getG()+one.PixelArray[k-1][j].getG()+one.PixelArray[k-1][j+1].getG()+one.PixelArray[k][j-1].getG()+one.PixelArray[k][j].getG()+one.PixelArray[k][j+1].getG())/6);
						b = (short)((one.PixelArray[k-1][j-1].getB()+one.PixelArray[k-1][j].getB()+one.PixelArray[k-1][j+1].getB()+one.PixelArray[k][j-1].getB()+one.PixelArray[k][j].getB()+one.PixelArray[k][j+1].getB())/6);
					}
					else if(one.PixelArray[k][j].getType() == 7) {
						r = (short)((one.PixelArray[k-1][j].getR()+one.PixelArray[k][j].getR()+one.PixelArray[k+1][j].getR()+one.PixelArray[k-1][j-1].getR()+one.PixelArray[k][j-1].getR()+one.PixelArray[k+1][j-1].getR())/6);
						g = (short)((one.PixelArray[k-1][j].getG()+one.PixelArray[k][j].getG()+one.PixelArray[k+1][j].getG()+one.PixelArray[k-1][j-1].getG()+one.PixelArray[k][j-1].getG()+one.PixelArray[k+1][j-1].getG())/6);
						b = (short)((one.PixelArray[k-1][j].getB()+one.PixelArray[k][j].getB()+one.PixelArray[k+1][j].getB()+one.PixelArray[k-1][j-1].getB()+one.PixelArray[k][j-1].getB()+one.PixelArray[k+1][j-1].getB())/6);
					}
					else if(one.PixelArray[k][j].getType() == 8) {
						r = (short)((one.PixelArray[k+1][j-1].getR()+one.PixelArray[k+1][j].getR()+one.PixelArray[k+1][j+1].getR()+one.PixelArray[k][j-1].getR()+one.PixelArray[k][j].getR()+one.PixelArray[k][j+1].getR())/6);
						g = (short)((one.PixelArray[k+1][j-1].getG()+one.PixelArray[k+1][j].getG()+one.PixelArray[k+1][j+1].getG()+one.PixelArray[k][j-1].getG()+one.PixelArray[k][j].getG()+one.PixelArray[k][j+1].getG())/6);
						b = (short)((one.PixelArray[k+1][j-1].getB()+one.PixelArray[k+1][j].getB()+one.PixelArray[k+1][j+1].getB()+one.PixelArray[k][j-1].getB()+one.PixelArray[k][j].getB()+one.PixelArray[k][j+1].getB())/6);
					}
					else{
						r = (short)((one.PixelArray[k-1][j-1].getR()+one.PixelArray[k][j-1].getR()+one.PixelArray[k+1][j-1].getR()+one.PixelArray[k-1][j].getR()+one.PixelArray[k][j].getR()+one.PixelArray[k+1][j].getR()+one.PixelArray[k-1][j+1].getR()+one.PixelArray[k][j+1].getR()+one.PixelArray[k+1][j+1].getR())/9);
						g = (short)((one.PixelArray[k-1][j-1].getG()+one.PixelArray[k][j-1].getG()+one.PixelArray[k+1][j-1].getG()+one.PixelArray[k-1][j].getG()+one.PixelArray[k][j].getG()+one.PixelArray[k+1][j].getG()+one.PixelArray[k-1][j+1].getG()+one.PixelArray[k][j+1].getG()+one.PixelArray[k+1][j+1].getG())/9);
						b = (short)((one.PixelArray[k-1][j-1].getB()+one.PixelArray[k][j-1].getB()+one.PixelArray[k+1][j-1].getB()+one.PixelArray[k-1][j].getB()+one.PixelArray[k][j].getB()+one.PixelArray[k+1][j].getB()+one.PixelArray[k-1][j+1].getB()+one.PixelArray[k][j+1].getB()+one.PixelArray[k+1][j+1].getB())/9);
					}
					two.setPixel(k, j, r, g, b);
				}
			}
			return two;	
	}
	else if(numIterations<0) {
		System.out.println("numIterations must be positive!");
		return this;
	}
	else {
		PixImage p = this.boxBlur(numIterations-1);
		return p.boxBlur(1);
	}
	
}

public int[] getRGB(int x, int y) {
	int[] rgb = new int[3];
	rgb[0] = this.getRed(x, y);
	rgb[1] = this.getGreen(x, y);
	rgb[2] = this.getBlue(x, y);
	return rgb;
}

public int[][][] expendRGB(){
	int[][][] ergb = new int[Width+2][Height+2][];
	ergb[0][0] = this.getRGB(0,0);
	ergb[Width+1][0] = this.getRGB(Width-1, 0);
	ergb[Width+1][Height+1] = this.getRGB(Width-1, Height-1);
	ergb[0][Height+1] = this.getRGB(0, Height-1);
	
	for(int i=1;i<Width+1;i++) {
		ergb[i][0] = this.getRGB(i-1, 0);
	}
	for(int i=1;i<Width+1;i++) {
		ergb[i][Height+1] = this.getRGB(i-1, Height-1);
	}
	for(int i=1;i<Height+1;i++) {
		ergb[0][i] = this.getRGB(0, i-1);
	}
	for(int i=1;i<Height+1;i++) {
		ergb[Width+1][i] = this.getRGB(Width-1,i-1);
	}
	
	for(int k=1;k<Width+1;k++) {
		for(int j=1;j<Height+1;j++) {
			ergb[k][j] = this.getRGB(k-1, j-1);
		}
	}	
	return ergb;
}

private final static int[][] Gx = {{1,0,-1},{2,0,-2},{1,0,-1}};

private final static int[][] Gy = {{1,2,1},{0,0,0},{-1,-2,-1}};

private static int gx(int[][][] ergb,int k,int j,int rgblevel) {
	int gx = 0;
	for(int m=0;m<Gx.length;m++) {
		for(int n=0;n<Gx[0].length;n++) {
			gx = gx + Gx[m][n]*ergb[k-1+n][j-1+m][rgblevel];
		}
	}
	return gx;
}

private static int gy(int[][][] ergb,int k,int j,int rgblevel) {
	int gy = 0;
	for(int m=0;m<Gy.length;m++) {
		for(int n=0;n<Gy[0].length;n++) {
			gy = gy + Gy[m][n]*ergb[k-1+n][j-1+m][rgblevel];
		}
	}
	return gy;
}

public PixImage sobelEdges() {
	int[][][] ergb = this.expendRGB();
	long[][] energy = new long[Width][Height];
	long gxr,gxg,gxb,gyr,gyg,gyb;
	for(int j=1;j<Height+1;j++) {
		for(int k=1;k<Width+1;k++) {
			gxr = gx(ergb,k,j,0);
			gxg = gx(ergb,k,j,1);
			gxb = gx(ergb,k,j,2);
			gyr = gy(ergb,k,j,0);
			gyg = gy(ergb,k,j,1);
			gyb = gy(ergb,k,j,2);
			energy[k-1][j-1] = gxr*gxr+gxg*gxg+gxb*gxb+gyr*gyr+gyg*gyg+gyb*gyb;
			//System.out.println("gyr["+(k-1)+"]["+(j-1)+"] is:");
			//System.out.println(gyr);
			//System.out.println("energy["+(k-1)+"]["+(j-1)+"] is:");
			//System.out.println(energy[k-1][j-1]);
		}
	}
	int[][] gray = new int[Width][Height];
	for(int j=0;j<Height;j++) {
		for(int k=0;k<Width;k++) {
			gray[k][j] = mag2gray(energy[k][j]);
			//System.out.println("gray["+(k)+"]["+(j)+"] is:");
			//System.out.println(gray[k][j]);
		}
	}
	
	return array2PixImage(gray);
}
private static PixImage array2PixImage(int[][] pixels) {
    int width = pixels.length;
   // System.out.println("width is:"+width);
    int height = pixels[0].length;
  //  System.out.println("height is:"+height);
    PixImage image = new PixImage(width, height);

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        image.setPixel(x, y, (short) pixels[x][y], (short) pixels[x][y],
                       (short) pixels[x][y]);
      }
    }
    return image;
  }
private static short mag2gray(long mag) {
    short intensity = (short) (30.0 * Math.log(1.0 + (double) mag) - 256.0);

    // Make sure the returned intensity is in the range 0...255, regardless of
    // the input value.
    if (intensity < 0) {
      intensity = 0;
    } else if (intensity > 255) {
      intensity = 255;
    }
    return intensity;
  }
private static void doTest(boolean b, String msg) {
    if (b) {
      System.out.println("Good.");
    } else {
      System.err.println(msg);
    }
  }

public boolean equals(PixImage image) {
    int width = getWidth();
    int height = getHeight();

    if (image == null ||
        width != image.getWidth() || height != image.getHeight()) {
      return false;
    }

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        if (! (getRed(x, y) == image.getRed(x, y) &&
               getGreen(x, y) == image.getGreen(x, y) &&
               getBlue(x, y) == image.getBlue(x, y))) {
          return false;
        }
      }
    }
    return true;
  }

public static void main(String[] args) {
    // Be forwarned that when you write arrays directly in Java as below,
    // each "row" of text is a column of your image--the numbers get
    // transposed.
    PixImage image1 = array2PixImage(new int[][] { { 0, 10, 240 },
                                                   { 30, 120, 250 },
                                                   { 80, 250, 255 } });
    System.out.println("Testing getWidth/getHeight on a 3x3 image.  " +
                       "Input image:");
    System.out.print(image1);
    doTest(image1.getWidth() == 3 && image1.getHeight() == 3,
           "Incorrect image width and height.");

    System.out.println("Testing blurring on a 3x3 image.");
    doTest(image1.boxBlur(1).equals(
           array2PixImage(new int[][] { { 40, 108, 155 },
                                        { 81, 137, 187 },
                                        { 120, 164, 218 } })),
           "Incorrect box blur (1 rep):\n" + image1.boxBlur(1));
    doTest(image1.boxBlur(2).equals(
           array2PixImage(new int[][] { { 91, 118, 146 },
                                        { 108, 134, 161 },
                                        { 125, 151, 176 } })),
           "Incorrect box blur (2 rep):\n" + image1.boxBlur(2));
    doTest(image1.boxBlur(2).equals(image1.boxBlur(1).boxBlur(1)),
           "Incorrect box blur (1 rep + 1 rep):\n" +
           image1.boxBlur(2) + image1.boxBlur(1).boxBlur(1));

    System.out.println("Testing edge detection on a 3x3 image.");
    doTest(image1.sobelEdges().equals(
           array2PixImage(new int[][] { { 104, 189, 180 },
                                        { 160, 193, 157 },
                                        { 166, 178, 96 } })),
           "Incorrect Sobel:\n" + image1.sobelEdges());


    PixImage image2 = array2PixImage(new int[][] { { 0, 100, 100 },
                                                   { 0, 0, 100 } });
   System.out.println("Testing getWidth/getHeight on a 2x3 image.  " +
                       "Input image:");
    System.out.print(image2);
    doTest(image2.getWidth() == 2 && image2.getHeight() == 3,
           "Incorrect image width and height.");

    System.out.println("Testing blurring on a 2x3 image.");
    
   //PixImage image3 = array2PixImage(new int[][] { { 25, 50, 75 },
   //     { 25, 50, 75 } });
   // System.out.println("Image3 is \n"+image3);
    
    doTest(image2.boxBlur(1).equals(
           array2PixImage(new int[][] { { 25, 50, 75 },
                                        { 25, 50, 75 } })),
           "Incorrect box blur (1 rep):\n" + image2.boxBlur(1));
  
    System.out.println("Testing edge detection on a 2x3 image.");
    doTest(image2.sobelEdges().equals(
           array2PixImage(new int[][] { { 122, 143, 74 },
                                        { 74, 143, 122 } })),
           "Incorrect Sobel:\n" + image2.sobelEdges());
     /**     */
  }
}
