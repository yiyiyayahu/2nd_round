/*
Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. 
Find the two elements that appear only once.

For example:

Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].

Note:
The order of the result is not important. So in the above example, [5, 3] is also correct.
Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
*/

/*
这个好难想啊。。。
如果所有都异或出来就是a^b，然后怎么把a，b分开呢，肯定还要遍历一遍nums
sum是所有异或的结果，那就是肯定有一位是1，也就是a和b不同的那一位
比如 110 - a和b在第一位和第二位不同
然后在array里面，可以分成两部分，一部分那一位和a想同，一部分和b想同。然后又因为其他的数字都出现了两次，所以就转化为I了
关键是如何找出其中不同的那一位呢，bit manipulation好难哇
110 & 101 = 100  100 ^ 110 = 010 
*/

public class Solution {
    public int[] singleNumber(int[] nums) {
        int sum = 0, a = 0, b = 0;
        for(int i = 0; i < nums.length; i++) {
            sum ^= nums[i];
        }
        //find the first different bit between a and b
        sum = (sum & (sum-1)) ^ sum;
        
        for(int i = 0; i < nums.length; i++) {
            if((sum & nums[i]) == 0) {
                a ^= nums[i];
            } else {
                b ^= nums[i];
            }
        }
        return new int[]{a, b};
    }
}
