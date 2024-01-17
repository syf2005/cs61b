public class ArrayDeque<T> {
    private T[] data;
    private int capacity;
    private int extendRatio = 2;
    private int frontIndex;
    private int endIndex;
    private int size;

    public ArrayDeque() {
        this.data = (T[]) new Object[8];
        this.capacity = 8;
        this.size = 0;
        this.frontIndex = 0;
        this.endIndex = 0;
    }

    private int[] generateCurrentValidIndex() {
        int[] result = new int[size];
        if (frontIndex <= endIndex) {
            int ptr = 0;
            for (int i = frontIndex; i < endIndex; i++) {
                result[ptr] = i;
                ptr++;
            }
        } else {
            int ptr = 0;
            for (int i = frontIndex; i < capacity; i++) {
                result[ptr] = i;
                ptr++;
            }
            for (int i = 0; i < endIndex; i++) {
                result[ptr] = i;
                ptr++;
            }
        }
        return result;
    }

    private void capacityManagement() {
        if (size == capacity) {
            T[] extendedData = (T[]) new Object[capacity * extendRatio];
            int[] index = generateCurrentValidIndex();
            int ptr = 0;
            for (int element : index) {
                extendedData[ptr] = data[element];
            }
            this.capacity = capacity * extendRatio;
            this.data = extendedData;
            this.frontIndex = 0;
            this.endIndex = size;

        }
        if (((double) size / capacity) < 0.3 && capacity >= 16) {
            T[] shortedData = (T[]) new Object[capacity / extendRatio];
            int[] index = generateCurrentValidIndex();
            int ptr = 0;
            for (int element : index) {
                shortedData[ptr] = data[element];
            }
            this.capacity = capacity / extendRatio;
            this.data = shortedData;
            this.frontIndex = 0;
            this.endIndex = size;
        }


    }

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

    /*处理前索引位置和后索引位置的方法并不相同，如果都先移动再写入，会导致中间留空*/
    public void addLast(T item) {
        if (endIndex == capacity) {
            capacityManagement();
        }
        data[endIndex] = item;
        endIndex = endIndex + 1;
        size++;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        int[] index = generateCurrentValidIndex();
        for (int element : index) {
            System.out.print(data[element] + " ");
        }
    }

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

    public T removeLast() {
        capacityManagement();
        if (isEmpty()) {
            return null;
        }
        if (endIndex == 0) {
            endIndex = capacity - 1;
            size--;
        } else {
            endIndex--;
            size--;
        }
        T temp = data[endIndex];
        data[endIndex] = null;
        return temp;
    }

    public T get(int index) {
        int ptr = frontIndex + index;
        if (ptr >= capacity) {
            ptr = ptr - capacity;
        }
        return data[ptr];
    }


}
