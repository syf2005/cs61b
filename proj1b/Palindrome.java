public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        if (word == null) {
            return null;
        }
        Deque<Character> theDeque = new ArrayDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            theDeque.addLast(word.charAt(i));
        }
        return theDeque;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> wordDeque = wordToDeque(word);
        if (wordDeque.size() == 0) {
            return true;
        }
        if (wordDeque.size() == 1) {
            return true;
        }
        int result = 1;
        while (wordDeque.size() > 0) {
            Character temp1 = wordDeque.removeFirst();
            Character temp2 = wordDeque.removeLast();
            if (temp1 == null || temp2 == null) {
                continue;
            } else {
                if (temp1 != temp2) {
                    result = 0;
                }
            }
        }
        return result == 1;
    }

    private boolean isPalindromeOverloadHelper(Deque<Character> wordDeque, CharacterComparator cc) {
        if (wordDeque.size() == 0) {
            return true;
        }
        if (wordDeque.size() == 1) {
            return true;
        } else {
            if (cc.equalChars(wordDeque.removeFirst(), wordDeque.removeLast())) {
                return isPalindromeOverloadHelper(wordDeque, cc);
            } else {
                return false;
            }
        }
    }


    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wordDeque = wordToDeque(word);
        return isPalindromeOverloadHelper(wordDeque, cc);
    }
}
