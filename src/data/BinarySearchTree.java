package data;

public class BinarySearchTree {
	
	private long value;
	private BinarySearchTree left, right;
	
//	public BinarySearchTree () {
//		
//	}
	
	public BinarySearchTree(long value) {
		this.value = value;
	}
	
	public void add(long value) {
		if(this.value > value) {
			if(left == null) {
				left = new BinarySearchTree(value);
			} else {
				left.add(value);
			}
		} else if (this.value > value) {
			if(right == null) {
				right = new BinarySearchTree(value);
			} else {
				right.add(value);
			}
		}
	}
	
	public boolean contains(long value) {
		if(this.value == value) {
			return true;
		} else {
			if(this.value > value && left != null) {
				return left.contains(value);
			} else if(right != null) {
				return right.contains(value);
			}
		}
		return false;
	}
}
