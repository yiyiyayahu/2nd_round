/*
Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
*/

/*
A对应的ASCII码是65
开始写的是char c = (char) ((n%26 + 64); n = n/26;
但是这样的话就发现，26会变成A@
所以应该这样，编号从0-25，然后26进制
*/
public class Solution {
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while(n != 0) {
            char c = (char) ((n-1)%26 + 65);
            sb.insert(0, c);
            n = (n-1)/26;
        }
        return sb.toString();
    }
}
