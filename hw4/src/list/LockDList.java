package list;

public class LockDList extends DList{
	public LockDList() {
		super();
	}
	
	protected DListNode newNode(Object item, DListNode prev, DListNode next) {
	    return new LockDListNode(item, prev, next);
	  }
	
	public void lockNode(DListNode node) {
	((LockDListNode) node).islocked = true;
	}

	public void remove(DListNode node) {
		if(((LockDListNode) node).islocked == true) {
			return;
		}
		else {
			super.remove(node);
		}
	}
	
	
}
