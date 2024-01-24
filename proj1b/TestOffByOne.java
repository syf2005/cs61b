import org.junit.Test;

import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void test() {
        assertTrue(offByOne.equalChars('a', 'a'));
        assertTrue(offByOne.equalChars('%', '%'));
        assertTrue(offByOne.equalChars('*', '*'));
        assertTrue(offByOne.equalChars('[', '['));
        assertFalse(offByOne.equalChars('a', 'A'));
        assertFalse(offByOne.equalChars('4', 'v'));
    }

    // Your tests go here.

}
