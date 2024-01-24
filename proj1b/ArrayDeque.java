public class ArrayDeque<T> implements Deque<T> {
    private T[] data;
    private int capacity;
    private int extendRatio = 2;
    private int frontIndex;
    private int size;

    public ArrayDeque() {
        this.data = (T[]) new Object[8];
        this.capacity = 8;
        this.size = 0;
        this.frontIndex = 0;
    }

    private void capacityManagement() {
        if (size == capacity) {
            T[] extendedData = (T[]) new Object[capacity * extendRatio];
            int ptr = 0;
            for (int i = 0; i < size; i++) {
                extendedData[ptr] = data[(frontIndex + i) % capacity];
                ptr++;
            }
            this.capacity = capacity * extendRatio;
            this.data = extendedData;
            this.frontIndex = 0;
        }
        if (((double) size / capacity) <= 0.4 && capacity >= 16) {
            T[] shortedData = (T[]) new Object[capacity / extendRatio];
            int ptr = 0;
            for (int i = 0; i < size; i++) {
                shortedData[ptr] = data[(frontIndex + i) % capacity];
                ptr++;
            }
            this.capacity = capacity / extendRatio;
            this.data = shortedData;
            this.frontIndex = 0;
        }


    }

    @Override
    public void addFirst(T item) {
        capacityManagement();
        if (frontIndex == 0) {
            frontIndex = capacity - 1;
        } else {
            frontIndex = frontIndex - 1;
        }
        data[frontIndex] = item;
        size++;

    }

    /*
     * 处理前索引位置和后索引位置的方法并不相同，如果都先移动再写入，会导致中间留空
     * 上为历史备注
     * 现删除了后索引位置，操作明显简便
     */
    @Override
    public void addLast(T item) {
        capacityManagement();
        data[(frontIndex + size) % capacity] = item;
        size++;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(data[(frontIndex + i) % capacity] + " ");
        }
    }

    @Override
    public T removeFirst() {
        capacityManagement();
        if (isEmpty()) {
            return null;
        }
        if (frontIndex == capacity - 1) {
            T temp = data[frontIndex];
            data[frontIndex] = null;
            frontIndex = 0;
            size--;
            return temp;
        } else {
            T temp = data[frontIndex];
            data[frontIndex] = null;
            frontIndex++;
            size--;
            return temp;
        }
    }

    @Override
    public T removeLast() {
        capacityManagement();
        if (isEmpty()) {
            return null;
        }
        T temp = data[(frontIndex + size - 1) % capacity];
        data[(frontIndex + size - 1) % capacity] = null;
        size--;
        return temp;
    }

    @Override
    public T get(int index) {
        return data[(frontIndex + index) % capacity];
    }


}
