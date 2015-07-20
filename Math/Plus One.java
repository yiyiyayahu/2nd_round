/*
Given a non-negative number represented as an array of digits, plus one to the number.

The digits are stored such that the most significant digit is at the head of the list.
*/

/*
如果碰到那种要进位的，就是result array要多一位的，肯定是1,0,0,0。。。这种的
开始我想着要复制digits到result里面，其实是不用的
*/
public class Solution {
    public int[] plusOne(int[] digits) {
        for(int i = digits.length-1; i >= 0; i--) {
            if(digits[i] < 9) {
                digits[i] += 1;
                return digits;
            } else {
                digits[i] = 0;
            }
        }
        int[] result = new int[digits.length+1];
        result[0] = 1;
        return result;
    }
}
