import static org.junit.Assert.*;

import org.junit.Test;


public class TestArrayDequeGold {
    private int sizeTracker = 0;

    @Test
    public void test1() {
        ArrayDequeSolution<Integer> correct = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> student = new StudentArrayDeque<>();
        for (int i = 0; i < 60; i++) {
            if (StdRandom.uniform() > 0.5) {
                int temp = StdRandom.uniform(1024);
                correct.addFirst(temp);
                student.addFirst(temp);
                sizeTracker++;
                System.out.println("addFirst(" + temp + ")");
            } else {
                int temp = StdRandom.uniform(1024);
                correct.addLast(temp);
                student.addLast(temp);
                sizeTracker++;
                System.out.println("addLast(" + temp + ")");
            }
        }
        for (int i = 0; i < 500000; i++) {
            if (StdRandom.uniform() > 0.5 && sizeTracker > 0) {
                if (StdRandom.uniform() > 0.5) {
                    System.out.println("removeFirst()");
                    assertEquals(correct.removeFirst(), student.removeFirst());
                } else {
                    System.out.println("removeLast()");
                    assertEquals(correct.removeLast(), student.removeLast());
                }
                sizeTracker--;
            } else {
                if (StdRandom.uniform() > 0.5) {
                    int temp = StdRandom.uniform(1024);
                    correct.addFirst(temp);
                    student.addFirst(temp);
                    sizeTracker++;
                    System.out.println("addFirst(" + temp + ")");
                } else {
                    int temp = StdRandom.uniform(1024);
                    correct.addLast(temp);
                    student.addLast(temp);
                    sizeTracker++;
                    System.out.println("addLast(" + temp + ")");
                }
            }
        }
    }


}
