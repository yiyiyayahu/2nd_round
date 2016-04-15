/*
Given an integer n, return the number of trailing zeroes in n!.

Note: Your solution should be in logarithmic time complexity.
*/

/*
我后来做，试着用
        for(int i = 5; i <= n; i*=5) {
            total += n/i;
        }
总是TLE。。越界了
但是后来我检测了一下这么做，有两个test过不了
Input:
1808548329
Output:
452137075
Expected:
452137076

Explain: The variable i is overflowed. 
n / i == 1 will appear twice in your case, 1220703125 (correct), 1808548329(overflowed). 
The correct value of the overflowed 1808548329 should be 6103515625.

Solution: use long or long long to replace the type of i.
*/
public class Solution {
    public int trailingZeroes(int n) {
        int total = 0;
        for(long i = 5; i <= n; i*=5) {
            total += n/i;
        }
        return total;
    }
}

//wrong version:
public class Solution {
    public int trailingZeroes(int n) {
        int total = 0;
        int limit = Integer.MAX_VALUE/5;
        for(int i = 5; i <= n && i <= limit; i*=5) {
            total += n/i;
        }
        return total;
    }
}
/*
better solution, no overflow issue
*/
public class Solution {
    public int trailingZeroes(int n) {
        int count = 0;
        while(n > 0) {
            count += n/5;
            n = n/5;
        }
        return count;
    }
}
