package bst;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class BinarySearchTree<E extends Comparable<? super E>> {
    BinaryNode<E> root;  // Används också i BSTVisaulizer
    int size;            // Används också i BSTVisaulizer
    private final Comparator<? super E> comparator;
    private ArrayList<E> list;

    /**
     * Constructs an empty binary search tree.
     */
    public BinarySearchTree() {
        this.list = new ArrayList<>();
        this.comparator = Comparable::compareTo;
        this.root = null;
        this.size = 0;
    }

    /**
     * Constructs an empty binary search tree, sorted according to the specified comparator.
     */
    public BinarySearchTree(Comparator<E> comparator) { //denna klassen används bara om klassen inte redan implementerar comparable (som ex String gör), så ex om man gör en egen klass!
        this.comparator = comparator;
        this.root = null;
        this.size = 0;
    }

    public static void main(String[] args) {
        BSTVisualizer visualizer = new BSTVisualizer("Hej", 400, 400);
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(2);
        tree.add(7);
        tree.add(3);
        tree.add(9);
        tree.add(10);
        tree.add(14);
        tree.add(4);
        tree.add(15);
        tree.add(19);
        tree.add(29);
        tree.add(32);
        tree.add(24);
        tree.add(44);
        tree.add(29);
        tree.add(34);
        tree.add(42);
        tree.add(32);
        tree.rebuild();
        visualizer.drawTree(tree);
    }

    /**
     * Inserts the specified element in the tree if no duplicate exists.
     *
     * @param x element to be inserted
     * @return true if the the element was inserted
     */
    public boolean add(E x) {
        int temp = size;
        this.root = recursive(root, x);
        return temp < size;
    }

    private BinaryNode<E> recursive(BinaryNode<E> root, E x) {
        if (root == null) {
            size++;
            return new BinaryNode<>(x);
        }
        if (x.compareTo(root.element) < 0) {
            root.left = recursive(root.left, x);
        } else if (x.compareTo(root.element) > 0) {
            root.right = recursive(root.right, x);
        } else {
            return root;
        }
        return root;
    }

    /**
     * Computes the height of tree.
     *
     * @return the height of the tree
     */
    public int height() {
        return height(this.root);
    }

    private int height(BinaryNode<E> node) {
        if (node == null) return 0;
        else {
            return 1 + Math.max(height(node.left), height(node.right));
        }
    }

    /**
     * Returns the number of elements in this tree.
     *
     * @return the number of elements in this tree
     */
    public int size() {
        return this.size;
    }

    /**
     * Removes all of the elements from this list.
     */
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Print tree contents in inorder.
     */
    public void printTree() {
        printTree(root);
    }

    private void printTree(BinaryNode<E> root) {
        if (root != null) {
            printTree(root.left);
            System.out.println(root.element);
            printTree(root.right);
        }
    }

    /**
     * Builds a complete tree from the elements in the tree.
     */
    public void rebuild() {
        toArray(root, list);
        root = buildTree(list, 0, list.size() - 1);
    }

    /*
     * Adds all elements from the tree rooted at 'root' in inorder to the list sorted.
     */
    private void toArray(BinaryNode<E> root, List<E> sorted) {
        if (root != null) {
            toArray(root.left, list);
            list.add(root.element);
            toArray(root.right, list);
        }
    }

    /*
     * Builds a complete tree from the elements from position first to
     * last in the list sorted.
     * Elements in the list a are assumed to be in ascending order.
     * Returns the root of tree.
     */
    private BinaryNode<E> buildTree(ArrayList<E> sorted, int first, int last) {
        if (first > last) return null;

        int mid = (first + last) / 2;

        BinaryNode<E> temp = new BinaryNode<>(sorted.get(mid));

        temp.left = buildTree(sorted, first, mid - 1);
        temp.right = buildTree(sorted, mid + 1, last);

        return temp;
    }


    static class BinaryNode<E> {
        E element;
        BinaryNode<E> left;
        BinaryNode<E> right;

        private BinaryNode(E element) {
            this.element = element;
        }
    }

}
