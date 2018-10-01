package pj1;

import java.util.Arrays;

/* RunLengthEncoding.java */

/**
 *  The RunLengthEncoding class defines an object that run-length encodes
 *  a PixImage object.  Descriptions of the methods you must implement appear
 *  below.  They include constructors of the form
 *
 *      public RunLengthEncoding(int width, int height);
 *      public RunLengthEncoding(int width, int height, int[] red, int[] green,
 *                               int[] blue, int[] runLengths) {
 *      public RunLengthEncoding(PixImage image) {
 *
 *  that create a run-length encoding of a PixImage having the specified width
 *  and height.
 *
 *  The first constructor creates a run-length encoding of a PixImage in which
 *  every pixel is black.  The second constructor creates a run-length encoding
 *  for which the runs are provided as parameters.  The third constructor
 *  converts a PixImage object into a run-length encoding of that image.
 *
 *  See the README file accompanying this project for additional details.
 */

import java.util.Iterator;

public class RunLengthEncoding implements Iterable {

  /**
   *  Define any variables associated with a RunLengthEncoding object here.
   *  These variables MUST be private.
   */
private int Width,Height;
private Run head = null;
private Run tail = null;

private int numOfRuns = 0;

  /**
   *  The following methods are required for Part II.
   */

  /**
   *  RunLengthEncoding() (with two parameters) constructs a run-length
   *  encoding of a black PixImage of the specified width and height, in which
   *  every pixel has red, green, and blue intensities of zero.
   *
   *  @param width the width of the image.
   *  @param height the height of the image.
   */

  public RunLengthEncoding(int width, int height) {//sole black
    // Your solution here.
	  this.Width = width;
	  this.Height = height;
	  head = new Run(width*height);
	  tail = head;
	  numOfRuns = 1;
	  
  }

  /**
   *  RunLengthEncoding() (with six parameters) constructs a run-length
   *  encoding of a PixImage of the specified width and height.  The runs of
   *  the run-length encoding are taken from four input arrays of equal length.
   *  Run i has length runLengths[i] and RGB intensities red[i], green[i], and
   *  blue[i].
   *
   *  @param width the width of the image.
   *  @param height the height of the image.
   *  @param red is an array that specifies the red intensity of each run.
   *  @param green is an array that specifies the green intensity of each run.
   *  @param blue is an array that specifies the blue intensity of each run.
   *  @param runLengths is an array that specifies the length of each run.
   *
   *  NOTE:  All four input arrays should have the same length (not zero).
   *  All pixel intensities in the first three arrays should be in the range
   *  0...255.  The sum of all the elements of the runLengths array should be
   *  width * height.  (Feel free to quit with an error message if any of these
   *  conditions are not met--though we won't be testing that.)
   */

  public RunLengthEncoding(int width, int height, int[] red, int[] green,
                           int[] blue, int[] runLengths) {
    // Your solution here.
	this(1,1);
	if(width>0 && height>0) {
		if(red.length != 0 && red.length == green.length && red.length == blue.length && red.length == runLengths.length) {
			int sum = 0;
			for(int i =0;i<runLengths.length;i++) {
				sum = sum+runLengths[i];
			}
			if(sum==width*height) {
				boolean validcolor = true;
				for(int i=0;i<red.length;i++) {
					validcolor = validcolor && red[i]>=0 && red[i]<=255;
				}
				for(int i=0;i<green.length;i++) {
					validcolor = validcolor && green[i]>=0 && green[i]<=255;
				}
				for(int i=0;i<blue.length;i++) {
					validcolor = validcolor && blue[i]>=0 && blue[i]<=255;
				}
				if(validcolor) {
					this.Width = width;
					this.Height = height;
					numOfRuns = red.length;
					head = new Run(red[0],green[0],blue[0],runLengths[0]);
					tail = head;
					for(int i=1;i<runLengths.length;i++) {
						tail.next = new Run(red[i],green[i],blue[i],runLengths[i]);
						tail.next.prev = tail;
						tail = tail.next;
					}
				}
				else {
					System.out.println("Color range is not valid! Make a 1×1 balck RunLengthEncoding!");
				}
				
			}
			else {
				System.out.println("The sum of all the elements of the runLengths array doesn't equal to width*height! Make a 1×1 black RunLengthEncoding!");
			}
		}
		else {
			System.out.println("RGBL length is not the same! Make a 1×1 black RunLengthEncoding!");
		}
	}
	else {
		System.out.println("Width & Height must be positive! Make a 1×1 black RunLengthEnoding!");
	}
  }

  /**
   *  getWidth() returns the width of the image that this run-length encoding
   *  represents.
   *
   *  @return the width of the image that this run-length encoding represents.
   */

  public int getWidth() {
    // Replace the following line with your solution.
    return Width;
  }

  /**
   *  getHeight() returns the height of the image that this run-length encoding
   *  represents.
   *
   *  @return the height of the image that this run-length encoding represents.
   */
  public int getHeight() {
    // Replace the following line with your solution.
    return Height;
  }

  /**
   *  iterator() returns a newly created RunIterator that can iterate through
   *  the runs of this RunLengthEncoding.
   *
   *  @return a newly created RunIterator object set to the first run of this
   *  RunLengthEncoding.
   */
  public RunIterator iterator() {
    // Replace the following line with your solution.
	  if(numOfRuns == 0) {
		  System.out.println("This RunLengthEncoding dosen't have any Run!");
	  }
	  RunIterator iterator = new RunIterator(head);
	  return iterator;
    // You'll want to construct a new RunIterator, but first you'll need to
    // write a constructor in the RunIterator class.
    
  }

  /**
   *  toPixImage() converts a run-length encoding of an image into a PixImage
   *  object.
   *
   *  @return the PixImage that this RunLengthEncoding encodes.
   */
  public PixImage toPixImage() {
    // Replace the following line with your solution.
	  PixImage p = new PixImage(Width,Height);
	  RunIterator iterator= this.iterator();
	  int prevNum = iterator.Length();
	  for(int j=0;j<Height;j++) {
		  for(int k=0;k<Width;k++) {
			  if((j*Width+k+1)>prevNum) {
				  iterator.move2next();
				  prevNum += iterator.Length();
			  }
			  p.setPixel(k, j, iterator.Color()[0], iterator.Color()[1], iterator.Color()[2]);
		  }
	  }
    return p;
    
  }

  /**
   *  toString() returns a String representation of this RunLengthEncoding.
   *
   *  This method isn't required, but it should be very useful to you when
   *  you're debugging your code.  It's up to you how you represent
   *  a RunLengthEncoding as a String.
   *
   *  @return a String representation of this RunLengthEncoding.
   */
  public String toString() {
    // Replace the following line with your solution.
	  String str = "";
	  Run pointer  = head;
	  for(int i=0;i<numOfRuns;i++) {
		  str = str+pointer.toString()+"\n";
		  pointer = pointer.next;
	  }
	  str += "Number of Runs is:"+numOfRuns+"\nhead is:"+head;
	  return str;
  }


  /**
   *  The following methods are required for Part III.
   */

  /**
   *  RunLengthEncoding() (with one parameter) is a constructor that creates
   *  a run-length encoding of a specified PixImage.
   * 
   *  Note that you must encode the image in row-major format, i.e., the second
   *  pixel should be (1, 0) and not (0, 1).
   *
   *  @param image is the PixImage to run-length encode.
   */
  public RunLengthEncoding(PixImage image) {
	  
    // Your solution here, but you should probably leave the following line
    // at the end.
	  Width = image.getWidth();
	  Height = image.getHeight();
	  int[] rgbl = new int[4];
	  rgbl[0] = image.getRed(0, 0);
	  rgbl[1] = image.getGreen(0, 0);
	  rgbl[2] = image.getBlue(0, 0);
	  rgbl[3] = 1;
	  head = new Run(rgbl);
	  tail = head;
	  numOfRuns = 1;
	  for(int j=0;j<Height;j++) {
		  for(int k=0;k<Width;k++) {
			  if( k==0 && j==0 ) {
				  continue;
			  }
			  if(image.getRed(k, j)==rgbl[0] && image.getGreen(k, j)==rgbl[1] && image.getBlue(k, j)==rgbl[2]) {
				  tail.RGBL[3] +=1;
			  }
			  else{
				  rgbl[0] = image.getRed(k, j);
				  rgbl[1] = image.getGreen(k, j);
				  rgbl[2] = image.getBlue(k, j);
				  rgbl[3] = 1;
				  tail.next = new Run(rgbl);
				  tail.next.prev = tail;
				  tail = tail.next;
				  numOfRuns +=1;
			  }
		  }
	  }
	  check();
  }

  /**
   *  check() walks through the run-length encoding and prints an error message
   *  if two consecutive runs have the same RGB intensities, or if the sum of
   *  all run lengths does not equal the number of pixels in the image.
   */
  public void check() {
    // Your solution here.
	  RunIterator iterator = this.iterator();
	  while(iterator.hasNext()) {
		  //System.out.println("now:"+iterator.Color()[0]+","+iterator.Color()[1]+","+iterator.Color()[2]);
		  //System.out.println("next:"+iterator.nextColor()[0]+","+iterator.nextColor()[1]+","+iterator.nextColor()[2]);
		  if(Arrays.equals(iterator.nextColor(), iterator.Color())) {
			  System.out.println("There are two consecutive runs have the same RGB intensities!");
		  }
		  iterator.move2next();
	  }
	  iterator = this.iterator();
	  int sum = 0;
	  while(iterator.hasNext()) {
		  sum += iterator.Length();
		  //System.out.println(sum);
		  iterator.move2next();
	  }
	  sum += iterator.Length();
	  if(sum != Width*Height) {
		  System.out.println("The sum of all run lengths dose not equal the number of pixels in the image!"+Width*Height);
	  }
  }


  /**
   *  The following method is required for Part IV.
   */

  /**
   *  setPixel() modifies this run-length encoding so that the specified color
   *  is stored at the given (x, y) coordinates.  The old pixel value at that
   *  coordinate should be overwritten and all others should remain the same.
   *  The updated run-length encoding should be compressed as much as possible;
   *  there should not be two consecutive runs with exactly the same RGB color.
   *
   *  @param x the x-coordinate of the pixel to modify.
   *  @param y the y-coordinate of the pixel to modify.
   *  @param red the new red intensity to store at coordinate (x, y).
   *  @param green the new green intensity to store at coordinate (x, y).
   *  @param blue the new blue intensity to store at coordinate (x, y).
   */
  
  private Run getHead() {
	  return head;
  }
  
  private Run getTail() {
	  return tail;
  }
  
  private int getNumOfRuns() {
	  return numOfRuns;
  }
  
  public void setPixel(int x, int y, short red, short green, short blue) {
    // Your solution here, but you should probably leave the following line
    //   at the end.
	  /**PixImage p = this.toPixImage();
	  p.setPixel(x, y, red, green, blue);
	  RunLengthEncoding r = new RunLengthEncoding(p);
	  this.head = r.getHead();
	  this.tail = r.getTail();
	  this.numOfRuns = r.getNumOfRuns();
	  check();*/
	  RunIterator iterator = this.iterator();
	  int prevnum = iterator.Length();
	  while((x+1+y*Width)>prevnum) {
		  iterator.move2next();
		  prevnum += iterator.Length();
		  //System.out.println(iterator.Color()[0]+""+iterator.Color()[1]+""+iterator.Color()[2]+"");
	  }
	  if(red==iterator.Color()[0] && green==iterator.Color()[1] && blue==iterator.Color()[2]) {
		  return;
	  }
	  if((x+1+y*Width)-(prevnum-iterator.Length())-1!=0) {
		  Run iteratorprev = new Run(iterator.Color()[0],iterator.Color()[1],iterator.Color()[2],(x+1+y*Width)-(prevnum-iterator.Length())-1);
	  	  iterator.addPrev(iteratorprev);
	  	  numOfRuns +=1;
		  //System.out.println((x+1+y*Width)+"-("+prevnum+"-"+iterator.Length()+")-1");
		  //System.out.println("iteratorprev "+iteratorprev);
	  }
	  if(prevnum-(x+1+y*Width)!=0) {
		  Run iteratornext = new Run(iterator.Color()[0],iterator.Color()[1],iterator.Color()[2],prevnum-(x+1+y*Width));
		  iterator.addNext(iteratornext);
	  	  numOfRuns +=1;
		  //System.out.println("iteratornext "+iteratornext);
	  }
	  iterator.changeRGBL(red, green, blue, 1);
	  //System.out.println("iterator "+iterator);
	  if(iterator.hasPrev()) {
		  iterator.move2prev();  
		  //System.out.println(iterator.hasPrev());
	  }
	  if(iterator.hasPrev()) {
		  iterator.move2prev();  
		  //System.out.println(iterator.hasPrev());
	  }
	  //System.out.println(iterator);
	  for(int i=0;i<4;i++) {
		  if(iterator.hasNext()) {
			  if(Arrays.equals(iterator.nextColor(), iterator.Color())) {
				  iterator.changeLength(iterator.Length() + iterator.nextLength());
				  iterator.deleteNext();
				  numOfRuns --;
			  }
			  else {
				  iterator.move2next();
			  }
		  }
	  }
	  if(!iterator.hasNext()) {
		  tail = iterator.getRun();

	  }
	  System.out.println("tail"+tail);
	  for(int i=0;i<5;i++) {
		  //System.out.println("before"+iterator);
		  if(!iterator.hasPrev()) {
			  head = iterator.getRun();
		  }
		  else {
			  iterator.move2prev();
		  }
		  //System.out.println("after"+iterator);
	  }
	  //System.out.println("head "+head);
	  
	  check();
  }
  
  


  /**
   * TEST CODE:  YOU DO NOT NEED TO FILL IN ANY METHODS BELOW THIS POINT.
   * You are welcome to add tests, though.  Methods below this point will not
   * be tested.  This is not the autograder, which will be provided separately.
   */


  /**
   * doTest() checks whether the condition is true and prints the given error
   * message if it is not.
   *
   * @param b the condition to check.
   * @param msg the error message to print if the condition is false.
   */
  private static void doTest(boolean b, String msg) {
    if (b) {
      System.out.println("Good.");
    } else {
      System.err.println(msg);
    }
  }

  /**
   * array2PixImage() converts a 2D array of grayscale intensities to
   * a grayscale PixImage.
   *
   * @param pixels a 2D array of grayscale intensities in the range 0...255.
   * @return a new PixImage whose red, green, and blue values are equal to
   * the input grayscale intensities.
   */
  private static PixImage array2PixImage(int[][] pixels) {
    int width = pixels.length;
    int height = pixels[0].length;
    PixImage image = new PixImage(width, height);

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        image.setPixel(x, y, (short) pixels[x][y], (short) pixels[x][y],
                       (short) pixels[x][y]);
      }
    }

    return image;
  }

  /**
   * setAndCheckRLE() sets the given coordinate in the given run-length
   * encoding to the given value and then checks whether the resulting
   * run-length encoding is correct.
   *
   * @param rle the run-length encoding to modify.
   * @param x the x-coordinate to set.
   * @param y the y-coordinate to set.
   * @param intensity the grayscale intensity to assign to pixel (x, y).
   */
  private static void setAndCheckRLE(RunLengthEncoding rle,
                                     int x, int y, int intensity) {
    rle.setPixel(x, y,(short) intensity, (short) intensity, (short) intensity);
    rle.check();
  }

  /**
   * main() runs a series of tests of the run-length encoding code.
   */
  public static void main(String[] args) {
    // Be forwarned that when you write arrays directly in Java as below,
    // each "row" of text is a column of your image--the numbers get
    // transposed.
    PixImage image1 = array2PixImage(new int[][] { { 0, 3, 6 },
                                                   { 1, 4, 7 },
                                                   { 2, 5, 8 } });

    System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                       "on a 3x3 image.  Input image:");
    System.out.print(image1);
    RunLengthEncoding rle1 = new RunLengthEncoding(image1);
    rle1.check();
    //System.out.println(rle1);
    System.out.println("Testing getWidth/getHeight on a 3x3 encoding.");
    doTest(rle1.getWidth() == 3 && rle1.getHeight() == 3,
           "RLE1 has wrong dimensions");

    System.out.println("Testing toPixImage() on a 3x3 encoding.");
    doTest(image1.equals(rle1.toPixImage()),
           "image1 -> RLE1 -> image does not reconstruct the original image");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 0, 0, 42);
    //System.out.println(rle1);
    image1.setPixel(0, 0, (short) 42, (short) 42, (short) 42);
    doTest(rle1.toPixImage().equals(image1),
           /*
                       array2PixImage(new int[][] { { 42, 3, 6 },
                                                    { 1, 4, 7 },
                                                    { 2, 5, 8 } })),
           */
           "Setting RLE1[0][0] = 42 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 1, 0, 42);
    image1.setPixel(1, 0, (short) 42, (short) 42, (short) 42);
    //System.out.println(rle1.toString());
    
    //System.out.println(image1);
    
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[1][0] = 42 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 0, 1, 2);
    image1.setPixel(0, 1, (short) 2, (short) 2, (short) 2);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[0][1] = 2 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 0, 0, 0);
    image1.setPixel(0, 0, (short) 0, (short) 0, (short) 0);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[0][0] = 0 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 2, 2, 7);
    image1.setPixel(2, 2, (short) 7, (short) 7, (short) 7);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[2][2] = 7 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 2, 2, 42);
    image1.setPixel(2, 2, (short) 42, (short) 42, (short) 42);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[2][2] = 42 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 1, 2, 42);
    image1.setPixel(1, 2, (short) 42, (short) 42, (short) 42);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[1][2] = 42 fails.");


    PixImage image2 = array2PixImage(new int[][] { { 2, 3, 5 },
                                                   { 2, 4, 5 },
                                                   { 3, 4, 6 } });

    System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                       "on another 3x3 image.  Input image:");
    System.out.print(image2);
    RunLengthEncoding rle2 = new RunLengthEncoding(image2);
    rle2.check();
    System.out.println("Testing getWidth/getHeight on a 3x3 encoding.");
    doTest(rle2.getWidth() == 3 && rle2.getHeight() == 3,
           "RLE2 has wrong dimensions");

    System.out.println("Testing toPixImage() on a 3x3 encoding.");
    doTest(rle2.toPixImage().equals(image2),
           "image2 -> RLE2 -> image does not reconstruct the original image");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle2, 0, 1, 2);
    image2.setPixel(0, 1, (short) 2, (short) 2, (short) 2);
    doTest(rle2.toPixImage().equals(image2),
           "Setting RLE2[0][1] = 2 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle2, 2, 0, 2);
    image2.setPixel(2, 0, (short) 2, (short) 2, (short) 2);
    doTest(rle2.toPixImage().equals(image2),
           "Setting RLE2[2][0] = 2 fails.");


    PixImage image3 = array2PixImage(new int[][] { { 0, 5 },
                                                   { 1, 6 },
                                                   { 2, 7 },
                                                   { 3, 8 },
                                                   { 4, 9 } });

    System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                       "on a 5x2 image.  Input image:");
    System.out.print(image3);
    RunLengthEncoding rle3 = new RunLengthEncoding(image3);
    rle3.check();
    System.out.println("Testing getWidth/getHeight on a 5x2 encoding.");
    doTest(rle3.getWidth() == 5 && rle3.getHeight() == 2,
           "RLE3 has wrong dimensions");

    System.out.println("Testing toPixImage() on a 5x2 encoding.");
    doTest(rle3.toPixImage().equals(image3),
           "image3 -> RLE3 -> image does not reconstruct the original image");

    System.out.println("Testing setPixel() on a 5x2 encoding.");
    setAndCheckRLE(rle3, 4, 0, 6);
    image3.setPixel(4, 0, (short) 6, (short) 6, (short) 6);
    doTest(rle3.toPixImage().equals(image3),
           "Setting RLE3[4][0] = 6 fails.");

    System.out.println("Testing setPixel() on a 5x2 encoding.");
    setAndCheckRLE(rle3, 0, 1, 6);
    image3.setPixel(0, 1, (short) 6, (short) 6, (short) 6);
    doTest(rle3.toPixImage().equals(image3),
           "Setting RLE3[0][1] = 6 fails.");

    System.out.println("Testing setPixel() on a 5x2 encoding.");
    setAndCheckRLE(rle3, 0, 0, 1);
    image3.setPixel(0, 0, (short) 1, (short) 1, (short) 1);
    doTest(rle3.toPixImage().equals(image3),
           "Setting RLE3[0][0] = 1 fails.");


    PixImage image4 = array2PixImage(new int[][] { { 0, 3 },
                                                   { 1, 4 },
                                                   { 2, 5 } });

    System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                       "on a 3x2 image.  Input image:");
    System.out.print(image4);
    RunLengthEncoding rle4 = new RunLengthEncoding(image4);
    rle4.check();
    System.out.println("Testing getWidth/getHeight on a 3x2 encoding.");
    doTest(rle4.getWidth() == 3 && rle4.getHeight() == 2,
           "RLE4 has wrong dimensions");

    System.out.println("Testing toPixImage() on a 3x2 encoding.");
    doTest(rle4.toPixImage().equals(image4),
           "image4 -> RLE4 -> image does not reconstruct the original image");

    System.out.println("Testing setPixel() on a 3x2 encoding.");
    setAndCheckRLE(rle4, 2, 0, 0);
    image4.setPixel(2, 0, (short) 0, (short) 0, (short) 0);
    doTest(rle4.toPixImage().equals(image4),
           "Setting RLE4[2][0] = 0 fails.");

    System.out.println("Testing setPixel() on a 3x2 encoding.");
    setAndCheckRLE(rle4, 1, 0, 0);
    image4.setPixel(1, 0, (short) 0, (short) 0, (short) 0);
    doTest(rle4.toPixImage().equals(image4),
           "Setting RLE4[1][0] = 0 fails.");

    System.out.println("Testing setPixel() on a 3x2 encoding.");
    setAndCheckRLE(rle4, 1, 0, 1);
    image4.setPixel(1, 0, (short) 1, (short) 1, (short) 1);
    doTest(rle4.toPixImage().equals(image4),
           "Setting RLE4[1][0] = 1 fails.");
  }
}
