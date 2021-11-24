package bst;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest<E extends Comparable<E>> {
    private BinarySearchTree<Integer> tree1;
    private BinarySearchTree<String> tree2;

    @BeforeEach
    void setUp() {
        tree1 = new BinarySearchTree<>();
        tree2 = new BinarySearchTree<>(String::compareTo); //Behövs egentligen inte med String
    }

    @AfterEach
    void tearDown() {
        tree1 = null;
        tree2 = null;
    }

    @Test
    void testAdd() {
        tree1.add(2);
        assertTrue( tree1.add(7));
        tree1.add(3);
        assertEquals(3, tree1.size());
        assertFalse(tree1.add(2));
        assertEquals(3, tree1.size());

        tree2.add("A");
        assertTrue(tree2.add("B"));
        tree2.add("C");
        assertEquals(3, tree2.size());
        assertFalse(tree2.add("A"));
    }

    @Test
    void testHeight() {
        assertEquals(0,tree1.height());

        tree1.add(2);
        tree1.add(7);
        tree1.add(3);
        tree1.add(9);
        tree1.add(10);
        tree1.add(14);
        tree1.add(4);
        tree1.add(15);
        tree1.add(19);
        tree1.add(29);
        assertEquals(8, tree1.height());
    }

    @Test
    void testSize() {
        tree1.add(2);
        tree1.add(7);
        tree1.add(3);
        tree1.add(9);
        tree1.add(10);
        tree1.add(14);
        tree1.add(4);
        tree1.add(15);
        tree1.add(19);
        tree1.add(29);
        assertEquals(10, tree1.size());
    }

    @Test
    void testClear() {
        tree1.add(5);
        tree1.add(2);
        tree1.add(9);
        tree1.add(4);
        tree1.add(6);
        tree1.add(8);
        assertEquals(6, tree1.size());
        tree1.clear();
        assertEquals(0,tree1.size());
    }

    @Test
    void testPrintTree() {
        tree1.add(5);
        tree1.add(2);
        tree1.add(9);
        tree1.add(4);
        tree1.add(6);
        tree1.add(8);
        tree1.printTree();

        tree2.add("H");
        tree2.add("B");
        tree2.add("I");
        tree2.add("A");
        tree2.printTree();

    }

    @Test
    void testRebuild() {
    }
}