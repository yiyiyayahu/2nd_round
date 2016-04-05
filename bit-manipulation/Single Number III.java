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

其实第二遍看还是不是很懂，是这样的
sum呢就是所有nums[i] xor的结果，所以最后也就是a ^ b, 存着所有a和b不同的bit
之所以要找到least significant different bit是因为只有这样才能通过 (sum&nums[i])==0区分开，不然就都不是0了
举个例子 a=5,b=2,就是101和010，那sum的结果是111，三位都不一样，找出最小不一样的位置就是最后的1，sum变成001
然后发现如果nums[i]=5的话结果肯定不是0，但是nums[i]是2的话&出来就是0
也就是说，最后的sum的值，肯定是a里面有b里面没有，或者a里面没有b里面有的，这样才能区分开
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
