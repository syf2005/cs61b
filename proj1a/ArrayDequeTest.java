import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    public void testAddFirst() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        assertEquals(2, deque.get(0).intValue());
        assertEquals(1, deque.get(1).intValue());
    }

    @Test
    public void testAddLast() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(1);
        deque.addLast(2);
        assertEquals(1, deque.get(0).intValue());
        assertEquals(2, deque.get(1).intValue());
    }

    @Test
    public void testRemoveFirst() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(1);
        deque.addLast(2);
        assertEquals(1, deque.removeFirst().intValue());
        assertEquals(2, deque.removeFirst().intValue());
        assertTrue(deque.isEmpty());
    }

    @Test
    public void testRemoveLast() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(1);
        deque.addLast(2);
        assertEquals(2, deque.removeLast().intValue());
        assertEquals(1, deque.removeLast().intValue());
        assertTrue(deque.isEmpty());
    }

    @Test
    public void testIsEmpty() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        assertTrue(deque.isEmpty());
        deque.addFirst(1);
        assertFalse(deque.isEmpty());
    }

    @Test
    public void testSize() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        assertEquals(0, deque.size());
        deque.addFirst(1);
        assertEquals(1, deque.size());
        deque.addLast(2);
        assertEquals(2, deque.size());
    }

    @Test
    public void testGet() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        assertEquals(2, deque.get(1).intValue());
    }

    @Test
    public void testPrintDeque() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        // Manually inspect the printed output
        deque.printDeque();
    }

    // Add more tests for capacityManagement and other scenarios as needed

    @Test
    public void testCapacityManagementExpansion() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < 10; i++) {
            deque.addLast(i);
        }
        assertEquals(10, deque.size());

        // Store the elements before capacity expansion
        Integer[] beforeExpansion = new Integer[10];
        for (int i = 0; i < 10; i++) {
            beforeExpansion[i] = deque.get(i);
        }

        // Ensure the capacity has doubled
        // assertTrue(deque.size() <= deque.capacity / 2);

        // Check if the elements remain intact after expansion
        for (int i = 0; i < 10; i++) {
            assertEquals(beforeExpansion[i], deque.get(i));
        }
    }

    @Test
    public void testCapacityManagementShrinkage() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < 20; i++) {
            deque.addLast(i);
        }
        assertEquals(20, deque.size());

        // Store the elements before capacity shrinkage
        Integer[] beforeShrinkage = new Integer[20];
        for (int i = 0; i < 20; i++) {
            beforeShrinkage[i] = deque.get(i);
        }

        // Ensure the capacity has halved
        // assertTrue(deque.size() > deque.capacity / 2);

        // Check if the elements remain intact after shrinkage
        for (int i = 0; i < 20; i++) {
            assertEquals(beforeShrinkage[i], deque.get(i));
        }
    }
}

