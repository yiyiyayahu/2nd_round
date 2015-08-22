/*
Given a string, determine if a permutation of the string could form a palindrome.

For example,
"code" -> False, "aab" -> True, "carerac" -> True.

Hint:

Consider the palindromes of odd vs even length. What difference do you notice?
Count the frequency of each character.
If each character occurs even number of times, then it must be a palindrome. How about character which occurs odd number of times?
*/

public class Solution {
    public boolean canPermutePalindrome(String s) {
        if(s == null || s.length() == 0) return false;
        int len = s.length();
        boolean oddIsFine = true;
        if(len % 2 == 0) oddIsFine = false;
        
        int[] charArr = new int[256];
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int value = (int)c;
            charArr[value] ++;
        }
        
        for(int i = 0; i < charArr.length; i++) {
            if(charArr[i] % 2 != 0) {
                if(!oddIsFine) return false;
                else oddIsFine = false;
            }
        }
        return true;
    }
}
