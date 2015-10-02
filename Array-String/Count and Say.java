/*
The count-and-say sequence is the sequence of integers beginning as follows:
1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth sequence.

Note: The sequence of integers will be represented as a string.
*/

public class Solution {
    public String countAndSay(int n) {
        String s = "1";
        String result = "";
        if(n == 1) return s;
        StringBuilder sb = new StringBuilder();
        
        for(int j = 2; j <= n; j++) {
            char c = s.charAt(0);
            int num = 1;            
            for(int i = 1; i < s.length(); i++) {            
                char tmp = s.charAt(i);
                if(tmp == c) {
                    num ++;
                } else {
                    sb.append(num).append(c);
                    c = tmp;
                    num = 1;
                }
            }
            sb.append(num).append(c);
            
            result = sb.toString();  
            sb.setLength(0);            //clear StringBuilder
            s = result;
        }
        return result;       
    }
}
