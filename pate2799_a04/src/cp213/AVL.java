package cp213;

/**
 * Implements an AVL (Adelson-Velsky Landis) tree. Extends BST.
 *
 * @author your name here
 * @author David Brown
 * @version 2024-01-01
 */
public class AVL<T extends Comparable<T>> extends BST<T> {

	/**
	 * Returns the balance value of node. If greater than 1, then left heavy, if
	 * less than -1, then right heavy. If in the range -1 to 1 inclusive, the node
	 * is balanced. Used to determine whether to rotate a node upon insertion.
	 *
	 * @param node The TreeNode to analyze for balance.
	 * @return A balance number.
	 */
	private int balance(final TreeNode<T> node) {

		// your code here
		if (node == null || (node.getRight() == null && node.getLeft() == null)) {
			return 0;
		}
		if (node.getLeft() == null) {
			return 0 - node.getHeight();
		}
		if (node.getRight() == null) {
			return node.getHeight();
		}
		return node.getLeft().getHeight() - node.getRight().getHeight();
	}

	/**
	 * Rebalances the current node if its children are not balanced.
	 *
	 * @param node the node to rebalance
	 * @return replacement for the rebalanced node
	 */
	private TreeNode<T> rebalance(TreeNode<T> node) {

		// your code here
		if (node == null) {
			return null;
		}

		// update height to current node
		node.updateHeight();

		// Calculate the balance factor
		int balance = balance(node);

		if (balance > 1) {
			if (balance(node.getLeft()) >= 0) {
				return rotateRight(node);
			} else {
				node.setLeft(rotateLeft(node.getLeft()));
				return rotateRight(node);
			}
		} else if (balance < -1) {
			if (balance(node.getRight()) <= 0) {
				return rotateLeft(node);
			} else {
				node.setRight(rotateRight(node.getRight()));
				return rotateLeft(node);
			}
		}
		return node;
	}

	/**
	 * Performs a left rotation around node.
	 *
	 * @param node The subtree to rotate.
	 * @return The new root of the subtree.
	 */
	private TreeNode<T> rotateLeft(final TreeNode<T> node) {

		// your code here
		// if empty return node
		if (node == null || node.getRight() == null) {
			return node;
		}

		TreeNode<T> rightNode = node.getRight();
		TreeNode<T> centerNode = rightNode.getLeft();

		rightNode.setLeft(node);
		node.setRight(centerNode);

		// new heights
		node.updateHeight();
		rightNode.updateHeight();

		return rightNode;
	}

	/**
	 * Performs a right rotation around node.
	 *
	 * @param node The subtree to rotate.
	 * @return The new root of the subtree.
	 */
	private TreeNode<T> rotateRight(final TreeNode<T> node) {

		// if empty return node
		if (node == null || node.getLeft() == null) {
			return node;
		}

		TreeNode<T> leftNode = node.getLeft();
		TreeNode<T> centerNode = leftNode.getRight();

		leftNode.setRight(node);
		node.setLeft(centerNode);

		// new heights
		node.updateHeight();
		leftNode.updateHeight();

		return leftNode;
	}

	/**
	 * Auxiliary method for insert. Inserts value into this AVL.
	 *
	 * @param node  The current node (TreeNode).
	 * @param value Data to be inserted into the node.
	 * @return The inserted node.
	 */
	@Override
	protected TreeNode<T> insertAux(TreeNode<T> node, final CountedValue<T> value) {

		// your code here
		if (node == null) { // Add a new node containing data
			TreeNode<T> new_node = new TreeNode<T>(value);
			this.size++;
			new_node.getValue().incrementCount();
			return new_node;
		} else if (value.compareTo(node.getValue()) > 0) {
			node.setRight(insertAux(node.getRight(), value));
		} else if (value.compareTo(node.getValue()) < 0) {
			node.setLeft(insertAux(node.getLeft(), value));
		} else {
			node.getValue().incrementCount();
			return node;
		}
		node.updateHeight();
		return rotateNode(node);
	}

	private TreeNode<T> rotateNode(TreeNode<T> node) {
		int balance = balance(node);
		if (balance > 1) {
			if (balance(node.getLeft()) < 0) {
				node.setLeft(rotateLeft(node.getLeft()));
			}
			return rotateRight(node);
		} else if (balance < -1) {
			if (balance(node.getRight()) > 0) {
				node.setRight(rotateRight(node.getRight()));
			}
			return rotateLeft(node);
		}
		return node;
	}

	/**
	 * Auxiliary method for valid. Determines if a subtree based on node is a valid
	 * subtree. An AVL must meet the BST validation conditions, and additionally be
	 * balanced in all its subtrees - i.e. the difference in height between any two
	 * children must be no greater than 1.
	 *
	 * @param node The root of the subtree to test for validity.
	 * @return true if the subtree base on node is valid, false otherwise.
	 */
	@Override
	protected boolean isValidAux(final TreeNode<T> node, TreeNode<T> minNode, TreeNode<T> maxNode) {

		// your code here
		if (node == null) {
			return true;
		} else if (minNode != null) {
			if (minNode.getValue().compareTo(node.getValue()) > 0) {
				return false;
			}
		} else if (maxNode != null) {
			if (maxNode.getValue().compareTo(node.getValue()) < 0) {
				return false;
			}
		}
		return isValidAux(node.getLeft(), minNode, node) && isValidAux(node.getRight(), node, maxNode);
	}

	/**
	 * Determines whether two AVLs are identical.
	 *
	 * @param target The AVL to compare this AVL against.
	 * @return true if this AVL and target contain nodes that match in position,
	 *         value, count, and height, false otherwise.
	 */
	public boolean equals(final AVL<T> target) {
		return super.equals(target);
	}

}
