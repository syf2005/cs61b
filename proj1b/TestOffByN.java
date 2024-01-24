import org.junit.Test;

import static org.junit.Assert.*;

public class TestOffByN {

    static CharacterComparator offByN = new OffByN(5);

    @Test
    public void test() {
        assertTrue(offByN.equalChars('a', 'f'));
        assertFalse(offByN.equalChars('f', 'a'));
        assertFalse(offByN.equalChars('f', 'h'));
    }

}
