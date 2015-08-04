/*
Given an array of integers, every element appears three times except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
*/

/*
这道题可能自己还是没有特别理解，所以每次都要看下答案，囧
threes出现三次的，但是发现一个number出现三次，要把ones twos threes都重新置为0
1，2，1，1这种
先考虑twos，如果ones的某个digit和nums[i]一样，那就说明出现了两次，twos |= ones&nums[i];就可以算出来出现两次的数字是什么
ones ^= nums[i]就是出现一次的数字
threes，如果ones和twos都是1的话，说明1出现了三次，就把threes置为0，所以threes = ~(ones&twos)，同时把ones和twos也置为0
*/
public class Solution {
    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0, threes = 0;
        
        for(int i = 0; i < nums.length; i++) {
            twos |= ones&nums[i];
            ones ^= nums[i];
            threes = ~(ones & twos);
            ones &= threes;
            twos &= threes;
        }
        return ones;
    }
}
