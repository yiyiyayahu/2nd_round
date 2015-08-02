/*
Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: 
Starting with any positive integer, replace the number by the sum of the squares of its digits, 
and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

Example: 19 is a happy number

12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
*/


/*
这道题的关键就是false的结束条件
it loops endlessly in a cycle
所以用一个set来track之前出现过的结果，如果发现进入一个loop了，就是false了
*/
public class Solution {
    public boolean isHappy(int n) {
        int squareSum = 0, tmp = n;
        HashSet<Integer> set = new HashSet<Integer>();
        
        while(tmp != 1) {
            while(tmp > 0) {
                int digit = tmp%10;
                squareSum += digit * digit;
                tmp = tmp/10;
            }
            if(set.contains(squareSum)) return false;
            
            set.add(squareSum);
            tmp = squareSum;
            squareSum = 0;
        }
        return true;
    }
}
