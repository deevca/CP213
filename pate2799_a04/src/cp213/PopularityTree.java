package cp213;

/**
 * Implements a Popularity Tree. Extends BST.
 *
 * @author your name here
 * @author David Brown
 * @version 2024-01-01
 */
public class PopularityTree<T extends Comparable<T>> extends BST<T> {

	/**
	 * Auxiliary method for valid. May force node rotation if the retrieval count of
	 * the located node item is incremented.
	 *
	 * @param node The node to examine for key.
	 * @param key  The item to search for. Count is updated to count in matching
	 *             node item if key is found.
	 * @return The updated node.
	 */
	private TreeNode<T> retrieveAux(TreeNode<T> node, final CountedValue<T> key) {

		// your code here
		if (node == null) {
			return node;
		}
		if (node.getValue().compareTo(key) > 0) {
			this.comparisons++;
			node = retrieveAux(node.getLeft(), key);
		} else if (node.getValue().compareTo(key) < 0) {
			this.comparisons++;
			node = retrieveAux(node.getRight(), key);
		} else if (node.getValue().compareTo(key) == 0) {
			this.comparisons++;
			node.getValue().incrementCount();
		}
		if (node != null) {
			node.updateHeight();
		}
		return rotateNode(node);
	}

	private TreeNode<T> rotateNode(TreeNode<T> node) {
		if (node == null) {
			return node;
		}
		if (node.getLeft() != null) {
			if (node.getLeft().getValue().getCount() > node.getValue().getCount()) {
				node.updateHeight();
				return rotateLeft(node);
			}
		}
		if (node.getRight() != null) {
			if (node.getRight().getValue().getCount() > node.getValue().getCount()) {
				node.updateHeight();
				return rotateRight(node);
			}
		}
		node.updateHeight();
		return node;
	}

	/**
	 * Performs a left rotation around node.
	 *
	 * @param parent The subtree to rotate.
	 * @return The new root of the subtree.
	 */
	private TreeNode<T> rotateLeft(final TreeNode<T> parent) {

		// your code here
		TreeNode<T> leftNode = parent.getLeft();
		TreeNode<T> centerNode = leftNode.getRight();

		leftNode.setRight(parent);
		parent.setLeft(centerNode);
		parent.updateHeight();
		leftNode.updateHeight();
		return leftNode;
	}

	/**
	 * Performs a right rotation around {@code node}.
	 *
	 * @param parent The subtree to rotate.
	 * @return The new root of the subtree.
	 */
	private TreeNode<T> rotateRight(final TreeNode<T> parent) {

		// your code here
		TreeNode<T> rightNode = parent.getRight();
		TreeNode<T> centerNode = rightNode.getLeft();

		rightNode.setLeft(parent);
		parent.setRight(centerNode);
		parent.updateHeight();
		rightNode.updateHeight();
		return rightNode;
	}

	/**
	 * Replaces BST insertAux - does not increment count on repeated insertion.
	 * Counts are incremented only on retrieve.
	 */
	@Override
	protected TreeNode<T> insertAux(TreeNode<T> node, final CountedValue<T> value) {

		// your code here
		if (node == null) { // Add a new node containing data
			TreeNode<T> new_node = new TreeNode<T>(value);
			this.size++;
			return new_node;
		} else if (value.compareTo(node.getValue()) > 0) {
			node.setRight(insertAux(node.getRight(), value));
		} else if (value.compareTo(node.getValue()) < 0) {
			node.setLeft(insertAux(node.getLeft(), value));
		}
		node.updateHeight();
		return node;
	}

	/**
	 * Auxiliary method for valid. Determines if a subtree based on node is a valid
	 * subtree. An Popularity Tree must meet the BST validation conditions, and
	 * additionally the counts of any node value must be greater than or equal to
	 * the counts of its children.
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
			} else if (minNode.getValue().getCount() < node.getValue().getCount()) {
				return false;
			}
		} else if (maxNode != null) {
			if (maxNode.getValue().compareTo(node.getValue()) < 0) {
				return false;
			} else if (maxNode.getValue().getCount() < node.getValue().getCount()) {
				return false;
			}
		}
		return isValidAux(node.getLeft(), minNode, node) && isValidAux(node.getRight(), node, maxNode);
	}

	/**
	 * Determines whether two PopularityTrees are identical.
	 *
	 * @param target The PopularityTree to compare this PopularityTree against.
	 * @return true if this PopularityTree and target contain nodes that match in
	 *         position, item, count, and height, false otherwise.
	 */
	public boolean equals(final PopularityTree<T> target) {
		return super.equals(target);
	}

	/**
	 * Very similar to the BST retrieve, but increments the value count here instead
	 * of in the insertion.
	 *
	 * @param key The key to search for.
	 */
	@Override
	public CountedValue<T> retrieve(CountedValue<T> key) {

		// your code here
		CountedValue<T> value = null;
		TreeNode<T> node = retrieveAux(this.root, key);
		if (node != null) {
			value = node.getValue();
		}
		return value;
	}

}
