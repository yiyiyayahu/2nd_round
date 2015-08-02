/*
Given two strings s and t, write a function to determine if t is an anagram of s.

For example,
s = "anagram", t = "nagaram", return true.
s = "rat", t = "car", return false.

Note:
You may assume the string contains only lowercase alphabets.
*/

public class Solution {
    public boolean isAnagram(String s, String t) {
        if(s == null || t == null) return false;
        if(s.length() != t.length()) return false;
        
        int[] arr = new int[256];
        for(int i = 0; i < s.length(); i++) {
            int sNum = (int)(s.charAt(i));
            arr[sNum] ++;
        }
        for(int i = 0; i < t.length(); i++) {
            int tNum = (int)(t.charAt(i));
            arr[tNum] --;
            if(arr[tNum] < 0) return false;
        }
        return true;
    }
}
