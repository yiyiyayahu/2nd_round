/*
Given an integer, write a function to determine if it is a power of two.
*/

/*
注意考虑负数的情况
*/
public class Solution {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n-1)) == 0;
    }
}

public class Solution {
    public boolean isPowerOfTwo(int n) {
        if(n <= 0) return false;
        int tmp = n;
        while(tmp != 1) {
            if((tmp^1) < tmp) return false;
            tmp = tmp >> 1;
        }
        return true;
    }
}
