/*
Given a roman numeral, convert it to an integer.

Input is guaranteed to be within the range from 1 to 3999.
*/

public class Solution {
    public int romanToInt(String s) {
      if(s == null || s.length() == 0) return 0;
    
      int result = getInt(s.charAt(0));
      for(int i = 1; i < s.length(); i++) {
        int curr = getInt(s.charAt(i));
        result += curr;
        int prev = getInt(s.charAt(i-1));
        if(curr > prev) result -= 2*prev;
      }
      return result;
    }
    public int getInt(char c) {
      switch(c) {
        case 'I': return 1;
        case 'V': return 5;
        case 'X': return 10;
        case 'L': return 50;
        case 'C': return 100;
        case 'D': return 500;
        case 'M': return 1000;
        default : return 0;
      }
    }
}
