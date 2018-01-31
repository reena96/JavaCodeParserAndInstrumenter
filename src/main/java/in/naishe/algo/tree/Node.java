package in.naishe.algo.tree;

/**
 * I am avoiding getters and setters for simplicity sake.
 * Not the best practice indeed. 
 * @author Nishant Neeraj (http://naishe.in)
 *
 */
public class Node {
	public Node(int val) {
		this.val = val;
	}
	
	public int val;
	public Node left;
	public Node right;
	public Node parent;
	
	@Override
	public String toString() {
		return this.val+"";
	}
}
