/*
Additive number is a string whose digits can form additive sequence.

A valid additive sequence should contain at least three numbers. Except for the first two numbers, 
each subsequent number in the sequence must be the sum of the preceding two.

For example:
"112358" is an additive number because the digits can form an additive sequence: 1, 1, 2, 3, 5, 8.

1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
"199100199" is also an additive number, the additive sequence is: 1, 99, 100, 199.
1 + 99 = 100, 99 + 100 = 199
Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.

Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.

Follow up:
How would you handle overflow for very large input integers?
*/

/*
这道题思路不难。。。但是我写的code很差。。。下面这个是人家简洁的code
我觉得可能最近这几天我比较急躁
不要急躁啊亲，都复习这么久了不是！慢慢写慢慢想，思路清晰点啦
*/
public class Solution {
    public boolean isAdditiveNumber(String num) {
        if (num == null || num.length() <= 2) return false;
        
        int len = num.length();
        for(int i = 0; i < (len-1)/2; i++) {
            for(int j = i+1; len-j > Math.max(i+1, j-i); j++) {
                String num1 = num.substring(0, i+1);
                String num2 = num.substring(i+1, j+1);
                if(isValid(num1, num2, num.substring(j+1))) return true;
            }
        }
        return false;
    }
    
    public boolean isValid(String num1, String num2, String remain) {
        if (remain.isEmpty()) return true;
        if (num1.charAt(0) == '0' && num1.length() > 1) return false;
        if (num2.charAt(0) == '0' && num2.length() > 1) return false;
        String sum = String.valueOf(Long.parseLong(num1) + Long.parseLong(num2));
        if (!remain.startsWith(sum)) return false;
        return isValid(num2, sum, remain.substring(sum.length()));
    }    
}
