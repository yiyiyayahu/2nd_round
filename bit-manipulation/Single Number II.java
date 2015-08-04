/*
Given an array of integers, every element appears three times except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
*/

/*
这道题可能自己还是没有特别理解，所以每次都要看下答案，囧


一个直接的实现就是用大小为 32的数组来记录所有 位上的和
改进做法：
ones: 第ith位上只出现一次
twos: 第ith位上只出现两次
threes: 第ith位上出现三次
发现一个number出现三次，要把ones twos重新置为0
比如5，5，5
ones=101, twos=0, threes=-1
ones=0, twos=101, threes=0
ones=0, twos=0, threes=101

1，2，1，1这种
先考虑twos，如果ones的某个digit和nums[i]一样，那就说明出现了两次，twos |= ones&nums[i];就可以算出来出现两次的数字是什么
ones ^= nums[i]就是出现一次的数字
threes，如果ones和twos都是1的话，说明1出现了三次，就把threes置为0，所以threes = ~(ones&twos)，同时把ones和twos也置为0
        otherwise, threes都是1，所以ones和twos都不变

但是如果出现1和3这样的，
01  ones=01, twos=0, threes=0
11  ones=10, twos=01, threes=0
01  ones=11, twos=01, threes=01 -> ones=10, twos=00
01  ones=11, twos=00, threes=01 -> ones=3, twos=0
*/
public class Solution {
    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0, threes = 0;
        
        for(int i = 0; i < nums.length; i++) {
            twos |= ones&nums[i];
            ones ^= nums[i];
            threes = (ones & twos);
            ones &= ~threes;
            twos &= ~threes;
        }
        return ones;
    }
}
