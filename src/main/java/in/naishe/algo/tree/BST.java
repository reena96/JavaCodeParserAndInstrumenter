package in.naishe.algo.tree;

import in.naishe.algo.utils.Util;

import java.util.Arrays;
import java.util.Random;
//import ast.Readable;



/**
 * Implementation as discussed here: http://code.naishe.in/2012/10/bst-binary-search-tree.html
 * 
 * In most of the implementations I will try to avoid use of 
 * parent pointer {@link Node#parent}, unless it gets hard to do without it.
 * @author Nishant Neeraj (http://naishe.in)
 *
 */
public class BST {
	private static final int ARRAY_LENGTH = 15;
	
	public static void main(String[] args) {
		/*bootstrap some random stuffs*/
		Random rgen = new Random();
		int[] rand = Util.generateIntUniqueArray(ARRAY_LENGTH);
		System.out.println("Array used to create the tree: ");
		Util.print(rand);
		Util.drawHr();
		/*-----------------------------------*/
		
		/*Insert/create a tree*/
		Node root = null;
		for(int val: rand)
			root = insert(root, val);
		System.out.println("Tree created!");
		printInOrder(root);
		System.out.println("\n");
		Util.drawHr();
		/*-----------------------------------*/
		
		/*Do some searches until we get a positive result*/
		boolean found = false;
		while(!found){
			int val = rgen.nextInt(100);
			System.out.println("searching: " + val);
			Node result = search(root, val);
			if(result != null && result.val == val){
				System.out.println("Found: " + val);
				found = true;
			}else{
				System.out.println("Does not exist: " + val);
			}
		}
		Util.drawHr();
		/*-----------------------------------*/
		
		/*find max and min*/
		System.out.println("Tree min: " + findMin(root) + ", Tree max: " + findMax(root));
		Util.drawHr();
		/*-----------------------------------*/
		
		/*sort the array, to make sure we are getting exact successor and predecessor*/
		Arrays.sort(rand);
		System.out.println("NOTE: \n  :If the array contains dupes, the expected value might NOT match.\n  :" +
				"You can always justify the accuracy by manually creating the tree in order of insertion " +
				"and checking. \n  :But this check good for non-dupe array.");
		
		for(int i=0; i<ARRAY_LENGTH/2; i++){
			int k = rgen.nextInt(ARRAY_LENGTH);
			int val = rand[k];
			Node node = search(root, val);
			Node successor = successor(node);
			Node predecessor = predecessor(node);
			
			System.out.println("for: " + val);
			if(k == 0 && predecessor == null){
				System.out.println("\tno predecessor of " + val);
			}else{
				System.out.println("\tFound predecessor: " + predecessor +"; expected: " + rand[k-1]);
			}
			
			if(k == ARRAY_LENGTH - 1 && successor == null){
				System.out.println("\tNo successor of " + val);
			}else{
				System.out.println("\tFound successor: " + successor +"; expected: " + rand[k+1]);
			}
		}
		Util.drawHr();
		/*-----------------------------------*/
		
		/*delete a quarter of the elements?*/
		printInOrder(root);
		Util.blankLine();
		for(int i=0; i<ARRAY_LENGTH/4; i++){
			int k = rgen.nextInt(ARRAY_LENGTH);
			int elem = rand[k]; //this element could possibly be deleted in some earlier iteration. No probs!
			Node toDel = search(root, elem);
			root = delete(root, toDel);
			System.out.println("Deleted '" + elem +"', the remaing is ");
			printInOrder(root);
			Util.blankLine();
			Util.blankLine();
		}
	}

	// we are passing root just in case we had to delete the root
	private static Node delete(Node root, Node toDel) {
		if(toDel == null)
			return root;
		Node parent = toDel.parent;
		
		if(toDel.left == null && toDel.right == null){
		/*No child, just fix parent*/
			if(parent != null){
				if(parent.left == toDel)
					parent.left = null;
				else
					parent.right = null;
					
			}else{
				root = null; //single element in tree!
			}
		}else if(toDel.left == null){
		/*Single child*/
			toDel.right.parent = parent;
			if(parent != null){
				if(toDel == parent.left)
					parent.left = toDel.right;
				else
					parent.right = toDel.right;
			}else{
				root = toDel.right; //new root
			}
		}else if(toDel.right == null){
		/*Single child*/
			toDel.left.parent = toDel.parent;
			if(parent != null){
				if(toDel == parent.left)
					parent.left = toDel.left;
				else
					parent.right = toDel.left;
			}else{
				root = toDel.left; //new root
			}
		}else{
		/*Full node*/
			Node successor = successor(toDel);//you may use predecessor too
			//move contents
			toDel.val = successor.val;
			delete(root, successor);
		}
		
		return root;
	}

	private static Node predecessor(Node node) {
		//if exists a left subtree
		if(node.left != null)
			return findMax(node.left);
		
		Node current = node;
		Node parent = node.parent;
		
		while (parent != null && parent.right != current){
			current = parent;
			parent = current.parent;
		}
		return parent;
	}

	private static Node successor(Node node) {
		// if exists a right subtree get the min of it
		if(node.right != null)
			return findMin(node.right);
		
		//move up the parent
		Node current = node;
		Node parent = node.parent;
		
		while (parent != null && parent.left != current){
			current = parent;
			parent = current.parent;
		}
		
		return parent;
	}

	//	The left most!
	private static Node findMin(Node root) {
		Node current = root;
		
		while(current.left != null)
			current = current.left;
			
		return current;
	}
	
//	The right most!
	private static Node findMax(Node root) {
		Node current = root;
		
		while(current.right != null)
			current = current.right;
			
		return current;
	}

	private static Node search(Node root, int val) {
		if(root == null)
			return null;
		
		if(val == root.val)
			return root;
		else if (val < root.val)
			return search(root.left, val);
		else
			return search(root.right, val);
	}

	/**
	 * In order traversal prints sorted BST
	 * @param root
	 */
	private static void printInOrder(Node root) {
		if (root == null)
			return;
		printInOrder(root.left);
		System.out.print(root.val + ", ");
		printInOrder(root.right);
	}

	private static Node insert(Node root, int val) {
//		base case, no such tree!
		if (root == null)
			return new Node(val);
		
//		two pointer to keep track
		Node current = root;
		Node parent = root;
		Node newNode = new Node(val);
		
//		get appropriate parent
		while (current != null){
			parent = current;
			if(val < current.val)
				current = current.left;
			else
				current = current.right;
		}
		
//		assign
		newNode.parent = parent;
		if(val < parent.val)
			parent.left = newNode;
		else
			parent.right = newNode;
		
		return root;
	}

}
