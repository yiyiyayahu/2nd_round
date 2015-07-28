/*
Implement atoi to convert a string to an integer.

Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.

Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.

Update (2015-02-10):
The signature of the C++ function had been updated. If you still see your function signature accepts a const char * argument, please click the reload button  to reset your code definition.

spoilers alert... click to show requirements for atoi.

Requirements for atoi:
The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
*/

/*
这道题思路理清了就比较好做
重点在于对边界的处理！！！
*/
public class Solution {
    public int myAtoi(String str) {
        if(str == null || str.length() == 0) return 0;
        int i = 0, result = 0;
        boolean isNeg = false;
        while(str.charAt(i) == ' ') i++;
        if(str.charAt(i) == '+') {
            i++;
        } else if(str.charAt(i) == '-') {
            isNeg = true; 
            i++;
        }
        if(!isNumeric(str.charAt(i))) return 0;
        while(i < str.length()) {
            char c = str.charAt(i);
            if(!isNumeric(c)) break;
            if(result > Integer.MAX_VALUE/10 || (result==Integer.MAX_VALUE/10 &&c>='8')) {
                return isNeg ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            result *= 10;
            result += c - '0';
            i++;
        }
        return isNeg ? -result : result;
    }
    public boolean isNumeric(char c) {
        return c>='0' && c<='9';
    }
}
