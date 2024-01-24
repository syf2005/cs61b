import org.junit.Test;

import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertFalse(palindrome.isPalindrome("fudanuniversity"));
        assertNotEquals(false, palindrome.isPalindrome("u"));
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("volov"));
        assertTrue(palindrome.isPalindrome("auuuuuua"));
        assertFalse(palindrome.isPalindrome("wwjwww"));
    }

    @Test
    public void testIsPalindromeOverload() {
        OffByOne cc = new OffByOne();
        assertFalse(palindrome.isPalindrome("aba", cc));
        assertTrue(palindrome.isPalindrome("", cc));
        assertTrue(palindrome.isPalindrome("a", cc));
        assertTrue(palindrome.isPalindrome("abb", cc));
        assertFalse(palindrome.isPalindrome("wwjwww", cc));
    }
}
