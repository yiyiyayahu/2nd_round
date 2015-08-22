/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to determine if a number is strobogrammatic. The number is represented as a string.

For example, the numbers "69", "88", and "818" are all strobogrammatic.
*/

/*
这么简单的一道题居然写的这么久我也是醉了。。。
开始没有想清楚，只考虑了6和9要对称，但是18是不valid，只有818才行
还有没有考虑到0也是valid。。。
*/
public class Solution {
    public boolean isStrobogrammatic(String num) {
        if(num == null || num.length() == 0) return false;
        
        int start = 0, end = num.length()-1;
        while(start <= end) {
            char cstart = num.charAt(start);
            char cend = num.charAt(end);

            if((cstart=='6' && cend=='9') || (cstart=='9' && cend=='6') || (cstart==cend && isValid(cstart))) {
                start ++;
                end --;
            } else {
                return false;
            }
        }
        return true;
    }
    
    public boolean isValid(char c) {
        return c == '1' || c == '0' || c == '8';
    }
}
