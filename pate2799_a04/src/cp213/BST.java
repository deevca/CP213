package cp213;

import java.util.ArrayList;

/**
 * Implements a Binary Search Tree.
 *
 * @author your name here
 * @author David Brown
 * @version 2024-01-01
 */
public class BST<T extends Comparable<T>> {

	// Attributes.
	/**
	 * Count of comparisons performed by tree.
	 */
	protected int comparisons = 0;
	/**
	 * Root node of the tree.
	 */
	protected TreeNode<T> root = null;
	/**
	 * Number of nodes in the tree.
	 */
	protected int size = 0;

	/**
	 * Auxiliary method for {@code equals}. Determines whether two subtrees are
	 * identical in values and height.
	 *
	 * @param source Node of this BST.
	 * @param target Node of that BST.
	 * @return true if source and target are identical in values and height.
	 */
	protected boolean equalsAux(final TreeNode<T> source, final TreeNode<T> target) {

		// your code here
		boolean equal = true;
		if (source == null && target != null || source != null && target == null) {
			equal = false;
		}
		if (equal && source.getValue().compareTo(target.getValue()) == 0) {
			equal = equalsAux(source.getLeft(), target.getLeft());
			if (equal) {
				equal = equalsAux(source.getRight(), target.getRight());
			}
		} else {
			equal = false;
		}
		return equal;
	}

	/**
	 * Auxiliary method for insert. Inserts value into this BST.
	 *
	 * @param node  The current node (TreeNode).
	 * @param value Data to be inserted into the tree.
	 * @return The inserted node.
	 */
	protected TreeNode<T> insertAux(TreeNode<T> node, final CountedValue<T> value) {

		if (node == null) {
			// Base case - add a new node containing the value.
			node = new TreeNode<T>(value);
			node.getValue().incrementCount();
			this.size++;
		} else {
			// Compare the node value against the insert value.
			final int result = node.getValue().compareTo(value);

			if (result > 0) {
				// General case - check the left subtree.
				node.setLeft(this.insertAux(node.getLeft(), value));
			} else if (result < 0) {
				// General case - check the right subtree.
				node.setRight(this.insertAux(node.getRight(), value));
			} else {
				// Base case - value is already in the tree, increment its count
				node.getValue().incrementCount();
			}
		}
		node.updateHeight();
		return node;
	}

	/**
	 * Auxiliary method for valid. Determines if a subtree based on node is a valid
	 * subtree.
	 *
	 * @param node    The root of the subtree to test for validity.
	 * @param minNode The node of the minimum value in the current subtree.
	 * @param maxNode The node of the maximum value in the current subtree.
	 * @return true if the subtree base on node is valid, false otherwise.
	 */
	protected boolean isValidAux(final TreeNode<T> node, TreeNode<T> minNode, TreeNode<T> maxNode) {

		// your code here
		if (node == null) {
			return true;
		} else if (minNode != null && minNode.getValue().compareTo(node.getValue()) > 0) {
			return false;
		} else if (maxNode != null && maxNode.getValue().compareTo(node.getValue()) < 0) {
			return false;
		}
		return isValidAux(node.getLeft(), minNode, node) && isValidAux(node.getRight(), node, maxNode);
	}

	/**
	 * Returns the height of a given TreeNode.
	 *
	 * @param node The TreeNode to determine the height of.
	 * @return The height attribute of node, 0 if node is null.
	 */
	protected int nodeHeight(final TreeNode<T> node) {
		int height = 0;

		if (node != null) {
			height = node.getHeight();
		}
		return height;
	}

	/**
	 * Determines if this BST contains key.
	 *
	 * @param key The key to search for.
	 * @return true if this contains key, false otherwise.
	 */
	public boolean contains(final CountedValue<T> key) {

		// your code here
		boolean found = false;
		if (this.root == null) {
			return false;
		} else {
			found = containsAux(key, this.root);
		}
		return found;
	}

	private boolean containsAux(final CountedValue<T> key, final TreeNode<T> node) {
		boolean found = false;
		if (node != null) {
			if (node.getValue().compareTo(key) == 0) {
				found = true;
			} else {
				found = containsAux(key, node.getLeft());
				if (found == false) {
					found = containsAux(key, node.getRight());
				}
			}
		}
		return found;
	}

	/**
	 * Determines whether two trees are identical.
	 *
	 * @param target The tree to compare this BST against.
	 * @return true if this and target contain nodes that match in position, value,
	 *         count, and height, false otherwise.
	 */
	public boolean equals(final BST<T> target) {
		boolean isEqual = false;

		if (this.size == target.size) {
			isEqual = this.equalsAux(this.root, target.root);
		}
		return isEqual;
	}

	/**
	 * Get number of comparisons executed by the retrieve method.
	 *
	 * @return comparisons
	 */
	public int getComparisons() {
		return this.comparisons;
	}

	/**
	 * Returns the height of the root node of this tree.
	 *
	 * @return height of root node, 0 if the root node is null.
	 */
	public int getHeight() {
		int height = 0;

		if (this.root != null) {
			height = this.root.getHeight();
		}
		return height;
	}

	/**
	 * Returns the number of nodes in the tree.
	 *
	 * @return number of nodes in this tree.
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * Returns a list of the value in the current tree. The list contents are in
	 * order from smallest to largest.
	 *
	 * Not thread safe as it assumes contents of the tree are not changed by an
	 * external thread during the loop.
	 *
	 * @return The contents of this tree as a list of value.
	 */
	public ArrayList<CountedValue<T>> inOrder() {
		return this.root.inOrder();
	}

	/**
	 * Inserts value into this tree.
	 *
	 * @param value Data to store.
	 */
	public void insert(final CountedValue<T> value) {
		this.root = this.insertAux(this.root, value);
		return;
	}

	/**
	 * Determines if this tree is empty.
	 *
	 * @return true if this tree is empty, false otherwise.
	 */
	public boolean isEmpty() {

		// your code here
		return this.root == null;
	}

	/**
	 * Determines if this tree is a valid BST; i.e. a node's left child value is
	 * smaller than its value, and its right child value is greater than its value,
	 * and a node's height is equal to the maximum of the heights of its two
	 * children (empty child nodes have a height of 0), plus 1.
	 *
	 * @return true if this tree is a valid BST, false otherwise.
	 */
	public boolean isValid() {
		return this.isValidAux(this.root, null, null);
	}

	/**
	 * Returns a list of the value in the current tree. The list contents are in
	 * node level order starting from the root node. Helps determine the structure
	 * of the tree.
	 *
	 * Not thread safe as it assumes contents of the tree are not changed by an
	 * external thread during the loop.
	 *
	 * @return this tree value as a list of value.
	 */
	public ArrayList<CountedValue<T>> levelOrder() {
		return this.root.levelOrder();
	}

	/**
	 * Returns a list of the value in the current tree. The list contents are in
	 * node preorder.
	 *
	 * Not thread safe as it assumes contents of the tree are not changed by an
	 * external thread during the loop.
	 *
	 * @return The contents of this tree as a list of value.
	 */
	public ArrayList<CountedValue<T>> preOrder() {
		return this.root.preOrder();
	}

	/**
	 * Resets the comparison count to 0.
	 */
	public void resetComparisons() {
		this.comparisons = 0;
		return;
	}

	/**
	 * Retrieves a copy of value matching key (key should have value count of 0).
	 * Returning a complete CountedValue gives access to the value and its count.
	 *
	 * @param key The key to look for.
	 * @return value The complete CountedValue that matches key, null otherwise.
	 */
	public CountedValue<T> retrieve(final CountedValue<T> key) {

		// your code here
		CountedValue<T> value = null;
		TreeNode<T> node = this.root;

		while (node != null && value == null) {
			this.comparisons++;
			if (node.getValue().compareTo(key) > 0) {
				node = node.getLeft();
			} else if (node.getValue().compareTo(key) < 0) {
				node = node.getRight();
			} else if (node.getValue().compareTo(key) == 0) {
				value = node.getValue();
			}
		}
		return value;
	}
}
