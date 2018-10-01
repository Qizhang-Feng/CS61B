/* HashTableChained.java */

package dict;

import list.*;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/
	SList[] hashtable;
	int BucketsNumber;
	int size;

	static boolean isPrimeNumber(int a) {
		for(int i = 2; i<=Math.sqrt(a); i++) {
			if(a%i==0) {
				return false;
			}
		}
		return true;
	}
	
  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTableChained(int sizeEstimate) {
	  BucketsNumber = 2*sizeEstimate-1;
	  while(!this.isPrimeNumber(BucketsNumber)) {
		  BucketsNumber--;
	  }
	  hashtable = new SList[BucketsNumber];
	  for(int i=0;i<BucketsNumber;i++) {
		  hashtable[i] = new SList();
	  }
	  size = 0;
    // Your solution here.
  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
	  this(51);
	  // Your solution here.
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {
	  return ((((code*2+3)%1999993)+1999993)%1999993)%BucketsNumber;
    // Replace the following line with your solution.1999993
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    // Replace the following line with your solution.
    return size;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    // Replace the following line with your solution.
	  return size==0;
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    // Replace the following line with your solution.
	  Entry NewEntry = new Entry();
	  NewEntry.key = key;
	  NewEntry.value = value;
	  
	  int code = key.hashCode();
	  //System.out.println(code);
	  int CompressedCode = this.compFunction(code);
	  //System.out.println(CompressedCode);
	  //System.out.println(BucketsNumber);
	  if(!hashtable[CompressedCode].isEmpty()) {
		  CompressedCode = this.compFunction(CompressedCode);
	  }
	  hashtable[CompressedCode].insertFront(NewEntry);
	  size++;
    return NewEntry;
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
    // Replace the following line with your solution.
    for(int i = 0; i<BucketsNumber; i++) {
    	if(!hashtable[i].isEmpty()) {
    		SListNode CURRENTNODE = (SListNode) hashtable[i].front();
    		try {
				while(CURRENTNODE.isValidNode()) {
					if(((Entry)CURRENTNODE.item()).key.equals(key)){
						return (Entry)CURRENTNODE.item();
					}
					CURRENTNODE = (SListNode) CURRENTNODE.next();
				}
    		} catch (InvalidNodeException e) {
				// 
				e.printStackTrace();
			}
    	}
    	
    }
	  return null;
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
    // Replace the following line with your solution.
	  for(int i = 0; i<BucketsNumber; i++) {
	    	if(!hashtable[i].isEmpty()) {
	    		SListNode CURRENTNODE = (SListNode) hashtable[i].front();
	    		try {
					while(CURRENTNODE.isValidNode()) {
						if(((Entry)CURRENTNODE.item()).key.equals(key)){
							Entry RemovedEntry = (Entry)CURRENTNODE.item();
							CURRENTNODE.remove();
							size--;
							return RemovedEntry;
						}
						CURRENTNODE = (SListNode) CURRENTNODE.next();
					}
					
	    		} catch (InvalidNodeException e) {
					e.printStackTrace();
				}
	    	}
	    	
	    }
		  return null;
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    // Your solution here.
	  hashtable = new SList[BucketsNumber];
	  for(int i=0;i<BucketsNumber;i++) {
		  hashtable[i] = new SList();
	  }
	  size = 0;
  }

  public String toString() {
	  String str="";
	  for(int i=0; i<BucketsNumber;i++) {
		  str+="Bucket["+i+"]: ";
		  if(!hashtable[i].isEmpty()) {
			 SListNode current = (SListNode) hashtable[i].front();
			 try {
				 while(current.isValidNode()) {
					 str+=current.item()+" ";
					 current = (SListNode) current.next();
				 }
			 }catch (InvalidNodeException e) {
				 e.printStackTrace();
			 }
		  }
		  str+="\n";
	  }
	  return str;
  }
  
  public String histograph() {
	  int[] s = new int[size];
	  for(int i=0;i<BucketsNumber;i++) {
		  s[hashtable[i].length()]++;
	  }
	  String str = "";
	  for(int i=0;i<size;i++) {
		  str += i+"collision: "+s[i]+"\n";
	  }
	  return str;
  }
  
  public static void main(String[] arg) {
	 
	 System.out.println(HashTableChained.isPrimeNumber(9));
  }
}
