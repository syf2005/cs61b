public class LinkedListDeque<T> {
    private T content;
    private int length;
    private LinkedListDeque<T> forward;
    private LinkedListDeque<T> backward;
    private LinkedListDeque<T> headHelper;
    private LinkedListDeque<T> tailHelper;


    private LinkedListDeque(T item, LinkedListDeque<T> forward, LinkedListDeque<T> backward) {
        this.content = item;
        this.forward = forward;
        this.backward = backward;
        this.tailHelper = null;
        this.headHelper = null;
        this.length = 0;
    }

    public LinkedListDeque() {
        this.content = null;
        this.length = 0;
        this.forward = null;
        this.backward = null;
        LinkedListDeque<T> head = new LinkedListDeque<>(null, null, null);
        LinkedListDeque<T> tail = new LinkedListDeque<>(null, null, null);
        this.headHelper = head;
        this.tailHelper = tail;
        this.headHelper.forward = this.tailHelper;
        this.tailHelper.backward = this.headHelper;
    }


    public void addFirst(T item) {
        LinkedListDeque<T> ptr1 = headHelper;
        LinkedListDeque<T> ptr2 = ptr1.forward;
        LinkedListDeque<T> node = new LinkedListDeque<>(item, ptr2, ptr1);
        this.headHelper.forward = node;
        ptr2.backward = node;
        this.length++;

    }

    public void addLast(T item) {
        LinkedListDeque<T> ptr1 = tailHelper;
        LinkedListDeque<T> ptr2 = ptr1.backward;
        LinkedListDeque<T> node = new LinkedListDeque<>(item, ptr1, ptr2);
        this.tailHelper.backward = node;
        ptr2.forward = node;
        this.length++;

    }

    public boolean isEmpty() {
        return length == 0;
    }

    public int size() {
        return length;

    }

    public void printDeque() {
        LinkedListDeque<T> ptr = headHelper.forward;
        while (ptr.forward != null) {
            System.out.println(ptr.content);
            ptr = ptr.forward;
        }
    }

    public T removeFirst() {
        if (length > 0) {
            LinkedListDeque<T> ptr1 = headHelper;
            LinkedListDeque<T> ptr2 = ptr1.forward.forward;
            T temp = ptr1.forward.content;
            ptr1.forward.forward = null;
            ptr1.forward.backward = null;
            ptr1.forward = ptr2;
            ptr2.backward = ptr1;
            length--;
            return temp;
        } else {
            return null;
        }
    }

    public T removeLast() {
        if (length > 0) {
            LinkedListDeque<T> ptr1 = tailHelper;
            LinkedListDeque<T> ptr2 = ptr1.backward.backward;
            T temp = ptr1.backward.content;
            ptr1.backward.forward = null;
            ptr1.backward.backward = null;
            ptr1.backward = ptr2;
            ptr2.forward = ptr1;
            length--;
            return temp;
        } else {
            return null;
        }

    }

    public T get(int index) {
        if ((index < 0) || (index > length - 1)) {
            return null;
        } else {
            LinkedListDeque<T> ptr = headHelper.forward;
            for (int i = 0; i < index; i++) {
                ptr = ptr.forward;
            }
            return ptr.content;
        }
    }

    private T getRecursiveHelper(LinkedListDeque<T> ptr, int left) {
        if (left == 0) {
            return ptr.content;
        } else {
            return getRecursiveHelper(ptr.forward, left - 1);
        }
    }

    public T getRecursive(int index) {
        if ((index < 0) || (index > length - 1)) {
            return null;
        } else {
            return getRecursiveHelper(headHelper.forward, index);
        }
    }
}
