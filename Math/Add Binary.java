/*
Given two binary strings, return their sum (also a binary string).

For example,
a = "11"
b = "1"
Return "100".
*/

public class Solution {
    public String addBinary(String a, String b) {
        if(a == null || b == null) return null;
        int len1 = a.length(), len2 = b.length();
        int len = Math.max(len1, len2);
        char[] arr = new char[len];
        int carry = 0;
        int i = len1-1, j = len2-1, index = len-1;
        while(index >= 0) {
            int sum = (i>=0 ? a.charAt(i)-'0' : 0) + (j >= 0 ? b.charAt(j)-'0' : 0) + carry;
            if(sum >= 2) {
                sum -= 2;
                carry = 1;
            } else {
                carry = 0;
            }
            arr[index--] = (char)(sum+'0');
            i --;
            j --;
        }
        String ret = String.copyValueOf(arr);
        if(carry > 0) ret = "1" + ret;
        return ret;
     }
}
