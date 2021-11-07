package testqueue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import queue_singlelinkedlist.FifoQueue;

import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class TestAppendFifoQueueTest {
    private FifoQueue<Integer> myIntQueue1;
    private FifoQueue<Integer> myIntQueue2;

    @BeforeEach
    void setUp() {
        myIntQueue1 = new FifoQueue<Integer>();
        myIntQueue2 = new FifoQueue<Integer>();
    }

    @AfterEach
    void tearDown() {
        myIntQueue1 = null;
        myIntQueue2 = null;
    }

    @Test
    void testAppendEmpty() {
        assertThrows(IllegalArgumentException.class, () -> myIntQueue1.append(myIntQueue2)); //Två tomma köer
    }


    @Test
    void testAppendQueueToEmpty() {
        myIntQueue1.offer(1);
        myIntQueue1.offer(2);
        myIntQueue1.offer(3);
        myIntQueue2.append(myIntQueue1);
        for (int i = 1; i <= 3; i++) {
            int k = myIntQueue2.poll();
            assertEquals(i, k, "poll returns incorrect element");
        }
        assertEquals(0, myIntQueue2.size(), "Inte tom");
    }

    @Test
    void testAppendEmptyToQueue() {
        myIntQueue1.offer(1);
        myIntQueue1.offer(2);
        myIntQueue1.offer(3);
        myIntQueue1.append(myIntQueue2);
        for (int i = 1; i <= 3; i++) {
            int k = myIntQueue1.poll();
            assertEquals(i, k, "poll returns incorrect element");
        }
    }

    @Test
    void testAppendTwoQueues() {
        myIntQueue1.offer(1);
        myIntQueue1.offer(2);
        myIntQueue1.offer(3);
        myIntQueue2.offer(1);
        myIntQueue2.offer(2);
        myIntQueue2.offer(3);
        myIntQueue1.append(myIntQueue2);
        assertEquals(6, myIntQueue1.size(), "Inte full");

        for (int i = 1; i <= 3; i++) {
            int k = myIntQueue1.poll();
            assertEquals(i, k, "poll returns incorrect element");
        }
        for (int i = 1; i <= 3; i++) {
            int k = myIntQueue1.poll();
            assertEquals(i, k, "poll returns incorrect element");
        }
        assertEquals(0, myIntQueue1.size(), "Inte tom");
    }

    @Test
    void testAppendQueueWithItself() {
        myIntQueue1.offer(1);
        myIntQueue1.offer(2);
        myIntQueue1.offer(3);
        assertThrows(IllegalArgumentException.class, () -> myIntQueue1.append(myIntQueue1));
    }
}