/*
 Write a program to find the n-th ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

Note that 1 is typically treated as an ugly number.

Hint:

    The naive approach is to call isUgly for every number until you reach the nth one. Most numbers are not ugly. Try to focus your effort on generating only the ugly ones.
    An ugly number must be multiplied by either 2, 3, or 5 from a smaller ugly number.
    The key is how to maintain the order of the ugly numbers. Try a similar approach of merging from three sorted lists: L1, L2, and L3.
    Assume you have Uk, the kth ugly number. Then Uk+1 must be Min(L1 * 2, L2 * 3, L3 * 5).
*/


/*
这个L1，L2，L3其实都是之前的ugly number！
所以用一个array来maintain算出的ugly number，然后用三个指针三个index指向array的element，就是L1,L2,L3的值
自己其实没想出来，看了下答案。。。
又做了一次，想出来啦，开森！但是还是看了题目里的hint的
*/
public class Solution {
    public int nthUglyNumber(int n) {
        int[] arr = new int[n];
        arr[0] = 1;
        int count = 1;
        int i2 = 0, i3 = 0, i5 = 0;
        while(count < n) {
            int m2 = arr[i2], m3 = arr[i3], m5 = arr[i5];
            int curr = Math.min(m2*2, Math.min(m3*3, m5*5));
            arr[count++] = curr;
            if(curr == m2*2) i2++;
            if(curr == m3*3) i3++;
            if(curr == m5*5) i5++;           
        }
        return arr[n-1];
    }
}
