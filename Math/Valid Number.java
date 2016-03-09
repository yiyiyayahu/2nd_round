/*
Validate if a given string is numeric.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
Note: It is intended for the problem statement to be ambiguous. 
You should gather all requirements up front before implementing one.
*/

/*
这个真的好难写啊啊啊啊，给跪。。
主要就是.和e略复杂
".1"  -> true
"0.1" -> true
"0."  -> true
".1." -> false

"e1" -> false
"1e" -> false
"1e1" -> true
"1e+1" -> true
"1e-1" -> true

所以e之前必须要都是数字，.之后必须全部是数字，需要一个isNumBefore的boolean
*/
public class Solution {
    public boolean isNumber(String s) {
        if(s == null || s.length() == 0) return false;
        
        int i = 0, len = s.length();
        while(i < len && s.charAt(i) == ' ') i++;
        if(i < len && (s.charAt(i) == '+' || s.charAt(i) == '-')) i++;
        
        boolean isNumBefore = false;
        while(i < len && Character.isDigit(s.charAt(i)) ) {
            i++;
            isNumBefore = true;
        }
        if(i < len && s.charAt(i) == '.') {
            i ++;
            while(i < len && Character.isDigit(s.charAt(i)) ) {
                i++;
                isNumBefore = true;
            }
        }
        if(isNumBefore && i < len && s.charAt(i) == 'e') {
            i++;
            isNumBefore = false;
            if(i < len && (s.charAt(i) == '+' || s.charAt(i) == '-')) i++;
            while(i < len && Character.isDigit(s.charAt(i)) ) {
                i++;
                isNumBefore = true;
            }
        }
        while(i < len && s.charAt(i) == ' ') i++;
        return isNumBefore && (i==len);
    }
}
