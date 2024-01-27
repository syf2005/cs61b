package synthesizer;


import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Consider if we need the last tracker. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        this.capacity = capacity;
        this.first = 0;
        this.last = 0;
        this.fillCount = 0;
        this.rb = (T[]) new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        last = (last + 1) % capacity;
        fillCount++;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T temp = rb[first];
        fillCount--;
        first = (first + 1) % capacity;
        return temp;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    private class MyIterator implements Iterator<T> {
        private ArrayRingBuffer<T> data;

        private void copyOrigin() {
            T temp;
            int count = fillCount;
            for (int i = 0; i < count; i++) {
                temp = dequeue();
                enqueue(temp);
                data.enqueue(temp);
            }
        }

        MyIterator() {
            data = new ArrayRingBuffer<>(fillCount);
            copyOrigin();
        }


        @Override
        public boolean hasNext() {
            return data.fillCount >= 1;
        }

        @Override
        public T next() {
            return data.dequeue();
        }
    }

    @Override
    public Iterator<T> iterator() {

        return new MyIterator();
    }

}
