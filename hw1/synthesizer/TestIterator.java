package synthesizer;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Iterator;

public class TestIterator {


    @Test
    public void testIterator() {
        // 创建一个 ArrayRingBuffer 对象
        ArrayRingBuffer<Integer> buffer = new ArrayRingBuffer<>(5);

        // 向缓冲区添加元素
        buffer.enqueue(1);
        buffer.enqueue(2);
        buffer.enqueue(3);

        // 创建迭代器
        Iterator<Integer> iterator = buffer.iterator();

        // 使用迭代器遍历缓冲区并进行断言
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(1), iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(2), iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(3), iterator.next());

        assertFalse(iterator.hasNext()); // 迭代器应该没有更多元素
    }


}
