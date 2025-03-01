package cp213;

import java.util.ArrayList;

/**
 * The individual node of a linked structure that stores CountedValue objects.
 * This is a doubly-linked node with left and right pointers to child nodes. The
 * node link can be updated, but not the node value, in order to avoid moving
 * value between nodes. Data structures must be reordered by moving nodes.
 *
 * @author David Brown
 * @version 2024-01-01
 */
public class TreeNode<T extends Comparable<T>> {

    // Attributes
    private CountedValue<T> value = null; // the node value
    private int height = 1; // the node height

    // Links to the child TreeNodes.
    private TreeNode<T> left = null; // pointer to the left child node
    private TreeNode<T> right = null; // pointer to the right child node

    /**
     * Creates a new TreeNode with value value and null links to its child
     * TreeNodes.
     *
     * @param value The value to store in the node.
     */
    public TreeNode(final CountedValue<T> value) {
	this.value = value;
    }

    /**
     * Performs an inorder traversal of a tree adding node value to a list.
     *
     * @param node A TreeNode.
     * @param list The final list of node value.
     */
    private final void inOrderAux(final TreeNode<T> node, final ArrayList<CountedValue<T>> list) {
	if (node != null) {
	    this.inOrderAux(node.getLeft(), list);
	    list.add(new CountedValue<T>(node.getValue()));
	    this.inOrderAux(node.getRight(), list);
	}
	return;
    }

    /**
     * Performs an preorder traversal of a tree adding node value to a list.
     *
     * @param node A TreeNode.
     * @param list The final list of node value.
     */
    private final void preOrderAux(final TreeNode<T> node, final ArrayList<CountedValue<T>> list) {
	if (node != null) {
	    list.add(new CountedValue<T>(node.getValue()));
	    this.preOrderAux(node.getLeft(), list);
	    this.preOrderAux(node.getRight(), list);
	}
	return;
    }

    /**
     * Returns this node value. Not copy safe as it returns a reference to the
     * value, not a copy of the value.
     *
     * @return this node value.
     */
    public CountedValue<T> getValue() {
	return this.value;
    }

    /**
     * Returns the height of this TreeNode.
     *
     * @return this node height.
     */
    public int getHeight() {
	return this.height;
    }

    /**
     * Returns the left child of this TreeNode.
     *
     * @return this left child pointer.
     */
    public TreeNode<T> getLeft() {
	return this.left;
    }

    /**
     * Returns the right child of this TreeNode.
     *
     * @return this right child pointer.
     */
    public TreeNode<T> getRight() {
	return this.right;
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
    public final ArrayList<CountedValue<T>> inOrder() {
	final ArrayList<CountedValue<T>> list = new ArrayList<>();
	this.inOrderAux(this, list);
	return list;
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
    public final ArrayList<CountedValue<T>> levelOrder() {
	final ArrayList<CountedValue<T>> list = new ArrayList<>();
	TreeNode<T> node = this;

	if (this != null) {
	    // Put the nodes for one level into a temporary queue.
	    final ArrayList<TreeNode<T>> queue = new ArrayList<>();
	    queue.add(node);

	    while (queue.size() > 0) {
		// Add the node to the queue
		node = queue.remove(0);
		// Add a copy of the node value to the list of value
		list.add(new CountedValue<T>(node.getValue()));

		if (node.getLeft() != null) {
		    queue.add(node.getLeft());
		}
		if (node.getRight() != null) {
		    queue.add(node.getRight());
		}
	    }
	}
	return list;
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
    public final ArrayList<CountedValue<T>> preOrder() {
	final ArrayList<CountedValue<T>> list = new ArrayList<>();
	this.preOrderAux(this, list);
	return list;
    }

    /**
     * Updates the left child reference of this TreeNode to another TreeNode.
     *
     * @param left this new left child node to link to.
     */
    public void setLeft(final TreeNode<T> left) {
	this.left = left;
    }

    /**
     * Updates the right child reference of this TreeNode to another TreeNode.
     *
     * @param right this new right child node to link to.
     */
    public void setRight(final TreeNode<T> right) {
	this.right = right;
    }

    /**
     * @return a string version of this node including the value and height.
     */
    @Override
    public String toString() {
	return "D: " + this.value + "; H: " + this.height;
    }

    /**
     * Updates the height of this TreeNode to 1 plus the maximum heights of its two
     * child nodes. Empty child nodes are defined to have a height of 0.
     */
    public void updateHeight() {
	int leftHeight = 0;
	int rightHeight = 0;

	if (this.left != null) {
	    leftHeight = this.left.height;
	}
	if (this.right != null) {
	    rightHeight = this.right.height;
	}
	this.height = Math.max(leftHeight, rightHeight) + 1;
	return;
    }

}
