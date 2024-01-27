package synthesizer;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Tests the ArrayRingBuffer class.
 *
 * @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        assertEquals(0, arb.fillCount());
        assertEquals(10, arb.capacity());
        arb.enqueue(5);
        arb.enqueue(6);
        arb.enqueue(7);
        arb.enqueue(8);
        arb.enqueue(9);
        arb.enqueue(10);
        assertEquals(6, arb.fillCount());
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
        Iterator<Integer> iterator = arb.iterator();
        arb.dequeue();
        arb.enqueue(5);
        arb.enqueue(6);
        arb.enqueue(7);
        arb.enqueue(8);
        arb.enqueue(9);
        arb.enqueue(10);
        assertEquals(10, (long) arb.dequeue());
        assertEquals(5, (long) arb.peek());
        assertEquals(6, arb.fillCount());


    }

    /**
     * Calls tests for ArrayRingBuffer.
     */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
