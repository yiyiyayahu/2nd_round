/*
Given an integer, write a function to determine if it is a power of two. 
*/

/*
额，位运算还是要练一下，总是不记得。。。
*/
public class Solution {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n-1)) == 0;
    }
}
